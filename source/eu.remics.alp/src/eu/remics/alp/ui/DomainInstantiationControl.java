package eu.remics.alp.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import eu.remics.alp.PatternSlice;
import eu.redseeds.engine.navigator.providers.AdapterFactory;

/**
 * Control placed on domain instantiation page of the ALP import wizard
 * @author aambroziewicz
 *
 */
public class DomainInstantiationControl extends Composite {
	
	protected ImportALPWizard wizard;
	
	private Label domainLabel;
	private Table domainTable;
	private TableViewer domainTableViewer;
	private ImportALPWizardDomainPage page;
	public static String[] columnProperties = new String[]{ 
			"Abstract domain elements", "Concrete domain elements' names"};
	PatternSlice pSlice;

	public DomainInstantiationControl(Composite parent, ImportALPWizardDomainPage page, PatternSlice pSlice, ImportALPWizard wizard) {
		super(parent, SWT.NONE);
		this.pSlice = pSlice;
		this.wizard = wizard;
		this.page = page;
		createContent();
	}
	
	private void createContent() {
		
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		
		domainLabel = new Label(this, SWT.NONE);
		domainLabel.setBounds(20, 10, 150, 20);
		domainLabel.setText("Domain Specification");
		
		// Create table
		domainTable = new Table(this, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | 
				SWT.FULL_SELECTION);
		domainTable.setLinesVisible(true);
		domainTable.setHeaderVisible(true);
		domainTable.setBounds(20, 40, 530, 200);
		domainTable.setLayoutData(gd);
		
		TableColumn abstractDomainColumn = new TableColumn(domainTable, SWT.LEFT, 0);
		abstractDomainColumn.setWidth(258);
		abstractDomainColumn.setText(columnProperties[0]);
		abstractDomainColumn.setToolTipText(columnProperties[0]);
		
		TableColumn concreteDomainColumn = new TableColumn(domainTable, SWT.LEFT, 1);
		concreteDomainColumn.setWidth(268);
		concreteDomainColumn.setText(columnProperties[1]);
		concreteDomainColumn.setToolTipText(columnProperties[1]);
		
		domainTableViewer = new TableViewer(domainTable);
		domainTableViewer.setUseHashlookup(true);
		// Create cell editors
		CellEditor[] editors = new CellEditor[2];
		editors[0] = null;
		editors[1] = new TextCellEditor(domainTable);
		// Assign the cell editors to the viewer 
		domainTableViewer.setCellEditors(editors);
		// Set the cell modifier for the viewer
		final DomainElementNameCellModifier cellModifier 
			= new DomainElementNameCellModifier(domainTableViewer, this, page);
		cellModifier.setEnabled(true);
		domainTableViewer.setCellModifier(cellModifier);
		DomainTableContentLabelProvider provider = new DomainTableContentLabelProvider(wizard);
		domainTableViewer.setContentProvider(provider);
		domainTableViewer.setLabelProvider(provider);
		domainTableViewer.setSorter(new ViewerSorter() {
			public int compare(Viewer viewer, Object o1, Object o2) {
				Object[] obj = (Object[])o1;
				String firstName = AdapterFactory.adapt(obj[0], null).getText(obj[0]);
				obj = (Object[])o2;
				String secondName = AdapterFactory.adapt(obj[0], null).getText(obj[0]);
				return firstName.compareToIgnoreCase(secondName);
			}
		});
		domainTableViewer.setInput(pSlice);
		domainTableViewer.setColumnProperties(columnProperties);
		
		// Event handling
//		domainTable.addTraverseListener(new TraverseListener() {
//			
//			@Override
//			public void keyTraversed(TraverseEvent e) {
//				System.out.println("traversed");
//		        if (e.keyCode == SWT.TAB)
//		            e.doit = false;
//				
//			}
//		});
		
//		domainTable.addKeyListener(new KeyListener() {
//			
//			@Override
//			public void keyReleased(KeyEvent e) {
//				System.out.println("released: " + e.keyCode);
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent e) {
//				System.out.println("pressed: " + e.keyCode);
//				if(e.keyCode == SWT.TAB || e.keyCode == SWT.CR) {
//					System.out.println("tab or return pressed");
//					int newSelectionIndex = 0;
//					if(domainTable.getItemCount() == domainTable.getSelectionIndex() + 1) {
//						newSelectionIndex = domainTable.getSelectionIndex();
//					}
//					else {
//						newSelectionIndex = domainTable.getSelectionIndex() + 1;
//					}
//					System.out.println("new selection="+newSelectionIndex);
//					domainTable.setSelection(newSelectionIndex);
//				}
//			}
//		});
		
//		domainTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
//			
//			@Override
//			public void selectionChanged(SelectionChangedEvent event) {
//				page.setPageComplete(validateItems());
//			}
//		});
		
//		domainTable.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				//TODO
//			}
//		});
//		
		// edit only on double-click
//		domainTable.addListener(SWT.MouseDoubleClick, new Listener() {
//			public void handleEvent(Event event) {
//				cellModifier.setEnabled(true);
//				TableItem[] selection = domainTable.getSelection();
//
//				if (selection.length != 1) {
//					return;
//				}
//
//				TableItem item = domainTable.getSelection()[0];
//
//				for (int i = 0; i < domainTable.getColumnCount(); i++) {
//					if (item.getBounds(i).contains(event.x, event.y)) {
//						domainTableViewer.editElement(item.getData(), i);
//						cellModifier.setEnabled(false);
//						break;
//					}
//				}
//			}
//		});
	}
	
	public void refresh(PatternSlice pSlice) {
		this.pSlice = pSlice;
		domainTableViewer.setInput(pSlice);
		domainTableViewer.refresh();
	}
	
	/**
	 * Gets array of triples "DomainElement - its new name - sense assigned" (last triple element is initially empty)
	 * @return
	 */
	public Object[] getItems() {
		if(domainTable != null) {
			TableItem[] tis = domainTable.getItems();
			List<Object[]> resultList 
				= new ArrayList<Object[]>(tis.length);
			for(TableItem ti : tis) {
				Object[] data = (Object[])ti.getData();
				Object[] result = new Object[] {data[0], data[1], ""};
				resultList.add(result);
			}
			return resultList.toArray();
		}
		return new Object[0];
	}
	
	public void setItems(Object[] items) {
		if(domainTable != null) {
			TableItem[] tis = domainTable.getItems();
			for(TableItem ti : tis) {
				Object[] data = (Object[])ti.getData();
				for(Object item : items) {
					Object[] itemArray = (Object[])item;
					if(data[0] == itemArray[0]) {
						data[1] = itemArray[1];
						ti.setData(data);
						break;
					}
				}
			}
			return;
		}
		return;
	}
	
	/**
	 * Validates what getItems returns
	 * @return true if all elements are valid (complete)
	 */
	public boolean validateItems() {
		for(Object triple : getItems()) {
			if(triple == null) {
				return false;
			}
			if(!(triple instanceof Object[])) {
				return false;
			}
			Object[] tripleArray = (Object[])triple;
			if(tripleArray.length != 3) {
				return false;
			}
			if(tripleArray[0] != null && tripleArray[1].equals("")) {
				return false;
			}	
		}
		return true;
	}

}
