/**
 * Copyright (c) 2004 - 2010 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 *    Stefan Winkler - major refactoring
 *    Stefan Winkler - Bug 271444: [DB] Multiple refactorings bug 271444
 *    Stefan Winkler - Bug 282976: [DB] Influence Mappings through EAnnotations
 *    Kai Schlamp - Bug 284680 - [DB] Provide annotation to bypass ClassMapping
 *    Stefan Winkler - maintenance
 */
package org.eclipse.emf.cdo.server.internal.db.mapping;

import org.eclipse.emf.cdo.common.branch.CDOBranch;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.model.CDOModelUtil;
import org.eclipse.emf.cdo.common.model.EMFUtil;
import org.eclipse.emf.cdo.common.revision.CDORevisionHandler;
import org.eclipse.emf.cdo.server.db.IDBStore;
import org.eclipse.emf.cdo.server.db.IDBStoreAccessor;
import org.eclipse.emf.cdo.server.db.IMetaDataManager;
import org.eclipse.emf.cdo.server.db.IPreparedStatementCache;
import org.eclipse.emf.cdo.server.db.mapping.IClassMapping;
import org.eclipse.emf.cdo.server.db.mapping.IListMapping;
import org.eclipse.emf.cdo.server.db.mapping.IMappingStrategy;
import org.eclipse.emf.cdo.server.db.mapping.ITypeMapping;
import org.eclipse.emf.cdo.server.internal.db.CDODBSchema;
import org.eclipse.emf.cdo.server.internal.db.DBAnnotation;
import org.eclipse.emf.cdo.server.internal.db.ObjectIDIterator;
import org.eclipse.emf.cdo.spi.common.commit.CDOChangeSetSegment;
import org.eclipse.emf.cdo.spi.common.model.InternalCDOPackageInfo;
import org.eclipse.emf.cdo.spi.common.model.InternalCDOPackageRegistry;
import org.eclipse.emf.cdo.spi.common.model.InternalCDOPackageUnit;
import org.eclipse.emf.cdo.spi.server.InternalRepository;

import org.eclipse.net4j.db.DBException;
import org.eclipse.net4j.db.DBUtil;
import org.eclipse.net4j.db.ddl.IDBSchema;
import org.eclipse.net4j.db.ddl.IDBTable;
import org.eclipse.net4j.util.ImplementationError;
import org.eclipse.net4j.util.StringUtil;
import org.eclipse.net4j.util.collection.CloseableIterator;
import org.eclipse.net4j.util.lifecycle.Lifecycle;
import org.eclipse.net4j.util.om.monitor.OMMonitor;
import org.eclipse.net4j.util.om.monitor.OMMonitor.Async;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This abstract base class implements those methods which are most likely common to most mapping strategies. It can be
 * used to derive custom mapping strategy implementation.
 * 
 * @author Eike Stepper
 * @since 2.0
 */
public abstract class AbstractMappingStrategy extends Lifecycle implements IMappingStrategy
{
  // --------- database name generation strings --------------
  protected static final String NAME_SEPARATOR = "_"; //$NON-NLS-1$

  protected static final String TYPE_PREFIX_FEATURE = "F"; //$NON-NLS-1$

  protected static final String TYPE_PREFIX_CLASS = "C"; //$NON-NLS-1$

  protected static final String TYPE_PREFIX_PACKAGE = "P"; //$NON-NLS-1$

  /**
   * Prefix for unsettable feature helper columns
   */
  protected static final String CDO_SET_PREFIX = "cdo_set_"; //$NON-NLS-1$

  protected static final String FEATEURE_TABLE_SUFFIX = "_list"; //$NON-NLS-1$

  private IDBStore store;

  private Map<String, String> properties;

  private ConcurrentMap<EClass, IClassMapping> classMappings;

  private boolean allClassMappingsCreated;

  public AbstractMappingStrategy()
  {
    classMappings = new ConcurrentHashMap<EClass, IClassMapping>();
  }

  // -- property related methods -----------------------------------------

  public synchronized Map<String, String> getProperties()
  {
    if (properties == null)
    {
      properties = new HashMap<String, String>();
    }

    return properties;
  }

  public synchronized void setProperties(Map<String, String> properties)
  {
    this.properties = properties;
  }

