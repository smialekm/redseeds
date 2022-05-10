package eu.redset.tsl.editor.editors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import eu.redset.emf.model.tsl.UseCaseTestScenario;


public class UseCaseTestScenarioChooseWidget extends Composite {

	public final static String[] columnNamesStatements = new String[] { "nazwa"};
	private EObject test;
	private UseCaseTestScenario scenario = null;
	private Table table;
	private TableViewer scenariosTableViewer;


	public UseCaseTestScenarioChooseWidget(Composite parent, EObject test) {
		super(parent, SWT.NONE);
		
		this.test = test;
		createContent();
	}

	
	private ISelectionChangedListener selListener = new ISelectionChangedListener(){

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			if (table.getSelectionCount() == 1){
				TableItem selectedItem = table.getItem(table.getSelectionIndex());
				UseCaseTestScenario selectedScenario = (UseCaseTestScenario)selectedItem.getData();
				scenario = selectedScenario;
			}
		};	
	};
	
	public UseCaseTestScenario getSelectedUseCaseTestScenario(){
		return scenario;
	}
	
	private void createContent(){
		
		// Create table
		table = new Table(this, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | 
				SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0,10,520,250);
		
		
		// Create columns
		TableColumn column = new TableColumn(table, SWT.LEFT, 0);
		column.setWidth(520);
		column.setText("Use Case Test Scenario");
		
		scenariosTableViewer = new TableViewer(table);
		scenariosTableViewer.setUseHashlookup(true);
		scenariosTableViewer.setColumnProperties(columnNamesStatements);
		
		IStructuredContentProvider scenariosContentProvider;
		scenariosContentProvider = new UseCaseTestScenarioChooseContentProvider();
		scenariosTableViewer.setContentProvider(scenariosContentProvider);
		scenariosTableViewer.setLabelProvider(new UseCaseTestScenarioChooseDecoratingLabelProvider(new UseCaseTestScenarioChooseLabelProvider(), null, test));
		scenariosTableViewer.setInput(test);		
		scenariosTableViewer.addSelectionChangedListener(selListener);
		refresh();
	}
	

	
	
	


	public void refresh(){
		if(scenariosTableViewer != null) {
			scenariosTableViewer.refresh();
	//		if (null!=wizard && null!=term && term.getSynonymUid()!=0) wizard.setPageComplete(true);
		}
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void addAssignSenseListener(SelectionListener externalListener){
		table.addSelectionListener(externalListener);
	}

	/**
	 * Sets column widths for the widget's table. 
	 * @param columnWidths
	 * An array of sizes measured in pixels. A number from each array cell will be assigned to a column with a corresponding index. The array has to have length equal to widget's table columns number. 
	 */
	public void setColumnWidths(int[] columnWidths) {
		if(table == null || columnWidths == null) {
			return;
		}
		TableColumn[] columns = table.getColumns();
		if(columns.length > columnWidths.length) {
			return;
		}
		int i = 0;
		for(TableColumn column : columns) {
			column.setWidth(columnWidths[i++]);
		}
	}
}

