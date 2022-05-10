package eu.redset.tsl.editor.editors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.common.SCProjectHelper;


import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.TestSpecification;
import eu.redset.emf.model.tsl.util.TSLModelHelper;

public class TestScenarioMainView extends Composite {

	
	private Label ucNameLabel;
	private Text ucNameInput;
	private Label ucTrailLabel;
	private Text ucTrail;
	
	private Label nameLabel;
	private Text nameInput;
	private Label trailLabel;
	private Text trail;
	private Label descLabel;
	private StyledText descInput;
	private Label mapedReqLabel;
	//private List mappedReqList;
	private Label relUCLabel;
	private Table relUCList;
	private TableViewer relUCListViewer;
	private Label actorsLabel;
	private List actorsList;
	private Composite lists;

	
	private Table relatedReqTable;
	private TableViewer relatedReqTableViewer;

	private final String REQUIREMENT_COLUMN = "Requirement";
	private final String RELATIONSHIP_COLUMN = "Relationship type";
	private final String DIRECTION_COLUMN = "Direction";
	private String[] columnNames = new String[] { REQUIREMENT_COLUMN,
			RELATIONSHIP_COLUMN, DIRECTION_COLUMN };

	private TestScenario testScenario;
	private TestScenarioEditor testScenarioEditor;

	private ModifyListener modListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
			testScenarioEditor.setDirty(true);
		}
	};

	public TestScenarioMainView(TestScenarioEditor editor, Composite parent, int style) {
		super(parent, style);

		this.testScenarioEditor = editor;

		createUI();
	}

	public void fillData(TestScenario testScenario) {

		this.testScenario = testScenario;

		if (testScenario.getName() != null) {
			
		}


		/*
		 * // set related use cases relUCList.removeAll(); for (UseCaseDTO uc :
		 * useCase.getRelatedUseCases().keySet()) { relUCList.add(uc.getName()); } //
		 * set actors actorsList.removeAll(); for (ActorDTO actor :
		 * useCase.getActors()) { actorsList.add(actor.getName()); } // set
		 * mapped req mappedReqList.removeAll(); for (RequirementDTO requirement :
		 * useCase.getRequirments()) { mappedReqList.add(requirement.getName()); }
		 *  // set description descInput.setText(useCase.getDescription());
		 */
		nameInput.setText(this.testScenario.getName());
		trail.setText(TSLModelHelper.instance().getFullPath(testScenario, false));
		if (testScenario.getDescription() != null)
			this.descInput.setText(this.testScenario.getDescription());
		else this.descInput.setText("");
		this.layout();
		testScenarioEditor.setDirty(false);
	}

	private void createUI() {
		FormLayout layout = new FormLayout();
		layout.marginWidth = 10;
		layout.marginHeight = 10;

		setLayout(layout);
		FormData fd = new FormData();

		// NAME input

		nameLabel = new Label(this, SWT.NONE);
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(0, 0);
		nameLabel.setText("Name:");
		nameLabel.setLayoutData(fd);

		nameInput = new Text(this, SWT.SINGLE | SWT.BORDER);
		//nameInput.setText(testScenario.getName());
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(nameLabel, 10);
		fd.right = new FormAttachment(100, 0);

		nameInput.setLayoutData(fd);

		nameInput.addModifyListener(modListener);

		// TRAIL

		trailLabel = new Label(this, SWT.NONE);
		trailLabel.setText("Path:");
		fd = new FormData();
		fd.top = new FormAttachment(nameInput, 10);
		fd.left = new FormAttachment(0, 0);
		trailLabel.setLayoutData(fd);


		trail = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		//trail.setText(getFullPath(testScenario));
		fd = new FormData();
		fd.top = new FormAttachment(nameInput, 10);
		fd.left = new FormAttachment(nameLabel, 10);
		fd.right = new FormAttachment(100, 0);

		trail.setLayoutData(fd);

		
		
		
		
		lists = new Composite(this, SWT.NONE);
		lists.setLayout(new FormLayout());

		fd = new FormData();
		fd.top = new FormAttachment(trail, 20);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(100, 0);
		fd.bottom = new FormAttachment(100, 0);

		lists.setLayoutData(fd);

		// DESC INPUT

		descLabel = new Label(lists, SWT.NONE);
		descLabel.setText("Description:");
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(100, 0);
		descLabel.setLayoutData(fd);
		
		
		descInput = new StyledText(lists, SWT.SINGLE | SWT.BORDER);
		fd = new FormData();
		fd.top = new FormAttachment(descLabel, 10);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(100, 0);
		fd.bottom = new FormAttachment(100, 0);

		descInput.setLayoutData(fd);

		descInput.addModifyListener(modListener);		
	}

	public boolean save() {
		testScenario.setName(nameInput.getText());
		//useCaseTest.setUid(idInput.getText());
		testScenario.setDescription(descInput.getText());
		return true;
}

	public TestScenarioEditor getTestScenarioEditor() {
		return testScenarioEditor;
	}

	public TestScenario getTestScenario() {
		return testScenario;
	}

}
