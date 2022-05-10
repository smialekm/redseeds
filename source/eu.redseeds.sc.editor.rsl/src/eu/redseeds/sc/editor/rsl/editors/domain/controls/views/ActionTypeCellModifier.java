package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.DomainElementEditorControlFactory;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.NotionEditorControl;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.ActionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;

public class ActionTypeCellModifier implements ICellModifier {
	
	private TableViewer viewer;
	private SCProject scProject;
	private NotionEditorControl control;

	public ActionTypeCellModifier(TableViewer viewer, NotionEditorControl control) {
		super();
		this.viewer = viewer;
		this.control = control;
	}

	@Override
	public boolean canModify(Object element, String property) {
		DomainStatementDTO ds = (DomainStatementDTO)element;
		int columnIndex = DomainElementEditorControlFactory.getIndexStatements(property);
		boolean result;
		
		switch (columnIndex) {
			case 1 : // CRUD Column 
				result = !(ds.getPhraseDTO() instanceof NounPhraseDTO) && 0<ActionTypesEnum.values(ds.getParent().getType()).length;
				break;
			default:
				result=true;
		}
		
		return result;
	}

	@Override
	public Object getValue(Object element, String property) {
		Object result = null;
		DomainStatementDTO ds = (DomainStatementDTO)element;
		
		int columnIndex = DomainElementEditorControlFactory.getIndexStatements(property);
		
		switch (columnIndex) {
			case 1 : // CRUD Column 
				result = ActionTypesEnum.getActionTypeNumber(ds);
				break;
			default :
				result = "";
		}
		return result;
	}

	@Override
	public void modify(Object element, String property, Object value) {
		TableItem item = (TableItem) element;
		DomainStatementDTO ds 
			= (DomainStatementDTO) item.getData();
		if (0<((Integer)value)) MNotion.setActionType(ds, ActionTypesEnum.tags(ds.getParent().getType())[((Integer)value)-1]);
		else MNotion.setActionType(ds, "");
		viewer.refresh(ds);
		if(scProject == null) {
			this.scProject = control.getEditor().getScProject();
		}
		scProject.save();
		
	}

}
