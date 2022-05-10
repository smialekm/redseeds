package eu.redseeds.sc.query.ui.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.query.ui.dialogs.SCSliceListDialog;
import eu.redseeds.sc.query.ui.editors.FourTreeView;
import eu.redseeds.sc.slicing.SliceType;
import eu.redseeds.sc.slicing.Slicer;

public class ImportSliceAction implements IEditorActionDelegate {
			
	private FourTreeView editor; 
	
	@Override
	public void run(IAction action) {
		IStructuredSelection select = (IStructuredSelection) editor.getSite()
				.getWorkbenchWindow().getSelectionService().getSelection();
		
		if(select.getFirstElement() == null) {
			return;
		}
		
		SCProject currProject = SCProjectContainer.instance().getSCProject(
				select.getFirstElement());
		
		List<String> projectNamesToShow = new ArrayList<String>();
		for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			if(!project.getName().equalsIgnoreCase(currProject.getEclipseProject().getName())) {
				projectNamesToShow.add(project.getName());
			}
		}
		
		SCSlicingDescriptor targetSC = showSelectSCDialog(projectNamesToShow);
		
		if(targetSC == null) {
			return;
		}
		
		Set<Vertex> reqElemsSet = new HashSet<Vertex>();
		for (Object selectedObject : select.toList()) if (selectedObject instanceof Vertex) {
			reqElemsSet.add((Vertex) selectedObject);
		}
		
		Display.getCurrent().getCursorControl().setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_WAIT));
		
		
		try {
			for (IProject targetProject : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
				if (targetProject.getName().equalsIgnoreCase(targetSC.getScName())) {
					SCProjectContainer.instance().getSCProject(targetProject)
						.importSlice(Slicer.computeSlice(reqElemsSet, targetSC.getSlicingMethod())); 
				}
			}
		} catch (RuntimeException e) {
			eu.redseeds.sc.query.ui.Activator.log("Error occurred during slice import", IStatus.ERROR);
			Display.getDefault().getCursorControl().setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_ERROR);
			mb.setText("Slice import");
			mb.setMessage("Slice import failed. Check error log for more details.");
			mb.open();
			return;
		} 
		
		Display.getDefault().getCursorControl().setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
		MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION);
		mb.setText("Slice import");
		mb.setMessage("Slice imported successfully.");
		mb.open();
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}
	
	protected List<Object> getSelectedObject(IStructuredSelection select) {
		ITreeSelection treeSelection = (ITreeSelection) select;

		List<Object> result = new ArrayList<Object>();
		TreePath[] tr = treeSelection.getPaths();

		for (int i = 0; i < tr.length; i++) {
			result.add(tr[i].getLastSegment());
		}
		return result;
	}

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.editor = (FourTreeView)targetEditor;
		
	}
	

	/**
	 * opens list dialog with SCs available in workspace (except for the current one shown in tree view) and slicing method selection
	 * 
	 * @param projectNamesToShow - string list of names to show
	 * @return
	 * name of selected SC case (if user selected one and pressed ok) or null (no selection or cancel pressed)
	 */
	protected SCSlicingDescriptor showSelectSCDialog(final List<String> projectNamesToShow) {
		
		if(projectNamesToShow == null || projectNamesToShow.size() == 0) {
			return null;
		}
		
		SCSliceListDialog ld = new SCSliceListDialog(SCProjectHelper.getShell(), projectNamesToShow);
		
		ld.setAddCancelButton(true);
		ld.setContentProvider(new ArrayContentProvider());
		ld.setLabelProvider(new LabelProvider());
		ld.setInput(projectNamesToShow);
		ld.setBlockOnOpen(true);
		ld.setHelpAvailable(false);
		ld.setTitle("Import a slice to a Software Case:");
		int result = ld.open();
		if(result == Window.OK) {
			if(ld.getResult() != null) {
				if(ld.getResult().length > 0) {
					return new SCSlicingDescriptor(ld.getResult()[0].toString(), ld.getSlicingMethod());
				}
			}
		}	
		return null;
	}
	
	/**
	 * A container for slicing information
	 * 	 *
	 */
	public class SCSlicingDescriptor {
		String scName;
		SliceType slicingMethod;
		
		/**
		 * @param scName SC name
		 *  
		 * @param slicingMethod slicing method
		 */
		public SCSlicingDescriptor(String scName, SliceType slicingMethod) {
			this.scName = scName;
			this.slicingMethod = slicingMethod;
		}
		
		public String getScName() {
			return scName;
		}

		public SliceType getSlicingMethod() {
			return slicingMethod;
		}

	}
	
	
}
