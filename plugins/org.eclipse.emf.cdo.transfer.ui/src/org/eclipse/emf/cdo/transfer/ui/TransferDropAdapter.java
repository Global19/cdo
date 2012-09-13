/*
 * Copyright (c) 2004 - 2012 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.transfer.ui;

import org.eclipse.emf.cdo.transfer.CDOTransfer;
import org.eclipse.emf.cdo.transfer.CDOTransferElement;
import org.eclipse.emf.cdo.transfer.CDOTransferSystem;
import org.eclipse.emf.cdo.transfer.internal.ui.TransferView;
import org.eclipse.emf.cdo.transfer.spi.ui.TransferUIProvider;
import org.eclipse.emf.cdo.transfer.spi.ui.TransferUIProvider.Factory;

import org.eclipse.net4j.util.container.IManagedContainer;
import org.eclipse.net4j.util.container.IPluginContainer;
import org.eclipse.net4j.util.ui.dnd.DNDDropAdapter;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eike Stepper
 */
public class TransferDropAdapter extends DNDDropAdapter<Object>
{
  private TransferUIProvider[] uiProviders;

  public TransferDropAdapter(StructuredViewer viewer)
  {
    super(viewer);
    uiProviders = getUIProviders();

    List<Transfer> transfers = new ArrayList<Transfer>();
    for (int i = 0; i < uiProviders.length; i++)
    {
      TransferUIProvider uiProvider = uiProviders[i];
      uiProvider.addSupportedTransfers(transfers);
    }

    setTransfers(transfers.toArray(new Transfer[transfers.size()]));
  }

  protected IManagedContainer getContainer()
  {
    return IPluginContainer.INSTANCE;
  }

  protected TransferUIProvider[] getUIProviders()
  {
    return Factory.getAll(getContainer());
  }

  @Override
  protected boolean validateTarget(Object target, int operation)
  {
    CDOTransferElement targetElement = getTargetElement(target);
    if (targetElement != null && targetElement.isDirectory())
    {
      overrideOperation(DND.DROP_COPY);
      return true;
    }

    return true;// XXX Must be false!!!
  }

  @Override
  protected boolean performDrop(Object data, Object target)
  {
    if (data == null)
    {
      return false;
    }

    List<CDOTransferElement> sourceElements = getSourceElements(data);
    if (sourceElements == null || sourceElements.isEmpty())
    {
      return false;
    }

    CDOTransferElement targetElement = getTargetElement(target);
    if (targetElement == null || !targetElement.isDirectory())
    {
      return false;
    }

    CDOTransferSystem sourceSystem = sourceElements.get(0).getSystem();
    CDOTransferSystem targetSystem = targetElement.getSystem();

    CDOTransfer transfer = new CDOTransfer(sourceSystem, targetSystem);
    transfer.getRootMapping().setRelativePath(targetElement.getPath());
    for (CDOTransferElement sourceElement : sourceElements)
    {
      transfer.map(sourceElement);
    }

    return performTransfer(transfer);
  }

  protected boolean performTransfer(CDOTransfer transfer)
  {
    if (TransferView.INSTANCE == null)
    {
      return false;
    }

    TransferView.INSTANCE.setTransfer(transfer);
    return false;
  }

  protected List<CDOTransferElement> getSourceElements(Object data)
  {
    for (int i = 0; i < uiProviders.length; i++)
    {
      TransferUIProvider uiProvider = uiProviders[i];

      List<CDOTransferElement> elements = uiProvider.convertTransferData(data);
      if (elements != null)
      {
        return elements;
      }
    }

    return null;
  }

  protected CDOTransferElement getTargetElement(Object target)
  {
    for (int i = 0; i < uiProviders.length; i++)
    {
      TransferUIProvider uiProvider = uiProviders[i];

      CDOTransferElement element = uiProvider.convertTransferTarget(target);
      if (element != null)
      {
        return element;
      }
    }

    return null;
  }

  public static TransferDropAdapter support(StructuredViewer viewer)
  {
    TransferDropAdapter dropAdapter = new TransferDropAdapter(viewer);
    Transfer[] transfers = dropAdapter.getTransfers();
    viewer.addDropSupport(DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_DEFAULT, transfers, dropAdapter);
    return dropAdapter;
  }
}
