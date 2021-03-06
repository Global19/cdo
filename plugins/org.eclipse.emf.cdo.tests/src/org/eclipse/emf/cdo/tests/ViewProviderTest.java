/*
 * Copyright (c) 2010-2012, 2015, 2020 Eike Stepper (Loehne, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.tests;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jViewProvider;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.tests.config.impl.RepositoryConfig;
import org.eclipse.emf.cdo.tests.model1.OrderDetail;
import org.eclipse.emf.cdo.tests.model1.Product1;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CDOURIData;
import org.eclipse.emf.cdo.util.CDOURIUtil;
import org.eclipse.emf.cdo.util.CommitException;

import org.eclipse.emf.internal.cdo.session.CDOSessionFactory;
import org.eclipse.emf.internal.cdo.view.PluginContainerViewProvider;

import org.eclipse.net4j.util.container.IPluginContainer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * @author Eike Stepper
 */
public class ViewProviderTest extends AbstractCDOTest
{
  private static final String REPO = RepositoryConfig.REPOSITORY_NAME;

  private static final String PATH = "/library/My.company";

  private URI uri;

  private void init() throws CommitException
  {
    Product1 product = getModel1Factory().createProduct1();
    product.setName("ESC");

    CDOSession session = openSession();
    CDOTransaction transaction = session.openTransaction();
    CDOResource resource = transaction.createResource(getResourcePath(PATH));
    resource.getContents().add(product);
    transaction.commit();
    session.close();

    uri = URI.createURI(getURIPrefix() + "/" + REPO + getResourcePath(PATH) + "?transactional=true");
  }

  @SuppressWarnings("deprecation")
  public void testNormal() throws Exception
  {
    init();
    URI uri = CDOURIUtil.createResourceURI(REPO, getResourcePath(PATH));
    IPluginContainer.INSTANCE.putElement(CDOSessionFactory.PRODUCT_GROUP, "my-type", "my-description", openSession());

    ResourceSet resourceSet = new ResourceSetImpl();
    CDOResource resource = (CDOResource)resourceSet.getResource(uri, true);

    String path = resource.getPath();
    assertEquals(getResourcePath(PATH), path);

    Product1 product = (Product1)resource.getContents().get(0);
    assertEquals("ESC", product.getName());
  }

  public void testConnectionAware() throws Exception
  {
    init();
    ResourceSet resourceSet = new ResourceSetImpl();
    CDOResource resource = (CDOResource)resourceSet.getResource(uri, true);

    String path = resource.getPath();
    assertEquals(getResourcePath(PATH), path);

    Product1 product = (Product1)resource.getContents().get(0);
    assertEquals("ESC", product.getName());
  }

  public void testSerialize() throws Exception
  {
    init();
    ResourceSet resourceSet = new ResourceSetImpl();
    Map<String, Object> map = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
    map.put("xmi", new XMIResourceFactoryImpl());

    CDOResource resource = (CDOResource)resourceSet.getResource(uri, true);
    Product1 product = (Product1)resource.getContents().get(0);

    OrderDetail orderDetail = getModel1Factory().createOrderDetail();
    orderDetail.setProduct(product);

    Resource volatileResource = resourceSet.createResource(URI.createURI("volatile.xmi"));
    volatileResource.getContents().add(orderDetail);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    volatileResource.save(baos, null);
    String xmi = baos.toString();
    msg("CHECK FOR: " + uri);
    msg(xmi);
    assertEquals(true, xmi.indexOf(uri.toString()) != -1);
  }

  public void testPluginViewProvider() throws Exception
  {
    PluginContainerViewProvider vp = new PluginContainerViewProvider();

    assertEquals("cdo://repo1/", vp.getResourceURI("repo1", null).toString());
    assertEquals("cdo://repo1/", vp.getResourceURI("repo1", "").toString());
    assertEquals("cdo://repo1/", vp.getResourceURI("repo1", "/").toString());
    assertEquals("cdo://repo1/", vp.getResourceURI("repo1", "//").toString());
    assertEquals("cdo://repo1/a", vp.getResourceURI("repo1", "/a").toString());
    assertEquals("cdo://repo1/a", vp.getResourceURI("repo1", "//a").toString());
    assertEquals("cdo://repo1/a", vp.getResourceURI("repo1", "/a/").toString());
    assertEquals("cdo://repo1/a", vp.getResourceURI("repo1", "a").toString());
    assertEquals("cdo://repo1/a", vp.getResourceURI("repo1", "a/").toString());
    assertEquals("cdo://repo1/a/b", vp.getResourceURI("repo1", "a/b").toString());
    assertEquals("cdo://repo1/a/b", vp.getResourceURI("repo1", "a//b").toString());
  }

