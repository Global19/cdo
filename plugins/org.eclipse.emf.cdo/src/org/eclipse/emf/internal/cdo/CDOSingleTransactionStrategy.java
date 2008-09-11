/***************************************************************************
 * Copyright (c) 2004 - 2008 Eike Stepper, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Simon McDuff - initial API and implementation
 **************************************************************************/
package org.eclipse.emf.internal.cdo;

import org.eclipse.emf.cdo.CDOSavepoint;
import org.eclipse.emf.cdo.CDOSession;

import org.eclipse.emf.internal.cdo.bundle.OM;
import org.eclipse.emf.internal.cdo.protocol.CommitTransactionRequest;
import org.eclipse.emf.internal.cdo.protocol.CommitTransactionResult;

import org.eclipse.net4j.channel.IChannel;
import org.eclipse.net4j.signal.failover.IFailOverStrategy;
import org.eclipse.net4j.util.om.trace.ContextTracer;
import org.eclipse.net4j.util.transaction.TransactionException;

/**
 * @author Simon McDuff
 * @since 2.0
 */
public class CDOSingleTransactionStrategy implements CDOTransactionStrategy
{
  public static final CDOSingleTransactionStrategy INSTANCE = new CDOSingleTransactionStrategy();

  private static final ContextTracer TRACER = new ContextTracer(OM.DEBUG_TRANSCTION, CDOSingleTransactionStrategy.class);

  public CDOSingleTransactionStrategy()
  {
  }

  public void commit(InternalCDOTransaction transaction) throws Exception
  {
    CDOCommitContext commitContext = transaction.createCommitContext();
    if (TRACER.isEnabled())
    {
      TRACER.format("CDOCommitContext.preCommit");
    }

    commitContext.preCommit();

    CDOSession session = transaction.getSession();
    IChannel channel = session.getChannel();
    IFailOverStrategy failOverStrategy = session.getFailOverStrategy();
    CommitTransactionRequest request = new CommitTransactionRequest(channel, commitContext);
    if (TRACER.isEnabled())
    {
      TRACER.format("Sending commit request");
    }

    CommitTransactionResult result = failOverStrategy.send(request, transaction.getCommitTimeout());
    String rollbackMessage = result.getRollbackMessage();
    if (rollbackMessage != null)
    {
      throw new TransactionException(rollbackMessage);
    }

    if (TRACER.isEnabled())
    {
      TRACER.format("CDOCommitContext.postCommit");
    }

    commitContext.postCommit(result);
  }

  public void rollback(InternalCDOTransaction transaction, CDOSavepoint savepoint, boolean remote)
  {
    transaction.handleRollback(savepoint, remote);
  }

  public CDOSavepoint setSavepoint(InternalCDOTransaction transaction)
  {
    return transaction.handleSetSavepoint();
  }

  public void setTarget(InternalCDOTransaction transaction)
  {
    // Do nothing
  }

  public void unsetTarget(InternalCDOTransaction transaction)
  {
    // Do nothing
  }
}
