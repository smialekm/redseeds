package eu.redset.tsl.editor.editors;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redset.emf.model.tsl.TSLBusinessLayerFacade;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;


public class UseCaseTestEditor extends MultiPageEditorPart {

	private UseCaseTestMainView mainView;
	private UseCaseTest useCaseTest;
	private TSLBusinessLayerFacade facade;
	private SCProject scProject;
	/** diagram image tab index */
	private Integer diagramImageTabIdx;

	private boolean dirty = false;

	public UseCaseTest getUseCaseTest() {
		return useCaseTest;
	}

	public void setUseCaseTest(UseCaseTest useCaseTest) {
		// set only if use case was not set before
		
		if (this.useCaseTest == null) {
			this.useCaseTest = useCaseTest;
			mainView.fillData(useCaseTest);
			createScenarioViews();
			// this one must be lest
			//createImageView();
		}
	}

	public TSLBusinessLayerFacade getFacade() {
		return facade;
	}

	public void setFacade(TSLBusinessLayerFacade facade) {
		this.facade = facade;
	}

	@Override
	protected void createPages() {
		createMainView();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		monitor.beginTask("Saving UC Test" + useCaseTest.getName(), 1);
		if (!mainView.save()) {// TODO check
			monitor.worked(1);
			monitor.done();
			return;
		}

		// save in scenario views, all but first and lest because lest is
		// Diagram graphic view.
		for (int viewIdx = 1; viewIdx < this.getPageCount(); viewIdx++) {
			UseCaseTestScenarioView view = (UseCaseTestScenarioView) this.getControl(viewIdx);
			view.save();
			//setPageText(viewIdx, view.getScenario().getName());
		}

		scProject.saveTS(useCaseTest);

		dirty = false;
		//SCProjectHelper.refreshSCNavigator();
		monitor.worked(1);
		monitor.done();
		firePropertyChange(PROP_DIRTY);
		setTitleToolTip(useCaseTest.getName());
		setPartName(useCaseTest.getName());
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {

		setSite(site);
		setInput(input);
		setPartName(input.getName());
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	private void createMainView() {
		mainView = new UseCaseTestMainView(this, getContainer(), SWT.NONE);
		int index = addPage(mainView);
		setPageText(index, "UC Test Editor");
	}

	private void createScenarioViews() {
		List<UseCaseTestScenario> list = this.useCaseTest.getEReference0();
		for (UseCaseTestScenario s : list) {
			addScenarioTab(s);
		}
	}

	private void createImageView() {
		//addDiagramImageViewTab(useCaseTest);
	}

	public int addScenarioTab(UseCaseTestScenario s) {
		UseCaseTestScenarioView ucsv = new UseCaseTestScenarioView(this, s, getContainer(), SWT.None);
		int index = addPage(ucsv);
		setPageText(index, s.getName());
		// if diagram image tab was added we want to add other tabs before it
		if (isDiagramImageTabAdded()) {
			// Because we added one new tab at last position and diagram image
			// tab is one less
			setDiagramImageTabIndex(Integer.valueOf(getPageCount() - 2));
			moveDiagramImageTabToLastPosition();
		}
		return index;
	}


	public SCProject getScProject() {
		return scProject;
	}

	public void setScProject(SCProject scProject) {
		this.scProject = scProject;
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
		firePropertyChange(PROP_DIRTY);
	}

	public int getActivePageId() {
		return super.getActivePage();
	}

	@Override
	protected void pageChange(int newPageIndex) {
		Control c = this.getControl(newPageIndex);
		if (c instanceof UseCaseTestScenarioView)
			((UseCaseTestScenarioView) c).refreshTab();
		super.pageChange(newPageIndex);
	}

	public UseCaseTestScenarioView getActiveScenarioView() {
		if (getActivePage() != -1)
			if (getControl(getActivePage()) instanceof UseCaseTestScenarioView)
				return (UseCaseTestScenarioView) getControl(getActivePage());
		return null;
	}

	public void refreshScenariosTabs(UseCaseTestScenarioView current) {

	}

	public void setTab(UseCaseTestScenario scenario) {
		int s;
		/*if((s = ((UseCaseTest)scenario.eContainer()).getEReference0().size()) != getPageCount()-2){
			addScenarioTab(scenario);
		}*/
		for (int i = 1; i < getPageCount(); i++) {
			if(this.getControl(i) instanceof UseCaseTestScenarioView){
				UseCaseTestScenarioView view = (UseCaseTestScenarioView) this.getControl(i);
				if (view.getScenario().equals(scenario)) {
					setActivePage(i);
					break;
				}
			}
		}

	}

	/**
	 * check if diagram image tab was added. to check what is it tab index
	 *
	 * @see #getDiagramImageTabIndex()
	 * @return true if yes
	 */
	private boolean isDiagramImageTabAdded() {
		return diagramImageTabIdx != null;
	}

	/**
	 * @see #isDiagramImageTabAdded()
	 * @return diagram image tab index or null
	 */
	private Integer getDiagramImageTabIndex() {
		return diagramImageTabIdx;
	}

	private void setDiagramImageTabIndex(Integer index) {
		diagramImageTabIdx = index;
	}

	/**
	 * Move diagram image tab to last position i tab list.
	 */
	private void moveDiagramImageTabToLastPosition() {
		int diagramIdx = getDiagramImageTabIndex().intValue();
		removePage(diagramIdx);
		createImageView();
	}
}