  private int getMaxTableNameLength()
  {
    String value = getProperties().get(PROP_MAX_TABLE_NAME_LENGTH);
    return value == null ? store.getDBAdapter().getMaxTableNameLength() : Integer.valueOf(value);
  }

  private int getMaxFieldNameLength()
  {
    String value = getProperties().get(PROP_MAX_FIELD_NAME_LENGTH);
    return value == null ? store.getDBAdapter().getMaxFieldNameLength() : Integer.valueOf(value);
  }

  private boolean isQualifiedNames()
  {
    String value = getProperties().get(PROP_QUALIFIED_NAMES);
    return value == null ? false : Boolean.valueOf(value);
  }

  private boolean isForceNamesWithID()
  {
    String value = getProperties().get(PROP_FORCE_NAMES_WITH_ID);
    return value == null ? false : Boolean.valueOf(value);
  }

  private String getTableNamePrefix()
  {
    String value = getProperties().get(PROP_TABLE_NAME_PREFIX);
    return StringUtil.safe(value);
  }

  // -- getters and setters ----------------------------------------------

  public final IDBStore getStore()
  {
    return store;
  }

  public final void setStore(IDBStore dbStore)
  {
    checkInactive();
    store = dbStore;
  }

  protected final IMetaDataManager getMetaDataManager()
  {
    return getStore().getMetaDataManager();
  }

  // -- object id related methods ----------------------------------------

  public void handleRevisions(IDBStoreAccessor accessor, EClass eClass, CDOBranch branch, long timeStamp,
      CDORevisionHandler handler)
  {
    if (eClass == null)
    {
      for (IClassMapping mapping : getClassMappings().values())
      {
        mapping.handleRevisions(accessor, branch, timeStamp, handler);
      }
    }
    else
    {
      IClassMapping classMapping = getClassMapping(eClass);
      classMapping.handleRevisions(accessor, branch, timeStamp, handler);
    }
  }

  public Set<CDOID> readChangeSet(IDBStoreAccessor accessor, CDOChangeSetSegment[] segments)
  {
    Set<CDOID> result = new HashSet<CDOID>();
    for (IClassMapping mapping : getClassMappings().values())
    {
      Set<CDOID> ids = mapping.readChangeSet(accessor, segments);
      result.addAll(ids);
    }

    return result;
  }

  public CloseableIterator<CDOID> readObjectIDs(IDBStoreAccessor accessor)
  {
    Collection<EClass> classes = getClassesWithObjectInfo();
    final Iterator<EClass> classIt = classes.iterator();

    return new ObjectIDIterator(this, accessor)
    {
      private PreparedStatement currentStatement;

      @Override
      protected ResultSet getNextResultSet()
      {
        while (classIt.hasNext())
        {
          EClass eClass = classIt.next();
          IClassMapping mapping = getClassMapping(eClass);
          currentStatement = mapping.createObjectIDStatement(getAccessor());

          ResultSet rset = null;

          try
          {
            rset = currentStatement.executeQuery();
            return rset;
          }
          catch (Exception ex)
          {
            DBUtil.close(rset); // only on error
            releaseCurrentStatement();
            throw new DBException(ex);
          }
        }

        return null;
      }

      @Override
      protected void closeCurrentResultSet()
      {
        super.closeCurrentResultSet();
        releaseCurrentStatement();
      }

      private void releaseCurrentStatement()
      {
        IPreparedStatementCache statementCache = getAccessor().getStatementCache();
        statementCache.releasePreparedStatement(currentStatement);
        currentStatement = null;
      }
    };
  }

  protected abstract Collection<EClass> getClassesWithObjectInfo();

  // -- database name demangling methods ---------------------------------

  public String getTableName(ENamedElement element)
  {
    String name = null;
    String typePrefix = null;

    if (element instanceof EClass)
    {
      typePrefix = TYPE_PREFIX_CLASS;
      name = DBAnnotation.TABLE_NAME.getValue(element);
      if (name == null)
      {
        name = isQualifiedNames() ? EMFUtil.getQualifiedName((EClass)element, NAME_SEPARATOR) : element.getName();
      }
    }
    else if (element instanceof EPackage)
    {
      typePrefix = TYPE_PREFIX_PACKAGE;
      name = DBAnnotation.TABLE_NAME.getValue(element);
      if (name == null)
      {
        name = isQualifiedNames() ? EMFUtil.getQualifiedName((EPackage)element, NAME_SEPARATOR) : element.getName();
      }
    }
    else
    {
      throw new ImplementationError("Unknown element: " + element); //$NON-NLS-1$
    }

    String prefix = getTableNamePrefix();
    if (prefix.length() != 0 && !prefix.endsWith(NAME_SEPARATOR))
    {
      prefix += NAME_SEPARATOR;
    }

    return getName(prefix + name, typePrefix + getMetaDataManager().getMetaID(element), getMaxTableNameLength());
  }

