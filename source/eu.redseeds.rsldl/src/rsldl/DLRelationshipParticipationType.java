/**
 */
package rsldl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>DL Relationship Participation Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see rsldl.RsldlPackage#getDLRelationshipParticipationType()
 * @model
 * @generated
 */
public enum DLRelationshipParticipationType implements Enumerator {
	/**
	 * The '<em><b>Standard</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STANDARD_VALUE
	 * @generated
	 * @ordered
	 */
	STANDARD(0, "standard", "standard"),

	/**
	 * The '<em><b>Auxiliary parent</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUXILIARY_PARENT_VALUE
	 * @generated
	 * @ordered
	 */
	AUXILIARY_PARENT(1, "auxiliary_parent", "auxiliary parent"),

	/**
	 * The '<em><b>Auxiliary role</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUXILIARY_ROLE_VALUE
	 * @generated
	 * @ordered
	 */
	AUXILIARY_ROLE(2, "auxiliary_role", "auxiliary role");

	/**
	 * The '<em><b>Standard</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Standard</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STANDARD
	 * @model name="standard"
	 * @generated
	 * @ordered
	 */
	public static final int STANDARD_VALUE = 0;

	/**
	 * The '<em><b>Auxiliary parent</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Auxiliary parent</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AUXILIARY_PARENT
	 * @model name="auxiliary_parent" literal="auxiliary parent"
	 * @generated
	 * @ordered
	 */
	public static final int AUXILIARY_PARENT_VALUE = 1;

	/**
	 * The '<em><b>Auxiliary role</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Auxiliary role</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AUXILIARY_ROLE
	 * @model name="auxiliary_role" literal="auxiliary role"
	 * @generated
	 * @ordered
	 */
	public static final int AUXILIARY_ROLE_VALUE = 2;

	/**
	 * An array of all the '<em><b>DL Relationship Participation Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DLRelationshipParticipationType[] VALUES_ARRAY =
		new DLRelationshipParticipationType[] {
			STANDARD,
			AUXILIARY_PARENT,
			AUXILIARY_ROLE,
		};

	/**
	 * A public read-only list of all the '<em><b>DL Relationship Participation Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<DLRelationshipParticipationType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>DL Relationship Participation Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static DLRelationshipParticipationType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DLRelationshipParticipationType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DL Relationship Participation Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static DLRelationshipParticipationType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DLRelationshipParticipationType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DL Relationship Participation Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static DLRelationshipParticipationType get(int value) {
		switch (value) {
			case STANDARD_VALUE: return STANDARD;
			case AUXILIARY_PARENT_VALUE: return AUXILIARY_PARENT;
			case AUXILIARY_ROLE_VALUE: return AUXILIARY_ROLE;
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
	private DLRelationshipParticipationType(int value, String name, String literal) {
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
	
} //DLRelationshipParticipationType
