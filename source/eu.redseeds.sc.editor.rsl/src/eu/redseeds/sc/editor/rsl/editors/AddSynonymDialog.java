package eu.redseeds.sc.editor.rsl.editors;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;

public class AddSynonymDialog extends Dialog {

	private Shell shell;
	private int retValue;
	
	private Label lbl1;
	private Text wordText;
	private Label lbl2;
	private Text posText;
	private Label lbl3;
	private List synonymList;
	private Label lbl4;
	private Text synonymText;
	private Button addButton;
	private Button okButton;
	private Button cancelButton;
	
	private RemoteJGWNL remoteJGWNL;
	private TermSenseDTO sense;
	private java.util.List<String> addedSynonyms;

	public AddSynonymDialog(TermSenseDTO sense) {
		super(SCProjectHelper.getShell());
		shell = new Shell(SCProjectHelper.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(400, 300);
		shell.setText("Add new synonym");
		retValue = SWT.None;
		remoteJGWNL = RemoteJGWNL.getInstance();
		this.sense = sense;
		addedSynonyms = new ArrayList<String>();
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
		wordText.setText(sense.getLemma());
		fd = new FormData();
		fd.top = new FormAttachment(lbl1, 5);
		fd. left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(65, 0);
		wordText.setLayoutData(fd);
		wordText.setFocus();
				
		lbl2 = new Label(shell, SWT.NONE);
		lbl2.setText("Part of speech:");
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(lbl1, 10);
		fd.right = new FormAttachment(100, 0);
		lbl2.setLayoutData(fd);
		
		posText = new Text(shell, SWT.READ_ONLY | SWT.BORDER);
		posText.setText(sense.getType());
		fd = new FormData();
		fd.top = new FormAttachment(lbl2, 5);
		fd.left = new FormAttachment(wordText, 10);
		fd.right = new FormAttachment(100, 0);
		posText.setLayoutData(fd);
		
		lbl3 = new Label(shell, SWT.NONE);
		lbl3.setText("Synonyms:");
		fd = new FormData();
		fd.top = new FormAttachment(wordText, 10);
		fd.left = new FormAttachment(0, 0);
		lbl3.setLayoutData(fd);
		
		synonymList = new List(shell, SWT.BORDER);
		for (TermSenseDTO ts : sense.getSynonyms()) {
			synonymList.add(ts.getLemma());
		}
		fd = new FormData();
		fd.top = new FormAttachment(lbl3, 5);
		fd. left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(100, 0);
		fd.bottom = new FormAttachment(75, 0);
		synonymList.setLayoutData(fd);
		
		lbl4 = new Label(shell, SWT.NONE);
		lbl4.setText("New Synonym: ");
		fd = new FormData();
		fd.top = new FormAttachment(synonymList, 5);
		fd.left = new FormAttachment(0, 0);
		lbl4.setLayoutData(fd);
		
		synonymText = new Text(shell, SWT.SINGLE | SWT.BORDER);
		fd = new FormData();
		fd.top = new FormAttachment(synonymList, 5);
		fd.left = new FormAttachment(lbl4, 5);
		fd.right = new FormAttachment(80, 0);
		synonymText.setLayoutData(fd);
		synonymText.setFocus();
		
		addButton = new Button(shell, SWT.NONE);
		addButton.setText("Add");
		fd = new FormData();
		fd.top = new FormAttachment(synonymList, 5);
		fd.left = new FormAttachment(synonymText, 5);
		fd.right = new FormAttachment(100, 0);
		addButton.setLayoutData(fd);
		addButton.setEnabled(false);
		
		okButton = new Button(shell, SWT.NONE);
		okButton.setText("OK");
		fd = new FormData();
		fd.top = new FormAttachment(synonymText, 15);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(18, 0);
		okButton.setLayoutData(fd);
		okButton.setEnabled(false);
		
		cancelButton = new Button(shell, SWT.NONE);
		cancelButton.setText("Cancel");
		fd = new FormData();
		fd.top = new FormAttachment(synonymText, 15);
		fd.left = new FormAttachment(okButton, 5);
		fd.right = new FormAttachment(40, 0);
		cancelButton.setLayoutData(fd);
		
		// Event handling
		
		synonymText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (synonymText.getText().length() > 0)
					addButton.setEnabled(true);
				else
					addButton.setEnabled(false);
			}
		});
		
		
		synonymText.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == '\r') {
					addedSynonyms.add(synonymText.getText());
					synonymList.add(synonymText.getText());
					synonymText.setText("");
					okButton.setEnabled(true);
				}					
			}
		});
		
		
		addButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				addedSynonyms.add(synonymText.getText());
				synonymList.add(synonymText.getText());
				synonymText.setText("");
				okButton.setEnabled(true);
			}
		});
		
		
		okButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				for (String synonym : addedSynonyms) {
					remoteJGWNL.addNewSynonym(synonym, sense);
				}
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
	
	
}
