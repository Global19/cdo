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
package org.eclipse.emf.cdo;

import org.eclipse.emf.cdo.eresource.CDOResourceFactory;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * A {@link ResourceSet} adapter to associate a set of {@link CDOView} instances.
 * <p>
 * <b>Note:</b> A view set must have exactly one resource set associated. A resource set can have only one view set
 * associated.
 * 
 * @author Simon McDuff
 * @since 2.0
 */
public interface CDOViewSet extends Notifier
{
  public CDOView[] getViews();

  public CDOResourceFactory getResourceFactory();

  public EPackage.Registry getPackageRegistry();

  public ResourceSet getResourceSet();
}
