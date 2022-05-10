package eu.redseeds.sc.editor.rsl.editors.domain;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.DomainElementEditorControlFactory;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.NotionEditorControl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionSpecialisation;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;
import eu.remics.recovery.model.preferences.MConfiguration;



public class NotionEditor extends EditorPart {

	private BusinessLayerFacade facade;
	private SCProject scProject;
	private NotionDTO notion;
	private NotionEditorControl notionEditorControl;
	private ScrolledComposite sc;
	
	/**
	 * Used for updating the editor
	 */
	private TableViewer viewer = null;
	
	/**
	 * Used for updating the editor
	 */
	private TableViewer viewerNotions = null;
	
	/**
	 * Used for updating the editor
	 */
	private TableViewer viewerStatements = null;
	
	/**
	 * Used for updating the editor
	 */
	private TableViewer viewerAttributes = null;
	
	private TableViewer viewerAttributeParents = null;
	
	private boolean dirty = false;
	
	public static final String EDITOR_ID = "eu.redseeds.sc.editor.rsl.editors.NotionEditor";

	public NotionEditor() {
		super();
	}

	public void dispose() {
		// colorManager.dispose();
		super.dispose();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		monitor.beginTask("Saving Notion " + notion.getName(), 1);
		boolean check = MConfiguration.isCheckRelations();
		if (check && !(0==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex() || MConfiguration.isAllowAttributesForDataViews() && (7==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex() || 8==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex() || 9==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex())) && !notion.getNotionDTOAttributeList().isEmpty()){
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Cannot change type because notion has assigned "+NotionTypesEnum.values()[4]+"s");
			mb.setText(DomainElementEditorControlFactory.NOTION_KIND_LABEL+NotionTypesEnum.EMPTY);
			mb.open();
		} else if (check && MNotion.isAttribute(notion) && 5!=((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex()){
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Cannot change type because notion is used as a "+NotionTypesEnum.values()[4]);
			mb.setText(DomainElementEditorControlFactory.NOTION_KIND_LABEL+NotionTypesEnum.values()[4]);
			mb.open();
		} else if (check && 0==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex() && !MNotion.canBeConcept(notion)){
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Notion related domain elements inappropriate for a "+NotionTypesEnum.EMPTY.toLowerCase());
			mb.setText(DomainElementEditorControlFactory.NOTION_KIND_LABEL+NotionTypesEnum.EMPTY.toLowerCase());
			mb.open();
		} else if (check && 1==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex() && !MNotion.canBeScreen(notion)){
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Notion related domain elements inappropriate for a "+NotionTypesEnum.values()[0]);
			mb.setText(DomainElementEditorControlFactory.NOTION_KIND_LABEL+NotionTypesEnum.values()[0]);
			mb.open();
		} else if (check && 2==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex() && !MNotion.canBeMessage(notion)){
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Notion related domain elements inappropriate for a "+NotionTypesEnum.values()[1]);
			mb.setText(DomainElementEditorControlFactory.NOTION_KIND_LABEL+NotionTypesEnum.values()[1]);
			mb.open();
		} else if (check && 3==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex() && !MNotion.canBeConfirmationDialog(notion)){
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Notion related domain elements inappropriate for a "+NotionTypesEnum.values()[2]);
			mb.setText(DomainElementEditorControlFactory.NOTION_KIND_LABEL+NotionTypesEnum.values()[2]);
			mb.open();
		} else if (check && 4==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex() && !MNotion.canBeTrigger(notion)){
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Notion related domain elements inappropriate for a "+NotionTypesEnum.values()[3]);
			mb.setText(DomainElementEditorControlFactory.NOTION_KIND_LABEL+NotionTypesEnum.values()[3]);
			mb.open();
		} else if (check && 5==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex() && !MNotion.canBeAttribute(notion)){
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Notion related domain elements inappropriate for a "+NotionTypesEnum.values()[4]);
			mb.setText(DomainElementEditorControlFactory.NOTION_KIND_LABEL+NotionTypesEnum.values()[4]);
			mb.open();
		} else if (check && 6==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex() && !MNotion.canBeOption(notion)){
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Notion related domain elements inappropriate for a "+NotionTypesEnum.values()[5]);
			mb.setText(DomainElementEditorControlFactory.NOTION_KIND_LABEL+NotionTypesEnum.values()[5]);
			mb.open();
		} else if (check && 7==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex() && !MNotion.canBeListView(notion)){
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Notion related domain elements inappropriate for a "+NotionTypesEnum.values()[6]);
			mb.setText(DomainElementEditorControlFactory.NOTION_KIND_LABEL+NotionTypesEnum.values()[6]);
			mb.open();
		} else if (check && 8==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex() && !MNotion.canBeSimpleView(notion)){
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Notion related domain elements inappropriate for a "+NotionTypesEnum.values()[7]);
			mb.setText(DomainElementEditorControlFactory.NOTION_KIND_LABEL+NotionTypesEnum.values()[7]);
			mb.open();
		} else if (check && 9==((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex() && !MNotion.canBeTreeView(notion)){
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Notion related domain elements inappropriate for a "+NotionTypesEnum.values()[8]);
			mb.setText(DomainElementEditorControlFactory.NOTION_KIND_LABEL+NotionTypesEnum.values()[8]);
			mb.open();
		} else if (check && !haveProperGeneralization(((Combo)notionEditorControl.getChildren()[22]).getSelectionIndex(), notion)){
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Inappropriate generaliztions");
			mb.setText("Generalizations");
			mb.open();
		} else if(DomainElementEditorControlFactory.saveNotion(notionEditorControl, notion)) {
			scProject.save();
			dirty = false;
			SCProjectHelper.refreshSCNavigator();
			firePropertyChange(PROP_DIRTY);
			setTitleToolTip(notion.getName());
			setPartName(notion.getName());
		} else {
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("Provided notion name is not unique");
			mb.setText("Name");
			mb.open();
		}
		monitor.worked(1);
		monitor.done();
		refresh();
	}
	
	private boolean haveProperGeneralization(int num, NotionDTO not){
		BusinessLayerFacade blf = (BusinessLayerFacade)((Notion) not).getGraph();
		String type = "";
		if (0!=num) type = NotionTypesEnum.values()[num-1].tag();
		for (NotionSpecialisation gen:blf.getNotionSpecialisationVertices())
			if (((Notion) ((NotionSpecialisationDTO) gen).getGeneralNotion()).getId()==((Notion) not).getId() && !type.equals(((NotionSpecialisationDTO) gen).getSpecialisedNotion().getType())
			|| ((Notion) ((NotionSpecialisationDTO) gen).getSpecialisedNotion()).getId()==((Notion) not).getId() && !type.equals(((NotionSpecialisationDTO) gen).getGeneralNotion().getType()))
				return false;
		return true;
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
		notionEditorControl = DomainElementEditorControlFactory
			.getNotionEditorControl(this, sc);
		notionEditorControl.setSize(
				DomainElementEditorControlFactory.NOTION_CONTROL_WIDTH, 
				DomainElementEditorControlFactory.NOTION_CONTROL_HEIGHT);
		sc.setContent(notionEditorControl);
	}

	@Override
	public void setFocus() {
		refresh();
	}

	public BusinessLayerFacade getFacade() {
		return facade;
	}

	public void setFacade(BusinessLayerFacade facade) {
		this.facade = facade;
	}

	public NotionDTO getNotion() {
		return notion;
	}

	public void setNotion(NotionDTO notion) {
		this.notion = notion;
		if (!isDirty()) {
			if(viewer == null || viewerNotions == null || viewerStatements == null || viewerAttributes == null) {
				viewer = DomainElementEditorControlFactory
					.fillNotionEditorControlWithData(this.notionEditorControl, notion);
			}
			else {
				DomainElementEditorControlFactory
					.updateNotionEditorControlWithData(this.notionEditorControl, notion, viewer, viewerNotions, viewerStatements, viewerAttributes, viewerAttributeParents);
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

	public TableViewer getViewerNotions() {
		return viewerNotions;
	}

	public void setViewerNotions(TableViewer viewerNotions) {
		this.viewerNotions = viewerNotions;
	}

	public TableViewer getViewerStatements() {
		return viewerStatements;
	}

	public void setViewerStatements(TableViewer viewerStatements) {
		this.viewerStatements = viewerStatements;
	}
	
	public void refresh(){
		if(viewer != null) {
			viewer.refresh();
		}
		if(viewerNotions != null) {
			viewerNotions.refresh();
		}
		if(viewerStatements != null) {
			viewerStatements.refresh();
		}
		if(viewerAttributes != null) {
			viewerAttributes.refresh();
		}
		if(viewerAttributeParents != null){
			viewerAttributeParents.refresh();
		}
	}

	public TableViewer getViewerAttributes() {
		return viewerAttributes;
	}
	
	public TableViewer getViewerAttributeParents() {
		return viewerAttributeParents;
	}

	public void setViewerAttributes(TableViewer viewerAttributes) {
		this.viewerAttributes = viewerAttributes;
	}
	
	public void setViewerAttributeParents(TableViewer viewerAttributeParents) {
		this.viewerAttributeParents = viewerAttributeParents;
	}
}
