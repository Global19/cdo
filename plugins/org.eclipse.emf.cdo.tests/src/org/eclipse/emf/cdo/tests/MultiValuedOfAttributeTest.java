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
package org.eclipse.emf.cdo.tests;

import org.eclipse.emf.cdo.CDOSession;
import org.eclipse.emf.cdo.CDOTransaction;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.tests.model5.Doctor;
import org.eclipse.emf.cdo.tests.model5.TestFeatureMap;
import org.eclipse.emf.cdo.util.CDOUtil;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Simon McDuff
 */
public class MultiValuedOfAttributeTest extends AbstractCDOTest
{
  public void testFeatureMaps() throws Exception
  {
    {
      CDOSession session = openSession(getModel5Package());
      CDOTransaction transaction = session.openTransaction();
      CDOResource resource = transaction.createResource("/res1");

      TestFeatureMap featureMap = getModel5Factory().createTestFeatureMap();

      Doctor doctor1 = getModel5Factory().createDoctor();
      Doctor doctor2 = getModel5Factory().createDoctor();
      resource.getContents().add(doctor1);
      resource.getContents().add(doctor2);

      featureMap.getPeople().add(getModel5Package().getTestFeatureMap_Doctors(), doctor1);
      featureMap.getPeople().add(getModel5Package().getTestFeatureMap_Doctors(), doctor2);

      resource.getContents().add(featureMap);

      assertEquals(doctor1, featureMap.getPeople().get(0).getValue());
      List<?> listForFeatureCustomers = (List<?>)featureMap.getPeople().get(
          getModel5Package().getTestFeatureMap_Doctors(), true);
      assertEquals(doctor1, listForFeatureCustomers.get(0));
      assertEquals(doctor2, listForFeatureCustomers.get(1));
      transaction.commit();
    }

    clearCache(getRepository().getRevisionManager());

    CDOSession session = openSession(getModel5Package());
    CDOTransaction transaction = session.openTransaction();
    CDOResource resource = transaction.getResource("/res1");

    TestFeatureMap featureMap = (TestFeatureMap)resource.getContents().get(0);
    List<?> listForFeatureCustomers = (List<?>)featureMap.getPeople().get(
        getModel5Package().getTestFeatureMap_Doctors(), true);
    assertTrue(listForFeatureCustomers.get(0) instanceof Doctor);
    assertTrue(listForFeatureCustomers.get(1) instanceof Doctor);
  }

  public void testListOfString() throws Exception
  {
    List<String> list = new ArrayList<String>();
    list.add("Ottawa");
    list.add("Toronto");
    list.add("Berlin");
    testMultiValuedIOfAttribute(list, getModel5Package().getGenListOfString(), getModel5Package()
        .getGenListOfString_Elements());
  }

  public void testListOfDate() throws Exception
  {
    List<Date> list = new ArrayList<Date>();
    list.add(new Date(1000));
    list.add(new Date());
    list.add(new Date(new Date().getTime() - 100));
    testMultiValuedIOfAttribute(list, getModel5Package().getGenListOfDate(), getModel5Package()
        .getGenListOfDate_Elements());
  }

  public void testListOfInt() throws Exception
  {
    List<Integer> list = new ArrayList<Integer>();
    list.add(10);
    list.add(11);
    list.add(20);
    testMultiValuedIOfAttribute(list, getModel5Package().getGenListOfInt(), getModel5Package()
        .getGenListOfInt_Elements());
  }

  public void testListOfShort() throws Exception
  {
    List<Short> list = new ArrayList<Short>();
    list.add((short)10);
    list.add((short)11);
    list.add((short)20);
    testMultiValuedIOfAttribute(list, getModel5Package().getGenListOfShort(), getModel5Package()
        .getGenListOfShort_Elements());
  }

  public void testListOfFloat() throws Exception
  {
    List<Float> list = new ArrayList<Float>();
    list.add((float)10);
    list.add((float)11);
    list.add((float)20);
    testMultiValuedIOfAttribute(list, getModel5Package().getGenListOfFloat(), getModel5Package()
        .getGenListOfFloat_Elements());
  }

  public void testListOfChar() throws Exception
  {
    List<Character> list = new ArrayList<Character>();
    list.add('c');
    list.add('d');
    list.add('z');
    testMultiValuedIOfAttribute(list, getModel5Package().getGenListOfChar(), getModel5Package()
        .getGenListOfChar_Elements());
  }

  public void testListOfBoolean() throws Exception
  {
    List<Boolean> list = new ArrayList<Boolean>();
    list.add(true);
    list.add(false);
    testMultiValuedIOfAttribute(list, getModel5Package().getGenListOfBoolean(), getModel5Package()
        .getGenListOfBoolean_Elements());
  }

  public void testListOfDouble() throws Exception
  {
    List<Double> list = new ArrayList<Double>();
    list.add(10.1928);
    list.add(11.12);
    list.add(20.99991);
    testMultiValuedIOfAttribute(list, getModel5Package().getGenListOfDouble(), getModel5Package()
        .getGenListOfDouble_Elements());
  }

  public void testListOfInteger() throws Exception
  {
    List<Integer> list = new ArrayList<Integer>();
    list.add(10);
    list.add(null);
    list.add(20);
    testMultiValuedIOfAttribute(list, getModel5Package().getGenListOfInteger(), getModel5Package()
        .getGenListOfInteger_Elements());
  }

  @SuppressWarnings("unchecked")
  private <T> void testMultiValuedIOfAttribute(List<T> list, EClass containerClass, EStructuralFeature feature)
  {
    {
      CDOSession session = openSession(getModel5Package());
      CDOTransaction transaction = session.openTransaction();
      CDOResource resource = transaction.createResource("/res1");

      EObject eGenObject = EcoreUtil.create(containerClass);
      EList<T> elements = (EList<T>)eGenObject.eGet(feature);
      for (int i = 0; i < list.size() - 1; i++)
      {
        elements.add(list.get(i));
      }
      resource.getContents().add(eGenObject);
      transaction.commit();
    }

    clearCache(getRepository().getRevisionManager());
    {
      CDOSession session = openSession(getModel5Package());
      CDOTransaction transaction = session.openTransaction();
      CDOResource resource = transaction.getResource("/res1");

      EObject eGenObject = resource.getContents().get(0);
      EList<T> elements = (EList<T>)eGenObject.eGet(feature);

      for (int i = 0; i < list.size() - 1; i++)
      {
        assertEquals(elements.get(i), list.get(i));
      }
      elements.add(list.get(list.size() - 1));
      transaction.commit();
    }

    clearCache(getRepository().getRevisionManager());

    {
      CDOSession session = openSession(getModel5Package());
      session.setCollectionLoadingPolicy(CDOUtil.createCollectionLoadingPolicy(0, 100));
      CDOTransaction transaction = session.openTransaction();
      CDOResource resource = transaction.getResource("/res1");

      EObject eGenObject = resource.getContents().get(0);
      EList<T> elements = (EList<T>)eGenObject.eGet(feature);

      for (int i = 0; i < list.size() - 1; i++)
      {
        assertEquals(elements.get(i), list.get(i));
      }
      transaction.commit();
    }
  }
}
