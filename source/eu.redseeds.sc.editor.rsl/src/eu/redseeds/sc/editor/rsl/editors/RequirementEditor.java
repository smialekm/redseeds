package eu.redseeds.sc.editor.rsl.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;

public class RequirementEditor extends EditorPart {

	public static final String EDITOR_ID = "eu.redseeds.sc.editor.rsl.editors.RequirementEditor";

	private RequirementDTO requirement;
	private BusinessLayerFacade facade;
	private RequirementEditorControl reqEditor;
	private SCProject scProject;

	private boolean dirty = false;

	public RequirementEditor() {
		super();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {

		monitor.beginTask("Saving Requirement " + requirement.getName(), 1);
		if(reqEditor.save()) {
			scProject.save();
			dirty = false;
			SCProjectHelper.refreshSCNavigator();
			firePropertyChange(PROP_DIRTY);
			setTitleToolTip(requirement.getName());
			setPartName(requirement.getName());
		}
		monitor.worked(1);
		monitor.done();
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {

		setSite(site);
		setInput(input);
		setPartName(input.getName());
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	public void createPartControl(Composite parent) {

		reqEditor = new RequirementEditorControl(this, parent);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public RequirementDTO getRequirement() {
		return requirement;
	}

	public void setRequirement(RequirementDTO requirement) {
		this.requirement = requirement;
		if (!isDirty()) {
			reqEditor.fillWithData(requirement);
		}
	}

	public BusinessLayerFacade getFacade() {
		return facade;
	}

	public void setFacade(BusinessLayerFacade facade) {
		this.facade = facade;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
		firePropertyChange(PROP_DIRTY);
	}

	public SCProject getScProject() {
		return scProject;
	}

	public void setScProject(SCProject scProject) {
		this.scProject = scProject;
	}

}
