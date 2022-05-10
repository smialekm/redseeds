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
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.TSLBusinessLayerFacade;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestCaseSentence;
import eu.redset.emf.model.tsl.TestInstance;
import eu.redset.emf.model.tsl.TestScenario;



public class TestScenarioEditor extends MultiPageEditorPart {

	private TestScenarioMainView mainView;
	private TestScenario testScenario;
	private TSLBusinessLayerFacade facade;
	private SCProject scProject;
	/** diagram image tab index */
	private Integer diagramImageTabIdx;

	private boolean dirty = false;

	public TestScenario getTestScenario() {
		return testScenario;
	}

	public void setTestScenario(TestScenario testScenario) {
		// set only if use case was not set before
		
		if (this.testScenario == null) {
			this.testScenario = testScenario;
			mainView.fillData(testScenario);
			createTestCaseViews();
			// this one must be lest
			createImageView();
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
		monitor.beginTask("Saving Test Scenario" + testScenario.getName(), 1);
		if (!mainView.save()) {// TODO check
			monitor.worked(1);
			monitor.done();
			return;
		}

		// save in scenario views, all but first and lest because lest is
		// Diagram graphic view.
		for (int viewIdx = 1; viewIdx < this.getPageCount(); viewIdx++) {
			TestCaseView view = (TestCaseView) this.getControl(viewIdx);
			view.save();
			//setPageText(viewIdx, view.getScenario().getName());
		}

		scProject.saveTS(testScenario);

		dirty = false;
		//SCProjectHelper.refreshSCNavigator();
		monitor.worked(1);
		monitor.done();
		firePropertyChange(PROP_DIRTY);
		setTitleToolTip(testScenario.getName());
		setPartName(testScenario.getName());
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
		mainView = new TestScenarioMainView(this, getContainer(), SWT.NONE);
		int index = addPage(mainView);
		setPageText(index, "Test Scenario Editor");
	}

	private void createInternalTestCaseViews(TestCase tc){
		for (TestCaseSentence tcs : tc.getSentences()){
			if (tcs.getScenarioSentence() instanceof ControlSentence){
				ControlSentence sc = (ControlSentence)tcs.getScenarioSentence();
				for (TestInstance ti : sc.getAttachedTests()){
					if (ti instanceof TestCase)
						addTestCaseTab((TestCase)ti);
					createInternalTestCaseViews((TestCase)ti);
				}
			}
		}
	}
	
	private void createTestCaseViews() {
		List<TestCase> list = this.testScenario.getEReference1();
		for (TestCase s : list) {
			addTestCaseTab(s);
			createInternalTestCaseViews(s);
		}
	}

	private void createImageView() {
		//addDiagramImageViewTab(useCaseTest);
	}

	public int addTestCaseTab(TestCase tc) {
		TestCaseView tcv = new TestCaseView(this, tc, getContainer(), SWT.NONE);
		int index = addPage(tcv);
		setPageText(index, tc.getOrderNumberGlobal()+". "+tc.getUcName());
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
		super.pageChange(newPageIndex);
	}

	public TestCaseView getActiveTestCaseView() {
		if (getActivePage() != -1)
			if (getControl(getActivePage()) instanceof TestCaseView)
				return (TestCaseView) getControl(getActivePage());
		return null;
	}

	public void refreshScenariosTabs(TestCaseView current) {

	}

	public void setTab(TestCase testCase) {
		int s;
		/*if((s = ((TestScenario)testCase.eContainer()).getEReference1().size()) != getPageCount()-2){
			addTestCaseTab(testCase);
		}*/
		for (int i = 1; i < getPageCount(); i++) {
			if(this.getControl(i) instanceof TestCaseView){
				TestCaseView view = (TestCaseView) this.getControl(i);
				if (view.getTestCase().equals(testCase)) {
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

