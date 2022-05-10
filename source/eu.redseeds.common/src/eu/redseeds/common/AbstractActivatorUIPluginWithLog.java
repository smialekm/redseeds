package eu.redseeds.common;

import org.eclipse.core.runtime.ILog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import eu.redseeds.common.log.LoggerFactory;
import eu.redseeds.common.log.LoggerFactory.IDialogExceptionLogger;
import eu.redseeds.common.log.LoggerFactory.ISimpleExceptionLogger;

public abstract class AbstractActivatorUIPluginWithLog extends AbstractUIPlugin {
	// The plug-in ID
	public final String PLUGIN_ID;
	public ILog log;

	public AbstractActivatorUIPluginWithLog(String plugin_id) {
		super();
		PLUGIN_ID = plugin_id;
	}

	private ILog getDefaultLog(){
		return log;
	}

	public IDialogExceptionLogger getDialogExceptionLogger() {
		return LoggerFactory.getDialogExceptionLogger(getDefaultLog(), PLUGIN_ID, getStandardDisplay());
	}

	public ISimpleExceptionLogger getSimpleExceptionLogger() {
		return LoggerFactory.getSimpleExceptionLogger(getDefaultLog(), PLUGIN_ID);
	}

	public Display getStandardDisplay() {
		Display display;
		display = Display.getCurrent();
		if (display == null)
			display = Display.getDefault();
		return display;
	}

	public void setLog(ILog log) {
		this.log = log;
	}
}
