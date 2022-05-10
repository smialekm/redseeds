package eu.redset.tsl.editor.editors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.StyledText;

import eu.redset.emf.model.tsl.DirectObject;
import eu.redset.emf.model.tsl.IndirectObject;

public class ReDSeTModifyListener implements ModifyListener{

	private EObject obj;
	private Composite view;
	private Composite text;
	
	public void setObject(EObject listenerObj){
		this.obj = listenerObj;
	}
	
	public ReDSeTModifyListener(EObject obj, Composite view, Composite text){
		this.obj = obj;
		this.view = view;
		this.text = text;
	}
	
	@Override
	public void modifyText(ModifyEvent e) {
		if (view instanceof UseCaseTestScenarioView)
			((UseCaseTestScenarioView)view).getEditor().setDirty(true);
		if (view instanceof TestCaseView)
			((TestCaseView)view).getEditor().setDirty(true);
		
		if (obj instanceof DirectObject){
			if (view instanceof UseCaseTestScenarioView);
				//((UseCaseTestScenarioView)view).getModifiedElementsMap();
			//if (view instanceof TestCaseView)
			//	((TestCaseView)view).getModifiedElementsMap().put((DirectObject)obj, ((StyledText)text).getText());

		} else if (obj instanceof IndirectObject){
			if (view instanceof UseCaseTestScenarioView);
			//((UseCaseTestScenarioView)view).getModifiedElementsMap();
			//if (view instanceof TestCaseView)
			//((TestCaseView)view).getModifiedElementsMap().put((IndirectObject)obj, ((StyledText)text).getText());
		} 

	}
}
