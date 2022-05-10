package eu.redseeds.sc.current.ui.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
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

import usecasediagram.UseCaseDiagram;
import usecasediagram.diagram.part.Messages;
import usecasediagram.diagram.part.UseCaseDiagramCreationWizardPage;
import usecasediagram.diagram.part.UseCaseDiagramDiagramEditorUtil;
import eu.redseeds.common.Constants;
import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsPackage;
import eu.remics.util.SCNavigatorHelper;

public class CreateUCDiagramAction implements IWorkbenchWindowActionDelegate,
IViewActionDelegate{
	
	protected Resource diagram;
	protected UseCaseDiagramCreationWizardPage diagramModelFilePage;
	protected UseCaseDiagramCreationWizardPage domainModelFilePage;
	String diagramName;

	public CreateUCDiagramAction() {
	}

	@Override
	public void run(IAction action) {
		
		IProgressMonitor monitor = new NullProgressMonitor();
		final RequirementsPackageDTO reqPack = (RequirementsPackageDTO)((ITreeSelection)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection()).getFirstElement();
		final int reqPackId = ((RequirementsPackage)reqPack).getId();
		
		String pdname = reqPack.getName();
		diagramName = pdname;
		int i = 1;
		while (searchForIdenticalNamesInReqPackages(reqPack,false)){
			diagramName = pdname+i;
			i++;
		}
		InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(),
	            "Use Case Diagram Name", "Enter Use Case Diagram name: ", diagramName, null);
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
			if(elem instanceof RequirementsPackageDTO){
				ArrayList<IFile> diagrams = getUseCaseDiagramsFromPackage((RequirementsPackageDTO)elem);
				if(!diagrams.isEmpty()){
					String diagramFileName = diagramName+".usecasediagram_diagram";
					for(IFile obj : diagrams){
						if(diagramName.equals(DiagramFileHelper.getUseCaseDiagramName(obj.getLocation().toOSString()))){
							MessageDialog.openError(Display.getCurrent().getActiveShell(), "Identical names", "Diagram with identical name already exists under the same requirements package! Please rename it.");
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
		
		if(searchForIdenticalNamesInReqPackages(reqPack,true))
			return;
		
		URI diagramModelFileURI = URI.createPlatformResourceURI(getDiagramModelFilePathName(), false);
		URI domainModelFileURI = URI.createPlatformResourceURI(getDomainModelFilePathName(), false);
		
		diagram = UseCaseDiagramDiagramEditorUtil.createDiagram(
				diagramModelFileURI,
				domainModelFileURI, monitor);
		
		final UseCaseDiagram ucdiagram = (UseCaseDiagram) ((Diagram)diagram.getContents().get(0)).getElement();
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(diagram);
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			
			@Override
			protected void doExecute() {
				ucdiagram.setPackage(reqPackId);
				ucdiagram.setName(diagramName);
				try {
					for(Resource res : diagram.getResourceSet().getResources())
						res.save(usecasediagram.diagram.part.UseCaseDiagramDiagramEditorUtil
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
				UseCaseDiagramDiagramEditorUtil.openDiagram(diagram);
			} catch (PartInitException e) {
				ErrorDialog
						.openError(
								new Shell(),
								Messages.UseCaseDiagramCreationWizardOpenEditorError,
								null, e.getStatus());
			}
		}
		SCNavigatorHelper.refresh();
	}
	
	public String getDiagramModelFilePathName(){
		return "/"+getProjectName()+"/"+Constants.CURRENT_SC_FOLDER_NAME+"/"+Constants.UCDIAGRAMS_FOLDER_NAME+"/"+diagramName+".usecasediagram_diagram";
	}
	
	public String getDomainModelFilePathName(){
		return "/"+getProjectName()+"/"+Constants.CURRENT_SC_FOLDER_NAME+"/"+Constants.UCDIAGRAMS_FOLDER_NAME+"/"+diagramName+".usecasediagram";
	}
	
	private String getProjectName() {
		Object elem = ((ITreeSelection)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection()).getFirstElement();
		SCProject scproj = SCProjectContainer.instance().getSCProject(elem);
		return scproj.getName();
	}
	
	private ArrayList<IFile> getUseCaseDiagramsFromPackage(RequirementsPackageDTO reqpack){

		ArrayList<IFile> ucdiagramsFiles = new ArrayList<IFile>();

		if(SCProjectHelper.getActiveProject() != null){

			IFolder currSCFolder = SCProjectHelper.getActiveProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			IFolder ucdiagrams = currSCFolder.getFolder(Constants.UCDIAGRAMS_FOLDER_NAME);
			IResource[] ucdiagramsRes = null;
			try {
				ucdiagramsRes = ((IContainer)ucdiagrams).members();
			} catch (CoreException e) {
				e.printStackTrace();
			}

			if(ucdiagramsRes != null)
				for(int i=0; i< ucdiagramsRes.length; i++)
					if(ucdiagramsRes[i] instanceof IFile && ((IFile)ucdiagramsRes[i]).getFileExtension().equalsIgnoreCase("usecasediagram_diagram") && null!=DiagramFileHelper.getUseCaseDiagramId(((IFile) ucdiagramsRes[i]).getLocation().toOSString()) && ((RequirementsPackage)reqpack).getId() == DiagramFileHelper.getUseCaseDiagramId(((IFile) ucdiagramsRes[i]).getLocation().toOSString()))
						ucdiagramsFiles.add((IFile)ucdiagramsRes[i]);
			
		}
		return ucdiagramsFiles;
	}
	
	public boolean searchForIdenticalNamesInReqPackages(RequirementsPackageDTO reqPack, boolean showMessage){
		List<RequirementsPackageDTO> activeProjectReqPackages = SCProjectContainer.instance().getSCProject(reqPack).getMainCase().getRequirementsSpecificationDTO().getRequirementsPackagesDTOList();
		for(RequirementsPackageDTO elem : activeProjectReqPackages){
			ArrayList<IFile> diagrams = getUseCaseDiagramsFromPackage(elem);
			if(!diagrams.isEmpty()){
				String curr = diagramName+".usecasediagram_diagram";
				for(IFile obj : diagrams){
					if(curr.equals(obj.getName())){
						if (showMessage)
							MessageDialog.openError(Display.getCurrent().getActiveShell(), "Matching names in active project", "Diagram unique name must be specified.");
						return true;
					}
				}
			}
		}
		for(RequirementsPackageDTO elem : activeProjectReqPackages){
			if(!elem.getRequirementsPackages().isEmpty()){
				if(!searchByRecursion(elem,showMessage)) continue;
				else{
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean searchByRecursion(RequirementsPackageDTO reqPackage, boolean showMessage){
		for(RequirementsPackageDTO elem : reqPackage.getRequirementsPackages()){
			ArrayList<IFile> diagrams = getUseCaseDiagramsFromPackage(elem);
			if(!diagrams.isEmpty()){
				String curr = diagramName+".usecasediagram_diagram";
				for(IFile obj : diagrams){
					if(curr.equals(obj.getName())){
						if (showMessage)
							MessageDialog.openError(Display.getCurrent().getActiveShell(), "Matching names in active project", "Diagram unique name must be specified.");
						return true;
					}
				}
			}
		}
		for(RequirementsPackageDTO elem : reqPackage.getRequirementsPackages()){
			if(!elem.getRequirementsPackages().isEmpty())
				return searchByRecursion(elem,showMessage);
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
