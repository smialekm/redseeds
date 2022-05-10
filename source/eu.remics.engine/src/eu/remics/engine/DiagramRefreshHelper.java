package eu.remics.engine;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import notiondiagram.Notion;
import notiondiagram.NotionDiagram;
import notiondiagram.diagram.edit.parts.PhraseTextEditPart;
import notiondiagram.diagram.part.NotionDiagramDiagramEditor;
import notiondiagram.diagram.part.NotionDiagramDiagramEditorUtil;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;

import usecasediagram.UseCaseDiagram;
import usecasediagram.diagram.part.UseCaseDiagramDiagramEditor;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.manager.views.UnassignedScenariosView;

public class DiagramRefreshHelper {
	
	/**
	 * helper for actually refreshing GMF diagrams
	 */
	public void refresh(){
		if(PlatformUI.getWorkbench().getActiveWorkbenchWindow() == null) return;
		IEditorReference[] editors = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
		for(int i=0; i < editors.length; i++){
			final IEditorPart editor = editors[i].getEditor(true);
			if(editor instanceof UseCaseDiagramDiagramEditor){
				final UseCaseDiagramDiagramEditor uceditor = (UseCaseDiagramDiagramEditor) editor;
				
				boolean isDirty = uceditor.isDirty();
				
				UseCaseDiagram ucdiagram = (UseCaseDiagram) ((org.eclipse.gmf.runtime.notation.Diagram) uceditor.getDiagram().eResource().getContents().get(0)).getElement();
				editPolicyRefresh(ucdiagram);
				
				GraphicalViewer gv = (GraphicalViewer) uceditor.getAdapter(GraphicalViewer.class);
				for(Iterator iter = gv.getEditPartRegistry().keySet().iterator(); iter.hasNext(); ){
					EditPart editpart = (EditPart) gv.getEditPartRegistry().get(iter.next());
					editpart.refresh();
				}
				
				if(!isDirty) {
					TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(uceditor.getDiagram().getDiagram().eResource());
					if(editingDomain == null) return;
					
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							try {
								for(Resource res : uceditor.getEditingDomain().getResourceSet().getResources()){
									if (null!=ResourcesPlugin.getWorkspace().getRoot().findMember(res.getURI().toPlatformString(true)))
									res.save(usecasediagram.diagram.part.UseCaseDiagramDiagramEditorUtil
											.getSaveOptions());
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
			else if(editor instanceof NotionDiagramDiagramEditor){
				final NotionDiagramDiagramEditor notioneditor = (NotionDiagramDiagramEditor) editor;
				
				boolean isDirty = notioneditor.isDirty();
				
				NotionDiagram notiondiagram = (NotionDiagram) ((org.eclipse.gmf.runtime.notation.Diagram) notioneditor.getDiagram().eResource().getContents().get(0)).getElement();
				editPolicyRefresh(notiondiagram);
				
				GraphicalViewer gv = (GraphicalViewer) notioneditor.getAdapter(GraphicalViewer.class);
				for(Iterator iter = gv.getEditPartRegistry().keySet().iterator(); iter.hasNext(); ){
					EditPart editpart = (EditPart) gv.getEditPartRegistry().get(iter.next());
					editpart.refresh();
				}
				
				if(!isDirty){
					TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(notioneditor.getDiagram().getDiagram().eResource());
					if(editingDomain == null) return;
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							try {
								for(Resource res : notioneditor.getEditingDomain().getResourceSet().getResources()){
									if (null!=ResourcesPlugin.getWorkspace().getRoot().findMember(res.getURI().toPlatformString(true)))
									res.save(NotionDiagramDiagramEditorUtil
											.getSaveOptions());
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		}
	}
	
	private void editPolicyRefresh(EObject elem){
		List editPolicies = CanonicalEditPolicy.getRegisteredEditPolicies(elem);
		for (Iterator it = editPolicies.iterator(); it.hasNext();) {
			CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it.next();
			nextEditPolicy.refresh();
		}
		if(elem instanceof NotionDiagram) for (Notion not:((NotionDiagram)elem).getNotions()){
			editPolicies = CanonicalEditPolicy.getRegisteredEditPolicies(not);
			for (Iterator it = editPolicies.iterator(); it.hasNext();) {
				CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it.next();
				nextEditPolicy.refresh();
			}
		}
	}
	
	public void refreshPhrases(){
		if(PlatformUI.getWorkbench().getActiveWorkbenchWindow() == null) return;
		IEditorReference[] editors = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
		for(int i=0; i < editors.length; i++){
			IEditorPart editor = editors[i].getEditor(true);
			if(editor instanceof NotionDiagramDiagramEditor){
				NotionDiagramDiagramEditor noteditor = (NotionDiagramDiagramEditor) editor;
				GraphicalViewer gv = (GraphicalViewer) noteditor.getAdapter(GraphicalViewer.class);
				for(Iterator iter = gv.getEditPartRegistry().keySet().iterator(); iter.hasNext(); ){
					EditPart editpart = (EditPart) gv.getEditPartRegistry().get(iter.next());
					if (editpart instanceof PhraseTextEditPart)
						editpart.refresh();
				}
			}
		}
	}
	
	/**
	 * helper for actually refreshing UnassignedScenariosView
	 */
	public void refreshUnassignedScenariosList() {
		UnassignedScenariosView uslv = (UnassignedScenariosView) RecoveryManagerHelper.getUnassignedScenarioListView();
		if(uslv != null){
			uslv.refresh();
		}
	}
}
