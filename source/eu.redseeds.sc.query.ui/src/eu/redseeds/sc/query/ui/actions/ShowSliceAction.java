package eu.redseeds.sc.query.ui.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.dialogs.ListDialog;

import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.engine.navigator.providers.AdapterFactory;
import eu.redseeds.engine.navigator.providers.IProvider;
import eu.redseeds.sc.query.ui.editors.FourTreeView;
import eu.redseeds.sc.slicing.SliceType;
import eu.redseeds.sc.slicing.Slicer;
import eu.redseeds.scl.model.traceability.TraceabilityLinkDTO;
import eu.redseeds.scl.model.traceability.TraceableObjectDTO;

public class ShowSliceAction implements IEditorActionDelegate {

	private FourTreeView editor;

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.editor = (FourTreeView) targetEditor;
	}

	@Override
	public void run(IAction action) {

		SliceType result = selectSlicingTypeDialog();
		if (result == null) {
			return;
		}

		Display.getCurrent().getCursorControl().setCursor(
				Display.getCurrent().getSystemCursor(SWT.CURSOR_WAIT));
		// Receiving selected elements
		IStructuredSelection select = getSelection();
		List<Object> preselectedElements = new ArrayList<Object>();
		List<Object> slicedReqElements = new ArrayList<Object>();
		List<Object> slicedArchElements = new ArrayList<Object>();
		List<Object> slicedArchParents = new ArrayList<Object>();
		List<Object> slicedDDElements = new ArrayList<Object>();

		// slicing
		switch (result) {
		case MAXIMAL_SLICE:
		case MINIMAL_SLICE:
		case IDEAL_SLICE:
		case DOMAIN_INCLUDING_SLICE:
			Set<Vertex> reqElemsSet = new HashSet<Vertex>();
			Set<Vertex> archElemsSet = new HashSet<Vertex>();
			for (Object selectedObject : select.toList()) if (selectedObject instanceof Vertex) {
				reqElemsSet.add((Vertex) selectedObject);
				preselectedElements.add((Vertex) selectedObject);
			}

			Iterable<Vertex> vertices = Slicer
					.computeSlice(reqElemsSet, result).getVertices();
			for (Vertex v : vertices) {
				slicedArchElements.add(v);
				archElemsSet.add(v);
				reqElemsSet.add(v);
				slicedReqElements.add(v);
				slicedDDElements.add(v);
				Object obj = v;
				IProvider provider = AdapterFactory.adapt(obj, null);
				while (provider.getParent(obj) != null) {
					obj = provider.getParent(obj);
					provider = AdapterFactory.adapt(obj, null);
					slicedArchParents.add(obj);
					slicedReqElements.add(obj);
					slicedDDElements.add(obj);
				}
			}

//			vertices = Slicer.computeSlice(archElemsSet, result).getVertices();
//			for (Vertex v : vertices) {
//				slicedDDElements.add(v);
//				Object obj = v;
//				IProvider provider = AdapterFactory.adapt(obj, null);
//				while (provider.getParent(obj) != null) {
//					obj = provider.getParent(obj);
//					provider = AdapterFactory.adapt(obj, null);
//					slicedDDElements.add(obj);
//				}
//			}
//			slicedReqElements.addAll(reqElemsSet);
			break;
		case TRACE_SLICE:
			for (Object selectedObject : select.toList()) {
				if (selectedObject instanceof TraceableObjectDTO) {
					List<TraceabilityLinkDTO> linkList = ((TraceableObjectDTO) selectedObject)
							.getTraceabilityLinkDTOList();

					for (TraceabilityLinkDTO link : linkList) {
						slicedArchElements.add(link.getTarget());
						Object obj = link.getTarget();
						IProvider provider = AdapterFactory.adapt(obj, null);
						while (provider.getParent(obj) != null) {
							obj = provider.getParent(obj);
							provider = AdapterFactory.adapt(obj, null);
							slicedArchParents.add(obj);
						}
					}

				}
			}
			for (Object selectedObject : slicedArchElements) {
				if (selectedObject instanceof TraceableObjectDTO) {
					List<TraceabilityLinkDTO> linkList = ((TraceableObjectDTO) selectedObject)
							.getTraceabilityLinkDTOList();

					for (TraceabilityLinkDTO link : linkList) {
						slicedDDElements.add(link.getTarget());
						Object obj = link.getTarget();
						IProvider provider = AdapterFactory.adapt(obj, null);
						while (provider.getParent(obj) != null) {
							obj = provider.getParent(obj);
							provider = AdapterFactory.adapt(obj, null);
							slicedDDElements.add(obj);
						}
					}

				}
			}
			break;
		default:
			throw new RuntimeException("Unhandeld slice type " + result);
		}

		// Passing into 4-tree view sliced elements and their parrents
		slicedArchElements.addAll(slicedArchParents);
		editor.refresh(slicedReqElements, slicedArchElements,
				slicedDDElements, null, preselectedElements);

		Display.getDefault().getCursorControl().setCursor(
				Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
	}

	protected IStructuredSelection getSelection() {
		return (IStructuredSelection) editor.getSite().getWorkbenchWindow()
				.getSelectionService().getSelection();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
	}

	protected SliceType selectSlicingTypeDialog() {
		ListDialog dialog = new ListDialog(SCProjectHelper.getShell());
		dialog.setTitle("Slicing method selection");
		dialog.setMessage("Select a method:");
		dialog.setInput(SliceType.values());
		dialog.setBlockOnOpen(true);
		dialog.setAddCancelButton(true);
		dialog.setContentProvider(new ArrayContentProvider());
		dialog.setLabelProvider(new LabelProvider());
		dialog.setHelpAvailable(false);
		// dialog.setEmptySelectionMessage("Please select a slicing method");
		if (dialog.open() == Window.OK) {
			if (dialog.getResult() != null) {
				if (dialog.getResult().length > 0) {
					return (SliceType) dialog.getResult()[0];
				}
			}
		}
		return null;
	}

}
