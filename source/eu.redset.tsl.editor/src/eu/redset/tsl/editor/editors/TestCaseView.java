package eu.redset.tsl.editor.editors;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import org.eclipse.swt.graphics.Color;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;


import eu.redset.emf.model.tsl.DirectObject;
import eu.redset.emf.model.tsl.IndirectObject;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestCaseSentence;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;

import eu.redset.emf.model.tsl.util.TSLModelHelper;



public class TestCaseView extends Composite {

	private TestScenarioEditor editor;
	private TestCase testCase;	
	private TestScenario testScenario;
	private Text nameBox;	
	private ScrolledComposite sc;
	private Composite scContent;
	private TCScenarioBasicInfoPanel scenarioBasicInfoPanel;
	private TCScenarioSentencesListPanel scenarioSentencesPanel;
	

	public TestCaseView(TestScenarioEditor editor,
			TestCase testCase, Composite parent, int style) {
		super(parent, style);
		this.editor = editor;
		this.testCase = testCase;
		testScenario = editor.getTestScenario();
		
		//Create basic scrolled composite
		setLayout(new FillLayout(SWT.HORIZONTAL));
	    sc = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    sc.setExpandHorizontal(true);
	    sc.setExpandVertical(true);
	    
	    
	    //Create composite for scrolled composite content
	    scContent = new Composite(sc, SWT.NONE);
	    scContent.setBackground(new Color(null, 0, 255, 50));
	    GridLayout gridLayout = new GridLayout(1, true);
		gridLayout.verticalSpacing = 10;
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		scContent.setLayout(gridLayout);
		scContent.setLayoutData(gd);

	    //Create scenario basic info panel
		scenarioBasicInfoPanel = new TCScenarioBasicInfoPanel(scContent, SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		scenarioBasicInfoPanel.setTestScenario(testScenario);
		scenarioBasicInfoPanel.setTestCase(testCase);
		scenarioBasicInfoPanel.createContent();
		scenarioBasicInfoPanel.setLayoutData(gd);

		//Create scenario sentences panel
		scenarioSentencesPanel = new TCScenarioSentencesListPanel(scContent, SWT.BORDER);
		scenarioSentencesPanel.setTestCase(testCase);
		scenarioSentencesPanel.createContent();
		
		sc.setContent(scContent);
		//sc.setContent(scenarioSentencesPanel);
		scContent.setSize(scContent.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	    sc.setMinSize(scContent.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	    this.setRedraw(true);
		
		//---
		//initSentenes();
		editor.setDirty(false);
		refreshTab();
	}

	public void refreshTab() {
		//for (Control c : comp.getChildren())
		//initSentenceControls();
		// this.setSize(this.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		// this.setBounds(this.getParent().getBounds());
		// this.layout();
		
		this.layout();
		this.redraw();
		//this.getVerticalBar().setVisible(true);
	}
		


	public void save() {
		testCase.setName(nameBox.getText());
	}

	public TestScenarioEditor getEditor() {
		return editor;
	}

	public void setEditor(TestScenarioEditor editor) {
		this.editor = editor;
	}

	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public TestScenario getTestScenario() {
		return testScenario;
	}

	public void setTestScenario(TestScenario testScenario) {
		this.testScenario = testScenario;
	}



}
