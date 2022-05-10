/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>DL Condition Pattern Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see rsldl.RsldlPackage#getDLConditionPatternType()
 * @model
 * @generated
 */
public enum DLConditionPatternType implements Enumerator {
	/**
	 * The '<em><b>Empty</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EMPTY_VALUE
	 * @generated
	 * @ordered
	 */
	EMPTY(0, "empty", "empty"),

	/**
	 * The '<em><b>Simple</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SIMPLE_VALUE
	 * @generated
	 * @ordered
	 */
	SIMPLE(1, "simple", "simple"),

	/**
	 * The '<em><b>Universal quantification</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIVERSAL_QUANTIFICATION_VALUE
	 * @generated
	 * @ordered
	 */
	UNIVERSAL_QUANTIFICATION(2, "universal_quantification", "universal quantification"),

	/**
	 * The '<em><b>Existential quantification</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXISTENTIAL_QUANTIFICATION_VALUE
	 * @generated
	 * @ordered
	 */
	EXISTENTIAL_QUANTIFICATION(3, "existential_quantification", "existential quantification"), /**
	 * The '<em><b>Membership</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MEMBERSHIP_VALUE
	 * @generated
	 * @ordered
	 */
	MEMBERSHIP(4, "membership", "membership");

	/**
	 * The '<em><b>Empty</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Empty</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EMPTY
	 * @model name="empty"
	 * @generated
	 * @ordered
	 */
	public static final int EMPTY_VALUE = 0;

	/**
	 * The '<em><b>Simple</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Simple</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SIMPLE
	 * @model name="simple"
	 * @generated
	 * @ordered
	 */
	public static final int SIMPLE_VALUE = 1;

	/**
	 * The '<em><b>Universal quantification</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Universal quantification</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNIVERSAL_QUANTIFICATION
	 * @model name="universal_quantification" literal="universal quantification"
	 * @generated
	 * @ordered
	 */
	public static final int UNIVERSAL_QUANTIFICATION_VALUE = 2;

	/**
	 * The '<em><b>Existential quantification</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Existential quantification</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EXISTENTIAL_QUANTIFICATION
	 * @model name="existential_quantification" literal="existential quantification"
	 * @generated
	 * @ordered
	 */
	public static final int EXISTENTIAL_QUANTIFICATION_VALUE = 3;

	/**
	 * The '<em><b>Membership</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Membership</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MEMBERSHIP
	 * @model name="membership"
	 * @generated
	 * @ordered
	 */
	public static final int MEMBERSHIP_VALUE = 4;

	/**
	 * An array of all the '<em><b>DL Condition Pattern Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DLConditionPatternType[] VALUES_ARRAY =
		new DLConditionPatternType[] {
			EMPTY,
			SIMPLE,
			UNIVERSAL_QUANTIFICATION,
			EXISTENTIAL_QUANTIFICATION,
			MEMBERSHIP,
		};

	/**
	 * A public read-only list of all the '<em><b>DL Condition Pattern Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<DLConditionPatternType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>DL Condition Pattern Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static DLConditionPatternType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DLConditionPatternType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DL Condition Pattern Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static DLConditionPatternType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DLConditionPatternType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DL Condition Pattern Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static DLConditionPatternType get(int value) {
		switch (value) {
			case EMPTY_VALUE: return EMPTY;
			case SIMPLE_VALUE: return SIMPLE;
			case UNIVERSAL_QUANTIFICATION_VALUE: return UNIVERSAL_QUANTIFICATION;
			case EXISTENTIAL_QUANTIFICATION_VALUE: return EXISTENTIAL_QUANTIFICATION;
			case MEMBERSHIP_VALUE: return MEMBERSHIP;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DLConditionPatternType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //DLConditionPatternType
