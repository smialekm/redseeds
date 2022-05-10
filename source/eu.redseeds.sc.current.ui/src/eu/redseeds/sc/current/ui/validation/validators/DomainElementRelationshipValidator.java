package eu.redseeds.sc.current.ui.validation.validators;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;

public class DomainElementRelationshipValidator implements IValidate {

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof DomainElementRelationshipDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		DomainElementRelationshipDTO der = (DomainElementRelationshipDTO) element;
		List<ValidationResult> list = new ArrayList<ValidationResult>();
		ValidationResult vr = null;
		if(der.getSource() instanceof NotionDTO && der.getTarget() instanceof NotionDTO && !MNotion.canExistRelation((NotionDTO) der.getSource(), (NotionDTO) der.getTarget(), der.isDirected(), false)){
			vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_DOMAIN_ELEMENT_RELATIONSHIP);
			vr.setMessage(MSG_DOMAIN_ELEMENT_RELATIONSHIP);
			vr.setSclElement(der);
			vr.setSeverity(IValidate.SEVERITY_WARNING);
			list.add(vr);
		}
		return list.toArray(new ValidationResult[0]);
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		return validate(element);
	}

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLabel() {
		return "Validating Domain Element Relationship";
	}

}
