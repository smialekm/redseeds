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
import eu.redseeds.sc.editor.rsl.editors.domain.controls.DomainElementEditorControlFactory;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.SystemElementEditorControl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;

public class SystemElementEditor extends EditorPart {
	
	private BusinessLayerFacade facade;
	private SCProject scProject;
	private SystemElementDTO sysElemDTO;
	private SystemElementEditorControl systemElementControl;
	private ScrolledComposite sc;
	
	/**
	 * Used for updating the editor
	 */
	private TableViewer viewer = null;
	
	private boolean dirty = false;
	
	public static final String EDITOR_ID = "eu.redseeds.sc.editor.rsl.editors.SystemElementEditor";


	public SystemElementEditor() {
		super();
	}

	public void dispose() {
		super.dispose();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		monitor.beginTask("Saving  SystemElement " + sysElemDTO.getName(), 1);
		if(DomainElementEditorControlFactory.saveSystemElement(systemElementControl, sysElemDTO)) {
			scProject.save();
			dirty = false;
			SCProjectHelper.refreshSCNavigator();
			firePropertyChange(PROP_DIRTY);
			setTitleToolTip(sysElemDTO.getName());
			setPartName(sysElemDTO.getName());
		} else {
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Provided system element name is not unique");
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
//		if (!(input instanceof IFileEditorInput))
//		throw new PartInitException("Invalid Input: Must be IFileEditorInput");
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
		systemElementControl = DomainElementEditorControlFactory
			.getSystemElementEditorControl(this, sc);
		systemElementControl.setSize(
				DomainElementEditorControlFactory.SYSTEM_ELEMENT_CONTROL_WIDTH, 
				DomainElementEditorControlFactory.SYSTEM_ELEMENT_CONTROL_HEIGHT);
		sc.setContent(systemElementControl);
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

	public SystemElementDTO getSysElemDTO() {
		return sysElemDTO;
	}

	public void setSysElemDTO(SystemElementDTO sysElemDTO) {
		this.sysElemDTO = sysElemDTO;
		if (!isDirty()) {
			if(viewer == null) {
				viewer = DomainElementEditorControlFactory
					.fillSystemElementEditorControlWithData(this.systemElementControl, sysElemDTO);
			}
			else {
				DomainElementEditorControlFactory
					.updateSystemElementEditorControlWithData(this.systemElementControl, sysElemDTO, viewer);
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
