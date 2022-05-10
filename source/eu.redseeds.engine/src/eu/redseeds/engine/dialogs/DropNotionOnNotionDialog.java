package eu.redseeds.engine.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IconAndMessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import eu.redseeds.common.SCProjectHelper;

public class DropNotionOnNotionDialog extends IconAndMessageDialog {
	
	/**
	 * creates dialog for a default shell from SCProjectHelper.getShell()
	 */
	public DropNotionOnNotionDialog(boolean drc, boolean dgc, boolean dac) {
		super(SCProjectHelper.getShell());
		disablerelationshipcreation = drc;
		disablegeneralisationcreation = dgc;
		disableattributecreation = dac;
	}

	public static final int ID_CREATE_RELATIONSHIP = 2;
	public static final int ID_CREATE_SPECIALISATION = 3;
	public static final int ID_ADD_ATTRIBUTE = 4;
	public static final int ID_MERGE_NOTIONS = 5;
	protected boolean disablerelationshipcreation = false;
	protected boolean disablegeneralisationcreation = false;
	protected boolean disableattributecreation = false;
	
	@Override
	protected Image getImage() {
		return null;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button b1 = createButton(parent, ID_CREATE_RELATIONSHIP, "Create relationship", false);
		Button b2 = createButton(parent, ID_CREATE_SPECIALISATION, "Create specialisation", false);
		Button b3 = createButton(parent, ID_ADD_ATTRIBUTE, "Add as attribute", false);
		createButton(parent, ID_MERGE_NOTIONS, "Merge notions", false);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
		b1.setEnabled(!disablerelationshipcreation);
		b2.setEnabled(!disablegeneralisationcreation);
		b3.setEnabled(!disableattributecreation);
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		setReturnCode(buttonId);
		close();
	}
	
	@Override
	protected Control createDialogArea(Composite composite) {
		createMessageArea(composite);
		
		// Create a composite to hold the label
	    Composite resultComposite = new Composite(composite, SWT.NONE);
	    GridData data = new GridData(GridData.FILL_BOTH);
	    data.horizontalSpan = 2;
	    resultComposite.setLayoutData(data);
	    resultComposite.setLayout(new FillLayout());
	    
		Label label = new Label(resultComposite, SWT.NONE);
		label.setText("Which action would you like to perform?");
		getShell().setText("Notions");
		
		return resultComposite;
	}

}
