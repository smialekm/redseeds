package eu.redseeds.transformation.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import eu.redseeds.ea.converter.interfaces.IConversions;
import eu.redseeds.transformation.engine.interfaces.ITransformationExecution;
import eu.redseeds.transformation.store.interfaces.ITransformationDetails;
import eu.redseeds.transformation.ui.interfaces.ITransformationBrowserLogic;
import eu.redseeds.transformation.ui.impl.TransformationUiImpl;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.redseeds.transformation.ui";

	// The shared instance
	private static Activator plugin;

	private ServiceRegistration transformationUiService;

	//service reference & interfaces caching
	ITransformationBrowserLogic transfBrowser = null;
	ITransformationExecution transfExecution = null;
	ITransformationDetails transfDetails = null;
	IConversions eaConversions = null;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		//registering services
		transformationUiService = context.registerService(
				ITransformationBrowserLogic.class.getName(), 
				new TransformationUiImpl(),
				new Hashtable<String, Object>());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		//unregistering services
		if (transformationUiService!=null) {
			transformationUiService.unregister();
			transformationUiService=null;
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
		display.asyncExec(new Runnable() {
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

	
	public ITransformationBrowserLogic getITransformationBrowserLogicInstance() {
		if (transfBrowser != null) {
			return transfBrowser;
		}
		
		BundleContext bc 
			= Activator.getDefault().getBundle().getBundleContext();
		ServiceReference trBrowsecRef 
			= bc.getServiceReference(ITransformationBrowserLogic.class.getName());

		if (trBrowsecRef != null) {
			ITransformationBrowserLogic tmpTransfBrowse = (ITransformationBrowserLogic) bc
					.getService(trBrowsecRef);
			if (tmpTransfBrowse != null) {
				return tmpTransfBrowse;
			} else {
				Activator.log("Service not obtained!", Status.WARNING);
				return null;
			}
		} else {
			Activator.log("No service for " + ITransformationBrowserLogic.class.getName()
					+ " found!", Status.WARNING);
			return null;
		}
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
	
	public ITransformationDetails getITransformationDetailsInstance() {
		if (transfDetails != null) {
			return transfDetails;
		}
		BundleContext bc 
			= Activator.getDefault().getBundle().getBundleContext();
		ServiceReference trDetRef 
			= bc.getServiceReference(ITransformationDetails.class.getName());

		if (trDetRef != null) {
			ITransformationDetails tmpTransfDetail = (ITransformationDetails) bc
					.getService(trDetRef);
			if (tmpTransfDetail != null) {
				return tmpTransfDetail;
			} else {
				Activator.log("Service not obtained!", Status.WARNING);
				return null;
			}
		} else {
			Activator.log("No service for " + ITransformationDetails.class.getName()
					+ " found!", Status.WARNING);
			return null;
		}
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
