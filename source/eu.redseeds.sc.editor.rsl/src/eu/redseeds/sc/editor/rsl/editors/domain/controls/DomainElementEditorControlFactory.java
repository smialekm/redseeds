package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.common.Constants;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.RequirementEditorControl;
import eu.redseeds.sc.editor.rsl.editors.UseCaseMainView;
import eu.redseeds.sc.editor.rsl.editors.domain.ActorEditor;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditor;
import eu.redseeds.sc.editor.rsl.editors.domain.SystemElementEditor;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.ActionTypeCellModifier;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.DomainStatementsContentProvider;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.DomainStatementsLabelProvider;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.NotionAttributeParentsComparator;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.NotionAttributeParentsContentProvider;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.NotionAttributeParentsLabelProvider;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.NotionAttributesComparator;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.NotionAttributesContentProvider;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.NotionAttributesLabelProvider;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.NotionSpecialisationsCellModifier;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.NotionSpecialisationsContentProvider;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.NotionSpecialisationsLabelProvider;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.NotionSpecialisationsList;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.RelatedDomainElementsCellModifier;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.RelatedDomainElementsContentProvider;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.RelatedDomainElementsLabelProvider;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.views.RelatedDomainElementsList;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.ActionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.AttributeDataTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.PrimitiveDataTypeDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.rsl.rsldomainelements.actors.Actor;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElement;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;
import eu.remics.recovery.model.preferences.MConfiguration;

public class DomainElementEditorControlFactory {
	
	//order in which controls are drawn:
//	private static int NAME_LABEL_INDEX = 0;
	private static int NAME_TEXT_INDEX = 1;
	private static int PATH_TEXT_INDEX = 3;
	private static int DESCRIPTION_TEXT_INDEX = 5;
	public static final int DELETE_RELATIONSHIP_BUTTON_INDEX = 7;
	public static final int RELATIONSHIPS_TABLE_INDEX = 8;
	public static final int DELETE_NOTION_SPECIALISATION_BUTTON_INDEX = 10;
	public static final int NOTIONS_SPECIALISATIONS_TABLE_INDEX = 11;
	public static final int ADD_DOMAIN_STATEMENT_BUTTON_INDEX = 13;
	public static final int DELETE_DOMAIN_STATEMENT_BUTTON_INDEX = 14;
	public static final int DOMAIN_STATEMENTS_TABLE_INDEX = 15;
	public static final int NOTION_TYPE_COMBO_INDEX = 17;
	public static final int NOTION_ATTRIBUTES_BUTTON_INDEX = 19;
	public static final int NOTION_ATTRIBUTES_TABLE_INDEX = 20;
	public static final int NOTION_KIND_COMBO_INDEX = 22;
	public static final int NOTION_ATTRIBUTE_PARENTS_BUTTON_INDEX = 24;
	public static final int NOTION_ATTRIBUTE_PARENTS_TABLE_INDEX = 25;
	
	private final static String DOMAIN_ELEMENT_COLUMN = "Domain Element";
//	private final static String RELATIONSHIP_COLUMN = "Relationship type";
	private final static String MULTIPLICITY_COLUMN = "Multiplicity (This Element)";
	private final static String MULTIPLICITY_OTHER_COLUMN = "Multiplicity (Other Element)";
	private final static String IS_DIRECTED_COLUMN = "Directed";
	private final static String NOTION_COLUMN = "Notion";
	private final static String ROLE_COLUMN = "Role";
	private final static String DS_NAME_COLUMN = "Name";
	private final static String DS_ACTION_TYPE_COLUMN = "Action Type";
	private final static String ATTRIBUTE_NAME_COLUMN = "Attribute";
	private final static String ATTRIBUTE_TYPE_COLUMN = "Type";
	private final static String ATTRIBUTE_PARENT_NAME_COLUMN = "Parent";
	private final static String ATTRIBUTE_PARENT_TYPE_COLUMN = "Type";
	public final static String[] columnNames = new String[] { DOMAIN_ELEMENT_COLUMN,
			MULTIPLICITY_COLUMN, MULTIPLICITY_OTHER_COLUMN, IS_DIRECTED_COLUMN };
	public final static String[] columnNamesNotions 
			= new String[] { NOTION_COLUMN, ROLE_COLUMN };
	public final static String[] columnNamesStatements 
			= new String[] { DS_NAME_COLUMN, DS_ACTION_TYPE_COLUMN };
	public final static String[] columnNamesAttributes 
			= new String[] { ATTRIBUTE_NAME_COLUMN, ATTRIBUTE_TYPE_COLUMN };
	public final static String[] columnNamesAttributeParents 
			= new String[] { ATTRIBUTE_PARENT_NAME_COLUMN, ATTRIBUTE_PARENT_TYPE_COLUMN };
	
	public static int ACTOR_CONTROL_WIDTH = 620;
	public static int ACTOR_CONTROL_HEIGHT = 320;
	
	public static int NOTION_CONTROL_WIDTH = 620;
	public static int NOTION_CONTROL_HEIGHT = 811;
	
	public static int SYSTEM_ELEMENT_CONTROL_WIDTH = 620;
	public static int SYSTEM_ELEMENT_CONTROL_HEIGHT = 320;
	
	private static Rectangle relLabelRect = new Rectangle(20, 189, 157, 13);
	private static Rectangle relButtonRect = new Rectangle(566, 206, 50, 23);
	private static Rectangle relTableRect = new Rectangle(20, 205, 540, 100);
	
	private static Rectangle relLabelRectNotions = new Rectangle(20, 315, 190, 13);
	private static Rectangle relButtonRectNotions = new Rectangle(566, 332, 50, 23);
	private static Rectangle relTableRectNotions = new Rectangle(20, 331, 540, 100);
	
	private static Rectangle specLabelRect = new Rectangle(20, 441, 190, 13);
	private static Rectangle specButtonRect = new Rectangle(566, 458, 50, 23);
	private static Rectangle specTableRect = new Rectangle(20, 457, 540, 100);
	
	private static Rectangle domLabelRect = new Rectangle(20, 189, 157, 13);
	private static Rectangle domButtonAddRect = new Rectangle(566, 206, 50, 23);
	private static Rectangle domButtonDelRect = new Rectangle(566, 236, 50, 23);
	private static Rectangle domTableRect = new Rectangle(20, 205, 540, 100);
	
	private static Rectangle notTypeLabelRect = new Rectangle(430, 43, 35, 13);
	private static Rectangle notTypeComboRect = new Rectangle(465, 42, 95, 13);
	
	
	private static Rectangle notKindLabelRect = new Rectangle(430, 18, 35, 13);
	private static Rectangle notKindComboRect = new Rectangle(465, 17, 95, 13);
	
	private static Rectangle notAttsLabelRect = new Rectangle(20, 567, 157, 13);
	private static Rectangle notAttsButtonRmvRect = new Rectangle(566, 584, 50, 23);
	private static Rectangle notAttsTableRect = new Rectangle(20, 583, 540, 100);
	
	private static Rectangle notAttPrtsLabelRect = new Rectangle(20, 693, 157, 13);
	private static Rectangle notAttPrtsButtonRmvRect = new Rectangle(566, 710, 50, 23);
	private static Rectangle notAttPrtsTableRect = new Rectangle(20, 709, 540, 100);
	
