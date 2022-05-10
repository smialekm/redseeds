package notiondiagram.diagram.edit.policies;

import java.util.Iterator;
import java.util.List;

import notiondiagram.Notion;
import notiondiagram.diagram.providers.NotionDiagramElementTypes;

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

import usecasediagram.diagram.part.UseCaseDiagramDiagramEditorPlugin;
import eu.remics.recovery.model.RecoveryModelHelper;

public class DropNotionEditPolicy extends DiagramDragDropEditPolicy {
	
	@Override
	protected Command createViewsAndArrangeCommand(DropObjectsRequest dropRequest, List viewDescriptors) {
		CompoundCommand cm = new CompoundCommand();
		Iterator elements = dropRequest.getObjects().iterator();
		while(elements.hasNext()) {
			Object obj = elements.next();
			if(obj instanceof Notion) {
				Command cmd = getDropElementCommand((Notion)obj, dropRequest);
				if(cmd != null){
					cm.add(cmd);
				}
			}
		}
		return cm;
	}
	
	@Override
	protected Command getDropElementCommand(final EObject element, DropObjectsRequest request) {
		IElementType type = NotionDiagramElementTypes.Notion_2001;    
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
