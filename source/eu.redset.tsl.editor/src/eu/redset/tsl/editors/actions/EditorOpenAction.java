package eu.redset.tsl.editors.actions;


import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.jface.dialogs.MessageDialog;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redset.emf.model.tsl.BLTest;
import eu.redset.emf.model.tsl.GUITest;
import eu.redset.emf.model.tsl.NFTest;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.tsl.editor.editors.TestScenarioEditor;
import eu.redset.tsl.editor.editors.TestScenarioEditorContributor;
import eu.redset.tsl.editor.editors.TestScenarioEditorInput;
import eu.redset.tsl.editor.editors.UseCaseTestEditor;
import eu.redset.tsl.editor.editors.UseCaseTestEditorInput;
import eu.redset.tsl.editor.editors.UseCaseTestEditorContributor;


public class EditorOpenAction extends Action implements ISelectionChangedListener{
	private IWorkbenchWindow window;

	private IWorkbenchSite wSite;
	private static String ID_USECASE_EDITOR ="eu.redset.tsl.editor.editors.UseCaseTestEditor";
	private static String ID_TESTSCENARIO_EDITOR ="eu.redset.tsl.editor.editors.TestScenarioEditor";
	
	private ISelection customSelection;
	private IProject project;
	private IProjectFindStrategy projectFindStrategy=ProjectFindStrategyFactory.newDynamicFindStrategy();

	private static interface IProjectFindStrategy{
		/**
		 * Method to find IProject using some or all parameters.
		 * @param treeSelection can/cannot be null according to strategy implementation
		 * @param selectedElement can/cannot be null according to strategy implementation
		 * @param project can/cannot be null according to strategy implementation
		 * @return IProject or null if IProject cannot be found
		 */
		public IProject findIProject(ITreeSelection treeSelection,Object selectedElement,IProject project);
	}
	private static class ProjectFindStrategyFactory{
		public static IProjectFindStrategy newDynamicFindStrategy(){
			return new DynamicFindStrategy();
		}
		public static IProjectFindStrategy newStaticFindStrategy(){
			return new StaticFindStrategy();
		}
		/**
		 * This strategy will every time look for current project using ITreeSelection and selectedElement.
		 */
		private static class DynamicFindStrategy implements IProjectFindStrategy{


			/**
			 * @param treeSelection cannot be null
			 * @param selectedElement cannot be null
			 * @param project can be null
			 */
			@Override
			public IProject findIProject(final ITreeSelection treeSelection,final Object selectedElement,final IProject project) {
				validParams(treeSelection,selectedElement);
				TreePath[] paths = treeSelection.getPathsFor(selectedElement);
				if (paths.length > 0){
					if (paths[0].getFirstSegment() instanceof IProject){
						IProject searchProject = (IProject)paths[0].getFirstSegment();
						return searchProject;
					}
				}
				return null;
			}

			private void validParams(final ITreeSelection treeSelection,final Object selectedElement){
				if(treeSelection==null){
					throw new IllegalArgumentException("for this strategy treeSelection cannot be null");
				}
				if(selectedElement==null){
					throw new IllegalArgumentException("for this strategy selectedElement cannot be null");
				}
			}
		}

		/**
		 * This strategy just return project that is passed as param
		 */
		private static class StaticFindStrategy implements IProjectFindStrategy{
			/**
			 * @param treeSelection can be null
			 * @param selectedElement can be null
			 * @param project can be null but will return null
			 * @return param project
			 */
			@Override
			public IProject findIProject(final ITreeSelection treeSelection,final Object selectedElement,final IProject project) {
				return project;
			}

		}
	}

	public EditorOpenAction() {
	}

