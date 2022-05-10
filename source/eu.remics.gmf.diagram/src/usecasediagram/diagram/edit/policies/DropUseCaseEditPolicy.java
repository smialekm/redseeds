package usecasediagram.diagram.edit.policies;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramDragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import usecasediagram.Actor;
import usecasediagram.UseCase;
import usecasediagram.diagram.part.UseCaseDiagramDiagramEditorPlugin;
import usecasediagram.diagram.providers.UseCaseDiagramElementTypes;
import eu.remics.recovery.model.RecoveryModelHelper;

public class DropUseCaseEditPolicy extends DiagramDragDropEditPolicy {
	
	@Override
	protected Command createViewsAndArrangeCommand(DropObjectsRequest dropRequest, List viewDescriptors) {
		CompoundCommand cm = new CompoundCommand();
		Iterator elements = dropRequest.getObjects().iterator();
		while(elements.hasNext()) {
			Object obj = elements.next();
			if(obj instanceof UseCase) {
				Command cmd = getDropElementCommand((UseCase)obj, dropRequest);
				if(cmd != null){
					cm.add(cmd);
				}
			}
			else if(obj instanceof Actor) {
				Command cmd = getDropElementCommand((Actor)obj, dropRequest);
				if(cmd != null){
					cm.add(cmd);
				}
			}
		}
		return cm;
	}
	
	@Override
	protected Command getDropElementCommand(final EObject element, DropObjectsRequest request) {
		IElementType type = element instanceof UseCase?UseCaseDiagramElementTypes.UseCase_2002:UseCaseDiagramElementTypes.Actor_2001;    
        CreateElementRequest cer = new CreateElementRequest(type);    
                cer.setContainer(((View) getHost().getModel()).getElement());    
        cer.setNewElement(element);    
          
        ViewAndElementDescriptor viewDescriptor = new ViewAndElementDescriptor(    
                new CreateElementRequestAdapter(cer), Node.class,    
                ((IHintedType) type).getSemanticHint(),    
                UseCaseDiagramDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);    
        
        CreateViewAndElementRequest req = new CreateViewAndElementRequest(    
                viewDescriptor);    
        req.setLocation(RecoveryModelHelper.dispersLocations(request.getLocation(),request.getObjects().indexOf(element),request.getObjects().size()));
        Command c = getHost().getCommand(req);
		return c;
	}
	
}









