package rsldl.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import rsldl.DLDomain;
import rsldl.DLRepository;
import rsldl.diagram.part.RsldlDiagramEditorPlugin;

import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;

public class DomainModelHelper {

	public static String[] getDomains(Object sclElement){
		IFile edm = DiagramFileHelper.getExtendedDomainModel(SCProjectContainer.instance().getSCProject(sclElement).getEclipseProject());
		if (null!=edm) {
			URI domainModelURI = URI.createPlatformResourceURI(edm.getFullPath().toString(), true);
			TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
			ResourceSet resourceSet = editingDomain.getResourceSet();
			EObject diagramRoot = null;
			Resource modelResource = null;
			try {
				modelResource = resourceSet.getResource(domainModelURI, true);
				diagramRoot = (EObject) modelResource.getContents().get(0);
			} catch (WrappedException ex) {
				RsldlDiagramEditorPlugin.getInstance().logError("Unable to load resource: " + domainModelURI, ex);
			}
			if (null!=diagramRoot){
				DLRepository repository = (DLRepository) diagramRoot;
				List<String> domains = new ArrayList<String>();
				for(DLDomain d:repository.getDomains())
					if(null!=d.getName())
						domains.add(d.getName());
				return domains.toArray(new String[domains.size()]);
			}
		}
		return new String[]{};
	}
	
}
