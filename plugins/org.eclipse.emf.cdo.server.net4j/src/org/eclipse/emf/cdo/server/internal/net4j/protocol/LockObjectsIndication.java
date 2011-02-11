/**
 * Copyright (c) 2004 - 2011 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Simon McDuff - initial API and implementation
 *    Eike Stepper - maintenance
 *    Caspar De Groot - maintenance
 */
package org.eclipse.emf.cdo.server.internal.net4j.protocol;

import org.eclipse.emf.cdo.common.branch.CDOBranch;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.id.CDOIDUtil;
import org.eclipse.emf.cdo.common.protocol.CDODataInput;
import org.eclipse.emf.cdo.common.protocol.CDODataOutput;
import org.eclipse.emf.cdo.common.protocol.CDOProtocolConstants;
import org.eclipse.emf.cdo.common.revision.CDORevision;
import org.eclipse.emf.cdo.common.revision.CDORevisionKey;
import org.eclipse.emf.cdo.server.IView;
import org.eclipse.emf.cdo.spi.common.revision.InternalCDORevision;
import org.eclipse.emf.cdo.spi.server.InternalLockManager;

import org.eclipse.net4j.util.WrappedException;
import org.eclipse.net4j.util.concurrent.IRWLockManager.LockType;
import org.eclipse.net4j.util.concurrent.TimeoutRuntimeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Simon McDuff
 */
public class LockObjectsIndication extends CDOReadIndication
{
  private List<Object> objectsToBeLocked = new ArrayList<Object>();

  private List<CDORevisionKey> staleRevisions = new LinkedList<CDORevisionKey>();

  private boolean timedOut;

  public LockObjectsIndication(CDOServerProtocol protocol)
  {
    super(protocol, CDOProtocolConstants.SIGNAL_LOCK_OBJECTS);
  }

  @Override
  protected void indicating(CDODataInput in) throws IOException
  {
    int viewID = in.readInt();
    LockType lockType = in.readCDOLockType();
    long timeout = in.readLong();
    CDOBranch viewedBranch = in.readCDOBranch();

    int nRevisions = in.readInt();
    CDORevisionKey[] revKeys = new CDORevisionKey[nRevisions];
    for (int i = 0; i < nRevisions; i++)
    {
      revKeys[i] = in.readCDORevisionKey();
      handleViewedRevision(viewedBranch, revKeys[i]);
    }

    if (staleRevisions.size() > 0)
    {
      // If we have 1 or more stale revisions, we should not lock
      return;
    }

    IView view = getSession().getView(viewID);
    InternalLockManager lockManager = getRepository().getLockManager();
    try
    {
      lockManager.lock(lockType, view, objectsToBeLocked, timeout);
    }
    catch (TimeoutRuntimeException ex)
    {
      timedOut = true;
    }
    catch (InterruptedException ex)
    {
      throw WrappedException.wrap(ex);
    }
  }

  private void handleViewedRevision(CDOBranch viewedBranch, CDORevisionKey revKey)
  {
    CDOID id = revKey.getID();
    InternalCDORevision rev = getRepository().getRevisionManager().getRevision(id, viewedBranch.getHead(),
        CDORevision.UNCHUNKED, CDORevision.DEPTH_NONE, true);

    if (rev == null)
    {
      throw new IllegalArgumentException(String.format("Object %s not found in branch %s (possibly detached)", id,
          viewedBranch));
    }

    if (!revKey.equals(rev))
    {
      staleRevisions.add(revKey);

      // If we have 1 or more stale revisions, the locking won't proceed for sure,
      // so we can return early
      return;
    }

    if (getRepository().isSupportingBranches())
    {
      objectsToBeLocked.add(CDOIDUtil.createIDAndBranch(id, viewedBranch));
    }
    else
    {
      objectsToBeLocked.add(id);
    }
  }

  @Override
  protected void responding(CDODataOutput out) throws IOException
  {
    boolean success = !timedOut && staleRevisions.size() == 0;
    out.writeBoolean(success);

    if (!success)
    {
      out.writeBoolean(timedOut);
      if (!timedOut)
      {
        out.writeInt(staleRevisions.size());
        for (CDORevisionKey staleRevision : staleRevisions)
        {
          out.writeCDORevisionKey(staleRevision);
        }
      }
    }
  }
}
