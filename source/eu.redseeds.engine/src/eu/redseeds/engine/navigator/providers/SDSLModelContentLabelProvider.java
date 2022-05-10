package eu.redseeds.engine.navigator.providers;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.engine.navigator.IImageKeys;
import eu.redseeds.scl.model.sdsl.ActorDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.ComponentDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;

public class SDSLModelContentLabelProvider extends EventManager
		implements IProvider {

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof PackageDTO) {
			PackageDTO pack = (PackageDTO) parentElement;
			Object[] actors  = pack.getActorDTOList().toArray();
			Object[] classes  = pack.getClassDTOList().toArray();
			Object[] components = pack.getComponentDTOList().toArray();
			Object[] interfaces = pack.getInterfaceDTOList().toArray();
			Object[] packages = pack.getPackageDTOList().toArray();
			Object[] result 
				= new Object[packages.length + classes.length + components.length + interfaces.length + actors.length];
			System.arraycopy(packages, 0, result, 0, packages.length);
			System.arraycopy(classes, 0, result, packages.length, classes.length);
			System.arraycopy(components, 0, result, packages.length + classes.length, components.length);
			System.arraycopy(interfaces, 0, result, packages.length + classes.length + components.length, interfaces.length);
			System.arraycopy(actors, 0, result, packages.length + classes.length + components.length + interfaces.length, actors.length);
			return result;
		}
		else if(parentElement instanceof ClassDTO) {
			ClassDTO cl = (ClassDTO)parentElement;
			return cl.getOperationDTOList().toArray();
		}
		else if(parentElement instanceof InterfaceDTO) {
			InterfaceDTO interf = (InterfaceDTO)parentElement;
			return interf.getOperationDTOList().toArray();
		}
		else if(parentElement instanceof ComponentDTO) {
			return new Object[0];
		}
		else if(parentElement instanceof OperationDTO) {
			return new Object[0];
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof PackageDTO) {
			PackageDTO pack = (PackageDTO) element;
			return pack.getParent();
		}
		else if (element instanceof ActorDTO) {
			ActorDTO act = (ActorDTO) element;
			return act.getParent();
		}
		else if(element instanceof ClassDTO) {
			ClassDTO cl = (ClassDTO) element;
			return cl.getParent();
		}
		else if(element instanceof InterfaceDTO) {
			InterfaceDTO interf = (InterfaceDTO) element;
			return interf.getParent();
		}
		else if(element instanceof ComponentDTO) {
			ComponentDTO compo = (ComponentDTO) element;
			return compo.getParent();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof PackageDTO) {
			PackageDTO pack = (PackageDTO) element;
			Object[] actors  = pack.getActorDTOList().toArray();
			Object[] classes  = pack.getClassDTOList().toArray();
			Object[] components = pack.getComponentDTOList().toArray();
			Object[] interfaces = pack.getInterfaceDTOList().toArray();
			Object[] packages = pack.getPackageDTOList().toArray();
			if((actors.length + packages.length + classes.length + components.length + interfaces.length) > 0) {
				return true;
			}
			else return false;
		}
		else if(element instanceof ClassDTO) {
			ClassDTO cl = (ClassDTO)element;
			return cl.getOperationDTOList().size() > 0 ? true : false;
		}
		else if(element instanceof InterfaceDTO) {
			InterfaceDTO interf = (InterfaceDTO)element;
			return interf.getOperationDTOList().size() > 0 ? true : false;
		}
		else if(element instanceof ComponentDTO) {
			return false;
		}
		else if(element instanceof OperationDTO) {
			return false;
		}
		return false;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}
	
	//----------------
	//label provider
	//----------------

	@Override
	public Image getImage(Object element) {
		if (element instanceof PackageDTO) {
			return ImageCache.getImage(IImageKeys.PACKAGE);
		}
		else if(element instanceof ActorDTO) {
			return ImageCache.getImage(IImageKeys.ACTOR_UML);
		}
		else if(element instanceof ClassDTO) {
			return ImageCache.getImage(IImageKeys.CLASS);
		}
		else if(element instanceof InterfaceDTO) {
			return ImageCache.getImage(IImageKeys.INTERFACE);
		}
		else if(element instanceof ComponentDTO) {
			return ImageCache.getImage(IImageKeys.COMPONENT);
		}
		else if(element instanceof OperationDTO) {
			return ImageCache.getImage(IImageKeys.OPERATION);
		}
		else {
			return null;
		}
			
	}

	@Override
	public String getText(Object element) {
		if (element instanceof PackageDTO) {
			return ((PackageDTO)element).getName();
		}
		else if(element instanceof ActorDTO) {
			return ((ActorDTO)element).getName();
		}
		else if(element instanceof ClassDTO) {
			return ((ClassDTO)element).getName();
		}
		else if(element instanceof InterfaceDTO) {
			return ((InterfaceDTO)element).getName();
		}
		else if(element instanceof ComponentDTO) {
			return ((ComponentDTO)element).getName();
		}
		else if(element instanceof OperationDTO) {
			return ((OperationDTO)element).getName();
		}
		return "";
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		addListenerObject(listener);
	}
	
	@Override
	public void removeListener(ILabelProviderListener listener) {
		removeListenerObject(listener);
	}
	
	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

}
