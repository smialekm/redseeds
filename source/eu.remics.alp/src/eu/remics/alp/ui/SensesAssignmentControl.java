package eu.remics.alp.ui;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import eu.redseeds.sc.terminology.model.TermSenseDTO;

/**
 * Control placed on senses instantiation page of the ALP import wizard
 * @author aambroziewicz
 *
 */
public class SensesAssignmentControl extends AbstractAssignmentControl {
	
//	protected Object[] notionNames;

	private static String[] columnProperties = new String[]{"Noun", "Selected sense"};
	private static String[] columnPropertiesTermSenses = new String[]{"Senses"};
	private Table sensesTable;
	private TableViewer sensesTableViewer;
	private Table termSensesTable;
	private TableViewer termSensesTableViewer;
//	protected ImportALPWizardSensesPage page;

	SensesAssignmentControl(Composite parent, Object[] notionNames, ImportALPWizardSensesPage page) {
		super(parent, notionNames, page);
//		this.notionNames = notionNames;
//		this.page = page;
		createContent();
	}

	private void createContent() {
		// Create controls
		sensesTable = new Table(this, SWT.SINGLE | SWT.BORDER | 
				SWT.FULL_SELECTION);
		sensesTable.setLinesVisible(true);
		sensesTable.setHeaderVisible(true);
		sensesTable.setBounds(20, 40, 520, 100);
		
		// Create columns
		final TableColumn columnNoun = new TableColumn(sensesTable, SWT.LEFT, 0);
		columnNoun.setWidth(123);
		columnNoun.setText(columnProperties[0]);
		
		final TableColumn columnSense = new TableColumn(sensesTable, SWT.LEFT, 1);
		columnSense.setWidth(393);
		columnSense.setText(columnProperties[1]);
		
		sensesTableViewer = new TableViewer(sensesTable);
		sensesTableViewer.setUseHashlookup(true);
		
		sensesTableViewer.setContentProvider(new SensesTableContentLabelProvider());
		sensesTableViewer.setLabelProvider(new SensesTableContentLabelProvider());

		sensesTableViewer.setInput(notionNames);
		sensesTableViewer.setColumnProperties(columnProperties);
		sensesTableViewer.setSorter(new ViewerSorter() {
			public int compare(Viewer viewer, Object o1, Object o2) {
				String firstName = ((Object[])o1)[1].toString();
				String secondName = ((Object[])o2)[1].toString();
				return firstName.compareToIgnoreCase(secondName);
			}
		});
		
		termSensesTable = new Table(this, SWT.SINGLE | SWT.BORDER | 
				SWT.FULL_SELECTION);
		termSensesTable.setLinesVisible(true);
		termSensesTable.setHeaderVisible(true);
		termSensesTable.setBounds(20, 135, 520, 100);
		
		final TableColumn columnTermSense = new TableColumn(termSensesTable, SWT.LEFT, 0);
		columnTermSense.setWidth(516);
		columnTermSense.setText(columnPropertiesTermSenses[0]);
		
		termSensesTableViewer = new TableViewer(termSensesTable);
		termSensesTableViewer.setUseHashlookup(true);
		
		termSensesTableViewer.setContentProvider(new TermSensesTableContentLabelProvider());
		termSensesTableViewer.setLabelProvider(new TermSensesTableContentLabelProvider());

		termSensesTableViewer.setInput(null);
		termSensesTableViewer.setColumnProperties(columnPropertiesTermSenses);
		
		//event handling
		sensesTableViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				if (sensesTable.getSelectionIndex() >= 0) {
					TableItem selectedItem = sensesTable.getItem(
							sensesTable.getSelectionIndex());
					if (selectedItem.getData() != null) {
						if(selectedItem.getData() instanceof Object[]) {
							termSensesTableViewer.setInput(((Object[])selectedItem.getData())[1]);
						}
					}
				}
			}
		});
		
		termSensesTableViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				//ensure both tables have rows selected
				if (termSensesTable.getSelectionIndex() >= 0 && sensesTable.getSelectionIndex() >= 0) {
					TableItem selectedItem = termSensesTable.getItem(
							termSensesTable.getSelectionIndex()); 
					TableItem selectedName = sensesTable.getItem(
							sensesTable.getSelectionIndex()); 
					if (selectedItem.getData() != null && selectedName.getData() != null) {
						if(selectedItem.getData() instanceof TermSenseDTO && selectedName.getData() instanceof Object[]) {
							TermSenseDTO selectedSense = (TermSenseDTO)selectedItem.getData();
							Object[] nameData = (Object[])selectedName.getData();
							nameData[2] = selectedSense;
							selectedName.setData(nameData);
							sensesTableViewer.refresh();
							page.setPageComplete(validateItems());
						}
					}
				}
			}
		});
		
	}
	
	@Override
	public Object[] getNotionNames() {
		return notionNames;
	}

	@Override
	public void setNotionNames(Object[] notionNames) {
		this.notionNames = notionNames;
		refresh();
	}
	
	@Override
	public void refresh() {
		if(sensesTableViewer != null) {
			sensesTableViewer.setInput(getNotionNames());
			sensesTableViewer.refresh();
		}
	}

	@Override
	public boolean validateItems() {
		if(sensesTableViewer.getTable().getItems().length == 0) {
			return false; //empty
		}
		for(TableItem item : sensesTableViewer.getTable().getItems()) {
			if(item == null) {
				return false;
			}
			if(!(item.getData() instanceof Object[])) {
				return false;
			}
			Object[] tripleArray = (Object[])item.getData();
			if(tripleArray.length != 3) {
				return false;
			}
			if(tripleArray[0] == null || tripleArray[1] == null || tripleArray[2].equals("")) {
				return false;
			}	
		}
		return true;
	}
	
}
