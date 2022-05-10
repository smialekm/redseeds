package eu.redset.tsl.editor.editors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;



public class UseCaseTestScenarioView extends Composite {

	private UseCaseTestEditor editor;
	private UseCaseTestScenario scenario;	
	private UseCaseTest uct;
	private Text nameBox;	
	private ScrolledComposite sc;
	private Composite scContent;
	private ScenarioBasicInfoPanel scenarioBasicInfoPanel;
	private ScenarioSentencesPanel scenarioSentencesPanel;
	

	public UseCaseTestScenarioView(UseCaseTestEditor editor,
			UseCaseTestScenario scenario, Composite parent, int style) {
		super(parent, style);
		this.editor = editor;
		this.scenario = scenario;
		uct = editor.getUseCaseTest();
		
		//Create basic scrolled composite
		setLayout(new FillLayout(SWT.HORIZONTAL));
	    sc = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    sc.setExpandHorizontal(true);
	    sc.setExpandVertical(true);
	    
	    
	    //Create composite for scrolled composite content
	    scContent = new Composite(sc, SWT.NONE);
	    GridLayout gridLayout = new GridLayout(1, true);
		gridLayout.verticalSpacing = 10;
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		scContent.setLayout(gridLayout);
		scContent.setLayoutData(gd);

	    //Create scenario basic info panel
		scenarioBasicInfoPanel = new ScenarioBasicInfoPanel(scContent, SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		scenarioBasicInfoPanel.setUct(uct);
		scenarioBasicInfoPanel.setScenario(scenario);
		scenarioBasicInfoPanel.createContent();
		scenarioBasicInfoPanel.setLayoutData(gd);

		//Create scenario sentences panel
		scenarioSentencesPanel = new ScenarioSentencesPanel(scContent, SWT.BORDER);
		scenarioSentencesPanel.setUseCaseTestScenario(scenario);
		scenarioSentencesPanel.createContent();
		
		sc.setContent(scContent);
		//sc.setContent(scenarioSentencesPanel);
		scContent.setSize(scContent.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	    sc.setMinSize(scContent.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	    this.setRedraw(true);
		
		//---
		//initSentenes();
		editor.setDirty(false);
		//refreshTab();
	}

	public void refreshTab() {
		//for (Control c : comp.getChildren())
		//initSentenceControls();
		// this.setSize(this.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		// this.setBounds(this.getParent().getBounds());
		// this.layout();
		scenarioSentencesPanel.dispose();
		
		//Create scenario sentences panel
		scenarioSentencesPanel = new ScenarioSentencesPanel(scContent, SWT.BORDER);
		scenarioSentencesPanel.setUseCaseTestScenario(scenario);
		scenarioSentencesPanel.createContent();
		
		sc.setContent(scContent);
		//sc.setContent(scenarioSentencesPanel);
		scContent.setSize(scContent.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	    sc.setMinSize(scContent.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		sc.layout();
		this.redraw();
		//this.getVerticalBar().setVisible(true);
	}
		
	public UseCaseTestEditor getEditor() {
		return editor;
	}

	public void save() {
		scenario.setName(nameBox.getText());
	}

	public UseCaseTestScenario getScenario() {
		return scenario;
	}

}
