package eu.redseeds.transformation.engine;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import eu.redseeds.ea.converter.interfaces.IConversions;
import eu.redseeds.transformation.engine.impl.TransformationEngineImpl;
import eu.redseeds.transformation.engine.interfaces.ITransformationExecution;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.redseeds.transformation.engine";

	// The shared instance
	private static Activator plugin;
	private ServiceRegistration transformationEngineService;

	//service reference & interfaces caching
	IConversions eaConversions = null;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		//registering services
		transformationEngineService = context.registerService(
				ITransformationExecution.class.getName(), 
				new TransformationEngineImpl(),
				new Hashtable<String, Object>());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		//unregistering services
		if (transformationEngineService!=null) {
			transformationEngineService.unregister();
			transformationEngineService=null;
		}
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

	public static void log(IStatus status) {
		ResourcesPlugin.getPlugin().getLog().log(status);
	}

	public static void logErrorMessage(String message) {
		log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, message, null));
	}

	public static void logException(
		Throwable e,
		final String title,
		String message) {
		if (e instanceof InvocationTargetException) {
			e = ((InvocationTargetException) e).getTargetException();
		}
		IStatus status = null;
		if (e instanceof CoreException)
			status = ((CoreException) e).getStatus();
		else {
			if (message == null)
				message = e.getMessage();
			if (message == null)
				message = e.toString();
			status = new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, message, e);
		}
		ResourcesPlugin.getPlugin().getLog().log(status);
		Display display = getStandardDisplay();
		
		StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    e.printStackTrace(pw);
	    String trace = sw.toString();
	    List<Status> childStatuses = new ArrayList<Status>();
	    childStatuses.add(new Status(IStatus.ERROR, Activator.PLUGIN_ID, trace.split(System.getProperty("line.separator"))[0]));
	    for (String line : trace.substring(trace.indexOf(System.getProperty("line.separator"))+1).split(System.getProperty("line.separator"))) {
	        childStatuses.add(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "  "+line));
	    }
	    final MultiStatus ms = new MultiStatus(Activator.PLUGIN_ID, IStatus.ERROR, childStatuses.toArray(new Status[] {}), e.getLocalizedMessage(), null);

//		display.asyncExec(new Runnable() {
		display.syncExec(new Runnable() {
			public void run() {
				ErrorDialog.openError(null, title, null, ms);
			}
		});
	}

	public static void logException(Throwable e) {
		logException(e, null, null);
	}

	public static void log(Throwable e) {
		if (e instanceof InvocationTargetException)
			e = ((InvocationTargetException) e).getTargetException();
		IStatus status = null;
		if (e instanceof CoreException)
			status = ((CoreException) e).getStatus();
		else
			status =
				new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, e.getMessage(), e);
		log(status);
	}
	
	public static Display getStandardDisplay() {
		Display display;
		display = Display.getCurrent();
		if (display == null)
			display = Display.getDefault();
		return display;
	}
	
	public static void log(String msg, int status) {
		getDefault().getLog().log(
				new Status(status, PLUGIN_ID, msg));
	}

	public IConversions getIConversionsInstance() {
		if (eaConversions != null) {
			return eaConversions;
		}
		
		BundleContext bc 
			= Activator.getDefault().getBundle().getBundleContext();
		ServiceReference trExecRef 
			= bc.getServiceReference(IConversions.class.getName());

		if (trExecRef != null) {
			IConversions eaConversions = (IConversions) bc
					.getService(trExecRef);
			if (eaConversions != null) {
				return eaConversions;
			} else {
				Activator.log("Service not obtained!", Status.WARNING);
				return null;
			}
		} else {
			Activator.log("No service for " + IConversions.class.getName()
					+ " found!", Status.WARNING);
			return null;
		}
	}
	
}
