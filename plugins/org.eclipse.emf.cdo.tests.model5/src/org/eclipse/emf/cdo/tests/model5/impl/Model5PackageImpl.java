/**
 * <copyright>
 * </copyright>
 *
 * $Id: Model5PackageImpl.java,v 1.3 2008-12-03 03:06:15 smcduff Exp $
 */
package org.eclipse.emf.cdo.tests.model5.impl;

import org.eclipse.emf.cdo.tests.model5.Doctor;
import org.eclipse.emf.cdo.tests.model5.GenListOfBoolean;
import org.eclipse.emf.cdo.tests.model5.GenListOfChar;
import org.eclipse.emf.cdo.tests.model5.GenListOfDate;
import org.eclipse.emf.cdo.tests.model5.GenListOfDouble;
import org.eclipse.emf.cdo.tests.model5.GenListOfFloat;
import org.eclipse.emf.cdo.tests.model5.GenListOfInt;
import org.eclipse.emf.cdo.tests.model5.GenListOfInteger;
import org.eclipse.emf.cdo.tests.model5.GenListOfLong;
import org.eclipse.emf.cdo.tests.model5.GenListOfShort;
import org.eclipse.emf.cdo.tests.model5.GenListOfString;
import org.eclipse.emf.cdo.tests.model5.Manager;
import org.eclipse.emf.cdo.tests.model5.Model5Factory;
import org.eclipse.emf.cdo.tests.model5.Model5Package;
import org.eclipse.emf.cdo.tests.model5.TestFeatureMap;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class Model5PackageImpl extends EPackageImpl implements Model5Package
{
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass testFeatureMapEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass managerEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass doctorEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass genListOfIntEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass genListOfIntegerEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass genListOfLongEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass genListOfBooleanEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass genListOfShortEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass genListOfFloatEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass genListOfDoubleEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass genListOfDateEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass genListOfCharEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass genListOfStringEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
   * EPackage.Registry} by the package package URI value.
   * <p>
   * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
   * performs initialization of the package, or returns the registered package, if one already exists. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.emf.cdo.tests.model5.Model5Package#eNS_URI
   * @see #init()
   * @generated
   */
  private Model5PackageImpl()
  {
    super(eNS_URI, Model5Factory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * Simple dependencies are satisfied by calling this method on all dependent packages before doing anything else. This
   * method drives initialization for interdependent packages directly, in parallel with this package, itself.
   * <p>
   * Of this package and its interdependencies, all packages which have not yet been registered by their URI values are
   * first created and registered. The packages are then initialized in two steps: meta-model objects for all of the
   * packages are created before any are initialized, since one package's meta-model objects may refer to those of
   * another.
   * <p>
   * Invocation of this method will not affect any packages that have already been initialized. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static Model5Package init()
  {
    if (isInited)
    {
      return (Model5Package)EPackage.Registry.INSTANCE.getEPackage(Model5Package.eNS_URI);
    }

    // Obtain or create and register package
    Model5PackageImpl theModel5Package = (Model5PackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof Model5PackageImpl ? EPackage.Registry.INSTANCE
        .getEPackage(eNS_URI)
        : new Model5PackageImpl());

    isInited = true;

    // Create package meta-data objects
    theModel5Package.createPackageContents();

    // Initialize created meta-data
    theModel5Package.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theModel5Package.freeze();

    return theModel5Package;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getTestFeatureMap()
  {
    return testFeatureMapEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getTestFeatureMap_Managers()
  {
    return (EReference)testFeatureMapEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getTestFeatureMap_Doctors()
  {
    return (EReference)testFeatureMapEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getTestFeatureMap_People()
  {
    return (EAttribute)testFeatureMapEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getManager()
  {
    return managerEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getDoctor()
  {
    return doctorEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getGenListOfInt()
  {
    return genListOfIntEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getGenListOfInt_Elements()
  {
    return (EAttribute)genListOfIntEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getGenListOfInteger()
  {
    return genListOfIntegerEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getGenListOfInteger_Elements()
  {
    return (EAttribute)genListOfIntegerEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getGenListOfLong()
  {
    return genListOfLongEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getGenListOfLong_Elements()
  {
    return (EAttribute)genListOfLongEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getGenListOfBoolean()
  {
    return genListOfBooleanEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getGenListOfBoolean_Elements()
  {
    return (EAttribute)genListOfBooleanEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getGenListOfShort()
  {
    return genListOfShortEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getGenListOfShort_Elements()
  {
    return (EAttribute)genListOfShortEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getGenListOfFloat()
  {
    return genListOfFloatEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getGenListOfFloat_Elements()
  {
    return (EAttribute)genListOfFloatEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getGenListOfDouble()
  {
    return genListOfDoubleEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getGenListOfDouble_Elements()
  {
    return (EAttribute)genListOfDoubleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getGenListOfDate()
  {
    return genListOfDateEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getGenListOfDate_Elements()
  {
    return (EAttribute)genListOfDateEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getGenListOfChar()
  {
    return genListOfCharEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getGenListOfChar_Elements()
  {
    return (EAttribute)genListOfCharEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getGenListOfString()
  {
    return genListOfStringEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getGenListOfString_Elements()
  {
    return (EAttribute)genListOfStringEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Model5Factory getModel5Factory()
  {
    return (Model5Factory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but its
   * first. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated)
    {
      return;
    }
    isCreated = true;

    // Create classes and their features
    testFeatureMapEClass = createEClass(TEST_FEATURE_MAP);
    createEReference(testFeatureMapEClass, TEST_FEATURE_MAP__MANAGERS);
    createEReference(testFeatureMapEClass, TEST_FEATURE_MAP__DOCTORS);
    createEAttribute(testFeatureMapEClass, TEST_FEATURE_MAP__PEOPLE);

    managerEClass = createEClass(MANAGER);

    doctorEClass = createEClass(DOCTOR);

    genListOfStringEClass = createEClass(GEN_LIST_OF_STRING);
    createEAttribute(genListOfStringEClass, GEN_LIST_OF_STRING__ELEMENTS);

    genListOfIntEClass = createEClass(GEN_LIST_OF_INT);
    createEAttribute(genListOfIntEClass, GEN_LIST_OF_INT__ELEMENTS);

    genListOfIntegerEClass = createEClass(GEN_LIST_OF_INTEGER);
    createEAttribute(genListOfIntegerEClass, GEN_LIST_OF_INTEGER__ELEMENTS);

    genListOfLongEClass = createEClass(GEN_LIST_OF_LONG);
    createEAttribute(genListOfLongEClass, GEN_LIST_OF_LONG__ELEMENTS);

    genListOfBooleanEClass = createEClass(GEN_LIST_OF_BOOLEAN);
    createEAttribute(genListOfBooleanEClass, GEN_LIST_OF_BOOLEAN__ELEMENTS);

    genListOfShortEClass = createEClass(GEN_LIST_OF_SHORT);
    createEAttribute(genListOfShortEClass, GEN_LIST_OF_SHORT__ELEMENTS);

    genListOfFloatEClass = createEClass(GEN_LIST_OF_FLOAT);
    createEAttribute(genListOfFloatEClass, GEN_LIST_OF_FLOAT__ELEMENTS);

    genListOfDoubleEClass = createEClass(GEN_LIST_OF_DOUBLE);
    createEAttribute(genListOfDoubleEClass, GEN_LIST_OF_DOUBLE__ELEMENTS);

    genListOfDateEClass = createEClass(GEN_LIST_OF_DATE);
    createEAttribute(genListOfDateEClass, GEN_LIST_OF_DATE__ELEMENTS);

    genListOfCharEClass = createEClass(GEN_LIST_OF_CHAR);
    createEAttribute(genListOfCharEClass, GEN_LIST_OF_CHAR__ELEMENTS);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
   * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized)
    {
      return;
    }
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(testFeatureMapEClass, TestFeatureMap.class, "TestFeatureMap", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTestFeatureMap_Managers(), getManager(), null, "managers", null, 0, -1, TestFeatureMap.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEReference(getTestFeatureMap_Doctors(), getDoctor(), null, "doctors", null, 0, -1, TestFeatureMap.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTestFeatureMap_People(), ecorePackage.getEFeatureMapEntry(), "people", null, 0, -1,
        TestFeatureMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(managerEClass, Manager.class, "Manager", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(doctorEClass, Doctor.class, "Doctor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(genListOfStringEClass, GenListOfString.class, "GenListOfString", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenListOfString_Elements(), ecorePackage.getEString(), "elements", null, 0, -1,
        GenListOfString.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(genListOfIntEClass, GenListOfInt.class, "GenListOfInt", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenListOfInt_Elements(), ecorePackage.getEInt(), "elements", null, 0, -1, GenListOfInt.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genListOfIntegerEClass, GenListOfInteger.class, "GenListOfInteger", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenListOfInteger_Elements(), ecorePackage.getEIntegerObject(), "elements", null, 0, -1,
        GenListOfInteger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(genListOfLongEClass, GenListOfLong.class, "GenListOfLong", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenListOfLong_Elements(), ecorePackage.getELong(), "elements", null, 0, -1, GenListOfLong.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genListOfBooleanEClass, GenListOfBoolean.class, "GenListOfBoolean", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenListOfBoolean_Elements(), ecorePackage.getEBoolean(), "elements", null, 0, -1,
        GenListOfBoolean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(genListOfShortEClass, GenListOfShort.class, "GenListOfShort", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenListOfShort_Elements(), ecorePackage.getEShort(), "elements", null, 0, -1,
        GenListOfShort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(genListOfFloatEClass, GenListOfFloat.class, "GenListOfFloat", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenListOfFloat_Elements(), ecorePackage.getEFloat(), "elements", null, 0, -1,
        GenListOfFloat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(genListOfDoubleEClass, GenListOfDouble.class, "GenListOfDouble", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenListOfDouble_Elements(), ecorePackage.getEDouble(), "elements", null, 0, -1,
        GenListOfDouble.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(genListOfDateEClass, GenListOfDate.class, "GenListOfDate", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenListOfDate_Elements(), ecorePackage.getEDate(), "elements", null, 0, -1, GenListOfDate.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(genListOfCharEClass, GenListOfChar.class, "GenListOfChar", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGenListOfChar_Elements(), ecorePackage.getEChar(), "elements", null, 0, -1, GenListOfChar.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
    createExtendedMetaDataAnnotations();
  }

  /**
   * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void createExtendedMetaDataAnnotations()
  {
    String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
    addAnnotation(getTestFeatureMap_Managers(), source, new String[] { "group", "#people" });
    addAnnotation(getTestFeatureMap_Doctors(), source, new String[] { "group", "#people" });
    addAnnotation(getTestFeatureMap_People(), source, new String[] { "kind", "group" });
  }

} // Model5PackageImpl