  public String getTableName(EClass eClass, EStructuralFeature feature)
  {
    String name = DBAnnotation.TABLE_NAME.getValue(eClass);
    if (name == null)
    {
      name = isQualifiedNames() ? EMFUtil.getQualifiedName(eClass, NAME_SEPARATOR) : eClass.getName();
    }

    name += NAME_SEPARATOR;
    name += feature.getName();
    name += FEATEURE_TABLE_SUFFIX;

    String prefix = getTableNamePrefix();
    if (prefix.length() != 0 && !prefix.endsWith(NAME_SEPARATOR))
    {
      prefix += NAME_SEPARATOR;
    }

    return getName(prefix + name, TYPE_PREFIX_FEATURE + getMetaDataManager().getMetaID(feature),
        getMaxTableNameLength());
  }

  public String getFieldName(EStructuralFeature feature)
  {
    String name = DBAnnotation.COLUMN_NAME.getValue(feature);
    if (name == null)
    {
      name = getName(feature.getName(), TYPE_PREFIX_FEATURE + getMetaDataManager().getMetaID(feature),
          getMaxFieldNameLength());
    }

    return name;
  }

  public String getUnsettableFieldName(EStructuralFeature feature)
  {
    String name = DBAnnotation.COLUMN_NAME.getValue(feature);
    if (name != null)
    {
      return CDO_SET_PREFIX + name;
    }

    return getName(CDO_SET_PREFIX + feature.getName(), TYPE_PREFIX_FEATURE + getMetaDataManager().getMetaID(feature),
        getMaxFieldNameLength());
  }

  private String getName(String name, String suffix, int maxLength)
  {
    boolean forceNamesWithID = isForceNamesWithID();
    if (store.getDBAdapter().isReservedWord(name))
    {
      forceNamesWithID = true;
    }

    if (name.length() > maxLength || forceNamesWithID)
    {
      suffix = NAME_SEPARATOR + suffix.replace('-', 'S');
      int length = Math.min(name.length(), maxLength - suffix.length());
      name = name.substring(0, length) + suffix;
    }

    return name;
  }

  // -- factories for mapping of classes, values, lists ------------------

  public void createMapping(Connection connection, InternalCDOPackageUnit[] packageUnits, OMMonitor monitor)
  {
    Async async = null;
    monitor.begin();

    try
    {
      async = monitor.forkAsync();

      try
      {
        mapPackageUnits(packageUnits, connection, false);
      }
      finally
      {
        if (async != null)
        {
          async.stop();
        }
      }
    }
    finally
    {
      monitor.done();
    }
  }

  public void removeMapping(Connection connection, InternalCDOPackageUnit[] packageUnits)
  {
    mapPackageUnits(packageUnits, connection, true);
  }

  private void mapPackageUnits(InternalCDOPackageUnit[] packageUnits, Connection connection, boolean unmap)
  {
    if (packageUnits != null && packageUnits.length != 0)
    {
      for (InternalCDOPackageUnit packageUnit : packageUnits)
      {
        mapPackageInfos(packageUnit.getPackageInfos(), connection, unmap);
      }
    }
  }

  private void mapPackageInfos(InternalCDOPackageInfo[] packageInfos, Connection connection, boolean unmap)
  {
    for (InternalCDOPackageInfo packageInfo : packageInfos)
    {
      EPackage ePackage = packageInfo.getEPackage();
      if (!CDOModelUtil.isCorePackage(ePackage))
      {
        mapClasses(EMFUtil.getPersistentClasses(ePackage), connection, unmap);
      }
    }
  }

