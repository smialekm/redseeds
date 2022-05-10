package eu.redseeds.common.log;


import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;

/**
 * Factory for getting helper class for {@link ILog}
 */
public class LoggerFactory {

	/**
	 * @param log instance of {@link ILog}
	 * @param plugin_id id of plugin which use this class
	 * @param display where to display dialog msg
	 * @return class for logging exception and showing dialog with information
	 */
	public static IDialogExceptionLogger getDialogExceptionLogger(ILog log, String plugin_id, Display display){
		return new DialogExceptionLogger(log, plugin_id, display);
	}

	/**
	 * @param log instance of {@link ILog}
	 * @param plugin_id plugin_id id of plugin which use this class
	 * @return class for logging exception
	 */
	public static ISimpleExceptionLogger getSimpleExceptionLogger(ILog log, String plugin_id){
		return new SimpleExceptionLogger(log, plugin_id);
	}

	/**
	 *  interface for logging exception and showing dialog with information
	 */
	public interface IDialogExceptionLogger extends IActivatorDialogExceptionLogger{
	}
	/**
	 *  interface for logging exception
	 */
	public interface ISimpleExceptionLogger extends IActivatorSimpleExceptionLogger{
	}

	/**
	 * Simple logger just write log
	 */
	private static class SimpleExceptionLogger implements ISimpleExceptionLogger {
		private final ILog log;
		public final String PLUGIN_ID;

		public SimpleExceptionLogger(ILog log, String plugin_id) {
			super();
			this.log = log;
			PLUGIN_ID = plugin_id;
		}

		public void log(final Throwable e,final String message) {
			log(IStatus.ERROR, e,message);
		}

		public void log(final Throwable e) {
			log(e, null);
		}

		@Override
		public void log(final int istatusCode,final Throwable e,final String message) {
			IStatus status = LoggerStatusFacory.getInstance().createStatus(istatusCode,e, message, PLUGIN_ID);
			log.log(status);
		}

		@Override
		public void log(int istatusCode, Throwable e) {
			log(istatusCode,e, null);
		}

	}

	/**
	 * Write log and show dialog with message
	 */
	private static class DialogExceptionLogger implements IDialogExceptionLogger {
		private final ISimpleExceptionLogger simpleLogHelper;
		private final Display display;
		public final String PLUGIN_ID;

		public DialogExceptionLogger(ILog log, String plugin_id, Display display) {
			this.simpleLogHelper = new SimpleExceptionLogger(log, plugin_id);
			this.display = display;
			this.PLUGIN_ID=plugin_id;
		}

		@Override
		public void log(Throwable e,final String title, String message) {
			log(IStatus.ERROR, e, title, message);
		}

		@Override
		public void log(Throwable e) {
			log(e, null, null);
		}

		@Override
		public void log(final int istatusCode,final Throwable e,final String title,final String message) {
			simpleLogHelper.log(e, message);
			final IStatus fstatus = LoggerStatusFacory.getInstance().createStatus(e, message, PLUGIN_ID);
			display.asyncExec(new Runnable() {
				public void run() {
					ErrorDialog.openError(null, title, null, fstatus);
				}
			});
		}

		@Override
		public void log(int istatusCode, Throwable e) {
			log(istatusCode,e, null, null);
		}
	}
}
