package eu.redseeds.sc.editor.rsl.editors.domain;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;

public class ActorEditorInput implements IEditorInput{

	private ActorDTO actorDTO;
	
	public ActorEditorInput() {
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
		if (actorDTO != null){
			if (actorDTO.getName() != null) {
				return actorDTO.getName();
			} 
		}
		return "Default Actor";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		if (actorDTO != null){
			if (actorDTO.getName() != null) {
				return actorDTO.getName();
			} 
		}
		return "Default Actor";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	public ActorDTO getActorDTO() {
		return actorDTO;
	}

	public void setActorDTO(ActorDTO actorDTO) {
		this.actorDTO = actorDTO;
	}

	@Override
	public boolean equals(Object input){
		if (! (input instanceof ActorEditorInput)){
			return false;
		}
		if (this.actorDTO.equals(((ActorEditorInput)input).getActorDTO())){
			return true;
		}
		return false;
	}
	
}
