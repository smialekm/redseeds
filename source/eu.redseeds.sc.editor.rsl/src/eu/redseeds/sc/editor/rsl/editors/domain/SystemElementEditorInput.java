package eu.redseeds.sc.editor.rsl.editors.domain;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;

public class SystemElementEditorInput implements IEditorInput {
	
	private SystemElementDTO sysElemDTO;
	
	public SystemElementEditorInput() {
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
		if (sysElemDTO != null){
			if (sysElemDTO.getName() != null) {
				return sysElemDTO.getName();
			} 
		}
		return "Default System Element";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		if (sysElemDTO != null){
			if (sysElemDTO.getName() != null) {
				return sysElemDTO.getName();
			} 
		}
		return "Default System Element";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object input){
		if (! (input instanceof SystemElementEditorInput)){
			return false;
		}
		if (this.sysElemDTO.equals(((SystemElementEditorInput)input).getSysElemDTO())){
			return true;
		}
		return false;
	}

	public SystemElementDTO getSysElemDTO() {
		return sysElemDTO;
	}

	public void setSysElemDTO(SystemElementDTO sysElemDTO) {
		this.sysElemDTO = sysElemDTO;
	}
	
}


