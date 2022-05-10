package eu.redseeds.engine.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import eu.redseeds.common.Constants;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.sclkernel.ArchitecturalModelDTO;
import eu.redseeds.scl.model.sclkernel.DetailedDesignModelDTO;

public class RecoveredSCProjectFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(element instanceof RequirementsPackageDTO){
			RequirementsPackageDTO req = (RequirementsPackageDTO) element;
			if(req.getName().equals("RecoveredScenarios")){
				return false;
			}
		}
		else if(element instanceof ArchitecturalModelDTO || element instanceof DetailedDesignModelDTO){
			return false;
		}
		else if(element instanceof IFile) {
			IFile eleFile = (IFile)element;
			if(eleFile.getName().endsWith(".eap") 
					|| eleFile.getName().endsWith(".tg") 
					|| eleFile.getName().endsWith(".project") 
					|| eleFile.getName().endsWith(".py")
					|| eleFile.getName().endsWith(".ldb")) {
				return false;
			}
		}
		else if(element instanceof IFolder) {
			IFolder eleFold = (IFolder)element;
			if(eleFold.getName().equals(Constants.CODE_FOLDER_NAME)) {
				return false;
			}
		}
		return true;
	}

}
