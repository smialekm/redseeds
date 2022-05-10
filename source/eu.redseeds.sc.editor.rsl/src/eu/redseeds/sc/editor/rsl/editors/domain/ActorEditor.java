package eu.redseeds.sc.editor.rsl.editors.domain;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.ActorEditorControl;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.DomainElementEditorControlFactory;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;

public class ActorEditor extends EditorPart {

	private BusinessLayerFacade facade;
	private SCProject scProject;
	private ActorDTO actor;
	private ActorEditorControl actorControl;
	private ScrolledComposite sc;
	
	/**
	 * Used for updating the editor
	 */
	private TableViewer viewer = null;

	private boolean dirty = false;

	public static final String EDITOR_ID = "eu.redseeds.sc.editor.rsl.editors.ActorEditor";

	public ActorEditor() {
		super();
	}

	public void dispose() {
		super.dispose();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		monitor.beginTask("Saving Actor " + actor.getName(), 1);
		if(DomainElementEditorControlFactory.saveActor(actorControl, actor)) {
			scProject.save();
			dirty = false;
			SCProjectHelper.refreshSCNavigator();
			firePropertyChange(PROP_DIRTY);
			setTitleToolTip(actor.getName());
			setPartName(actor.getName());
		}
		else {
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Provided actor name is not unique");
			mb.setText("Name");
			mb.open();
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
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		sc = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		actorControl = DomainElementEditorControlFactory.getActorEditorControl(
				this, sc);
		actorControl.setSize(
				DomainElementEditorControlFactory.ACTOR_CONTROL_WIDTH, 
				DomainElementEditorControlFactory.ACTOR_CONTROL_HEIGHT);
		sc.setContent(actorControl);
	}

	@Override
	public void setFocus() {
		if(viewer != null) {
			viewer.refresh();
		}
	}

	public BusinessLayerFacade getFacade() {
		return facade;
	}

	public void setFacade(BusinessLayerFacade facade) {
		this.facade = facade;
	}

	public ActorDTO getActor() {
		return actor;
	}

	public void setActor(ActorDTO actor) {
		this.actor = actor;
		if (!isDirty()) {
			if(viewer == null) {
				viewer = DomainElementEditorControlFactory.fillActorEditorControlWithData(
					this.actorControl, actor);
			}
			else {
				DomainElementEditorControlFactory.updateActorEditorControlWithData(
						this.actorControl, actor, viewer);
			}
		}
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
