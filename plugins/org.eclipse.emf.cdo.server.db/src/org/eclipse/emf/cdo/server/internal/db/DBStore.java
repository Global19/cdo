/***************************************************************************
 * Copyright (c) 2004, 2005, 2006 Eike Stepper, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Eike Stepper - initial API and implementation
 **************************************************************************/
package org.eclipse.emf.cdo.server.internal.db;

import org.eclipse.emf.cdo.internal.protocol.revision.CDORevisionImpl;
import org.eclipse.emf.cdo.internal.server.RevisionManager;
import org.eclipse.emf.cdo.internal.server.store.AddRevisionOperation;
import org.eclipse.emf.cdo.internal.server.store.LoadHistoricalRevisionOperation;
import org.eclipse.emf.cdo.internal.server.store.LoadResourceIDOperation;
import org.eclipse.emf.cdo.internal.server.store.LoadResourcePathOperation;
import org.eclipse.emf.cdo.internal.server.store.LoadRevisionOperation;
import org.eclipse.emf.cdo.internal.server.store.RegisterResourceOperation;
import org.eclipse.emf.cdo.internal.server.store.Store;
import org.eclipse.emf.cdo.protocol.CDOID;

import org.eclipse.net4j.db.DBUtil;
import org.eclipse.net4j.util.transaction.ITransaction;

import javax.sql.DataSource;

import java.util.Map;

/**
 * @author Eike Stepper
 */
public class DBStore extends Store
{
  private static final String TYPE = "db";

  private DataSource dataSource;

  public DBStore(DataSource dataSource)
  {
    super(TYPE);
    this.dataSource = dataSource;
  }

  public ITransaction createTransaction()
  {
    return DBUtil.createTransaction(dataSource);
  }

  @Override
  protected AddRevisionOperation createAddRevisionOperation(RevisionManager revisionManager, CDORevisionImpl revision)
  {
    return new AddRevisionOperation(revisionManager, revision)
    {
      @Override
      protected void update(ITransaction transaction, CDORevisionImpl revision)
      {
        // TODO Implement method .update()
        throw new UnsupportedOperationException("Not yet implemented");
      }
    };
  }

  @Override
  protected LoadRevisionOperation createLoadRevisionOperation(CDOID id)
  {
    return new LoadRevisionOperation(id)
    {
      @Override
      protected CDORevisionImpl query(ITransaction transaction, CDOID id) throws Exception
      {
        // TODO Implement method .query()
        throw new UnsupportedOperationException("Not yet implemented");
      }
    };
  }

  @Override
  protected LoadHistoricalRevisionOperation createLoadHistoricalRevisionOperation(CDOID id, long timeStamp)
  {
    return new LoadHistoricalRevisionOperation(id, timeStamp)
    {
      @Override
      protected CDORevisionImpl query(ITransaction transaction, CDOID id, long timeStamp) throws Exception
      {
        // TODO Implement method .query()
        throw new UnsupportedOperationException("Not yet implemented");
      }
    };
  }

  @Override
  protected RegisterResourceOperation createRegisterResourceOperation(CDOID id, String path,
      Map<CDOID, String> idToPathMap, Map<String, CDOID> pathToIDMap)
  {
    return new RegisterResourceOperation(id, path, idToPathMap, pathToIDMap)
    {
      @Override
      protected void update(ITransaction transaction, CDOID id, String path)
      {
        // TODO Implement method .update()
        throw new UnsupportedOperationException("Not yet implemented");
      }
    };
  }

  @Override
  protected LoadResourceIDOperation createLoadResourceIDOperation(String path)
  {
    return new LoadResourceIDOperation(path)
    {
      @Override
      protected CDOID query(ITransaction transaction, String path) throws Exception
      {
        // TODO Implement method .query()
        throw new UnsupportedOperationException("Not yet implemented");
      }
    };
  }

  @Override
  protected LoadResourcePathOperation createLoadResourcePathOperation(CDOID id)
  {
    return new LoadResourcePathOperation(id)
    {
      @Override
      protected String query(ITransaction transaction, CDOID id) throws Exception
      {
        // TODO Implement method .query()
        throw new UnsupportedOperationException("Not yet implemented");
      }
    };
  }
}
