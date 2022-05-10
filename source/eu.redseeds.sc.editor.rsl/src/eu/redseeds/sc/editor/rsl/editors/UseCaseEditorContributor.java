package eu.redseeds.sc.editor.rsl.editors;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.texteditor.ITextEditor;

import eu.redseeds.sc.editor.rsl.Activator;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;

public class UseCaseEditorContributor extends
MultiPageEditorActionBarContributor {
	
	private UseCaseEditor editor;
	private Action forkAction;
	private Action createSVOAction;
	//private Action createInvokeRequestAction;
	private Action  createInvokeInsertAction;	
	private Action  joinAction;
	private Action  finalSuccessAction;
	private Action  finalFailureAction;
	private Action  deleteSentenceAction;
	private Action  deleteScenarioAction;
	private Action  insertionPointAction;
	private Action  addAllDomainElementsAction;
	private IToolBarManager manager;
	private IMenuManager menuManager;
	private IMenuManager menu;
	private ActionContributionItem createSVOActionItem;
	private ActionContributionItem createInvokeInsertActionItem;
	//private ActionContributionItem createInvokeRequestActionItem;
	private ActionContributionItem forkActionItem;
	private ActionContributionItem joinActionItem;
	private ActionContributionItem finalSuccessActionItem;
	private ActionContributionItem finalFailureActionItem;
	private ActionContributionItem deleteSentenceActionItem;
	private ActionContributionItem deleteScenarioActionItem;
	private ActionContributionItem insertionPointActionItem;
	private ActionContributionItem  addAllDomainElementsActionItem;
	//private ActionContributionItem IToolBarManagerItem;

	
	public UseCaseEditorContributor() {
		super();
		createActions();
		this.createSVOActionItem = new ActionContributionItem(createSVOAction);
		this.createInvokeInsertActionItem = new ActionContributionItem(createInvokeInsertAction);
		//this.createInvokeRequestActionItem = new ActionContributionItem(createInvokeRequestAction);
		this.forkActionItem = new ActionContributionItem(forkAction);
		this.joinActionItem = new ActionContributionItem(joinAction);
		this.finalSuccessActionItem = new ActionContributionItem(finalSuccessAction);
		this.finalFailureActionItem = new ActionContributionItem(finalFailureAction);
		this.deleteSentenceActionItem = new ActionContributionItem(deleteSentenceAction);
		this.deleteScenarioActionItem = new ActionContributionItem(deleteScenarioAction);
		this.insertionPointActionItem = new ActionContributionItem(insertionPointAction);
		this.addAllDomainElementsActionItem = new ActionContributionItem(addAllDomainElementsAction);
		createSVOAction.setEnabled(false);
		createSVOActionItem.update();
		createInvokeInsertAction.setEnabled(false);
		createInvokeInsertActionItem.update();
		//createInvokeRequestAction.setEnabled(false);
		//createInvokeRequestActionItem.update();
		forkAction.setEnabled(false);
		forkActionItem.update();
		joinAction.setEnabled(false);
		joinActionItem.update();
		finalSuccessAction.setEnabled(false);
		finalSuccessActionItem.update();
		finalFailureAction.setEnabled(false);
		finalFailureActionItem.update();
		deleteSentenceAction.setEnabled(false);
		deleteSentenceActionItem.update();
		deleteScenarioAction.setEnabled(false);
		deleteScenarioActionItem.update();
		insertionPointAction.setEnabled(false);
		insertionPointActionItem.update();
		addAllDomainElementsAction.setEnabled(false);
		addAllDomainElementsActionItem.update();
	}

	/**
	 * Returns the action registed with the given text editor.
	 * @return IAction or null if editor is null.
	 */
	protected IAction getAction(ITextEditor editor, String actionID) {
		return (editor == null ? null : editor.getAction(actionID));
	}

	
	@Override
	 public void setActiveEditor(IEditorPart part) {
	        this.editor = (UseCaseEditor)part;
	    }
	
	/* (non-JavaDoc)
	 * Method declared in AbstractMultiPageEditorActionBarContributor.
	 */
	public void setActivePage(IEditorPart part) {
		if (editor == part){
				return;
		}
		if (editor.getActivePageId() > 0 && editor.getSelectedPage() instanceof UseCaseScenarioView){
			setItemsEnablement(true);
		} else {
			setItemsEnablement(false);
		}
	}
	
	
	
	private void createActions() {
		
		forkAction = new Action() {
			public void run() {
				editor.getActiveScenarioView().fork();										
			}
			
		};
		forkAction.setText("Fork scenario");//+activeEditorPart.getActive());
		forkAction.setToolTipText("Fork scenario");
		forkAction.setImageDescriptor(ImageDescriptor
				.createFromURL(Platform.getBundle(Activator.PLUGIN_ID)
				.getEntry("icons/fork.png")));
		forkAction.setEnabled(false);
		
		createSVOAction = new Action() {
			public void run() {
				editor.getActiveScenarioView().createSVOSentence();								
			}
			
		};
		createSVOAction.setText("Create SVO sentence");//+activeEditorPart.getActive());
		createSVOAction.setToolTipText("Create SVO sentence");
		createSVOAction.setImageDescriptor(ImageDescriptor
				.createFromURL(Platform.getBundle(Activator.PLUGIN_ID)
				.getEntry("icons/svo.png")));
		createSVOAction.setEnabled(false);
		
		createInvokeInsertAction = new Action() {
			public void run() {				
				editor.getActiveScenarioView().createInvokeInsertSentence();								
			}
			
		};
		createInvokeInsertAction.setText("Create invoke/insert sentence");//+activeEditorPart.getActive());
		createInvokeInsertAction.setToolTipText("Create invoke/insert sentence");
		createInvokeInsertAction.setImageDescriptor(ImageDescriptor
				.createFromURL(Platform.getBundle(Activator.PLUGIN_ID)
				.getEntry("icons/invoke_insert.png")));
		createInvokeInsertAction.setEnabled(false);
		
		/*createInvokeRequestAction = new Action() {
			public void run() {
				editor.getActiveScenarioView().createInvokeRequestSentence();								
			}
			
		};
		createInvokeRequestAction.setText("Create invoke/request");//+activeEditorPart.getActive());
		createInvokeRequestAction.setToolTipText("Create invoke/request sentence");
		createInvokeRequestAction.setImageDescriptor(ImageDescriptor
				.createFromURL(Platform.getBundle(Activator.PLUGIN_ID)
				.getEntry("icons/invoke_request.png")));
		*/
		joinAction = new Action() {
			public void run() {
				editor.getActiveScenarioView().createRejoinSentnece();								
			}
			
		};
		joinAction.setText("Create rejoin sentence");//+activeEditorPart.getActive());
		joinAction.setToolTipText("Create rejoin sentence");
		joinAction.setImageDescriptor(ImageDescriptor
				.createFromURL(Platform.getBundle(Activator.PLUGIN_ID)
				.getEntry("icons/join.png")));
		
		finalSuccessAction = new Action() {
			public void run() {
				editor.getActiveScenarioView().createFinalSuccess();								
			}
			
		};
		finalSuccessAction.setText("Create final/success sentence");
		finalSuccessAction.setToolTipText("Create final/success sentence");
		finalSuccessAction.setImageDescriptor(ImageDescriptor
				.createFromURL(Platform.getBundle(Activator.PLUGIN_ID)
				.getEntry("icons/final_success.png")));
		
		finalFailureAction = new Action() {
			public void run() {
				editor.getActiveScenarioView().createFinalFailure();								
			}
			
		};
		finalFailureAction.setText("Create final/failure sentence");
		finalFailureAction.setToolTipText("Create final/failure sentence");
		finalFailureAction.setImageDescriptor(ImageDescriptor
				.createFromURL(Platform.getBundle(Activator.PLUGIN_ID)
				.getEntry("icons/final_failure.png")));
		
		
		deleteSentenceAction = new Action() {
			public void run() {
				editor.getActiveScenarioView().deleteSentence();								
			}
			
		};
		deleteSentenceAction.setText("Delete sentence");
		deleteSentenceAction.setToolTipText("Delete sentence");
		deleteSentenceAction.setImageDescriptor(ImageDescriptor
				.createFromURL(Platform.getBundle(Activator.PLUGIN_ID)
				.getEntry("icons/del_svo.png")));
		
		deleteScenarioAction = new Action() {
			public void run() {
				editor.getActiveScenarioView().deleteScenario();								
			}
			
		};
		deleteScenarioAction.setText("Delete scenario");
		deleteScenarioAction.setToolTipText("Delete scenario");
		deleteScenarioAction.setImageDescriptor(ImageDescriptor
				.createFromURL(Platform.getBundle(Activator.PLUGIN_ID)
				.getEntry("icons/del_scen.png")));
		
		insertionPointAction = new Action() {
			public void run() {
				editor.getActiveScenarioView().createInvokeSentenceAsInsertionPoint();								
			}
			
		};
		insertionPointAction.setText("Create ALP Insertion Point");
		insertionPointAction.setToolTipText("Create ALP Insertion Point");
		insertionPointAction.setImageDescriptor(ImageDescriptor
				.createFromURL(Platform.getBundle(Activator.PLUGIN_ID)
				.getEntry("icons/alp_insertion.png")));
		addAllDomainElementsAction = new Action(){
			
			public void run() {
				if (RemoteJGWNL.getInstance().isConnected())
					editor.getActiveScenarioView().addAllDomainElements();
			}
			
		};
		addAllDomainElementsAction.setText("Add all domain elements");
		addAllDomainElementsAction.setToolTipText("Add all domain elements");
		addAllDomainElementsAction.setImageDescriptor(ImageDescriptor
				.createFromURL(Platform.getBundle(Activator.PLUGIN_ID)
						.getEntry("icons/add_all.png")));
	}

	public void contributeToMenu(IMenuManager manager) {
		this.menuManager = manager;
		menu = new MenuManager("&UseCase");
		menu.add(createSVOAction);
		menu.add(createInvokeInsertAction);
		//menu.add(createInvokeRequestAction);
		menu.add(forkAction);
		menu.add(joinAction);
		menu.add(finalSuccessAction);
		menu.add(finalFailureAction);
		menu.add(deleteSentenceAction);
		menu.add(deleteScenarioAction);
		menu.add(insertionPointAction);
		menu.add(addAllDomainElementsAction);
		this.menuManager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, menu);
	}
	
	public void contributeToToolBar(IToolBarManager manager) {
		this.manager = manager;			
		this.manager.add(new Separator());
		this.manager.add(createSVOActionItem);
		this.manager.add(createInvokeInsertActionItem);
		//this.manager.add(createInvokeRequestActionItem);
		this.manager.add(forkActionItem);
		this.manager.add(joinActionItem);
		this.manager.add(finalSuccessAction);
		this.manager.add(finalFailureAction);
		this.manager.add(deleteSentenceAction);
		this.manager.add(deleteScenarioAction);
		this.manager.add(insertionPointAction);
		this.manager.add(addAllDomainElementsAction);
	}
	
	public void setItemsEnablement(boolean enablement){
		createSVOAction.setEnabled(enablement);
		createInvokeInsertAction.setEnabled(enablement);
		//createInvokeRequestAction.setEnabled(enablement);
		forkAction.setEnabled(enablement);
		joinAction.setEnabled(enablement);
		finalSuccessAction.setEnabled(enablement);
		finalFailureAction.setEnabled(enablement);
		deleteSentenceAction.setEnabled(enablement);
		deleteScenarioAction.setEnabled(enablement);
		insertionPointAction.setEnabled(enablement);
		addAllDomainElementsAction.setEnabled(enablement);
		createSVOActionItem.update();
		createInvokeInsertActionItem.update();
		//createInvokeRequestActionItem.update();
		forkActionItem.update();	
		joinActionItem.update();	
		finalSuccessActionItem.update();	
		finalFailureActionItem.update();	
		deleteSentenceActionItem.update();
		deleteScenarioActionItem.update();
		insertionPointActionItem.update();
		addAllDomainElementsActionItem.update();
	}

}
