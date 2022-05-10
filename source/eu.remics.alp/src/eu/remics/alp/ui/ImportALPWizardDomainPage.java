package eu.remics.alp.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import eu.remics.alp.PatternSlice;

/**
 * ALP import wizard page for domain instantiation
 * @author aambroziewicz
 *
 */
public class ImportALPWizardDomainPage extends WizardPage {
	
	protected Composite container;
	protected DomainInstantiationControl control;
	
	PatternSlice pSlice;

	public PatternSlice getpSlice() {
		return pSlice;
	}

	public void setpSlice(PatternSlice pSlice) {
		this.pSlice = pSlice;
		control.refresh(pSlice);
	}

	protected ImportALPWizardDomainPage(String pageName) {
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
		layout.numColumns = 1;
		control = new DomainInstantiationControl(container, this, getpSlice(), (ImportALPWizard)getWizard());
		setControl(container);
		
		updateStatus(null);
		setPageComplete(false);
	}
	
	protected void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
	public Object[] getItems() {
		if(control != null) {
			return control.getItems();
		}
		return new Object[0];
	}
	
	public List<Object[]> getDomainInstantiationInfo() {
		List<Object[]> result = new ArrayList<Object[]>();
		if(getItems() == null) {
			return result;
		}
		for(Object item : getItems()) {
			result.add((Object[])item);
		}
		return result;
	}
	
	public void setItems(Object[] items) {
		if(control != null) {
			control.setItems(items);
		}
	}
	
	@Override
	public boolean isPageComplete() {
		return control.validateItems();
	}

}
