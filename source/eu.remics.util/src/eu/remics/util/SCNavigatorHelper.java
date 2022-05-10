package eu.remics.util;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import eu.redseeds.engine.navigator.SCCommonViewer;
import eu.redseeds.engine.navigator.SCNavigator;
import eu.remics.common.Constans;

public class SCNavigatorHelper {
	/*
	 * Helper class providing utility for handling navigator.
	 * Created in response to eliminate loop cycle in plugins overall.
	 */
	public static SCNavigator getSCNavigator(){
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if(activePage == null) return null;
		return (SCNavigator) activePage.findView(Constans.SCNavigatorID);
	}
	
	public static Object getInput(){
		return getSCNavigator().getCommonViewer().getInput();
	}
	
	public static void setInput(){
		getSCNavigator().getCommonViewer().setInput(getInput());
	}
	
	public static SCCommonViewer getViewer(){
		if(getSCNavigator() != null){
			return (SCCommonViewer) getSCNavigator().getCommonViewer();
		}
		return null;
	}
	
	public static void refresh(){
		getViewer().refresh();
	}
}
