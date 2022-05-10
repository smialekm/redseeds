package eu.redseeds.ea.converter.impl;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Status; //import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox; //import org.eclipse.jface.viewers.TreePath;
//import org.eclipse.ui.IWorkbench;
//import org.eclipse.ui.IWorkbenchPage;
//import org.eclipse.ui.IWorkbenchWindow;
//import org.eclipse.ui.PlatformUI;
import org.eclipse.core.runtime.Platform;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.ea.converter.interfaces.IConversions;
import eu.redseeds.ea.converter.Activator;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;

import java.io.File;
import java.io.IOException; //import java.net.JarURLConnection;
import java.net.URL;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.osgi.framework.Bundle;

public class EAConverterImpl implements IConversions {

	private static final String EAP_FILE_NAME = "ea_redseeds.eap";
	private static final String EA_DLLFILE_NAME = "SSJavaCOM.dll";
	private static final String GRAPH_FILE_NAME = "sclgraph.tg";
	private static final String EAP_SOURCE = "lib/ea_redseeds.eap";
	private static final String EA_DLLSOURCE = "lib/SSJavaCOM.dll";
	// private static final String EA_INSTALL_PATH = "C:/Program Files/Sparx
	// Systems/EA/EA.exe";

	private ExportJGraLabToEA TG_EA_Instance = null;
	private ImportEAToJGralab EA_TG_Instance = null;
	private ExportRSLUseCaseFromJGraLabToEA Requirements_EA_Instance = null;

	public void convertTGtoEA(SCProject currSCProject, boolean openAfter, boolean dgrmBitmapSave, boolean genCode) {
		Activator.log("convertTGtoEA invoked...", Status.INFO);
		if (TG_EA_Instance == null)
			TG_EA_Instance = new ExportJGraLabToEA();

		if (currSCProject == null)
			currSCProject = getCurrentSCProject();
		if (currSCProject == null) {
			Activator.log("Can't locate current SCProject...", Status.ERROR);
			return;
		}

		String projLocation = currSCProject.getEclipseProject().getLocation().toString();

		if (createEAEnvironment(currSCProject) == false)
			return;

		TG_EA_Instance.exportDataToEA(projLocation + "/" + EAP_FILE_NAME, projLocation + "/" + GRAPH_FILE_NAME, currSCProject.getSCLGraph(), dgrmBitmapSave, genCode);

		currSCProject.save();
		// TODO refresh project tree ??

		if (openAfter) // open EA after data export
			openEA(null);
		/*
		 * if (openAfter) { //open EA after data export File eaExecutable = new
		 * File(EA_INSTALL_PATH); if (!eaExecutable.exists()) {
		 * Activator.log("Can't locate Enterprise Architect tool",
		 * Status.ERROR); return; }
		 *
		 * Runtime rt = Runtime.getRuntime(); try{ rt.exec("cmd /C start \"\"
		 * \"" + projLocation + "/" + EAP_FILE_NAME + "\""); //
		 * rt.exec(EA_INSTALL_PATH + " " + projLocation + "/" + EAP_FILE_NAME); //
		 * Process p = rt.exec(EA_INSTALL_PATH + " " + projLocation + "/" +
		 * EAP_FILE_NAME); // if(p.waitFor() != 0) // Activator.log("EA process
		 * exit value = " + p.exitValue(), Status.ERROR); }catch (Exception ex) {
		 * Activator.logException(ex); } }
		 */
		return;
	}

	public void convertEAtoTG(SCProject currSCProject, String eaModel) {
		Activator.log("convertEAtoTG invoked...", Status.INFO);
		if (EA_TG_Instance == null)
			EA_TG_Instance = new ImportEAToJGralab();

		if (currSCProject == null)
			currSCProject = getCurrentSCProject();
		if (currSCProject == null) {
			Activator.log("Can't locate current SCProject...", Status.ERROR);
			return;
		}

		String projLocation = currSCProject.getEclipseProject().getLocation().toString();

		if (createEAEnvironment(currSCProject) == false)
			return;

		EA_TG_Instance.importDataFromEA(projLocation + "/" + EAP_FILE_NAME, projLocation + "/" + GRAPH_FILE_NAME, currSCProject.getSCLGraph(), eaModel);
		currSCProject.save();
		// TODO refresh project tree ??
		return;
	}
	
