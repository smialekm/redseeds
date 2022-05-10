package eu.redseeds.common.log;
/**
 * Interface for logging of {@link Throwable}.
 * @see IActivatorDialogExceptionLogger
 */
public interface IActivatorSimpleExceptionLogger {
	public void log(Throwable e, String message);
	public void log(int istatusCode,Throwable e, String message);
	public void log(Throwable e);
	public void log(int istatusCode,Throwable e);
}
