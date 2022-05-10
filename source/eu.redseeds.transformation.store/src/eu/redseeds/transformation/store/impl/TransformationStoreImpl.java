package eu.redseeds.transformation.store.impl;

//import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import java.util.jar.Attributes;

//import org.eclipse.core.runtime.Platform;
//import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;
import org.eclipse.core.runtime.FileLocator;
//import org.eclipse.osgi.baseadaptor.bundlefile.DirBundleFile;

import eu.redseeds.scl.model.TransformationProfile;
import eu.redseeds.scl.model.TransformationRuntime;
import eu.redseeds.scl.model.TransformationType;
import eu.redseeds.transformation.engine.interfaces.ITransformationExecution;
import eu.redseeds.transformation.store.interfaces.ITransformationDetails;
import eu.redseeds.transformation.store.interfaces.ITransformationExchange;
import eu.redseeds.transformation.store.Activator;

public class TransformationStoreImpl implements ITransformationDetails, ITransformationExchange {

	private final static String TRANSFLIB_PREFIX = "lib/transformations";
	private final static String TGFILE_INPUT = "sclgraph.tg";
	private final static String TGFILE_OUTPUT = "sclgraph_res.tg";

	public TransformationProfile getTransformationDetails(TransformationProfile transformationProfile) {
		// TODO method stub
		Activator.log("getTransformationDetails invoked ...", Status.INFO);
		return null;
	}

	public List<TransformationProfile> getTransformationsList() {
		Activator.log("getTransformationsList invoked ...", Status.INFO);

		ITransformationExecution transfExecution = Activator.getDefault().getITransformationExecutionInstance();
		Bundle transfEngineBundle = transfExecution.getTransformationEngineBundle();

		List<TransformationProfile> tmpTrList = new ArrayList<TransformationProfile>();
		try {

			// Get absolute path of transfEngineBundle
			URL pluginUrl = FileLocator.resolve(transfEngineBundle.getEntry("/"));
			String bundleAbsPath = pluginUrl.getPath();
			// Activator.log("bundleAbsPath1->" + bundleAbsPath, Status.INFO);
			int tmpIdx = bundleAbsPath.indexOf("file:");
			// Activator.log("tmpIdx1->" + tmpIdx, Status.INFO);
			if (tmpIdx != -1)
				bundleAbsPath = bundleAbsPath.substring(tmpIdx + 4);
			tmpIdx = bundleAbsPath.lastIndexOf(".jar");
			// Activator.log("tmpIdx2->" + tmpIdx, Status.INFO);
			if (tmpIdx != -1) {
				bundleAbsPath = bundleAbsPath.substring(1, tmpIdx + 4);
			} else {
				// TODO TP: THIS IS WORK-AROUND!! second part of it is in
				// EAConverterImpl.createEAFile. This code allow generation in
				// dev mode but this should be done better
				// at this point plugin must be a jar file.If there is non, like
				// in development mode when plugin is a folder, you must make
				// this jar by your self and put it into the workspace and
				// remove version info.
				if (bundleAbsPath.endsWith("\\") || bundleAbsPath.endsWith("/")) {
					bundleAbsPath = bundleAbsPath.substring(0, bundleAbsPath.length() - 1);
				}
				bundleAbsPath = bundleAbsPath.concat(".jar");
			}
			// Activator.log("bundleAbsPath2->" + bundleAbsPath, Status.INFO);
			// --------------------
			JarFile pluginJarFile = new JarFile(bundleAbsPath);
			// Activator.log("pluginJarFile->" + pluginJarFile.getName(),
			// Status.INFO);

			// was some problems for Alberts
			// URL pluginUrl =
			// FileLocator.resolve(transfEngineBundle.getEntry("/"));
			// Activator.log("Bundle location->" +
			// transfEngineBundle.getLocation(), Status.INFO);
			// Activator.log("transfEngineBundle.getEntry()->" +
			// transfEngineBundle.getEntry("/"), Status.INFO);
			// Activator.log("pluginUrl->" + pluginUrl, Status.INFO);
			// JarURLConnection uc =
			// (JarURLConnection)pluginUrl.openConnection();
			// JarFile pluginJarFile = uc.getJarFile();
			// Activator.log("pluginJarFile->" + pluginJarFile.getName(),
			// Status.INFO);

			/*
			 * String tgFileLocation = null;
			 *
			 * URL installUrl = Platform.getInstallLocation().getURL(); if
			 * (installUrl == null){ Activator.log("can't find
			 * MOLATransformations folder ...", Status.ERROR); jarFile = "<unknown
			 * MOLATransformations folder>/"; tgFileLocation = "<unknown
			 * MOLATransformations folder>/"; } else { jarFile =
			 * installUrl.toString() + transformationFolder + "/";
			 * tgFileLocation = installUrl.getFile() + transformationFolder +
			 * "/"; }
			 */

			TransformationProfile tmpTrProf = null;

			Enumeration<JarEntry> jarEntries = pluginJarFile.entries();
			while (jarEntries.hasMoreElements()) {
				JarEntry entry = (JarEntry) jarEntries.nextElement();
				if (entry.isDirectory())
					continue;
				String jarEntryName = entry.getName();

				if (jarEntryName.startsWith(TRANSFLIB_PREFIX)) {
					tmpTrProf = createTransformationProfileFromJar(pluginUrl, pluginJarFile, entry);
					tmpTrList.add(tmpTrProf);
				}
			}
		} catch (Exception ex) {
			Activator.logException(ex);
		}

		return tmpTrList;
	}

