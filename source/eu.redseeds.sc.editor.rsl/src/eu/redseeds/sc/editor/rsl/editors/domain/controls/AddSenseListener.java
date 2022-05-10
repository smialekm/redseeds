package eu.redseeds.sc.editor.rsl.editors.domain.controls;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.redseeds.sc.editor.rsl.editors.AddSenseDialog;
import eu.redseeds.sc.editor.rsl.editors.AddWordDialog;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;


public class AddSenseListener implements SelectionListener {

	private TerminologyWidget termWidget;

	public AddSenseListener(TerminologyWidget termWidget){
		this.termWidget = termWidget;
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		
		TermDTO term = termWidget.getTerm();
		//TermSenseDTO termSense = (TermSenseDTO)selectedItem.getData();
		//System.out.println("> " + term);
		
		Table table = termWidget.getTable();
//		if(table.getSelectionIndex()<0) {
//			return;
//		}
//		TableItem selectedItem = table.getItem(table.getSelectionIndex());
//		if(selectedItem.getData() == null) {
//			return;
//		}
		
		TableItem[] items = table.getItems();
		
		if (items.length > 0) { // open AddSenseDialog
			List<TermSenseDTO> senses = new ArrayList<TermSenseDTO>();
			for (TableItem o : items) {
				if (o.getData() instanceof TermSenseDTO)
					senses.add((TermSenseDTO)o.getData());
			}
			AddSenseDialog addSenseDlg = new AddSenseDialog();
			addSenseDlg.setSenses(senses);
			addSenseDlg.open();
		}
		else { // open AddWordDialog
			AddWordDialog addWordDlg = new AddWordDialog();
			addWordDlg.setWord(null!=term?term.getName():"");
			addWordDlg.open();
		}

		
//		AssignSenseAction action = new AssignSenseAction(term, termSense, phrase, parent);
//		action.run();

		termWidget.refresh();		
	}
		
	
}
