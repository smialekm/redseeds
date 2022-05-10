package eu.redseeds.owl;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin{
	
	public static final String PLUGIN_ID = "eu.redseeds.dlsimilarity";

	private static Activator plugin;
	
	public Activator() {
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}
	public static Activator getDefault() {
		return plugin;
	}

	public static void log(IStatus status) {
		if ( (getDefault() != null) && (getDefault().getLog() != null))
			getDefault().getLog().log(status);
	}

//	public static void log(IStatus status) {
//		ResourcesPlugin.getPlugin().getLog().log(status);
//	}

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
