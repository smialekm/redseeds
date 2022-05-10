package eu.redseeds.sc.merging.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
import eu.redseeds.sc.merging.views.MergeConflictViewCellModifier;
import eu.redseeds.sc.merging.views.MergeConflictViewContentProvider;
import eu.redseeds.sc.merging.views.MergeConflictViewLabelProvider;

/**
 * Dialog used for displaying merge conflicts. Allows user to select merge action. 
 *
 */
public class MergeConflictsDialog extends Dialog {
	
	private TreeViewer fTreeViewer;
	private ITableLabelProvider fLabelProvider;
	private IStructuredContentProvider fContentProvider;
	private Object fInput;
	private String title = "Merge conflicts found";
	private String message = "Merge can be completed only after following conflicts are resolved:";
	
	public static String[] columnNames = {
		"Current SC element", 
		"Past SC element", 
		"Conflict type",
		"Action"
	};

	public MergeConflictsDialog(Shell parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Control createDialogArea(Composite container) {
		Composite parent = (Composite) super.createDialogArea(container);
		configure(container);

		GridData glayout = new GridData(GridData.FILL);
		Label messageLabel = createMessageArea(parent);
		messageLabel.setLayoutData(glayout);

		fTreeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | 
				SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		fTreeViewer.setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);
		
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = convertHeightInCharsToPixels(15);
		gd.widthHint = convertWidthInCharsToPixels(125);
		Tree tree = fTreeViewer.getTree();
		tree.setLayoutData(gd);
		tree.setFont(container.getFont());
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		TreeColumn col0 = new TreeColumn(tree, SWT.LEFT, 0);
		col0.setWidth(130);
		col0.setText(columnNames[0]);
		TreeColumn col1 = new TreeColumn(tree, SWT.LEFT, 1);
		col1.setWidth(130);
		col1.setText(columnNames[1]);
		TreeColumn col2 = new TreeColumn(tree, SWT.LEFT, 2);
		col2.setWidth(250);
		col2.setText(columnNames[2]);
		TreeColumn col3 = new TreeColumn(tree, SWT.LEFT, 3);
		col3.setWidth(100);
		col3.setText(columnNames[3]);
		fTreeViewer.setColumnProperties(columnNames);
		
		fTreeViewer.setUseHashlookup(true);
		// Create cell editors
		CellEditor[] editors = new CellEditor[4];
		// --
		editors[0] = null;
		editors[1] = null;
		editors[2] = null;
		editors[3] = new ComboBoxCellEditor(tree, MergeConstants.MERGE_ACTION_LABELS);
		fTreeViewer.setCellEditors(editors);
		fTreeViewer.setCellModifier(new MergeConflictViewCellModifier(fTreeViewer));
		
		fTreeViewer.setContentProvider(fContentProvider);
		fTreeViewer.setLabelProvider(fLabelProvider);
		fTreeViewer.setInput(fInput);
		
		createMarkAllButtonBar(parent);
		
		return parent;
	}
	
	/**
	* Creates and returns the contents of this dialog's button bar for 'mark all' type buttons.
	* 
	* @param parent
	* the parent composite to contain the button bar
	* @return the button bar control
	*/
	protected Control createMarkAllButtonBar(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		// create a layout with spacing and margins appropriate for the font
		// size.
		GridLayout layout = new GridLayout();
		layout.numColumns = 0;//incremented by createMarkAllButton
		layout.makeColumnsEqualWidth = true;
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		composite.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END
				| GridData.VERTICAL_ALIGN_CENTER);
		composite.setLayoutData(data);
		composite.setFont(parent.getFont());

