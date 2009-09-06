/**
 * Copyright (c) 2009 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 *
 *  Initial Publication:
 *    Eclipse Magazin - http://www.eclipse-magazin.de
 */
package org.gastro.rcp.internal.department;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * @author Eike Stepper
 */
public class DepartmentPerspective implements IPerspectiveFactory
{
  public void createInitialLayout(IPageLayout layout)
  {
    String editorArea = layout.getEditorArea();
    layout.setEditorAreaVisible(false);
    layout.setFixed(true);
    layout.addStandaloneView(EmployeesView.ID, false, IPageLayout.LEFT, 0.13f, editorArea);
    layout.addStandaloneView(OrdersView.ID, false, IPageLayout.RIGHT, 1.0f, editorArea);
  }
}
