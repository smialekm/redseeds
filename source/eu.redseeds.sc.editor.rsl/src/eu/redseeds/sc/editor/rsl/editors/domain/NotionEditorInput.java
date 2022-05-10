package eu.redseeds.sc.editor.rsl.editors.domain;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;


public class NotionEditorInput implements IEditorInput {
	

	private NotionDTO notionDTO;
		
	public NotionEditorInput() {
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
		if (notionDTO != null){
			if (notionDTO.getName() != null) {
				return notionDTO.getName();
			} 
		}
		return "Default Notion";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		if (notionDTO != null){
			if (notionDTO.getName() != null) {
				return notionDTO.getName();
			} 
		}
		return "Default Notion";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	public NotionDTO getNotionDTO() {
		return notionDTO;
	}

	public void setNotionDTO(NotionDTO notionDTO) {
		this.notionDTO = notionDTO;
	}

	@Override
	public boolean equals(Object input){
		if (! (input instanceof NotionEditorInput)){
			return false;
		}
		if (this.notionDTO.equals(((NotionEditorInput)input).getNotionDTO())){
			return true;
		}
		return false;
	}
	
}