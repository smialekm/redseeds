package eu.redseeds.scl.model.impl.rsl.rsldomainelements.terms;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.common.Constants;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.impl.rsl.rsldomainelements.terms.NounImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;


public class NounDTOImpl extends NounImpl implements NounDTO {

	@Override
	public String toString() {		
		return super.getName()==null?"":super.getName();
	}

	public NounDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setName(String name){
		super.setName(null!=name?name.replace('_', ' ').trim():null);
	}
	
	public boolean equals(NounDTO noun){
		
		if (this != null && noun != null){
			if (this.getName() == null || noun.getName() == null){
				return false;
			}
			List<TermSenseDTO> thisSenses = new ArrayList<TermSenseDTO>();
			List<TermSenseDTO> thatSenses = new ArrayList<TermSenseDTO>();
			
			TermSenseDTO thisSense = 
				RemoteJGWNL.getInstance().getTermSenseDTO(this.getSynonymUid());
			TermSenseDTO thatSense = 
				RemoteJGWNL.getInstance().getTermSenseDTO(noun.getSynonymUid());
			
			if (thisSense == null){
				TermSenseDTO[] senses =  
					RemoteJGWNL.getInstance().getTermSenses(this.getName().toLowerCase(), Constants.TERM_NOUN);
				for (TermSenseDTO s : senses){
					thisSenses.add(s);
				}
			} else {
				thisSenses.add(thisSense);
			}
			
			if (thatSense == null){
				TermSenseDTO[] senses =  
					RemoteJGWNL.getInstance().getTermSenses(noun.getName().toLowerCase(), Constants.TERM_NOUN);
				for (TermSenseDTO s : senses){
					thatSenses.add(s);
				}
			} else {
				thatSenses.add(thatSense);
			}
			
			boolean nounEquals = 0!=this.getSynonymUid() && 0!=noun.getSynonymUid() && this.getSynonymUid()==noun.getSynonymUid();
			for (TermSenseDTO thisS : thisSenses){
				for (TermSenseDTO thatS : thatSenses){	
					if (thisS.getUid() == thatS.getUid()){
						nounEquals = true;
					}
				}
			}
			return nounEquals;
		} else return false; 
	}

	@Override
	public boolean autoAssignSense() {
		TermSenseDTO[] senses = RemoteJGWNL.getInstance().getTermSenses(this.name.toLowerCase(), Constants.TERM_NOUN);
		if (senses.length > 0){
			this.setSynonymUid(senses[0].getUid());
			this.setName(senses[0].getLemma());
			((BusinessLayerFacade)this.getGraph()).cleanNouns(this);
			return true;
		} else
			return false;
	}

	@Override
	public void autoAddAndAssignSense() {
		if (!autoAssignSense()){
			this.setSynonymUid(RemoteJGWNL.getInstance().addNewSense(name, Constants.DEFAULT_GLOSS+" "+name, Constants.TERM_NOUN, true));
			RemoteJGWNL.getInstance().connect(Constants.getJGWNLAddress());
			this.setName(RemoteJGWNL.getInstance().getTermSenseDTO(getSynonymUid()).getLemma());
			((BusinessLayerFacade)this.getGraph()).cleanNouns(this);
		}
	}
	
	@Override
	public boolean isConsistent() {
		TermSenseDTO[] senses = RemoteJGWNL.getInstance()
		.getTermSenses(getName(),
				Constants.TERM_NOUN);
		for (TermSenseDTO s : senses) if (s.getUid()==getSynonymUid()) return true;
		return false;
	}

	@Override
	public TermDTO getTerm() {
		return this;
	}

}