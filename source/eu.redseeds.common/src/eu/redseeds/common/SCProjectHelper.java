package eu.redseeds.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;

public class SCProjectHelper {

	/** current active project setting by select listener so can be access by non-ui thread */
	private static IProject activeProject;
	private static Object diagramRefreshHelper;

	/**
	 * Refreshes SC Navigator
	 */
	public static void refreshSCNavigator() {
		if(((CommonNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().findView("eu.redseeds.engine.navigator.view")) == null)
			return;
		((CommonNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().findView("eu.redseeds.engine.navigator.view"))
				.getCommonViewer().refresh();
	}
	
	/**
	 * Refreshes TS Navigator
	 */
	public static void refreshTSNavigator() {
		if(((CommonNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().findView("eu.redset.navigator.view")) == null)
			return;
		((CommonNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().findView("eu.redset.navigator.view"))
				.getCommonViewer().refresh();
	}

	/**
	 * Refresh whole workspace
	 * @see #refreshActiveProject()
	 */
	public static void refreshWorkspace(){
    	try {
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			Activator.log("Problem with workspace refresh: "+e.getMessage(), IStatus.WARNING);
		}
	}

	/**
	 * Refresh only current active project
	 */
	public static void refreshActiveProject(){
		try {
			ResourcesPlugin.getWorkspace().getRoot().getProject(getActiveProject().getName()).refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			Activator.log("Problem with active project refresh: "+e.getMessage(), IStatus.WARNING);
		}
	}

	public static Shell getShell() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	}

	/**
	 * gets IProject for current selection
	 * @param select
	 * @return
	 */
	public static IProject getIProject(IStructuredSelection select) {
		if(select == null) {
			return null;
		}
		ITreeSelection treeSelection = (ITreeSelection) select;

		TreePath[] tr = treeSelection.getPaths();

		IProject eclipseProject = null;
		if (tr[0].getFirstSegment() instanceof IProject) {
			eclipseProject = (IProject) tr[0].getFirstSegment();
		}

		return eclipseProject;
	}

	public static ISelection getSelection() {
		return ((CommonNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().findView("eu.redseeds.engine.navigator.view"))
				.getSite().getSelectionProvider().getSelection();
	}

	/**
	 * Refreshes SC Navigator
	 */
	public static IViewPart getSCNavigator() {
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
		.getActivePage();
		return null!=activePage?activePage.findView("eu.redseeds.engine.navigator.view"):null;
	}

	/** can be access by non-ui thread */
	public static IProject getActiveProject() {
		return activeProject;
	}

	public static void setActiveProject(IProject currnetActiveProject) {
		SCProjectHelper.activeProject = currnetActiveProject;
	}
	
	/**
	 * helper method for java reflection used in SCProject (to refresh GMF diagrams and UnassignedScenariosView)
	 */
	public static void setDiagramRefreshHelper(Object refreshHelper) {
		diagramRefreshHelper = refreshHelper;
	}
	
	public static Object getDiagramRefreshHelper() {
		return diagramRefreshHelper;
	}
	
	public static void refreshUnassignedScenariosList() {
		Method method = null;
		try {
			method = SCProjectHelper.getDiagramRefreshHelper().getClass().getMethod("refreshUnassignedScenariosList", (Class<?>[])null);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		Object classInvokedOn = SCProjectHelper.getDiagramRefreshHelper();
		try {
			method.invoke(classInvokedOn, (Object[])null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * gets flag which indicates if terminology is used for manage senses or basic form
	 * @param
	 * @return
	 * true - work with senses
	 * false - work with basic form
	 */
	public static boolean getSenseAutoAssigmentFlag(){
		return Activator.getDefault().getPreferenceStore().getBoolean("senseAutoAssigmentFlag");
	}
	
	public static boolean getSenseAutoAddAndAssigmentFlag(){
		return Activator.getDefault().getPreferenceStore().getBoolean("senseAutoAddAndAssigmentFlag");
	}
	
}
