package eu.redseeds.sc.editor.rsl.editors;

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
import eu.redseeds.sc.editor.rsl.editors.domain.controls.DomainElementEditorControlFactory;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.NonInvocationRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;

public class UseCaseMainView extends Composite {

	private Label nameLabel;
	private Label idLabel;
	private Text idInput;
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

	private RequirementRelationshipList relationshipList;

	private Table relatedReqTable;
	private TableViewer relatedReqTableViewer;

	private final String REQUIREMENT_COLUMN = "Requirement";
	private final String RELATIONSHIP_COLUMN = "Relationship type";
	private final String DIRECTION_COLUMN = "Direction";
	private String[] columnNames = new String[] { REQUIREMENT_COLUMN,
			RELATIONSHIP_COLUMN, DIRECTION_COLUMN };

	private UseCaseDTO useCase;
	private UseCaseEditor editor;

	private ModifyListener modListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
			if (nameInput.getText().length() == 0) {
				editor.clearBackupScenariosIfDirty();
				editor.setDirty(false);
				nameInput.setBackground(new Color(null, 255, 155, 155));
			}
			else {
				editor.backupScenariosIfNotDirty();
				nameInput.setBackground(new Color(null, 255, 255, 255));
				editor.setDirty(true);
			}
		}
	};

	public UseCaseMainView(UseCaseEditor editor, Composite parent, int style) {
		super(parent, style);

		this.editor = editor;

		createUI();
	}

	public void fillData(UseCaseDTO useCase) {

		this.useCase = useCase;

		if (useCase.getName() != null) {
			nameInput.setText(useCase.getName());
		}

		if (useCase.getUid() != null) {
			idInput.setText(useCase.getUid());
		}
		if (useCase.getDescription() != null) {
			DomainElementEditorControlFactory.convertMarkedTextToStyledText(
					descInput, useCase.getDescription(),
					(BusinessLayerFacade)((RSLUseCase)(useCase)).getGraph());

		}

		if (useCase.getSpecificationPath() != null) {
			trail.setText(useCase.getSpecificationPath());
		}


		relUCListViewer.setContentProvider(new RelatedUseCaseContentProvider());
		relUCListViewer.setLabelProvider(new RelatedUseCaseLabelProvider(useCase));
		relUCListViewer.setInput(useCase);

		/*
		 * // set related use cases relUCList.removeAll(); for (UseCaseDTO uc :
		 * useCase.getRelatedUseCases().keySet()) { relUCList.add(uc.getName()); } //
		 * set actors actorsList.removeAll(); for (ActorDTO actor :
		 * useCase.getActors()) { actorsList.add(actor.getName()); } // set
		 * mapped req mappedReqList.removeAll(); for (RequirementDTO requirement :
		 * useCase.getRequirments()) { mappedReqList.add(requirement.getName()); }
		 *  // set description descInput.setText(useCase.getDescription());
		 */

		relatedReqTableViewer.setContentProvider(new RelatedRequirementsContentProvider());
		relatedReqTableViewer.setLabelProvider(new RelatedRequirementsLabelProvider(useCase));

		relationshipList = new RequirementRelationshipList(useCase);
		relatedReqTableViewer.setInput(relationshipList);

		java.util.List<ActorDTO> actors = useCase.getActors();
		java.util.List<SystemElementDTO> sysElems= useCase.getSystemElements();

		for (int i = 0; i < actors.size(); i++){
			actorsList.add(actors.get(i).toString());
		}
		for (int i = 0; i < sysElems.size(); i++){
			actorsList.add(sysElems.get(i).toString());
		}

		editor.clearBackupScenariosIfDirty();
		editor.setDirty(false);
	}

	private void createUI() {

		FormLayout layout = new FormLayout();
		layout.marginWidth = 10;
		layout.marginHeight = 10;

		setLayout(layout);

		idLabel = new Label(this, SWT.NONE);
		idLabel.setText("ID:");

		FormData fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(0, 0);

		idLabel.setLayoutData(fd);

		idInput = new Text(this, SWT.SINGLE | SWT.BORDER);
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(idLabel, 26);
		fd.right = new FormAttachment(20, 0);

		idInput.setLayoutData(fd);

		idInput.addModifyListener(modListener);

		// NAME input

		nameLabel = new Label(this, SWT.NONE);
		fd = new FormData();
		fd.top = new FormAttachment(idInput, 10);
		fd.left = new FormAttachment(0, 0);
		nameLabel.setText("Name:");
		nameLabel.setLayoutData(fd);

		nameInput = new Text(this, SWT.SINGLE | SWT.BORDER);
		fd = new FormData();
		fd.top = new FormAttachment(idInput, 10);
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

		fd = new FormData();
		fd.top = new FormAttachment(nameInput, 10);
		fd.left = new FormAttachment(trailLabel, 15);
		fd.right = new FormAttachment(50, 0);

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
		fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(50, 0);

		descLabel.setLayoutData(fd);
		descLabel.setText("Description:");

		descInput = DomainElementEditorControlFactory.
			createDescriptionField(lists, modListener).getTextWidget();
		fd = new FormData();
		fd.top = new FormAttachment(descLabel, 10);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(50, 0);
		fd.bottom = new FormAttachment(50, 0);

		descInput.setLayoutData(fd);

		descInput.addModifyListener(modListener);

		// MAPPED REQ INPUT

		mapedReqLabel = new Label(lists, SWT.NONE);
		mapedReqLabel.setText("Related requirments:");
		fd = new FormData();
		fd.top = new FormAttachment(nameInput, 0);
		fd.left = new FormAttachment(descInput, 15);
		fd.right = new FormAttachment(100, 0);
		mapedReqLabel.setLayoutData(fd);


		relatedReqTable = new Table(lists, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL |
				SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		relatedReqTable.setLinesVisible(true);
		relatedReqTable.setHeaderVisible(true);
		//relatedReqTable.setBounds(20, 254, 400, 150);
		fd = new FormData();
		fd.top = new FormAttachment(mapedReqLabel, 10);
		fd.left = new FormAttachment(descInput, 15);
		fd.right = new FormAttachment(100, 0);
		fd.bottom = new FormAttachment(50, 0);
		relatedReqTable.setLayoutData(fd);

		TableColumn column = new TableColumn(relatedReqTable, SWT.LEFT, 0);
		column.setWidth(180);
		column.setText(REQUIREMENT_COLUMN);

		column = new TableColumn(relatedReqTable, SWT.LEFT, 1);
		column.setWidth(140);
		column.setText(RELATIONSHIP_COLUMN);

		column = new TableColumn(relatedReqTable, SWT.LEFT, 2);
		column.setWidth(60);
		column.setText(DIRECTION_COLUMN);

		relatedReqTableViewer = new TableViewer(relatedReqTable);
		relatedReqTableViewer.setUseHashlookup(true);
		relatedReqTableViewer.setColumnProperties(columnNames);

		Menu relReqMenu = new Menu(relatedReqTable);
		MenuItem addReqItem = new MenuItem(relReqMenu, SWT.PUSH);
		addReqItem.setText("Add relationship");
		final MenuItem delReqItem = new MenuItem(relReqMenu, SWT.PUSH);
		delReqItem.setText("Delete relationship");
		relatedReqTable.setMenu(relReqMenu);

		relReqMenu.addMenuListener(new MenuAdapter() {
			public void menuShown(MenuEvent e) {
				if (relatedReqTable.getSelectionCount() < 1)
					delReqItem.setEnabled(false);
				else
					delReqItem.setEnabled(true);
			}
		});

		addReqItem.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				AddRequirementRelationshipDialog addRelationDlg = new AddRequirementRelationshipDialog(useCase);
				if (addRelationDlg.open() == SWT.OK) {
					fillData(useCase);
					editor.getScProject().save();
				}
			}

		});

		delReqItem.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				Object r = ((IStructuredSelection)relatedReqTableViewer.getSelection()).getFirstElement();
				if (r != null && r instanceof NonInvocationRelationshipDTO)
					((NonInvocationRelationshipDTO) r).deleteRelationship();
				fillData(useCase);
				editor.getScProject().save();
			}

		});

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
		relUCLabel.setText("Related use cases:");

		fd = new FormData();
		fd.top = new FormAttachment(descInput, 10);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(50, 0);

		relUCLabel.setLayoutData(fd);

