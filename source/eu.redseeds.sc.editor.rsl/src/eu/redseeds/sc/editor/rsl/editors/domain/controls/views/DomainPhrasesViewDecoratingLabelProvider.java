package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;

public class DomainPhrasesViewDecoratingLabelProvider extends DecoratingLabelProvider implements
ITableLabelProvider{

	public DomainPhrasesViewDecoratingLabelProvider(ILabelProvider provider,
			ILabelDecorator decorator) {
		super(provider, decorator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		if(!(element instanceof PhraseDTO)) {
			return result;
		}
		switch (columnIndex) {
			case 0 :
				if (element instanceof NounPhraseDTO)
					result = "NP";
				else if (element instanceof SimpleVerbPhraseDTO)
					result = "VPS";
				else if (element instanceof ComplexVerbPhraseDTO)
					result = "VPC";
				else
					result = "---";
				break;
			case 1 :
				if ((((PhraseDTO)element)).hasSenses()){
					result = "yes";
				} else result = "no";
				break;
			case 2 :
				BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(element).getBusinessLayerFacade();
				ClipboardDTO clip =  facade.whichClipboardMember(element);
				if (clip != null){
					result = clip.getName();
				} else
					result = "";
				break;
			case 3 :
				result = ((PhraseDTO)element).toString();
				break;
			default:
				result = "--TODO--";
				break;
		}
		return result;
	}
	
	@Override
	public Color getBackground(Object element) {
		if (element instanceof PhraseDTO){
			if (!((PhraseDTO)element).hasSenses())
				return new Color(null, 255, 100, 100);
		}
		
		return new Color(null, 255, 255, 255);
	}
	
}
