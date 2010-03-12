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
package org.eclipse.emf.cdo.tests;

import org.eclipse.emf.cdo.common.CDOCommonRepository;
import org.eclipse.emf.cdo.common.branch.CDOBranch;
import org.eclipse.emf.cdo.common.revision.CDORevision;
import org.eclipse.emf.cdo.common.revision.CDORevisionUtil;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.internal.server.mem.MEMStore;
import org.eclipse.emf.cdo.server.IMEMStore;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.session.CDOSessionInvalidationEvent;
import org.eclipse.emf.cdo.spi.server.InternalRepository;
import org.eclipse.emf.cdo.spi.server.InternalStore;
import org.eclipse.emf.cdo.tests.config.IRepositoryConfig;
import org.eclipse.emf.cdo.tests.config.impl.RepositoryConfig.OfflineConfig;
import org.eclipse.emf.cdo.tests.model1.Company;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CDOUtil;

import org.eclipse.net4j.util.event.IEvent;

import org.eclipse.emf.ecore.EObject;

import java.util.List;
import java.util.Map;

/**
 * @author Eike Stepper
 */
public class OfflineTest extends AbstractCDOTest
{
  public OfflineConfig getOfflineConfig()
  {
    return (OfflineConfig)getRepositoryConfig();
  }

  @Override
  public boolean isValid()
  {
    IRepositoryConfig repositoryConfig = getRepositoryConfig();
    return repositoryConfig instanceof OfflineConfig;
  }

  public void testMasterCommits_ArrivalInClone() throws Exception
  {
    CDOSession session = openSession(getRepository().getName() + "_master");
    CDOTransaction transaction = session.openTransaction();
    CDOResource resource = transaction.createResource("/my/resource");

    Company company = getModel1Factory().createCompany();
    company.setName("Test");

    // 2 * Root resource + folder + resource + company
    int expectedRevisions = 2 + 1 + 1 + 1;

    resource.getContents().add(company);
    long timeStamp = transaction.commit().getTimeStamp();
    checkClone(timeStamp, expectedRevisions);

    for (int i = 0; i < 10; i++)
    {
      company.setName("Test" + i);
      timeStamp = transaction.commit().getTimeStamp();
      expectedRevisions += 1; // Changed company
      checkClone(timeStamp, expectedRevisions);
    }

    for (int i = 0; i < 10; i++)
    {
      company.getCategories().add(getModel1Factory().createCategory());
      timeStamp = transaction.commit().getTimeStamp();
      expectedRevisions += 2; // Changed company + new category
      checkClone(timeStamp, expectedRevisions);
    }

    for (int i = 0; i < 10; i++)
    {
      company.getCategories().remove(0);
      timeStamp = transaction.commit().getTimeStamp();
      expectedRevisions += 2; // Changed company + detached category
      checkClone(timeStamp, expectedRevisions);
    }

    session.close();
  }

  private void checkClone(final long timeStamp, int expectedRevisions) throws InterruptedException
  {
    final InternalRepository repository = getRepository();
    long lastCommitTimeStamp = repository.getLastCommitTimeStamp();
    while (lastCommitTimeStamp < timeStamp)
    {
      lastCommitTimeStamp = repository.waitForCommit(DEFAULT_TIMEOUT);
    }

    InternalStore store = repository.getStore();
    if (store instanceof MEMStore)
    {
      Map<CDOBranch, List<CDORevision>> allRevisions = ((MEMStore)store).getAllRevisions();
      System.out.println("\n\n\n\n\n\n\n\n\n\n" + CDORevisionUtil.dumpAllRevisions(allRevisions));

      List<CDORevision> revisions = allRevisions.get(repository.getBranchManager().getMainBranch());
      assertEquals(expectedRevisions, revisions.size());
    }
  }

  public void testMasterCommits_NotificationsFromClone() throws Exception
  {
    CDOSession masterSession = openSession(getRepository().getName() + "_master");
    CDOTransaction transaction = masterSession.openTransaction();
    CDOResource resource = transaction.createResource("/my/resource");

    TestListener listener = new TestListener();
    CDOSession cloneSession = openSession();
    cloneSession.addListener(listener);

    Company company = getModel1Factory().createCompany();
    company.setName("Test");

    resource.getContents().add(company);
    long timeStamp = transaction.commit().getTimeStamp();
    assertEquals(true, cloneSession.waitForUpdate(timeStamp, DEFAULT_TIMEOUT));
    checkEvent(listener, 1, 3, 1, 0);

    for (int i = 0; i < 10; i++)
    {
      company.setName("Test" + i);
      timeStamp = transaction.commit().getTimeStamp();
      assertEquals(true, cloneSession.waitForUpdate(timeStamp, DEFAULT_TIMEOUT));
      checkEvent(listener, 0, 0, 1, 0);
    }

    for (int i = 0; i < 10; i++)
    {
      company.getCategories().add(getModel1Factory().createCategory());
      timeStamp = transaction.commit().getTimeStamp();
      assertEquals(true, cloneSession.waitForUpdate(timeStamp, DEFAULT_TIMEOUT));
      checkEvent(listener, 0, 1, 1, 0);
    }

    for (int i = 0; i < 10; i++)
    {
      company.getCategories().remove(0);
      timeStamp = transaction.commit().getTimeStamp();
      assertEquals(true, cloneSession.waitForUpdate(timeStamp, DEFAULT_TIMEOUT));
      checkEvent(listener, 0, 0, 1, 1);
    }

    cloneSession.close();
    masterSession.close();
  }

