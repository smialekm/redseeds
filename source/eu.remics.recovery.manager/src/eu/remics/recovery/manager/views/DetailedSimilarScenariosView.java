package eu.remics.recovery.manager.views;

import java.util.Iterator;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.part.ViewPart;

import eu.redseeds.engine.navigator.SCProjectSorter;
import eu.redseeds.sc.query.ui.editors.TreeViewContentProvider;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.common.TableView;
import eu.remics.common.bindings.ICommandIds;
import eu.remics.recovery.manager.applogic.AMain;
import eu.remics.recovery.manager.applogic.ActionsFactory;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;

public class DetailedSimilarScenariosView extends ViewPart {

	private TableView tabview = new TableView();
	private TableView tabview2 = new TableView();
	private String[] columnTitles = {"Scenario"};
	private String[] columnTitles2 = {"Similar scenario"};
	Button btnUp;
	Button btnDown;
	private int step = 1;
	private TreeViewer treeViewer;
	private TreeViewer referenceTreeViewer;
	RequirementsSpecificationDTO reqModel;
	
	private MergeShifter shifter;
	
	@Override
	public void createPartControl(Composite parent) {
		createControls(parent);
		btnDown.addSelectionListener(new SelectionListenerOnBtnDown());
		btnUp.addSelectionListener(new SelectionListenerOnBtnUp());
		addTreeSelectionListener(treeViewer);
		addTreeSelectionListener(referenceTreeViewer);
		
		shifter = new MergeShifter(this);
		
		try {
			ActionsFactory.createAction(this, AMain.cMergeUseCases, AMain.cMergeUseCases.getClass().getMethod("_ClicksMergeUseCasesOption",  (Class<?>[])null), "Merge", ICommandIds.CMD_MERGE_USE_CASES);
			ActionsFactory.createAction(this, AMain.cMergeUseCases, AMain.cMergeUseCases.getClass().getMethod("_ClicksPartialMergeUseCasesOption",  (Class<?>[])null), "Move scenario", ICommandIds.CMD_PARTIAL_MERGE_USE_CASES);
		} 
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	private void createControls(Composite parent){
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginWidth = 0;
		layout.marginHeight = 2;
		parent.setLayout(layout);
		
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		reqModel = rmh.getRequirementsSpecification();
		TreeViewContentProvider reqContentProvider = new TreeViewContentProvider(reqModel);
		
		Group controls = new Group(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		controls.setLayout(gridLayout);
		controls.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		
		treeViewer = new TreeViewer(controls);
		treeViewer.setContentProvider(reqContentProvider);
		treeViewer.setLabelProvider(reqContentProvider);
		treeViewer.setInput(reqModel);
		treeViewer.setSorter(new SCProjectSorter());
		getSite().setSelectionProvider(treeViewer);
		treeViewer.getControl().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		
		referenceTreeViewer = new TreeViewer(controls);
		referenceTreeViewer.setContentProvider(reqContentProvider);
		referenceTreeViewer.setLabelProvider(reqContentProvider);
		referenceTreeViewer.setInput(reqModel);
		referenceTreeViewer.setSorter(new SCProjectSorter());
		getSite().setSelectionProvider(referenceTreeViewer);
		referenceTreeViewer.getControl().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		
		Group nested = new Group(parent, SWT.NONE);
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 3;
		nested.setLayout(gridLayout2);
		nested.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		tabview.createViewer(new Composite(nested,SWT.BORDER), getSite(), columnTitles );
		tabview2.createViewer(new Composite(nested,SWT.BORDER), getSite(), columnTitles2 );
		
		Composite c = new Composite(nested, SWT.BORDER);
		GridLayout gridLayout3 = new GridLayout();
		gridLayout3.numColumns = 1;
		c.setLayout(gridLayout3);
		c.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, false, false));
		btnUp = new Button(c, SWT.ARROW | SWT.UP | SWT.CENTER);
		btnUp.setEnabled(false);
		btnDown = new Button(c, SWT.ARROW | SWT.DOWN | SWT.CENTER);
	}
	
