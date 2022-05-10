package eu.redset.navigator.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;

import eu.redseeds.common.SCProjectHelper;
import eu.redset.navigator.Activator;

public class SCLElementWizard extends Wizard {
	
	private SCLElementWizardPage one;
	private ISelection selection;
	private java.lang.Object type;
	private SCLElementCreationOperation operation;

	public SCLElementWizard(java.lang.Object type){
		super();
		this.type = type;
	}
	
	@Override
	public boolean performFinish() {
		try {
			if (selection != null) {
				
				ITreeSelection treeSelection = (ITreeSelection) selection;
				
				TreePath[] tr = treeSelection.getPaths();
				
				if(tr[0] != null) {
					IProject eclipseProject = null;
					Object parent = tr[0].getLastSegment();
					if (tr[0].getFirstSegment() instanceof IProject){
						eclipseProject = (IProject)tr[0].getFirstSegment();
					} else {
						return false;
					}
					if(parent == null) {
						return false;
					}
					operation = new SCLElementCreationOperation(eclipseProject, parent, type, one);
					
					getContainer().run(false, true, operation);
					
					SCProjectHelper.refreshTSNavigator();
					
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
        return true;
    }
	
	@Override
	public void addPages() {
		one = new SCLElementWizardPage(selection, type);
		addPage(one);
	}
	
	
//	@Override //TODO
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		this.selection = selection;		
	}

	public SCLElementWizardPage getSCLElementWizardPage() {
		return one;
	}

	public SCLElementCreationOperation getSCLElementCreationOperation() {
		return operation;
	}

	
}
