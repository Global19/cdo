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
package org.eclipse.emf.internal.cdo;

import org.eclipse.emf.cdo.CDOSavepoint;
import org.eclipse.emf.cdo.CDOTransaction;
import org.eclipse.emf.cdo.common.id.CDOIDProvider;

/**
 * @author Simon McDuff
 * @since 2.0
 */
public interface InternalCDOTransaction extends CDOTransaction, CDOIDProvider
{
  public CDOCommitContext createCommitContext();

  public void handleRollback(CDOSavepoint savepoint, boolean remote);

  public CDOSavepoint handleSetSavepoint();

  public CDOTransactionStrategy getTransactionStrategy();

  public void setTransactionStrategy(CDOTransactionStrategy transactionStrategy);

}
