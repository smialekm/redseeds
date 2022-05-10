package eu.redset.navigator.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;


import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.TestSpecificationContainer;
import eu.redseeds.sc.current.repository.interfaces.ICurrRepoContent;
import eu.redset.emf.model.tsl.TSLBusinessLayerFacade;
import eu.redset.emf.model.tsl.TestSpecification;
import eu.redset.navigator.Activator;


public class IProjectContentLabelProvider extends EventManager
					implements IProvider  {

	@Override
	public Object[] getChildren(Object parentElement) {
		ICurrRepoContent currRepo 
			= Activator.getDefault().getICurrRepoContentInstance();
		if (parentElement instanceof IProject) {
			IProject eclipseProject = (IProject) parentElement;
			SCProject project = currRepo.getSCProject(eclipseProject);
			List list = new ArrayList();
			
			TestSpecificationContainer testSpecContainer = project.getTestSpecificationContainer();
						
			Object[] tslFacadeCollection = testSpecContainer.getTSLFacades().toArray();
			 
			if (tslFacadeCollection.length == 0)
				return new Object[0];
			
			for (Object obj : tslFacadeCollection){
				if (obj instanceof TSLBusinessLayerFacade){
					list.add(((TSLBusinessLayerFacade)obj).getTestSpecification());
				}
			}
			
			//TestSpecification ts = project.getTSLBusinessLayerFacade().getTestSpecification();

			Object[] result = new Object[list.size()];
			result = list.toArray();
			return result;
//			return new Object[] { sc };
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		IProject eclipseProject = (IProject) element;
		if(!eclipseProject.isOpen()) {
			return false;
		}
		return true;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}
	
	//--------------
	//label provider
	//--------------
	
	@Override
	public Image getImage(Object element) {
		return null;
	}
	
	/* (non-Javadoc)
	 * just do toString for now
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		return element.toString();
	}
	
	@Override
	public void addListener(ILabelProviderListener listener) {
		addListenerObject(listener);
	}
	
	@Override
	public void removeListener(ILabelProviderListener listener) {
		removeListener(listener);
		
	}
	
	/* (non-Javadoc)
	 * return true
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isLabelProperty(Object element, String property) {
		return true;
	}
	
}
