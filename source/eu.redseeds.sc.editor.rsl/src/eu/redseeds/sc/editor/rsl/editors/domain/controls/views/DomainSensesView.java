package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.actions.PropertiesOpenAction;
import eu.redseeds.sc.editor.rsl.editors.domain.DomainManagerEditor;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;


public class DomainSensesView extends Composite {

	public static final String COLUMN_NOUN = "Noun";
	public static final String COLUMN_SENSE = "Assigned Sense";
	public static final String COLUMN_WHICH_CLIPBOARD = "Clipboard";
	
	private SCProject scProject;
	private Table table;
	private TableViewer sensesTableViewer;
	private Button refreshButton;
	private DomainManagerEditor parentEditor;
	private DomainManagerSorter sorter;
	private Composite comp;
	
	private Listener sortListener = new Listener() {
	    public void handleEvent(Event e) {
	        // determine new sort column and direction
	        TableColumn sortColumn = table.getSortColumn();
	        TableColumn currentColumn = (TableColumn) e.widget;
	        int dir = table.getSortDirection();
	        if (sortColumn == currentColumn) {
	        	table.setSortColumn(currentColumn);
	            dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
	        } else {
	            table.setSortColumn(currentColumn);
	            dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
	        }
	        // sort the data based on column and direction
	        int category = 0;
	        if (currentColumn.getText().equals(COLUMN_NOUN)) {
	        	category = DomainManagerSorter.CATEGORY_NOUN_NAME;
	        }
	        if (currentColumn.getText().equals(COLUMN_SENSE)) {
	        	category = DomainManagerSorter.CATEGORY_NOUN_SENSE;
	        }
	        if (currentColumn.getText().equals(COLUMN_WHICH_CLIPBOARD)) {
	        	category = DomainManagerSorter.CATEGORY_CLIPBOARD;
	        }
	        
	        sorter.setCategory(category);
	        table.setSortDirection(dir);
	        sensesTableViewer.refresh();
	        sensesTableViewer.setComparator(sorter);

	    }
	};
	
	public DomainSensesView(Composite parent, int style, SCProject scProject) {
		super(parent, style);
		this.scProject = scProject;
		this.comp = this;
		createContent();
		
	}

	
	private void createContent(){
		
		// Set the main layout
		FormLayout layout = new FormLayout();
		layout.marginWidth = 10;
		layout.marginHeight = 10;
		setLayout(layout);
		
		// Composite with Refresh Button
		Composite rowComp = new Composite(this, SWT.NONE);
		RowLayout rowLayout = new RowLayout();
		rowComp.setLayout(rowLayout);
		refreshButton = new Button(rowComp, SWT.PUSH);
		refreshButton.setText("Refresh");

		// Composite with table
		Composite fillComp = new Composite(this, SWT.NONE);
		FillLayout fillLayout = new FillLayout();
		fillComp.setLayout(fillLayout);
	
		// Create controls
		table = new Table(fillComp, SWT.SINGLE | SWT.BORDER | 
				SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		FormData fd = new FormData();
		fd.top = new FormAttachment(rowComp, 5);
		fd.left = new FormAttachment(0, 5);		
		fd.bottom = new FormAttachment(100, -10);
		fd.right = new FormAttachment(100, -10);
		fillComp.setLayoutData(fd);
		
		// Create columns
		final TableColumn column1 = new TableColumn(table, SWT.LEFT, 0);
		column1.setWidth(70);
		column1.setText(COLUMN_NOUN);
		column1.addListener(SWT.Selection, sortListener);
		
		// Create columns
		final TableColumn column2 = new TableColumn(table, SWT.LEFT, 1);
		column2.setWidth(70);
		column2.setText(COLUMN_WHICH_CLIPBOARD);
		//column2.addListener(SWT.Selection, sortListener);
		
		final TableColumn column3 = new TableColumn(table, SWT.LEFT, 2);
		
		column3.setText(COLUMN_SENSE);
		column3.addListener(SWT.Selection, sortListener);
		
		this.addControlListener(new ControlAdapter() {
		    public void controlResized(ControlEvent e) {
		    	Rectangle area = comp.getClientArea();
		      Point preferredSize = table.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		      int width = area.width;// - table.getBorderWidth();
		      if (preferredSize.y > area.height + table.getHeaderHeight()) {
		        // Subtract the scrollbar width from the total column width
		        // if a vertical scrollbar will be required
		        Point vBarSize = table.getVerticalBar().getSize();
		        width -= vBarSize.x;
		      }
		      Point oldSize = table.getSize();
		      if (oldSize.x > area.width) {
		        // table is getting smaller so make the columns 
		        // smaller first and then resize the table to
		        // match the client area width
		        column1.setWidth(70);
		        column2.setWidth(70);
		        column3.setWidth(width - column1.getWidth());
		        table.setSize(area.width, area.height);
		      } else {
		        // table is getting bigger so make the table 
		        // bigger first and then make the columns wider
		        // to match the client area width
		        table.setSize(area.width, area.height);
		        column1.setWidth(70);
		        column2.setWidth(70);
		        column3.setWidth(width - column1.getWidth());
		      }
		    }
		  });
		
		sensesTableViewer = new TableViewer(table);
		sensesTableViewer.setUseHashlookup(true);
		
		sensesTableViewer.setContentProvider(new DomainSensesViewContentProvider());
		sensesTableViewer.setLabelProvider(new DomainSensesViewDecoratingLabelProvider(new DomainSensesViewLabelProvider(), null));

		sensesTableViewer.setInput(scProject);
		sorter = new DomainManagerSorter();
		sensesTableViewer.setComparator(sorter);
		sensesTableViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {

				if (table.getSelectionIndex() >= 0) {

					TableItem selectedItem = table.getItem(table
							.getSelectionIndex());
					if (selectedItem.getData() != null) {
						TermDTO term = (TermDTO) selectedItem.getData();
						PropertiesOpenAction openPropertyAction = new PropertiesOpenAction();
						openPropertyAction.setProject(SCProjectContainer
								.instance().getSCProject(term));
						openPropertyAction.setParent(parentEditor);
						openPropertyAction.setPhraseOrTerm(term);
						openPropertyAction.run();
					}

				}

			}
		});
		
		refreshButton.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				sensesTableViewer.setInput(scProject);
			}});
		
	}
	
	public DomainManagerEditor getParentEditor() {
		return parentEditor;
	}


	public void setParentEditor(DomainManagerEditor parentEditor) {
		this.parentEditor = parentEditor;
	}
	
}
