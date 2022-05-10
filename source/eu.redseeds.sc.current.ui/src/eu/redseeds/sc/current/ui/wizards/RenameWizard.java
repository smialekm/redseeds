package eu.redseeds.sc.current.ui.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbench;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.current.ui.Activator;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounLinkDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;

public class RenameWizard extends Wizard {
	
	protected RenameWizardPage page;
	protected RenameWizardPageSenses page2;
	protected RenameWizardPageResolveProblems page3;
	private ISelection selection;
	
	protected String newElementName;

	public String getNewElementName() {
		return newElementName;
	}

	public void setNewElementName(String newElementName) {
		this.newElementName = newElementName;
	}

	@Override
	public boolean performFinish() {
		try {
			if (selection != null) {
				
				ITreeSelection treeSelection = (ITreeSelection) selection;
				
				TreePath[] tr = treeSelection.getPaths();
				
				if(tr[0] != null) {
					IProject eclipseProject = null;
					Object selectedObj = tr[0].getLastSegment();
					if (tr[0].getFirstSegment() instanceof IProject){
						eclipseProject = (IProject)tr[0].getFirstSegment();
					} else {
						return false;
					}
					if(selectedObj == null) {
						return false;
					}
					if(selectedObj instanceof NotionDTO) {
						if(page3 != null) {
							getContainer().run(false, true,
									new RenameOperation(page2.getNamePhrase(), page3.getNonBasicForms(), selectedObj, eclipseProject));
						}
						else {
							getContainer().run(false, true,
									new RenameOperation(page2.getNamePhrase(), new ArrayList<NounLinkDTO>(0), selectedObj, eclipseProject));
						}
					}
					else if(selectedObj instanceof ActorDTO || selectedObj instanceof SystemElementDTO) {
						getContainer().run(false, true,
									new RenameOperation(page2.getNamePhrase(), selectedObj, eclipseProject));
					}
					else {
						getContainer().run(false, true,
								new RenameOperation(page.getNewElementName(), selectedObj, eclipseProject));
					}
					SCProjectHelper.refreshSCNavigator();
					
					return true;
				}
				else return false;
			}
		} catch (InvocationTargetException e) {
			Activator.log(e.getMessage(), Status.ERROR); 
		} catch (InterruptedException e) {
			Activator.log(e.getMessage(), Status.ERROR);
		}
		return false;
	}
	
	@Override
	public boolean performCancel() {
		if(page2 != null) {
			if(page2.getNamePhrase() != null) {
				page2.getNamePhrase().deleteRecursively();
				SCProjectContainer.instance().getSCProject(page2.getNamePhrase()).save();
			}
		}
		return true;
	}
	
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		
		setHelpAvailable(false);
		((WizardDialog)getContainer()).addPageChangingListener(new IPageChangingListener() {

			@Override
			public void handlePageChanging(PageChangingEvent event) {
				if (event.getTargetPage() instanceof RenameWizardPage && event.getCurrentPage() instanceof RenameWizardPageSenses) {
					page2.deletePhrase();
					page2.setPageComplete(false);
				}
				if(event.getTargetPage() instanceof RenameWizardPageSenses && event.getCurrentPage() instanceof RenameWizardPage) {
					page2.setElementName(newElementName);
					if (null!=page3) page3.refreshViewer();
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag() 
							&& RemoteJGWNL.getInstance().isConnected() && !newElementName.replace('_', ' ').trim().isEmpty())
						page2.setPageComplete(true);
				}
				
				if(event.getTargetPage() instanceof RenameWizardPageResolveProblems || event.getTargetPage() instanceof RenameWizardPageSenses && event.getCurrentPage() instanceof RenameWizardPageResolveProblems) {
					page3.refreshViewer();
				}
			}
			
		});
	}
	
	@Override
	public void addPages() {
		page = new RenameWizardPage(selection);
		addPage(page);
		if(getSelectedObject(selection) instanceof NotionDTO 
				|| getSelectedObject(selection) instanceof ActorDTO 
				|| getSelectedObject(selection) instanceof SystemElementDTO) {
			page2 = new RenameWizardPageSenses(selection);
			addPage(page2);
		}
		if(getSelectedObject(selection) instanceof NotionDTO) {
			if(((NotionDTO)getSelectedObject(selection)).getNonBasicNounLinksValues().size() > 0) {
				page3 = new RenameWizardPageResolveProblems(selection);
				addPage(page3);
			}
		}
	}
	
	protected Object getSelectedObject(ISelection selection) {
		if (selection != null) {
			ITreeSelection treeSelection = (ITreeSelection) selection;
			TreePath[] tr = treeSelection.getPaths();
			
			if(tr[0] != null) {
				Object selectedObj = tr[0].getLastSegment();
				return selectedObj;
			}
			else return null;
		}
		else return null;
	}
	
	protected IProject getSelectedProject(ISelection selection) {
		if (selection != null) {
			ITreeSelection treeSelection = (ITreeSelection) selection;
			TreePath[] tr = treeSelection.getPaths();
			
			if(tr[0] != null) {
				return (IProject)tr[0].getFirstSegment();
			}
			else return null;
		}
		else return null;
	}
	
}
