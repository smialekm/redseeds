package eu.redseeds.sc.editor.rsl.editors;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;


public class UseCaseEditorInput implements IEditorInput {


	private UseCaseDTO useCase;
	
	public UseCaseEditorInput() {
		super();
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
		if (useCase != null){
			if (useCase.getName() != null) {
				return useCase.getName();
			} 
			return "Default Use Case";
		}
		return "Default Use Case";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		if (useCase != null){
			if (useCase.getName() != null) {
				return useCase.getName();
			} 
			return "Default Use Case";
		}
		return "Default Use Case";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	public UseCaseDTO getUseCaseDTO() {
		return useCase;
	}

	public void setUseCaseDTO(UseCaseDTO useCase) {
		this.useCase = useCase;
	}

	@Override
	public boolean equals(Object input){
		if (! (input instanceof UseCaseEditorInput)){
			return false;
		}
		if (null==this.useCase && null==((UseCaseEditorInput)input).getUseCaseDTO() || null!=this.useCase && this.useCase.equals(((UseCaseEditorInput)input).getUseCaseDTO())){
			return true;
		}
		return false;
	}
	
}