	public void convertRequirementsToEA(SCProject currSCProject, boolean openAfter) {
		Activator.log("convertRequirementsToEA invoked...", Status.INFO);
		if (Requirements_EA_Instance == null)
			Requirements_EA_Instance = new ExportRSLUseCaseFromJGraLabToEA();

		if (currSCProject == null)
			currSCProject = getCurrentSCProject();
		if (currSCProject == null) {
			Activator.log("Can't locate current SCProject...", Status.ERROR);
			return;
		}

		String projLocation = currSCProject.getEclipseProject().getLocation().toString();

		if (createEAEnvironment(currSCProject) == false)
			return;

		Requirements_EA_Instance.exportDataToEA(projLocation + "/" + EAP_FILE_NAME, projLocation + "/" + GRAPH_FILE_NAME, currSCProject.getSCLGraph());

		currSCProject.save();

		if (openAfter)
			openEA(null);
		return;
	}

	public void openEA(String eapPath_FileName) {

		// File eaExecutable = new File(EA_INSTALL_PATH);
		// if (!eaExecutable.exists()) {
		// Activator.log("Can't locate Enterprise Architect tool",
		// Status.ERROR);
		// return;
		// }

		String eapFile = eapPath_FileName;
		if (eapFile == null || eapFile.isEmpty()) {

			SCProject currProject = getCurrentSCProject();
			if (currProject == null) {
				Activator.log("Can't locate current SCProject...", Status.ERROR);
				MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_ERROR);
				confirmMB.setMessage("Can't find selected project!");
				confirmMB.open();
				return;
			}

			String projLocation = currProject.getEclipseProject().getLocation().toString();
			eapFile = projLocation + "/" + EAP_FILE_NAME;

			if (createEAEnvironment(currProject) == false)
				return;
		}

		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("cmd /C start \"\" \"" + eapFile + "\"");
			// rt.exec(EA_INSTALL_PATH + " " + projLocation + "/" +
			// EAP_FILE_NAME);
			// Process p = rt.exec(EA_INSTALL_PATH + " " + projLocation + "/" +
			// EAP_FILE_NAME);
			// if(p.waitFor() != 0)
			// Activator.log("EA process exit value = " + p.exitValue(),
			// Status.ERROR);
		} catch (Exception ex) {
			Activator.logException(ex);
		}
	}

	private boolean createEAEnvironment(SCProject currProject) {

		if (currProject == null)
			currProject = getCurrentSCProject();
		if (currProject == null)
			return false;

		String projLocation = currProject.getEclipseProject().getLocation().toString();
		// Find or create .eap file in current project folder
		File tmpFile = new File(projLocation + "/" + EAP_FILE_NAME);
		if (!tmpFile.exists()) {
			// create empty eap file
			Activator.log(EAP_FILE_NAME + " not found...", Status.INFO);
			if (createEAFile(projLocation + "/" + EAP_FILE_NAME, EAP_SOURCE) == null) {
				Activator.log("Can't create " + EAP_FILE_NAME + " file", Status.ERROR);
				return false;
			}
		}

		// Find or create EA_DLLFILE_NAME file in eclipse installation path
		String installPath = Platform.getInstallLocation().getURL().getPath();
		tmpFile = new File(installPath + "/" + EA_DLLFILE_NAME);
		if (!tmpFile.exists()) {
			// create ea dll file
			Activator.log(EA_DLLFILE_NAME + " not found...", Status.INFO);
			if (createEAFile(installPath + "/" + EA_DLLFILE_NAME, EA_DLLSOURCE) == null) {
				Activator.log("File \"" + EA_DLLFILE_NAME + "\" is missing", Status.ERROR);
				return false;
			}
		}

		return true;
	}

	private SCProject getCurrentSCProject() {

		// workaround for SCProjectHelper.getIProject(currSelection)
		ITreeSelection currSelection = (ITreeSelection) SCProjectHelper.getSelection();
		if (currSelection == null)
			return null;
		TreePath[] tr = currSelection.getPaths();
		if (tr == null || tr.length < 1)
			return null;
		// ------------

		IProject project = SCProjectHelper.getIProject(currSelection);
		return SCProjectContainer.instance().getSCProject(project);

		/*
		 * IWorkbench workbench = PlatformUI.getWorkbench(); if (workbench ==
		 * null) { Activator.log("Can't locate IWorkbench...", Status.ERROR);
		 * return null; }
		 *
		 * IWorkbenchWindow window = workbench.getActiveWorkbenchWindow(); if
		 * (window == null) { Activator.log("Can't locate IWorkbenchWindow...",
		 * Status.ERROR); return null; }
		 *
		 * IWorkbenchPage activePage = window.getActivePage(); if (activePage ==
		 * null) { Activator.log("Can't locate IWorkbenchPage...",
		 * Status.ERROR); return null; }
		 *
		 * ISelection selection = activePage.getSelection(); if (selection ==
		 * null) { Activator.log("Can't get ISelection...", Status.ERROR);
		 * return null; }
		 *
		 * Object selectedElement =
		 * ((IStructuredSelection)selection).getFirstElement(); if
		 * (selectedElement == null) { Activator.log("Can't get
		 * selectedElement...", Status.ERROR); return null; }
		 *
		 * ITreeSelection treeSelection = (ITreeSelection)selection; if
		 * (selection == null) { Activator.log("Can't get ITreeSelection...",
		 * Status.ERROR); return null; }
		 *
		 * TreePath[] paths = treeSelection.getPathsFor(selectedElement);
		 * IProject project = null; if (paths.length > 0){ if
		 * (paths[0].getFirstSegment() instanceof IProject){ project =
		 * (IProject)paths[0].getFirstSegment(); } else return null; } else
		 * return null;
		 *
		 * return SCProjectContainer.instance().getSCProject(project);
		 */
	}

	private File createEAFile(String destPath, String jarEntryName) {

		// String home =
		// getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20",
		// " ");
		// Activator.log("home:" + home, Status.INFO);

		try {
			// Bundle tmpBundle = Activator.getDefault().getBundle();
			// URL pluginUrl = FileLocator.resolve(tmpBundle.getEntry("/"));
			// JarURLConnection uc =
			// (JarURLConnection)pluginUrl.openConnection();
			// JarFile pluginJarFile = uc.getJarFile();
			// ZipEntry entry = pluginJarFile.getEntry(jarEntryName);

			// Get absolute path of transfEngineBundle
			Bundle tmpBundle = Activator.getDefault().getBundle();
			URL pluginUrl = FileLocator.resolve(tmpBundle.getEntry("/"));
			String bundleAbsPath = pluginUrl.getPath();
			int tmpIdx = bundleAbsPath.indexOf("file:");
			if (tmpIdx != -1)
				bundleAbsPath = bundleAbsPath.substring(tmpIdx + 4);
			tmpIdx = bundleAbsPath.lastIndexOf(".jar");
			if (tmpIdx != -1) {
				bundleAbsPath = bundleAbsPath.substring(1, tmpIdx + 4);
			} else {
				// TODO TP: THIS IS WORK-AROUND!! second part of it is in
				// TransformationStoreImpl.getTransformationsList. This code
				// allow generation in dev mode but this should be done better
				// at this point plugin must be a jar file.If there is non, like
				// in development mode when plugin is a folder, you must make
				// this jar by your self and put it into the workspace and
				// remove version info.
				if (bundleAbsPath.endsWith("\\") || bundleAbsPath.endsWith("/")) {
					bundleAbsPath = bundleAbsPath.substring(0, bundleAbsPath.length() - 1);
				}
				bundleAbsPath = bundleAbsPath.concat(".jar");
			}
			// --------------------
			JarFile pluginJarFile = new JarFile(bundleAbsPath);
			ZipEntry entry = pluginJarFile.getEntry(jarEntryName);

			File eapFile = new File(destPath);
			InputStream in = new BufferedInputStream(pluginJarFile.getInputStream(entry));
			OutputStream out = new BufferedOutputStream(new FileOutputStream(eapFile));
			byte[] buffer = new byte[2048];
			for (;;) {
				int nBytes = in.read(buffer);
				if (nBytes <= 0)
					break;
				out.write(buffer, 0, nBytes);
			}
			out.flush();
			out.close();
			in.close();
			return eapFile;

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			Activator.logException(ex);
		}
		return null;
	}
}
