package eu.remics.alp.ui;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbench;

import eu.remics.alp.Constants;
import eu.remics.alp.ImportManager;
import eu.remics.alp.PatternSlice;
import eu.remics.alp.SliceJob;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

/**
 * A wizard guiding user through ALP import process
 * @author aambroziewicz
 *
 */
public class ImportALPWizard extends Wizard {
	
	protected ImportALPWizardPatternSelectionPage page1;
	protected ImportALPWizardDomainPage page2;
	protected ImportALPWizardSensesPage page3;
	
	protected PatternSlice slice;
	protected Object[] domainItems = null;
	
	private ISelection selection;

	@Override
	public boolean performFinish() {
		// TODO 
		if (selection != null) {
			ITreeSelection treeSelection = (ITreeSelection) selection;
			TreePath[] tr = treeSelection.getPaths();
			
			if(tr[0] != null) {
				Object selectedObj = tr[0].getLastSegment();
				if(selectedObj == null) {
					return false;
				}
				
				if(SCProjectHelper.getSenseAutoAddAndAssigmentFlag() || SCProjectHelper.getSenseAutoAssigmentFlag()) {
					getSlice().setDomainInstantiationInfo(page2.getDomainInstantiationInfo());
				}
				else {
					getSlice().setDomainInstantiationInfo(page3.getDomainInstantiationInfo());
				}
				
				boolean result = false;
				if(selectedObj instanceof IProject) {
					selectedObj = SCProjectContainer.instance().getSCProject((IProject)selectedObj).getMainCase();
				}
				if(selectedObj instanceof SoftwareCaseDTO) {
					selectedObj = ((SoftwareCaseDTO)selectedObj).getRequirementsSpecificationDTO();
				}
				if(selectedObj instanceof RequirementsPackageDTO) {
					result = ImportManager.importPattern((RequirementsPackageDTO)selectedObj, null);
					SCProjectHelper.refreshSCNavigator();
					return result;
				}
				if(selectedObj instanceof RequirementsSpecificationDTO) {
					result = ImportManager.importPattern((RequirementsSpecificationDTO)selectedObj, getSlice(), getContainer());
					SCProjectHelper.refreshSCNavigator();
					return result;
				}
			}
		}
		return false;
	}
	
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		setHelpAvailable(false);
		setWindowTitle(Constants.IMPORT_WIZARD_NAME);
		setNeedsProgressMonitor(true);
		((WizardDialog)getContainer()).addPageChangingListener(new IPageChangingListener() {

			@Override
			public void handlePageChanging(PageChangingEvent event) {
				if(event.getTargetPage() instanceof ImportALPWizardPatternSelectionPage) {
					page2.setpSlice(null);//reset
				}
				
				if(event.getTargetPage() instanceof ImportALPWizardDomainPage) {
					if(event.getCurrentPage() instanceof ImportALPWizardSensesPage) { //going back
						if(getDomainItems() != null) {
							page2.setItems(getDomainItems());
						}
					}
					else {
						SliceJob sliceJob = new SliceJob(page1.getSelectedPattern());
						try {
						      // puts the data into a database ...
						      getContainer().run(true, true, sliceJob);
						    } catch (InvocationTargetException e) {
						      e.printStackTrace();
						    } catch (InterruptedException e) {
						      e.printStackTrace();
						    }
						
	//					page2.setSelectedPattern(page1.getSelectedPattern());
						page2.setpSlice(sliceJob.getpSlice());
						setSlice(sliceJob.getpSlice());
					}
				}
				
				if(event.getTargetPage() instanceof ImportALPWizardSensesPage) {
					setDomainItems(page2.getItems());
					page3.setNotionNames(page2.getItems());
				}
			}
			
		});
	}
	
	public void addPages() {
		page1 = new ImportALPWizardPatternSelectionPage(Constants.IMPORT_WIZARD_PATTERN_SELECTION_PAGE_NAME);
		addPage(page1);
		page2 = new ImportALPWizardDomainPage(Constants.IMPORT_WIZARD_DOMAIN_PAGE_NAME);
		addPage(page2);
		if(!(SCProjectHelper.getSenseAutoAddAndAssigmentFlag() || SCProjectHelper.getSenseAutoAssigmentFlag())) {
			page3 = new ImportALPWizardSensesPage(Constants.IMPORT_WIZARD_SENSES_PAGE_NAME);
			addPage(page3);
		}
	}
	
	@Override
	public boolean canFinish() {
		if(!(SCProjectHelper.getSenseAutoAddAndAssigmentFlag() || SCProjectHelper.getSenseAutoAssigmentFlag())) {
			if(page1.isPageComplete() && page2.isPageComplete() && page3.isPageComplete()) {
				return true;
			}
		}
		else {
			if(page1.isPageComplete() && page2.isPageComplete()) {
				return true;
			}
		}
		return false;
	}

	public PatternSlice getSlice() {
		return slice;
	}

	public void setSlice(PatternSlice slice) {
		this.slice = slice;
	}
	
	public Object[] getDomainItems() {
		return domainItems;
	}

	public void setDomainItems(Object[] domainItems) {
		this.domainItems = domainItems;
	}

}
