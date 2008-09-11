/***************************************************************************
 * Copyright (c) 2004 - 2008 Eike Stepper, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Simon McDuff - initial API and implementation
 **************************************************************************/
package org.eclipse.emf.cdo.internal.server;


import org.eclipse.emf.cdo.internal.server.bundle.OM;

import org.eclipse.net4j.util.WrappedException;
import org.eclipse.net4j.util.concurrent.ConcurrentValue;
import org.eclipse.net4j.util.om.trace.ContextTracer;

/**
 * @author Simon McDuff
 * @since 2.0
 */
public class XATransactionCommitContext extends TransactionCommitContextImpl
{
  private static final ContextTracer TRACER = new ContextTracer(OM.DEBUG_TRANSACTION, XATransactionCommitContext.class);

  private ConcurrentValue<CommitState> state = new ConcurrentValue<CommitState>(CommitState.STARTING);

  public XATransactionCommitContext(Transaction transaction)
  {
    super(transaction);
  }

  public ConcurrentValue<CommitState> getState()
  {
    return state;
  }

  @Override
  public void postCommit(boolean success)
  {
    getTransaction().getRepository().getCommitManager().remove(this);
    super.postCommit(success);
  }

  @Override
  protected void rollback()
  {
    super.rollback();

    // Change the state to unblock call.
    state.set(CommitState.ROLLED_BACK);
  }

  /**
   * Wait until another thread fills ID mapping for external objects.
   */
  @Override
  public void applyIDMappings()
  {
    if (TRACER.isEnabled())
    {
      TRACER.format("Notify phase2 to fill ID mapping.");
    }

    state.set(CommitState.APPLY_ID_MAPPING);
    if (TRACER.isEnabled())
    {
      TRACER.format("Waiting for phase2 to be completed before continueing.");
    }

    try
    {
      state.acquire(PHASEAPPLYMAPPING_DONE);
    }
    catch (InterruptedException ex)
    {
      throw WrappedException.wrap(ex);
    }

    if (TRACER.isEnabled())
    {
      TRACER.format("Received signal to continue.");
    }

    super.applyIDMappings();
  }

  /**
   * Object to test if the process is at ApplyIDMapping
   */
  final public static Object PHASEAPPLYMAPPING = new Object()
  {
    @Override
    public boolean equals(Object object)
    {
      return CommitState.ROLLED_BACK == object || CommitState.APPLY_ID_MAPPING == object;
    }
  };

  /**
   * Object to test if the process did applyIDMapping
   */
  final public static Object PHASEAPPLYMAPPING_DONE = new Object()
  {
    @Override
    public boolean equals(Object object)
    {
      return CommitState.ROLLED_BACK == object || CommitState.APPLY_ID_MAPPING_DONE == object;
    }
  };

  /**
   * @author Simon McDuff
   * @since 2.0
   */
  public enum CommitState
  {
    STARTING, APPLY_ID_MAPPING, APPLY_ID_MAPPING_DONE, ROLLED_BACK
  };
}
