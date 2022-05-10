package eu.redseeds.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

public class DiagramFileHelper {
	
	public static Integer getNotionDiagramId(String path){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path.substring(0,path.lastIndexOf('_'))), "UTF-8"));
			br.readLine();
			String s = br.readLine();
			br.close();
			s=s.substring(s.indexOf("Package=\"")+9);
			return Integer.parseInt(s.substring(0,s.indexOf("\"")));
		} catch (Exception e){
			return null;
		}
	}
	
	public static Integer getUseCaseDiagramId(String path){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path.substring(0,path.lastIndexOf('_'))), "UTF-8"));
			br.readLine();
			String s = br.readLine();
			br.close();
			s=s.substring(s.indexOf("package=\"")+9);
			return Integer.parseInt(s.substring(0,s.indexOf("\"")));
		} catch (Exception e){
			return null;
		}
	}
	
	public static String getNotionDiagramName(String path){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path.substring(0,path.lastIndexOf('_'))), "UTF-8"));
			br.readLine();
			String s = br.readLine();
			br.close();
			s=s.substring(s.indexOf("Name=\"")+6);
			return s.substring(0,s.indexOf("\""));
		} catch (Exception e){
			return "";
		}
	}
	
	public static String getUseCaseDiagramName(String path){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path.substring(0,path.lastIndexOf('_'))), "UTF-8"));
			br.readLine();
			String s = br.readLine();
			br.close();
			s=s.substring(s.indexOf("name=\"")+6);
			return s.substring(0,s.indexOf("\""));
		} catch (Exception e){
			return "";
		}
	}
	
	public static IFile getExtendedDomainModel(IProject project) {
		if (null!=project){
			IFolder currSCFolder = project.getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			if(!currSCFolder.isAccessible())
				InitialFolderStructureHelper.createInitialFolderStructure(project);
			IFile edm = currSCFolder.getFile(Constants.DOMAINDIAGRAMS_FILE_NAME);
			if(edm.isAccessible())
				return edm;
		}
		return null;
	}
	
	public static boolean isEnableRSLDL(){
		return Activator.getDefault().getPreferenceStore().getBoolean("enableRSLDL");
	}
	
}
