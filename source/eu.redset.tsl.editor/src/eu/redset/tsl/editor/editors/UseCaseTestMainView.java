package eu.redset.tsl.editor.editors;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;


import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.util.TSLModelHelper;

public class UseCaseTestMainView extends Composite {

	
	private String ucName = "";
	private String ucTrailName = "";
	private String uctName = "";
	private String uctTrailName = "";
	
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

	private Label relUCLabel;
	private Table relUCList;
	private TableViewer relUCListViewer;
	private Composite lists;	
	private TableColumn column;


	private UseCaseTest useCaseTest;
	private UseCaseTestEditor testEditor;

	private ModifyListener modListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
			
		}
	};

	public UseCaseTestMainView(UseCaseTestEditor editor, Composite parent, int style) {
		super(parent, style);

		this.testEditor = editor;

		createUI();
	}

	public void fillData(UseCaseTest useCaseTest) {

		this.useCaseTest = useCaseTest;
		if (useCaseTest.getName() != null) {
			
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

		nameInput.setText(this.useCaseTest.getName());
		trail.setText(TSLModelHelper.instance().getFullPath(useCaseTest, false));
		ucNameInput.setText(this.useCaseTest.getUcName());
		ucTrail.setText(this.useCaseTest.getUcTrail());
		relUCListViewer.setContentProvider(new RelatedUseCaseTestContentProvider());
		relUCListViewer.setInput(useCaseTest);
		relUCListViewer.setLabelProvider(new RelatedUseCaseTestLabelProvider());
		relUCListViewer.setInput(useCaseTest);
		relUCListViewer.refresh();
		//relUCListViewer.re;
		testEditor.setDirty(false);
		this.layout();
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

		nameInput = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		nameInput.setText(uctName);
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(nameLabel, 10);
		fd.right = new FormAttachment(50, 0);

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
		trail.setText(uctTrailName);
		fd = new FormData();
		fd.top = new FormAttachment(nameInput, 10);
		fd.left = new FormAttachment(nameLabel, 10);
		fd.right = new FormAttachment(50, 0);

		trail.setLayoutData(fd);

		// Use Case NAME input

		ucNameLabel = new Label(this, SWT.NONE);
		ucNameLabel.setText("Use Case Name:");
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(nameInput, 15);
		ucNameLabel.setText("Use Case Name:");
		ucNameLabel.setLayoutData(fd);

		ucNameInput = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		ucNameInput.setText(ucName);
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(ucNameLabel, 10);
		fd.right = new FormAttachment(100, 0);

		ucNameInput.setLayoutData(fd);

		ucNameInput.addModifyListener(modListener);

		// TRAIL

		ucTrailLabel = new Label(this, SWT.NONE);
		ucTrailLabel.setText("Use Case Path:");
		fd = new FormData();
		fd.top = new FormAttachment(ucNameInput, 10);
		fd.left = new FormAttachment(trail, 15);
		ucTrailLabel.setLayoutData(fd);


		ucTrail = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		ucTrail.setText(ucTrailName);
		fd = new FormData();
		fd.top = new FormAttachment(ucNameInput, 10);
		fd.left = new FormAttachment(ucNameLabel, 10);
		fd.right = new FormAttachment(100, 0);

		ucTrail.setLayoutData(fd);
					
		lists = new Composite(this, SWT.BORDER);
		lists.setLayout(new FormLayout());

		fd = new FormData();
		fd.top = new FormAttachment(ucTrail, 20);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(100, 0);
		fd.bottom = new FormAttachment(100, 0);

		lists.setLayoutData(fd);

		// DESC INPUT

		descLabel = new Label(lists, SWT.NONE);
		descLabel.setText("Description:");
		fd = new FormData();
		fd.top = new FormAttachment(0, 5);
		fd.left = new FormAttachment(0, 5);
		fd.right = new FormAttachment(50, 0);
		descLabel.setLayoutData(fd);
		
		
		descInput = new StyledText(lists, SWT.SINGLE | SWT.BORDER);
		fd = new FormData();
		fd.top = new FormAttachment(descLabel, 10);
		fd.left = new FormAttachment(0, 5);
		fd.right = new FormAttachment(50, 0);
		fd.bottom = new FormAttachment(100, -5);

		descInput.setLayoutData(fd);

		descInput.addModifyListener(modListener);
		
		// MAPPED REQ INPUT

		




//		mapedReqLabel = new Label(lists, SWT.NONE);
//		mapedReqLabel.setText("Mapped Requirments:");
//
//		fd = new FormData();
//		fd.top = new FormAttachment(nameInput, 0);
//		fd.left = new FormAttachment(descInput, 15);
//		fd.right = new FormAttachment(100, 0);
//
//		mapedReqLabel.setLayoutData(fd);
//
//		mappedReqList = new List(lists, SWT.MULTI);
//		fd = new FormData();
//		fd.top = new FormAttachment(mapedReqLabel, 10);
//		fd.left = new FormAttachment(descInput, 15);
//		fd.right = new FormAttachment(100, 0);
//		fd.bottom = new FormAttachment(50, 0);
//
//		for (int i = 0; i < 10; i++)
//			mappedReqList.add("Requirment" + i);
//
//		mappedReqList.setLayoutData(fd);

		// REL UC INPUT

		relUCLabel = new Label(lists, SWT.NONE);
		relUCLabel.setText("Related use case tests:");

		fd = new FormData();
		fd.top = new FormAttachment(0, 5);
		fd.left = new FormAttachment(descInput, 10);
		fd.right = new FormAttachment(100, -5);

		relUCLabel.setLayoutData(fd);

//		relUCList = new Table(lists, SWT.BORDER);
		relUCList = new Table(lists, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL |
				SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		relUCList.setLinesVisible(true);
		relUCList.setHeaderVisible(true);

		fd = new FormData();
		fd.top = new FormAttachment(relUCLabel, 10);
		fd.left = new FormAttachment(descInput, 10);
		fd.right = new FormAttachment(100, -5);
		fd.bottom = new FormAttachment(100, -5);

		relUCList.setLayoutData(fd);

		column = new TableColumn(relUCList, SWT.LEFT, 0);
		column.setWidth(180);
		column.setText("Use cases test");

		column = new TableColumn(relUCList, SWT.LEFT, 1);
		column.setWidth(140);
		column.setText("Invocation type");

		column = new TableColumn(relUCList, SWT.LEFT, 2);
		column.setWidth(60);
		column.setText("Direction");

		relUCListViewer = new TableViewer(relUCList);

		
		//relUCListViewer.setContentProvider(new RelatedUseCaseTestContentProvider());
		//relUCListViewer.setLabelProvider(new RelatedUseCaseTestLabelProvider(useCaseTest));
		//relUCListViewer.setInput(useCaseTest);
		//relUCListViewer.refresh();
		
		// ACTORS INPUT		
	}



	public UseCaseTestEditor getTestEditor() {
		return testEditor;
	}

	public UseCaseTest getUseCaseTest() {
		return useCaseTest;
	}
	
	public boolean save() {
			useCaseTest.setName(nameInput.getText());
			//useCaseTest.setUid(idInput.getText());
			useCaseTest.setDescription(descInput.getText());
			return true;
	}

}
