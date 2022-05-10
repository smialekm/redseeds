package eu.redseeds.scl.model.impl.rsl.rsldomainelements.terms;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.common.Constants;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.impl.rsl.rsldomainelements.terms.DeterminerImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.DeterminerDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;


public class DeterminerDTOImpl extends DeterminerImpl implements DeterminerDTO {

	public DeterminerDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setName(String name){
		if(name!=null) {
			super.setName(name.trim());
		}
		else {
			super.setName(name);
		}
	}
	
	@Override
	public String toString() {		
		return super.getName()==null?"":super.getName();
	}
	
	@Override
	public boolean autoAssignSense() {
		TermSenseDTO[] senses = RemoteJGWNL.getInstance().getTermSenses(this.name.toLowerCase(), Constants.TERM_DETERMINER);
		if (senses.length > 0){
			this.setSynonymUid(senses[0].getUid());
			return true;
		} else
			return false;
	}

	@Override
	public void autoAddAndAssignSense() {
		if (!autoAssignSense()){
			this.setSynonymUid(RemoteJGWNL.getInstance().addNewSense(name, Constants.DEFAULT_GLOSS+" "+name, Constants.TERM_DETERMINER, true));
			RemoteJGWNL.getInstance().connect(Constants.getJGWNLAddress());
		}
	}

	@Override
	public boolean isConsistent() {
		TermSenseDTO[] senses = RemoteJGWNL.getInstance()
		.getTermSenses(getName(),
				Constants.TERM_DETERMINER);
		for (TermSenseDTO s : senses) if (s.getUid()==getSynonymUid()) return true;
		return false;
	}

	@Override
	public TermDTO getTerm() {
		return this;
	}

}