//		relUCList = new Table(lists, SWT.BORDER);
		relUCList = new Table(lists, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL |
				SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		relUCList.setLinesVisible(true);
		relUCList.setHeaderVisible(true);

		fd = new FormData();
		fd.top = new FormAttachment(relUCLabel, 10);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(50, 0);
		fd.bottom = new FormAttachment(100, 0);

		relUCList.setLayoutData(fd);

		column = new TableColumn(relUCList, SWT.LEFT, 0);
		column.setWidth(180);
		column.setText("Use cases");

		column = new TableColumn(relUCList, SWT.LEFT, 1);
		column.setWidth(140);
		column.setText("Invocation type");

		column = new TableColumn(relUCList, SWT.LEFT, 2);
		column.setWidth(60);
		column.setText("Direction");

		relUCListViewer = new TableViewer(relUCList);

		//relUCListViewer.setContentProvider(new RelatedUseCaseContentProvider());
		//relUCListViewer.setLabelProvider(new RelatedRequirementsLabelProvider(useCase));

		// ACTORS INPUT

		actorsLabel = new Label(lists, SWT.NONE);
		actorsLabel.setText("Actors and System Elements:");

		fd = new FormData();
		fd.top = new FormAttachment(relatedReqTable, 10);
		fd.left = new FormAttachment(relUCList, 15);
		fd.right = new FormAttachment(100, 0);

		actorsLabel.setLayoutData(fd);

		actorsList = new List(lists, SWT.MULTI | SWT.BORDER);
		fd = new FormData();
		fd.top = new FormAttachment(actorsLabel, 10);
		fd.left = new FormAttachment(relUCList, 15);
		fd.right = new FormAttachment(100, 0);
		fd.bottom = new FormAttachment(100, 0);

		actorsList.setLayoutData(fd);

	}

	class RelatedRequirementsContentProvider implements IStructuredContentProvider, IRelatedRequirementsViewer {

		@Override
		public void addRelatedRequirement(RequirementDTO req) {
			relatedReqTableViewer.add(req);
		}

		@Override
		public void removeRelatedRequirement(RequirementDTO req) {
			relatedReqTableViewer.remove(req);
		}

		@Override
		public void updateRelatedRequirement(RequirementDTO req) {
			relatedReqTableViewer.update(req, null);
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return relationshipList.getRelationships().toArray();
		}

		@Override
		public void dispose() {
			relationshipList.removeChangeListener(this);
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			if (newInput != null)
				((RequirementRelationshipList) newInput).addChangeListener(this);
			if (oldInput != null)
				((RequirementRelationshipList) oldInput).removeChangeListener(this);
		}

	}


	public boolean save() {
		if (!useCase.isNameUnique(nameInput.getText())
				&& !useCase.getName().equalsIgnoreCase(nameInput.getText())) {
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("'" + nameInput.getText() + "' is not a unique name");
			mb.setText("Name");
			mb.open();
			return false;
		} else {
			useCase.setName(nameInput.getText());
			useCase.setUid(idInput.getText());
			useCase.setDescription(DomainElementEditorControlFactory.convertStyledTextToMarkedText(descInput));
			return true;
		}
	}

	public UseCaseEditor getEditor() {
		return editor;
	}

	public UseCaseDTO getUseCase() {
		return useCase;
	}

}