	private static String LINK_TO_NOTION_MENU_ITEM = "Link to notion";
	
	public static String NOTION_TYPE_LABEL = "";
	
	public static String NOTION_KIND_LABEL = "Type: ";
	
	private static NotionAttributesComparator notAttrComparator = new NotionAttributesComparator();
	
	private static NotionAttributeParentsComparator notAttrPrtComparator = new NotionAttributeParentsComparator();
	
	public static ActorEditorControl getActorEditorControl(ActorEditor editor, Composite parent) {
		ActorEditorControl aec = new ActorEditorControl(editor, parent);
		createBasicContent(aec, aec.getModListener());
		createRelationshipsTable(aec, relLabelRect, relButtonRect, relTableRect);
		return aec;
	}
	
	public static TableViewer fillActorEditorControlWithData(ActorEditorControl control, ActorDTO actor) {
		((Text)control.getChildren()[getNAME_TEXT_INDEX()]).setText(actor.getName());
		((Text)control.getChildren()[getPATH_TEXT_INDEX()]).setText(actor.getSpecificationPath());
		convertMarkedTextToStyledText((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()], actor.getDescription(), (BusinessLayerFacade)((Actor)(control.getActorDTO())).getGraph());
//		((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setText(actor.getDescription());
		Table relTable = ((Table)control.getChildren()[RELATIONSHIPS_TABLE_INDEX]);
		TableViewer viewer = createRelationshipsTableViewer(relTable, actor, control);
		control.setClean();
		disableFieldsActor(control, actor);
		return viewer;
	}
	
	public static void updateActorEditorControlWithData(ActorEditorControl control, ActorDTO actor, TableViewer viewer) {
		((Text)control.getChildren()[getNAME_TEXT_INDEX()]).setText(actor.getName());
		((Text)control.getChildren()[getPATH_TEXT_INDEX()]).setText(actor.getSpecificationPath());
		convertMarkedTextToStyledText((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()], actor.getDescription(), (BusinessLayerFacade)((Actor)(control.getActorDTO())).getGraph());
//		((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setText(actor.getDescription());
		updateRelationshipsTableViewer(viewer);
		disableFieldsActor(control, actor);
		control.setClean();
	}
	
	/**
	 * Disables editable fields and buttons for Actor control if the actor is from a clipboard 
	 * @param control
	 * @param actor
	 */
	private static void disableFieldsActor(ActorEditorControl control, ActorDTO actor) {
		BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(actor).getBusinessLayerFacade();
		if(facade.isAnyClipboardMember(actor)) {
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEditable(false);
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEnabled(false);
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEnabled(false);
			((Button)control.getChildren()[getDELETE_RELATIONSHIP_BUTTON_INDEX()]).setEnabled(false);
			((Table)control.getChildren()[RELATIONSHIPS_TABLE_INDEX]).setEnabled(false);
		}
		else {
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEditable(true);
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEnabled(true);
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEnabled(true);
			((Button)control.getChildren()[getDELETE_RELATIONSHIP_BUTTON_INDEX()]).setEnabled(true);
			((Table)control.getChildren()[RELATIONSHIPS_TABLE_INDEX]).setEnabled(true);
		}
	}
	
	/**
	 * @param control
	 * @param actor
	 * @return false when name is not valid (unique)
	 */
	public static boolean saveActor(ActorEditorControl control, ActorDTO actor) {
		String newName = ((Text)control.getChildren()[getNAME_TEXT_INDEX()]).getText();
		if(!actor.isNameUnique(newName) && !actor.getName().equalsIgnoreCase(newName)) {
			return false;
		}
		actor.setName(newName);
//		actor.setDescription(((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).getText());
		actor.setDescription(convertStyledTextToMarkedText(((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()])));
		return true;
	}
	
	public static NotionEditorControl getNotionEditorControl(NotionEditor editor, Composite parent) {
		NotionEditorControl nec = new NotionEditorControl(editor, parent);
		createBasicContent(nec, nec.getModListener());
		createRelationshipsTable(nec, relLabelRectNotions, relButtonRectNotions, relTableRectNotions);
		createNotionSpecialisationTable(nec, specLabelRect, specButtonRect, specTableRect);
		createDomainStatementsTable(nec, domLabelRect, domButtonAddRect, domButtonDelRect, domTableRect);
		createNotionTypeCombo(nec, notTypeLabelRect, notTypeComboRect, nec.getModListener());
		createNotionAttributesTable(nec, notAttsLabelRect, notAttsButtonRmvRect, notAttsTableRect);
		createNotionKindCombo(nec, notKindLabelRect, notKindComboRect, nec.getModListener());
		createNotionAttributeParentsTable(nec, notAttPrtsLabelRect, notAttPrtsButtonRmvRect, notAttPrtsTableRect);
		return nec;
	}
	
	public static TableViewer fillNotionEditorControlWithData(NotionEditorControl control, NotionDTO notion) {
		((Text)control.getChildren()[getNAME_TEXT_INDEX()]).setText(notion.getName());
		((Text)control.getChildren()[getPATH_TEXT_INDEX()]).setText(notion.getSpecificationPath());
		convertMarkedTextToStyledText((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()], notion.getDescription(), (BusinessLayerFacade)((Notion)(control.getNotionDTO())).getGraph());
//		((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setText(notion.getDescription());
		Table relTable = ((Table)control.getChildren()[getRELATIONSHIPS_TABLE_INDEX()]);
		TableViewer viewer = createRelationshipsTableViewer(relTable, notion, control);
		Table specTable = ((Table)control.getChildren()[getNOTIONS_SPECIALISATIONS_TABLE_INDEX()]);
		control.getEditor().setViewerNotions(createNotionSpecialisationTableViewer(specTable, notion, control));
		Table domStatTable = ((Table)control.getChildren()[getDOMAIN_STATEMENTS_TABLE_INDEX()]);
		control.getEditor().setViewerStatements(createDomainStatementsTableViewer(control, domStatTable, notion));
		updateNotionKindCombo(control, notion);
		updateNotionTypeCombo(control, notion);
		Table notAttsTable = ((Table)control.getChildren()[getNOTION_ATTRIBUTES_TABLE_INDEX()]);
		TableViewer notAttsTableViewer = createNotionAttributesTableViewer(notAttsTable, notion, control);
		control.getEditor().setViewerAttributes(notAttsTableViewer);
		Table notAttPrtsTable = ((Table)control.getChildren()[getNOTION_ATTRIBUTE_PARENTS_TABLE_INDEX()]);
		TableViewer notAttPrtsTableViewer = createNotionAttributeParentsTableViewer(notAttPrtsTable, notion, control);
		control.getEditor().setViewerAttributeParents(notAttPrtsTableViewer);
		disableFieldsNotion(control, notion);
		control.setClean();
		return viewer;
	}
	
	public static void updateNotionEditorControlWithData(NotionEditorControl control, NotionDTO notion, TableViewer viewer, TableViewer viewerNotions, TableViewer viewerStatements, TableViewer viewerAttributes, TableViewer viewerAttributeParents) {
		((Text)control.getChildren()[getNAME_TEXT_INDEX()]).setText(notion.getName());
		((Text)control.getChildren()[getPATH_TEXT_INDEX()]).setText(notion.getSpecificationPath());
		convertMarkedTextToStyledText((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()], notion.getDescription(), (BusinessLayerFacade)((Notion)(control.getNotionDTO())).getGraph());
//		((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setText(notion.getDescription());
		updateRelationshipsTableViewer(viewer);
		updateNotionSpecialisationTableViewer(viewerNotions);
		updateStatementsTableViewer(viewerStatements);
		updateNotionKindCombo(control, notion);
		updateNotionTypeCombo(control, notion);
		updateNotionAttributesTableViewer(viewerAttributes);
		updateNotionAttributeParentsTableViewer(viewerAttributeParents);
		disableFieldsNotion(control, notion);
		control.setClean();
	}
	
