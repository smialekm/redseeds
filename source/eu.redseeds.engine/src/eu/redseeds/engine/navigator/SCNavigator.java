package eu.redseeds.engine.navigator;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.sclkernel.SCLElement;

//import eu.redseeds.engine.navigator.actions.CutSCLElementAction;
//import eu.redseeds.engine.navigator.actions.PasteSCLElementAction;
//import org.eclipse.ui.IActionBars;
//import org.eclipse.ui.actions.ActionFactory;
//import org.eclipse.swt.dnd.Clipboard;

public class SCNavigator extends CommonNavigator {

	// private Clipboard clipboard;

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		this.getCommonViewer().setSorter(new SCProjectSorter());
		getSite().setSelectionProvider(getCommonViewer());
	}

	/*
	 * public void refresh() { this.getCommonViewer().refresh(); }
	 */

	/*
	 * overriden
	 */
	@Override
	protected CommonViewer createCommonViewer(Composite aParent) {
		SCCommonViewer aViewer = new SCCommonViewer(getViewSite().getId(), aParent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		initListeners(aViewer);
		aViewer.addTreeListener(new ITreeViewerListener() {
			
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				Object expandedElement = event.getElement();
				if(expandedElement instanceof SCLElement){
					if(SCProjectHelper.getActiveProject() == null){
						SCProjectHelper.setActiveProject(SCProjectContainer.instance().getSCProject(expandedElement).getEclipseProject());
					}
					else{
						if(SCProjectContainer.instance().getSCProject(expandedElement) != null){
							if(SCProjectHelper.getActiveProject()
									.equals(SCProjectContainer.instance().getSCProject(expandedElement).getEclipseProject())){
								return;
							}
							SCProjectHelper.setActiveProject(SCProjectContainer.instance().getSCProject(expandedElement).getEclipseProject());
						}
					}
				}
			}
			
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
			}
		});

		// cut-copy
		// clipboard = new Clipboard(getSite().getShell().getDisplay());
		// IActionBars bars = getViewSite().getActionBars();
		// bars.setGlobalActionHandler(ActionFactory.CUT.getId(),
		// new CutSCLElementAction(aViewer, clipboard));
		// bars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
		// new PasteSCLElementAction(aViewer, clipboard));

		// HACK: not sure if it still works...
		// aViewer.getNavigatorContentService().restoreState(memento);

		// add change listener to track current project change
		addProjectSelectListener();
		return aViewer;
	}

	private void addProjectSelectListener() {
		ProjectSelectListener.addToPostSelectionListener(this);
	}

	private void rmvProjectSelectListener() {
		ProjectSelectListener.rmvPostSelectionListener(this);
	}
	
	/**
	 * This listener track and save current active project
	 */
	public static class ProjectSelectListener implements ISelectionListener {

		private static ProjectSelectListener thisInstance;

		private ProjectSelectListener() {
		}

		public static synchronized void addToPostSelectionListener(IWorkbenchPart workbenchPart) {
			if (thisInstance == null) {
				thisInstance = new ProjectSelectListener();
				workbenchPart.getSite().getWorkbenchWindow().getSelectionService().addPostSelectionListener(thisInstance);
			}
		}

		public static synchronized void rmvPostSelectionListener(IWorkbenchPart workbenchPart) {
			if (thisInstance != null) {
				workbenchPart.getSite().getWorkbenchWindow().getSelectionService().removePostSelectionListener(thisInstance);
				thisInstance = null;
			}
		}

		@Override
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			processSelection(part, selection);

		}

		public static void processSelection(IWorkbenchPart sourcepart, ISelection selection) {
			if (selection instanceof ITreeSelection) {
				ITreeSelection treeSelection = (ITreeSelection) selection;
				TreePath[] treePaths = treeSelection.getPaths();
				if (treePaths != null && treePaths.length > 0) {
					TreePath treePath = treePaths[0];
					if (treePath != null) {
						int segmentCount = treePath.getSegmentCount();
						for (int i = 0; i < segmentCount; i++) {
							if (treePath.getSegment(i) instanceof IProject) {
								IProject project = (IProject) treePath.getSegment(i);
								SCProjectHelper.setActiveProject(project);
//								Activator.log("Current active project name is: " + SCProjectHelper.getActiveProject().getName(), IStatus.INFO);
								break;
							}
						}
					}
				}

			}
		}

	}

}
