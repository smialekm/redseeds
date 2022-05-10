package eu.remics.alp.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * ALP import wizard page for senses assignment 
 * @author aambroziewicz
 *
 */
public class ImportALPWizardSensesPage extends WizardPage {
	
	protected Composite container;
	protected AbstractAssignmentControl control;
	protected Object[] notionNames;

	protected ImportALPWizardSensesPage(String pageName) {
		super(pageName);
		//TODO
		setTitle(pageName);
		setDescription(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		control = TerminologyControlFactory.getAssignmentControl(container, notionNames, this);
			//new SensesAssignmentControl(container, notionNames, this);
		setControl(container);
		
		updateStatus(null);
		setPageComplete(false);
	}
	
	protected void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	public void setNotionNames(Object[] items) {
		notionNames = items;
		control.setNotionNames(items);
	}
	
	public List<Object[]> getDomainInstantiationInfo() {
		List<Object[]> result = new ArrayList<Object[]>();
		if(notionNames == null) {
			return result;
		}
		for(Object item : notionNames) {
			result.add((Object[])item);
		}
		return result;
	}
	
	@Override
	public boolean isPageComplete() {
		return control.validateItems();
	}
}
