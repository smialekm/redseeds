package eu.redseeds.engine.navigator.providers;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.RetargetAction;

import eu.redseeds.sc.editor.rsl.actions.EditorOpenAction;


public class OpenAction extends RetargetAction {


	private static final String ACTION_ID = "eu.redseeds.engine.navigator.providers.OpenAction";
	private ISelectionProvider provider;
	private EditorOpenAction action;

	//TODO TP:rmv i factory methods are working properly
//	public OpenAction(IViewPart viewPart, ISelectionProvider provider) {
//		super(ACTION_ID, "Open");
//		this.provider = provider;
//		action = new EditorOpenAction(viewPart.getSite());
//		action.calculateEnabled(provider.getSelection());
//		action.setEnabled(true);
//		provider.addSelectionChangedListener(action);
//		setActionHandler(action);
//	}
//	public OpenAction(IViewPart viewPart, ISelectionProvider provider,IProject project,boolean useSelectionFromProvider) {
//		super(ACTION_ID, "Open");
//		this.provider = provider;
//		if(useSelectionFromProvider){
//			action = new EditorOpenAction(viewPart.getSite(),provider.getSelection(),project);
//		}else{
//			action = new EditorOpenAction(viewPart.getSite());
//		}
//		action.calculateEnabled(provider.getSelection());
//		action.setEnabled(true);
//		provider.addSelectionChangedListener(action);
//		setActionHandler(action);
//	}

	private OpenAction(EditorOpenAction editorOpenAction,ISelectionProvider provider){
		super(ACTION_ID, "Open");
		this.provider = provider;
		action = editorOpenAction;
		action.calculateEnabled(provider.getSelection());
		action.setEnabled(true);
		provider.addSelectionChangedListener(action);
		setActionHandler(action);
	}

	/**
	 * Open action which will find object to edit in active page and get project from navigator view
	 * @param viewPart
	 * @param provider SelectionProvider need to add selection changed listener
	 * @return
	 */
	public static OpenAction newInstanceGetSelectionToEditFromActivePage(IViewPart viewPart, ISelectionProvider provider){
		EditorOpenAction editorOpenAction=new EditorOpenAction(viewPart.getSite());
		return new OpenAction(editorOpenAction,provider);
	}

	/**
	 * Open action which will find object to edit in ISelectionProvider
	 * @param viewPart
	 * @param provider ISelectionProvider in which will find object to edit
	 * @param project object to edit is from this IProject
	 * @return
	 */
	public static OpenAction newInstanceGetSelectionToEditFromSelectionProvider(IViewPart viewPart, ISelectionProvider provider,IProject project){
		EditorOpenAction editorOpenAction=new EditorOpenAction(viewPart.getSite(),provider.getSelection(),project);
		return new OpenAction(editorOpenAction,provider);
	}

	@Override
	public void dispose() {
		super.dispose();
		if (provider != null)
			provider.removeSelectionChangedListener(action);
	}

	@Override
	public boolean isEnabled() {
		return action.isEnabled();
	}

}
