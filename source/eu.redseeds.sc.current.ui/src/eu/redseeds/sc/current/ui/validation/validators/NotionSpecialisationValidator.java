package eu.redseeds.sc.current.ui.validation.validators;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;

public class NotionSpecialisationValidator implements IValidate {

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof NotionSpecialisationDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		NotionSpecialisationDTO ns = (NotionSpecialisationDTO) element;
		List<ValidationResult> list = new ArrayList<ValidationResult>();
		ValidationResult vr = null;
		try {
			if(!MNotion.canExistGeneralization(ns.getSpecialisedNotion(), ns.getGeneralNotion())){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_SPECIALISATION);
				vr.setMessage(MSG_NOTION_SPECIALISATION);
				vr.setSclElement(ns);
				vr.setSeverity(IValidate.SEVERITY_WARNING);
				list.add(vr);
			}
		} catch (Exception e) {
			vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_NOTION_SPECIALIZATION_EXCEPTION);
			vr.setMessage(MSG_NOTION_SPECIALIZATION_EXCEPTION);
			vr.setSclElement(element);
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
		return "Validating Notion Specialisation";
	}

}
