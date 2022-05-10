package eu.remics.alp.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;

/**
 * Control placed on pattern selection page of the ALP import wizard
 * @author aambroziewicz
 *
 */
public class PatternSelectionControl extends Composite {
	
	private Label scProjectLabel;
	private Table scProjectTable;
	private TableViewer scProjectTableViewer;
	private Label patternsLabel;
	private Table patternsTable;
	private TableViewer patternsTableViewer;
	
	protected ImportALPWizardPatternSelectionPage parentPage;

	PatternSelectionControl(Composite parent, ImportALPWizardPatternSelectionPage parentPage) {
		super(parent, SWT.NONE);
		this.parentPage = parentPage;
		createContent();
	}
	
	private void createContent() {
		
		scProjectLabel = new Label(this, SWT.NONE);
		scProjectLabel.setBounds(20, 10, 150, 20);
		scProjectLabel.setText("Software Cases");
		
		// Create table
		scProjectTable = new Table(this, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
		scProjectTable.setLinesVisible(true);
		scProjectTable.setHeaderVisible(true);
		scProjectTable.setBounds(20, 40, 260, 200);
		
		TableColumn scColumn = new TableColumn(scProjectTable, SWT.LEFT, 0);
		scColumn.setWidth(256);
		scColumn.setText("Software Case");
		scColumn.setToolTipText("Software Case");
		
		scProjectTableViewer = new TableViewer(scProjectTable);
		scProjectTableViewer.setUseHashlookup(true);
		scProjectTableViewer.setContentProvider(new SCProjectTableContentLabelProvider());
		scProjectTableViewer.setLabelProvider(new SCProjectTableContentLabelProvider());
		scProjectTableViewer.setSorter(new ViewerSorter() {
			public int compare(Viewer viewer, Object o1, Object o2) {
				return ((SCProject)o1).getName().compareToIgnoreCase(((SCProject)o2).getName());
			}
		});
		
		IStructuredSelection select = (IStructuredSelection) PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(select == null) {
			return;
		}
		if(select.getFirstElement() == null) {
			return;
		}
		SCProject currProject = null;
		if(select.getFirstElement() instanceof IProject) {
			currProject = SCProjectContainer.instance()
				.getSCProject((IProject)select.getFirstElement());
		}
		else {
			currProject = SCProjectContainer.instance()
				.getSCProject(select.getFirstElement());
		}
		
		scProjectTableViewer.setInput(currProject);
		scProjectTableViewer.refresh();
		
		patternsLabel = new Label(this, SWT.NONE);
		patternsLabel.setBounds(290, 10, 150, 20);
		patternsLabel.setText("Patterns");
		
		// Create table
		patternsTable = new Table(this, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
		patternsTable.setLinesVisible(true);
		patternsTable.setHeaderVisible(true);
		patternsTable.setBounds(290, 40, 260, 200);
		
		TableColumn patternsColumn = new TableColumn(patternsTable, SWT.LEFT, 0);
		patternsColumn.setWidth(256);
		patternsColumn.setText("Pattern");
		patternsColumn.setToolTipText("Pattern");
		
		patternsTableViewer = new TableViewer(patternsTable);
		patternsTableViewer.setUseHashlookup(true);
		patternsTableViewer.setContentProvider(new PatternTableContentLabelProvider());
		patternsTableViewer.setLabelProvider(new PatternTableContentLabelProvider());
		patternsTableViewer.setSorter(new ViewerSorter() {
			public int compare(Viewer viewer, Object o1, Object o2) {
				return (((RequirementsPackageDTO)o1).getName().compareToIgnoreCase(((RequirementsPackageDTO)o2).getName()));
			}
		});
		patternsTableViewer.setInput(null);
		
		// Event handling
		scProjectTable.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				refreshPatterns();
				enableButtons();
			}
		});
		
		patternsTable.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				enableButtons();
			}
		});
	}
	
	private void enableButtons() {
		if(getSelectionItem(patternsTable) != null) {
			parentPage.setPageComplete(true);
			parentPage.setSelectedPattern((RequirementsPackageDTO)getSelectionItem(patternsTable));
		}
		else {
			parentPage.setPageComplete(false);
		}
		
	}

	private void refreshPatterns() {
		patternsTableViewer.setInput(getSelectionItem(scProjectTable));
		patternsTableViewer.refresh();
	}
	
	private Object getSelectionItem(Table table){
		TableItem[] items = table.getSelection();
		if (items.length == 1){
				return items[0].getData();
		}
		return null;
	}

}
