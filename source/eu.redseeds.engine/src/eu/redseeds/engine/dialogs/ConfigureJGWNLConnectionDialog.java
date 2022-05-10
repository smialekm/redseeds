package eu.redseeds.engine.dialogs;

import java.awt.Checkbox;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


import de.uni_koblenz.jgwnl.JGWNL;
import eu.redseeds.common.SCProjectHelper;

/**
 * Allows to configure and test connection to a JGWNL server. 
 * When closed, returns 
 * <br>a) null - user canceled configuration
 * <br>b) valid rmi server url (String)
 *
 */
public class ConfigureJGWNLConnectionDialog extends Dialog {

	private static String MESSAGE 
		= "URL to JGWNL server (example: \"rmi://127.0.0.1/JGWNL\")";
	
	private static String TERMINOLOGY_STYLE_DESC 
	= "Select the terminology style";
	
	private static String IP_ADDRESS_PART_REG_EXPRESSION 
		= "(([0-2]*[0-9]*[0-9]+)\\.([0-2]*[0-9]*[0-9]+)\\.([0-2]*[0-9]*[0-9]+)\\.([0-2]*[0-9]*[0-9]+))";
	
	private static String RMI_IP_ADDRESS_REG_EXPRESSION
		= "^(rmi://" + IP_ADDRESS_PART_REG_EXPRESSION + "/JGWNL)$";
	
	private static String URL_PART_REG_EXPRESSION
		= "(?:(?:w{3}\\.)?(?:[a-zA-Z0-9/;\\?&=:\\-_\\$\\+!\\*'\\(\\|\\\\~\\[\\]#%\\.])+)";
	
	private static String RMI_URL_REG_EXPRESSION
	= "^(rmi://" + URL_PART_REG_EXPRESSION + "/JGWNL)$";

	/**
	 * value returned by the dialog
	 */
	private String jgwnlAddress = "";

	public ConfigureJGWNLConnectionDialog(Shell parent) {
		super(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		setText("Configure Terminology server connection");
	}

	/**
	 * Opens the dialog and returns the input
	 * 
	 * @return String
	 */
	public String open() {
		// Create the dialog window
		Shell shell = new Shell(getParent(), getStyle());
		shell.setText(getText());
		createContents(shell);
		shell.pack();
		placeDialogInCenter(getParent(), shell);
		shell.open();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		// Return the entered value, or null
		return getJgwnlAddress();
	}

	/**
	 * Creates the dialog's contents
	 * 
	 * @param shell the dialog window
	 */
	private void createContents(final Shell shell) {
		shell.setLayout(new GridLayout(3, true));

		// Show the message
		Label labelTerminologyStyle = new Label(shell, SWT.NONE);
		labelTerminologyStyle.setText(TERMINOLOGY_STYLE_DESC);
		GridData data = new GridData();
		data.horizontalSpan = 3;
		labelTerminologyStyle.setLayoutData(data);
		
		// Create the checkbox for auto assign sense option
		final Button aasCheckBox = new Button(shell, SWT.CHECK);
		aasCheckBox.setText("Auto add and assign");
		data = new GridData(GridData.FILL_HORIZONTAL);
		aasCheckBox.setLayoutData(data);
		//okButton.setEnabled(false);//initial state
		
		// Create the checkbox for auto assign sense option
		final Button aaasCheckBox = new Button(shell, SWT.CHECK);
		aaasCheckBox.setText("Auto assign");
		data = new GridData(GridData.FILL_HORIZONTAL);
		aaasCheckBox.setLayoutData(data);
		//okButton.setEnabled(false);//initial state
		
		// Show the blank line
		Label blankLine = new Label(shell, SWT.NONE);
		blankLine.setText(" ");
		data = new GridData();
		data.horizontalSpan = 3;
		blankLine.setLayoutData(data);
		
		// Show the message
		Label label = new Label(shell, SWT.NONE);
		label.setText(MESSAGE);
		data = new GridData();
		data.horizontalSpan = 3;
		label.setLayoutData(data);

		// Display the input box
		final Text text = new Text(shell, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		text.setLayoutData(data);
		text.setText(getJgwnlAddress());
		
		// Create the Test Connection button and add a handler
		// so that pressing it test entered connection url 
		final Button testButton = new Button(shell, SWT.PUSH);
		testButton.setText("Test connection");
		data = new GridData(GridData.FILL_HORIZONTAL);
		testButton.setLayoutData(data);
		
		// Create the OK button and add a handler
		// so that pressing it will set return value
		// to the entered value
		final Button okButton = new Button(shell, SWT.PUSH);
		okButton.setText("Apply");
		data = new GridData(GridData.FILL_HORIZONTAL);
		okButton.setLayoutData(data);
		okButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setJgwnlAddress(text.getText());
				shell.close();
			}
		});
		okButton.setEnabled(false);//initial state

