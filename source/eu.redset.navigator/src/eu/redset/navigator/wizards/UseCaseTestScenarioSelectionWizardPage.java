package eu.redset.navigator.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.operation.IRunnableWithProgress;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.TSLBusinessLayerFacade;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.util.TSLModelHelper;
import eu.redset.tsl.editor.editors.UseCaseTestScenarioChooseWidget;

public class UseCaseTestScenarioSelectionWizardPage extends WizardPage implements IRunnableWithProgress {

	private Text elementName;
	private Composite container;
	private UseCaseTestScenario ucts;
	private Text elementDescription;
	private UseCaseTest uct;
	private Button isInsertButton;
	private UseCaseTestScenarioChooseWidget tw;
	private ControlSentence sentence;
	private TSLBusinessLayerFacade tslFacade;
	
	public UseCaseTestScenarioSelectionWizardPage(
			UseCaseTest useCaseTest, ControlSentence sentence) {
		super(ResourceMessages.SelectUseCaseTestScenario_windowTitle);

		this.uct = useCaseTest;
		this.sentence = sentence;
		setTitle("Invoked Use Case Test Scenario Selection");
		setDescription("Invoked Use Case Test Scenario Selection");
		this.tslFacade = SCProjectContainer.instance().getSCProject(uct).getTestSpecificationContainer().getTSLFacade(uct);
	}

	@Override
	public void createControl(Composite parent) {


		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		
		Label labelSelectedItem = new Label(container, SWT.NULL);
		labelSelectedItem.setText("Parent");
		Text textSelectedItem = new Text(container, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		String pathName = "";
		EObject eObj = tslFacade.getTestCase(sentence);
		while (!(eObj instanceof TestScenario)) {
			if (eObj instanceof TestCase){
				pathName = ((TestCase)eObj).getOrderNumber()+". "+((TestCase)eObj).getUcName()+"/"+pathName;
			}
			eObj = eObj.eContainer();
		}
		pathName = ((TestScenario)eObj).getName()+"/"+pathName;
		textSelectedItem.setText(pathName);

		Label labelClipboardName = new Label(container, SWT.NULL);
		labelClipboardName.setText("Name");
		elementName = new Text(container, SWT.BORDER | SWT.SINGLE);
		elementName.setText("");

		Label checkLabel = new Label(container, SWT.NULL);
		checkLabel.setText("Invoke?");
		
		isInsertButton = new Button(container, SWT.CHECK);
		isInsertButton.setSelection(false);
		isInsertButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (isInsertButton.getSelection()){
					elementName.setText("");
					tw.setEnabled(true);
					setPageComplete(false);
				}
				else {
					elementName.setText("");
					tw.setEnabled(false);
					setPageComplete(true);
				}
				tw.refresh();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Label emptyLabel = new Label(container, SWT.NULL);
		emptyLabel.setText("");
		
		tw = new UseCaseTestScenarioChooseWidget(
				container, uct);
		
		tw.setBounds(10, 60, 650, 165);
		tw.setEnabled(true);
		tw.refresh();
		tw.setEnabled(false);
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
		tw.addAssignSenseListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				UseCaseTestScenario s = tw.getSelectedUseCaseTestScenario();

				elementName.setText(TSLModelHelper.instance().getName(
						s.eContainer())
						+ " - " + s.getName());
				ucts = s;
			}
		});

		elementName.setEnabled(false);

		elementName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				//dialogChanged();
				if (tw.getSelectedUseCaseTestScenario() != null)
					if (elementName.getText().length() > 0)
						setPageComplete(true);
			}
		});

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		elementName.setLayoutData(gd);
		elementName.setFocus();
		setControl(container);
		setPageComplete(true);

	}

	/**
	 * Ensures that data is properly set.
	 */
	/*private String dialogChanged() {
		String elemNameString = getSCLElementName();

		if (elemNameString.length() == 0) {
			updateStatus(ResourceMessages.Validation_name);
			return ResourceMessages.Validation_name;
		}

		updateStatus(null);
		return "";
	}
	*/

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
		if (message == null) {
			setPageComplete(true);
			setErrorMessage(null);
		} else {
			setPageComplete(false);
			setErrorMessage(message);
		}

	}

	public UseCaseTestScenario getUseCaseTestScenario() {
		return this.ucts;
	}

	@Override
	public Control getControl() {
		return container;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		// TODO Auto-generated method stub
		
	}

}
