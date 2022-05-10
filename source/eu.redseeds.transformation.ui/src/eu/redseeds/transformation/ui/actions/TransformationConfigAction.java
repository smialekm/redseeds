package eu.redseeds.transformation.ui.actions;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import eu.redseeds.transformation.ui.Activator;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class TransformationConfigAction implements IWorkbenchWindowActionDelegate {
	//private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */
	public TransformationConfigAction() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {

		Activator.log("Transformation Configuration... action invoked", Status.INFO);
		String[] tmpStr = new String[2];
		String[] tmpSel = new String[1];
		tmpStr[0] = "Basic style";
		tmpStr[1] = "Keyword based style";
		tmpSel[0] = tmpStr[0];
		
	    Properties molaProperties = new Properties();
	    try {
			FileInputStream input = new FileInputStream("mola.properties");
			molaProperties.load( input ); // load properties
			if (!molaProperties.getProperty("TransformationStyle", "Basic style").equals(tmpSel[0])) {
				tmpSel[0] = tmpStr[1];
			}
			input.close();
	    } catch (IOException e) {
	    	//mola.properties file not yet created, do nothing
	    }
		
		
		ElementListSelectionDialog dlg = new ElementListSelectionDialog(null,
	            								new LabelProvider());
        dlg.setTitle("TransformationsConfiguration");
		dlg.setElements(tmpStr);
		dlg.setInitialSelections(tmpSel);
		dlg.setMultipleSelection(false);
        dlg.open();
	    Object tmpRes = dlg.getFirstResult();
	    if (tmpRes == null) return;
		
	    try {
			FileInputStream input = new FileInputStream("mola.properties");
			molaProperties.load( input ); // load properties
			input.close();
	    } catch (IOException e) {
	    	//mola.properties file not yet created, do nothing
	    }
	
	    try {
		    molaProperties.setProperty("TransformationStyle", tmpRes.toString());
	    	molaProperties.store(new FileOutputStream("mola.properties"), null);
	    } catch (Exception e) {
            Activator.log("Error during writing MOLA properties file", Status.ERROR);
	    }
	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		//this.window = window;
	}
	
}