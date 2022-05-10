package eu.remics.recovery.manager.applogic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.eclipse.jface.action.Action; 
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.part.ViewPart;

@SuppressWarnings("restriction")
public class ActionsFactory {
	
	static Action action;
	static ArrayList<Action> actions = new ArrayList<Action>();
	public static final String ATTACH = "Attach";
	public static final String DELETE = "Delete scenario";
	public static final String CREATE = "Create new Use Case";
	public static final String MERGE = "Merge";
	public static final String MOVE = "Move scenario";
	public static final String ATTACH_UC = "Attach to Use Case";
	public static final String SPLIT = "Split";
	public static final String PREVIEW = "Preview";
	
	/**
	 * Create action
	 */
	public static void createAction(final ViewPart view, final Object classInvokedOn, final Method method, final String title, String cmdID) {
		action = new Action(title) {
			public void run() { 
				try {
					Class<?>[] parameterTypes = method.getParameterTypes();
			
					if(parameterTypes.length == 0){
						@SuppressWarnings("unused")
						Object returnValue = method.invoke(classInvokedOn, (Object[])null);
					}
					else if(parameterTypes.length == 1){
						@SuppressWarnings("unused")
						Object returnValue = method.invoke(classInvokedOn, parameterTypes[0].cast(parameterTypes[0].newInstance()));
					}
					else if(parameterTypes.length == 2){
						@SuppressWarnings("unused")
						Object returnValue = method.invoke(classInvokedOn, parameterTypes[0].cast(parameterTypes[0].newInstance()), parameterTypes[1].cast(parameterTypes[1].newInstance()));
					}
				
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		};
		// The id is used to refer to the action in a menu or toolbar
		action.setId(cmdID);
		action.setEnabled(false);
		
		if(title.equalsIgnoreCase(ATTACH)){
			action.setImageDescriptor(WorkbenchImages.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVE_EDIT));
		}
		else if(title.equalsIgnoreCase(DELETE)){
			action.setImageDescriptor(WorkbenchImages.getImageDescriptor(ISharedImages.IMG_ETOOL_DELETE));
		}
		else if(title.equalsIgnoreCase(CREATE)){
			
		}
        
        actions.add(action);
        
        IToolBarManager toolbarmgr = view.getViewSite().getActionBars().getToolBarManager();
        toolbarmgr.add(action);
	}
	
	public static void enableAction(String name){
		for(Action a : actions){
			if(a.getText().equals(name))
				a.setEnabled(true);
		}
	}
	
	public static void disableAction(String name){
		for(Action a : actions){
			if(a.getText().equals(name))
				a.setEnabled(false);
		}
	}
	
	public void addPropertyListener(final Action action){
		action.addPropertyChangeListener(new IPropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			action.setEnabled(true);
		}
	});
	}
	
}
