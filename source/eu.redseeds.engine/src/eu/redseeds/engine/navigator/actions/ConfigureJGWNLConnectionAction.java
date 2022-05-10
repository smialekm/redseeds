package eu.redseeds.engine.navigator.actions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import eu.redseeds.common.Activator;
import eu.redseeds.common.Constants;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.engine.dialogs.ConfigureJGWNLConnectionDialog;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;

public class ConfigureJGWNLConnectionAction implements IWorkbenchWindowActionDelegate {

	@Override
	public void run(IAction action) {
		ConfigureJGWNLConnectionDialog dialog = new ConfigureJGWNLConnectionDialog(SCProjectHelper.getShell());
		String url = Constants.getJGWNLAddress();
		dialog.setJgwnlAddress(url);
		String newURL = dialog.open();
		if(newURL != null) {//save url to properties file
			Properties jgwnlProps = new Properties();
			jgwnlProps.setProperty(Constants.JGWNL_SERVER_ADDRESS_PROPERTY_KEY, newURL);
			try {
				jgwnlProps.store(new FileOutputStream(Constants.JGWNL_PROPERTIES_FILE_NAME), 
						Constants.JGWNL_PROPERTIES_FILE_COMMENT);
			} catch (FileNotFoundException e) {
				Activator.log("File " + Constants.JGWNL_PROPERTIES_FILE_NAME 
						+ " not found.", IStatus.WARNING);
				return;
			} catch (IOException e) {
				Activator.log("Problem with writing to file " + Constants.JGWNL_PROPERTIES_FILE_NAME 
						+ ".", IStatus.WARNING);
				return;
			}
			//connect to new url
			RemoteJGWNL.getInstance().connect(newURL);
		}
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		
	}

}
