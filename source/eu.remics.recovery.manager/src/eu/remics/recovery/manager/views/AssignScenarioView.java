package eu.remics.recovery.manager.views;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import eu.redseeds.engine.navigator.SCProjectSorter;
import eu.redseeds.sc.query.ui.editors.TreeViewContentProvider;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.common.TableView;
import eu.remics.common.bindings.ICommandIds;
import eu.remics.recovery.manager.applogic.AMain;
import eu.remics.recovery.manager.applogic.ActionsFactory;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;
import eu.remics.recovery.model.dto.XScenariosCommonPart;

public class AssignScenarioView extends ViewPart {
	
	private TableView tabview = new TableView();
	private TableView tabview2 = new TableView();
	private String[] columnTitles = {"Scenario"};
	private String[] columnTitles2 = {"Alternative scenario"};
	private TreeViewer viewer;
	private int step;
	Button btnUp;
	public Button btnDown;
	
	int extIndex = 0;
	
	private ISelectionChangedListener selectListener;
	
	private AttachShifter shifter;
	
	@Override
	public void dispose(){
		AMain.cAssignScenarioToUseCase.setFilter(new UseCasesFilter());
		
		UnassignedScenariosView uslv = (UnassignedScenariosView) RecoveryManagerHelper.getUnassignedScenarioListView();
		if(uslv == null) return;
		uslv.disableButtons();
	}
	

	@Override
	public void createPartControl(Composite parent) {
		createControls(parent);
		addTreeSelectionListener();
		getAlternativeScenarioTable().setInput(AMain.cAssignScenarioToUseCase.aScenario.getConstrainedLanguageScenarioDTOList().get(0));
		if(tabview.getViewer().getTable().getItemCount() == 0){
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
		}
		btnDown.addSelectionListener(new SelectionListenerOnBtnDown());
		btnUp.addSelectionListener(new SelectionListenerOnBtnUp());
		
		shifter = new AttachShifter(this);
		
		try {
			ActionsFactory.createAction(this, AMain.cAssignScenarioToUseCase.vAssignScenarioWindow, AMain.cAssignScenarioToUseCase.vAssignScenarioWindow.getClass().getMethod("saveNewUseCase",  (Class<?>[])null), ActionsFactory.ATTACH, ICommandIds.CMD_SAVE_USE_CASE);
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
		
		viewer = new TreeViewer(parent);
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		RequirementsSpecificationDTO reqModel = rmh.getRequirementsSpecification();
		TreeViewContentProvider reqContentProvider = new TreeViewContentProvider(reqModel);
		viewer.setContentProvider(reqContentProvider);
		viewer.setLabelProvider(reqContentProvider);
		viewer.setInput(reqModel);
		viewer.setSorter(new SCProjectSorter());
		getSite().setSelectionProvider(viewer);
		
		revealUseCases(reqModel);
		viewer.getControl().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
		
		Group controls = new Group(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		controls.setLayout(gridLayout);
		controls.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		
		Label label = new Label(controls, SWT.NONE);
		label.setText("Colored row indicates insertion point.");
		
		Group nested = new Group(controls, SWT.NONE);
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
		btnDown = new Button(c, SWT.ARROW | SWT.DOWN | SWT.CENTER);
	}
	
	private void addTreeSelectionListener(){
		selectListener = new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event) {
				
				ActionsFactory.disableAction(ActionsFactory.ATTACH);
				
				if(event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					for (Iterator<?> iterator = selection.iterator(); iterator.hasNext();) {
						Object domain = (Object)iterator.next();
						if(domain instanceof UseCaseDTO){
							UseCaseDTO obj = (UseCaseDTO) domain;
							
							AMain.cAssignScenarioToUseCase.vAssignScenarioWindow.setUseCase(obj);
						}
						else if(domain instanceof ConstrainedLanguageScenarioDTO){
							ConstrainedLanguageScenarioDTO scen = (ConstrainedLanguageScenarioDTO)domain;
							getScenarioTable().getTable().removeAll();
							getScenarioTable().setInput(scen);
							
							getAlternativeScenarioTable().getTable().removeAll();
							UseCaseDTO detachedScenario = AMain.cAssignScenarioToUseCase.aScenario;
							if(detachedScenario != null && !detachedScenario.getConstrainedLanguageScenarioDTOList().isEmpty()){
								getAlternativeScenarioTable().setInput(detachedScenario.getConstrainedLanguageScenarioDTOList().get(0));
							}

							AMain.cAssignScenarioToUseCase.vAssignScenarioWindow.setScenario(scen);
							AMain.cAssignScenarioToUseCase.vAssignScenarioWindow.setUseCase(scen.getParent());
							
							if(scen.getScenarioSentenceList().size() != 1){
								btnUp.setEnabled(false);
								btnDown.setEnabled(true);
							}
							setReferencedStepNumber(0);
							ConstrainedLanguageScenarioDTO alterScen = (ConstrainedLanguageScenarioDTO)getAlternativeScenarioTable().getInput();
							int[] shift = MScenario.compareScenarios(scen, alterScen);
							initSimilarity(shift, scen.getScenarioSentenceList().size());
						}
					}
				}
			}
		};
		
		viewer.addSelectionChangedListener(selectListener);
	}
	