		// Add the buttons to the button bar.
		createMarkAllButtonsForButtonBar(composite);
		return composite;
	}
	
	/**
	 * Adds buttons to this dialog's 'mark-all' button bar.
	 * @param parent
	 */
	protected void createMarkAllButtonsForButtonBar(Composite parent) {
		// create OK and Cancel buttons by default
		createMarkAllButton(parent, MergeConstants.MERGE_ACTION_SKIP_ID
				, "Mark all '" + MergeConstants.MERGE_ACTION_SKIP_LABEL + "'");
		createMarkAllButton(parent, MergeConstants.MERGE_ACTION_OVERWRITE_ID
				, "Mark all '" + MergeConstants.MERGE_ACTION_OVERWRITE_LABEL + "'");
		createMarkAllButton(parent, MergeConstants.MERGE_ACTION_AUTOSOLVE_ID
				, "Mark all '" + MergeConstants.MERGE_ACTION_AUTOSOLVE_LABEL + "'");
	}
	
	/**
	 * Creates mark-all type button on the button bar
	 * @param parent
	 * button parent
	 * @param id
	 * button id, should correspond to merge constants' action id
	 * @param label
	 * button's label
	 * @return
	 */
	protected Button createMarkAllButton(Composite parent, int id,
			String label) {
		// increment the number of columns in the button bar
		((GridLayout) parent.getLayout()).numColumns++;
		Button button = new Button(parent, SWT.PUSH);
		button.setText(label);
		button.setFont(JFaceResources.getDialogFont());
		button.setData(new Integer(id));
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				markAllButtonPressed(((Integer) event.widget.getData()).intValue());
			}
		});
		setButtonLayoutData(button);
		return button;
	}
	
	/**
	 * Prepares processing of conflict objects accordingly to the button pressed
	 * @param buttonId
	 */
	protected void markAllButtonPressed(int buttonId) {
		if (buttonId == MergeConstants.MERGE_ACTION_SKIP_ID) {
			changeAllConflictActions(MergeConstants.MERGE_ACTION_SKIP_ID);
		} else if (buttonId == MergeConstants.MERGE_ACTION_OVERWRITE_ID) {
			changeAllConflictActions(MergeConstants.MERGE_ACTION_OVERWRITE_ID);
		} else if (buttonId == MergeConstants.MERGE_ACTION_AUTOSOLVE_ID) {
			changeAllConflictActions(MergeConstants.MERGE_ACTION_AUTOSOLVE_ID);
		}		
		if(fTreeViewer != null){
			fTreeViewer.refresh();
		}
	}
	
	private void changeAllConflictActions(int newActionID) {
		if(getInput() == null) {
			return;
		}
		if(!(getInput() instanceof Object[])) {
			return;
		}
		Object[] conflictObjects = (Object[])getInput();
		for(Object obj : conflictObjects) {
			if (obj instanceof MergeConflictObject) {
				MergeConflictObject conflict = (MergeConflictObject) obj;
				conflict.setConflictAction(newActionID);
				
			}
		}
	}

	public TreeViewer getTreeViewer() {
		return fTreeViewer;
	}

	public ITableLabelProvider getLabelProvider() {
		return fLabelProvider;
	}

	public void setLabelProvider(ITableLabelProvider labelProvider) {
		fLabelProvider = labelProvider;
	}

	public IStructuredContentProvider getContentProvider() {
		return fContentProvider;
	}

	public void setContentProvider(IStructuredContentProvider contentProvider) {
		fContentProvider = contentProvider;
	}

	public Object getInput() {
		return fInput;
	}

	public void setInput(Object input) {
		fInput = input;
	}

//	public void setAddCancelButton(boolean addCancelButton) {
//		fAddCancelButton = addCancelButton;
//	}
	
	/**
	* Creates the message area for this dialog.
	* <p>
	* This method is provided to allow subclasses to decide where the message
	* will appear on the screen.
	* </p>
	* 
	* @param composite
	* the parent composite
	* @return the message label
	*/
	protected Label createMessageArea(Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		if (message != null) {
			label.setText(message);
		}
		label.setFont(composite.getFont());
		return label;
	}
	
	protected void configure(Composite parent) {
		this.setBlockOnOpen(true);
		
		if (title != null) {
			getShell().setText(title);
		}
		
		setContentProvider(new MergeConflictViewContentProvider());
		setLabelProvider(new MergeConflictViewLabelProvider());
	}

}
