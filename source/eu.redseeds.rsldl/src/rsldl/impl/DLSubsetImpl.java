/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl.impl;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import rsldl.DLNotion;
import rsldl.DLRelationship;
import rsldl.DLSubset;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DL Subset</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class DLSubsetImpl extends EObjectImpl implements DLSubset {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DLSubsetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RsldlPackage.Literals.DL_SUBSET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public abstract EList<DLNotion> getDomainNotions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public abstract EList<DLRelationship> getDomainRelationships();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DLNotion getNotion(String name) {
		for (DLNotion n:getDomainNotions())
            if (name.equals(n.getName()))
                return n;
        return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<DLNotion> getNotions(EList<String> names) {
		EList<DLNotion> l = new BasicEList<DLNotion>();
        for (DLNotion n:getDomainNotions())
        	if(names.contains(n.getName()))
        		l.add(n);
        return l;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DLRelationship getRelationshipByAbbreviation(String abbreviation) {
		for (DLRelationship r:getDomainRelationships())
            if (abbreviation.equals(r.getAbbreviation()))
                return r;
        return null;
	}

} //DLSubsetImpl
