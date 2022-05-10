package usecasediagram.diagram.dnd;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramDropTargetListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import usecasediagram.Actor;
import usecasediagram.UseCase;
import usecasediagram.UsecasediagramFactory;
import usecasediagram.diagram.part.UseCaseDiagramDiagramEditor;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;

public class DiagramDragDropListenerProvider extends DiagramDropTargetListener {
	
	public DiagramDragDropListenerProvider(EditPartViewer viewer, Transfer xfer) {
		super(viewer, xfer);
	}

	@Override
	protected List<?> getObjectsBeingDropped() {
		LinkedList<UseCase> usecases = new LinkedList<UseCase>();
		LinkedList<Actor> actors = new LinkedList<Actor>();
		ITreeSelection selection = (ITreeSelection) SCProjectHelper.getSelection();
		
		for(Iterator<?> iterator = selection.iterator(); iterator.hasNext() ;){
			Object obj = (Object) iterator.next();
			if(obj instanceof UseCaseDTO){
				UseCaseDTO uc = (UseCaseDTO) obj;
				UseCase newElement = UsecasediagramFactory.eINSTANCE.createUseCase();
				newElement.setId(((RSLUseCase) uc).getId());
				
				usecases.add(newElement);
			}
			else if(obj instanceof ActorDTO){
				ActorDTO actor = (ActorDTO) obj;
				Actor newElement = UsecasediagramFactory.eINSTANCE.createActor();
				newElement.setId(((eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) actor).getId());
				
				actors.add(newElement);
			}
		}
		return usecases.isEmpty() ? actors : usecases;
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
		if(editor instanceof UseCaseDiagramDiagramEditor){
			
			Resource res = ((UseCaseDiagramDiagramEditor) editor).getDiagram().getDiagram().eResource();
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
