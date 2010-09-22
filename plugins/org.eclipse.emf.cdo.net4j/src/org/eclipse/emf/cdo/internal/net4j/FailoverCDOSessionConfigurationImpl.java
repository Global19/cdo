/**
 * Copyright (c) 2004 - 2010 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.internal.net4j;

import org.eclipse.emf.cdo.common.branch.CDOBranch;
import org.eclipse.emf.cdo.common.branch.CDOBranchPoint;
import org.eclipse.emf.cdo.net4j.FailoverCDOSessionConfiguration;
import org.eclipse.emf.cdo.session.CDOSession.ExceptionHandler;
import org.eclipse.emf.cdo.spi.common.branch.CDOBranchUtil;
import org.eclipse.emf.cdo.transaction.CDOTransaction;

import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.signal.RequestWithConfirmation;
import org.eclipse.net4j.signal.SignalProtocol;
import org.eclipse.net4j.signal.failover.IFailOverStrategy;
import org.eclipse.net4j.util.WrappedException;
import org.eclipse.net4j.util.collection.Pair;
import org.eclipse.net4j.util.container.IManagedContainer;
import org.eclipse.net4j.util.container.IPluginContainer;
import org.eclipse.net4j.util.io.ExtendedDataInputStream;
import org.eclipse.net4j.util.io.ExtendedDataOutputStream;

import org.eclipse.emf.spi.cdo.CDOSessionProtocol;
import org.eclipse.emf.spi.cdo.InternalCDOSession;
import org.eclipse.emf.spi.cdo.InternalCDOView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eike Stepper
 * @since 4.0
 */
public class FailoverCDOSessionConfigurationImpl extends CDONet4jSessionConfigurationImpl implements
    FailoverCDOSessionConfiguration
{
  private String monitorConnectorDescription;

  private String repositoryGroup;

  private IConnector monitorConnector;

  private SignalProtocol<Object> monitorProtocol;

  public FailoverCDOSessionConfigurationImpl(String monitorConnectorDescription, String repositoryGroup)
  {
    this.monitorConnectorDescription = monitorConnectorDescription;
    this.repositoryGroup = repositoryGroup;
  }

  public String getMonitorConnectorDescription()
  {
    return monitorConnectorDescription;
  }

  public String getRepositoryGroup()
  {
    return repositoryGroup;
  }

  @Override
  public void setRepositoryName(String repositoryName)
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setConnector(IConnector connector)
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setFailOverStrategy(IFailOverStrategy failOverStrategy)
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setExceptionHandler(ExceptionHandler exceptionHandler)
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public InternalCDOSession createSession()
  {
    updateConnectorAndRepositoryName();
    return new FailoverCDOSessionImpl(this);
  }

  public void failover(FailoverCDOSessionImpl session)
  {
    try
    {
      List<Object> targets = getViewTargets(session);
      uncheckedSetPassiveUpdateEnabled(session.options().isPassiveUpdateEnabled());
      uncheckedSetPassiveUpdateMode(session.options().getPassiveUpdateMode());

      updateConnectorAndRepositoryName();
      initProtocol(session);

      reregisterViews(session, targets);
      reregisterRemoteSessions(session);
      System.out.println("Connected");
    }
    catch (RuntimeException ex)
    {
      session.deactivate();
      throw ex;
    }
    catch (Error ex)
    {
      session.deactivate();
      throw ex;
    }
  }

  private void updateConnectorAndRepositoryName()
  {
    Pair<String, String> info = queryRepositoryInfoFromMonitor();
    IConnector connector = getConnector(info.getElement1());
    String repositoryName = info.getElement2();

    System.out.println("Connecting to " + info.getElement1() + "/" + repositoryName);

    uncheckedSetConnector(connector);
    uncheckedSetRepositoryName(repositoryName);
  }

  protected Pair<String, String> queryRepositoryInfoFromMonitor()
  {
    if (monitorConnector == null)
    {
      monitorConnector = getConnector(monitorConnectorDescription);
      monitorProtocol = new SignalProtocol<Object>("failover-client");
      monitorProtocol.open(monitorConnector);
    }

    try
    {
      return new RequestWithConfirmation<Pair<String, String>>(monitorProtocol, (short)1, "QueryRepositoryInfo")
      {
        @Override
        protected void requesting(ExtendedDataOutputStream out) throws Exception
        {
          out.writeString(repositoryGroup);
        }

        @Override
        protected Pair<String, String> confirming(ExtendedDataInputStream in) throws Exception
        {
          String connectorDescription = in.readString();
          String repositoryName = in.readString();
          return new Pair<String, String>(connectorDescription, repositoryName);
        }
      }.send();
    }
    catch (Exception ex)
    {
      throw WrappedException.wrap(ex);
    }
    finally
    {
      // protocol.close();
      // if (connector.getChannels().isEmpty())
      // {
      // connector.close();
      // }
    }
  }

  protected IConnector getConnector(String description)
  {
    IManagedContainer container = getContainer();
    // container.removeElement("org.eclipse.net4j.connectors", "tcp", description);
    return (IConnector)container.getElement("org.eclipse.net4j.connectors", "tcp", description);
  }

  protected IManagedContainer getContainer()
  {
    return IPluginContainer.INSTANCE;
  }

  private List<Object> getViewTargets(FailoverCDOSessionImpl session)
  {
    List<Object> targets = new ArrayList<Object>();
    for (InternalCDOView view : session.getViews())
    {
      if (view instanceof CDOTransaction)
      {
        CDOTransaction transaction = (CDOTransaction)view;
        targets.add(transaction.getBranch());
      }
      else
      {
        targets.add(CDOBranchUtil.copyBranchPoint(view));
      }
    }

    return targets;
  }

  private void reregisterViews(FailoverCDOSessionImpl session, List<Object> targets)
  {
    CDOSessionProtocol sessionProtocol = session.getSessionProtocol();
    int viewID = 0;

    for (Object target : targets)
    {
      if (target instanceof CDOBranchPoint)
      {
        CDOBranchPoint branchPoint = (CDOBranchPoint)target;
        sessionProtocol.openView(++viewID, branchPoint, true);
      }
      else
      {
        CDOBranch branch = (CDOBranch)target;
        sessionProtocol.openView(++viewID, branch.getHead(), false);
      }
    }
  }

  private void reregisterRemoteSessions(FailoverCDOSessionImpl session)
  {
    // TODO
  }
}
