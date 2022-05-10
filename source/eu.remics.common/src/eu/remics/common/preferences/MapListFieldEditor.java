package eu.remics.common.preferences;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class MapListFieldEditor extends FieldEditor {

	protected static final String DEFAULT_ADD_LABEL = "Add";
	protected static final String DEFAULT_REMOVE_LABEL = "Remove";
	private static final String DEFAULT_SEPERATOR = "\n";
	
	private static final int VERTICAL_DIALOG_UNITS_PER_CHAR = 8;
	private static final int LIST_HEIGHT_IN_CHARS = 9;
	protected static final int LIST_HEIGHT_IN_DLUS = 
		LIST_HEIGHT_IN_CHARS * VERTICAL_DIALOG_UNITS_PER_CHAR;
	
	protected Composite top;
	protected Table table;
	protected Text textField;
	protected Button add;
	protected Button remove;
	protected String seperator = DEFAULT_SEPERATOR;
	private Text textField2;
	
	public MapListFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, parent);		
	}
	
	public MapListFieldEditor(String name, String labelText, String addButtonText, String removeButtonText, Composite parent) {
		super(name, labelText, parent);
		setAddButtonText(addButtonText);
		setRemoveButtonText(removeButtonText);		
	}
	
	protected void adjustForNumColumns(int numColumns) {
		((GridData)top.getLayoutData()).horizontalSpan = numColumns;
	}
	
	@SuppressWarnings("deprecation")
	protected void doFillIntoGrid(Composite parent, int numColumns) {
		top = parent;
	
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = numColumns;
		top.setLayoutData(gd);
	
		Label label = getLabelControl(top);
		GridData labelData = new GridData();
		labelData.horizontalSpan = numColumns;
		label.setLayoutData(labelData);
	
		table = new Table(top, SWT.BORDER);
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = convertVerticalDLUsToPixels(table, LIST_HEIGHT_IN_DLUS);
		data.horizontalSpan = numColumns;
		table.setLayoutData(data);
		String[] titles = {"Value", "Replacement"};
		for (int i=0; i<titles.length; i++) {
			TableColumn column = new TableColumn (table, SWT.NONE);
			column.setText (titles [i]);
		}
		table.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				selectionChanged();
			}
		});
		
		// Create a composite for the add and remove 
		// buttons and the input text field.
		Composite addRemoveGroup = new Composite(top, SWT.NONE);
	
		GridData addRemoveData = new GridData(GridData.FILL_HORIZONTAL);
		addRemoveData.horizontalSpan = numColumns;
		addRemoveGroup.setLayoutData(addRemoveData);
	
		GridLayout addRemoveLayout = new GridLayout();
		addRemoveLayout.numColumns = numColumns;
		addRemoveLayout.marginHeight = 0;
		addRemoveLayout.marginWidth = 0;
		addRemoveGroup.setLayout(addRemoveLayout);
		
		// Create a composite for the add and remove buttons.
		Composite buttonGroup = new Composite(addRemoveGroup, SWT.NONE);
		buttonGroup.setLayoutData(new GridData());
	
		GridLayout buttonLayout = new GridLayout();
		buttonLayout.marginHeight = 0;
		buttonLayout.marginWidth = 0;
		buttonGroup.setLayout(buttonLayout);
	
		// Create the add button.
		add = new Button(buttonGroup, SWT.NONE);
		add.setText(DEFAULT_ADD_LABEL);
		add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {	
				add();
			}	
		});		
		GridData addData = new GridData(GridData.FILL_HORIZONTAL);
		addData.heightHint = convertVerticalDLUsToPixels(
			add,
			IDialogConstants.BUTTON_HEIGHT);
		addData.widthHint = convertHorizontalDLUsToPixels(
			add,
			IDialogConstants.BUTTON_WIDTH);	
		add.setLayoutData(addData);	
		
		// Create the remove button.
		remove = new Button(buttonGroup, SWT.NONE);
		remove.setEnabled(false);
		remove.setText(DEFAULT_REMOVE_LABEL);
		remove.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {	
				table.remove(table.getSelectionIndex());
				selectionChanged();
			}
		});
		GridData removeData = new GridData(GridData.FILL_HORIZONTAL);
		removeData.heightHint = convertVerticalDLUsToPixels(
			remove,
			IDialogConstants.BUTTON_HEIGHT);
		removeData.widthHint = convertHorizontalDLUsToPixels(
			remove,
			IDialogConstants.BUTTON_WIDTH);
		remove.setLayoutData(removeData);
		
		Composite fieldGroup = new Composite(addRemoveGroup, SWT.NONE);
		fieldGroup.setLayoutData(new GridData(SWT.BEGINNING,SWT.BEGINNING,false,false));
	
		GridLayout fieldLayout = new GridLayout(3, false);
		fieldLayout.marginHeight = 0;
		fieldLayout.marginWidth = 0;
		fieldGroup.setLayout(fieldLayout);
		
		textField = new Text(fieldGroup, SWT.BORDER);
		
		GridData textData = new GridData(GridData.CENTER/*FILL_HORIZONTAL*/);
		textData.horizontalSpan = numColumns - 1;
		textData.verticalAlignment = GridData.BEGINNING;
		textField.setLayoutData(textData);
		
		textField2 = new Text(fieldGroup, SWT.BORDER);
		
		GridData textData2 = new GridData(GridData.CENTER/*FILL_HORIZONTAL*/);
		textData2.horizontalSpan = numColumns - 1;
		textData2.verticalAlignment = GridData.BEGINNING;
		textField2.setLayoutData(textData2);	
	}
	
	protected void doLoad() {
		String items = getPreferenceStore().getString(getPreferenceName());
		setTable(items);
	}
	
	protected void doLoadDefault() {
		String items = getPreferenceStore().getDefaultString(getPreferenceName());
		setTable(items);
	}
	
	protected void setTable(String items) {
		table.removeAll();
		String[] st = items.split(seperator);
		for (int i=0;i<st.length-1;i+=2){
			TableItem item = new TableItem (table, 0);
			item.setText (0,(String) st[i]);
			item.setText(1,(String) st[i+1]);
		}
		for (int i=0; i<table.getColumnCount(); i++) {
			table.getColumn (i).pack ();
		}
	}
	
	protected void doStore() {
		String s = createTableString(table.getItems());
		if (s != null)
			getPreferenceStore().setValue(getPreferenceName(), s);
	}
	
	public int getNumberOfControls() {
		return 2;
	}
	
	protected void add() {
		if (textField.getText() != null && !textField.getText().isEmpty() && textField2.getText() != null){
			TableItem item = new TableItem (table, 0);
			item.setText (0,textField.getText());
			item.setText(1,textField2.getText());
		}
		for (int i=0; i<table.getColumnCount(); i++) {
			table.getColumn (i).pack ();
		}
		textField.setText("");
		textField2.setText("");
	}
	
	public void setAddButtonText(String text) {
		add.setText(text);
	}
	
	public void setRemoveButtonText(String text) {
		remove.setText(text);
	}
	 
	public void setSeperator(String seperator) {
		this.seperator = seperator;	
	}
	
	protected String createTableString(TableItem[] tableItems) {
		StringBuffer path = new StringBuffer("");
		for (TableItem t:tableItems) {
			path.append(t.getText(0));
			path.append(seperator);
			path.append(t.getText(1));
			path.append(seperator);
		}
		return path.toString();
	}

	void selectionChanged() {
		int index = table.getSelectionIndex();
		remove.setEnabled(index >= 0);		
	}

}
