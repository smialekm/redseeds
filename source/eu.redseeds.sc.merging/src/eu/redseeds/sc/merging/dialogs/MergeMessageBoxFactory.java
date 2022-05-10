package eu.redseeds.sc.merging.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import eu.redseeds.common.SCProjectHelper;

/**
 * Creates message boxes used for communicating with user during merge process. 
 *
 */
public class MergeMessageBoxFactory {
	
	/**
	 * Shows message: "This Clipboard is not valid for merging."
	 * @return
	 */
	public static MessageBox clipboardNotValidMB() {
		MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_WARNING);
		mb.setMessage("This Clipboard is not valid for merging.");
		mb.setText("Merge operation aborted");
		return mb;
	}
	
	/**
	 * Shows message: "This Software Case is not valid for merging."
	 * @return
	 */
	public static MessageBox scNotValidMB() {
		MessageBox mb = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_WARNING);
		mb.setMessage("This Software Case is not valid for merging.");
		mb.setText("Merge operation aborted");
		return mb;
	}

}
