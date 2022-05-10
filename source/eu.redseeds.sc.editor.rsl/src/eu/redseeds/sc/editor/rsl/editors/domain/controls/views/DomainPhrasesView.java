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
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;

public class DomainPhrasesView extends Composite {

	public static final String COLUMN_PHRASE_TYPE = "Type";
	public static final String COLUMN_PHRASE_NAME = "Name";
	public static final String COLUMN_PHRASE_HAS_SENSES = "Hase senses";
	public static final String COLUMN_WHICH_CLIPBOARD = "Clipboard";
	
	private SCProject scProject;
	private Table table;
	private TableViewer phrasesTableViewer;
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
	        if (currentColumn.getText().equals(COLUMN_PHRASE_NAME)) {
	        	category = DomainManagerSorter.CATEGORY_PHRASE_NAME;
	        }
	        if (currentColumn.getText().equals(COLUMN_PHRASE_TYPE)) {
	        	category = DomainManagerSorter.CATEGORY_PHRASE_TYPE;
	        }
	        if (currentColumn.getText().equals(COLUMN_PHRASE_HAS_SENSES)) {
	        	category = DomainManagerSorter.CATEGORY_PHRASE_ASSIGMENT;
	        }
	        if (currentColumn.getText().equals(COLUMN_WHICH_CLIPBOARD)) {
	        	category = DomainManagerSorter.CATEGORY_CLIPBOARD;
	        }
	        
	        sorter.setCategory(category);
	        table.setSortDirection(dir);
	        phrasesTableViewer.refresh();
	        phrasesTableViewer.setComparator(sorter);

	    }
	};
	
	public DomainPhrasesView(Composite parent, int style, SCProject scProject) {
		super(parent, style);
		this.scProject = scProject;
		sorter = new DomainManagerSorter();
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
		column1.setText(COLUMN_PHRASE_TYPE);
		column1.addListener(SWT.Selection, sortListener);
		
		final TableColumn column2 = new TableColumn(table, SWT.LEFT, 1);
		column2.setWidth(70);
		column2.setText(COLUMN_PHRASE_HAS_SENSES);
		column2.addListener(SWT.Selection, sortListener);
		
		final TableColumn column3 = new TableColumn(table, SWT.LEFT, 2);
		column3.setWidth(70);
		column3.setText(COLUMN_WHICH_CLIPBOARD);
		//column3.addListener(SWT.Selection, sortListener);
		
		final TableColumn column4 = new TableColumn(table, SWT.LEFT, 3);
		column4.setWidth(231);
		column4.setText(COLUMN_PHRASE_NAME);
		column4.addListener(SWT.Selection, sortListener);
		
	
		
		
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
		        column3.setWidth(70);
		        column4.setWidth(width - column1.getWidth() - column2.getWidth());
		        table.setSize(area.width, area.height);
		      } else {
		        // table is getting bigger so make the table 
		        // bigger first and then make the columns wider
		        // to match the client area width
		        table.setSize(area.width, area.height);
		        column1.setWidth(70);
		        column2.setWidth(70);
		        column3.setWidth(70);
		        column4.setWidth(width - column1.getWidth() - column2.getWidth());
		      }
		    }
		  });
		
		phrasesTableViewer = new TableViewer(table);
		phrasesTableViewer.setUseHashlookup(true);
		
		phrasesTableViewer.setContentProvider(new DomainPhrasesViewContentProvider());
		phrasesTableViewer.setLabelProvider(new DomainPhrasesViewDecoratingLabelProvider(new DomainPhrasesViewLabelProvider(), null));

		phrasesTableViewer.setInput(scProject);
		
		sorter = new DomainManagerSorter();
		phrasesTableViewer.setComparator(sorter);
		phrasesTableViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {

				if (table.getSelectionIndex() >= 0) {

					TableItem selectedItem = table.getItem(table
							.getSelectionIndex());
					if (selectedItem.getData() != null) {
						PhraseDTO phrase = (PhraseDTO) selectedItem.getData();
						if (checkPhrase(phrase)){
							PropertiesOpenAction openPropertyAction = new PropertiesOpenAction();
							openPropertyAction.setProject(SCProjectContainer
								.instance().getSCProject(phrase));
							openPropertyAction.setParent(parentEditor);
							openPropertyAction.setPhraseOrTerm(phrase);
							openPropertyAction.run();
						}
					}

				}

			}

			private boolean checkPhrase(PhraseDTO phrase) {
				if (phrase instanceof ComplexVerbPhraseDTO && (((ComplexVerbPhraseDTO) phrase).getObject().getNounText()==null || ((ComplexVerbPhraseDTO) phrase).getPreposition().getName()==null || ((ComplexVerbPhraseDTO) phrase).getSimpleVerbPhrase().getObject().getNounText()==null || ((ComplexVerbPhraseDTO) phrase).getSimpleVerbPhrase().getVerb().getName()==null))return false;
				else if (phrase instanceof SimpleVerbPhraseDTO && (((SimpleVerbPhraseDTO) phrase).getObject().getNounText()==null || ((SimpleVerbPhraseDTO) phrase).getVerb().getName()==null)) return false;
				else if (phrase instanceof NounPhraseDTO && ((NounPhraseDTO) phrase).getNounText()==null) return false;
				return true;
			}
			
		});
			
		refreshButton.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				phrasesTableViewer.setInput(scProject);
			}});
		
		
	}
	
	public DomainManagerEditor getParentEditor() {
		return parentEditor;
	}


	public void setParentEditor(DomainManagerEditor parentEditor) {
		this.parentEditor = parentEditor;
	}
}