	private void addTreeSelectionListener(final TreeViewer viewer){
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if(event.getSelection() instanceof ITreeSelection) {
					ITreeSelection selection = (ITreeSelection)event.getSelection();
					for (Iterator<?> iterator = selection.iterator(); iterator.hasNext();) {
						Object domain = (Object)iterator.next();
						if(domain != null && domain instanceof ConstrainedLanguageScenarioDTO){
							ConstrainedLanguageScenarioDTO scen = (ConstrainedLanguageScenarioDTO)domain;
							if(viewer.equals(treeViewer)){
								btnUp.setEnabled(false);
								btnDown.setEnabled(true);
								ActionsFactory.disableAction(ActionsFactory.MERGE);
								ActionsFactory.disableAction(ActionsFactory.MOVE);

								RecoveryManagerHelper.getSimilarScenarios().setReferenceScenario(scen);
								getScenarioViewer().getTable().removeAll();
								getScenarioViewer().setInput(scen);
								
								getSimilarScenarioViewer().getTable().removeAll();
								getSimilarScenarioViewer().setInput((ConstrainedLanguageScenarioDTO)getSimilarScenarioViewer().getInput());

								int[] shift = MScenario.compareScenarios(scen, (ConstrainedLanguageScenarioDTO)getSimilarScenarioViewer().getInput());
								initSimilarity(shift, ((ConstrainedLanguageScenarioDTO)getScenarioViewer().getInput()).getScenarioSentenceList().size());
							}
							else if(viewer.equals(referenceTreeViewer)){
								btnUp.setEnabled(false);
								btnDown.setEnabled(true);
								ActionsFactory.disableAction(ActionsFactory.MERGE);
								ActionsFactory.disableAction(ActionsFactory.MOVE);
								
								RecoveryManagerHelper.getSimilarScenarios().setScenario(scen);
								getSimilarScenarioViewer().getTable().removeAll();
								getSimilarScenarioViewer().setInput(scen);
								
								getScenarioViewer().getTable().removeAll();
								getScenarioViewer().setInput((ConstrainedLanguageScenarioDTO)getScenarioViewer().getInput());
								
								int[] shift = MScenario.compareScenarios((ConstrainedLanguageScenarioDTO)getScenarioViewer().getInput(), scen);
								initSimilarity(shift, ((ConstrainedLanguageScenarioDTO)getScenarioViewer().getInput()).getScenarioSentenceList().size());
							}
						}
					}
				}
			}
		});
	}
	
	public void initSimilarity(int[] shift, int size) {
		if(shift[1] < 0){
			shifter.selectFirstRows(size);
		}
		if(shift[1] == 0 && shift[2] == 1){
			ActionsFactory.enableAction(ActionsFactory.MERGE);
			ActionsFactory.enableAction(ActionsFactory.MOVE);
		}
		if(shift[0] == 0 && shift[1] == 0 && shift[2] == 1){
			shifter.selectFirstRows(size);
		}
		else{
			shifter.setShift(shift);
		}
	}

	@Override
	public void setFocus() {
	}
	
	public TableViewer getScenarioViewer(){
		return tabview.getViewer();
	}
	
	public TableViewer getSimilarScenarioViewer(){
		return tabview2.getViewer();
	}
	
	public void setReferencedStepNumber(int step){
		this.step = step;
	}
	
	public int getReferencedStepNumber(){
		return step;
	}
	
	class SelectionListenerOnBtnUp implements SelectionListener {

		@Override
		public void widgetSelected(SelectionEvent e) {
			if(!btnDown.getEnabled()){
				btnDown.setEnabled(true);
			}
			
			tabview.getViewer().getTable().deselectAll();
			tabview2.getViewer().getTable().deselectAll();
			
			shifter.clearOnlySelection(tabview.getViewer());
			shifter.moveOneRowUp(tabview2.getViewer());
			int counter = shifter.countNullRows(tabview2.getViewer());
			shifter.syncTablesOnUp(tabview.getViewer(), tabview2.getViewer(), counter);
			
			if(tabview.getViewer().getTable().getItem(0).getText().equalsIgnoreCase("precondition: ")){
				if(tabview2.getViewer().getElementAt(2) != null){
					btnUp.setEnabled(false);
				}
			}
			else{
				if(tabview2.getViewer().getElementAt(1) != null){
					btnUp.setEnabled(false);
				}
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}
	
	class SelectionListenerOnBtnDown implements SelectionListener {

		@Override
		public void widgetSelected(SelectionEvent e) {
			if(!btnUp.getEnabled()){
				btnUp.setEnabled(true);
			}
			ActionsFactory.enableAction(ActionsFactory.MERGE);
			ActionsFactory.enableAction(ActionsFactory.MOVE);
			
			tabview.getViewer().getTable().deselectAll();
			tabview2.getViewer().getTable().deselectAll();
			
			shifter.clearOnlySelection(tabview.getViewer());
			shifter.moveOneRowDown(tabview2.getViewer());
			
			Object o = tabview2.getViewer().getElementAt(1);
			String text = tabview2.getViewer().getTable().getItem(1).getText();
			if(o != null && text.equalsIgnoreCase("precondition: ")){
				btnUp.setEnabled(false);
				shifter.moveOneRowDown(tabview2.getViewer());
			}
			o = tabview2.getViewer().getElementAt(2);
			text = tabview2.getViewer().getTable().getItem(2).getText();
			if(o != null && text.equalsIgnoreCase("precondition: ")){
				btnUp.setEnabled(false);
			}
			
			int counter = shifter.countNullRows(tabview2.getViewer());
			shifter.syncTablesOnDown(tabview.getViewer(), tabview2.getViewer(), counter);
			
			if(counter == tabview.getViewer().getTable().getItemCount()-1){
				btnDown.setEnabled(false);
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}
	
	public TreeViewer getScenarioTree() {
		return treeViewer;
	}
	
	public TreeViewer getReferencedScenarioTree() {
		return referenceTreeViewer;
	}
	
	public void refreshScenarioTree(){
		treeViewer.refresh();
	}
	
	public void refreshReferenceScenarioTree(){
		referenceTreeViewer.refresh();
	}
	
	public void refreshScenarioViewer(UseCaseDTO uc){
		getScenarioViewer().getTable().removeAll();
		getScenarioViewer().setInput(uc.getConstrainedLanguageScenarioDTOList().get(0));
	}
	
	public void refreshSimilarScenarioViewer(UseCaseDTO uc){
		getSimilarScenarioViewer().getTable().removeAll();
		getSimilarScenarioViewer().setInput(uc.getConstrainedLanguageScenarioDTOList().get(0));
	}
}
