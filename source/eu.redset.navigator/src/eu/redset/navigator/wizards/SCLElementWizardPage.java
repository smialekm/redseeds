package eu.redset.navigator.wizards;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redset.emf.model.tsl.TSLBusinessLayerFacade;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestPackage;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.util.TSLModelHelper;
import eu.redset.tsl.editor.editors.UseCaseTestScenarioChooseWidget;



public class SCLElementWizardPage extends WizardPage {
	
	private Text elementName;
	private Composite container;
	private ISelection selection;
	private Object type;
	private TSLBusinessLayerFacade facade;
	private Table table;
	private TableViewer sensesTableViewer;
	private UseCaseTestScenario ucts;
	private Text elementDescription;

	
	public SCLElementWizardPage(ISelection selection, java.lang.Object type) {
		super(ResourceMessages.NewTest_windowTitle);
		this.type = type;
		facade = SCProjectContainer.instance().getSCProject(
					SCProjectHelper.getIProject((IStructuredSelection)selection))
					.getTestSpecificationContainer().getTSLFacade(((IStructuredSelection)selection).getFirstElement());

		if (type == TestPackage.class) {
			setTitle(ResourceMessages.NewTestPackage_title);
			setDescription(ResourceMessages.NewTestPackage_description);
		}
		
		if (type == TestScenario.class) {
			setTitle(ResourceMessages.NewTestScenario_title);
			setDescription(ResourceMessages.NewTestScenario_description);
		}
		
		if (type == TestCase.class) {
			setTitle(ResourceMessages.NewTestCase_title);
			setDescription(ResourceMessages.NewTestCase_description);
		}
		this.selection = selection;
	}

	@Override
	public void createControl(Composite parent) {
		String selectedItemPath = "";
		
		if (selection != null) {
			ITreeSelection treeSelection = (ITreeSelection) selection;
			TreePath[] treePaths = treeSelection.getPaths();
			if (treePaths.length == 1){
				TreePath treePath = treePaths[0];
				for (int i = 1; i < treePath.getSegmentCount(); i++) {
					selectedItemPath+=TSLModelHelper.instance().getName(treePath.getSegment(i));
					if (i<treePath.getSegmentCount()-1){
						selectedItemPath+="\\";
					}
				}
			}			
		}
		
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		Label labelSelectedItem = new Label(container, SWT.NULL);
		labelSelectedItem.setText("Parent");
		Text textSelectedItem = new Text(container, SWT.BORDER | SWT.SINGLE |SWT.READ_ONLY);
		textSelectedItem.setText(selectedItemPath);
		
		if (!(type == TestCase.class)){
			Label labelClipboardName = new Label(container, SWT.NULL);
			labelClipboardName.setText("Name");
			elementName = new Text(container, SWT.BORDER | SWT.SINGLE);
			elementName.setText("");
			if (type == TestScenario.class){
				Label descLabel = new Label(container, SWT.NULL);
				GridData gridData = new GridData();
				gridData.verticalAlignment = SWT.TOP;
				descLabel.setText("Description");
				elementDescription = new Text(container, SWT.BORDER | SWT.SINGLE);
				gridData = new GridData();
				gridData.horizontalAlignment = SWT.FILL;
				gridData.grabExcessHorizontalSpace = true;
				gridData.verticalAlignment = SWT.FILL;
				gridData.grabExcessVerticalSpace = true;
				elementDescription.setLayoutData(gridData);
				elementDescription.setText("-- Put description here --");
			}
		} else {
			Label labelClipboardName = new Label(container, SWT.NULL);
			labelClipboardName.setText("Name");
			elementName = new Text(container, SWT.BORDER | SWT.SINGLE);
			elementName.setText("");
			
			Label emptyLabel = new Label(container, SWT.NULL);
			emptyLabel.setText("");
			
			System.out.println(facade);
			final UseCaseTestScenarioChooseWidget tw = new UseCaseTestScenarioChooseWidget(container, facade.getTestSpecification());
			tw.setBounds(10, 60, 650, 165);
			tw.setEnabled(true);
			tw.refresh();
			tw.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseUp(MouseEvent e) {

					
				}
				
				@Override
				public void mouseDown(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			tw.addAssignSenseListener(new SelectionListener(){
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}
				@Override
				public void widgetSelected(SelectionEvent e) {
					UseCaseTestScenario s = tw.getSelectedUseCaseTestScenario();
					
					elementName.setText(TSLModelHelper.instance().getName(s.eContainer())+" - "+s.getName());
					ucts = s;
				}
			});
			
			elementName.setEnabled(false);
		}
		
		

		Label emptyLabel = new Label(container, SWT.NULL);
		emptyLabel.setText("");
				
		elementName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		elementName.setLayoutData(gd);
		elementName.setFocus();
		setControl(container);
		setPageComplete(false);

	}
	
	/**
	 * Ensures that data is properly set.
	 */
	private String dialogChanged() {
		String elemNameString = getSCLElementName();

		if(elemNameString.length() == 0) {
			updateStatus(ResourceMessages.Validation_name);
			return ResourceMessages.Validation_name;
		}
		
		updateStatus(null);
		return "";
	}
	
	public String getSCLElementName() {
		return elementName.getText();
	}
	
	public String getTSElementDescription() {
		if (this.elementDescription != null)
			return this.elementDescription.getText();
		else
			return "";
	}
		
	private void updateStatus(String message) {
		if (message == null){
			setPageComplete(true);
			setErrorMessage(null);
		} else {
			setPageComplete(false);
			setErrorMessage(message);
		}

	}
	
	public UseCaseTestScenario getUseCaseTestScenario(){
		return this.ucts;
	}
	
	@Override
	public Control getControl() {
		return container;
	}

}
