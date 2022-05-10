package eu.redseeds.common;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;

public class InitialFolderStructureHelper {

	public static void createInitialFolderStructure(IProject project) {
		try {
			IFolder currSCFolder = project.getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			IFolder requirementsFolder = currSCFolder.getFolder(Constants.REQUIREMENTS_FOLDER_NAME);
			IFolder architectureFolder = currSCFolder.getFolder(Constants.ARCHITECTURE_FOLDER_NAME);
			IFolder designFolder = currSCFolder.getFolder(Constants.DESIGN_FOLDER_NAME);
			IFolder codeFolder = currSCFolder.getFolder(Constants.CODE_FOLDER_NAME);
			IFolder transformationsFolder = currSCFolder.getFolder(Constants.TRANSFORMATIONS_FOLDER_NAME);
			IFolder attachmentsFolder = currSCFolder.getFolder(Constants.ATTACHMENTS_FOLDER_NAME);
			IFolder ucdiagramsFolder = currSCFolder.getFolder(Constants.UCDIAGRAMS_FOLDER_NAME);
			IFolder notionsdiagramsFolder = currSCFolder.getFolder(Constants.NOTIONSDIAGRAMS_FOLDER_NAME);
			IFolder domaindiagramsFolder = currSCFolder.getFolder(Constants.DOMAINDIAGRAMS_FOLDER_NAME);
			IFolder testsFolder = currSCFolder.getFolder(Constants.TESTS_FOLDER_NAME);
			
			currSCFolder.create(false, true, null);
			requirementsFolder.create(false, true, null);
			architectureFolder.create(false, true, null);
			designFolder.create(false, true, null);
			codeFolder.create(false, true, null);
			transformationsFolder.create(false, true, null);
			attachmentsFolder.create(false, true, null);
			ucdiagramsFolder.create(false, true, null);
			notionsdiagramsFolder.create(false, true, null);
			domaindiagramsFolder.create(false, true, null);
			testsFolder.create(false, true, null);
			
			ResourceAttributes rAttr = new ResourceAttributes();
			rAttr.setReadOnly(false);
			rAttr.setExecutable(true);
			
			currSCFolder.setResourceAttributes(rAttr);
			requirementsFolder.setResourceAttributes(rAttr);
			architectureFolder.setResourceAttributes(rAttr);
			designFolder.setResourceAttributes(rAttr);
			codeFolder.setResourceAttributes(rAttr);
			transformationsFolder.setResourceAttributes(rAttr);
			attachmentsFolder.setResourceAttributes(rAttr);
			ucdiagramsFolder.setResourceAttributes(rAttr);
			notionsdiagramsFolder.setResourceAttributes(rAttr);
			domaindiagramsFolder.setResourceAttributes(rAttr);
			testsFolder.setResourceAttributes(rAttr);
		} catch (CoreException ce) {
			Activator.log("Error during creating initial folder structure: " 
					+ ce.getMessage(), Status.ERROR);
		}
	}
	
}
