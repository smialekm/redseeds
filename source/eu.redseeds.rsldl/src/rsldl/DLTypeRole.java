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
 * A representation of the literals of the enumeration '<em><b>DL Type Role</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see rsldl.RsldlPackage#getDLTypeRole()
 * @model
 * @generated
 */
public enum DLTypeRole implements Enumerator {
	/**
	 * The '<em><b>Identity</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IDENTITY_VALUE
	 * @generated
	 * @ordered
	 */
	IDENTITY(0, "identity", "Identity"),

	/**
	 * The '<em><b>Persistent role</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PERSISTENT_ROLE_VALUE
	 * @generated
	 * @ordered
	 */
	PERSISTENT_ROLE(1, "persistent_role", "Assigned role"),

	/**
	 * The '<em><b>Temporary role</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEMPORARY_ROLE_VALUE
	 * @generated
	 * @ordered
	 */
	TEMPORARY_ROLE(2, "temporary_role", "Inferred role"), /**
	 * The '<em><b>Template</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEMPLATE_VALUE
	 * @generated
	 * @ordered
	 */
	TEMPLATE(3, "template", "Template");

	/**
	 * The '<em><b>Identity</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Identity</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IDENTITY
	 * @model name="identity" literal="Identity"
	 * @generated
	 * @ordered
	 */
	public static final int IDENTITY_VALUE = 0;

	/**
	 * The '<em><b>Persistent role</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Persistent role</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PERSISTENT_ROLE
	 * @model name="persistent_role" literal="Assigned role"
	 * @generated
	 * @ordered
	 */
	public static final int PERSISTENT_ROLE_VALUE = 1;

	/**
	 * The '<em><b>Temporary role</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Temporary role</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TEMPORARY_ROLE
	 * @model name="temporary_role" literal="Inferred role"
	 * @generated
	 * @ordered
	 */
	public static final int TEMPORARY_ROLE_VALUE = 2;

	/**
	 * The '<em><b>Template</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Template</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TEMPLATE
	 * @model name="template" literal="Template"
	 * @generated
	 * @ordered
	 */
	public static final int TEMPLATE_VALUE = 3;

	/**
	 * An array of all the '<em><b>DL Type Role</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DLTypeRole[] VALUES_ARRAY =
		new DLTypeRole[] {
			IDENTITY,
			PERSISTENT_ROLE,
			TEMPORARY_ROLE,
			TEMPLATE,
		};

	/**
	 * A public read-only list of all the '<em><b>DL Type Role</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<DLTypeRole> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>DL Type Role</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static DLTypeRole get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DLTypeRole result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DL Type Role</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static DLTypeRole getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DLTypeRole result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>DL Type Role</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static DLTypeRole get(int value) {
		switch (value) {
			case IDENTITY_VALUE: return IDENTITY;
			case PERSISTENT_ROLE_VALUE: return PERSISTENT_ROLE;
			case TEMPORARY_ROLE_VALUE: return TEMPORARY_ROLE;
			case TEMPLATE_VALUE: return TEMPLATE;
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
	private DLTypeRole(int value, String name, String literal) {
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
	
} //DLTypeRole
