package eu.redset.tsl.editor.editors;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestCaseSentence;

public class TCScenarioSentencesListPanel extends Composite{

	private Composite sentencePanel;
	private Composite preconditionPanel;
	private Composite postconditionPanel;
	private Label postconditionLabel;
	private Label preconditionLabel;
	
	private TestCase testCase;
	
	FormData fd;
	
	public TCScenarioSentencesListPanel(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	public void createContent(){
				
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.verticalSpacing = 10;
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		setLayout(gridLayout);
		setLayoutData(gd);
		
		preconditionPanel = new Composite(this, SWT.BORDER_SOLID);
		preconditionPanel.setBackground(new Color(null, 255, 255, 150));
		preconditionPanel.setLayout(new FormLayout());
		fd = new FormData();
		fd.top = new FormAttachment(0,0);
		fd.left = new FormAttachment(0, 0);
		preconditionPanel.setLayoutData(gd);
		
		preconditionLabel = new Label(preconditionPanel, SWT.None);
		preconditionLabel.setBackground(new Color(null, 255, 255, 150));
		preconditionLabel.setText("precondition: "+testCase.getPrecondition().getName());
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(30, 0);
		preconditionLabel.setLayoutData(fd);
		
		List sentences = this.testCase.getSentences();
		for (int i=0; i<sentences.size(); i++){
			sentencePanel = new TCScenarioSentencePanel(((TestCaseSentence)sentences.get(i)).getScenarioSentence(), i, this, SWT.NONE, this);
			gd = new GridData();
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessHorizontalSpace = true;
			gd.verticalAlignment = SWT.FILL;
			gd.grabExcessVerticalSpace = true;
			sentencePanel.setLayoutData(gd);
		}
		
		postconditionPanel = new Composite(this, SWT.BORDER_SOLID);
		preconditionPanel.setBackground(new Color(null, 255, 255, 150));
		postconditionPanel.setLayout(new FormLayout());
		//fd = new FormData();
		//fd.top = new FormAttachment(0,10);
		//fd.left = new FormAttachment(0, 10);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		postconditionPanel.setLayoutData(gd);
		
		postconditionLabel = new Label(postconditionPanel, SWT.None);
		postconditionLabel.setBackground(new Color(null, 255, 255, 150));
		postconditionLabel.setText("postcondition: "+testCase.getPostcondition().getName());
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(100, 0);
		postconditionLabel.setLayoutData(fd);
	}

	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}


}
