package eu.remics.alp.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public abstract class AbstractAssignmentControl extends Composite {
	
	protected ImportALPWizardSensesPage page;
	protected Object[] notionNames;
	
	public AbstractAssignmentControl(Composite parent, Object[] notionNames, ImportALPWizardSensesPage page) {
		super(parent, SWT.NONE);
		this.notionNames = notionNames;
		this.page = page;
	}
	
	/**
	 * Validates what is in the table
	 * @return true if all elements are valid (complete)
	 */
	public abstract boolean validateItems();
	
	/**
	 * Refreshes the control data.
	 */
	public abstract void refresh();
	
	/**
	 * Getter for used notion names
	 * @return
	 */
	public abstract Object[] getNotionNames();

	/**
	 * Setter for used notion names
	 * @param notionNames
	 */
	public abstract void setNotionNames(Object[] notionNames);

}
