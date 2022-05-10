package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.DomainElementEditorControlFactory;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.NotionEditorControl;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;

public class NotionSpecialisationsCellModifier implements ICellModifier {
	
	private NotionDTO notion;
	private TableViewer viewer;
	private SCProject scProject;
	private NotionEditorControl control;

	public NotionSpecialisationsCellModifier(NotionDTO notion,
			TableViewer viewer, NotionEditorControl control) {
		super();
		this.notion = notion;
		this.viewer = viewer;
		this.control = control;
	}

	@Override
	public boolean canModify(Object element, String property) {
		return true;
	}

	@Override
	public Object getValue(Object element, String property) {
		Object result = null;
		NotionSpecialisationDTO relation = (NotionSpecialisationDTO)element;
		
		int columnIndex = DomainElementEditorControlFactory.getIndexSpecialisations(property);
		
		switch (columnIndex) {
			case 1 : // Role Column 
				String role = NotionSpecialisationsList.getRoleToDisplay(
							relation, this.notion);
				int i = NotionSpecialisationsList.roleTypes.length - 1;
				while(!role.equals(NotionSpecialisationsList.roleTypes[i]) && i > 0) {
					i--;
				}
				result = new Integer(i);
				break;
			default :
				result = "";
		}
		return result;	
	}

	@Override
	public void modify(Object element, String property, Object value) {
		TableItem item = (TableItem) element;
		NotionSpecialisationDTO specialisation 
			= (NotionSpecialisationDTO) item.getData();
		String role = NotionSpecialisationsList.getRoleToDisplay(
				specialisation, this.notion);
		//check if role changed
		if(role.equalsIgnoreCase(NotionSpecialisationsList.roleTypes[0])) {
			if(((Integer)value).intValue() != 0) {
				specialisation.reverse();
			}
			else {
				return;
			}
		}
		else {
			if(((Integer)value).intValue() != 1) {
				specialisation.reverse();
			}
			else {
				return;
			}
		}
		viewer.refresh(specialisation);
		if(scProject == null) {
			this.scProject = control.getEditor().getScProject();
		}
		scProject.save();
		
	}

}