	/**
	 * Disables editable fields and buttons for Notion control if the notion is from a clipboard 
	 * @param control
	 * @param actor
	 */
	private static void disableFieldsNotion(NotionEditorControl control, NotionDTO notion) {
		BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(notion).getBusinessLayerFacade();
		if(facade.isAnyClipboardMember(notion)) {
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEditable(false);
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEnabled(false);
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEnabled(false);
			((Button)control.getChildren()[getDELETE_RELATIONSHIP_BUTTON_INDEX()]).setEnabled(false);
			((Table)control.getChildren()[RELATIONSHIPS_TABLE_INDEX]).setEnabled(false);
			((Button)control.getChildren()[DELETE_NOTION_SPECIALISATION_BUTTON_INDEX]).setEnabled(false);
			((Table)control.getChildren()[NOTIONS_SPECIALISATIONS_TABLE_INDEX]).setEnabled(false);
			((Button)control.getChildren()[ADD_DOMAIN_STATEMENT_BUTTON_INDEX]).setEnabled(false);
			((Button)control.getChildren()[DELETE_DOMAIN_STATEMENT_BUTTON_INDEX]).setEnabled(false);
			((Table)control.getChildren()[DOMAIN_STATEMENTS_TABLE_INDEX]).setEnabled(false);
			((Combo)control.getChildren()[getNOTION_TYPE_COMBO_INDEX()]).setEnabled(false);
			((Button)control.getChildren()[NOTION_ATTRIBUTES_BUTTON_INDEX]).setEnabled(false);
			((Table)control.getChildren()[NOTION_ATTRIBUTES_TABLE_INDEX]).setEnabled(false);
			((Button)control.getChildren()[NOTION_ATTRIBUTE_PARENTS_BUTTON_INDEX]).setEnabled(false);
			((Table)control.getChildren()[NOTION_ATTRIBUTE_PARENTS_TABLE_INDEX]).setEnabled(false);
			((Combo)control.getChildren()[getNOTION_KIND_COMBO_INDEX()]).setEnabled(false);
		}
		else {
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEditable(true);
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEnabled(true);
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEnabled(true);
			((Button)control.getChildren()[getDELETE_RELATIONSHIP_BUTTON_INDEX()]).setEnabled(true);
			((Table)control.getChildren()[RELATIONSHIPS_TABLE_INDEX]).setEnabled(true);
			((Button)control.getChildren()[DELETE_NOTION_SPECIALISATION_BUTTON_INDEX]).setEnabled(true);
			((Table)control.getChildren()[NOTIONS_SPECIALISATIONS_TABLE_INDEX]).setEnabled(true);
			((Button)control.getChildren()[ADD_DOMAIN_STATEMENT_BUTTON_INDEX]).setEnabled(true);
			((Button)control.getChildren()[DELETE_DOMAIN_STATEMENT_BUTTON_INDEX]).setEnabled(true);
			((Table)control.getChildren()[DOMAIN_STATEMENTS_TABLE_INDEX]).setEnabled(true);
			if (((Combo)control.getChildren()[getNOTION_KIND_COMBO_INDEX()]).getSelectionIndex()!=5) ((Combo)control.getChildren()[getNOTION_TYPE_COMBO_INDEX()]).setEnabled(false);
			else ((Combo)control.getChildren()[getNOTION_TYPE_COMBO_INDEX()]).setEnabled(true);
			((Button)control.getChildren()[NOTION_ATTRIBUTES_BUTTON_INDEX]).setEnabled(true);
			((Table)control.getChildren()[NOTION_ATTRIBUTES_TABLE_INDEX]).setEnabled(true);
			((Button)control.getChildren()[NOTION_ATTRIBUTE_PARENTS_BUTTON_INDEX]).setEnabled(true);
			((Table)control.getChildren()[NOTION_ATTRIBUTE_PARENTS_TABLE_INDEX]).setEnabled(true);
			((Combo)control.getChildren()[getNOTION_KIND_COMBO_INDEX()]).setEnabled(true);
		}
		
	}
	
	/**
	 * @param control
	 * @param notion
	 * @return false when name is not valid (unique)
	 */
	public static boolean saveNotion(NotionEditorControl control, NotionDTO notion) {
		String newName = ((Text)control.getChildren()[getNAME_TEXT_INDEX()]).getText();
		if(!notion.isNameUnique(newName) && !notion.getName().equalsIgnoreCase(newName)) {
			return false;
		}
		notion.setName(newName);
//		notion.setDescription(((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).getText());
		notion.setDescription(convertStyledTextToMarkedText(((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()])));
		
		Combo typeCombo = (Combo)control.getChildren()[getNOTION_TYPE_COMBO_INDEX()];
		String typeName = typeCombo.getItem(typeCombo.getSelectionIndex());
		if(!typeName.equalsIgnoreCase(AttributeDataTypesEnum.EMPTY)) { 
			for(AttributeDataTypesEnum type : AttributeDataTypesEnum.values()) {
				if(type.getName().equalsIgnoreCase(typeName)) {
					if(notion.getDataType() != null) {
						if(!notion.getDataType().getTypeName().toString()
								.equalsIgnoreCase(type.toString())) {
							notion.getDataType().setTypeName(type.getType());
						}
					}
					else {
						BusinessLayerFacade facade 
							= SCProjectContainer.instance()
								.getSCProject(notion).getBusinessLayerFacade();
						PrimitiveDataTypeDTO newDataType 
							= facade.createPrimitiveDataTypeDTO();
						newDataType.setTypeName(type.getType());
						notion.setDataType(newDataType);
						typeCombo.remove(AttributeDataTypesEnum.EMPTY);
					}
					MNotion.setExtendedDataType(notion, type.tag());
					break;
				}
			}
		}
		
		Combo kindCombo = (Combo)control.getChildren()[getNOTION_KIND_COMBO_INDEX()];
		String type = kindCombo.getSelectionIndex()>0?NotionTypesEnum.values()[kindCombo.getSelectionIndex()-1].tag():"";
		if (MNotion.isClearActionType(notion,kindCombo.getSelectionIndex())){
			MNotion.clearActionTypes(notion,type);
			String [] values = new String[ActionTypesEnum.values(type).length+1];
			values[0] = ActionTypesEnum.EMPTY;
			for (int i = 1; i < values.length;i++) values[i] = ActionTypesEnum.names(type)[i-1];
			control.getEditor().getViewerStatements().getCellEditors()[1] = new ComboBoxCellEditor(control.getEditor().getViewerStatements().getTable(),values);
		}
		if (MConfiguration.isCheckRelations() && type.isEmpty() && !notion.getType().isEmpty())
			for (DomainElementRelationshipDTO der:notion.getDomainElementRelationshipDTOList()){
				if (!"1".equals(der.getSourceMultiplicity())) der.setSourceMultiplicity("1");
				if (!"1".equals(der.getTargetMultiplicity())) der.setTargetMultiplicity("1");
			}
		for (Stereotype s:((Notion) notion).getStereotypeList()) if (Arrays.asList(NotionTypesEnum.tags()).contains(s.getName())){
			s.delete();
		}
		if (kindCombo.getSelectionIndex()>0){
			Stereotype stereotype = ((BusinessLayerFacade) ((Notion )notion).getGraph()).createSclkernel$Stereotype();
			stereotype.setName(NotionTypesEnum.values()[kindCombo.getSelectionIndex()-1].tag());
			((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)notion).addStereotype(stereotype);
		}
		if (5!=kindCombo.getSelectionIndex() || typeName.equalsIgnoreCase(AttributeDataTypesEnum.EMPTY)){
			notion.setDataType(null);
		}
		return true;
	}
	