	/**
	 * The constructor.
	 */
	public EditorOpenAction(IWorkbenchSite site) {
		wSite = site;
		setEnabled(true);
	}
	/**
	* Use this constructor if you want to set permanent IProject (it want be calculated in runtime)
	*/
	public EditorOpenAction(IWorkbenchSite site,ISelection customSelection,IProject project) {
		wSite = site;
		setEnabled(true);
		this.customSelection=customSelection;
		this.project=project;
		projectFindStrategy=ProjectFindStrategyFactory.newStaticFindStrategy();
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	@Override
	public void run() {

		
		try {
			// Receiving references to opened editors in active page
			//TODO: field window is never written and always be null. Check this.
			IWorkbenchPage activePage = wSite != null ? wSite.getPage() : window.getActivePage();
			//get selection that was manually set or from active page
			ISelection selection = customSelection!=null?customSelection:activePage.getSelection();
			Object selectedElement = ((IStructuredSelection)selection).getFirstElement();
			ITreeSelection treeSelection = (ITreeSelection)selection;

			//TODO TP: this code have been moved to IProjectFindStrategy, rmv it after test
//			TreePath[] paths = treeSelection.getPathsFor(selectedElement);
//				if (paths.length > 0){
//					if (paths[0].getFirstSegment() instanceof IProject){
//						project = (IProject)paths[0].getFirstSegment();
//					} else return;
//			} else return;

			//TODO TP: uncomment, this is correct use of IProjectFindStrategy
			project=projectFindStrategy.findIProject(treeSelection, selectedElement, project);
			if(project==null){
				return;
			}


			if(selectedElement instanceof UseCaseTest) {
				//activePage.openEditor(new UseCaseEditorInput(), ID_USECASE_EDITOR, false);
				UseCaseTestEditorInput useCaseInput = new UseCaseTestEditorInput();
				useCaseInput.setUseCaseTest((UseCaseTest)selectedElement);
				UseCaseTestEditor useCaseTestEditor
					= (UseCaseTestEditor)activePage.openEditor(useCaseInput, ID_USECASE_EDITOR, false, 1);
				useCaseTestEditor.setFacade(SCProjectContainer.instance()
						.getSCProject(project).getTestSpecificationContainer().getTSLFacade(selectedElement));
				useCaseTestEditor.setScProject(SCProjectContainer.instance()
						.getSCProject(project));
				useCaseTestEditor.setUseCaseTest((UseCaseTest) selectedElement);
				
			} else if (selectedElement instanceof UseCaseTestScenario) {
				UseCaseTestEditorInput useCaseTestInput = new UseCaseTestEditorInput();
				UseCaseTest useCaseTest
					= (UseCaseTest)((UseCaseTestScenario)selectedElement).eContainer();
				useCaseTestInput.setUseCaseTest(useCaseTest);
				UseCaseTestEditor useCaseTestEditor
					= (UseCaseTestEditor)activePage.openEditor(useCaseTestInput, ID_USECASE_EDITOR, false, 1);
				useCaseTestEditor.setFacade(SCProjectContainer.instance().getSCProject(project).getTestSpecificationContainer().getTSLFacade(selectedElement));
				useCaseTestEditor.setScProject(SCProjectContainer.instance().getSCProject(project));
				useCaseTestEditor.setUseCaseTest(useCaseTest);
				useCaseTestEditor.setTab((UseCaseTestScenario)selectedElement);
				
				((UseCaseTestEditorContributor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
						getActiveEditor().getEditorSite().getActionBarContributor()).setItemsEnablement(true);
				
				Menu menubar = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getMenuBar();
				MenuItem[] mItems = menubar.getItems();
				for(int i=0; i<mItems.length; i++){
					if(mItems[i].getText().equals("&UseCase")){
						Menu menu =mItems[i].getMenu();
						for(int j=0; j < menu.getItemCount(); j++){
							MenuItem subitem = menu.getItem(j);
							Object menuItemData = subitem.getData();
							if(menuItemData == null)
								continue;
							if(menuItemData instanceof SubContributionItem){
								SubContributionItem cItem = (SubContributionItem) menuItemData;
								IContributionItem conItem = cItem.getInnerItem();
								if(conItem instanceof ActionContributionItem){
									ActionContributionItem item = (ActionContributionItem) conItem;
									item.getAction().setEnabled(true);
								}
							}
							
							subitem.setEnabled(true);
						}
					}
				}
				
			} else if(selectedElement instanceof TestScenario) {
				//activePage.openEditor(new UseCaseEditorInput(), ID_USECASE_EDITOR, false);
				TestScenarioEditorInput testScenarioInput = new TestScenarioEditorInput();
				testScenarioInput.setTestScenario((TestScenario)selectedElement);
				TestScenarioEditor testScenarioEditor
					= (TestScenarioEditor)activePage.openEditor(testScenarioInput, ID_TESTSCENARIO_EDITOR, false, 1);
				testScenarioEditor.setFacade(SCProjectContainer.instance()
						.getSCProject(project).getTestSpecificationContainer().getTSLFacade(selectedElement));
				testScenarioEditor.setScProject(SCProjectContainer.instance()
						.getSCProject(project));
				testScenarioEditor.setTestScenario((TestScenario) selectedElement);
				
				TestDetailsEditorOpenAction openPropertyAction = new TestDetailsEditorOpenAction();
				openPropertyAction.setProject(SCProjectContainer.instance().getSCProject(selectedElement));
				//openPropertyAction.setParent((TestScenario)testScenarioEditor);
				openPropertyAction.setTest((TestScenario)selectedElement);
				openPropertyAction.run();
				
			} else if (selectedElement instanceof TestCase) {
				TestScenarioEditorInput testScenarioInput = new TestScenarioEditorInput();
				
				EObject eObj = ((TestCase)selectedElement).eContainer();
				while (!(eObj instanceof TestScenario)) {
					eObj = eObj.eContainer();
				}
		
				TestScenario testScenario
					= (TestScenario)eObj;
				testScenarioInput.setTestScenario(testScenario);
				TestScenarioEditor testScenarioEditor
				= (TestScenarioEditor)activePage.openEditor(testScenarioInput, ID_TESTSCENARIO_EDITOR, false, 1);
				testScenarioEditor.setFacade(SCProjectContainer.instance().getSCProject(project).getTestSpecificationContainer().getTSLFacade(selectedElement));
				testScenarioEditor.setScProject(SCProjectContainer.instance().getSCProject(project));
				testScenarioEditor.setTestScenario(testScenario);
				testScenarioEditor.setTab((TestCase)selectedElement);
				
				((TestScenarioEditorContributor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
						getActiveEditor().getEditorSite().getActionBarContributor()).setItemsEnablement(true);
				
				Menu menubar = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getMenuBar();
				MenuItem[] mItems = menubar.getItems();
				for(int i=0; i<mItems.length; i++){
					if(mItems[i].getText().equals("&UseCase")){
						Menu menu =mItems[i].getMenu();
						for(int j=0; j < menu.getItemCount(); j++){
							MenuItem subitem = menu.getItem(j);
							Object menuItemData = subitem.getData();
							if(menuItemData == null)
								continue;
							if(menuItemData instanceof SubContributionItem){
								SubContributionItem cItem = (SubContributionItem) menuItemData;
								IContributionItem conItem = cItem.getInnerItem();
								if(conItem instanceof ActionContributionItem){
									ActionContributionItem item = (ActionContributionItem) conItem;
									item.getAction().setEnabled(true);
								}
							}
							
							subitem.setEnabled(true);
						}
					}
				}
				
			} else if (selectedElement instanceof NFTest){
				TestDetailsEditorOpenAction propertiesOpenAction = new TestDetailsEditorOpenAction();
				propertiesOpenAction.setTest((NFTest)selectedElement);
				propertiesOpenAction.run();
			} else if (selectedElement instanceof GUITest){
				TestDetailsEditorOpenAction propertiesOpenAction = new TestDetailsEditorOpenAction();
				propertiesOpenAction.setTest((GUITest)selectedElement);
				propertiesOpenAction.run();
			} else if (selectedElement instanceof BLTest){
				TestDetailsEditorOpenAction propertiesOpenAction = new TestDetailsEditorOpenAction();
				propertiesOpenAction.setTest((BLTest)selectedElement);
				propertiesOpenAction.run();
			} else if (selectedElement instanceof Notion){
				TestDetailsEditorOpenAction propertiesOpenAction = new TestDetailsEditorOpenAction();
				propertiesOpenAction.setTest((Notion)selectedElement);
				propertiesOpenAction.run();
			}

		} catch (PartInitException e) {
			MessageDialog.openInformation(window.getShell(),
					"Current Software Case Browser", e.toString());
		}
	}

	public void calculateEnabled(ISelection selection) {
		setEnabled(true);
	}

	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		ISelection selection = event.getSelection();
		calculateEnabled(selection);

	}


}