	public TransformationProfile getTransfomation(int transformationId) {
		// TODO method stub
		Activator.log("getTransfomation invoked ...", Status.INFO);
		return null;
	}

	private TransformationProfile createTransformationProfileFromJar(URL pluginUrl, JarFile pluginJarFile, JarEntry entry) throws IOException {

		// Activator.log("createTransformationProfileFromJar: " + pluginUrl + "
		// : " +
		// pluginJarFile.getName() + " : " + entry.getName(), Status.INFO);

		TransformationProfile tmpTrProf = new TransformationProfile();
		TransformationType tmpTransfType = new TransformationType();
		TransformationRuntime tmpTransfRuntime = new TransformationRuntime();
		tmpTrProf.setType(tmpTransfType);
		tmpTrProf.setTransformationRuntime(tmpTransfRuntime);

		tmpTransfRuntime.setJarURL(pluginUrl);
		tmpTransfRuntime.setJarFilePath(pluginJarFile.getName());
		tmpTransfRuntime.setJarEntry(entry);
		tmpTransfRuntime.setTgInputName(TGFILE_INPUT);
		tmpTransfRuntime.setTgResultName(TGFILE_OUTPUT);
		// tmpTransfRuntime.setTgInputName(tgFileLocation + TG_FILE);
		// tmpTransfRuntime.setTgResultName(tgFileLocation + TG_FILE);

		JarInputStream jis = new JarInputStream(pluginJarFile.getInputStream(entry));
		if (jis == null) {
			Activator.log("Unable to load transformation jar " + pluginJarFile.getName() + "!/" + entry.getName() + " using " + this, Status.ERROR);
			return tmpTrProf;
		}

		Manifest manifest = jis.getManifest();
		jis.close();
		if (manifest != null) {
			tmpTrProf.setName(manifest.getMainAttributes().getValue("Transformation-Description"));
			tmpTransfRuntime.setProfileName(tmpTrProf.getName());
			tmpTransfType.setType(manifest.getMainAttributes().getValue("Transformation-Type"));
			tmpTransfRuntime.setMainClass(manifest.getMainAttributes().getValue(Attributes.Name.MAIN_CLASS));
			tmpTransfRuntime.setProcedure(manifest.getMainAttributes().getValue("Transformation-Procedure"));
			tmpTransfRuntime.setVersion(manifest.getMainAttributes().getValue("Transformation-Version"));
		}

		return tmpTrProf;
	}
}
