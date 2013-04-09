/**
 */
package org.eclipse.emf.cdo.tests.model6.legacy;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * @extends org.eclipse.emf.cdo.tests.model6.Model6Package
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.cdo.tests.model6.legacy.Model6Factory
 * @model kind="package"
 * @generated
 */
public interface Model6Package extends EPackage, org.eclipse.emf.cdo.tests.model6.Model6Package
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "model6";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  String eNS_URI = "http://www.eclipse.org/emf/CDO/tests/legacy/model6/1.0.0";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "model6";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Model6Package eINSTANCE = org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.RootImpl <em>Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.RootImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getRoot()
   * @generated
   */
  int ROOT = 0;

  /**
   * The feature id for the '<em><b>List A</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROOT__LIST_A = 0;

  /**
   * The feature id for the '<em><b>List B</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROOT__LIST_B = 1;

  /**
   * The feature id for the '<em><b>List C</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROOT__LIST_C = 2;

  /**
   * The feature id for the '<em><b>List D</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROOT__LIST_D = 3;

  /**
   * The number of structural features of the '<em>Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROOT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.BaseObjectImpl <em>Base Object</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.BaseObjectImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getBaseObject()
   * @generated
   */
  int BASE_OBJECT = 1;

  /**
   * The feature id for the '<em><b>Attribute Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BASE_OBJECT__ATTRIBUTE_OPTIONAL = 0;

  /**
   * The feature id for the '<em><b>Attribute Required</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BASE_OBJECT__ATTRIBUTE_REQUIRED = 1;

  /**
   * The feature id for the '<em><b>Attribute List</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BASE_OBJECT__ATTRIBUTE_LIST = 2;

  /**
   * The number of structural features of the '<em>Base Object</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BASE_OBJECT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.ReferenceObjectImpl <em>Reference Object</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.ReferenceObjectImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getReferenceObject()
   * @generated
   */
  int REFERENCE_OBJECT = 2;

  /**
   * The feature id for the '<em><b>Attribute Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_OBJECT__ATTRIBUTE_OPTIONAL = BASE_OBJECT__ATTRIBUTE_OPTIONAL;

  /**
   * The feature id for the '<em><b>Attribute Required</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_OBJECT__ATTRIBUTE_REQUIRED = BASE_OBJECT__ATTRIBUTE_REQUIRED;

  /**
   * The feature id for the '<em><b>Attribute List</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_OBJECT__ATTRIBUTE_LIST = BASE_OBJECT__ATTRIBUTE_LIST;

  /**
   * The feature id for the '<em><b>Reference Optional</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_OBJECT__REFERENCE_OPTIONAL = BASE_OBJECT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Reference List</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_OBJECT__REFERENCE_LIST = BASE_OBJECT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Reference Object</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_OBJECT_FEATURE_COUNT = BASE_OBJECT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.ContainmentObjectImpl <em>Containment Object</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.ContainmentObjectImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getContainmentObject()
   * @generated
   */
  int CONTAINMENT_OBJECT = 3;

  /**
   * The feature id for the '<em><b>Attribute Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINMENT_OBJECT__ATTRIBUTE_OPTIONAL = BASE_OBJECT__ATTRIBUTE_OPTIONAL;

  /**
   * The feature id for the '<em><b>Attribute Required</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINMENT_OBJECT__ATTRIBUTE_REQUIRED = BASE_OBJECT__ATTRIBUTE_REQUIRED;

  /**
   * The feature id for the '<em><b>Attribute List</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINMENT_OBJECT__ATTRIBUTE_LIST = BASE_OBJECT__ATTRIBUTE_LIST;

  /**
   * The feature id for the '<em><b>Containment Optional</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINMENT_OBJECT__CONTAINMENT_OPTIONAL = BASE_OBJECT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Containment List</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINMENT_OBJECT__CONTAINMENT_LIST = BASE_OBJECT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Containment Object</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINMENT_OBJECT_FEATURE_COUNT = BASE_OBJECT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.UnorderedListImpl <em>Unordered List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.UnorderedListImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getUnorderedList()
   * @generated
   */
  int UNORDERED_LIST = 4;

  /**
   * The feature id for the '<em><b>Contained</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNORDERED_LIST__CONTAINED = 0;

  /**
   * The feature id for the '<em><b>Referenced</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNORDERED_LIST__REFERENCED = 1;

  /**
   * The number of structural features of the '<em>Unordered List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNORDERED_LIST_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.PropertiesMapImpl <em>Properties Map</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.PropertiesMapImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getPropertiesMap()
   * @generated
   */
  int PROPERTIES_MAP = 5;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTIES_MAP__LABEL = 0;

  /**
   * The feature id for the '<em><b>Persistent Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTIES_MAP__PERSISTENT_MAP = 1;

  /**
   * The feature id for the '<em><b>Transient Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTIES_MAP__TRANSIENT_MAP = 2;

  /**
   * The number of structural features of the '<em>Properties Map</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTIES_MAP_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.PropertiesMapEntryImpl <em>Properties Map Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.PropertiesMapEntryImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getPropertiesMapEntry()
   * @generated
   */
  int PROPERTIES_MAP_ENTRY = 6;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTIES_MAP_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTIES_MAP_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the '<em>Properties Map Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTIES_MAP_ENTRY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.PropertiesMapEntryValueImpl <em>Properties Map Entry Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.PropertiesMapEntryValueImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getPropertiesMapEntryValue()
   * @generated
   */
  int PROPERTIES_MAP_ENTRY_VALUE = 7;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTIES_MAP_ENTRY_VALUE__LABEL = 0;

  /**
   * The number of structural features of the '<em>Properties Map Entry Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTIES_MAP_ENTRY_VALUE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.AImpl <em>A</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.AImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getA()
   * @generated
   */
  int A = 8;

  /**
   * The feature id for the '<em><b>Owned Ds</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__OWNED_DS = 0;

  /**
   * The feature id for the '<em><b>Owned Bs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__OWNED_BS = 1;

  /**
   * The number of structural features of the '<em>A</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.BImpl <em>B</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.BImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getB()
   * @generated
   */
  int B = 9;

  /**
   * The feature id for the '<em><b>Owned C</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int B__OWNED_C = 0;

  /**
   * The number of structural features of the '<em>B</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int B_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.CImpl <em>C</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.CImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getC()
   * @generated
   */
  int C = 10;

  /**
   * The number of structural features of the '<em>C</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.DImpl <em>D</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.DImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getD()
   * @generated
   */
  int D = 11;

  /**
   * The feature id for the '<em><b>Data</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int D__DATA = 0;

  /**
   * The number of structural features of the '<em>D</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int D_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.EImpl <em>E</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.EImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getE()
   * @generated
   */
  int E = 12;

  /**
   * The feature id for the '<em><b>Owned As</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int E__OWNED_AS = 0;

  /**
   * The number of structural features of the '<em>E</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int E_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.FImpl <em>F</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.FImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getF()
   * @generated
   */
  int F = 13;

  /**
   * The feature id for the '<em><b>Owned Es</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int F__OWNED_ES = 0;

  /**
   * The number of structural features of the '<em>F</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int F_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.GImpl <em>G</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.GImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getG()
   * @generated
   */
  int G = 14;

  /**
   * The feature id for the '<em><b>Dummy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int G__DUMMY = 0;

  /**
   * The feature id for the '<em><b>Reference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int G__REFERENCE = 1;

  /**
   * The feature id for the '<em><b>List</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int G__LIST = 2;

  /**
   * The number of structural features of the '<em>G</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int G_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.MyEnumListImpl <em>My Enum List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.MyEnumListImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getMyEnumList()
   * @generated
   */
  int MY_ENUM_LIST = 15;

  /**
   * The feature id for the '<em><b>My Enum</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MY_ENUM_LIST__MY_ENUM = 0;

  /**
   * The number of structural features of the '<em>My Enum List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MY_ENUM_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.MyEnumListUnsettableImpl <em>My Enum List Unsettable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.MyEnumListUnsettableImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getMyEnumListUnsettable()
   * @generated
   */
  int MY_ENUM_LIST_UNSETTABLE = 16;

  /**
   * The feature id for the '<em><b>My Enum</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MY_ENUM_LIST_UNSETTABLE__MY_ENUM = 0;

  /**
   * The number of structural features of the '<em>My Enum List Unsettable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MY_ENUM_LIST_UNSETTABLE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.HoldableImpl <em>Holdable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.HoldableImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getHoldable()
   * @generated
   */
  int HOLDABLE = 19;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HOLDABLE__NAME = 0;

  /**
   * The number of structural features of the '<em>Holdable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HOLDABLE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.HolderImpl <em>Holder</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.HolderImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getHolder()
   * @generated
   */
  int HOLDER = 17;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HOLDER__NAME = HOLDABLE__NAME;

  /**
   * The feature id for the '<em><b>Held</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HOLDER__HELD = HOLDABLE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Owned</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HOLDER__OWNED = HOLDABLE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Holder</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HOLDER_FEATURE_COUNT = HOLDABLE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.ThingImpl <em>Thing</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.ThingImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getThing()
   * @generated
   */
  int THING = 18;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THING__NAME = HOLDABLE__NAME;

  /**
   * The number of structural features of the '<em>Thing</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THING_FEATURE_COUNT = HOLDABLE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.HasNillableAttributeImpl <em>Has Nillable Attribute</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.HasNillableAttributeImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getHasNillableAttribute()
   * @generated
   */
  int HAS_NILLABLE_ATTRIBUTE = 20;

  /**
   * The feature id for the '<em><b>Nillable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HAS_NILLABLE_ATTRIBUTE__NILLABLE = 0;

  /**
   * The number of structural features of the '<em>Has Nillable Attribute</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HAS_NILLABLE_ATTRIBUTE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.EmptyStringDefaultImpl <em>Empty String Default</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.EmptyStringDefaultImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getEmptyStringDefault()
   * @generated
   */
  int EMPTY_STRING_DEFAULT = 21;

  /**
   * The feature id for the '<em><b>Attribute</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMPTY_STRING_DEFAULT__ATTRIBUTE = 0;

  /**
   * The number of structural features of the '<em>Empty String Default</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMPTY_STRING_DEFAULT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.EmptyStringDefaultUnsettableImpl <em>Empty String Default Unsettable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.EmptyStringDefaultUnsettableImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getEmptyStringDefaultUnsettable()
   * @generated
   */
  int EMPTY_STRING_DEFAULT_UNSETTABLE = 22;

  /**
   * The feature id for the '<em><b>Attribute</b></em>' attribute.
   * <!-- begin-user-doc -->
  	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMPTY_STRING_DEFAULT_UNSETTABLE__ATTRIBUTE = 0;

  /**
   * The number of structural features of the '<em>Empty String Default Unsettable</em>' class.
   * <!-- begin-user-doc -->
  	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMPTY_STRING_DEFAULT_UNSETTABLE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.legacy.impl.UnsettableAttributesImpl <em>Unsettable Attributes</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.UnsettableAttributesImpl
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getUnsettableAttributes()
   * @generated
   */
  int UNSETTABLE_ATTRIBUTES = 23;

  /**
   * The feature id for the '<em><b>Attr Big Decimal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_BIG_DECIMAL = 0;

  /**
   * The feature id for the '<em><b>Attr Big Integer</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_BIG_INTEGER = 1;

  /**
   * The feature id for the '<em><b>Attr Boolean</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_BOOLEAN = 2;

  /**
   * The feature id for the '<em><b>Attr Boolean Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_BOOLEAN_OBJECT = 3;

  /**
   * The feature id for the '<em><b>Attr Byte</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_BYTE = 4;

  /**
   * The feature id for the '<em><b>Attr Byte Array</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_BYTE_ARRAY = 5;

  /**
   * The feature id for the '<em><b>Attr Byte Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_BYTE_OBJECT = 6;

  /**
   * The feature id for the '<em><b>Attr Char</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_CHAR = 7;

  /**
   * The feature id for the '<em><b>Attr Character Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_CHARACTER_OBJECT = 8;

  /**
   * The feature id for the '<em><b>Attr Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_DATE = 9;

  /**
   * The feature id for the '<em><b>Attr Double</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_DOUBLE = 10;

  /**
   * The feature id for the '<em><b>Attr Double Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_DOUBLE_OBJECT = 11;

  /**
   * The feature id for the '<em><b>Attr Float</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_FLOAT = 12;

  /**
   * The feature id for the '<em><b>Attr Float Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_FLOAT_OBJECT = 13;

  /**
   * The feature id for the '<em><b>Attr Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_INT = 14;

  /**
   * The feature id for the '<em><b>Attr Integer Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_INTEGER_OBJECT = 15;

  /**
   * The feature id for the '<em><b>Attr Java Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_JAVA_CLASS = 16;

  /**
   * The feature id for the '<em><b>Attr Java Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_JAVA_OBJECT = 17;

  /**
   * The feature id for the '<em><b>Attr Long</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_LONG = 18;

  /**
   * The feature id for the '<em><b>Attr Long Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_LONG_OBJECT = 19;

  /**
   * The feature id for the '<em><b>Attr Short</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_SHORT = 20;

  /**
   * The feature id for the '<em><b>Attr Short Object</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_SHORT_OBJECT = 21;

  /**
   * The feature id for the '<em><b>Attr String</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES__ATTR_STRING = 22;

  /**
   * The number of structural features of the '<em>Unsettable Attributes</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSETTABLE_ATTRIBUTES_FEATURE_COUNT = 23;

  /**
   * The meta object id for the '{@link org.eclipse.emf.cdo.tests.model6.MyEnum <em>My Enum</em>}' enum.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @see org.eclipse.emf.cdo.tests.model6.MyEnum
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getMyEnum()
   * @generated
   */
  int MY_ENUM = 24;

  /**
   * The meta object id for the '<em>My String</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.cdo.tests.model6.legacy.impl.Model6PackageImpl#getMyString()
   * @generated
   */
  int MY_STRING = 25;

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.Root <em>Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Root</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.Root
   * @generated
   */
  EClass getRoot();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.cdo.tests.model6.Root#getListA <em>List A</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>List A</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.Root#getListA()
   * @see #getRoot()
   * @generated
   */
  EReference getRoot_ListA();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.cdo.tests.model6.Root#getListB <em>List B</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>List B</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.Root#getListB()
   * @see #getRoot()
   * @generated
   */
  EReference getRoot_ListB();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.cdo.tests.model6.Root#getListC <em>List C</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>List C</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.Root#getListC()
   * @see #getRoot()
   * @generated
   */
  EReference getRoot_ListC();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.cdo.tests.model6.Root#getListD <em>List D</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>List D</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.Root#getListD()
   * @see #getRoot()
   * @generated
   */
  EReference getRoot_ListD();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.BaseObject <em>Base Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Base Object</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.BaseObject
   * @generated
   */
  EClass getBaseObject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.BaseObject#getAttributeOptional <em>Attribute Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attribute Optional</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.BaseObject#getAttributeOptional()
   * @see #getBaseObject()
   * @generated
   */
  EAttribute getBaseObject_AttributeOptional();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.BaseObject#getAttributeRequired <em>Attribute Required</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attribute Required</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.BaseObject#getAttributeRequired()
   * @see #getBaseObject()
   * @generated
   */
  EAttribute getBaseObject_AttributeRequired();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.cdo.tests.model6.BaseObject#getAttributeList <em>Attribute List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Attribute List</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.BaseObject#getAttributeList()
   * @see #getBaseObject()
   * @generated
   */
  EAttribute getBaseObject_AttributeList();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.ReferenceObject <em>Reference Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Reference Object</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.ReferenceObject
   * @generated
   */
  EClass getReferenceObject();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.cdo.tests.model6.ReferenceObject#getReferenceOptional <em>Reference Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reference Optional</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.ReferenceObject#getReferenceOptional()
   * @see #getReferenceObject()
   * @generated
   */
  EReference getReferenceObject_ReferenceOptional();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.cdo.tests.model6.ReferenceObject#getReferenceList <em>Reference List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Reference List</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.ReferenceObject#getReferenceList()
   * @see #getReferenceObject()
   * @generated
   */
  EReference getReferenceObject_ReferenceList();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.ContainmentObject <em>Containment Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Containment Object</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.ContainmentObject
   * @generated
   */
  EClass getContainmentObject();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.cdo.tests.model6.ContainmentObject#getContainmentOptional <em>Containment Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Containment Optional</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.ContainmentObject#getContainmentOptional()
   * @see #getContainmentObject()
   * @generated
   */
  EReference getContainmentObject_ContainmentOptional();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.cdo.tests.model6.ContainmentObject#getContainmentList <em>Containment List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Containment List</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.ContainmentObject#getContainmentList()
   * @see #getContainmentObject()
   * @generated
   */
  EReference getContainmentObject_ContainmentList();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.UnorderedList <em>Unordered List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unordered List</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnorderedList
   * @generated
   */
  EClass getUnorderedList();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.cdo.tests.model6.UnorderedList#getContained <em>Contained</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Contained</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnorderedList#getContained()
   * @see #getUnorderedList()
   * @generated
   */
  EReference getUnorderedList_Contained();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.cdo.tests.model6.UnorderedList#getReferenced <em>Referenced</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Referenced</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnorderedList#getReferenced()
   * @see #getUnorderedList()
   * @generated
   */
  EReference getUnorderedList_Referenced();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.PropertiesMap <em>Properties Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Properties Map</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.PropertiesMap
   * @generated
   */
  EClass getPropertiesMap();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.PropertiesMap#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.PropertiesMap#getLabel()
   * @see #getPropertiesMap()
   * @generated
   */
  EAttribute getPropertiesMap_Label();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.cdo.tests.model6.PropertiesMap#getPersistentMap <em>Persistent Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Persistent Map</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.PropertiesMap#getPersistentMap()
   * @see #getPropertiesMap()
   * @generated
   */
  EReference getPropertiesMap_PersistentMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.cdo.tests.model6.PropertiesMap#getTransientMap <em>Transient Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Transient Map</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.PropertiesMap#getTransientMap()
   * @see #getPropertiesMap()
   * @generated
   */
  EReference getPropertiesMap_TransientMap();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Properties Map Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Properties Map Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyDataType="org.eclipse.emf.ecore.EString"
   *        valueType="org.eclipse.emf.cdo.tests.model6.PropertiesMapEntryValue" valueContainment="true" valueResolveProxies="true"
   * @generated
   */
  EClass getPropertiesMapEntry();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getPropertiesMapEntry()
   * @generated
   */
  EAttribute getPropertiesMapEntry_Key();

  /**
   * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getPropertiesMapEntry()
   * @generated
   */
  EReference getPropertiesMapEntry_Value();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.PropertiesMapEntryValue <em>Properties Map Entry Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Properties Map Entry Value</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.PropertiesMapEntryValue
   * @generated
   */
  EClass getPropertiesMapEntryValue();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.PropertiesMapEntryValue#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.PropertiesMapEntryValue#getLabel()
   * @see #getPropertiesMapEntryValue()
   * @generated
   */
  EAttribute getPropertiesMapEntryValue_Label();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.A <em>A</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>A</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.A
   * @generated
   */
  EClass getA();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.cdo.tests.model6.A#getOwnedDs <em>Owned Ds</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Ds</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.A#getOwnedDs()
   * @see #getA()
   * @generated
   */
  EReference getA_OwnedDs();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.cdo.tests.model6.A#getOwnedBs <em>Owned Bs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Bs</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.A#getOwnedBs()
   * @see #getA()
   * @generated
   */
  EReference getA_OwnedBs();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.B <em>B</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>B</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.B
   * @generated
   */
  EClass getB();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.cdo.tests.model6.B#getOwnedC <em>Owned C</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned C</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.B#getOwnedC()
   * @see #getB()
   * @generated
   */
  EReference getB_OwnedC();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.C <em>C</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>C</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.C
   * @generated
   */
  EClass getC();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.D <em>D</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>D</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.D
   * @generated
   */
  EClass getD();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.cdo.tests.model6.D#getData <em>Data</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Data</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.D#getData()
   * @see #getD()
   * @generated
   */
  EReference getD_Data();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.E <em>E</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>E</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.E
   * @generated
   */
  EClass getE();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.cdo.tests.model6.E#getOwnedAs <em>Owned As</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned As</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.E#getOwnedAs()
   * @see #getE()
   * @generated
   */
  EReference getE_OwnedAs();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.F <em>F</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>F</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.F
   * @generated
   */
  EClass getF();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.cdo.tests.model6.F#getOwnedEs <em>Owned Es</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Es</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.F#getOwnedEs()
   * @see #getF()
   * @generated
   */
  EReference getF_OwnedEs();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.G <em>G</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>G</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.G
   * @generated
   */
  EClass getG();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.G#getDummy <em>Dummy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Dummy</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.G#getDummy()
   * @see #getG()
   * @generated
   */
  EAttribute getG_Dummy();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.cdo.tests.model6.G#getReference <em>Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reference</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.G#getReference()
   * @see #getG()
   * @generated
   */
  EReference getG_Reference();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.cdo.tests.model6.G#getList <em>List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>List</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.G#getList()
   * @see #getG()
   * @generated
   */
  EReference getG_List();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.MyEnumList <em>My Enum List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>My Enum List</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.MyEnumList
   * @generated
   */
  EClass getMyEnumList();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.cdo.tests.model6.MyEnumList#getMyEnum <em>My Enum</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>My Enum</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.MyEnumList#getMyEnum()
   * @see #getMyEnumList()
   * @generated
   */
  EAttribute getMyEnumList_MyEnum();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.MyEnumListUnsettable <em>My Enum List Unsettable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>My Enum List Unsettable</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.MyEnumListUnsettable
   * @generated
   */
  EClass getMyEnumListUnsettable();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.cdo.tests.model6.MyEnumListUnsettable#getMyEnum <em>My Enum</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>My Enum</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.MyEnumListUnsettable#getMyEnum()
   * @see #getMyEnumListUnsettable()
   * @generated
   */
  EAttribute getMyEnumListUnsettable_MyEnum();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.Holder <em>Holder</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Holder</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.Holder
   * @generated
   */
  EClass getHolder();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.cdo.tests.model6.Holder#getHeld <em>Held</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Held</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.Holder#getHeld()
   * @see #getHolder()
   * @generated
   */
  EReference getHolder_Held();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.cdo.tests.model6.Holder#getOwned <em>Owned</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.Holder#getOwned()
   * @see #getHolder()
   * @generated
   */
  EReference getHolder_Owned();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.Thing <em>Thing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Thing</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.Thing
   * @generated
   */
  EClass getThing();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.Holdable <em>Holdable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Holdable</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.Holdable
   * @generated
   */
  EClass getHoldable();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.Holdable#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.Holdable#getName()
   * @see #getHoldable()
   * @generated
   */
  EAttribute getHoldable_Name();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.HasNillableAttribute <em>Has Nillable Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Has Nillable Attribute</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.HasNillableAttribute
   * @generated
   */
  EClass getHasNillableAttribute();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.HasNillableAttribute#getNillable <em>Nillable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Nillable</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.HasNillableAttribute#getNillable()
   * @see #getHasNillableAttribute()
   * @generated
   */
  EAttribute getHasNillableAttribute_Nillable();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.EmptyStringDefault <em>Empty String Default</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Empty String Default</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.EmptyStringDefault
   * @generated
   */
  EClass getEmptyStringDefault();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.EmptyStringDefault#getAttribute <em>Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attribute</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.EmptyStringDefault#getAttribute()
   * @see #getEmptyStringDefault()
   * @generated
   */
  EAttribute getEmptyStringDefault_Attribute();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.EmptyStringDefaultUnsettable <em>Empty String Default Unsettable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Empty String Default Unsettable</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.EmptyStringDefaultUnsettable
   * @generated
   */
  EClass getEmptyStringDefaultUnsettable();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.EmptyStringDefaultUnsettable#getAttribute <em>Attribute</em>}'.
   * <!-- begin-user-doc -->
  	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attribute</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.EmptyStringDefaultUnsettable#getAttribute()
   * @see #getEmptyStringDefaultUnsettable()
   * @generated
   */
  EAttribute getEmptyStringDefaultUnsettable_Attribute();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes <em>Unsettable Attributes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unsettable Attributes</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes
   * @generated
   */
  EClass getUnsettableAttributes();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrBigDecimal <em>Attr Big Decimal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Big Decimal</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrBigDecimal()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrBigDecimal();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrBigInteger <em>Attr Big Integer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Big Integer</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrBigInteger()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrBigInteger();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#isAttrBoolean <em>Attr Boolean</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Boolean</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#isAttrBoolean()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrBoolean();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrBooleanObject <em>Attr Boolean Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Boolean Object</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrBooleanObject()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrBooleanObject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrByte <em>Attr Byte</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Byte</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrByte()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrByte();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrByteArray <em>Attr Byte Array</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Byte Array</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrByteArray()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrByteArray();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrByteObject <em>Attr Byte Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Byte Object</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrByteObject()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrByteObject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrChar <em>Attr Char</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Char</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrChar()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrChar();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrCharacterObject <em>Attr Character Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Character Object</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrCharacterObject()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrCharacterObject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrDate <em>Attr Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Date</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrDate()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrDate();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrDouble <em>Attr Double</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Double</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrDouble()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrDouble();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrDoubleObject <em>Attr Double Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Double Object</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrDoubleObject()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrDoubleObject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrFloat <em>Attr Float</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Float</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrFloat()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrFloat();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrFloatObject <em>Attr Float Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Float Object</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrFloatObject()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrFloatObject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrInt <em>Attr Int</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Int</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrInt()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrInt();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrIntegerObject <em>Attr Integer Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Integer Object</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrIntegerObject()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrIntegerObject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrJavaClass <em>Attr Java Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Java Class</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrJavaClass()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrJavaClass();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrJavaObject <em>Attr Java Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Java Object</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrJavaObject()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrJavaObject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrLong <em>Attr Long</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Long</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrLong()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrLong();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrLongObject <em>Attr Long Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Long Object</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrLongObject()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrLongObject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrShort <em>Attr Short</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Short</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrShort()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrShort();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrShortObject <em>Attr Short Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr Short Object</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrShortObject()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrShortObject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrString <em>Attr String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Attr String</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.UnsettableAttributes#getAttrString()
   * @see #getUnsettableAttributes()
   * @generated
   */
  EAttribute getUnsettableAttributes_AttrString();

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.cdo.tests.model6.MyEnum <em>My Enum</em>}'.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @return the meta object for enum '<em>My Enum</em>'.
   * @see org.eclipse.emf.cdo.tests.model6.MyEnum
   * @generated
   */
  EEnum getMyEnum();

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>My String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My String</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   * @generated
   */
  EDataType getMyString();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  Model6Factory getModel6Factory();

} // Model6Package