  private void checkEvent(TestListener listener, int newPackageUnits, int newObjects, int changedObjects,
      int detachedObjects)
  {
    IEvent[] events = listener.getEvents();
    assertEquals(1, events.length);

    IEvent event = events[0];
    if (event instanceof CDOSessionInvalidationEvent)
    {
      CDOSessionInvalidationEvent e = (CDOSessionInvalidationEvent)event;
      assertEquals(newPackageUnits, e.getNewPackageUnits().size());
      assertEquals(newObjects, e.getNewObjects().size());
      assertEquals(changedObjects, e.getChangedObjects().size());
      assertEquals(detachedObjects, e.getDetachedObjects().size());
    }
    else
    {
      fail("Invalid event: " + event);
    }

    listener.clearEvents();
  }

  public void testClientCommits() throws Exception
  {
    InternalRepository clone = getRepository();
    InternalRepository master = getRepository(clone.getName() + "_master");

    TestListener listener = new TestListener();
    CDOSession masterSession = openSession(master.getName());
    masterSession.addListener(listener);

    Company company = getModel1Factory().createCompany();
    company.setName("Test");

    CDOSession cloneSession = openSession();
    while (cloneSession.getRepositoryInfo().getState() != CDOCommonRepository.State.ONLINE)
    {
      System.out.println("Waiting for ONLINE...");
      sleep(1000);
    }

    CDOTransaction transaction = cloneSession.openTransaction();
    CDOResource resource = transaction.createResource("/my/resource");

    resource.getContents().add(company);
    transaction.commit();

    IEvent[] events = listener.getEvents();
    assertEquals(1, events.length);

    checkRevision(company, master);
    checkRevision(company, clone);
  }

  private void checkRevision(EObject object, InternalRepository repository)
  {
    // Check if revision arrived in cache
    checkRevision(object, repository.getRevisionManager().getCache().getAllRevisions());

    // Check if revision arrived in store
    InternalStore store = repository.getStore();
    if (store instanceof IMEMStore)
    {
      checkRevision(object, ((IMEMStore)store).getAllRevisions());
    }
  }

  private void checkRevision(EObject object, Map<CDOBranch, List<CDORevision>> allRevisions)
  {
    CDORevision revision = CDOUtil.getCDOObject(object).cdoRevision();
    List<CDORevision> revisions = allRevisions.get(revision.getBranch());
    for (CDORevision rev : revisions)
    {
      if (revision.equals(rev))
      {
        return;
      }
    }

    fail("Revision missing: " + revision);
  }

  public void testDisconnectAndSync() throws Exception
  {
    InternalRepository clone = getRepository();
    while (clone.getState() != CDOCommonRepository.State.ONLINE)
    {
      System.out.println("Waiting for ONLINE <-- " + clone.getState());
      sleep(100);
    }

    getOfflineConfig().stopMasterTransport();

    CDOSession masterSession = openSession(clone.getName() + "_master");
    CDOTransaction masterTransaction = masterSession.openTransaction();
    CDOResource masterResource = masterTransaction.createResource("/master/resource");

    masterResource.getContents().add(getModel1Factory().createCompany());
    masterTransaction.commit();

    masterResource.getContents().add(getModel1Factory().createCompany());
    masterTransaction.commit();

    masterTransaction.close();
    TestListener listener = new TestListener();
    masterSession.addListener(listener);

    Company company = getModel1Factory().createCompany();
    company.setName("Test");

    getOfflineConfig().startMasterTransport();

    while (clone.getState() != CDOCommonRepository.State.ONLINE)
    {
      System.out.println("Waiting for ONLINE <-- " + clone.getState());
      sleep(100);
    }

    CDOSession session = openSession();
    CDOTransaction transaction = session.openTransaction();
    CDOResource resource = transaction.createResource("/my/resource");

    resource.getContents().add(company);
    transaction.commit();

    IEvent[] events = listener.getEvents();
    assertEquals(1, events.length);
  }
}
