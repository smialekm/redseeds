package eu.redset.tsl.editor.editors;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redset.emf.model.tsl.BLTest;
import eu.redset.emf.model.tsl.DomainObject;
import eu.redset.emf.model.tsl.GUITest;
import eu.redset.emf.model.tsl.NFTest;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.TSLBusinessLayerFacade;
import eu.redset.emf.model.tsl.Test;
import eu.redset.emf.model.tsl.TestParameter;
import eu.redset.emf.model.tsl.TestValue;

public class NotionTestDetailesViewControl extends Composite{

	private Test test;
	Label typeLabel;
	Label nameLabel;
	Label descLabel;
	//Label chToTestLabel;
	Label inputValueLabel;
	Text nameText;
	Text descText;
	//Text chToTestText;
	Text inputValueText;
	Label testDataLabel;
	Text testDataText;
	private TestDetailsEditor editor;
	private TSLBusinessLayerFacade tslFacade;
	

	
	public NotionTestDetailesViewControl(Composite parent,TestDetailsEditor editor, Test test) {
		super(parent, SWT.None);
		this.test = test;
		this.editor = editor;
		this.tslFacade = SCProjectContainer.instance().getSCProject(test).getTestSpecificationContainer().getTSLFacade(test);
		//parent.setLayout(new FillLayout());
		createContent();
	}
	

	private void createContent(){
		

		FormLayout layout = new FormLayout();
		layout.marginWidth = 10;
		layout.marginHeight = 10;

		setLayout(layout);
		FormData fd = new FormData();
		
		typeLabel = new Label(this,SWT.BOLD);
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(0, 0);
		typeLabel.setText("Notion                          ");
		typeLabel.setLayoutData(fd);
 
		
		nameLabel = new Label(this, SWT.RIGHT);
		fd = new FormData();
		fd.top = new FormAttachment(typeLabel, 10);
		fd.left = new FormAttachment(0, 0);
		nameLabel.setText("Name:");
		nameLabel.setLayoutData(fd);
		
		
		nameText = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		if (test instanceof Notion)
			nameText.setText(((Notion)test).getNotionName());
		else if (test instanceof DomainObject)
			nameText.setText(((DomainObject)test).getName());
		fd = new FormData();
		fd.top = new FormAttachment(typeLabel, 10);
		fd.left = new FormAttachment(typeLabel, 5);
		fd.right = new FormAttachment(100, 5);
		nameText.setLayoutData(fd);
		
		descLabel = new Label(this, SWT.RIGHT);
		fd = new FormData();
		fd.top = new FormAttachment(nameLabel, 10);
		fd.left = new FormAttachment(0, 0);
		descLabel.setText("Description:");
		descLabel.setLayoutData(fd);

		descText = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		if (test instanceof Notion)
			if (((Notion)test).getNotionDescription() == null)
				descText.setText("");
			else
				descText.setText(((Notion)test).getNotionDescription());
		else if (test instanceof DomainObject){
			if (((DomainObject)test).getDescription() == null)
				descText.setText("");
			else
				descText.setText(((DomainObject)test).getDescription());
		}
		fd = new FormData();
		fd.top = new FormAttachment(nameLabel, 10);
		fd.left = new FormAttachment(typeLabel, 5);
		fd.right = new FormAttachment(100, 5);
		descText.setLayoutData(fd);
		
		/*
		chToTestLabel = new Label(this, SWT.RIGHT);
		fd = new FormData();
		fd.top = new FormAttachment(descLabel, 10);
		fd.left = new FormAttachment(0, 0);
		chToTestLabel.setText("Characterisctics to test:");
		chToTestLabel.setLayoutData(fd);
		
		chToTestText = new Text(this, SWT.BORDER);
		chToTestText.setText("Ergonomy");
		fd = new FormData();
		fd.top = new FormAttachment(descLabel, 10);
		fd.left = new FormAttachment(typeLabel, 5);
		fd.right = new FormAttachment(100, 5);
		chToTestText.setLayoutData(fd);
		*/
		
		testDataLabel = new Label(this, SWT.RIGHT);
		fd = new FormData();
		fd.top = new FormAttachment(descLabel, 10);
		fd.left = new FormAttachment(0, 0);
		testDataLabel.setText("Test data:");
		testDataLabel.setLayoutData(fd);
		
		testDataText = new Text(this, SWT.BORDER);
		//inputValueText.setText("Ergonomic");
		fd = new FormData();
		if (test instanceof Notion)
			testDataText.setEditable(false);
		else if (test instanceof DomainObject){
			testDataText.setEditable(true);
			List tvList = ((DomainObject)test).getInputData();
			if (tvList.size()>0)
				testDataText.setText(((TestValue)tvList.get(0)).getName());
		}
		fd.top = new FormAttachment(descLabel, 10);
		fd.left = new FormAttachment(typeLabel, 5);
		fd.right = new FormAttachment(100, 5);
		testDataText.setLayoutData(fd);
		testDataText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				editor.setDirty(true);
			}
		});
		
		inputValueLabel = new Label(this, SWT.RIGHT);
		fd = new FormData();
		fd.top = new FormAttachment(testDataLabel, 10);
		fd.left = new FormAttachment(0, 0);
		inputValueLabel.setText("Input value:");
		inputValueLabel.setLayoutData(fd);
		
		inputValueText = new Text(this, SWT.BORDER);
		//inputValueText.setText("Ergonomic");
		fd = new FormData();
		if (test instanceof Notion)
			inputValueText.setEditable(false);
		else if (test instanceof DomainObject){
			inputValueText.setEditable(true);
			List tvList = ((DomainObject)test).getInputData();
			if (tvList.size()>0)
				inputValueText.setText(((TestValue)tvList.get(0)).getValue());
		}
		fd.top = new FormAttachment(testDataLabel, 10);
		fd.left = new FormAttachment(typeLabel, 5);
		fd.right = new FormAttachment(100, 5);
		inputValueText.setLayoutData(fd);
		inputValueText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				editor.setDirty(true);
			}
		});
	}
	
	public void saveTest(){
		if (test instanceof DomainObject){
			
			
			DomainObject obj = (DomainObject)test;
			TestValue testValue;
			List<TestValue> testInValList = obj.getInputData();
				
			if (testInValList.size() == 0){
				testValue = tslFacade.createTestValue(obj);
				testValue.setName(testDataText.getText());
				testValue.setValue(inputValueText.getText());
			} else {
				testValue = testInValList.get(0);
				testValue.setName(testDataText.getText());
				testValue.setValue(inputValueText.getText());
			}
		}
			
		SCProjectContainer.instance().getSCProject(test).saveTS(test);
	}
		
}
