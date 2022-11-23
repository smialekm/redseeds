package eu.redseeds.sc.current.ui.validation.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationAdapter;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementRelationship;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;
import eu.remics.recovery.model.preferences.MConfiguration;

public class NotionValidator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLabel() {
		return "Validating notion";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof NotionDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		NotionDTO not = (NotionDTO) element;
		List<ValidationResult> list = new ArrayList<ValidationResult>();
		ValidationResult vr = null;
		try {
			if(!validateNotionDuplicate(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_DUPLICATE);
				vr.setMessage(MSG_NOTION_DUPLICATE);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_ERROR);
				list.add(vr);
			}
			if(!validateList(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_LIST_WITHOUT_ELEMENTS);
				vr.setMessage(MSG_NOTION_LIST_WITHOUT_ELEMENTS);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_ERROR);
				list.add(vr);
			}
			if(!validateSimpleView(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_SIMPLE_VIEW_WITHOUT_ELEMENTS);
				vr.setMessage(MSG_NOTION_SIMPLE_VIEW_WITHOUT_ELEMENTS);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_ERROR);
				list.add(vr);
			}
			if(!validateAttribute(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_ATTRIBUTE_WITHOUT_PARENT);
				vr.setMessage(MSG_NOTION_ATTRIBUTE_WITHOUT_PARENT);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_WARNING);
				list.add(vr);
			}
			if(!validateAttributeVisibility(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_INVISIBLE_NOTION_ATTRIBUTE);
				vr.setMessage(MSG_INVISIBLE_NOTION_ATTRIBUTE);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_INFO);
				list.add(vr);
			}
			if(!validateAttributeAttachment(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_UNATTACH_NOTION_ATTRIBUTE);
				vr.setMessage(MSG_UNATTACH_NOTION_ATTRIBUTE);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_INFO);
				list.add(vr);
			}
			if(!validateGeneralizations(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_MULTIPLE_GENERALIZATION);
				vr.setMessage(MSG_NOTION_MULTIPLE_GENERALIZATION);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_INFO);
				list.add(vr);
			}
			if(not.getType().isEmpty() && !MNotion.canBeConcept(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_CONCEPT);
				vr.setMessage(MSG_NOTION_CONCEPT);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_WARNING);
				list.add(vr);
			}
			if(NotionTypesEnum.Screen.tag().equals(not.getType()) && !MNotion.canBeScreen(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_SCREEN);
				vr.setMessage(MSG_NOTION_SCREEN);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_WARNING);
				list.add(vr);
			}
			if(NotionTypesEnum.Message.tag().equals(not.getType()) && !MNotion.canBeMessage(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_MESSAGE);
				vr.setMessage(MSG_NOTION_MESSAGE);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_WARNING);
				list.add(vr);
			}
			if(NotionTypesEnum.Confirmation_Dialog.tag().equals(not.getType()) && !MNotion.canBeConfirmationDialog(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_CONFIRMATION_DIALOG);
				vr.setMessage(MSG_NOTION_CONFIRMATION_DIALOG);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_WARNING);
				list.add(vr);
			}
			if(NotionTypesEnum.Trigger.tag().equals(not.getType()) && !MNotion.canBeTrigger(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_TRIGGER);
				vr.setMessage(MSG_NOTION_TRIGGER);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_WARNING);
				list.add(vr);
			}
			if(NotionTypesEnum.Attribute.tag().equals(not.getType()) && !MNotion.canBeAttribute(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_ATTRIBUTE);
				vr.setMessage(MSG_NOTION_ATTRIBUTE);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_WARNING);
				list.add(vr);
			}
			if(NotionTypesEnum.Option.tag().equals(not.getType()) && !MNotion.canBeOption(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_OPTION);
				vr.setMessage(MSG_NOTION_OPTION);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_WARNING);
				list.add(vr);
			}
			if(NotionTypesEnum.List_View.tag().equals(not.getType()) && !MNotion.canBeListView(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_LIST_VIEW);
				vr.setMessage(MSG_NOTION_LIST_VIEW);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_WARNING);
				list.add(vr);
			}
			if(NotionTypesEnum.Simple_View.tag().equals(not.getType()) && !MNotion.canBeSimpleView(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_SIMPLE_VIEW);
				vr.setMessage(MSG_NOTION_SIMPLE_VIEW);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_WARNING);
				list.add(vr);
			}
			if(NotionTypesEnum.Tree_View.tag().equals(not.getType()) && !MNotion.canBeTreeView(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_TREE_VIEW);
				vr.setMessage(MSG_NOTION_TREE_VIEW);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_WARNING);
				list.add(vr);
			}
			if(Arrays.asList(NotionTypesEnum.viewTags()).contains(not.getType()) && !validateMainConcept(not)){
				vr = new ValidationResult();
				vr.setProblemID(IValidate.ID_NOTION_DATA_VIEW_WITHOUT_MAIN_CONCEPT);
				vr.setMessage(MSG_NOTION_DATA_VIEW_WITHOUT_MAIN_CONCEPT);
				vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
				vr.setSclElement(not);
				vr.setSeverity(IValidate.SEVERITY_WARNING);
				list.add(vr);
			}
			for (NotionDTO par:not.getNotionAttributeParents())
				if (!MNotion.canExistAddedAtribute(not, par)){
					vr = new ValidationResult();
					vr.setProblemID(IValidate.ID_ATTRIBUTE);
					vr.setMessage(MSG_ATTRIBUTE);
					vr.setPath(not.getSpecificationPath()+"\\"+not.getName());
					vr.setSclElement(not);
					vr.setSeverity(IValidate.SEVERITY_WARNING);
					list.add(vr);
				}
		} catch (Exception e) {
			vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_NOTION_EXCEPTION);
			vr.setMessage(MSG_NOTION_EXCEPTION);
			vr.setSclElement(element);
			list.add(vr);
		}
		return list.toArray(new ValidationResult[0]);
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		List<ValidationResult> results = new ArrayList<ValidationResult>();
		results.addAll(Arrays.asList(validate(element)));
		if ((element instanceof NotionDTO)) {
			NotionDTO notion = (NotionDTO)element;
			List<DomainStatementDTO> statements = notion.getDomainStatementDTOList();
			if(statements.size() > 0) {
				IValidate validatorDS = ValidationAdapter.getValidator(notion.getDomainStatementDTOList().get(0));
				for(DomainStatementDTO statement : statements) {
					results.addAll(Arrays.asList(validatorDS.validateRecursively(statement)));
				}	
			}
		}
		return (ValidationResult[])results.toArray(new ValidationResult[results.size()]);
	}
	
	private boolean validateNotionDuplicate(NotionDTO not) {
		if(SCProjectContainer.instance().getSCProject(not).getBusinessLayerFacade().findNotions(not.getNamePhrase().getNoun()).size() > 1) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean validateList(NotionDTO not){
		if (!NotionTypesEnum.List_View.tag().equals(not.getType())) return true;
		for (DomainElementRelationship der:SCProjectContainer.instance().getSCProject(not).getBusinessLayerFacade().getDomainElementRelationshipVertices()) if (der.getSourceList().contains(not) && !der.getTargetList().isEmpty() && null!=der.getTargetList().get(0) && der.getTargetList().get(0) instanceof NotionDTO && NotionTypesEnum.Attribute.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType())){
			return true;
		}
		if (MConfiguration.isAllowAttributesForDataViews() && !not.getNotionDTOAttributeList().isEmpty()) return true;
		if (!MConfiguration.isCheckRelations())
			for (DomainElementRelationship der:SCProjectContainer.instance().getSCProject(not).getBusinessLayerFacade().getDomainElementRelationshipVertices()) if (der.getSourceList().contains(not) && !der.getTargetList().isEmpty() && null!=der.getTargetList().get(0) && der.getTargetList().get(0) instanceof NotionDTO && (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) || ((NotionDTO) der.getTargetList().get(0)).getType().isEmpty())){
				return true;
			}
		return false;
	}
	
	private boolean validateSimpleView(NotionDTO not){
		if (!NotionTypesEnum.Simple_View.tag().equals(not.getType())) return true;
		for (DomainElementRelationship der:SCProjectContainer.instance().getSCProject(not).getBusinessLayerFacade().getDomainElementRelationshipVertices()) if (der.getSourceList().contains(not) && !der.getTargetList().isEmpty() && null!=der.getTargetList().get(0) && der.getTargetList().get(0) instanceof NotionDTO && NotionTypesEnum.Attribute.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType())){
			return true;
		}
		if (MConfiguration.isAllowAttributesForDataViews() && !not.getNotionDTOAttributeList().isEmpty()) return true;
		return false;
	}
	
	private boolean validateAttribute(NotionDTO not){
		if (!NotionTypesEnum.Attribute.tag().equals(not.getType())) return true;
		if (MNotion.isAttribute(not)) return true;
		return false;
	}
	
	private boolean validateAttributeVisibility(NotionDTO not){
		if (!NotionTypesEnum.Attribute.tag().equals(not.getType())) return true;
		if (MNotion.isVisibleAttribute(not)) return true;
		return false;
	}
	
	private boolean validateAttributeAttachment(NotionDTO not){
		if (!NotionTypesEnum.Attribute.tag().equals(not.getType())) return true;
		if (MNotion.isAttachedAttribute(not)) return true;
		return false;
	}
	
	private boolean validateGeneralizations(NotionDTO not){
		int i = 0;
		for (NotionSpecialisationDTO ns:not.getNotionSpecialisationDTOList()){
			if (((Notion) ns.getSpecialisedNotion()).getId()==((Notion) not).getId()) i++;
			if (i>=2) return false;
		}
		return true;
	}
	
	private boolean validateMainConcept(NotionDTO not){
		for (DomainElementRelationshipDTO r:not.getDomainElementRelationshipDTOList())
			if (r.getSource().equals(not) && r.isDirected() && r.getTarget() instanceof NotionDTO && ((NotionDTO) r.getTarget()).getType().isEmpty())
				return true;
		return false;
	}
	
}
