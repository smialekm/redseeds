package eu.remics.recovery.manager.presentation;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

public class VUseCaseNameInputDialog {

	public String display(String selectedScenarioName) {
		InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(),
	            "Use Case Name", "Enter Use Case name for script: " + selectedScenarioName + ". If empty, use case will have script name", selectedScenarioName, null);
		if(dlg.open() == Window.OK){
			if(!dlg.getValue().equals("")){
				return dlg.getValue();
			}
			else{
				return selectedScenarioName;
			}
		}
		return null;
	}
	
	public String display() {
		InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(),
	            "Use Case Name", "Enter Use Case name for scenario: ", "", null);
		if(dlg.open() == Window.OK){
			if(!dlg.getValue().equals("")){
				return dlg.getValue();
			}
			else{
				return "Merged Use Case";
			}
		}
		return null;
	}
	
	public String displayForUnsplitted(String initialName) {
		InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(),
	            "Scenario Name", "Enter name for newly created joined scenario: ", initialName.substring(1), null);
		if(dlg.open() == Window.OK){
			if(!dlg.getValue().equals("")){
				return dlg.getValue();
			}
			else{
				return initialName;
			}
		}
		return null;
	}
	
	public String displayForPartiallyMerge(String initialName) {
		InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(),
	            "Use Case Name", "Change name for newly merged use case. If empty new use case will have default name.", initialName, null);
		if(dlg.open() == Window.OK){
			if(!dlg.getValue().equals("")){
				return dlg.getValue();
			}
			else{
				return initialName;
			}
		}
		else{
			return null;
		}
	}
}
