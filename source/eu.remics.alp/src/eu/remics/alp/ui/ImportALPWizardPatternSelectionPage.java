package eu.remics.alp.ui;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;

/**
 * ALP import wizard page for pattern selection 
 * @author aambroziewicz
 *
 */
public class ImportALPWizardPatternSelectionPage extends WizardPage {
	
	protected Composite container;
	
	protected RequirementsPackageDTO selectedPattern;

	protected ImportALPWizardPatternSelectionPage(String pageName) {
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
		new PatternSelectionControl(container, this);
		setControl(container);
		
		updateStatus(null);
		setPageComplete(false);
	}
	
	protected void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	public RequirementsPackageDTO getSelectedPattern() {
		return selectedPattern;
	}

	public void setSelectedPattern(RequirementsPackageDTO selectedPattern) {
		this.selectedPattern = selectedPattern;
	}

}
