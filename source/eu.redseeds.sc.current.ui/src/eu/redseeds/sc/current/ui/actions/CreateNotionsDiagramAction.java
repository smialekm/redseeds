package eu.redseeds.sc.current.ui.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import notiondiagram.NotionDiagram;
import notiondiagram.diagram.part.Messages;
import notiondiagram.diagram.part.NotionDiagramDiagramEditorUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.Constants;
import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;
import eu.remics.util.SCNavigatorHelper;

public class CreateNotionsDiagramAction implements IWorkbenchWindowActionDelegate,
IViewActionDelegate{
	
	protected Resource diagram;
	String diagramName;

	@Override
	public void run(IAction action) {
		
		IProgressMonitor monitor = new NullProgressMonitor();
		final NotionsPackageDTO notionPack = (NotionsPackageDTO)((ITreeSelection)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection()).getFirstElement();
		final int notionPackId = ((NotionsPackage)notionPack).getId();
		
		String pdname = notionPack.getName();
		diagramName = pdname;
		int i = 1;
		while (searchForIdenticalNamesInNotionPackages(notionPack,false)){
			diagramName = pdname+i;
			i++;
		}
		InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(),
	            "Notions Diagram Name", "Enter Notions Diagram name: ", diagramName, null);
		if(dlg.open() == Window.OK){
			if(!dlg.getValue().equals("")){
				diagramName = dlg.getValue();
			}
		}
		else{
			return;
		}
		/**
		 * Checking if diagram name contains invalid characters due to resource name restriction: {/, \, :, *, ", |, ?, <, >, #}
		 */
		for(char c : diagramName.toCharArray()){
			if(c == '<' || c == '>' || c == '/' || c == '\\' 
				|| c == '?' || c == ':' || c == '|' 
					|| c == '*' || c == '"' || c == '#'){
				MessageDialog.openError(Display.getCurrent().getActiveShell(), "Invalid characters in name", "Diagram name contains invalid characters! Invalid characters are: /, \\, :, *, \", |, ?, <, >, #. Please rename it.");
				run(action);
				return;
			}
		}
		/**
		 * Checking if diagram file with this name already exist under the same requirements package
		 */
		ITreeSelection select = (ITreeSelection) SCProjectHelper.getSelection();
		for(Iterator<?> iterator = select.iterator(); iterator.hasNext();) {
			Object elem = (Object)iterator.next();
			if(elem instanceof NotionsPackageDTO){
				ArrayList<IFile> diagrams = ((NotionsPackageDTO)elem).getNotionsDiagrams();
				if(!diagrams.isEmpty()){
					String diagramFileName = diagramName+".notiondiagram_diagram";
					for(IFile obj : diagrams){
						if(diagramName.equals(DiagramFileHelper.getNotionDiagramName(obj.getLocation().toOSString()))){
							MessageDialog.openError(Display.getCurrent().getActiveShell(), "Identical names", "Diagram with identical name already exists under the same notions package! Please rename it.");
							return;
						}
						else if(diagramFileName.equals(obj.getName())){
							MessageDialog.openError(Display.getCurrent().getActiveShell(), "Matching names in model", "Diagram name matches filename of diagram already exists in model! Please rename it.");
							return;
						}
					}
				}
			}
		}
		
		if(searchForIdenticalNamesInNotionPackages(notionPack,true))
			return;
		
		URI diagramModelFileURI = URI.createPlatformResourceURI(getDiagramModelFilePathName(), false);
		URI domainModelFileURI = URI.createPlatformResourceURI(getDomainModelFilePathName(), false);
		
		diagram = NotionDiagramDiagramEditorUtil.createDiagram(
				diagramModelFileURI,
				domainModelFileURI, monitor);
		
		final NotionDiagram notiondiagram = (NotionDiagram) ((Diagram)diagram.getContents().get(0)).getElement();
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(diagram);
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			
			@Override
			protected void doExecute() {
				notiondiagram.setPackage(notionPackId);
				notiondiagram.setName(diagramName);
				try {
					for(Resource res : diagram.getResourceSet().getResources())
						res.save(NotionDiagramDiagramEditorUtil
							.getSaveOptions());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		/**
		 * Open diagram
		 */
		if (diagram != null) {
			try {
				NotionDiagramDiagramEditorUtil.openDiagram(diagram);
			} catch (PartInitException e) {
				ErrorDialog
						.openError(
								new Shell(),
								Messages.NotionDiagramCreationWizardOpenEditorError,
								null, e.getStatus());
			}
		}
		SCNavigatorHelper.refresh();
	}
	
	public String getDiagramModelFilePathName(){
		return "/"+getProjectName()+"/"+Constants.CURRENT_SC_FOLDER_NAME+"/"+Constants.NOTIONSDIAGRAMS_FOLDER_NAME+"/"+diagramName+".notiondiagram_diagram";
	}
	
	public String getDomainModelFilePathName(){
		return "/"+getProjectName()+"/"+Constants.CURRENT_SC_FOLDER_NAME+"/"+Constants.NOTIONSDIAGRAMS_FOLDER_NAME+"/"+diagramName+".notiondiagram";
	}
	
	private String getProjectName() {
		Object elem = ((ITreeSelection)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection()).getFirstElement();
		SCProject scproj = SCProjectContainer.instance().getSCProject(elem);
		return scproj.getName();
	}
	
	public boolean searchForIdenticalNamesInNotionPackages(NotionsPackageDTO notionPack, boolean showMessage){
		List<NotionsPackageDTO> activeProjectNotionPackages = SCProjectContainer.instance().getSCProject(notionPack).getMainCase().getRequirementsSpecificationDTO().getDomainSpecificationDTO().getNotionsPackageDTOList();
		for(NotionsPackageDTO elem : activeProjectNotionPackages){
			ArrayList<IFile> diagrams = elem.getNotionsDiagrams();
			if(!diagrams.isEmpty()){
				String curr = diagramName+".notiondiagram_diagram";
				for(IFile obj : diagrams){
					if(curr.equals(obj.getName())){
						if (showMessage)
							MessageDialog.openError(Display.getCurrent().getActiveShell(), "Matching names in active project", "Diagram unique name must be specified.");
						return true;
					}
				}
			}
		}
		for(NotionsPackageDTO elem : activeProjectNotionPackages){
			if(!elem.getNotionsPackageDTOList().isEmpty()){
				if(!searchByRecursion(elem,showMessage)) continue;
				else{
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean searchByRecursion(NotionsPackageDTO notionPackage, boolean showMessage){
		for(NotionsPackageDTO elem : notionPackage.getNotionsPackageDTOList()){
			ArrayList<IFile> diagrams = elem.getNotionsDiagrams();
			if(!diagrams.isEmpty()){
				String curr = diagramName+".notiondiagram_diagram";
				for(IFile obj : diagrams){
					if(curr.equals(obj.getName())){
						if (showMessage)
							MessageDialog.openError(Display.getCurrent().getActiveShell(), "Matching names in active project", "Diagram unique name must be specified.");
						return true;
					}
				}
			}
		}
		for(NotionsPackageDTO elem : notionPackage.getNotionsPackageDTOList()){
			if(!elem.getNotionsPackageDTOList().isEmpty())
				return searchByRecursion(elem, showMessage);
		}
		return false;
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		
	}

	@Override
	public void init(IViewPart view) {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		
	}

}
