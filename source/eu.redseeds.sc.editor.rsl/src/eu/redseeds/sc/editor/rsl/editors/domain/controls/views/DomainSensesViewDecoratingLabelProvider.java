package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;

public class DomainSensesViewDecoratingLabelProvider extends DecoratingLabelProvider implements
ITableLabelProvider{

	public DomainSensesViewDecoratingLabelProvider(ILabelProvider provider,
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
			if(!(element instanceof NounDTO)) {
				return result;
			}
			switch (columnIndex) {
				case 0 :
					result = ((NounDTO)element).getName();
					break;
				case 1 :
					BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(element).getBusinessLayerFacade();
					ClipboardDTO clip =  facade.whichClipboardMember(element);
					if (clip != null){
						result = clip.getName();
					} else
						result = "";
					break;
				case 2:
					if (0==((NounDTO)element).getSynonymUid() || RemoteJGWNL.getInstance().getTermSenseDTO(((NounDTO)element).getSynonymUid()) == null){
						result = "---";
					} else{
						result = RemoteJGWNL.getInstance().getTermSenseDTO(((NounDTO)element).getSynonymUid()).getSense();
					}
					break;
				default:
					result = "--TODO--";
					break;
			}
			return result;
	}
	
	@Override
	public Color getBackground(Object element) {
		if (element instanceof NounDTO){
			if (0==((NounDTO)element).getSynonymUid() || RemoteJGWNL.getInstance().getTermSenseDTO(((NounDTO)element).getSynonymUid()) == null)
				return new Color(null, 255, 100, 100);
		}
		
		return new Color(null, 255, 255, 255);
	}

}
