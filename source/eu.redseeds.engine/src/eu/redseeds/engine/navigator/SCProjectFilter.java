package eu.redseeds.engine.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import eu.redseeds.common.Constants;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;

/**
 * Filter for hiding (in project explorer) files like .tg or .eap (ones not to be edited directly by a user) 
 *
 */
public class SCProjectFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		// TODO add filtering
		if(element instanceof IFile) {
			IFile eleFile = (IFile)element;
			if(eleFile.getName().endsWith(".tg") 
					|| eleFile.getName().endsWith(".project") 
					|| eleFile.getName().endsWith(".py")
					|| eleFile.getName().endsWith(".ldb")) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(element instanceof IFolder) {
			IFolder eleFold = (IFolder)element;
			if(eleFold.getName().equals(Constants.CURRENT_SC_FOLDER_NAME)) {
				return false;
			}
			else if(eleFold.getName().startsWith(Constants.CLIPBOARD_FOLDER_NAME_PREFIX)) {
				return false;
			}
			else if(eleFold.getName().endsWith(".settings")){
				return false;
			}
			else {
				return true;
			}
		}
		else if(element instanceof RequirementsPackageDTO){
			RequirementsPackageDTO req = (RequirementsPackageDTO) element;
			if(req.getName().equals("RecoveredScenarios")){
				return false;
			}
		}
		return true;
	}

}