	public void initSimilarity(int[] shift, int size) {
		if(shift[1] != 0 && shift[2] == 1){
			ActionsFactory.enableAction(ActionsFactory.ATTACH);
		}
		if(shift[0] == 0 && shift[1] == 1 && shift[2] == 1){
			shifter.selectFirstRows(size);
		}
		else if(shift[1] < 0){
			shifter.selectFirstRows(size);
		}
		else{
			if(shift[0] != 0 && shift[1] == 1 && shift[2] == 1 
					&& tabview.getViewer().getTable().getItemCount() == 2){
				btnDown.setEnabled(false);
			}
			shifter.setShift(shift);
		}
	}
	
	@Override
	public void setFocus() {
	}

	public TreeViewer getAssignScenarioTree() {
		return viewer;
	}
	
	public TableViewer getScenarioTable(){
		return tabview.getViewer();
	}
	
	public TableViewer getAlternativeScenarioTable(){
		return tabview2.getViewer();
	}
	
	public void setReferencedStepNumber(int step){
		this.step = step;
	}
	
	public int getReferencedStepNumber(){
		return step;
	}
	
	public void setSimilarScenarios(List<XScenariosCommonPart> similar){
	}
	
	public void revealUseCases(Object reqModel){
		if(reqModel instanceof RequirementsSpecificationDTO){
			for(RequirementsPackageDTO reqpack : ((RequirementsSpecificationDTO) reqModel).getRequirementsPackagesDTOList()){
				if(reqpack.getRequirementsPackages().size() != 0){
					revealUseCases(reqpack);
				}
				//for(RequirementDTO uc : reqpack.getRequirements()){
					viewer.reveal(/*uc*/reqpack);
				//}
			}
		}
		else if(reqModel instanceof RequirementsPackageDTO){
			for(RequirementsPackageDTO reqpack : ((RequirementsPackageDTO) reqModel).getRequirementsPackages()){
				if(reqpack.getRequirementsPackages().size() != 0){
					revealUseCases(reqpack);
				}
				//for(RequirementDTO uc : reqpack.getRequirements()){
					viewer.reveal(/*uc*/reqpack);
				//}
			}
		}
	}
	
	class SelectionListenerOnBtnUp implements SelectionListener {

		@Override
		public void widgetSelected(SelectionEvent e) {
			if(!btnDown.getEnabled()){
				btnDown.setEnabled(true);
			}
			
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
			ActionsFactory.enableAction(ActionsFactory.ATTACH);
			
			shifter.clearOnlySelection(tabview.getViewer());
			
			if(tabview2.getViewer().getElementAt(0) instanceof SVOSentenceDTO){
				shifter.moveOneRowDown(tabview2.getViewer());
				btnUp.setEnabled(false);
			}
			if(tabview2.getViewer().getElementAt(1) instanceof SVOSentenceDTO){
				btnUp.setEnabled(false);
			}
			
			shifter.moveOneRowDown(tabview2.getViewer());
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
}


