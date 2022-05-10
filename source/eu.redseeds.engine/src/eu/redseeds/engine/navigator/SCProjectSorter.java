package eu.redseeds.engine.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseArtifact;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.ComponentDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;

public class SCProjectSorter extends ViewerSorter {
	
	//categories for sorting, 0 - default, sorting by categories uses categories in ascending order
	public static int CATEGORY_SOFTWARE_CASE = 1;
	public static int CATEGORY_SOFTWARE_CASE_ARTIFACT = 2;
	public static int CATEGORY_REQUIREMENTS_SPEC = 3;
	public static int CATEGORY_REQUIREMENTS_PKG = 4;
	public static int CATEGORY_DOMAIN_SPEC = 5;
	public static int CATEGORY_REQUIREMENT = 6;
	public static int CATEGORY_ACTORS_PKG = 7;
	public static int CATEGORY_ACTOR = 8;
	public static int CATEGORY_NOTIONS_PKG = 9;
	public static int CATEGORY_NOTION = 10;
	public static int CATEGORY_SYS_ELEMENTS_PKG = 11;
	public static int CATEGORY_SYS_ELEMENT = 12;
	public static int CATEGORY_SCL_ELEMENT = 13;
	public static int CATEGORY_UML_PACKAGE = 14;
	public static int CATEGORY_UML_CLASS = 15;
	public static int CATEGORY_UML_INTERFACE = 16;
	public static int CATEGORY_UML_COMPONENT = 17;
	public static int CATEGORY_FOLDER = 20;
	public static int CATEGORY_FILE = 21;
	public static int CATEGORY_PROJECT = 99;
	
	
	@Override
	public int category(Object element) {
		if(element instanceof SoftwareCaseDTO) {
			return CATEGORY_SOFTWARE_CASE;
		}
		else if(element instanceof SoftwareCaseArtifact) {
			return CATEGORY_SOFTWARE_CASE_ARTIFACT;
		}
		else if(element instanceof RequirementsSpecificationDTO) {
			return CATEGORY_REQUIREMENTS_SPEC;
		}
		else if(element instanceof DomainSpecificationDTO) {
			return CATEGORY_DOMAIN_SPEC;
		}
		else if(element instanceof RequirementsPackageDTO) {
			return CATEGORY_REQUIREMENTS_PKG;
		}
		else if(element instanceof RequirementDTO) {
			return CATEGORY_REQUIREMENT;
		}
		else if(element instanceof ActorsPackageDTO) {
			return CATEGORY_ACTORS_PKG;
		}
		else if(element instanceof ActorDTO) {
			return CATEGORY_ACTOR;
		}
		else if(element instanceof NotionsPackageDTO) {
			return CATEGORY_NOTIONS_PKG;
		}
		else if(element instanceof NotionDTO) {
			return CATEGORY_NOTION;
		}
		else if(element instanceof SystemElementsPackageDTO) {
			return CATEGORY_SYS_ELEMENTS_PKG;
		}
		else if(element instanceof SystemElementDTO) {
			return CATEGORY_SYS_ELEMENT;
		}
		else if(element instanceof PackageDTO) {
			return CATEGORY_UML_PACKAGE;
		}
		else if(element instanceof ClassDTO) {
			return CATEGORY_UML_CLASS;
		}
		else if(element instanceof InterfaceDTO) {
			return CATEGORY_UML_INTERFACE;
		}
		else if(element instanceof ComponentDTO) {
			return CATEGORY_UML_COMPONENT;
		}
		else if(element instanceof IFolder) {
			return CATEGORY_FOLDER;
		}
		else if(element instanceof IFile) {
			return CATEGORY_FILE;
		}
		else if(element instanceof IProject) {
			return CATEGORY_PROJECT;
		}
		else {
			return super.category(element);
		}
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if(category(e1) != category(e2)) {
			return (category(e1) < category(e2)) ? -1 : 1;
		}
		//projects
		if(category(e1) == CATEGORY_PROJECT && category(e2) == CATEGORY_PROJECT) {
			IProject proj1 = (IProject)e1;
			IProject proj2 = (IProject)e2;
			return proj1.getName().compareTo(proj2.getName()); //just compare names
		}
		//files and folders
		else if(category(e1) == CATEGORY_FOLDER || category(e1) == CATEGORY_FILE) {
			return super.compare(viewer, e1, e2); //just compare names
		}
		//everything else
		else if(category(e1) == CATEGORY_SOFTWARE_CASE 
			&& category(e2) == CATEGORY_SOFTWARE_CASE) {
				SoftwareCaseDTO sc1 = (SoftwareCaseDTO)e1;
				SoftwareCaseDTO sc2 = (SoftwareCaseDTO)e2;
				return sc1.getName().compareTo(sc2.getName());
		}
		else if(category(e1) == CATEGORY_REQUIREMENTS_PKG 
				&& category(e2) == CATEGORY_REQUIREMENTS_PKG) {
			RequirementsPackageDTO reqp1 = (RequirementsPackageDTO)e1;
			RequirementsPackageDTO reqp2 = (RequirementsPackageDTO)e2;
			return reqp1.getName().compareTo(reqp2.getName());
		}
		else if(category(e1) == CATEGORY_REQUIREMENTS_PKG 
				&& category(e2) == CATEGORY_REQUIREMENT) {
			return -1;
		}
		else if(category(e1) == CATEGORY_REQUIREMENT 
				&& category(e2) == CATEGORY_REQUIREMENTS_PKG) {
			return 1;
		}
		else if(category(e1) == CATEGORY_REQUIREMENT 
				&& category(e2) == CATEGORY_REQUIREMENT) {
			RequirementDTO req1 = (RequirementDTO)e1;
			RequirementDTO req2 = (RequirementDTO)e2;
			if(req1.getName() == null || req2.getName() == null) {
				return -1;
			}
			return req1.getName().compareTo(req2.getName());
		}
		else if(category(e1) == CATEGORY_ACTORS_PKG 
				&& category(e2) == CATEGORY_ACTORS_PKG) {
			ActorsPackageDTO actp1 = (ActorsPackageDTO)e1;
			ActorsPackageDTO actp2 = (ActorsPackageDTO)e2;
			return actp1.getName().compareTo(actp2.getName());
		}
		else if(category(e1) == CATEGORY_ACTOR 
				&& category(e2) == CATEGORY_ACTOR) {
			ActorDTO act1 = (ActorDTO)e1;
			ActorDTO act2 = (ActorDTO)e2;
			if(act1.getName() == null || act2.getName() == null) {
				return -1;
			}
			return act1.getName().compareTo(act2.getName());
		}
		else if(category(e1) == CATEGORY_NOTIONS_PKG 
				&& category(e2) == CATEGORY_NOTIONS_PKG) {
			NotionsPackageDTO notp1 = (NotionsPackageDTO)e1;
			NotionsPackageDTO notp2 = (NotionsPackageDTO)e2;
			return notp1.getName().compareTo(notp2.getName());
		}
		else if(category(e1) == CATEGORY_NOTION 
				&& category(e2) == CATEGORY_NOTION) {
			NotionDTO not1 = (NotionDTO)e1;
			NotionDTO not2 = (NotionDTO)e2;
			return not1.getName().compareTo(not2.getName());
		}
		else if(category(e1) == CATEGORY_SYS_ELEMENTS_PKG 
				&& category(e2) == CATEGORY_SYS_ELEMENTS_PKG) {
			SystemElementsPackageDTO sep1 = (SystemElementsPackageDTO)e1;
			SystemElementsPackageDTO sep2 = (SystemElementsPackageDTO)e2;
			return sep1.getName().compareTo(sep2.getName());
		}
		else if(category(e1) == CATEGORY_SYS_ELEMENT 
				&& category(e2) == CATEGORY_SYS_ELEMENT) {
			SystemElementDTO se1 = (SystemElementDTO)e1;
			SystemElementDTO se2 = (SystemElementDTO)e2;
			return se1.getName().compareTo(se2.getName());
		}
		else if(category(e1) == CATEGORY_UML_PACKAGE 
				&& category(e2) == CATEGORY_UML_PACKAGE) {
			PackageDTO p1 = (PackageDTO)e1;
			PackageDTO p2 = (PackageDTO)e2;
			return p1.getName().compareTo(p2.getName());
		}
		else if(category(e1) == CATEGORY_UML_CLASS 
				&& category(e2) == CATEGORY_UML_CLASS) {
			ClassDTO c1 = (ClassDTO)e1;
			ClassDTO c2 = (ClassDTO)e2;
			return c1.getName().compareTo(c2.getName());
		}
		else if(category(e1) == CATEGORY_UML_INTERFACE 
				&& category(e2) == CATEGORY_UML_INTERFACE) {
			InterfaceDTO i1 = (InterfaceDTO)e1;
			InterfaceDTO i2 = (InterfaceDTO)e2;
			return i1.getName().compareTo(i2.getName());
		}
		else if(category(e1) == CATEGORY_UML_COMPONENT 
				&& category(e2) == CATEGORY_UML_COMPONENT) {
			ComponentDTO c1 = (ComponentDTO)e1;
			ComponentDTO c2 = (ComponentDTO)e2;
			return c1.getName().compareTo(c2.getName());
		}
		else {
			return 0;
		}
//		return super.compare(viewer, e1, e2);
	}
}
