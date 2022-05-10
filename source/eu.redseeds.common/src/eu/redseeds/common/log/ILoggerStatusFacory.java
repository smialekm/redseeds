/**
 *
 */
package eu.redseeds.common.log;

import org.eclipse.core.runtime.IStatus;

/**
 *	Create {@link IStatus}
 */
interface ILoggerStatusFacory {
	public IStatus createStatus(int istatusCode,Throwable e, String message, String PLUGIN_ID);
	public IStatus createStatus(Throwable e, String message, String PLUGIN_ID);
}