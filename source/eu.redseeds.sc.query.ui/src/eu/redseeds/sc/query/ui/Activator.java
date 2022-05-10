package eu.redseeds.sc.query.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;

import eu.redseeds.common.AbstractActivatorUIPluginWithLog;


/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractActivatorUIPluginWithLog {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.redseeds.sc.query.ui";

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
		super(PLUGIN_ID);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public static void log(String msg, int status) {
		getDefault().getLog().log(
				new Status(status, PLUGIN_ID, msg));
	}

	
	public static void log(IStatus status) {
		if ( (getDefault() != null) && (getDefault().getLog() != null))
			getDefault().getLog().log(status);
	}

	public static void log(String msg, int status, Throwable ex) {
		log(new Status(status, PLUGIN_ID, msg, ex));
	}
	
	public static void logInfo(String msg) {
		Activator.log(msg, Status.INFO, null);
	}

	public static void logWarning(String msg) {
		Activator.log(msg, Status.WARNING, null);
	}
	
	public static void logWarning(String msg, Throwable ex) {
		Activator.log(msg, Status.WARNING, ex);
	}
	
	public static void logError(String msg, Throwable ex) {
		Activator.log(msg, Status.ERROR, ex);
	}
}
