package eu.redseeds.sc.editor.rsl.editors.domain.controls;


import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.redseeds.sc.editor.rsl.actions.AssignSenseAction;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;


public class AssignSenseListener implements SelectionListener {

	private TerminologyWidget termWidget;
	private PhraseDTO phrase;

	public AssignSenseListener(TerminologyWidget termWidget){
		this.termWidget = termWidget;
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		
		Table table = termWidget.getTable();
		if(table.getSelectionIndex()<0) {
			return;
		}
		TableItem selectedItem = table.getItem(table.getSelectionIndex());
		if(selectedItem.getData() == null) {
			return;
		}
		TermDTO term = termWidget.getTerm();
		TermSenseDTO termSense = (TermSenseDTO)selectedItem.getData();
		Composite parent = termWidget.getParent();
		phrase = termWidget.getPhrase();
		AssignSenseAction action = new AssignSenseAction(term, termSense, phrase, parent);
		action.run();
		
		termWidget.refresh();		
	}
		
	
}
