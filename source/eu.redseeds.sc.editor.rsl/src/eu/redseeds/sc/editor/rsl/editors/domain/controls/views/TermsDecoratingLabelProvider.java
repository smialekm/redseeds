package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;


import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;



public class TermsDecoratingLabelProvider extends DecoratingLabelProvider implements
ITableLabelProvider{

	private TermDTO term;
	
	public TermsDecoratingLabelProvider(ILabelProvider provider,
			ILabelDecorator decorator, TermDTO term) {
		super(provider, decorator);
		this.term = term;
	}
	
	public Color getBackground(Object element) {
		if (element instanceof TermSenseDTO && term!= null){
			TermSenseDTO termSense = (TermSenseDTO)element;
			if (termSense.getUid() == term.getSynonymUid())
				return new Color(null, 200, 200, 255);
		}
		
		return new Color(null, 255, 255, 255);
	}
	

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		if(!(element instanceof TermSenseDTO)) {
			return result;
		}
		TermSenseDTO termSense = (TermSenseDTO)element;
		switch (columnIndex) {
			case 0 :
				result = termSense.getType();
				break;
			case 1:
				result = termSense.getLemma();
				break;
			case 2:
				result = termSense.getSense();
				break;
			default:
				result = "--TODO--";
				break;
		}
		return result;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
}