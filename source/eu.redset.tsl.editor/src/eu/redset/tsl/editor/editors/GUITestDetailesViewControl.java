package eu.redset.tsl.editor.editors;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redset.emf.model.tsl.GUITest;
import eu.redset.emf.model.tsl.GUITestInstance;
import eu.redset.emf.model.tsl.TSLBusinessLayerFacade;
import eu.redset.emf.model.tsl.Test;
import eu.redset.emf.model.tsl.TestParameter;

public class GUITestDetailesViewControl extends Composite{

	private Test test;
	Label typeLabel;
	Label nameLabel;
	Label descLabel;
	Label chToTestLabel;
	Label expectedValueLabel;
	Text nameText;
	Text descText;
	Text chToTestText;
	Text expectedValueText;
	private TestDetailsEditor editor;
	private TSLBusinessLayerFacade tslFacade;

	
	public GUITestDetailesViewControl(Composite parent, TestDetailsEditor editor, Test test) {
		super(parent, SWT.None);
		this.editor = editor;
		this.test = test;
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
		typeLabel.setText("GUI Test                         ");
		typeLabel.setLayoutData(fd);
 
		
		nameLabel = new Label(this, SWT.RIGHT);
		fd = new FormData();
		fd.top = new FormAttachment(typeLabel, 10);
		fd.left = new FormAttachment(0, 0);
		nameLabel.setText("Name:");
		nameLabel.setLayoutData(fd);
		
		
		nameText = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		nameText.setText(test.getName());
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
		if (test.getDescription() == null)
			descText.setText("");
		else
			descText.setText(test.getDescription());
		if (test instanceof GUITest){
			descText.setEditable(true);
		} else {
			descText.setEditable(false);
		}
		fd = new FormData();
		fd.top = new FormAttachment(nameLabel, 10);
		fd.left = new FormAttachment(typeLabel, 5);
		fd.right = new FormAttachment(100, 5);
		descText.setLayoutData(fd);
				
		chToTestLabel = new Label(this, SWT.RIGHT);
		fd = new FormData();
		fd.top = new FormAttachment(descLabel, 10);
		fd.left = new FormAttachment(0, 0);
		chToTestLabel.setText("Characterisctics to test:");
		chToTestLabel.setLayoutData(fd);
		
		chToTestText = new Text(this, SWT.BORDER);
		chToTestText.setText("");
		fd = new FormData();
		fd.top = new FormAttachment(descLabel, 10);
		fd.left = new FormAttachment(typeLabel, 5);
		fd.right = new FormAttachment(100, 5);
		chToTestText.setLayoutData(fd);
		if (test instanceof GUITest)
			chToTestText.setEditable(false);
		else if (test instanceof GUITestInstance)
			chToTestText.setEditable(true);
		
		expectedValueLabel = new Label(this, SWT.RIGHT);
		fd = new FormData();
		fd.top = new FormAttachment(chToTestLabel, 10);
		fd.left = new FormAttachment(0, 0);
		expectedValueLabel.setText("Expected value:");
		expectedValueLabel.setLayoutData(fd);
		
		expectedValueText = new Text(this, SWT.BORDER);
		expectedValueText.setText("");
		if (test instanceof GUITest)
			expectedValueText.setEditable(false);
		else if (test instanceof GUITestInstance)
			expectedValueText.setEditable(true);
		fd = new FormData();
		fd.top = new FormAttachment(chToTestLabel, 10);
		fd.left = new FormAttachment(typeLabel, 5);
		fd.right = new FormAttachment(100, 5);
		expectedValueText.setLayoutData(fd);
		
		List<TestParameter> testParamList = test.getTestParameters();
		if (testParamList != null){
			if (testParamList.size() > 0){
				if (testParamList.get(0) != null){
					if (testParamList.get(0) instanceof TestParameter)
						chToTestText.setText(testParamList.get(0).getName());
						expectedValueText.setText(testParamList.get(0).getExpectedValue());
				}
			}
		}
		
		descText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				editor.setDirty(true);
			}
		});
		chToTestText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				editor.setDirty(true);
			}
		});
		expectedValueText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				editor.setDirty(true);
			}
		});
		
		
	}
	
	public void saveTest(){
		if (test instanceof GUITest)
			test.setDescription(descText.getText());
		else if (test instanceof GUITestInstance){
			TestParameter testParameter;
			List<TestParameter> testParamList = test.getTestParameters();
		
			if (testParamList.size() == 0){
				testParameter = tslFacade.createTestParameter(test, chToTestText.getText());
				testParameter.setExpectedValue(expectedValueText.getText());
			} else {
				testParameter = testParamList.get(0);
				testParameter.setName(chToTestText.getText());
				testParameter.setExpectedValue(expectedValueText.getText());
			}
		}
		SCProjectContainer.instance().getSCProject(test).saveTS(test);
	}

}
