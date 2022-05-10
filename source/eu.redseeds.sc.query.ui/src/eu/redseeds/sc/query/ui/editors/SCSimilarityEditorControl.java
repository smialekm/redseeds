package eu.redseeds.sc.query.ui.editors;

import java.lang.reflect.InvocationTargetException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.query.engine.ComparisonType;
import eu.redseeds.sc.query.engine.SimilarSCProject;
import eu.redseeds.sc.query.ui.Activator;
import eu.redseeds.sc.query.ui.actions.FourTreeViewOpenAction;
import eu.redseeds.sc.query.ui.jobs.FindCasesJob;
import eu.redseeds.sc.query.ui.view.SCSimilarityResultView;




public class SCSimilarityEditorControl extends Composite {


	//private Button customiseButton;
	private Button runButton;
	private Button openButton;
	private Button includeRequirements;
	private Button includeDomain;
	private Label resultListLabel;
	private Label scProjectLabel;
	private Label simLevelLabel;
	private Label includeDomLabel;
	private Label includeReqLabel;
	private Table scProjectTable;
	private Table resultTable;
	private TableViewer scProjectTableViewer;
	private TableViewer resultTableViewer;
	private Spinner spin;
	private SimilarSCProjectTableContentProvider provider;
	private ComparisonType comparisonType;
	private final Map<TableColumn,SortType> tableColumnSortType=new HashMap<TableColumn, SortType>();


	public SCSimilarityEditorControl(Composite parent, SCSimilarityEditor editor) {
		super(parent, SWT.NONE);
		createContent();
	}

	private IWorkbenchPage getActivePage(){
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	}

	private void createContent() {



		scProjectLabel = new Label(this, SWT.NONE);
		scProjectLabel.setBounds(20, 10, 150, 20);
		scProjectLabel.setText("Software Cases");


		// Create table
		scProjectTable = new Table(this, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
		scProjectTable.setLinesVisible(true);
		scProjectTable.setHeaderVisible(true);
		scProjectTable.setBounds(20, 40, 230, 200);

		TableColumn sCColumn = new TableColumn(scProjectTable, SWT.LEFT, 0);
		sCColumn.setWidth(226);
		sCColumn.setText("Software Case");
		sCColumn.setToolTipText("Software Case");

		scProjectTableViewer = new TableViewer(scProjectTable);
		scProjectTableViewer.setUseHashlookup(true);
		scProjectTableViewer.setContentProvider(new SCProjectTableContentProvider());
		scProjectTableViewer.setLabelProvider(new SCProjectTableContentProvider());
		scProjectTableViewer.setInput("");

//		customiseButton = new Button(this, SWT.NONE);
//		customiseButton.setText("Custom query");
//		customiseButton.setBounds(20, 260, 80, 22);

		includeDomain = new Button(this, SWT.CHECK);
		includeDomain.setEnabled(true);
		includeDomain.setSelection(true);
		includeDomain.setBounds(20, 257, 20, 20);

		includeDomLabel = new Label(this, SWT.NONE);
		includeDomLabel.setBounds(45, 260, 100, 20);
		includeDomLabel.setText("Include Domain");

		includeRequirements = new Button(this, SWT.CHECK);
		includeRequirements.setEnabled(true);
		includeRequirements.setSelection(true);
		includeRequirements.setBounds(20, 277, 20, 20);

		includeReqLabel = new Label(this, SWT.NONE);
		includeReqLabel.setBounds(45, 280, 110, 20);
		includeReqLabel.setText("Include Requirements");

		simLevelLabel = new Label(this, SWT.NONE);
		simLevelLabel.setBounds(20, 310, 75, 20);
		simLevelLabel.setText("Similarity level");

		spin = new Spinner(this, SWT.BORDER);
	    spin.setSize(30, 20);
	    spin.setValues(50, 0, 100, 2, 5, 20);
	    spin.setBounds(95, 307, 50, 20);

		runButton = new Button(this, SWT.NONE);
		runButton.setText("Run query");
		runButton.setBounds(170, 257, 80, 22);

		resultListLabel = new Label(this, SWT.NONE);
		resultListLabel.setText("Results");
		resultListLabel.setBounds(300, 10, 60, 20);

		resultTable = new Table(this, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
		resultTable.setLinesVisible(true);
		resultTable.setHeaderVisible(true);
		resultTable.setBounds(300, 40, 300, 200);


		TableColumn resultScColumn = new TableColumn(resultTable, SWT.LEFT, 0);
		resultScColumn.setWidth(226);
		resultScColumn.setText("Software Case");
		resultScColumn.setToolTipText("Software Case");
		tableColumnSortType.put(resultScColumn, SortType.DOWN);
		addSortListenerAlphabetic(resultTable, resultScColumn);

		TableColumn similarityColumn = new TableColumn(resultTable, SWT.LEFT, 1);
		similarityColumn.setWidth(70);
		similarityColumn.setText("Similarity");
		similarityColumn.setToolTipText("Similarity");
		tableColumnSortType.put(similarityColumn, SortType.DOWN);
		addSortListenerAlphabetic(resultTable, similarityColumn);

		resultTableViewer = new TableViewer(resultTable);
		resultTableViewer.setUseHashlookup(true);
		provider = new SimilarSCProjectTableContentProvider();
		provider.setSimLevel((double)spin.getSelection()/100);
		resultTableViewer.setContentProvider(provider);
		resultTableViewer.setLabelProvider(provider);
		//resultTableViewer.setInput(null);

		openButton = new Button(this, SWT.NONE);
		openButton.setText("Open");
		openButton.setBounds(300, 257, 60, 22);

		enableButtons();

		// Event handling
		resultTable.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				enableButtons();
				notifyResultView(e);
			}
		});

