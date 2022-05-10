package eu.redset.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import eu.redseeds.common.Constants;


/**
 * Filter for hiding (in project explorer) files like .tg or .eap (ones not to be edited directly by a user) 
 *
 */
public class TSProjectFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		// TODO add filtering
		if(element instanceof IFile) {
			IFile eleFile = (IFile)element;
			if(eleFile.getName().endsWith(".tg") || eleFile.getName().endsWith(".project")
					|| eleFile.getName().endsWith(".eap")) {
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
			else if(eleFold.getName().equals(Constants.ARCHITECTURE_FOLDER_NAME)){
				return false;
			}
			else if(eleFold.getName().equals(Constants.CODE_FOLDER_NAME)){
				return false;
			}
			else if(eleFold.getName().equals(Constants.DESIGN_FOLDER_NAME)){
				return false;
			}
			else if(eleFold.getName().equals(Constants.NOTIONSDIAGRAMS_FOLDER_NAME)){
				return false;
			}
			else if(eleFold.getName().equals(Constants.REQUIREMENTS_FOLDER_NAME)){
				return false;
			}
			else if(eleFold.getName().equals(Constants.TESTS_FOLDER_NAME)){
				return false;
			}
			else if(eleFold.getName().equals(Constants.TRANSFORMATIONS_FOLDER_NAME)){
				return false;
			}
			else if(eleFold.getName().equals(Constants.UCDIAGRAMS_FOLDER_NAME)){
				return false;
			}
			else if(eleFold.getName().equals(Constants.DOMAINDIAGRAMS_FOLDER_NAME)){
				return false;
			}
			else {
				return true;
			}
		}
		return true;
	}

}
