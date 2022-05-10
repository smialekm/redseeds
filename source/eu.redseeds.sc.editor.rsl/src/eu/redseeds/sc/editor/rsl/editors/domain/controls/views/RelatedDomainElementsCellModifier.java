package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableItem;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.ActorEditorControl;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.DomainElementEditorControlFactory;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.NotionEditorControl;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.SystemElementEditorControl;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.ValidateMultiplicityListener;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.remics.recovery.model.preferences.MConfiguration;

public class RelatedDomainElementsCellModifier implements ICellModifier {
	
	private Object domainElement;
	private TableViewer viewer;
	private SCProject scProject;
	private Control control;
	
	/**
	 * Constructor 
	 * @param domainElement - an element for which editor is open 
	 */
	public RelatedDomainElementsCellModifier(Object domainElement, 
				TableViewer viewer, Control control) {
		super();
		this.domainElement = domainElement;
		this.viewer = viewer;
		this.control = control;
	}

	/**
	 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
	 */
	public boolean canModify(Object element, String property) {
		DomainElementRelationshipDTO relation = (DomainElementRelationshipDTO)element;
		int columnIndex = DomainElementEditorControlFactory.getIndex(property);
		boolean result;
		
		switch (columnIndex) {
			case 1 :
			case 2 :
				result = !(relation.getSource() instanceof NotionDTO) || !(relation.getTarget() instanceof NotionDTO) || !MConfiguration.isCheckRelations() || ((NotionDTO) relation.getSource()).getType().isEmpty() && ((NotionDTO) relation.getTarget()).getType().isEmpty() ;
					break;
			default:
				result=true;
		}
		return result;
	}

	/**
	 * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
	 */
	public Object getValue(Object element, String property) {
		
		Object result = null;
		DomainElementRelationshipDTO relation = (DomainElementRelationshipDTO)element;
		
		int columnIndex = DomainElementEditorControlFactory.getIndex(property);
		
		switch (columnIndex) { //commented out = uneditable
//			case 0 : // Domain Element column 
//				result = new String(relation.getSource().toString());
//				break;
//			case 1 : // Relationship type column 
//				result = new String("--TODO--");
//				break;
			case 1 : // "This" Multiplicity Column 
				result = RelatedDomainElementsList.getThisMultiplicityToDisplay(
							relation, this.domainElement);
				break;
			case 2 : // "Other" Multiplicity Column 
				result = RelatedDomainElementsList.getOtherMultiplicityToDisplay(
							relation, this.domainElement);
				break;
			case 3 : // "Is directed" Column 
				int i = RelatedDomainElementsList.boolValues.length - 1;
				while(!Boolean.toString(relation.isDirected()).equals(RelatedDomainElementsList.boolValues[i]) && i > 0) {
					i--;
				}
				result = new Integer(i);
				break;
			default :
				result = "";
		}
		return result;	
	}

	/**
	 * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
	 */
	public void modify(Object element, String property, Object value) {
		
		int columnIndex = DomainElementEditorControlFactory.getIndex(property);
		
		TableItem item = (TableItem) element;
		DomainElementRelationshipDTO relation 
				= (DomainElementRelationshipDTO) item.getData();
		
		if(columnIndex == 1 || columnIndex ==2) {
			//prevent change of value if not validated
			if(ValidateMultiplicityListener.isValid(value.toString()) != null) {
				return;
			}
			if(property.equalsIgnoreCase(DomainElementEditorControlFactory.columnNames[1])) {
				RelatedDomainElementsList.setThisMultiplicity(
						relation, domainElement, value.toString());
			}
			else if(property.equalsIgnoreCase(DomainElementEditorControlFactory.columnNames[2])){
				RelatedDomainElementsList.setOtherMultiplicity(
						relation, domainElement, value.toString());
			}
			viewer.refresh(domainElement);
			save();
		}
		else if(columnIndex == 3) {
			//check if "directed" changed
			if(String.valueOf(relation.isDirected()).equalsIgnoreCase(RelatedDomainElementsList.boolValues[0])) {
				if(((Integer)value).intValue() != 0) {
					relation.setDirected(false);
				}
				else {
					return;
				}
			}
			else {
				if(((Integer)value).intValue() != 1) {
					relation.setDirected(true);
				}
				else {
					return;
				}
			}
			viewer.refresh(domainElement);
			save();
		}
		
	}
	
	private void save() {
		if(scProject == null) {
			if(this.control instanceof ActorEditorControl) {
				this.scProject = ((ActorEditorControl)control).getEditor().getScProject();
			}
			else if(this.control instanceof NotionEditorControl) {
				this.scProject = ((NotionEditorControl)control).getEditor().getScProject();
			}
			else if(this.control instanceof SystemElementEditorControl) {
				this.scProject = ((SystemElementEditorControl)control).getEditor().getScProject();
			}
		}
		scProject.save();
	}
}