		scProjectTable.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				enableButtons();
			}
		});

		includeDomain.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				enableButtons();
			}
		});

		includeRequirements.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				enableButtons();
			}
		});


		runButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				  final IWorkbench workbench = PlatformUI.getWorkbench();
				  //final ComparisonType comparisonType = null;
				  Display display = Display.getCurrent();
				  if(display == null) {
				    display = Display.getDefault();
				  }

				  display.syncExec(new Runnable() {
					public void run() {
						IWorkbenchWindow window = workbench
								.getActiveWorkbenchWindow();
						if (window != null) {
							Shell shell = window.getShell();
							boolean cancelable = true;
							// provider.setSimLevel();
							if (includeDomain.getSelection() && includeRequirements.getSelection()){
								comparisonType=ComparisonType.REQUIREMENTS_AND_DOMAIN;
							} else if (includeDomain.getSelection()){
								comparisonType=ComparisonType.DOMAIN_ONLY;
							} else if (includeRequirements.getSelection()){
								comparisonType=ComparisonType.REQUIREMENTS_ONLY;
							}
							SCProject selectedProj = (SCProject) getSelectionItem(scProjectTable);
							FindCasesJob fSCJob = new FindCasesJob(
									selectedProj,
									(double) spin.getSelection() / 100, comparisonType);
							try {

								ProgressMonitorDialog dialog = new ProgressMonitorDialog(
										shell);
								// eclipse throws null pointer before IJob.run
								// if is forked
								dialog.run(true, cancelable, fSCJob);
							} catch (InvocationTargetException e) {
								Activator.logError("Error during running query: " + e.getMessage(), e);
							} catch (InterruptedException e) {
								Activator.logError("Error during running query: " + e.getMessage(), e);
							}
							resultTableViewer.setInput(fSCJob.getFoundProjects());
						}
					}
				});