  private void mapClasses(EClass[] eClasses, Connection connection, boolean unmap)
  {
    for (EClass eClass : eClasses)
    {
      if (!(eClass.isInterface() || eClass.isAbstract()))
      {
        if (!unmap)
        {
          String mapping = DBAnnotation.TABLE_MAPPING.getValue(eClass);

          // TODO Maybe we should explicitly report unknown values of the annotation
          if (mapping == null || !mapping.equalsIgnoreCase(DBAnnotation.TABLE_MAPPING_NONE))
          {
            IClassMapping classMapping = createClassMapping(eClass);
            getStore().getDBAdapter().createTables(classMapping.getDBTables(), connection);
          }
        }
        else
        {
          IClassMapping mapping = removeClassMapping(eClass);
          getStore().getDBAdapter().dropTables(mapping.getDBTables(), connection);
        }
      }
    }
  }

  private IClassMapping createClassMapping(EClass eClass)
  {
    IClassMapping mapping = doCreateClassMapping(eClass);
    if (mapping != null)
    {
      classMappings.put(eClass, mapping);
    }

    return mapping;
  }

  private IClassMapping removeClassMapping(EClass eClass)
  {
    IClassMapping mapping = classMappings.get(eClass);
    if (mapping != null)
    {
      IDBSchema schema = getStore().getDBSchema();
      for (IDBTable table : mapping.getDBTables())
      {
        ((CDODBSchema)schema).removeTable(table.getName());
      }
      classMappings.remove(eClass);
    }
    return mapping;
  }

  protected abstract IClassMapping doCreateClassMapping(EClass eClass);

  public final IClassMapping getClassMapping(EClass eClass)
  {
    // Try without synchronization first; this will almost always succeed, so it avoids the
    // performance penalty of syncing in the majority of cases
    IClassMapping result = classMappings.get(eClass);
    if (result == null)
    {
      // Synchronize on the classMappings to prevent concurrent invocations of createClassMapping
      // (Synchronizing on the eClass allows for more concurrency, but is risky because application
      // code may be syncing on the eClass also.)
      synchronized (classMappings)
      {
        // Check again, because other thread may have just added the mapping
        result = classMappings.get(eClass);
        if (result == null)
        {
          result = createClassMapping(eClass);
        }
      }
    }

    return result;
  }

  public final Map<EClass, IClassMapping> getClassMappings()
  {
    return getClassMappings(true);
  }

  public final Map<EClass, IClassMapping> getClassMappings(boolean createOnDemand)
  {
    if (createOnDemand)
    {
      synchronized (classMappings)
      {
        if (!allClassMappingsCreated)
        {
          createAllClassMappings();
          allClassMappingsCreated = true;
        }
      }
    }

    return classMappings;
  }

  private void createAllClassMappings()
  {
    InternalRepository repository = (InternalRepository)getStore().getRepository();
    InternalCDOPackageRegistry packageRegistry = repository.getPackageRegistry(false);
    for (InternalCDOPackageInfo packageInfo : packageRegistry.getPackageInfos())
    {
      if (!packageInfo.isSystemPackage())
      {
        for (EClassifier eClassifier : packageInfo.getEPackage().getEClassifiers())
        {
          if (eClassifier instanceof EClass)
          {
            EClass eClass = (EClass)eClassifier;
            if (!eClass.isAbstract() && !eClass.isInterface())
            {
              getClassMapping(eClass); // Get or create it
            }
          }
        }
      }
    }
  }

  public ITypeMapping createValueMapping(EStructuralFeature feature)
  {
    return TypeMappingFactory.createTypeMapping(this, feature);
  }

  public final IListMapping createListMapping(EClass containingClass, EStructuralFeature feature)
  {
    checkArg(feature.isMany(), "Only many-valued features allowed"); //$NON-NLS-1$
    IListMapping mapping = doCreateListMapping(containingClass, feature);
    return mapping;
  }

  public final IListMapping createFeatureMapMapping(EClass containingClass, EStructuralFeature feature)
  {
    checkArg(FeatureMapUtil.isFeatureMap(feature), "Only FeatureMaps allowed"); //$NON-NLS-1$
    IListMapping mapping = doCreateFeatureMapMapping(containingClass, feature);
    return mapping;
  }

  public abstract IListMapping doCreateListMapping(EClass containingClass, EStructuralFeature feature);

  public abstract IListMapping doCreateFeatureMapMapping(EClass containingClass, EStructuralFeature feature);
}
