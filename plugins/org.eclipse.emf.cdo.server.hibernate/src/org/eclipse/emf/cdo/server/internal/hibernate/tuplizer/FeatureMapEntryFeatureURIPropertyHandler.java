/**
 * Copyright (c) 2004 - 2009 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Martin Taal - initial api
 *    Eike Stepper - maintenance
 */
package org.eclipse.emf.cdo.server.internal.hibernate.tuplizer;

import org.eclipse.emf.cdo.server.internal.hibernate.HibernateCommitContext;
import org.eclipse.emf.cdo.server.internal.hibernate.HibernateThreadContext;
import org.eclipse.emf.cdo.spi.common.revision.CDOFeatureMapEntry;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.hibernate.HibernateException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.Setter;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Handles the string representation of the feature of the feature map entry in the database.
 * 
 * @see CDOFeatureMapEntry#setEStructuralFeature(EStructuralFeature)
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 */
public class FeatureMapEntryFeatureURIPropertyHandler implements PropertyAccessor, Getter, Setter
{
  private static final String EFEATURE_SEPARATOR = "#";

  private static final long serialVersionUID = 1L;

  @SuppressWarnings("unchecked")
  public Getter getGetter(Class theClass, String propertyName) throws PropertyNotFoundException
  {
    return this;
  }

  @SuppressWarnings("unchecked")
  public Setter getSetter(Class theClass, String propertyName) throws PropertyNotFoundException
  {
    return this;
  }

  public Object get(Object owner) throws HibernateException
  {
    final CDOFeatureMapEntry cdoFeatureMapEntry = (CDOFeatureMapEntry)owner;
    return getEStructuralFeatureAsString(cdoFeatureMapEntry);
  }

  @SuppressWarnings("unchecked")
  public Object getForInsert(Object owner, Map mergeMap, SessionImplementor session) throws HibernateException
  {
    return get(owner);
  }

  public Method getMethod()
  {
    return null;
  }

  public String getMethodName()
  {
    return null;
  }

  @SuppressWarnings("unchecked")
  public Class getReturnType()
  {
    return String.class;
  }

  public void set(Object target, Object value, SessionFactoryImplementor factory) throws HibernateException
  {
    final CDOFeatureMapEntry cdoFeatureMapEntry = (CDOFeatureMapEntry)target;
    setEStructuralFeatureFromString(cdoFeatureMapEntry, (String)value);
  }

  /**
   * Set the EStructuralFeature (see {@link #setEStructuralFeature(EStructuralFeature)}) from its String representation.
   * 
   * @param eFeatureURI
   *          a String representation, must be created by the {@link #getEStructuralFeatureAsString()} method
   * @see #getEStructuralFeatureAsString()
   */
  private void setEStructuralFeatureFromString(CDOFeatureMapEntry fmEntry, String eFeatureURI)
  {
    final int firstSeparator = eFeatureURI.indexOf(EFEATURE_SEPARATOR);
    final int lastSeparator = eFeatureURI.lastIndexOf(EFEATURE_SEPARATOR);
    if (firstSeparator == -1 || lastSeparator == -1 || firstSeparator == lastSeparator)
    {
      throw new IllegalArgumentException("EFeature URI " + eFeatureURI + " has an illegal format");
    }

    final String ePackageURI = eFeatureURI.substring(0, firstSeparator);
    final String eClassName = eFeatureURI.substring(1 + firstSeparator, lastSeparator);
    final String eFeatureName = eFeatureURI.substring(1 + lastSeparator);

    final HibernateCommitContext hbCommitContext = HibernateThreadContext.getCommitContext();
    final EPackage ePackage = hbCommitContext.getCommitContext().getPackageRegistry().getEPackage(ePackageURI);
    if (ePackage == null)
    {
      throw new IllegalArgumentException("EPackage not found using " + eFeatureURI + " and EPackageURI: " + ePackageURI);
    }

    final EClass eClass = (EClass)ePackage.getEClassifier(eClassName);
    if (eClass == null)
    {
      throw new IllegalArgumentException("EClass not found using " + eFeatureURI + " and EClass name " + eClassName);
    }

    final EStructuralFeature eFeature = eClass.getEStructuralFeature(eFeatureName);
    if (eFeature == null)
    {
      throw new IllegalArgumentException("EClass not found using " + eFeatureURI + ", EClass name " + eClassName
          + " and EFeature name " + eFeatureName);
    }

    fmEntry.setEStructuralFeature(eFeature);
  }

  /**
   * @return a unique String version for an EStructuralFeature
   */
  private String getEStructuralFeatureAsString(CDOFeatureMapEntry fmEntry)
  {
    final EStructuralFeature eFeature = fmEntry.getEStructuralFeature();
    final EClass eClass = eFeature.getEContainingClass();
    return eClass.getEPackage().getNsURI() + EFEATURE_SEPARATOR + eClass.getName() + EFEATURE_SEPARATOR
        + eFeature.getName();
  }
}
