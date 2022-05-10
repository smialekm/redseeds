package eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.common.Constants;
import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.scl.impl.rsl.rsldomainelements.notions.NotionsPackageImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;
import eu.redseeds.scl.sclkernel.SCLElementsPackage;

public class NotionsPackageDTOImpl extends NotionsPackageImpl implements
		NotionsPackageDTO {

	public NotionsPackageDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public List<NotionsPackageDTO> getNotionsPackageDTOList() {
		List<? extends SCLElementsPackage> l = super.getNestedPackageList();
		List<NotionsPackageDTO> result = new ArrayList<NotionsPackageDTO>();
		for (SCLElementsPackage pack : l) {
			result.add((NotionsPackageDTO) pack);
		}
		return result;
	}

	@Override
	public List<NotionDTO> getNotionDTOList() {
		List<? extends Notion> l = super.getNotionList();
		List<NotionDTO> result = new ArrayList<NotionDTO>();
		for (Notion notion : l) {
			result.add((NotionDTO) notion);
		}
		return result;
	}

	@Override
	public void addNotionDTO(NotionDTO notion) {
		super.addNotion((NotionDTOImpl) notion);
	}

	@Override
	public void addNotionsPackageDTO(NotionsPackageDTO notionsPackage) {
		super.addNestedPackage((NotionsPackageDTOImpl) notionsPackage);
	}

	@Override
	public void deleteRecursively() {
		for (NotionsPackageDTO np : getNotionsPackageDTOList()) {
			np.deleteRecursively();
		}
		for (NotionDTO notion : getNotionDTOList()) {
			notion.delete();
		}
		super.delete();
	}

	@Override
	public String toString() {
		return super.getName();
	}

	@Override
	public NotionsPackageDTO getParent() {
		List<? extends SCLElementsPackage> pl = super.getNestingPackageList();
		NotionsPackageDTO parent = null;
		for (SCLElementsPackage pack : pl) {
			parent = (NotionsPackageDTO) pack;
			break;
		}
		return parent;
	}

	@Override
	public void removeChildNotionDTO(NotionDTO child) {
		Notion n = (Notion) child;
		NotionsPackage np = this;
		for (Edge e : n.getPackageContainsNotionIncidences(EdgeDirection.IN)) {
			if (e.getAlpha() == np) {
				e.delete();
				break;
			}
		}

	}

	@Override
	public void removeChildNotionsPackageDTO(NotionsPackageDTO child) {
		NotionsPackage npChild = (NotionsPackage) child;
		NotionsPackage npParent = this;
		for (Edge e : npChild
				.getrsl$rsldomainelements$domainelements$NestingPackageContainsNestedPackageIncidences(EdgeDirection.IN)) {
			if (e.getAlpha() == npParent) {
				e.delete();
				break;
			}
		}

	}

	@Override
	public void setParent(NotionsPackageDTO parent) {
		NotionsPackageDTO oldParent = null;
		if (getParent() != null) {
			oldParent = getParent();
			getParent().removeChildNotionsPackageDTO(this);
		}
		// check if new parent is not among children
		for (NotionsPackageDTO rp : getNotionsPackageDTOList()) {
			if (rp.equals(parent)) {
				if (getParent() != null) {
					parent.setParent(getParent());// rewire child to this.parent
					break;
				} else {
					parent.setParent(oldParent);// rewire child to this.parent
					break;
				}
			}
		}
		super.addNestingPackage((NotionsPackage) parent);
	}

	@Override
	public String getSpecificationPath() {
		String path = "\\" + getName();
		NotionsPackageDTO parent = getParent();
		while (parent != null) {
			path = "\\" + parent.getName() + path;
			parent = parent.getParent();
		}
		return path;
	}

	@Override
	public DomainSpecificationDTO getDomainSpecificationParent() {
		if (getDomainSpecificationList() != null) {
			if (getDomainSpecificationList().size() > 0) {
				return (DomainSpecificationDTO) getDomainSpecificationList()
						.get(0);
			}
		}
		return null;
	}
	
	public ArrayList<IFile> getNotionsDiagrams(){
		ArrayList<IFile> notiondiagramsFiles = new ArrayList<IFile>();
		if(SCProjectHelper.getActiveProject() != null){
			IFolder currSCFolder = SCProjectHelper.getActiveProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			IFolder notiondiagrams = currSCFolder.getFolder(Constants.NOTIONSDIAGRAMS_FOLDER_NAME);
			IResource[] notiondiagramsRes = null;
			try {
				notiondiagramsRes = ((IContainer)notiondiagrams).members();
			} catch (CoreException e) {
				e.printStackTrace();
			}

			if(notiondiagramsRes != null)
				for (int i=0; i< notiondiagramsRes.length; i++) {
					Integer id;
					if(notiondiagramsRes[i] instanceof IFile && ((IFile)notiondiagramsRes[i]).getFileExtension().equalsIgnoreCase("notiondiagram_diagram") && null!=(id=DiagramFileHelper.getNotionDiagramId(((IFile)notiondiagramsRes[i]).getLocation().toOSString())) && id==this.getId())
						notiondiagramsFiles.add((IFile)notiondiagramsRes[i]);
				}
		}
		return notiondiagramsFiles;
	}
}