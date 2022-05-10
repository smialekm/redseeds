package eu.remics.alp.ui;

import org.eclipse.swt.widgets.Composite;

//import eu.redseeds.sc.terminology.model.RemoteJGWNL;

public class TerminologyControlFactory {
	
	/**
	 * Creates an instance of AssignmentControl subclass depending on terminology usage scenario 
	 * (full term senses or only terms' base forms).
	 * @param parent - parent control
	 * @param notionNames - names of notions used in the control (terms)
	 * @param page - wizard page on which control is placed
	 * @return An created instance.
	 */
	public static AbstractAssignmentControl getAssignmentControl(
			Composite parent, Object[] notionNames, ImportALPWizardSensesPage page) {
		
//		//TODO
//		if(RemoteJGWNL.getInstance().isConnected()) {
//			return new SensesAssignmentControl(parent, notionNames, page);
//		}
//		else {
//			return new BaseFormAssignmentControl(parent, notionNames, page);
//		}
		
		return new SensesAssignmentControl(parent, notionNames, page);
//		return new BaseFormAssignmentControl(parent, notionNames, page);
	}

}
