package eu.redseeds.sc.editor.rsl.actions;


import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.Activator;
import eu.redseeds.sc.editor.rsl.editors.RequirementEditor;
import eu.redseeds.sc.editor.rsl.editors.RequirementEditorInput;
import eu.redseeds.sc.editor.rsl.editors.UseCaseEditor;
import eu.redseeds.sc.editor.rsl.editors.UseCaseEditorInput;
import eu.redseeds.sc.editor.rsl.editors.domain.ActorEditor;
import eu.redseeds.sc.editor.rsl.editors.domain.ActorEditorInput;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditor;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditorInput;
import eu.redseeds.sc.editor.rsl.editors.domain.SystemElementEditor;
import eu.redseeds.sc.editor.rsl.editors.domain.SystemElementEditorInput;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.DomainStatementDialog;
import eu.redseeds.sc.editor.rsl.view.CodePreviewView;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.remics.common.RecoveryManagerHelper;

public class EditorOpenAction extends Action implements ISelectionChangedListener{
	private IWorkbenchWindow window;

	private IWorkbenchSite wSite;
	private static String ID_USECASE_EDITOR ="eu.redseeds.sc.editor.rsl.editors.UseCaseEditor";

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


			if(selectedElement instanceof UseCaseDTO) {
				//activePage.openEditor(new UseCaseEditorInput(), ID_USECASE_EDITOR, false);
				UseCaseEditorInput useCaseInput = new UseCaseEditorInput();
				useCaseInput.setUseCaseDTO((UseCaseDTO)selectedElement);
				UseCaseEditor useCaseEditor
					= (UseCaseEditor)activePage.openEditor(useCaseInput, ID_USECASE_EDITOR, false, 1);
				useCaseEditor.setFacade(SCProjectContainer.instance()
						.getSCProject(project).getBusinessLayerFacade());
				useCaseEditor.setScProject(SCProjectContainer.instance()
						.getSCProject(project));
				useCaseEditor.setUseCase((UseCaseDTO) selectedElement);
				//added lines
				useCaseEditor.setFocus();
				useCaseEditor.setItemsEnablement();
			} else if (selectedElement instanceof RequirementDTO) {
				RequirementEditorInput reqInput = new RequirementEditorInput();
				reqInput.setRequirementDTO((RequirementDTO) selectedElement);
				RequirementEditor reqEditor
					= (RequirementEditor)activePage.openEditor(reqInput, RequirementEditor.EDITOR_ID, false, 1);
				reqEditor.setFacade(SCProjectContainer.instance().getSCProject(
						project).getBusinessLayerFacade());
				reqEditor.setScProject(SCProjectContainer.instance()
						.getSCProject(project));
				reqEditor.setRequirement((RequirementDTO) selectedElement);
			} else if (selectedElement instanceof ActorDTO) {
				ActorEditorInput actorInput = new ActorEditorInput();
				actorInput.setActorDTO((ActorDTO) selectedElement);
				ActorEditor actorEditor = (ActorEditor) activePage.openEditor(
						actorInput, ActorEditor.EDITOR_ID, false, 1);
				actorEditor.setActor((ActorDTO) selectedElement);
				actorEditor.setFacade(SCProjectContainer.instance()
						.getSCProject(project).getBusinessLayerFacade());
				actorEditor.setScProject(SCProjectContainer.instance()
						.getSCProject(project));
			} else if (selectedElement instanceof NotionDTO) {
				NotionEditorInput notionInput = new NotionEditorInput();
				notionInput.setNotionDTO((NotionDTO) selectedElement);
				NotionEditor notionEditor = (NotionEditor) activePage
						.openEditor(notionInput, NotionEditor.EDITOR_ID, false, 1);
				notionEditor.setNotion((NotionDTO) selectedElement);
				notionEditor.setFacade(SCProjectContainer.instance()
						.getSCProject(project).getBusinessLayerFacade());
				notionEditor.setScProject(SCProjectContainer.instance()
						.getSCProject(project));
			} else if (selectedElement instanceof SystemElementDTO) {
				SystemElementEditorInput sysElemInput = new SystemElementEditorInput();
				sysElemInput.setSysElemDTO((SystemElementDTO) selectedElement);
				SystemElementEditor sysElemEditor
					= (SystemElementEditor) activePage.openEditor(sysElemInput, SystemElementEditor.EDITOR_ID, false, 1);
				sysElemEditor.setSysElemDTO((SystemElementDTO) selectedElement);
				sysElemEditor.setFacade(SCProjectContainer.instance()
						.getSCProject(project).getBusinessLayerFacade());
				sysElemEditor.setScProject(SCProjectContainer.instance()
						.getSCProject(project));
			} else if (selectedElement instanceof ConstrainedLanguageScenarioDTO) {
				UseCaseEditorInput useCaseInput = new UseCaseEditorInput();
				UseCaseDTO useCase
					= ((ConstrainedLanguageScenarioDTO)selectedElement).getParent();
				useCaseInput.setUseCaseDTO(useCase);
				UseCaseEditor useCaseEditor
					= (UseCaseEditor)activePage.openEditor(useCaseInput, ID_USECASE_EDITOR, false, 1);
				useCaseEditor.setFacade(SCProjectContainer.instance().getSCProject(project).getBusinessLayerFacade());
				useCaseEditor.setScProject(SCProjectContainer.instance().getSCProject(project));
				useCaseEditor.setUseCase(useCase);
				useCaseEditor.setTab((ConstrainedLanguageScenarioDTO)selectedElement);
				//added lines
				useCaseEditor.setFocus();
				useCaseEditor.setItemsEnablement();
				
				/*Menu menubar = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getMenuBar();
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
				}*/
				
			} else if (selectedElement instanceof DomainStatementDTO) {


				NotionEditorInput notionInput = new NotionEditorInput();
				NotionDTO notion = ((DomainStatementDTO)selectedElement).getParent();

				notionInput.setNotionDTO(notion);
				NotionEditor notionEditor = (NotionEditor) activePage
						.openEditor(notionInput, NotionEditor.EDITOR_ID, false, 1);
				notionEditor.setNotion(notion);
				notionEditor.setFacade(SCProjectContainer.instance()
						.getSCProject(project).getBusinessLayerFacade());
				notionEditor.setScProject(SCProjectContainer.instance()
						.getSCProject(project));

				PropertiesOpenAction openPropertyAction = new PropertiesOpenAction();
				openPropertyAction.setProject(SCProjectContainer.instance().getSCProject(selectedElement));
				openPropertyAction.setParent(notionEditor);
				openPropertyAction.setPhraseOrTerm(((DomainStatementDTO)selectedElement).getPhraseDTO());
				openPropertyAction.run();

				DomainStatementDialog dialog = new DomainStatementDialog(SCProjectHelper.getShell());
				dialog.setDomStat((DomainStatementDTO)selectedElement);
				dialog.setScProject(SCProjectContainer.instance().getSCProject(selectedElement));
				dialog.setNotion(notion);
				dialog.open();


			} else if (selectedElement instanceof OperationDTO) {
				Operation op = (Operation) selectedElement;
				if(op.getOwnedCommentList() != null && !op.getOwnedCommentList().isEmpty() && op.getOwnedCommentList().size() == 1){
					RecoveryManagerHelper.hideCodePreviewView();
					RecoveryManagerHelper.showCodePreviewView();
					CodePreviewView codeView = (CodePreviewView) RecoveryManagerHelper.getCodePreviewView();
					codeView.setInput(op);
				}
				
			} else if (selectedElement instanceof ClassDTO) {
				Class umlClass  = (Class) selectedElement;
				RecoveryManagerHelper.hideCodePreviewView();
				RecoveryManagerHelper.showCodePreviewView();
				CodePreviewView codeView = (CodePreviewView) RecoveryManagerHelper.getCodePreviewView();
				codeView.setInput(umlClass);
				
			} else {
				Activator.log("EditorOpenAction: unknown element: "+selectedElement, Status.WARNING);
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