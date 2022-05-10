package eu.redseeds.scl.model;

import java.net.URL;
import java.util.jar.JarEntry;

/**
 * Represents transformation runtime as a set of artefacts.
 */
public class TransformationRuntime {

	/**
	 * A name of a transformation profile it corresponds to.
	 */
	private String profileName;

	/**
	 * A name of a input tg file
	 */
	private String tgInputName;

	/**
	 * A name of a result tg file
	 */
	private String tgResultName;
	
	/**
	 * An URL of a transformation jar file
	 */
	private URL jarURL;

	/**
	 * An absolute path to the transformation jar file
	 */
	private String jarFilePath;

	/**
	 * JarEntry for this transformation
	 */
	private JarEntry jarEntry;
	
	/**
	 * Main-Class for this transformation
	 */
	private String mainClass;

	/**
	 * Mola procedure to execute transformation
	 */
	private String procedure;
	
	/**
	 * Version of transformation
	 */
	private String version;

	public TransformationRuntime(){

	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getTgInputName() {
		return tgInputName;
	}

	public void setTgInputName(String tgInputName) {
		this.tgInputName = tgInputName;
	}

	public String getTgResultName() {
		return tgResultName;
	}

	public void setTgResultName(String tgResultName) {
		this.tgResultName = tgResultName;
	}

	public URL getJarURL() {
		return jarURL;
	}

	public void setJarURL(URL jarURL) {
		this.jarURL = jarURL;
	}

	public String getJarFilePath() {
		return jarFilePath;
	}

	public void setJarFilePath(String jarFilePath) {
		this.jarFilePath = jarFilePath;
	}

	public JarEntry getJarEntry() {
		return jarEntry;
	}

	public void setJarEntry(JarEntry jarEntry) {
		this.jarEntry = jarEntry;
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}