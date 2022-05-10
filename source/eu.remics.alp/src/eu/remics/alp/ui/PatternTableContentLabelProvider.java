package eu.remics.alp.ui;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;

/**
 * Content and label provider for pattern selection of the ALP import wizard (manages patterns data)
 * @author aambroziewicz
 *
 */
public class PatternTableContentLabelProvider extends LabelProvider implements IStructuredContentProvider, ITableLabelProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement == null) {
			return null;
		}
		else if (inputElement instanceof SCProject) {
			SCProject proj = (SCProject)inputElement;
			return ((BusinessLayerFacade)SCProjectContainer.instance()
						.getSCProject(proj.getEclipseProject()).getSCLGraph())
					.getALPs().toArray();
		}
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		
		if (element instanceof RequirementsPackageDTO){
			result = ((RequirementsPackageDTO)element).getName();			
		}

		return result;
	}

}
