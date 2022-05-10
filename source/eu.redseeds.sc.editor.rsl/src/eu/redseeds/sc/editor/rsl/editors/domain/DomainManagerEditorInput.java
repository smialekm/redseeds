package eu.redseeds.sc.editor.rsl.editors.domain;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class DomainManagerEditorInput implements IEditorInput {
	
	private TreePath path;
	private SoftwareCaseDTO softwareCase;
	private List<Object> preSelectedObjectsList = new ArrayList<Object>();
	
	
	public DomainManagerEditorInput() {
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
		return "DomainManagerEditorInput Input";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		return "DomainManagerEditorInput";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	public TreePath getPath() {
		return path;
	}

	public void setPath(TreePath path) {
		this.path = path;
	}

	public SoftwareCaseDTO getSoftwareCase() {
		return softwareCase;
	}

	public void setSoftwareCase(SoftwareCaseDTO softwareCase) {
		this.softwareCase = softwareCase;
	}

	public List<Object> getPreSelectedObjectsList() {
		return preSelectedObjectsList;
	}

	public void setPreSelectedObjectsList(List<Object> preSelectedObjectsList) {
		this.preSelectedObjectsList = preSelectedObjectsList;
	}
}
