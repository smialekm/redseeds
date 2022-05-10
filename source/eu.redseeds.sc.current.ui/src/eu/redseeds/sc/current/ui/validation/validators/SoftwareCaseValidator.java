package eu.redseeds.sc.current.ui.validation.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationAdapter;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;
import eu.redseeds.scl.sclkernel.SCLRelationship;

public class SoftwareCaseValidator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getLabel() {
		return "Validating Software Case";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof SoftwareCaseDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		return new ValidationResult[0];
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		List<ValidationResult> results = new ArrayList<ValidationResult>();
		results.addAll(Arrays.asList(validate(element)));
		if (element instanceof SoftwareCaseDTO) {
			SoftwareCaseDTO sc = (SoftwareCaseDTO)element;
			//models
			IValidate validator = ValidationAdapter.getValidator(sc.getRequirementsSpecificationDTO());
			results.addAll(Arrays.asList(validator.validateRecursively(sc.getRequirementsSpecificationDTO())));
			validator = ValidationAdapter.getValidator(sc.getArchitecturalModelDTO());
			results.addAll(Arrays.asList(validator.validateRecursively(sc.getArchitecturalModelDTO())));
			validator = ValidationAdapter.getValidator(sc.getDetailedDesignModelDTO());
			results.addAll(Arrays.asList(validator.validateRecursively(sc.getDetailedDesignModelDTO())));
			//terminology
			BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(sc).getBusinessLayerFacade();
			List<TermDTO> terms = facade.getAllTerms();
			if(terms != null){
				validator = ValidationAdapter.getValidator(terms.get(0));
				for(TermDTO term : terms) {
					results.addAll(Arrays.asList(validator.validateRecursively(term)));
				}
			}
			List<SCLRelationship> relationships = facade.getValidableRelationship();
			if(relationships != null)
				for(SCLRelationship relation : relationships) {
					validator = ValidationAdapter.getValidator(relation);
					results.addAll(Arrays.asList(validator.validateRecursively(relation)));
				}
		}
		return (ValidationResult[])results.toArray(new ValidationResult[results.size()]);
	}

}
