package eu.redseeds.common.log;

/**
 * Interface logging of {@link Throwable} and
 * show dialog with information about it.
 *
 * @see IActivatorSimpleExceptionLogger
 */
public interface IActivatorDialogExceptionLogger {
	public void log(Throwable e,String title, String message);
	public void log(int istatusCode,Throwable e,String title, String message);
	public void log(Throwable e);
	public void log(int istatusCode,Throwable e);
}
