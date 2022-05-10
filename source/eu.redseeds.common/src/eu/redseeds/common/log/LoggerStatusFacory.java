/**
 *
 */
package eu.redseeds.common.log;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
/**
 * Strategy for creating {@link IStatus}
 */
public class LoggerStatusFacory implements ILoggerStatusFacory {

	private static ILoggerStatusFacory logHelperStatusFacory=new LoggerStatusFacory();

	private LoggerStatusFacory() {
	}

	public static ILoggerStatusFacory getInstance() {
		return logHelperStatusFacory;
	}

	@Override
	public IStatus createStatus(final Throwable e, final String message, final String PLUGIN_ID) {
		return createStatus(IStatus.ERROR,e, message, PLUGIN_ID);
	}

	@Override
	public IStatus createStatus(final int istatusCode,final Throwable e,final String message,final String PLUGIN_ID) {
		String correctMessage = message;
		Throwable correctE = e;

		if (correctE instanceof InvocationTargetException) {
			correctE = ((InvocationTargetException) correctE).getTargetException();
		}
		IStatus status = null;
		if (correctE instanceof CoreException)
			status = ((CoreException) correctE).getStatus();
		else {
			if (correctMessage == null)
				correctMessage = correctE.getMessage();
			if (correctMessage == null)
				correctMessage = correctE.toString();
			status = new Status(istatusCode, PLUGIN_ID, IStatus.OK, correctMessage, correctE);
		}
		return status;
	}

}