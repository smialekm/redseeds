package eu.redseeds.engine;

import java.net.URL;
import java.util.logging.Level;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.uni_koblenz.jgralab.JGraLab;
import eu.redseeds.common.AbstractActivatorUIPluginWithLog;
import eu.redseeds.sc.current.repository.interfaces.ICurrRepoContent;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractActivatorUIPluginWithLog {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.redseeds.engine";

	// The shared instance
	private static Activator plugin;

	//service reference & interfaces caching
	ICurrRepoContent currRepo = null;

	/**
	 * The constructor
	 */
	public Activator() {
		super(PLUGIN_ID);
		JGraLab.setLogLevel(Level.SEVERE);
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

	public static void log(String msg, int status) {
		getDefault().getLog().log(
				new Status(status, PLUGIN_ID, msg));
	}

	public ICurrRepoContent getICurrRepoContentInstance() {
		if (currRepo != null) {
			return currRepo;
		}

		BundleContext bc = Activator.getDefault().getBundle().getBundleContext();
		ServiceReference currRepoRef = bc.getServiceReference(ICurrRepoContent.class.getName());

		if (currRepoRef != null) {
			ICurrRepoContent currRepo = (ICurrRepoContent) bc
					.getService(currRepoRef);
			if (currRepo != null) {
				return currRepo;
			} else {
				Activator.log("Service not obtained!", Status.WARNING);
				return null;
			}
		} else {
			Activator.log("No service for " + ICurrRepoContent.class.getName()
					+ " found!", Status.WARNING);
			return null;
		}
	}

	public ImageDescriptor createImageDescriptorFor(String id) {
		  URL url = Platform.getBundle(PLUGIN_ID).getEntry(id);
		  return ImageDescriptor.createFromURL(url);
	}

}
