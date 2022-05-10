package eu.redseeds.transformation.store;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import eu.redseeds.transformation.engine.interfaces.ITransformationExecution;
import eu.redseeds.transformation.store.interfaces.ITransformationDetails;
import eu.redseeds.transformation.store.interfaces.ITransformationExchange;
import eu.redseeds.transformation.store.impl.TransformationStoreImpl;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.redseeds.transformation.store";

	// The shared instance
	private static Activator plugin;

	private ServiceRegistration transformationStoreService;

	//service reference & interfaces caching
	ITransformationExecution transfExecution = null;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		String [] implInterf  = new String [2];
		implInterf[0] = ITransformationDetails.class.getName();
		implInterf[1] = ITransformationExchange.class.getName();
		//registering services
		transformationStoreService = context.registerService(
				implInterf,
				new TransformationStoreImpl(),
				new Hashtable<String, Object>());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		//unregistering services
		if (transformationStoreService!=null) {
			transformationStoreService.unregister();
			transformationStoreService=null;
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
		final IStatus fstatus = status;
		display.syncExec(new Runnable() {
			public void run() {
				ErrorDialog.openError(null, title, null, fstatus);
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

	public ITransformationExecution getITransformationExecutionInstance() {
		if (transfExecution != null) {
			return transfExecution;
		}

		BundleContext bc
			= Activator.getDefault().getBundle().getBundleContext();
		ServiceReference trExecRef
			= bc.getServiceReference(ITransformationExecution.class.getName());

		if (trExecRef != null) {
			ITransformationExecution tmpTransfExecution = (ITransformationExecution) bc
					.getService(trExecRef);
			if (tmpTransfExecution != null) {
				return tmpTransfExecution;
			} else {
				Activator.log("Service not obtained!", Status.WARNING);
				return null;
			}
		} else {
			Activator.log("No service for " + ITransformationExecution.class.getName()
					+ " found!", Status.WARNING);
			return null;
		}
	}

}
