package eu.redseeds.sc.editor.rsl.editors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentence;

public class UseCaseEditor extends MultiPageEditorPart {

	private UseCaseMainView mainView;
	private UseCaseDTO useCase;
	private Map<Integer,ConstrainedLanguageSentenceDTO> backupSentences = new HashMap<Integer,ConstrainedLanguageSentenceDTO>();
	private List<List<Integer>> backupScenarios = new ArrayList<List<Integer>>();
	private List<String> backupScenariosNames = new ArrayList<String>();
	private BusinessLayerFacade facade;
	private SCProject scProject;
	/** diagram image tab index */
	private Integer diagramImageTabIdx;

	private boolean dirty = false;

	public UseCaseDTO getUseCase() {
		return useCase;
	}

	public void setUseCase(UseCaseDTO useCase) {
		// set only if use case was not set before
		if (this.useCase == null) {
			this.useCase = useCase;
			mainView.fillData(useCase);
			createScenarioViews();
			// this one must be lest
			createImageView();
		}
	}

	public BusinessLayerFacade getFacade() {
		return facade;
	}

	public void setFacade(BusinessLayerFacade facade) {
		this.facade = facade;
	}

	@Override
	protected void createPages() {
		createMainView();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		monitor.beginTask("Saving UC " + useCase.getName(), 1);
		if (!mainView.save()) {// TODO check
			monitor.worked(1);
			monitor.done();
			return;
		}

		// save in scenario views, all but first and lest because lest is
		// Diagram graphic view.
		for (int viewIdx = 1; viewIdx < this.getPageCount() - 1; viewIdx++) {
			UseCaseScenarioView view = (UseCaseScenarioView) this.getControl(viewIdx);
			view.save();
			setPageText(viewIdx, view.getScenario().getName());
		}
		
		clearBackupScenarios();
		
		scProject.save();
		
		dirty = false;
		SCProjectHelper.refreshSCNavigator();
		monitor.worked(1);
		monitor.done();
		firePropertyChange(PROP_DIRTY);
		setTitleToolTip(useCase.getName());
		setPartName(useCase.getName());
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
		mainView = new UseCaseMainView(this, getContainer(), SWT.NONE);
		int index = addPage(mainView);
		setPageText(index, "UC Editor");
	}

	private void createScenarioViews() {
		List<ConstrainedLanguageScenarioDTO> list = this.useCase.getConstrainedLanguageScenarioDTOList();
		for (ConstrainedLanguageScenarioDTO s : list) {
			addScenarioTab(s);
		}
	}

	private void createImageView() {
		addDiagramImageViewTab(useCase);
	}

	public int addScenarioTab(ConstrainedLanguageScenarioDTO s) {
		UseCaseScenarioView ucsv = new UseCaseScenarioView(this, s, getContainer(), SWT.NONE);
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

	private int addDiagramImageViewTab(UseCaseDTO useCaseDTO) {
		Composite imgv = new UseCaseImageView(useCaseDTO, getContainer(), SWT.NONE);
		int index = addPage(imgv);
		setDiagramImageTabIndex(Integer.valueOf(index));
		setPageText(index, UseCaseImageView.getTabName());
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
		if (c instanceof UseCaseScenarioView)
			((UseCaseScenarioView) c).refreshSentenes();
		super.pageChange(newPageIndex);
	}

	public UseCaseScenarioView getActiveScenarioView() {
		if (getActivePage() != -1)
			if (getControl(getActivePage()) instanceof UseCaseScenarioView)
				return (UseCaseScenarioView) getControl(getActivePage());
		return null;
	}

	public void refreshScenariosTabs(UseCaseScenarioView current) {

	}

	public void setTab(ConstrainedLanguageScenarioDTO scenario) {
		for (int i = 1; i < getPageCount(); i++) {
			if(this.getControl(i) instanceof UseCaseScenarioView){
				UseCaseScenarioView view = (UseCaseScenarioView) this.getControl(i);
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
	//added lines
	public void setItemsEnablement() {
		if (this.getActivePageId() > 0 && this.getSelectedPage() instanceof UseCaseScenarioView){
			((UseCaseEditorContributor) this.getEditorSite().getActionBarContributor()).setItemsEnablement(true);
		} else {
			((UseCaseEditorContributor) this.getEditorSite().getActionBarContributor()).setItemsEnablement(false);
		}
	}
	
	@Override
	public void dispose() {
		if (dirty){
			restoreScenarios();
		} else {
			clearBackupScenarios();
		}
		super.dispose();
	}
	
	public void backupScenariosIfNotDirty(){
		if (!dirty) backupScenarios();
	}
	
	private void backupScenarios(){
		for (ConstrainedLanguageScenarioDTO scen:useCase.getConstrainedLanguageScenarioDTOList())
			for (ConstrainedLanguageSentenceDTO sent:scen.getScenarioSentenceList())
				if (!backupSentences.keySet().contains(((ConstrainedLanguageSentence) sent).getId()))
					backupSentences.put(((ConstrainedLanguageSentence) sent).getId(),sent.copy());
		for (ConstrainedLanguageSentenceDTO sent:backupSentences.values())
			if (sent instanceof RejoinSentenceDTO)
				((RejoinSentenceDTO) sent).setRejoinedSentence(backupSentences.get(((ConstrainedLanguageSentence) ((RejoinSentenceDTO) sent).getRejoinedSentence()).getId()));
		for (ConstrainedLanguageScenarioDTO scen:useCase.getConstrainedLanguageScenarioDTOList()){
			backupScenariosNames.add(scen.getName());
			List<Integer> scenario = new ArrayList<Integer>();
			for (ConstrainedLanguageSentenceDTO sent:scen.getScenarioSentenceList())
				scenario.add(((ConstrainedLanguageSentence) sent).getId());
			backupScenarios.add(scenario);
		}
	}
	
	private void restoreScenarios(){
		while(!useCase.getConstrainedLanguageScenarioDTOList().isEmpty()) useCase.getConstrainedLanguageScenarioDTOList().get(0).delete();
		for (int i =0; i<backupScenarios.size();i++){
			ConstrainedLanguageScenarioDTO scenario = facade.createConstrainedLanguageScenarioDTO();
			scenario.setName(backupScenariosNames.get(i));
			for (Integer j:backupScenarios.get(i))
				scenario.addScenarioStep(backupSentences.get(j));
			useCase.addConstrainedLanguageScenario(scenario);
		}
		SCProjectHelper.refreshSCNavigator();
	}
	
	private void clearBackupScenarios(){
		for (ConstrainedLanguageSentenceDTO sent:backupSentences.values()) sent.delete();
		backupSentences.clear();
		backupScenarios.clear();
		backupScenariosNames.clear();
	}
	
	public void clearBackupScenariosIfDirty(){
		if (dirty) clearBackupScenarios();
	}

}
