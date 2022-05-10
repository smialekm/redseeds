package eu.redseeds.sc.current.ui.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.Constants;
import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.RequirementEditorInput;
import eu.redseeds.sc.editor.rsl.editors.UseCaseEditorInput;
import eu.redseeds.sc.editor.rsl.editors.domain.ActorEditorInput;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditorInput;
import eu.redseeds.sc.editor.rsl.editors.domain.SystemElementEditorInput;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.rsl.rsldomainelements.actors.ActorsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElementsPackage;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsPackage;
import eu.remics.recovery.model.domainlogic.usecases.MUseCase;
import eu.remics.script.loader.applogic.CLoadScripts;

public class DeleteAction implements IWorkbenchWindowActionDelegate,
		IViewActionDelegate {

	private IViewPart navigator;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run(IAction action) {
		IStructuredSelection select = (IStructuredSelection) navigator
				.getViewSite().getSelectionProvider().getSelection();

		MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(),
				SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		if (getSelectedObject(select).size() == 1) {
			confirmMB
					.setMessage("Are you sure you want to delete this element?");
		} else {
			confirmMB
					.setMessage("Are you sure you want to delete these elements?");
		}
		confirmMB.setText("Confirm delete");

		if (confirmMB.open() == SWT.NO) {
			return; // do nothing
		}

		// else proceed with delete
		for (Object selectedObj : getSelectedObject(select)) {
			try {
				closeEditor(selectedObj);
				closeEditorsRecursively(selectedObj);
				if (selectedObj instanceof RequirementDTO) {
					SCProjectHelper.setActiveProject(SCProjectHelper.getIProject(select));
					RequirementDTO req = (RequirementDTO) selectedObj;
					if (req instanceof UseCaseDTO){
						MUseCase.restoreRecoveredScenarios((UseCaseDTO) req);
						new CLoadScripts().vFileSelectorWindow.showUnassignedScenariosListView();
					}
					req.delete();
				} else if (selectedObj instanceof RequirementsPackageDTO) {
					RequirementsPackageDTO reqpack = (RequirementsPackageDTO) selectedObj;
					deleteUseCaseDiagramsFromPackage(reqpack);
					if(((RequirementsPackage)reqpack).isValid()){
						reqpack.deleteRecursively();
					}
				} else if (selectedObj instanceof ActorsPackageDTO) {
					ActorsPackageDTO actpack = (ActorsPackageDTO) selectedObj;
					if(((ActorsPackage)actpack).isValid()){
						actpack.deleteRecursively();
					}
				} else if (selectedObj instanceof ActorDTO) {
					ActorDTO actor = (ActorDTO) selectedObj;
					actor.delete();
				} else if (selectedObj instanceof NotionsPackageDTO) {
					NotionsPackageDTO notpack = (NotionsPackageDTO) selectedObj;
					deleteNotionDiagramsFromPackage(notpack);
					if(((NotionsPackage)notpack).isValid()){
						notpack.deleteRecursively();
					}
				} else if (selectedObj instanceof NotionDTO) {
					NotionDTO notion = (NotionDTO) selectedObj;
					notion.delete();
				} else if (selectedObj instanceof SystemElementsPackageDTO) {
					SystemElementsPackageDTO syselpack = (SystemElementsPackageDTO) selectedObj;
					if(((SystemElementsPackage)syselpack).isValid()){
						syselpack.deleteRecursively();
					}
				} else if (selectedObj instanceof SystemElementDTO) {
					SystemElementDTO sysel = (SystemElementDTO) selectedObj;
					sysel.delete();
				} else if (selectedObj instanceof ClipboardDTO) {
					ClipboardDTO clip = (ClipboardDTO) selectedObj;
					clip.delete();
				} else if (selectedObj instanceof DomainStatementDTO) {
					DomainStatementDTO domStatement = (DomainStatementDTO) selectedObj;
					if (!domStatement.getPhraseDTO().equals(domStatement.getParent().getNamePhrase())) domStatement.delete();
					else MessageDialog.openError(Display.getCurrent().getActiveShell(),
							"Name Phrase", "You cannot delete name phrase!");
				}
				
			} catch (de.uni_koblenz.jgralab.GraphException e) {
				System.err.println("Error while deleting element "
						+ selectedObj);
				e.printStackTrace();
			} finally {
				getSCProject(select).save();
				refresh();
			}
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		navigator = view;
	}

	protected SCProject getSCProject(IStructuredSelection select) {
		return SCProjectContainer.instance().getSCProject(
				SCProjectHelper.getIProject(select));
	}

	protected List<Object> getSelectedObject(IStructuredSelection select) {
		ITreeSelection treeSelection = (ITreeSelection) select;

		List<Object> result = new ArrayList<Object>();
		TreePath[] tr = treeSelection.getPaths();

		for (TreePath element : tr) {
			result.add(element.getLastSegment());
		}
		return result;
	}

	protected void closeEditor(Object elem) {
		IEditorInput input = null;
		if (elem instanceof RequirementDTO) {
			input = new RequirementEditorInput();
			((RequirementEditorInput) input)
					.setRequirementDTO((RequirementDTO) elem);
		}
		if (elem instanceof UseCaseDTO) {
			input = new UseCaseEditorInput();
			((UseCaseEditorInput) input).setUseCaseDTO((UseCaseDTO) elem);
		}
		if (elem instanceof NotionDTO) {
			input = new NotionEditorInput();
			((NotionEditorInput) input).setNotionDTO((NotionDTO) elem);
		}
		if (elem instanceof ActorDTO) {
			input = new ActorEditorInput();
			((ActorEditorInput) input).setActorDTO((ActorDTO) elem);
		}
		if (elem instanceof SystemElementDTO) {
			input = new SystemElementEditorInput();
			((SystemElementEditorInput) input)
					.setSysElemDTO((SystemElementDTO) elem);
		}

		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		if (input != null) {
			IEditorPart editor = page.findEditor(input);
			page.closeEditor(editor, true);
		}
		// IWorkbenchPage page =
		// PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		// IEditorReference[] editorReferences = page.getEditorReferences();
		// for (IEditorReference er : editorReferences){
		// IEditorPart editorToClose = er.getEditor(false);
		// String elemName = editorToClose.getEditorInput().toString();
		// if (elemName.equals(elem.toString())){
		// page.closeEditor(editorToClose, true);
		// }
		// }

	}

	protected void closeEditorsRecursively(Object child) {

		if (child instanceof RequirementsPackageDTO) {
			for (Object r : ((RequirementsPackageDTO) child).getRequirements()) {
				closeEditor(r);
			}
			for (RequirementsPackageDTO rp : ((RequirementsPackageDTO) child)
					.getRequirementsPackages()) {
				closeEditorsRecursively(rp);
			}
		}

		if (child instanceof NotionsPackageDTO) {
			for (NotionDTO n : ((NotionsPackageDTO) child).getNotionDTOList()) {
				closeEditor(n);
			}
			for (NotionsPackageDTO np : ((NotionsPackageDTO) child)
					.getNotionsPackageDTOList()) {
				closeEditorsRecursively(np);
			}
		}

		if (child instanceof ActorsPackageDTO) {
			for (ActorDTO a : ((ActorsPackageDTO) child).getActorDTOList()) {
				closeEditor(a);
			}
			for (ActorsPackageDTO ap : ((ActorsPackageDTO) child)
					.getActorsPackageDTOList()) {
				closeEditorsRecursively(ap);
			}
		}

		if (child instanceof SystemElementsPackageDTO) {
			for (SystemElementDTO s : ((SystemElementsPackageDTO) child)
					.getSystemElementDTOList()) {
				closeEditor(s);
			}
			for (SystemElementsPackageDTO sp : ((SystemElementsPackageDTO) child)
					.getSystemElementsPackageDTOList()) {
				closeEditorsRecursively(sp);
			}
		}

	}

	protected void refresh() {
		SCProjectHelper.refreshSCNavigator();
	}
	
	private void deleteUseCaseDiagramsFromPackage(RequirementsPackageDTO reqpack){

		if(SCProjectHelper.getActiveProject() != null){

			IFolder currSCFolder = SCProjectContainer.instance().getSCProject(reqpack).getEclipseProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			IFolder ucdiagrams = currSCFolder.getFolder(Constants.UCDIAGRAMS_FOLDER_NAME);
			IResource[] ucdiagramsRes = null;
			try {
				ucdiagramsRes = ((IContainer)ucdiagrams).members();
			} catch (CoreException e) {
				e.printStackTrace();
			}

			if(ucdiagramsRes != null){
				for(int i=0; i< ucdiagramsRes.length; i++)
					if(ucdiagramsRes[i] instanceof IFile && ((IFile)ucdiagramsRes[i]).getFileExtension().equalsIgnoreCase("usecasediagram_diagram") && (null==DiagramFileHelper.getUseCaseDiagramId(((IFile)ucdiagramsRes[i]).getLocation().toOSString()) || ((RequirementsPackage)reqpack).getId() == DiagramFileHelper.getUseCaseDiagramId(((IFile)ucdiagramsRes[i]).getLocation().toOSString()))){
						try {
							((IFile)ucdiagramsRes[i]).delete(false, new NullProgressMonitor());
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				
				for(int i=0; i< ucdiagramsRes.length; i++)
					if(ucdiagramsRes[i] instanceof IFile && ((IFile)ucdiagramsRes[i]).getFileExtension().equalsIgnoreCase("usecasediagram") && (null==DiagramFileHelper.getUseCaseDiagramId(((IFile)ucdiagramsRes[i]).getLocation().toOSString()) || ((RequirementsPackage)reqpack).getId() == DiagramFileHelper.getUseCaseDiagramId(((IFile)ucdiagramsRes[i]).getLocation().toOSString()))){
								try {
									((IFile)ucdiagramsRes[i]).delete(false, new NullProgressMonitor());
								} catch (CoreException e) {
									e.printStackTrace();
								}
					}
			}
		}
	}
	
	private void deleteNotionDiagramsFromPackage(NotionsPackageDTO notpack){

		if(SCProjectHelper.getActiveProject() != null){

			IFolder currSCFolder = SCProjectContainer.instance().getSCProject(notpack).getEclipseProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			IFolder notdiagrams = currSCFolder.getFolder(Constants.NOTIONSDIAGRAMS_FOLDER_NAME);
			IResource[] notdiagramsRes = null;
			try {
				notdiagramsRes = ((IContainer)notdiagrams).members();
			} catch (CoreException e) {
				e.printStackTrace();
			}

			if(notdiagramsRes != null){
				for(int i=0; i< notdiagramsRes.length; i++)
					if(notdiagramsRes[i] instanceof IFile && ((IFile)notdiagramsRes[i]).getFileExtension().equalsIgnoreCase("notiondiagram_diagram") && (null == DiagramFileHelper.getNotionDiagramId(((IFile)notdiagramsRes[i]).getLocation().toOSString()) || ((NotionsPackage)notpack).getId() == DiagramFileHelper.getNotionDiagramId(((IFile)notdiagramsRes[i]).getLocation().toOSString()))){
						try {
							((IFile)notdiagramsRes[i]).delete(false, new NullProgressMonitor());
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				
				for(int i=0; i< notdiagramsRes.length; i++)
					if(notdiagramsRes[i] instanceof IFile && ((IFile)notdiagramsRes[i]).getFileExtension().equalsIgnoreCase("notiondiagram") && (null==DiagramFileHelper.getNotionDiagramId(((IFile)notdiagramsRes[i]).getLocation().toOSString()) || ((NotionsPackage)notpack).getId() == DiagramFileHelper.getNotionDiagramId(((IFile)notdiagramsRes[i]).getLocation().toOSString()))){
						try {
							((IFile)notdiagramsRes[i]).delete(false, new NullProgressMonitor());
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
			}
		}
	}
}
