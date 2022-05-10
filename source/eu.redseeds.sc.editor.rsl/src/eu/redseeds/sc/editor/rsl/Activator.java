package eu.redseeds.sc.editor.rsl;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;

import eu.redseeds.common.AbstractActivatorUIPluginWithLog;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractActivatorUIPluginWithLog {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.redseeds.sc.editor.rsl";

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
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		setLog(getDefault().getLog());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
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
		getDefault().getLog().log(new Status(status, PLUGIN_ID, msg));
	}

	public ImageDescriptor createImageDescriptorFor(String id) {
		URL url = Platform.getBundle(PLUGIN_ID).getEntry(id);
		return ImageDescriptor.createFromURL(url);
	}

}
