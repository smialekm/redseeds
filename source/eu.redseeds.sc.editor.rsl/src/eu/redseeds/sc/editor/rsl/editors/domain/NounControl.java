package eu.redseeds.sc.editor.rsl.editors.domain;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.redseeds.sc.editor.rsl.editors.domain.controls.TerminologyWidget;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;

public class NounControl extends Composite{

	private TermDTO term;
	

	
	public NounControl(Composite parent, TermDTO term) {
		super(parent, SWT.None);
		this.term = term;
		createContent();
	}
	

	private void createContent(){
		
		TermDTO initialTerm = null;

		final TerminologyWidget tw = new TerminologyWidget(this, initialTerm);
		tw.setBounds(10, 60, 650, 165);
		tw.setPhrase(null);
		tw.setTerm(term);
		tw.setEnabled(true);
		tw.refresh();	
	}
	

	

	
}
