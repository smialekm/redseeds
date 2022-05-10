package eu.remics.navigator.providers;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {

    public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}
    
	public void dispose() {
	}
    
	public Object[] getElements(Object parent) {
		return getChildren(parent);
	}
    
	public Object getParent(Object child) {
		/*if (child instanceof XTopPackage) {
			return ((XTopPackage)child).getParent();
		}*/
		if (child instanceof NotionsPackageDTO) {
			return ((NotionsPackageDTO)child).getParent();
		}
		else if (child instanceof RequirementsPackageDTO) {
			return ((RequirementsPackageDTO)child).getParent();
		}
		else if (child instanceof NotionDTO) {
			return ((NotionDTO)child).getParent();
		}
		else if (child instanceof UseCaseDTO) {
			return ((UseCaseDTO)child).getParent();
		}
		else if (child instanceof ConstrainedLanguageScenarioDTO) {
			return ((ConstrainedLanguageScenarioDTO)child).getParent();
		}
		return null;
	}
    
	public Object[] getChildren(Object parent) {
		if (parent instanceof NotionsPackageDTO) {
			return ((NotionsPackageDTO)parent).getNotionDTOList().toArray();
		}
		else if (parent instanceof RequirementsPackageDTO) {
			Object[] usecases = ((RequirementsPackageDTO)parent).getRequirements().toArray();
			return usecases;
		}
		else if (parent instanceof UseCaseDTO) {
			return ((UseCaseDTO)parent).getConstrainedLanguageScenarioDTOList().toArray();
		}
		/*else if(parent instanceof XTopPackage){
			return ((XTopPackage)parent).getPackages().toArray();
		}*/
		return new Object[0];
	}

    public boolean hasChildren(Object parent) {
		if (parent instanceof NotionsPackageDTO)
			return ((NotionsPackageDTO)parent).getNotionDTOList().size() > 0 ? true : false;
		else if (parent instanceof RequirementsPackageDTO)
			return (((RequirementsPackageDTO)parent).getRequirements().size() /*+ ((RequirementsPackageDTO)parent).getDiagrams().size()*/) > 0 ? true : false;
		else if (parent instanceof UseCaseDTO)
			return ((UseCaseDTO)parent).getConstrainedLanguageScenarioDTOList().size() > 0 ? true : false;
		/*else if (parent instanceof XPackage)
			return ((XTopPackage)parent).getPackages().size() > 0 ? true : false;*/
		return false;
	}
}
