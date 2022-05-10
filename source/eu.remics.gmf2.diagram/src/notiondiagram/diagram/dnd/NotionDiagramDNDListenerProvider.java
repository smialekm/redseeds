package notiondiagram.diagram.dnd;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import notiondiagram.Notion;
import notiondiagram.NotiondiagramFactory;
import notiondiagram.diagram.part.NotionDiagramDiagramEditor;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramDropTargetListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;

public class NotionDiagramDNDListenerProvider extends DiagramDropTargetListener {
	
	public NotionDiagramDNDListenerProvider(EditPartViewer viewer, Transfer xfer) {
		super(viewer, xfer);
	}

	@Override
	protected List<?> getObjectsBeingDropped() {
		LinkedList<Notion> notions = new LinkedList<Notion>();
		ITreeSelection selection = (ITreeSelection) SCProjectHelper.getSelection();
		
		for(Iterator<?> iterator = selection.iterator(); iterator.hasNext() ;){
			Object obj = (Object) iterator.next();
			if(obj instanceof NotionDTO){
				NotionDTO notion = (NotionDTO) obj;
				Notion newElement = NotiondiagramFactory.eINSTANCE.createNotion();
				newElement.setId(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion) notion).getId());
				
				notions.add(newElement);
			}
		}
		return notions;
	}
	
	@Override
	protected void handleDragOver() {
		updateTargetRequest();
		updateTargetEditPart();
		showTargetFeedback();
	}
	
	@Override
	public boolean isEnabled(DropTargetEvent event) {
		
		if(!checkTargetAndSourceInSameSCProject())
			return false;
		
		return super.isEnabled(event);
	}
	
	private boolean checkTargetAndSourceInSameSCProject() {
		IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if(editor instanceof NotionDiagramDiagramEditor){
			
			Resource res = ((NotionDiagramDiagramEditor) editor).getDiagram().getDiagram().eResource();
			String[] pathSegments = res.getURI().segments();
			String projectNameContainsEditor = pathSegments[1];
			for(SCProject scproj : SCProjectContainer.instance().getSCProjects()){
				String scname = scproj.getName().replaceAll("\\s", "%20");
				if(scname.equals(projectNameContainsEditor)){
					ITreeSelection selection = (ITreeSelection) SCProjectHelper.getSelection();
					for(Iterator<?> iterator = selection.iterator(); iterator.hasNext() ;){
						Object obj = (Object) iterator.next();
						if(scproj.equals(SCProjectContainer.instance().getSCProject(obj))){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
