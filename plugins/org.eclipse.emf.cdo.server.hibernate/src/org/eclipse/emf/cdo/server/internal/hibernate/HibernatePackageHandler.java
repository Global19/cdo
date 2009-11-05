/**
 * Copyright (c) 2004 - 2009 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 *    Martin Taal  - moved code from HibernateStore to this class
 */
package org.eclipse.emf.cdo.server.internal.hibernate;

import org.eclipse.emf.cdo.common.model.CDOModelUtil;
import org.eclipse.emf.cdo.common.model.EMFUtil;
import org.eclipse.emf.cdo.server.IStoreAccessor.CommitContext;
import org.eclipse.emf.cdo.server.internal.hibernate.bundle.OM;
import org.eclipse.emf.cdo.spi.common.model.InternalCDOPackageRegistry;
import org.eclipse.emf.cdo.spi.common.model.InternalCDOPackageUnit;
import org.eclipse.emf.cdo.spi.server.InternalRepository;

import org.eclipse.net4j.util.WrappedException;
import org.eclipse.net4j.util.io.IOUtil;
import org.eclipse.net4j.util.lifecycle.Lifecycle;
import org.eclipse.net4j.util.om.trace.ContextTracer;

import org.eclipse.emf.ecore.EPackage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Delegate which stores and retrieves cdo packages.
 * <p>
 * TODO extend {@link Lifecycle}. See {@link #doActivate()} and {@link #doDeactivate()}.
 * 
 * @author Eike Stepper
 * @author Martin Taal
 */
public class HibernatePackageHandler extends Lifecycle
{
  private static final ContextTracer TRACER = new ContextTracer(OM.DEBUG, HibernatePackageHandler.class);

  private static final boolean ZIP_PACKAGE_BYTES = true;

  private static final String CDO_PACKAGE_UNIT_ENTITY_NAME = "CDOPackageUnit";

  // made static and synchronized because apparently there can be multiple package handlers
  // in some test cases: TestExternalReferenceTest.testOneXMIResourceManyViewsOnOneResourceSet
  private static synchronized boolean writePackageUnits(InternalCDOPackageUnit[] packageUnits,
      SessionFactory sessionFactory, EPackage.Registry registry)
  {
    if (TRACER.isEnabled())
    {
      TRACER.trace("Persisting new EPackages");
    }

    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    boolean err = true;
    boolean updated = false;

    try
    {
      // first store and update the packageunits and the epackages
      for (InternalCDOPackageUnit packageUnit : packageUnits)
      {
        final HibernateCDOPackageUnitDTO hbPackageUnitDTO = new HibernateCDOPackageUnitDTO(packageUnit);

        if (packageUnit.getPackageInfos().length > 0)
        {
          final String rootNSUri = packageUnit.getTopLevelPackageInfo().getPackageURI();
          final EPackage rootEPackage = registry.getEPackage(rootNSUri);
          hbPackageUnitDTO.setEPackageBlob(EMFUtil.getEPackageBytes(rootEPackage, true, registry));
        }

        if (session.get("CDOPackageUnit", hbPackageUnitDTO.getNsUri()) == null)
        {
          session.saveOrUpdate("CDOPackageUnit", hbPackageUnitDTO);
        }

        updated = true;
      }

      tx.commit();
      err = false;
    }
    catch (Exception e)
    {
      e.printStackTrace(System.err);
      throw new Error(e);
    }
    finally
    {
      if (err)
      {
        tx.rollback();
      }

      session.close();
    }

    return updated;
  }

  private Configuration configuration;

  private SessionFactory sessionFactory;

  private int nextPackageID;

  private int nextClassID;

  private int nextFeatureID;

  private Collection<InternalCDOPackageUnit> packageUnits = null;

  private Map<String, byte[]> ePackageBlobsByRootUri = new HashMap<String, byte[]>();

  private Map<String, EPackage[]> ePackagesByRootUri = new HashMap<String, EPackage[]>();

  private HibernateStore hibernateStore;

  private boolean doDropSchema = false;

  /**
   * TODO Necessary to pass/store/dump the properties from the store?
   */
  public HibernatePackageHandler(HibernateStore store)
  {
    hibernateStore = store;
  }

  /**
   * @return the full list of EPackages registered in the PackageRegistry of the commit context as well as the EPackages
   *         registered earlier.
   * @see CommitContext#getPackageRegistry()
   * @see InternalRepository#getPackageRegistry()
   */
  public List<EPackage> getEPackages()
  {
    List<EPackage> ePackages = new ArrayList<EPackage>();
    final InternalRepository localRepository = hibernateStore.getRepository();

    for (EPackage ePackage : localRepository.getPackageRegistry(false).getEPackages())
    {
      ePackages.add(ePackage);
    }

    for (EPackage ePackage : localRepository.getPackageRegistry(true).getEPackages())
    {
      if (!ePackages.contains(ePackage))
      {
        ePackages.add(ePackage);
      }
    }

    return ePackages;
  }

  private InternalCDOPackageRegistry getPackageRegistry()
  {
    return hibernateStore.getRepository().getPackageRegistry();
  }

  public void writePackageUnits(InternalCDOPackageUnit[] packageUnits)
  {
    final boolean updated = writePackageUnits(packageUnits, getSessionFactory(), getPackageRegistry());
    if (updated)
    {
      reset();
      hibernateStore.reInitialize();
    }
  }

  public Collection<InternalCDOPackageUnit> getPackageUnits()
  {
    readPackageUnits();
    return packageUnits;
  }

  public EPackage[] loadPackageUnit(InternalCDOPackageUnit packageUnit)
  {
    final String nsUri = packageUnit.getTopLevelPackageInfo().getPackageURI();
    if (TRACER.isEnabled())
    {
      TRACER.trace("Reading EPackages with root uri " + nsUri + " from db");
    }

    EPackage[] epacks = ePackagesByRootUri.get(nsUri);
    if (epacks == null)
    {
      final byte[] ePackageBlob = ePackageBlobsByRootUri.get(nsUri);
      if (ePackageBlob == null)
      {
        throw new IllegalArgumentException("EPackages with root uri " + nsUri + " not found");
      }

      final EPackage rootEPackage = EMFUtil
          .createEPackage(nsUri, ePackageBlob, ZIP_PACKAGE_BYTES, getPackageRegistry());
      epacks = EMFUtil.getAllPackages(rootEPackage);
      ePackagesByRootUri.put(nsUri, epacks);
    }

    return epacks;
  }

  @SuppressWarnings("unchecked")
  protected void readPackageUnits()
  {
    if (packageUnits == null || packageUnits.size() == 0)
    {
      if (TRACER.isEnabled())
      {
        TRACER.trace("Reading Package Units from db");
      }

      Session session = getSessionFactory().openSession();

      try
      {
        Criteria criteria = session.createCriteria(CDO_PACKAGE_UNIT_ENTITY_NAME);
        List<?> list = criteria.list();
        if (TRACER.isEnabled())
        {
          TRACER.trace("Found " + list.size() + " CDOPackageUnits in DB");
        }

        CDOModelUtil.createPackageUnit();

        packageUnits = new ArrayList<InternalCDOPackageUnit>();
        for (HibernateCDOPackageUnitDTO dto : (Collection<HibernateCDOPackageUnitDTO>)list)
        {
          packageUnits.add(dto.createCDOPackageUnit(getPackageRegistry()));
          // cache the blob because resolving the epackages right away gives errors
          ePackageBlobsByRootUri.put(dto.getNsUri(), dto.getEPackageBlob());
        }
      }
      finally
      {
        session.close();
      }
    }

    if (TRACER.isEnabled())
    {
      TRACER.trace("Finished reading Package Units");
    }
  }

  public synchronized SessionFactory getSessionFactory()
  {
    if (sessionFactory == null)
    {
      sessionFactory = configuration.buildSessionFactory();
    }

    return sessionFactory;
  }

  public synchronized int getNextPackageID()
  {
    return nextPackageID++;
  }

  public synchronized int getNextClassID()
  {
    return nextClassID++;
  }

  public synchronized int getNextFeatureID()
  {
    return nextFeatureID++;
  }

  public void reset()
  {
    packageUnits = null;
  }

  @Override
  protected void doActivate() throws Exception
  {
    super.doActivate();
    initConfiguration();
    initSchema();
  }

  @Override
  protected void doDeactivate() throws Exception
  {
    if (sessionFactory != null)
    {
      sessionFactory.close();
      sessionFactory = null;
    }

    if (doDropSchema)
    {
      final SchemaExport se = new SchemaExport(configuration);
      se.drop(false, true);
    }

    configuration = null;
    super.doDeactivate();
  }

  protected void initConfiguration()
  {
    if (TRACER.isEnabled())
    {
      TRACER.trace("Initializing configuration for CDO metadata");
    }

    InputStream in = null;

    try
    {
      in = OM.BUNDLE.getInputStream("/mappings/meta.hbm.xml");
      configuration = new Configuration();
      configuration.addInputStream(in);
      configuration.setProperties(HibernateUtil.getInstance().getPropertiesFromStore(hibernateStore));
      // prevent the drop at session factory close...
      // the drop is done by the HibernateStore
      if (configuration.getProperty(Environment.HBM2DDL_AUTO) != null
          && configuration.getProperty(Environment.HBM2DDL_AUTO).startsWith("create"))
      {
        doDropSchema = true;
        // note that the value create also re-creates the db and drops the old one
        configuration.setProperty(Environment.HBM2DDL_AUTO, "update");
      }
      else
      {
        doDropSchema = false;
      }
    }
    catch (Exception ex)
    {
      throw WrappedException.wrap(ex);
    }
    finally
    {
      IOUtil.close(in);
    }
  }

  SystemInformation getSystemInformation()
  {
    Session session = getSessionFactory().openSession();
    session.beginTransaction();
    try
    {
      final Criteria c = session.createCriteria(SystemInformation.class);
      List<?> l = c.list();

      final SystemInformation systemInformation;
      if (l.size() == 0)
      {
        systemInformation = new SystemInformation();
        systemInformation.setFirstTime(true);
        systemInformation.setCreationTime(System.currentTimeMillis());
        session.saveOrUpdate(systemInformation);
      }
      else if (l.size() > 1)
      {
        throw new IllegalStateException(
            "More than one records in the cdo_system_information table, this is an illegal situation");
      }
      else
      {
        systemInformation = (SystemInformation)l.get(0);
        systemInformation.setFirstTime(false);
      }

      return systemInformation;
    }
    finally
    {
      session.getTransaction().commit();
      session.close();
    }
  }

  protected void initSchema()
  {
    if (TRACER.isEnabled())
    {
      TRACER.trace("Updating db schema for Hibernate PackageHandler");
    }

    new SchemaUpdate(configuration).execute(true, true);
  }
}