	public static SystemElementEditorControl getSystemElementEditorControl(SystemElementEditor editor, Composite parent) {
		SystemElementEditorControl seec = new SystemElementEditorControl(editor, parent);
		createBasicContent(seec, seec.getModListener());
		createRelationshipsTable(seec, relLabelRect, relButtonRect, relTableRect);
		return seec;
	}
	
	public static void updateSystemElementEditorControlWithData(SystemElementEditorControl control, SystemElementDTO sysEl, TableViewer viewer) {
		((Text)control.getChildren()[getNAME_TEXT_INDEX()]).setText(sysEl.getName());
		((Text)control.getChildren()[getPATH_TEXT_INDEX()]).setText(sysEl.getSpecificationPath());
		convertMarkedTextToStyledText((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()], sysEl.getDescription(), (BusinessLayerFacade)((SystemElement)(control.getSystemElementDTO())).getGraph());
//		((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setText(sysEl.getDescription());
		updateRelationshipsTableViewer(viewer);
		disableFieldsSystemElement(control, sysEl);
		control.setClean();
	}
	
	public static TableViewer fillSystemElementEditorControlWithData(SystemElementEditorControl control, SystemElementDTO sysEl) {
		((Text)control.getChildren()[getNAME_TEXT_INDEX()]).setText(sysEl.getName());
		((Text)control.getChildren()[getPATH_TEXT_INDEX()]).setText(sysEl.getSpecificationPath());
		convertMarkedTextToStyledText((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()], sysEl.getDescription(), (BusinessLayerFacade)((SystemElement)(control.getSystemElementDTO())).getGraph());
//		((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setText(sysEl.getDescription());
		Table relTable = ((Table)control.getChildren()[getRELATIONSHIPS_TABLE_INDEX()]);
		TableViewer viewer = createRelationshipsTableViewer(relTable, sysEl, control);
		control.setClean();
		disableFieldsSystemElement(control, sysEl);
		return viewer;
	}
	