  public void testNet4jViewProvider() throws Exception
  {
    CDONet4jViewProvider vp = new CDONet4jViewProvider("xyz", 5000)
    {
    };

    assertEquals("cdo.net4j.xyz://localhost/repo1/?branch=MAIN/branch1&time=4711",
        vp.getResourceURI("xyz", "localhost", "repo1", null, "MAIN/branch1", 4711L, true).toString());

    assertEquals("cdo.net4j.xyz://localhost/repo1/?branch=MAIN/branch1&time=4711",
        vp.getResourceURI("xyz", "localhost", "repo1", "", "MAIN/branch1", 4711L, true).toString());

    assertEquals("cdo.net4j.xyz://localhost/repo1/?branch=MAIN/branch1&time=4711",
        vp.getResourceURI("xyz", "localhost", "repo1", "/", "MAIN/branch1", 4711L, true).toString());

    assertEquals("cdo.net4j.xyz://localhost/repo1/?branch=MAIN/branch1&time=4711",
        vp.getResourceURI("xyz", "localhost", "repo1", "//", "MAIN/branch1", 4711L, true).toString());

    assertEquals("cdo.net4j.xyz://localhost/repo1/a?branch=MAIN/branch1&time=4711",
        vp.getResourceURI("xyz", "localhost", "repo1", "/a", "MAIN/branch1", 4711L, true).toString());

    assertEquals("cdo.net4j.xyz://localhost/repo1/a?branch=MAIN/branch1&time=4711",
        vp.getResourceURI("xyz", "localhost", "repo1", "//a", "MAIN/branch1", 4711L, true).toString());

    assertEquals("cdo.net4j.xyz://localhost/repo1/a?branch=MAIN/branch1&time=4711",
        vp.getResourceURI("xyz", "localhost", "repo1", "/a/", "MAIN/branch1", 4711L, true).toString());

    assertEquals("cdo.net4j.xyz://localhost/repo1/a?branch=MAIN/branch1&time=4711",
        vp.getResourceURI("xyz", "localhost", "repo1", "a", "MAIN/branch1", 4711L, true).toString());

    assertEquals("cdo.net4j.xyz://localhost/repo1/a?branch=MAIN/branch1&time=4711",
        vp.getResourceURI("xyz", "localhost", "repo1", "a/", "MAIN/branch1", 4711L, true).toString());

    assertEquals("cdo.net4j.xyz://localhost/repo1/a/b?branch=MAIN/branch1&time=4711",
        vp.getResourceURI("xyz", "localhost", "repo1", "a/b", "MAIN/branch1", 4711L, true).toString());

    assertEquals("cdo.net4j.xyz://localhost/repo1/a/b?branch=MAIN/branch1&time=4711",
        vp.getResourceURI("xyz", "localhost", "repo1", "a//b", "MAIN/branch1", 4711L, true).toString());

    // Test getViewURI(URI):

    URI resourceURI = URI.createURI("cdo.net4j.xyz://localhost/repo1/a/b?branch=MAIN/branch1&time=4711");
    assertEquals("cdo.net4j.xyz://localhost/repo1?branch=MAIN/branch1&time=4711", vp.getViewURI(resourceURI).toString());

  }

  public void testURIs() throws Exception
  {
    checkURI("cdo.net4j.tcp://eike:passw@127.0.0.1:2042/repo/folder/resource", true);
    checkURI("cdo.net4j.tcp://eike@127.0.0.1:2042/repo/folder/resource", true);
    checkURI("cdo.net4j.tcp://127.0.0.1:2042/repo/folder/resource", true);
    checkURI("cdo.net4j.tcp://127.0.0.1:2042/repo/resource", true);
    checkURI("cdo.net4j.tcp://127.0.0.1/repo/resource", true);
    checkURI("cdo.net4j.xyz://127.0.0.1/repo/resource", true);

    checkURI("cdo.net4j.tcp://127.0.0.1/repo/resource?branch=MAIN/team1", true);
    checkURI("cdo.net4j.tcp://127.0.0.1/repo/resource?branch=MAIN/team1&time=12345678987", true);
    checkURI("cdo.net4j.tcp://127.0.0.1/repo/resource?branch=MAIN/team1&transactional=true", true);

    checkURI("cdo.net4j.tcp://127.0.0.1/repo/resource?branch=MAIN/team1&time=12345&transactional=false", false);
    checkURI("cdo.net4j.tcp://127.0.0.1/repo/resource?branch=MAIN/team1&transactional=false", false);
    checkURI("cdo.net4j.tcp://127.0.0.1/repo/resource?branch=MAIN/team1&time=HEAD", false);
    checkURI("cdo.net4j.tcp://127.0.0.1/repo/resource?branch=MAIN", false);
    checkURI("cdo.net4j.tcp://127.0.0.1/repo/resource?branch=MAIN&time=HEAD", false);
  }

  private static void checkURI(String uri, boolean valid)
  {
    URI uri1 = URI.createURI(uri);
    CDOURIData data = new CDOURIData(uri1);
    URI uri2 = data.toURI();
    if (valid)
    {
      assertEquals(uri1, uri2);
    }
    else
    {
      assertNotSame(uri1, uri2);
    }
  }
}
