package eu.redseeds.sc.editor.rsl.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.common.Constants;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;

public class AddSenseDialog extends Dialog {
	
	private Shell shell;
	private int retValue;
	
	private Label lbl1;
	private Text wordText;
	private Combo wordCombo;
	private Label lbl2;
	private Combo posCombo;
	private Label lbl3;
	private Text senseText;
	private Button addButton;
	private Button cancelButton;
	
	private RemoteJGWNL remoteJGWNL;
	private List<TermSenseDTO> senses;

	public AddSenseDialog() {
		super(SCProjectHelper.getShell());
		shell = new Shell(SCProjectHelper.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(450, 300);
		shell.setText("Add new sense");
		retValue = SWT.None;
		remoteJGWNL = RemoteJGWNL.getInstance();
		senses = null;
	}
	
	public void setSenses(List<TermSenseDTO> senses) {
		this.senses = senses;
	}
	
	public int open() {
		createContent();
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed()){
			if (!display.readAndDispatch())
				display.sleep();
		}
		return retValue;
	}

	private void createContent() {
		
		// Set layout
		
		FormLayout layout = new FormLayout();
		layout.marginWidth = 10;
		layout.marginHeight = 10;
		shell.setLayout(layout);

		// Create controls
		
		lbl1 = new Label(shell, SWT.NONE);
		lbl1.setText("Word's base form:");
		FormData fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(65, 0);
		lbl1.setLayoutData(fd);
		
		wordText = new Text(shell, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
		wordText.setBackground(new Color(null, 255, 255, 255));
		fd = new FormData();
		fd.top = new FormAttachment(lbl1, 5);
		fd. left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(65, 0);
		wordText.setLayoutData(fd);
		wordText.setVisible(false);
		
		wordCombo = new Combo(shell, SWT.READ_ONLY | SWT.BORDER);
		fd = new FormData();
		fd.top = new FormAttachment(lbl1, 5);
		fd. left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(65, 0);
		wordCombo.setLayoutData(fd);
		wordCombo.setVisible(false);
		
		if (senses != null) {
			if (senses.size() > 0) {
				
				List<String> baseForms = new ArrayList<String>();
				for (TermSenseDTO s : senses) {
					if (!baseForms.contains(s.getLemma()))
						baseForms.add(s.getLemma());
				}
				
				if (baseForms.size() == 1) {
					wordText.setText(baseForms.get(0));
					wordText.setVisible(true);
				}
				else {
					for (String bf : baseForms)
						wordCombo.add(bf);
					wordCombo.select(0);
					wordCombo.setVisible(true);
				}
			}
		}
		
		lbl2 = new Label(shell, SWT.NONE);
		lbl2.setText("Part of speech:");
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(lbl1, 10);
		fd.right = new FormAttachment(100, 0);
		lbl2.setLayoutData(fd);
		
		posCombo = new Combo(shell, SWT.READ_ONLY | SWT.BORDER);
		fd = new FormData();
		fd.top = new FormAttachment(lbl2, 5);
		fd.left = new FormAttachment(wordText, 10);
		fd.right = new FormAttachment(100, 0);
		posCombo.setLayoutData(fd);
		for (String pos : remoteJGWNL.getPOSNames())
			posCombo.add(pos);
		posCombo.setVisibleItemCount(posCombo.getItemCount());
		posCombo.addModifyListener(modListener);
		
		lbl3 = new Label(shell, SWT.NONE);
		lbl3.setText("Enter sense:");
		fd = new FormData();
		fd.top = new FormAttachment(wordText, 10);
		fd.left = new FormAttachment(0, 0);
		lbl3.setLayoutData(fd);
		
		senseText = new Text(shell, SWT.BORDER | SWT.WRAP);
		fd = new FormData();
		fd.top = new FormAttachment(lbl3, 5);
		fd. left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(100, 0);
		fd.bottom = new FormAttachment(85, 0);
		senseText.setLayoutData(fd);
		senseText.addModifyListener(modListener);
		
		addButton = new Button(shell, SWT.NONE);
		addButton.setText("Add sense");
		fd = new FormData();
		fd.top = new FormAttachment(senseText, 10);
		fd.left = new FormAttachment(0, 0);
		addButton.setLayoutData(fd);
		addButton.setEnabled(false);
		
		cancelButton = new Button(shell, SWT.NONE);
		cancelButton.setText("Cancel");
		fd = new FormData();
		fd.top = new FormAttachment(senseText, 10);
		fd.left = new FormAttachment(addButton, 10);
		cancelButton.setLayoutData(fd);
		
		senseText.setFocus();
		
		// Event handling
		
		addButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String lemma;
				if (wordText.isVisible())
					lemma = wordText.getText();
				else
					lemma = wordCombo.getText();
				String gloss = senseText.getText();
				String pos = posCombo.getText();
				Display.getCurrent().getCursorControl().setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_WAIT));
//				if (!remoteJGWNL.addNewSense(lemma, gloss, pos, true)) {
//					MessageBox mb = new MessageBox(shell, SWT.OK | SWT.ICON_ERROR);
//					mb.setMessage("Failed to add a new sense to the dictionary.");
//				}
				remoteJGWNL.addNewSense(lemma, gloss, pos, true);
				RemoteJGWNL.getInstance().connect(Constants.getJGWNLAddress());
				Display.getCurrent().getCursorControl().setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
				retValue = SWT.OK;
				shell.close();
			}
		});
		
		cancelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				retValue = SWT.CANCEL;
				shell.close();
			}
		});
	
			
	}
	
	private ModifyListener modListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
			if (posCombo.getText().replace('_',' ').trim().length() != 0 && senseText.getText().replace('_',' ').trim().length() != 0)
				addButton.setEnabled(true);
			else
				addButton.setEnabled(false);
			
		}
	};

}
