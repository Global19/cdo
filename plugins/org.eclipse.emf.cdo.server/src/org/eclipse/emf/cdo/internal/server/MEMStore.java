/**
 * Copyright (c) 2004 - 2009 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Simon McDuff - initial API and implementation
 *    Simon McDuff - http://bugs.eclipse.org/233273    
 *    Eike Stepper - maintenance
 */
package org.eclipse.emf.cdo.internal.server;

import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.id.CDOIDUtil;
import org.eclipse.emf.cdo.common.model.CDOModelConstants;
import org.eclipse.emf.cdo.common.revision.CDORevision;
import org.eclipse.emf.cdo.server.IMEMStore;
import org.eclipse.emf.cdo.server.ISession;
import org.eclipse.emf.cdo.server.IStoreAccessor;
import org.eclipse.emf.cdo.server.ITransaction;
import org.eclipse.emf.cdo.server.IView;
import org.eclipse.emf.cdo.server.StoreThreadLocal;
import org.eclipse.emf.cdo.spi.common.revision.InternalCDORevision;

import org.eclipse.net4j.util.ObjectUtil;

import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Simon McDuff
 */
public class MEMStore extends LongIDStore implements IMEMStore
{
  public static final String TYPE = "mem";

  private long creationTime;

  private Map<CDOID, List<CDORevision>> revisions = new HashMap<CDOID, List<CDORevision>>();

  private int listLimit;

  /**
   * @param listLimit
   *          See {@link #setListLimit(int)}.
   * @since 2.0
   */
  public MEMStore(int listLimit)
  {
    super(TYPE, set(ChangeFormat.REVISION, ChangeFormat.DELTA), set(RevisionTemporality.NONE,
        RevisionTemporality.AUDITING), set(RevisionParallelism.NONE));
    setRevisionTemporality(RevisionTemporality.AUDITING);
    this.listLimit = listLimit;
  }

  public MEMStore()
  {
    this(UNLIMITED);
  }

  /**
   * @since 2.0
   */
  public int getListLimit()
  {
    return listLimit;
  }

  /**
   * @since 2.0
   */
  public synchronized void setListLimit(int listLimit)
  {
    if (listLimit != UNLIMITED && this.listLimit != listLimit)
    {
      for (List<CDORevision> list : revisions.values())
      {
        enforceListLimit(list);
      }
    }

    this.listLimit = listLimit;
  }

  /**
   * @since 2.0
   */
  public synchronized List<CDORevision> getCurrentRevisions()
  {
    ArrayList<CDORevision> simpleRevisions = new ArrayList<CDORevision>();
    Iterator<List<CDORevision>> itr = revisions.values().iterator();
    while (itr.hasNext())
    {
      List<CDORevision> list = itr.next();
      CDORevision revision = list.get(list.size() - 1);
      simpleRevisions.add(revision);
    }

    return simpleRevisions;
  }

  public synchronized CDORevision getRevision(CDOID id)
  {
    List<CDORevision> list = revisions.get(id);
    if (list != null)
    {
      return list.get(list.size() - 1);
    }

    return null;
  }

  public synchronized CDORevision getRevisionByVersion(CDOID id, int version)
  {
    if (getRepository().isSupportingAudits())
    {
      List<CDORevision> list = revisions.get(id);
      if (list != null)
      {
        return getRevisionByVersion(list, version);
      }

      return null;
    }

    throw new UnsupportedOperationException();
  }

  /**
   * @since 2.0
   */
  public synchronized CDORevision getRevisionByTime(CDOID id, long timeStamp)
  {
    if (getRepository().isSupportingAudits())
    {
      List<CDORevision> list = revisions.get(id);
      if (list != null)
      {
        return getRevisionByTime(list, timeStamp);
      }

      return null;
    }

    throw new UnsupportedOperationException();
  }

  public synchronized void addRevision(CDORevision revision)
  {
    CDOID id = revision.getID();
    int version = revision.getVersion();

    List<CDORevision> list = revisions.get(id);
    if (list == null)
    {
      list = new ArrayList<CDORevision>();
      revisions.put(id, list);
    }

    InternalCDORevision rev = (InternalCDORevision)getRevisionByVersion(list, version);
    if (rev != null)
    {
      throw new IllegalStateException("Concurrent modification of revision " + rev);
    }

    rev = (InternalCDORevision)getRevisionByVersion(list, version - 1);
    if (rev != null)
    {
      rev.setRevised(revision.getCreated() - 1);
    }

    if (revision.isResource())
    {
      EStructuralFeature feature = revision.getEClass().getEStructuralFeature(
          CDOModelConstants.RESOURCE_NODE_NAME_ATTRIBUTE);
      CDOID revisionFolder = (CDOID)revision.data().getContainerID();
      String revisionName = (String)revision.data().get(feature, 0);

      IStoreAccessor accessor = StoreThreadLocal.getAccessor();
      CDOID resourceID = accessor.readResourceID(revisionFolder, revisionName, revision.getCreated());
      if (!CDOIDUtil.isNull(resourceID))
      {
        throw new IllegalStateException("Duplicate resource: " + revisionName + " (folderID=" + revisionFolder + ")");
      }
    }

    list.add(revision);
    if (listLimit != UNLIMITED)
    {
      enforceListLimit(list);
    }
  }

