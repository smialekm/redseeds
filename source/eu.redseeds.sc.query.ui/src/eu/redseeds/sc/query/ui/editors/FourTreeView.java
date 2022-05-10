package eu.redseeds.sc.query.ui.editors;

import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
//import org.eclipse.ui.texteditor.ITextEditor;

import eu.redseeds.engine.navigator.SCProjectSorter;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.sclkernel.ArchitecturalModelDTO;
import eu.redseeds.scl.model.sclkernel.DetailedDesignModelDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseArtifact;

public class FourTreeView extends EditorPart {

//	ITextEditor editor;
	private TreeViewer reqViewer, archViewer, ddViewer;
	private TreeViewContentProvider
		reqContentProvider, archContentProvider, detDesignContentProvider;

	private List<Object> preSelectedObjectsList;

	public FourTreeView() {
		super();
	}

	public void dispose() {
		super.dispose();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {

		setSite(site);
		setInput(input);
		if(input instanceof FourTreeViewInput) {
			preSelectedObjectsList = ((FourTreeViewInput)input).getPreSelectedObjectsList();

//			setPartName(((FourTreeViewInput)input).getPath().getLastSegment().toString());
			setPartName(((FourTreeViewInput)input).getPath().getFirstSegment().toString());
			TreePath treePath = ((FourTreeViewInput)input).getPath();
			String path="";
			for (int i=0; i<treePath.getSegmentCount(); i++){
				path=path+treePath.getSegment(i).toString();
				if (i<treePath.getSegmentCount()-1){
					path=path+".";
				}
			}
			setContentDescription(path);
		}
		else {
			throw new PartInitException("Invalid Input: Must be FourTreeViewInput");
		}
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {

		reqViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		RequirementsSpecificationDTO reqModel = (RequirementsSpecificationDTO)((FourTreeViewInput)this.getEditorInput()).getSoftwareCase().getRequirementsSpecificationDTO();
		reqContentProvider = new TreeViewContentProvider(reqModel);
		reqViewer.setContentProvider(reqContentProvider);
		reqViewer.setLabelProvider(reqContentProvider);
		reqViewer.setInput(reqModel);
		reqViewer.setSorter(new SCProjectSorter());
		getSite().setSelectionProvider(reqViewer);
		setPreselection();

		archViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		ArchitecturalModelDTO archModel = ((FourTreeViewInput)this.getEditorInput()).getSoftwareCase().getArchitecturalModelDTO();
		archContentProvider = new TreeViewContentProvider((SoftwareCaseArtifact)archModel);
		archViewer.setContentProvider(archContentProvider);
		archViewer.setLabelProvider(archContentProvider);
		archViewer.setInput(archModel);
		archViewer.setSorter(new SCProjectSorter());

		ddViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		DetailedDesignModelDTO ddModel = ((FourTreeViewInput)this.getEditorInput()).getSoftwareCase().getDetailedDesignModelDTO();
		detDesignContentProvider = new TreeViewContentProvider((SoftwareCaseArtifact)ddModel);
		ddViewer.setContentProvider(detDesignContentProvider);
		ddViewer.setLabelProvider(detDesignContentProvider);
		ddViewer.setInput(detDesignContentProvider);
		ddViewer.setSorter(new SCProjectSorter());
	}

	@Override
	public void setFocus() {
		reqViewer.getControl().setFocus();
	}

	public void refresh(List<Object> requirements, List<Object> architecture,
			List<Object> design, List<Object> code, List<Object> preSelected) {
		reqViewer.setLabelProvider(new FourTreeViewDecoratingLabelProvider(
				reqContentProvider, null, requirements, preSelected, 0));
		archViewer.setLabelProvider(new FourTreeViewDecoratingLabelProvider(
				archContentProvider, null, architecture, preSelected, 1));
		ddViewer.setLabelProvider(new FourTreeViewDecoratingLabelProvider(
				detDesignContentProvider, null, design, preSelected, 2));
//		codeViewer.setLabelProvider(new FourTreeViewDecoratingLabelProvider(
//				new FourTreeViewLabelProvider(), null, code, 3));
	}

	/**
	 * handles pre-selecting elements in viewers
	 */
	protected void setPreselection() {
		if(preSelectedObjectsList != null) {
			if(preSelectedObjectsList.size() > 0) {
				reqViewer.expandAll();//TODO - expand one-by-one as needed
			}
			IStructuredSelection preSelection = new StructuredSelection(preSelectedObjectsList);
			reqViewer.setSelection(preSelection);
			for(Object obj : preSelectedObjectsList) {
				reqViewer.reveal(obj);
			}
		}
	}
}
