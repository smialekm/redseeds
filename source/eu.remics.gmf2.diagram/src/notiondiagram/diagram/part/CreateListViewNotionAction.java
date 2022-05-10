package notiondiagram.diagram.part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import notiondiagram.diagram.providers.NotionDiagramElementTypes;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

import usecasediagram.diagram.part.UseCaseDiagramDiagramEditorPlugin;

/**
 * @generated NOT
 */
public class CreateListViewNotionAction extends DiagramAction {

	public CreateListViewNotionAction(IWorkbenchPart part) {
		super(part);
	}

	public CreateListViewNotionAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage);
	}

	public void init() {
		super.init();
		setId("addListViewNotionAction");
		setText("Notion (ListView)");
		setToolTipText("Create Notion (ListView)");
		setHoverImageDescriptor(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif"));
		setImageDescriptor(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif"));
		setDisabledImageDescriptor(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif"));
	}

	@Override
	protected Request createTargetRequest() {
		IElementType type = NotionDiagramElementTypes.Notion_2008;
		CreateElementRequest cer = new CreateElementRequest(type);
		cer.setContainer(getDiagramWorkbenchPart().getDiagram().getElement());
		ViewAndElementDescriptor viewDescriptor = new ViewAndElementDescriptor(new CreateElementRequestAdapter(cer), Node.class,((IHintedType) type).getSemanticHint(),UseCaseDiagramDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);    
        return new CreateViewAndElementRequest(viewDescriptor);
	}

	protected void updateTargetRequest() {
		super.updateTargetRequest();
		CreateViewAndElementRequest req = (CreateViewAndElementRequest) getTargetRequest();
		req.getViewAndElementDescriptor().getCreateElementRequestAdapter().setNewElement(null);
		req.setLocation(getMouseLocation());
	}

	@Override
	protected boolean isSelectionListener() {
		return true;
	}
	
	protected void doRun(IProgressMonitor progressMonitor) {
        super.doRun(progressMonitor);
        selectAddedObject();
    }
    
    protected void selectAddedObject() {
        Object result = ((CreateRequest) getTargetRequest()).getNewObject();
        if (!(result instanceof Collection)) {
            return;
        }
        final List editparts = new ArrayList(1);

        IDiagramGraphicalViewer viewer = getDiagramGraphicalViewer();
        if (viewer == null) {
            return;
        }

        Map editpartRegistry = viewer.getEditPartRegistry();
        for (Iterator iter = ((Collection) result).iterator(); iter.hasNext();) {
            Object viewAdaptable = iter.next();
            if (viewAdaptable instanceof IAdaptable) {
                Object editPart = editpartRegistry
                    .get(((IAdaptable) viewAdaptable).getAdapter(View.class));
                if (editPart != null)
                    editparts.add(editPart);
            }
        }

        if (!editparts.isEmpty()) {
            viewer.setSelection(new StructuredSelection(editparts));

            // automatically put the first shape into edit-mode
            Display.getCurrent().asyncExec(new Runnable() {

                public void run() {
                    EditPart editPart = (EditPart) editparts.get(0);
                    editPart.performRequest(new Request(
                        RequestConstants.REQ_DIRECT_EDIT));
                }
            });
        }
    }
	
}