	/**
	 * Disables editable fields and buttons for System Element control if the system element is from a clipboard 
	 * @param control
	 * @param actor
	 */
	private static void disableFieldsSystemElement(SystemElementEditorControl control, SystemElementDTO sysElem) {
		BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(sysElem).getBusinessLayerFacade();
		if(facade.isAnyClipboardMember(sysElem)) {
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEditable(false);
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEnabled(false);
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEnabled(false);
			((Button)control.getChildren()[getDELETE_RELATIONSHIP_BUTTON_INDEX()]).setEnabled(false);
			((Table)control.getChildren()[RELATIONSHIPS_TABLE_INDEX]).setEnabled(false);
		}
		else {
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEditable(true);
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEnabled(true);
			((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).setEnabled(true);
			((Button)control.getChildren()[getDELETE_RELATIONSHIP_BUTTON_INDEX()]).setEnabled(true);
			((Table)control.getChildren()[RELATIONSHIPS_TABLE_INDEX]).setEnabled(true);
		}
	}
	
	public static boolean saveSystemElement(SystemElementEditorControl control, SystemElementDTO sysElem) {
		String newName = ((Text)control.getChildren()[getNAME_TEXT_INDEX()]).getText();
		if(!sysElem.isNameUnique(newName) && !sysElem.getName().equalsIgnoreCase(newName)) {
			return false;
		}
		sysElem.setName(((Text)control.getChildren()[getNAME_TEXT_INDEX()]).getText());
//		sysElem.setDescription(((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()]).getText());
		sysElem.setDescription(convertStyledTextToMarkedText(((StyledText)control.getChildren()[getDESCRIPTION_TEXT_INDEX()])));
		return true;
	}
	
	private static void createBasicContent(final Composite control, ModifyListener modListener) {
		//check static indices after making any changes! 
		final Label nameLabel = new Label(control, SWT.NONE);
		nameLabel.setBounds(20, 20, 35, 13);
		nameLabel.setText("Name:");

		final Text nameText = new Text(control, SWT.BORDER);
		nameText.setBounds(70, 17, 350, 18);
		nameText.addModifyListener(modListener);//TODO aambroziewicz: no longer necessary, but leaving it as it is
		nameText.setEditable(false);
		
		final Label pathLabel = new Label(control, SWT.NONE);
		pathLabel.setBounds(20, 45, 26, 13);
		pathLabel.setText("Path:");

		final Text pathText = new Text(control, SWT.READ_ONLY | SWT.BORDER);
		pathText.setBounds(70, 42, 351, 18);
		
		final Label descriptionLabel = new Label(control, SWT.NONE);
		descriptionLabel.setText("Description:");
		descriptionLabel.setBounds(20, 80, 63, 13);
		
		createDescriptionField(control, modListener);
	}
	
	public static TextViewer createDescriptionField(final Composite control, ModifyListener modListener) {
		
		final TextViewer textViewer = new TextViewer(control, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);

		textViewer.setDocument(new Document());

		final ContentAssistant assistant = new ContentAssistant();
		DescriptionTextAssistProcessor myProcessor = new DescriptionTextAssistProcessor();
		assistant.setContentAssistProcessor(myProcessor, IDocument.DEFAULT_CONTENT_TYPE);
		assistant.install(textViewer);
		assistant.setStatusMessage("Select a notion");
		assistant.setStatusLineVisible(true);
		
	    textViewer.getControl().addKeyListener(new KeyAdapter() {
	    	
//	    	private boolean completionMode = false;

			public void keyPressed(KeyEvent e) {
				if (e.character == ' ' && e.stateMask == SWT.CTRL) { // ctrl-space as a trigger
					assistant.showPossibleCompletions();
//					completionMode = true;
				}
				if(e.stateMask == SWT.CTRL) { 
					if((e.keyCode == 'a') || (e.keyCode == 'A')){//"select all"
						((StyledText)e.widget).setSelection(0, ((StyledText)e.widget).getText().length());
					}
				}
//				if (e.keyCode == SWT.CR) {
//					if(completionMode) {
//						completionMode = false;
//					}
//				}
//				if(e.keyCode == SWT.ESC) {
//					completionMode = false;
//				}
			}
		});
	    
		final StyledText descriptionText = textViewer.getTextWidget();//= new StyledText(control, SWT.WRAP | SWT.V_SCROLL | SWT.MULTI | SWT.BORDER);
		descriptionText.setBounds(20, 99, 540, 80);

		Menu menu = new Menu(descriptionText);
		MenuItem addNotion = new MenuItem(menu, SWT.PUSH); 
		addNotion.setText(LINK_TO_NOTION_MENU_ITEM);
		addNotion.addSelectionListener(new CreateHyperlinkListener(descriptionText, control));
		descriptionText.setMenu(menu);
		descriptionText.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				for (MenuItem menuItem : descriptionText.getMenu().getItems()) {
					if (menuItem.getText().equalsIgnoreCase(LINK_TO_NOTION_MENU_ITEM)) {
						if (descriptionText.getSelectionCount() > 0) {
							if(descriptionText.getStyleRanges(descriptionText.getSelection().x, descriptionText.getSelection().y - descriptionText.getSelection().x).length > 0) {
								menuItem.setEnabled(false);
								return;
							}
							menuItem.setEnabled(true);
						} else {
							menuItem.setEnabled(false);
						}
					}
				}
			}
			
		});
		
		descriptionText.addMouseListener(new DomainElementHyperlinkListener(descriptionText, control));
		descriptionText.addVerifyKeyListener(new VerifyMarkedTextListener(descriptionText));
		descriptionText.addVerifyKeyListener(new EditHyperlinkedTextListener(descriptionText));
		descriptionText.addModifyListener(modListener);
		
		descriptionText.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// unselect
				if(descriptionText.getText().length() > 0) {
					descriptionText.setSelection(0);
				}
			}
			
		});
		
		return textViewer;
	}
	
	private static void createNotionTypeCombo(Composite control, Rectangle labelRect, Rectangle comboRect, ModifyListener modListener) {
		//check static indices after making any changes! 
		final Label typeLabel = new Label(control, SWT.NONE);
		typeLabel.setText(NOTION_TYPE_LABEL);
		typeLabel.setBounds(labelRect);
		
		final Combo typeCombo = new Combo(control, SWT.READ_ONLY | SWT.BORDER);
		typeCombo.setBounds(comboRect);
		typeCombo.add(AttributeDataTypesEnum.EMPTY);
		for(AttributeDataTypesEnum typeName : AttributeDataTypesEnum.values()) {
			typeCombo.add(typeName.getName());
		}
		typeCombo.select(0);
		typeCombo.addModifyListener(modListener);
	}
	
	private static void createNotionKindCombo(Composite control, Rectangle labelRect, Rectangle comboRect, ModifyListener modListener) {
		//check static indices after making any changes! 
		final Label typeLabel = new Label(control, SWT.NONE);
		typeLabel.setText(NOTION_KIND_LABEL);
		typeLabel.setBounds(labelRect);
		
		final Combo typeCombo = new Combo(control, SWT.READ_ONLY | SWT.BORDER);
		typeCombo.setBounds(comboRect);
		typeCombo.add(NotionTypesEnum.EMPTY);
		for(NotionTypesEnum typeName : NotionTypesEnum.values()) {
			typeCombo.add(typeName.toString().replace('_', ' '));
		}
		typeCombo.select(0);
		typeCombo.addModifyListener(modListener);
	}
	
	private static void createRelationshipsTable(Composite control, Rectangle labelRect, Rectangle buttonRect, Rectangle tableRect) {
		//check static indices after making any changes! 
		final Label relatedRequirementsLabel = new Label(control, SWT.NONE);
		relatedRequirementsLabel.setText("Related domain elements:");
		relatedRequirementsLabel.setBounds(labelRect);
		
		final Button deleteRelationshipButton = new Button(control, SWT.NONE);
		deleteRelationshipButton.setText("Delete");
		deleteRelationshipButton.setBounds(buttonRect);
		
		Listener listener = new DeleteRelationshipListener();
		
		deleteRelationshipButton.addListener(SWT.Selection, listener);
		
		// Create table
		Table relatedDomElsTable = new Table(control, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | 
				SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		relatedDomElsTable.setLinesVisible(true);
		relatedDomElsTable.setHeaderVisible(true);
		relatedDomElsTable.setBounds(tableRect);
		
		// Create columns
		TableColumn column = new TableColumn(relatedDomElsTable, SWT.LEFT, 0);
		column.setWidth(160);
		column.setText(DOMAIN_ELEMENT_COLUMN);
		
		column = new TableColumn(relatedDomElsTable, SWT.LEFT, 1);
		column.setWidth(150);
		column.setText(MULTIPLICITY_COLUMN);
		
		column = new TableColumn(relatedDomElsTable, SWT.LEFT, 2);
		column.setWidth(150);
		column.setText(MULTIPLICITY_OTHER_COLUMN);
		
		column = new TableColumn(relatedDomElsTable, SWT.LEFT, 3);
		column.setWidth(70);
		column.setText(IS_DIRECTED_COLUMN);
		
	}
	
	private static void createNotionAttributesTable(Composite control, Rectangle labelRect, Rectangle buttonRect, Rectangle tableRect) {
		//check static indices after making any changes! 
		final Label notionAttributesLabel = new Label(control, SWT.NONE);
		notionAttributesLabel.setText("Attributes:");
		notionAttributesLabel.setBounds(labelRect);
		
		final Button deleteNotionAttributeButton = new Button(control, SWT.NONE);
		deleteNotionAttributeButton.setText("Remove");
		deleteNotionAttributeButton.setBounds(buttonRect);
		
		Listener listener = new RemoveNotionAttributeListener();
		
		deleteNotionAttributeButton.addListener(SWT.Selection, listener);
		
		// Create table
		Table notionAttsTable = new Table(control, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | 
				SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		notionAttsTable.setLinesVisible(true);
		notionAttsTable.setHeaderVisible(true);
		notionAttsTable.setBounds(tableRect);
		
		// Create columns
		TableColumn column = new TableColumn(notionAttsTable, SWT.LEFT, 0);
		column.setWidth(260);
		column.setText(columnNamesAttributes[0]);
		column.addSelectionListener(getSelectionAdapter(column));
		
		column = new TableColumn(notionAttsTable, SWT.LEFT, 1);
		column.setWidth(185);
		column.setText(columnNamesAttributes[1]);
	}
	
	private static void createNotionAttributeParentsTable(Composite control, Rectangle labelRect, Rectangle buttonRect, Rectangle tableRect) {
		//check static indices after making any changes! 
		final Label notionAttributeParentsLabel = new Label(control, SWT.NONE);
		notionAttributeParentsLabel.setText("Attribute parents:");
		notionAttributeParentsLabel.setBounds(labelRect);
		
		final Button deleteNotionAttributeParentButton = new Button(control, SWT.NONE);
		deleteNotionAttributeParentButton.setText("Remove");
		deleteNotionAttributeParentButton.setBounds(buttonRect);
		
		Listener listener = new RemoveNotionAttributeParentListener();
		
		deleteNotionAttributeParentButton.addListener(SWT.Selection, listener);
		
		// Create table
		Table notionAttPrtsTable = new Table(control, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | 
				SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		notionAttPrtsTable.setLinesVisible(true);
		notionAttPrtsTable.setHeaderVisible(true);
		notionAttPrtsTable.setBounds(tableRect);
		
		// Create columns
		TableColumn column = new TableColumn(notionAttPrtsTable, SWT.LEFT, 0);
		column.setWidth(260);
		column.setText(columnNamesAttributeParents[0]);
		column.addSelectionListener(getSelectionAdapter(column));
		
		column = new TableColumn(notionAttPrtsTable, SWT.LEFT, 1);
		column.setWidth(185);
		column.setText(columnNamesAttributeParents[1]);
	}
	
	private static SelectionAdapter getSelectionAdapter(final TableColumn column) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				notAttrComparator.setColumn(0);
				int direction = notAttrComparator.getDirection();
				column.getParent().setSortDirection(direction);
				column.getParent().setSortColumn(column);
				notAttrComparator.getViewer().refresh();
			}
		};
		return selectionAdapter;
	}
	
	private static TableViewer createNotionAttributesTableViewer(Table table, Object input, Control control) {
		TableViewer notAttsTableViewer = new TableViewer(table);
		notAttsTableViewer.setUseHashlookup(true);
		notAttsTableViewer.setColumnProperties(columnNamesAttributes);
		
		notAttrComparator.setViewer(notAttsTableViewer);
		notAttsTableViewer.setComparator(notAttrComparator);
		
		notAttsTableViewer.setContentProvider(new NotionAttributesContentProvider());
		notAttsTableViewer.setLabelProvider(new NotionAttributesLabelProvider());
		notAttsTableViewer.setInput(input);
		
		return notAttsTableViewer;
	}
	
	private static TableViewer createNotionAttributeParentsTableViewer(Table table, Object input, Control control) {
		TableViewer notAttPrtsTableViewer = new TableViewer(table);
		notAttPrtsTableViewer.setUseHashlookup(true);
		notAttPrtsTableViewer.setColumnProperties(columnNamesAttributeParents);
		
		notAttrPrtComparator.setViewer(notAttPrtsTableViewer);
		notAttPrtsTableViewer.setComparator(notAttrPrtComparator);
		
		notAttPrtsTableViewer.setContentProvider(new NotionAttributeParentsContentProvider());
		notAttPrtsTableViewer.setLabelProvider(new NotionAttributeParentsLabelProvider());
		notAttPrtsTableViewer.setInput(input);
		
		return notAttPrtsTableViewer;
	}
	
	private static TableViewer createRelationshipsTableViewer(Table table, Object input, Control control) {
		TableViewer relatedDomElsTableViewer = new TableViewer(table);
		relatedDomElsTableViewer.setUseHashlookup(true);
		relatedDomElsTableViewer.setColumnProperties(columnNames);
		
		// Create cell editors
		CellEditor[] editors = new CellEditor[columnNames.length];
//		String[] multiplicitiesTypes = new String[] {"0..1", "1", "0..*", "1..*"};
		// --
		editors[0] = null;
//		editors[1] = null;
//		editors[2] = new ComboBoxCellEditor(table, multiplicitiesTypes);
		editors[1] = new TextCellEditor(table);
		editors[2] = new TextCellEditor(table);
		editors[3] = new ComboBoxCellEditor(table, RelatedDomainElementsList.boolValues);
		
		((Text)editors[1].getControl()).addListener(SWT.TRAVERSE_TAB_NEXT, new ValidateMultiplicityListener());
		((Text)editors[2].getControl()).addListener(SWT.TRAVERSE_TAB_NEXT, new ValidateMultiplicityListener());
		
		// Assign the cell editors to the viewer 
		relatedDomElsTableViewer.setCellEditors(editors);
		// Set the cell modifier for the viewer
		relatedDomElsTableViewer.setCellModifier(
				new RelatedDomainElementsCellModifier(input, relatedDomElsTableViewer, control));
		// Set the default sorter for the viewer 
		//relatedReqTableViewer.setSorter(new ExampleTaskSorter(ExampleTaskSorter.DESCRIPTION));
		
		relatedDomElsTableViewer.setContentProvider(new RelatedDomainElementsContentProvider());
		relatedDomElsTableViewer.setLabelProvider(new RelatedDomainElementsLabelProvider(input));
		relatedDomElsTableViewer.setInput(input);
		
		return relatedDomElsTableViewer;
	}
	
	private static void updateRelationshipsTableViewer(TableViewer viewer) {
		viewer.refresh();
	}
	
	private static void createNotionSpecialisationTable(Composite control, Rectangle labelRect, Rectangle buttonRect, Rectangle tableRect) {
		//check static indices after making any changes! 
		final Label relatedRequirementsLabel = new Label(control, SWT.NONE);
		relatedRequirementsLabel.setText("Generalised and specialised notions:");
		relatedRequirementsLabel.setBounds(labelRect);
		
		final Button deleteRelationshipButton = new Button(control, SWT.NONE);
		deleteRelationshipButton.setText("Delete");
		deleteRelationshipButton.setBounds(buttonRect);
		
		Listener listener = new DeleteSpecialisationListener();
		
		deleteRelationshipButton.addListener(SWT.Selection, listener);
		
		// Create table
		Table notionsTable = new Table(control, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | 
				SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		notionsTable.setLinesVisible(true);
		notionsTable.setHeaderVisible(true);
		notionsTable.setBounds(tableRect);
		
		// Create columns
		TableColumn column = new TableColumn(notionsTable, SWT.LEFT, 0);
		column.setWidth(260);
		column.setText(NOTION_COLUMN);
		
		column = new TableColumn(notionsTable, SWT.LEFT, 1);
		column.setWidth(185);
		column.setText(ROLE_COLUMN);
	}
	
	private static void createDomainStatementsTable(Composite control, Rectangle labelRect, Rectangle buttonAddRect, Rectangle buttonDelRect, Rectangle tableRect) {
		//check static indices after making any changes! 
		final Label domStatementsLabel = new Label(control, SWT.NONE);
		domStatementsLabel.setText("Domain statements:");
		domStatementsLabel.setBounds(labelRect);
		
		final Button addStatementButton = new Button(control, SWT.NONE);
		addStatementButton.setText("Add");
		addStatementButton.addSelectionListener((SelectionListener)(new AddDomainStatementListener((NotionEditorControl)control)));
		addStatementButton.setBounds(buttonAddRect);
		
		final Button deleteStatementButton = new Button(control, SWT.NONE);
		deleteStatementButton.setText("Delete");
		deleteStatementButton.addSelectionListener((SelectionListener)(new DeleteDomainStatementListener((NotionEditorControl)control)));
		deleteStatementButton.setBounds(buttonDelRect);
		
		// Create table
		Table dsTable = new Table(control, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | 
				SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		dsTable.setLinesVisible(true);
		dsTable.setHeaderVisible(true);
		dsTable.setBounds(tableRect);
		
		// Create columns
		TableColumn column = new TableColumn(dsTable, SWT.LEFT, 0);
		column.setWidth(460);
		column.setText(DS_NAME_COLUMN);
		
		column = new TableColumn(dsTable, SWT.LEFT, 1);
		column.setWidth(75);
		column.setText(DS_ACTION_TYPE_COLUMN);
	}
	
	private static TableViewer createNotionSpecialisationTableViewer(Table table, Object input, NotionEditorControl control) {
		TableViewer notionsTableViewer = new TableViewer(table);
		notionsTableViewer.setUseHashlookup(true);
		notionsTableViewer.setColumnProperties(columnNamesNotions);
		
		// Create cell editors
		CellEditor[] editors = new CellEditor[columnNamesNotions.length];
		// --
		editors[0] = null;
		editors[1] = new ComboBoxCellEditor(table, 
						NotionSpecialisationsList.roleTypes);
		
		// Assign the cell editors to the viewer 
		notionsTableViewer.setCellEditors(editors);
		// Set the cell modifier for the viewer
		notionsTableViewer.setCellModifier(new NotionSpecialisationsCellModifier((NotionDTO)input, notionsTableViewer, control));
		// Set the default sorter for the viewer 
		//relatedReqTableViewer.setSorter(new ExampleTaskSorter(ExampleTaskSorter.DESCRIPTION));
		
		notionsTableViewer.setContentProvider(new NotionSpecialisationsContentProvider());
		notionsTableViewer.setLabelProvider(new NotionSpecialisationsLabelProvider((NotionDTO)input));
		notionsTableViewer.setInput(input);
		
		return notionsTableViewer;
	}
	
	private static TableViewer createDomainStatementsTableViewer(NotionEditorControl control, Table table, Object input) {
		TableViewer domStatTableViewer = new TableViewer(table);
		domStatTableViewer.setUseHashlookup(true);
		domStatTableViewer.setColumnProperties(columnNamesStatements);
		
		CellEditor[] editors = new CellEditor[columnNamesStatements.length];
		editors[0] = null;
		String [] values = new String[ActionTypesEnum.values(((NotionDTO) input).getType()).length+1];
		values[0] = ActionTypesEnum.EMPTY;
		for (int i = 1; i < values.length;i++) values[i] = ActionTypesEnum.names(((NotionDTO) input).getType())[i-1];
		editors[1] = new ComboBoxCellEditor(table,values);
		domStatTableViewer.setCellEditors(editors);
		
		domStatTableViewer.setCellModifier(new ActionTypeCellModifier(domStatTableViewer, control));
		
		domStatTableViewer.setContentProvider(new DomainStatementsContentProvider());
		domStatTableViewer.setLabelProvider(new DomainStatementsLabelProvider((NotionDTO)input));
		domStatTableViewer.addOpenListener(new OpenDomainStatementListener(control,domStatTableViewer));
		domStatTableViewer.addSelectionChangedListener(new SelectDomainStatementListener(control));
		domStatTableViewer.setInput(input);
		
		return domStatTableViewer;
	}
	
	private static void updateNotionSpecialisationTableViewer(TableViewer viewer) {
		viewer.refresh();
	}
	
	private static void updateStatementsTableViewer(TableViewer viewer) {
		viewer.refresh();
	}
	
	private static void updateNotionAttributesTableViewer(TableViewer viewer) {
		viewer.refresh();
	}
	
	private static void updateNotionAttributeParentsTableViewer(TableViewer viewer) {
		viewer.refresh();
	}
	
	private static void updateNotionTypeCombo(NotionEditorControl control, NotionDTO notion) {
		PrimitiveDataTypeDTO dataType = notion.getDataType();
		if(!notion.getExtendedDataType().isEmpty() || dataType != null) {//then set the type combo
			int index = 0;
			for(AttributeDataTypesEnum type : AttributeDataTypesEnum.values()) {
				index++;
				if(type.tag().equals(notion.getExtendedDataType()) || notion.getExtendedDataType().isEmpty() && type.getType().equals(dataType.getTypeName())) {
					break;
				}
			}
			if(((Combo)control.getChildren()[getNOTION_TYPE_COMBO_INDEX()]).indexOf(AttributeDataTypesEnum.EMPTY) != -1){
				((Combo)control.getChildren()[getNOTION_TYPE_COMBO_INDEX()]).select(index);
				((Combo)control.getChildren()[getNOTION_TYPE_COMBO_INDEX()]).remove(AttributeDataTypesEnum.EMPTY);
			} else ((Combo)control.getChildren()[getNOTION_TYPE_COMBO_INDEX()]).select(index-1);
			
		}
	}
	
	private static void updateNotionKindCombo(NotionEditorControl control, NotionDTO notion) {
		loop:
		for (Stereotype s:((Notion)notion).getStereotypeList()){
			int i = 1;
			for (NotionTypesEnum val:NotionTypesEnum.values()){
				if (val.tag().equals(s.getName())){
					((Combo)control.getChildren()[getNOTION_KIND_COMBO_INDEX()]).select(i);
					break loop;
				}
				i++;
			}
		}
	}
	
	/**
	 * Finds the index of the column by given column name in relationships table
	 * @param columnName
	 * @return
	 */
	public static int getIndex(String columnName) {
		for (int i = 0; i < columnNames.length; i++) {
			if(columnName.equalsIgnoreCase(columnNames[i])) {
				return i;
			}
		}
		return -1; //unknown column
		

	}
	
	/**
	 * Finds the index of the column by given column name in specialisations table
	 * @param columnName
	 * @return
	 */
	public static int getIndexSpecialisations(String columnName) {
		for (int i = 0; i < columnNamesNotions.length; i++) {
			if(columnName.equalsIgnoreCase(columnNamesNotions[i])) {
				return i;
			}
		}
		return -1; //unknown column
	}
	
	public static int getIndexStatements(String columnName) {
		for (int i = 0; i < columnNamesStatements.length; i++) {
			if(columnName.equalsIgnoreCase(columnNamesStatements[i])) {
				return i;
			}
		}
		return -1; //unknown column
	}

	private static int getNAME_TEXT_INDEX() {
		return NAME_TEXT_INDEX;
	}

	private static int getPATH_TEXT_INDEX() {
		return PATH_TEXT_INDEX;
	}

	private static int getDESCRIPTION_TEXT_INDEX() {
		return DESCRIPTION_TEXT_INDEX;
	}

	public static int getRELATIONSHIPS_TABLE_INDEX() {
		return RELATIONSHIPS_TABLE_INDEX;
	}

	public static int getNOTIONS_SPECIALISATIONS_TABLE_INDEX() {
		return NOTIONS_SPECIALISATIONS_TABLE_INDEX;
	}

	public static int getDOMAIN_STATEMENTS_TABLE_INDEX() {
		return DOMAIN_STATEMENTS_TABLE_INDEX;
	}
	
	public static int getNOTION_TYPE_COMBO_INDEX() {
		return NOTION_TYPE_COMBO_INDEX;
	}
	
	public static int getNOTION_KIND_COMBO_INDEX() {
		return NOTION_KIND_COMBO_INDEX;
	}
	
	/**
	 * Converts text containing styles, to text with "[[" and "]]"
	 * @param styledTextControl control containing styles
	 * @return text marked with hyperlink markers
	 * @see Constants.HYPERLINK_LEFT_MARKER Constants.HYPERLINK_RIGHT_MARKER 
	 */
	public static String convertStyledTextToMarkedText(StyledText styledTextControl) {
		String text = styledTextControl.getText();
		String result = "";
		StyleRange[] styles = styledTextControl.getStyleRanges(true);
		int beginIndex = 0;
		int endIndex = 0;
		for(StyleRange style : styles) {
			beginIndex = endIndex;
			endIndex = style.start;
			if(beginIndex < endIndex) {
				result += text.substring(beginIndex, endIndex); //text before style
			}
			result += Constants.HYPERLINK_LEFT_MARKER;
			beginIndex = endIndex;
			endIndex = style.start + style.length;
			result += text.substring(beginIndex, endIndex); //styled text
			result += Constants.HYPERLINK_RIGHT_MARKER;
		}
		if(endIndex <= text.length() - 1) {
			result += text.substring(endIndex); //what's left after styles
		}
		return result;
	}
	
	/**
	 * Updates styles in a given StyledText control, accordingly to markings in marked text
	 * @param styledTextControl control to update styles in
	 * @param markedText text marked with hyperlink markers
	 * @see Constants.HYPERLINK_LEFT_MARKER Constants.HYPERLINK_RIGHT_MARKER
	 */
	public static void convertMarkedTextToStyledText(StyledText styledTextControl, String markedText, BusinessLayerFacade facade) {
//		if(markedText.isEmpty()) {//handle empty description
//			styledTextControl.setText("\n");
//			return;
//		}
		String unmarkedText = "";
		String tempText = "";
		int beginIndex = 0;
		int endIndex = markedText.indexOf(Constants.HYPERLINK_LEFT_MARKER);
		if(endIndex < 0) { //no left markers, do nothing
			styledTextControl.setText(markedText);
			return;
		}
		int removedMarkers = 0;
		List<StyleRange> styles = new ArrayList<StyleRange>();
		while(endIndex >= 0 && endIndex >= beginIndex) { //at least one occurrence left
			if(endIndex != 0) {
				unmarkedText += markedText.substring(beginIndex, endIndex);
			}
			beginIndex = endIndex + Constants.HYPERLINK_LEFT_MARKER.length();
			endIndex = markedText.indexOf(Constants.HYPERLINK_RIGHT_MARKER, beginIndex);
			if(endIndex < 0) { //no closing ]]
				break;
			}
			String notionName = markedText.substring(beginIndex, endIndex);
			unmarkedText += notionName;
			removedMarkers += 2;
			//create a style
			//colours
			Color linkColor = SCProjectHelper.getShell().getDisplay().getSystemColor(Constants.HYPERLINK_FONT_COLOR_TARGET_DOES_NOT_EXIST);
			if(facade != null) {
	        	  List<NotionDTO> notions = facade.getAllNotions();
	        	  //prepared lemmas
	        	  List<String> lemmas = new ArrayList<String>();//TODO no repetitions in the lists
	        	  if(RemoteJGWNL.getInstance().isConnected()) {
		        	  TermSenseDTO[] ts = RemoteJGWNL.getInstance().getTermSenses(notionName, Constants.TERM_NOUN);
		        	  for(TermSenseDTO sense : ts) {
		        		  lemmas.add(sense.getLemma());
		        	  }
	        	  }
	        	  for(NotionDTO notion : notions) {
	        		  //first check by names
		        	  if(notion.getName().equalsIgnoreCase(notionName)) {
		        		  linkColor = SCProjectHelper.getShell().getDisplay().getSystemColor(Constants.HYPERLINK_FONT_COLOR_TARGET_EXISTS);
		        		  break;
		        	  }
		        	  //now check by lemmas
		        	  for(String lemma : lemmas) {
			        	  if(notion.getName().equalsIgnoreCase(lemma)) {
			        		  linkColor = SCProjectHelper.getShell().getDisplay().getSystemColor(Constants.HYPERLINK_FONT_COLOR_TARGET_EXISTS);
			        		  break;
			        	  }
		        	  }
	        	  }
	        }
			StyleRange style 
				= new StyleRange(beginIndex - (removedMarkers - 1)*Constants.HYPERLINK_RIGHT_MARKER.length(), // - markers - 1
						notionName.length(),   
						linkColor, null, Constants.HYPERLINK_FONT_STYLE);
			styles.add(style);
			beginIndex = endIndex + Constants.HYPERLINK_RIGHT_MARKER.length();
			tempText = markedText.substring(beginIndex);//what's left
			endIndex = tempText.indexOf(Constants.HYPERLINK_LEFT_MARKER) + unmarkedText.length() + removedMarkers*Constants.HYPERLINK_RIGHT_MARKER.length();
		}
		unmarkedText += tempText;
		styledTextControl.setText(unmarkedText);
		styledTextControl.setStyleRanges(styles.toArray(new StyleRange[0]));
	}
	
	/**
	 * Accepts controls of type {@link ActorEditorControl}, {@link NotionEditorControl}, {@link SystemElementEditorControl} and returns facade
	 * @param control
	 * @return null if control is not in one of recognizable types
	 */
	public static BusinessLayerFacade getFacadeForControl(Composite control) {
		if(control == null) {
			return null;
		}
		BusinessLayerFacade facade = null;
		if (control instanceof ActorEditorControl) {
			facade = ((ActorEditorControl) control).getEditor().getFacade();
		} else if (control instanceof NotionEditorControl) {
			facade = ((NotionEditorControl) control).getEditor().getFacade();
		} else if (control instanceof SystemElementEditorControl) {
			facade = ((SystemElementEditorControl) control).getEditor().getFacade();
		} else if (control instanceof RequirementEditorControl) {
			facade = ((RequirementEditorControl) control).getEditor().getFacade();
		} else if (control instanceof RequirementEditorControl) {
			facade = ((RequirementEditorControl) control).getEditor().getFacade();
		} else if (control.getParent() instanceof UseCaseMainView) {
			facade = ((UseCaseMainView) control.getParent()).getEditor().getFacade();
		}
		return facade;
	}
	
	/**
	 * Accepts controls of type {@link ActorEditorControl}, {@link NotionEditorControl}, {@link SystemElementEditorControl} and returns facade
	 * @param control
	 * @return null if control is not in one of recognizable types
	 */
	public static SCProject getSCPRojectForControl(Composite control) {
		if(control == null) {
			return null;
		}
		SCProject project = null;
		if (control instanceof ActorEditorControl) {
			project = ((ActorEditorControl) control).getEditor().getScProject();
		} else if (control instanceof NotionEditorControl) {
			project = ((NotionEditorControl) control).getEditor().getScProject();
		} else if (control instanceof SystemElementEditorControl) {
			project = ((SystemElementEditorControl) control).getEditor().getScProject();
		} else if (control instanceof RequirementEditorControl) {
			project = ((RequirementEditorControl) control).getEditor().getScProject();
		} else if (control instanceof UseCaseMainView) {
			project = ((UseCaseMainView) control).getEditor().getScProject();
		} else if (control.getParent() instanceof UseCaseMainView){
			project = ((UseCaseMainView) control.getParent()).getEditor().getScProject();
		}
		return project;
	}
	
	/**
	 * Compares two colours. 
	 * @param c1
	 * @param c2
	 * @return true if RGB numbers are equal for a given pair of colours, false otherwise
	 */
	public static boolean compareColors(Color c1, Color c2) {
		if(c1 == null || c2 == null) {
			return false;
		}
		if(c1.getRed() != c2.getRed()) {
			return false;
		}
		if(c1.getGreen() != c2.getGreen()) {
			return false;
		}
		if(c1.getBlue() != c2.getBlue()) {
			return false;
		}
		return true;
	}

	public static int getDELETE_RELATIONSHIP_BUTTON_INDEX() {
		return DELETE_RELATIONSHIP_BUTTON_INDEX;
	}

	public static int getNOTION_ATTRIBUTES_TABLE_INDEX() {
		return NOTION_ATTRIBUTES_TABLE_INDEX;
	}
	
	public static int getNOTION_ATTRIBUTE_PARENTS_TABLE_INDEX() {
		return NOTION_ATTRIBUTE_PARENTS_TABLE_INDEX;
	}
	
}
