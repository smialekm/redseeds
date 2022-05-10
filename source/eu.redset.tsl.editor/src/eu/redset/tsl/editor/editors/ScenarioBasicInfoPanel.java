package eu.redset.tsl.editor.editors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.util.TSLModelHelper;

public class ScenarioBasicInfoPanel extends Composite{

	private UseCaseTest uct;
	private UseCaseTestScenario scenario;
	
	private Label ucNameLabel;
	private Text ucNameInput;
	private Label ucTrailLabel;
	private Text ucTrail;
	
	private Label nameLabel;
	private Text nameInput;
	private Label trailLabel;
	private Text trail;
	
	private RelatedTestsPanel scenRelatedTests;
	
	public ScenarioBasicInfoPanel(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	public void createContent(){
		setLayout(new FormLayout());
		FormData fd = new FormData();
		nameLabel = new Label(this, SWT.NONE);
		fd = new FormData();
		fd.top = new FormAttachment(0, 10);
		fd.left = new FormAttachment(0, 10);
		nameLabel.setText("Name:");
		nameLabel.setLayoutData(fd);
		


		nameInput = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		nameInput.setText(this.scenario.getName());
		fd = new FormData();
		fd.top = new FormAttachment(0, 10);
		fd.left = new FormAttachment(nameLabel, 10);
		fd.right = new FormAttachment(50, 0);

		nameInput.setLayoutData(fd);

		//nameInput.addModifyListener(modListener);

		// TRAIL

		trailLabel = new Label(this, SWT.NONE);
		trailLabel.setText("Path:");
		fd = new FormData();
		fd.top = new FormAttachment(nameInput, 10);
		fd.left = new FormAttachment(0, 10);
		trailLabel.setLayoutData(fd);


		trail = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		trail.setText(TSLModelHelper.instance().getFullPath(this.scenario.eContainer(), false));
		fd = new FormData();
		fd.top = new FormAttachment(nameInput, 10);
		fd.left = new FormAttachment(nameLabel, 10);
		fd.right = new FormAttachment(50, 0);

		trail.setLayoutData(fd);

		// Use Case NAME input

		ucNameLabel = new Label(this, SWT.NONE);
		ucNameLabel.setText("Use Case Name:");
		fd = new FormData();
		fd.top = new FormAttachment(0, 10);
		fd.left = new FormAttachment(nameInput, 10);
		ucNameLabel.setLayoutData(fd);

		ucNameInput = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		ucNameInput.setText(this.uct.getUcName());
		fd = new FormData();
		fd.top = new FormAttachment(0, 10);
		fd.left = new FormAttachment(ucNameLabel, 10);
		fd.right = new FormAttachment(100, -5);

		ucNameInput.setLayoutData(fd);

		//ucNameInput.addModifyListener(modListener);

		// TRAIL

		ucTrailLabel = new Label(this, SWT.NONE);
		ucTrailLabel.setText("Use Case Path:");
		fd = new FormData();
		fd.top = new FormAttachment(ucNameInput, 10);
		fd.left = new FormAttachment(trail, 10);
		ucTrailLabel.setLayoutData(fd);


		ucTrail = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		ucTrail.setText(this.uct.getUcTrail());
		fd = new FormData();
		fd.top = new FormAttachment(ucNameInput, 10);
		fd.left = new FormAttachment(ucNameLabel, 10);
		fd.right = new FormAttachment(100, -5);

		ucTrail.setLayoutData(fd);
		
		
		scenRelatedTests = new RelatedTestsPanel(this, scenario, SWT.NONE);
		fd = new FormData();
		fd.top = new FormAttachment(ucTrailLabel, 10);
		fd.left = new FormAttachment(0, 5);
		scenRelatedTests.setLayoutData(fd);
	}
	
	public UseCaseTest getUct() {
		return uct;
	}

	public void setUct(UseCaseTest uct) {
		this.uct = uct;
	}

	public UseCaseTestScenario getScenario() {
		return scenario;
	}

	public void setScenario(UseCaseTestScenario scenario) {
		this.scenario = scenario;
	}
	
}