  /**
   * @since 2.0
   */
  public synchronized boolean rollbackRevision(CDORevision revision)
  {
    CDOID id = revision.getID();
    List<CDORevision> list = revisions.get(id);
    if (list == null)
    {
      return false;
    }

    int version = revision.getVersion();
    for (Iterator<CDORevision> it = list.iterator(); it.hasNext();)
    {
      InternalCDORevision rev = (InternalCDORevision)it.next();
      if (rev.getVersion() == version)
      {
        it.remove();
        return true;
      }
      else if (rev.getVersion() == version - 1)
      {
        rev.setRevised(CDORevision.UNSPECIFIED_DATE);
      }
    }

    return false;
  }

  /**
   * @since 2.0
   */
  public synchronized void removeID(CDOID id)
  {
    revisions.remove(id);
  }

  /**
   * @since 2.0
   */
  public synchronized void queryResources(IStoreAccessor.QueryResourcesContext context)
  {
    CDOID folderID = context.getFolderID();
    String name = context.getName();
    boolean exactMatch = context.exactMatch();
    for (List<CDORevision> list : revisions.values())
    {
      if (!list.isEmpty())
      {
        CDORevision revision = list.get(0);
        if (revision.isResourceNode())
        {
          revision = getRevisionByTime(list, context.getTimeStamp());
          if (revision != null)
          {
            CDOID revisionFolder = (CDOID)revision.data().getContainerID();
            if (CDOIDUtil.equals(revisionFolder, folderID))
            {
              EStructuralFeature feature = revision.getEClass().getEStructuralFeature(
                  CDOModelConstants.RESOURCE_NODE_NAME_ATTRIBUTE);
              String revisionName = (String)revision.data().get(feature, 0);
              boolean match = exactMatch || revisionName == null || name == null ? ObjectUtil
                  .equals(revisionName, name) : revisionName.startsWith(name);

              if (match)
              {
                if (!context.addResource(revision.getID()))
                {
                  // No more results allowed
                  break;
                }
              }
            }
          }
        }
      }
    }
  }

  public void repairAfterCrash()
  {
    // Do nothing
  }

  @Override
  public MEMStoreAccessor createReader(ISession session)
  {
    return new MEMStoreAccessor(this, session);
  }

  /**
   * @since 2.0
   */
  @Override
  public MEMStoreAccessor createWriter(ITransaction transaction)
  {
    return new MEMStoreAccessor(this, transaction);
  }

  /**
   * @since 2.0
   */
  public long getCreationTime()
  {
    return creationTime;
  }

  /**
   * @since 2.0
   */
  @Override
  protected void doActivate() throws Exception
  {
    super.doActivate();
    creationTime = System.currentTimeMillis();
  }

  @Override
  protected void doDeactivate() throws Exception
  {
    revisions.clear();
    super.doDeactivate();
  }

  @Override
  protected StoreAccessorPool getReaderPool(ISession session, boolean forReleasing)
  {
    // Pooling of store accessors not supported
    return null;
  }

  @Override
  protected StoreAccessorPool getWriterPool(IView view, boolean forReleasing)
  {
    // Pooling of store accessors not supported
    return null;
  }

  private CDORevision getRevisionByVersion(List<CDORevision> list, int version)
  {
    for (CDORevision revision : list)
    {
      if (revision.getVersion() == version)
      {
        return revision;
      }
    }

    return null;
  }

  private CDORevision getRevisionByTime(List<CDORevision> list, long timeStamp)
  {
    for (CDORevision revision : list)
    {
      if (timeStamp == CDORevision.UNSPECIFIED_DATE)
      {
        if (revision.isCurrent())
        {
          return revision;
        }
      }
      else
      {
        if (revision.isValid(timeStamp))
        {
          return revision;
        }
      }
    }

    return null;
  }

  private void enforceListLimit(List<CDORevision> list)
  {
    while (list.size() > listLimit)
    {
      list.remove(0);
    }
  }
}