		// Create the cancel button and add a handler
		// so that pressing it will set return value to null
		Button cancelButton = new Button(shell, SWT.PUSH);
		cancelButton.setText("Cancel");
		data = new GridData(GridData.FILL_HORIZONTAL);
		cancelButton.setLayoutData(data);
		cancelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setJgwnlAddress(null);
				shell.close();
			}
		});

		// Set the Cancel button as the default, so
		// user can type input and press Enter
		// to dismiss
		shell.setDefaultButton(cancelButton);
		
		//listeners
		testButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				JGWNL jgwnl = null;
				try {
					Display.getCurrent().getCursorControl().setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_WAIT));
					jgwnl = (JGWNL) Naming.lookup(text.getText());
				} catch (MalformedURLException e1) {
					connectionFailedMB();
					okButton.setEnabled(false);
					return;
				} catch (RemoteException e1) {
					connectionFailedMB();
					okButton.setEnabled(false);
					return;
				} catch (NotBoundException e1) {
					connectionFailedMB();
					okButton.setEnabled(false);
					return;
				}
				finally {
					Display.getCurrent().getCursorControl().setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
				}
				if(jgwnl == null) {
					connectionFailedMB();
					okButton.setEnabled(false);
				}
				else {
					okButton.setEnabled(true);
					connectionSuccessfulMB();
				}
			}
		});
		
		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				testButton.setEnabled(
						((Text)e.widget).getText().matches(RMI_IP_ADDRESS_REG_EXPRESSION)
						|| ((Text)e.widget).getText().matches(RMI_URL_REG_EXPRESSION)
					);
				okButton.setEnabled(false);//always disable if not tested
			}
		});
		
	}
	
	private void placeDialogInCenter(Shell parent, Shell shell){
		Rectangle parentSize = parent.getBounds();
		Rectangle mySize = shell.getBounds();

		int locationX, locationY;
		locationX = (parentSize.width - mySize.width)/2+parentSize.x;
		locationY = (parentSize.height - mySize.height)/2+parentSize.y;

		shell.setLocation(new Point(locationX, locationY));
	}

	public String getJgwnlAddress() {
		return jgwnlAddress;
	}

	public void setJgwnlAddress(String jgwnlAddress) {
		this.jgwnlAddress = jgwnlAddress;
	}
	
	private void connectionFailedMB() {
		MessageBox messageBox = new MessageBox(SCProjectHelper.getShell(), 
				SWT.ICON_WARNING | SWT.OK);
        
        messageBox.setText("Connection failed!");
        messageBox.setMessage("Terminology connection failed!");
        
        messageBox.open();
	}
	
	private void connectionSuccessfulMB() {
		MessageBox messageBox = new MessageBox(SCProjectHelper.getShell(), 
				SWT.ICON_INFORMATION | SWT.OK);
        
        messageBox.setText("Connection successful!");
        messageBox.setMessage("Successfully connected to a Terminology server.");
        
        messageBox.open();
	}

}
