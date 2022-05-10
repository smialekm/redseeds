package eu.redseeds.sc.editor.rsl.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;

public class RequirementEditorInput implements IEditorInput{

	
	private RequirementDTO req;
	
	public RequirementEditorInput(){
		
	}
	
	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		if (req != null){
			if (req.getName() != null) {
				return req.getName();
			} 
			return "Default Requirement";
		}
		return "Default Requirement";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		if (req != null){
			if (req.getName() != null) {
				return req.getName();
			} 
			return "Default Requirement";
		}
		return "Default Requirement";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean equals(Object input){
		if (! (input instanceof RequirementEditorInput)){
			return false;
		}
		if (this.req.equals(((RequirementEditorInput)input).getRequirementDTO())){
			return true;
		}
		return false;
	}

	public RequirementDTO getRequirementDTO() {
		return req;
	}

	public void setRequirementDTO(RequirementDTO req) {
		this.req = req;
	}

}
