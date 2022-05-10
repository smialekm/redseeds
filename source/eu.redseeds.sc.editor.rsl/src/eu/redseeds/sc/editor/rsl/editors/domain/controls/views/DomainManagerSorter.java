package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.sclkernel.SCLElement;

public class DomainManagerSorter extends ViewerComparator{
	
	//categories for sorting, 0 - default, sorting by categories uses categories in ascending order
	public static int CATEGORY_PHRASE_NAME = 1;
	public static int CATEGORY_PHRASE_TYPE = 2;
	public static int CATEGORY_PHRASE_ASSIGMENT = 3;
	public static int CATEGORY_NOUN_NAME = 4;
	public static int CATEGORY_NOUN_SENSE = 5;
	public static int CATEGORY_CLIPBOARD = 6;
	
	private int category = 0;
	
	

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		int dir=0;
		int result=0;
		dir=((TableViewer)viewer).getTable().getSortDirection();
		
		if (category == CATEGORY_PHRASE_NAME){
			PhraseDTO ph1 = (PhraseDTO) e1;
			PhraseDTO ph2 = (PhraseDTO) e2;
			if (dir==SWT.DOWN){
				result=(-1)*ph1.toString().compareTo(ph2.toString());
			}
			else if (dir==SWT.UP){
				result=ph1.toString().compareTo(ph2.toString());
			}
			return result;
		}
		
		if (category == CATEGORY_PHRASE_TYPE){
			String type1="";
			String type2="";
			if (e1 instanceof NounPhraseDTO)
				type1 = "NP";
			else if (e1 instanceof SimpleVerbPhraseDTO)
				type1 = "VPS";
			else if (e1 instanceof ComplexVerbPhraseDTO)
				type1 = "VPC";
			
			if (e2 instanceof NounPhraseDTO)
				type2 = "NP";
			else if (e2 instanceof SimpleVerbPhraseDTO)
				type2 = "VPS";
			else if (e2 instanceof ComplexVerbPhraseDTO)
				type2 = "VPC";
			
			if (dir==SWT.DOWN){
				result=(-1)*type1.compareTo(type2.toString());
			}
			else if (dir==SWT.UP){
				result=type1.compareTo(type2.toString());
			}
			return result;
		}
		
		if (category == CATEGORY_PHRASE_ASSIGMENT){
			boolean hasSenses1 = ((PhraseDTO) e1).hasSenses();
			boolean hasSenses2 = ((PhraseDTO) e2).hasSenses();
			
			if (dir==SWT.DOWN){
				if (hasSenses1 && !hasSenses2)
					result=-1;
				if (!hasSenses1 && hasSenses2)
					result=1;
			}
			else if (dir==SWT.UP){
				if (hasSenses1 && !hasSenses2)
					result=1;
				if (!hasSenses1 && hasSenses2)
					result=-1;
			} else 
				result = 0;			
			return result;
		}
		
		if (category == CATEGORY_NOUN_NAME){
			NounDTO n1 = (NounDTO) e1;
			NounDTO n2 = (NounDTO) e2;
			if (dir==SWT.DOWN){
				result=(-1)*n1.getName().compareTo(n2.getName());
			}
			else if (dir==SWT.UP){
				result=n1.getName().compareTo(n2.getName());
			}
			return result;
		}
		
		if (category == CATEGORY_CLIPBOARD){
			BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject((SCLElement)e1).getBusinessLayerFacade();
			ClipboardDTO clip1 = facade.whichClipboardMember((SCLElement)e1);
			ClipboardDTO clip2 = facade.whichClipboardMember((SCLElement)e2);
			String n1 = "";
			String n2 = "";
			if (clip1 != null)
				n1 = clip1.getName();
			if (clip2 != null)
				n2 = clip2.getName();
			if (dir==SWT.DOWN){
				result=(-1)*n1.compareTo(n2);
			}
			else if (dir==SWT.UP){
				result=n1.compareTo(n2);
			}
			return result;
		}
		
		if (category == CATEGORY_NOUN_SENSE){
			NounDTO n1 = (NounDTO) e1;
			NounDTO n2 = (NounDTO) e2;
			
			String sense1 = "";
			String sense2 = "";
			
			if (RemoteJGWNL.getInstance().getTermSenseDTO(((NounDTO)n1).getSynonymUid()) == null){
				sense1 = "---";
			} else{
				sense1 = RemoteJGWNL.getInstance().getTermSenseDTO(((NounDTO)n1).getSynonymUid()).getSense();
			}
			
			if (RemoteJGWNL.getInstance().getTermSenseDTO(((NounDTO)n2).getSynonymUid()) == null){
				sense2 = "---";
			} else{
				sense2 = RemoteJGWNL.getInstance().getTermSenseDTO(((NounDTO)n2).getSynonymUid()).getSense();
			}
			
			
			if (dir==SWT.DOWN){
				result=(-1)*sense1.compareTo(sense2);
			}
			else if (dir==SWT.UP){
				result=sense1.compareTo(sense2);
			}
			return result;
		}
		
		return 0;
	}
	
	@Override
	public boolean isSorterProperty(Object element, String property) {
        return true;
    }

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

}
