package eu.redseeds.transformation.engine.impl;

import java.io.IOException;

import lv.lu.mii.lx.repository.ILxModelRepository;
import lv.lu.mii.lx.repository.jGralabImpl.JGralabLxModelRepository;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.osgi.framework.Bundle;
import org.xml.sax.SAXException;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.model.TransformationProfile;
import eu.redseeds.transformation.engine.Activator;
import eu.redseeds.transformation.engine.interfaces.ITransformationExecution;
import eu.remics.recovery.model.TransformationHelper;

public class TransformationEngineImpl implements ITransformationExecution {
	
	private static final String GRAPH_FILE_NAME = "sclgraph.tg";
	private static final String RES_GRAPH_FILE_NAME = "sclgraph.tg";
	
	public void execute(SCProject currSCProject, TransformationProfile transformationProfile) {
		Activator.log("Transformation \"" + transformationProfile + "(version-" +
				        transformationProfile.getTransformationRuntime().getVersion() + 
				        ")\" invoked...", Status.INFO);
		
		if (currSCProject == null)
			currSCProject = getCurrentSCProject();
		if (currSCProject == null) {
			Activator.log("Can't locate current SCProject...", Status.ERROR);
			return;
		}
		
		String projLocation = currSCProject.getEclipseProject().getLocation().toString();
		transformationProfile.getTransformationRuntime().setTgInputName(projLocation + "/" + GRAPH_FILE_NAME);
		transformationProfile.getTransformationRuntime().setTgResultName(projLocation + "/" + RES_GRAPH_FILE_NAME);
		
		String cache = TransformationHelper.removeRecoveredScenarios();
		String cache2 = TransformationHelper.backupDuplicateUseCaseStructure();
		//boolean isOK = true;
		try {
			//SCLSchema sch = SCLSchema.instance();
			//SCLGraph graphClass = sch.loadSCLGraph(transformationProfile.getTransformationRuntime().getTgInputName());
			SCLGraph graphClass = currSCProject.getSCLGraph();
//			graphClass = sch.createSCLGraph("SampleGraph", 100, 100);
			ILxModelRepository <?, ?, ?> repo =  new JGralabLxModelRepository(graphClass);
			MOLATransfomationInvoker.instance().invokeMOLATransformation(transformationProfile.getTransformationRuntime(), repo);
			//GraphIO.saveGraphToFile(transformationProfile.getTransformationRuntime().getTgResultName(), graphClass , null);
			TransformationHelper.restoreRecoveredScenarios(cache);
			TransformationHelper.restoreDuplicatedUseCaseStructure(cache2);
			currSCProject.save();
		} catch (Exception ex) {
			// IllegalArgumentException, MalformedURLException, ClassNotFoundException, 
			// NoSuchMethodException, InstantiationException, IllegalAccessException, 
			// InvocationTargetException, IOException, GraphIOException
			// ex.printStackTrace();
			Activator.logException(ex);
			//isOK = false;
			try {
				TransformationHelper.restoreRecoveredScenarios(cache);
				TransformationHelper.restoreDuplicatedUseCaseStructure(cache2);
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		SCProjectHelper.refreshUnassignedScenariosList();
		Activator.log("Transformation completed ...", Status.INFO);

/*
		if (isOK)
			Activator.getStandardDisplay().asyncExec(new Runnable() {
					             public void run() {
					            	 MessageDialog.openInformation(null, "Transformations", "Transformation completed ...");
					             }});
*/		
		
		// in this case run also data export to EA 
		//if (isOK && transformationProfile.getType().getType().equals("UML_EA_TRANSFORMATION")) {
			//IConversions eaConversions = Activator.getDefault().getIConversionsInstance();	
			//eaConversions.convertTGtoEA(true);
		//}
	}
	 
/*
		// statically linked version !!!!
	  	public void execute2(TransformationProfile transformationProfile) {

		Activator.log("Transformation execute invoked ...", Status.INFO);
		
		try {

			SCLSchema sch = SCLSchema.instance();
			SCLGraph graphClass = sch.loadSCLGraph(transformationProfile.getTransformationRuntime().getTgInputName());
			ILxModelRepository repo =  new JGralabLxModelRepository(graphClass);
			MolaModel m = new MolaModel( repo );
			if (transformationProfile.getType().getType().equals("REQ_ARCH_TRANSFORMATION")) 
				m.L0_Func_RSLtoA_Main_RequirementsToArchitecture();
			else if (transformationProfile.getType().getType().equals("ARCH_DD_TRANSFORMATION"))
				m.L0_Func_DetailedDesign_dd_Main();
			else if (transformationProfile.getType().getType().equals("UML_EA_TRANSFORMATION"))
				m.L0_Func_ExportToEA_ea_main();
			else {
				Activator.log("unknown transformation type:" + transformationProfile.getType().getType(), Status.INFO);
				return;
			}
				
			GraphIO.saveGraphToFile(transformationProfile.getTransformationRuntime().getTgResultName(), graphClass , null);
			
		} catch (Exception ex) {
			// TODO 
			// IllegalArgumentException, MalformedURLException, ClassNotFoundException, 
			// NoSuchMethodException, InstantiationException, IllegalAccessException, 
			// InvocationTargetException, IOException, GraphIOException
//			ex.printStackTrace();
			Activator.logException(ex);
		}
		
		Activator.log("completed ...", Status.INFO);
	}
*/	

/*	
	public void execute3(TransformationProfile transformationProfile) {
	    // TODO method stub
		Activator.log("Transformation execute invoked ...", Status.INFO);
		URL installUrl = Platform.getInstallLocation().getURL();
		Activator.log(installUrl.toString(), Status.INFO);
		
		org.osgi.framework.Bundle tmpBundle = Activator.getDefault().getBundle();
		
		Activator.log("Bundle location:" + tmpBundle.getLocation(), Status.INFO);
		java.util.Enumeration en = tmpBundle.getEntryPaths("/");
		for (;en.hasMoreElements();) {
			Activator.log("En element:" + en.nextElement(), Status.INFO);
	     }
		en = tmpBundle.findEntries("lib/", "*", true);
		for (;en.hasMoreElements();) {
			Activator.log("FindEntries element:" + ((URL)en.nextElement()), Status.INFO);
	     }

		URL tmpUrl = tmpBundle.getEntry(tmpBundle.findEntries("lib/", "*", true).nextElement().toString());
		if (tmpUrl != null)
		  Activator.log("1:" + tmpUrl.toString(), Status.INFO);
		tmpUrl = tmpBundle.getEntry("lib/transformations/req_arch.jar");
		if (tmpUrl != null)
		  Activator.log("2:" + tmpUrl.toString(), Status.INFO);
		tmpUrl = tmpBundle.getEntry("/lib/transformations/req_arch.jar"); //"//159/lib/RSLtoA.jar"
		if (tmpUrl != null)
		  Activator.log("3:" + tmpUrl.toString(), Status.INFO);
		
		
		try {
			en = tmpBundle.getResources("RSLtoA.jar");
		} catch (Exception ex) {
			Activator.logException(ex);
		}
		if (en != null)
			for (;en.hasMoreElements();) {
				Activator.log("getResources element:" + ((URL)en.nextElement()), Status.INFO);
		     }
		
		tmpUrl = Activator.getDefault().find(new org.eclipse.core.runtime.Path("."));
		if (tmpUrl != null)
			  Activator.log("4:" + tmpUrl.toString(), Status.INFO);
		
		tmpUrl = org.eclipse.core.runtime.FileLocator.find(tmpBundle, new org.eclipse.core.runtime.Path("."), null);
		if (tmpUrl != null)
			  Activator.log("5:" + tmpUrl.toString(), Status.INFO);

//
//   temporary implementation !!!
//   allows execute hardcoded transformations saved inside this plugin
//

		Activator.log("try to execute ...", Status.INFO);
		
		String tmpUlr = installUrl.toString() + tmpBundle.getLocation().split("@")[1];
		Activator.log("bbb:" + tmpUlr, Status.INFO);
		try {
			Activator.log("ccc:" + new URL(tmpUlr), Status.INFO);
	    	URL u = new URL("jar", "", new URL(tmpUlr) + "!/lib/UMLtoEA.jar");
	    	JarURLConnection uc = (JarURLConnection)u.openConnection();
	    	Attributes attr = uc.getMainAttributes();
	    	Activator.log("attributes:" + attr.values(), Status.INFO);
	    	attr = uc.getAttributes();
	    	if (attr != null)
	    		Activator.log("attributes222:" + attr.values(), Status.INFO);
	    	else
	    		Activator.log("attributes222:NULL", Status.INFO);

		} catch (Exception ex) {
			Activator.logException(ex);
		}
		
		tmpUrl = tmpBundle.getEntry("/");
		Activator.log("tmpBundle.getEntry():" + tmpUrl, Status.INFO);
		try {
			tmpUrl = org.eclipse.core.runtime.FileLocator.resolve(tmpUrl);
			Activator.log("FileLocator.resolve():" + tmpUrl, Status.INFO);
			tmpUrl = org.eclipse.core.runtime.FileLocator.toFileURL(tmpUrl);
			Activator.log("FileLocator.toFileURL():" + tmpUrl, Status.INFO);
		} catch (Exception ex) {
			Activator.logException(ex);
		}

		tmpUlr = tmpUrl.toString().replace('\\', '/');
//		tmpUlr = tmpUlr.substring(4); //eliminate "jar:" 
		Activator.log("ddd:" + tmpUlr, Status.INFO);
		try {
			Activator.log("eee:" + new URL(tmpUlr), Status.INFO);
//	    	URL u = new URL("jar", "", new URL(tmpUlr) + "lib/UMLtoEA.jar");
	    	URL u = new URL(new URL(tmpUlr) + "lib/transformations/req_arch.jar");
			Activator.log("eee URL u:" + u, Status.INFO);
	    	JarURLConnection uc = (JarURLConnection)u.openConnection();
	    	Attributes attr = uc.getMainAttributes();
	    	Activator.log("attributes2:" + attr.values(), Status.INFO);
	    	attr = uc.getAttributes();
	    	if (attr != null)
	    		Activator.log("attributes222:" + attr.values(), Status.INFO);
	    	else
	    		Activator.log("attributes222:NULL", Status.INFO);

	    	java.util.jar.Manifest tmpManif = uc.getManifest();
	    	Activator.log("attributesManifest" + tmpManif.getEntries(), Status.INFO);
	    	
	    	java.util.jar.JarFile tmpJarFile = uc.getJarFile();
	    	tmpManif = tmpJarFile.getManifest();
	    	Activator.log("attributesManifest2" + tmpManif.getEntries(), Status.INFO);
	    	Activator.log("attributesManifest3" + tmpJarFile.getName(), Status.INFO);
		} catch (Exception ex) {
			Activator.logException(ex);
		}

		java.util.jar.JarFile tmpJarFile = null;
		java.util.jar.JarFile tmpJarFile2 = null;
		try {
			tmpUrl = org.eclipse.core.runtime.FileLocator.resolve(tmpBundle.getEntry("/"));
			tmpUlr = tmpUrl.toString().replace('\\', '/');
//			tmpUlr = tmpUlr.substring(4); //eliminate "jar:" 
			Activator.log("last1:" + tmpUlr, Status.INFO);
			Activator.log("last2:" + new URL(tmpUlr), Status.INFO);
//	    	URL u = new URL("jar", "", new URL(tmpUlr) + "lib/UMLtoEA.jar");
	    	URL u = new URL(new URL(tmpUlr),"/lib/transformations/req_arch.jar");

	    	Activator.log("last3 URL u:" + u, Status.INFO);
	    	JarURLConnection uc = (JarURLConnection)u.openConnection();
	    	Attributes attr = uc.getMainAttributes();
	    	Activator.log("attributes2:" + attr.values(), Status.INFO);
	    	attr = uc.getAttributes();
	    	if (attr != null)
	    		Activator.log("attributes222:" + attr.values(), Status.INFO);
	    	else
	    		Activator.log("attributes222:NULL", Status.INFO);

	    	java.util.jar.Manifest tmpManif = uc.getManifest();
	    	Activator.log("attributesManifest" + tmpManif.getEntries(), Status.INFO);
	    	
	    	tmpJarFile = uc.getJarFile();
	    	tmpManif = tmpJarFile.getManifest();
	    	Activator.log("attributesManifest2" + tmpManif.getEntries(), Status.INFO);
	    	Activator.log("attributesManifest3" + tmpJarFile.getName(), Status.INFO);
	    	
	    	tmpJarFile2 = new java.util.jar.JarFile(tmpJarFile.getName());
	    	
			Activator.log("attributesManifest33" + tmpJarFile2.getJarEntry("lib/transformations/req_arch.jar"), Status.INFO);
			java.util.jar.JarInputStream jis = new java.util.jar.JarInputStream(tmpJarFile2.getInputStream(tmpJarFile2.getJarEntry("lib/transformations/req_arch.jar")));
			tmpManif = jis.getManifest();
			Activator.log("attributesManifest4" + tmpManif.getEntries(), Status.INFO);
			
			java.util.zip.ZipEntry zipEntr = jis.getNextEntry();
			while (zipEntr != null) {
				Activator.log("zipEntr" + zipEntr, Status.INFO);
				zipEntr = jis.getNextEntry();
			}

			
			tmpManif = jis.getManifest();
			Activator.log("getMainAttributes" + tmpManif.getMainAttributes().values(), Status.INFO); ////// is ok !!!!!!
			Activator.log("Main Class !!!:" + jis.getManifest().getMainAttributes().getValue(Attributes.Name.MAIN_CLASS), Status.INFO); ////// is ok !!!!!!
			
			
			jis.close();

	    	
		} catch (Exception ex) {
			Activator.logException(ex);
		}
		
		en = tmpJarFile.entries();
		for (;en.hasMoreElements();) {
			Activator.log("En element jarFile:" + en.nextElement(), Status.INFO);
	     }


//		transformationProfile.getTransformationRuntime().setJarName(tmpUlr + "lib/transformations/req_arch.jar");	
//		Activator.log("----------------:" + transformationProfile.getTransformationRuntime().getJarName(), Status.INFO);

		try {
			SCLSchema sch = SCLSchema.instance();
			Activator.log("tesc1 ...", Status.INFO);
			SCLGraph graphClass = sch.loadSCLGraph(transformationProfile.getTransformationRuntime().getTgInputName());
			Activator.log("tesc2 ...", Status.INFO);
//			graphClass = sch.createSCLGraph("SampleGraph", 100, 100);
			ILxModelRepository repo =  new JGralabLxModelRepository(graphClass);
			Activator.log("tesc3 ...", Status.INFO);
//			MOLATransfomationInvoker.instance().
//			               invokeMOLATransformation(transformationProfile.getTransformationRuntime().getJarName(), repo);
//			MOLATransfomationInvoker.instance().invokeMOLATransformation(tmpJarFile.getName(), "lib/transformations/req_arch.jar", repo);
			MOLATransfomationInvoker.instance().invokeMOLATransformation(transformationProfile.getTransformationRuntime(), repo);
			Activator.log("tesc4 ...", Status.INFO);
			GraphIO.saveGraphToFile(transformationProfile.getTransformationRuntime().getTgResultName(), graphClass , null);
			Activator.log("tesc5 ...", Status.INFO);
		} catch (Exception ex) {
			// TODO 
			// IllegalArgumentException, MalformedURLException, ClassNotFoundException, 
			// NoSuchMethodException, InstantiationException, IllegalAccessException, 
			// InvocationTargetException, IOException, GraphIOException
//			ex.printStackTrace();
			Activator.logException(ex);
		}
		
		Activator.log("completed ...", Status.INFO);
	}
*/	
	

	public Bundle getTransformationEngineBundle() {
		return Activator.getDefault().getBundle();
	}

	private SCProject getCurrentSCProject() {
	
		//workaround for SCProjectHelper.getIProject(currSelection)
		ITreeSelection currSelection = (ITreeSelection) SCProjectHelper.getSelection();
		if (currSelection == null) return null;
		TreePath[] tr = currSelection.getPaths();
		if (tr == null || tr.length < 1) return null;
		// ------------
		
		IProject project = SCProjectHelper.getIProject(currSelection);
		return SCProjectContainer.instance().getSCProject(project);
		
/*		
		IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench == null) {
			Activator.log("Can't locate IWorkbench...", Status.ERROR);
			return null;
		}
		
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		if (window == null) {
			Activator.log("Can't locate IWorkbenchWindow...", Status.ERROR);
			return null;
		}

		IWorkbenchPage activePage = window.getActivePage();
		if (activePage == null) {
			Activator.log("Can't locate IWorkbenchPage...", Status.ERROR);
			return null;
		}
		
		ISelection selection = activePage.getSelection();
		if (selection == null) {
			Activator.log("Can't get ISelection...", Status.ERROR);
			return null;
		}

		Object selectedElement = ((IStructuredSelection)selection).getFirstElement();
		if (selectedElement == null) {
			Activator.log("Can't get selectedElement...", Status.ERROR);
			return null;
		}

		ITreeSelection treeSelection = (ITreeSelection)selection;
		if (selection == null) {
			Activator.log("Can't get ITreeSelection...", Status.ERROR);
			return null;
		}

		TreePath[] paths = treeSelection.getPathsFor(selectedElement);
		IProject project = null;
		if (paths.length > 0){
			if (paths[0].getFirstSegment() instanceof IProject){
				project = (IProject)paths[0].getFirstSegment();
			} else return null;
		} else return null;
		
		return SCProjectContainer.instance().getSCProject(project);
*/		
	}
}
