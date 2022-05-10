package eu.redseeds.sc.editor.rsl.editors;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.DomainElementEditorControlFactory;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.NonInvocationRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;

public class RequirementEditorControl extends Composite {

	//private Composite parent;

	private RequirementDTO requirement;

	private Table relatedReqTable;
	private TableViewer relatedReqTableViewer;
	private StyledText descriptionText;
	private Text pathText;
	private Text nameText;
	private Text idText;

	private final String REQUIREMENT_COLUMN = "Requirement";
	private final String RELATIONSHIP_COLUMN = "Relationship type";
	private final String DIRECTION_COLUMN = "Direction";
	private String[] columnNames = new String[] { REQUIREMENT_COLUMN,
			RELATIONSHIP_COLUMN, DIRECTION_COLUMN };

	private RequirementRelationshipList relationshipList;
//	private Composite parent;
	private RequirementEditor editor;

	private ModifyListener modListener = new ModifyListener(){
		@Override
		public void modifyText(ModifyEvent e) {
			if (nameText.getText().length() == 0) {
				editor.setDirty(false);
				nameText.setBackground(new Color(null, 255, 155, 155));
			}
			else {
				nameText.setBackground(new Color(null, 255, 255, 255));
				editor.setDirty(true);
			}
		}
	};

	public RequirementEditorControl(RequirementEditor editor, Composite parent) {
		super(parent, SWT.NONE);
//		this.parent = parent;
		this.editor = editor;
		createContent();
	}

//	public void setRequirement(RequirementDTO req) {
//		this.requirement = req;
//		createContent();
//	}

	public void fillWithData(RequirementDTO req) {

		this.requirement = req;

		if (requirement.getRequirementId() != null)
			idText.setText(requirement.getRequirementId());

		if (requirement.getName() != null)
			nameText.setText(requirement.getName());

		if (requirement.getDescription() != null) {
			DomainElementEditorControlFactory.convertMarkedTextToStyledText(
					descriptionText, requirement.getDescription(),
					(BusinessLayerFacade)((Requirement)(requirement)).getGraph());
		}

		if (requirement.getSpecificationPath() != null)
			pathText.setText(requirement.getSpecificationPath());


		relatedReqTableViewer.setContentProvider(new RelatedRequirementsContentProvider());
		relatedReqTableViewer.setLabelProvider(new RelatedRequirementsLabelProvider(requirement));

		// Set the input for the RelatedRequirementsTableViewer

		relationshipList = new RequirementRelationshipList(requirement);
		relatedReqTableViewer.setInput(relationshipList);

		editor.setDirty(false);
	}


	private void createContent() {

		// Set the layout of the control

		//setLayout(new RowLayout(SWT.VERTICAL));


		// Create controls

		final Label idLabel = new Label(this, SWT.NONE);
		idLabel.setBounds(20, 20, 15, 13);
		idLabel.setText("ID:");

		idText = new Text(this, SWT.BORDER);
		idText.setBounds(70, 17, 120, 18);
		idText.addModifyListener(modListener);

		final Label nameLabel = new Label(this, SWT.NONE);
		nameLabel.setBounds(20, 45, 31, 13);
		nameLabel.setText("Name:");

		nameText = new Text(this, SWT.BORDER);
		nameText.setBounds(70, 42, 350, 18);
		nameText.addModifyListener(modListener);

		final Label pathLabel = new Label(this, SWT.NONE);
		pathLabel.setBounds(20, 70, 26, 13);
		pathLabel.setText("Path:");

		pathText = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		pathText.setBounds(70, 67, 351, 18);

		final Label descriptionLabel = new Label(this, SWT.NONE);
		descriptionLabel.setText("Description:");
		descriptionLabel.setBounds(20, 101, 63, 13);

		descriptionText = DomainElementEditorControlFactory.
			createDescriptionField(this, modListener).getTextWidget();
		descriptionText.setBounds(20, 120, 400, 80);

		// Create related requirements table

		final Label relatedRequirementsLabel = new Label(this, SWT.NONE);
		relatedRequirementsLabel.setText("Related requirements:");
		relatedRequirementsLabel.setBounds(20, 206, 107, 13);

		final Button addRelationshipButton = new Button(this, SWT.NONE);
		addRelationshipButton.setText("Add");
		addRelationshipButton.setBounds(425, 226, 44, 23);

		addRelationshipButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddRequirementRelationshipDialog addRelationDlg = new AddRequirementRelationshipDialog(requirement);
				if (addRelationDlg.open() == SWT.OK) {
					fillWithData(requirement);
					editor.getScProject().save();
				}
			}
		});

		final Button deleteRelationshipButton = new Button(this, SWT.NONE);
		deleteRelationshipButton.setText("Delete");
		deleteRelationshipButton.setBounds(425, 255, 44, 23);

		deleteRelationshipButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Object r = ((IStructuredSelection)relatedReqTableViewer.getSelection()).getFirstElement();
				if (r != null && r instanceof NonInvocationRelationshipDTO)
					((NonInvocationRelationshipDTO) r).deleteRelationship();
				fillWithData(requirement);
				editor.getScProject().save();
			}
		});

		createRelatedRequirementsTable();

		createRelatedRequirementsTableViewer();

		// Create attributes table

		// ...

		//fillWithData();
	}

	private void createRelatedRequirementsTable(){

		// Create table
		relatedReqTable = new Table(this, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL |
				SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		relatedReqTable.setLinesVisible(true);
		relatedReqTable.setHeaderVisible(true);
		relatedReqTable.setBounds(20, 225, 400, 150);

		// Create columns
		TableColumn column = new TableColumn(relatedReqTable, SWT.LEFT, 0);
		column.setWidth(180);
		column.setText(REQUIREMENT_COLUMN);

		column = new TableColumn(relatedReqTable, SWT.LEFT, 1);
		column.setWidth(140);
		column.setText(RELATIONSHIP_COLUMN);

		column = new TableColumn(relatedReqTable, SWT.LEFT, 2);
		column.setWidth(60);
		column.setText(DIRECTION_COLUMN);

	}

	private void createRelatedRequirementsTableViewer(){
		// Create table viewer
		relatedReqTableViewer = new TableViewer(relatedReqTable);
		relatedReqTableViewer.setUseHashlookup(true);
		relatedReqTableViewer.setColumnProperties(columnNames);
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

	/**
	 * @return true if save was successful
	 */
	public boolean save() {
		if(!requirement.isNameUnique(nameText.getText())
		&& !requirement.getName().equalsIgnoreCase(nameText.getText()) ) {
			MessageBox mb = new MessageBox(SCProjectHelper.getShell(),
					SWT.ICON_INFORMATION | SWT.OK );
			mb.setMessage("'"+nameText.getText()+"' is not a unique name");
			mb.setText("Name");
			mb.open();
			return false;
		}
		else {
			requirement.setRequirementId(idText.getText());
			requirement.setName(nameText.getText());
			requirement.setDescription(DomainElementEditorControlFactory.convertStyledTextToMarkedText(descriptionText));
			return true;
		}
	}

	public RequirementEditor getEditor() {
		return editor;
	}

	public RequirementDTO getRequirement() {
		return requirement;
	}
}