// Display.getDefault().getCursorControl().setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_WAIT));
//				try {
//					provider.setSimLevel((double)spin.getSelection()/100);
//					SCProject selectedProj = (SCProject)getSelectionItem(scProjectTable);
//					resultTableViewer.setInput(selectedProj);
//				} finally {
//					Display.getDefault().getCursorControl().setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
//				}
			}
		});

		openButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				if(resultTable.getSelectionIndex() < 0) {
					return;
				}
				Object obj = resultTable.getSelection()[0].getData();
				if(obj instanceof SimilarSCProject) {
					FourTreeViewOpenAction fourTreeAction = new FourTreeViewOpenAction();
					fourTreeAction.init(PlatformUI.getWorkbench().getActiveWorkbenchWindow());
					fourTreeAction.run(
							((SimilarSCProject)obj).getScProject(),
							Arrays.asList(((SimilarSCProject)obj)
									.getSimilarityValue()
									.getMatchedRequirementsInPastCase().toArray()));
				}
			}

		});

	}

	private Object getSelectionItem(Table table){
		TableItem[] items = table.getSelection();
		if (items.length == 1){
				return items[0].getData();
		}
		return null;
	}

	private void addSortListenerAlphabetic(final Table table,final TableColumn forColumn){
		Listener sortListener = new Listener() {
	        public void handleEvent(Event e) {
	            TableItem[] items = table.getItems();
	            Collator collator = Collator.getInstance(Locale.getDefault());
	            TableColumn column = (TableColumn)e.widget;
	            int columnsNumber=table.getColumnCount();
	            if(column==forColumn){
	            	int index = table.indexOf(forColumn);
		            for (int i = 1; i < items.length; i++) {
		                String value1 = items[i].getText(index);
		                for (int j = 0; j < i; j++){
		                    String value2 = items[j].getText(index);
		                    boolean flipItems=CompareStrategy.comapre(tableColumnSortType.get(column), collator, value1, value2);
		                    if (flipItems) {
		                    	List<String> columnsValues=new ArrayList<String>();
		                    	for(int cidx=0;cidx<columnsNumber;cidx++){
		                    		columnsValues.add(items[i].getText(cidx));
		                    	}
		                        String[] values = columnsValues.toArray(new String[0]);
		                        Object data=items[i].getData();
		                        items[i].dispose();
		                        TableItem item = new TableItem(table, SWT.NONE, j);
		                        item.setText(values);
		                        item.setData(data);
		                        items = table.getItems();
		                        break;
		                    }
		                }
		            }
		            table.setSortColumn(column);
		            if(tableColumnSortType.containsKey(column)){
		            	tableColumnSortType.put(column, tableColumnSortType.get(column).getInverseSortType());
		            }
	            }
	        }


	    };
	    forColumn.addListener(SWT.Selection, sortListener);
	}


	private void notifyResultView(SelectionEvent e){
		try {
			SCSimilarityResultView resultView =(SCSimilarityResultView)getActivePage().showView(SCSimilarityResultView.VIEW_ID);
			if(resultView!=null){
				if(e.item instanceof TableItem){
					TableItem tableItem=(TableItem) e.item;
					if(tableItem.getData() instanceof SimilarSCProject){
						SimilarSCProject similarSCProject=(SimilarSCProject) tableItem.getData();
						resultView.selectionInEditorChanged(similarSCProject);
					}else{
						Activator.log("Element in TableItem is not instance of SimilarSCProject, but this implementation assume that it should be", IStatus.ERROR);
					}
				}else{
					Activator.log("Element in result table is not instance of TableItem, but this implementation assume that it should be", IStatus.ERROR);
				}
			}
		} catch (PartInitException e1) {
			Activator.log(e1.getMessage(),IStatus.WARNING);
		}
	}

	private void enableButtons(){
		if (getSelectionItem(scProjectTable) == null){
			runButton.setEnabled(false);
			//customiseButton.setEnabled(false);
		} else {
			runButton.setEnabled(true);
			//customiseButton.setEnabled(true);
		}

		if ((includeDomain.getSelection() || includeRequirements.getSelection()) && runButton.getEnabled()){
			runButton.setEnabled(true);
		} else {
			runButton.setEnabled(false);
		}

		if (getSelectionItem(resultTable) == null){
			openButton.setEnabled(false);
		} else {
			openButton.setEnabled(true);
		}
	}

	private static class CompareStrategy{
    	static boolean comapre(SortType sortType,Collator collator,String value1,String value2){
    		//default
    		boolean result=compareDown( collator, value1, value2);
    		if(SortType.UP==sortType){
    				result=compareUp( collator, value1, value2);
    		}
    		return result;

    	}
    	private static boolean compareDown(Collator collator,String value1,String value2){
        	return collator.compare(value1, value2)< 0;
        }
        private static boolean compareUp(Collator collator,String value1,String value2){
        	return collator.compare(value1, value2)> 0;
        }
    }
	private enum SortType{UP,DOWN;
	private static final Map<SortType,SortType> sortTypeToInverse=new EnumMap<SortType, SortType>(SortType.class);
	static{
		sortTypeToInverse.put(UP, DOWN);
		sortTypeToInverse.put(DOWN, UP);
	}

	public SortType getInverseSortType() {
		return sortTypeToInverse.get(this);
	}

}

}
