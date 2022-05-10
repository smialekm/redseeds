package eu.redseeds.engine.navigator.dnd;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.engine.navigator.SCCommonViewer;
import eu.redseeds.engine.navigator.SCNavigator;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;

public class SCLElementDragListener extends DragSourceAdapter {
	
	private StructuredViewer viewer;
	
	public SCLElementDragListener(StructuredViewer viewer) {
		this.viewer = viewer;
	}
	
	/**
	 * Method declared on DragSourceListener
	 */
	public void dragFinished(DragSourceEvent event) {
		if (!event.doit)
			return;
		if (event.detail == DND.DROP_MOVE) {
			//save changes
			if(viewer.getSelection() instanceof ITreeSelection){
				
				try{
					ITreeSelection selection = (ITreeSelection) viewer
					.getSelection();
					TreePath[] path = selection.getPaths();
					if(path.length != 0){
						if(path[0] != null) {
							IProject eclipseProject = null;
							if (path[0].getFirstSegment() instanceof IProject) {
								eclipseProject = (IProject) path[0].getFirstSegment();
							}
							SCProject scproject 
							= SCProjectContainer.instance().getSCProject(eclipseProject);
							scproject.save();
						}
					}
				}catch(ClassCastException e){
					e.printStackTrace();
				}
				//refresh the viewer
				viewer.refresh();
			}
			else{
				SCProjectHelper.refreshSCNavigator();
				((SCCommonViewer)((SCNavigator)SCProjectHelper.getSCNavigator()).getCommonViewer()).beenDragged = true;
			}
		}
	}

	/**
	 * Method declared on DragSourceListener
	 */
	public void dragSetData(DragSourceEvent event) {
		IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
		Object[] objects = (Object[]) selection.toArray();
		if (SCLElementTransfer.getInstance().isSupportedType(event.dataType)) {
			event.data = objects;
		}
//		} else if (PluginTransfer.getInstance().isSupportedType(event.dataType)) {
//			byte[] data = GadgetTransfer.getInstance().toByteArray(gadgets);
//			event.data = new PluginTransferData(
//					"org.eclipse.ui.examples.gdt.gadgetDrop", data);
//		}
		//TODO
	}

	/**
	 * Method declared on DragSourceListener
	 */
	public void dragStart(DragSourceEvent event) {
		event.doit = !viewer.getSelection().isEmpty();
		
		IStructuredSelection selection 
			= (IStructuredSelection) viewer.getSelection();
		if(selection.toArray().length > 0) {
			Object source = selection.toArray()[selection.toArray().length-1];
			//check possible types
			event.doit = SCLElementTransfer.getInstance().isSupportedType(source);
		}
	}
}
