package eu.redset.tsl.editor.editors;

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

import eu.redset.tsl.editor.Activator;

public class TestScenarioEditorContributor extends
MultiPageEditorActionBarContributor {
	
	private TestScenarioEditor editor;
	
	private IToolBarManager manager;
	private IMenuManager menuManager;
	private IMenuManager menu;

	//private ActionContributionItem IToolBarManagerItem;

	
	public TestScenarioEditorContributor() {
		super();
		createActions();
		
		//createInvokeRequestAction.setEnabled(false);
		//createInvokeRequestActionItem.update();
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
	        this.editor = (TestScenarioEditor)part;
	    }
	
	/* (non-JavaDoc)
	 * Method declared in AbstractMultiPageEditorActionBarContributor.
	 */
	public void setActivePage(IEditorPart part) {
		if (editor == part){
				return;
		}
		if (editor.getActivePageId() > 0){
			setItemsEnablement(true);
		} else {
			setItemsEnablement(false);
		}
	}
	
	
	
	private void createActions() {
		
		
	}

	public void contributeToMenu(IMenuManager manager) {
		this.menuManager = manager;
		menu = new MenuManager("&TestScenario");

		this.menuManager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, menu);
	}
	
	public void contributeToToolBar(IToolBarManager manager) {
		this.manager = manager;			
		this.manager.add(new Separator());

	}
	
	public void setItemsEnablement(boolean enablement){

	}

}
