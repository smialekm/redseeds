package eu.redseeds.scl.model.impl.rsl.rsldomainelements.terms;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.common.Constants;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.impl.rsl.rsldomainelements.terms.VerbImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;


public class VerbDTOImpl extends VerbImpl implements VerbDTO {

	public VerbDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setName(String name){
		super.setName(null!=name?name.trim():null);
	}

	@Override
	public String toString() {		
		return super.getName()==null?"":super.getName();
	}

//	@Override
//	public boolean isClipboardMember() {
//		Object att = null;
//		try {
//			att = getAttribute(IS_CLIPBOARD_MEMBER_ATTRIBUTE);
//		} catch (NoSuchFieldException e) {
//			// TODO: handle exception
//		}
//		if(att == null) {
//			return false;
//		}
//		System.out.println(IS_CLIPBOARD_MEMBER_ATTRIBUTE + " " + att);
//		return false;
//	}
//
//	@Override
//	public void setClipboardMember(boolean member) {
//		try {
//			setAttribute(IS_CLIPBOARD_MEMBER_ATTRIBUTE, member);
//		} catch (NoSuchFieldException e) {
//			// TODO: handle exception
//		}
//	}

	@Override
	public boolean autoAssignSense() {
		TermSenseDTO[] senses = RemoteJGWNL.getInstance().getTermSenses(this.name.toLowerCase(), Constants.TERM_VERB);
		if (senses.length > 0){
			this.setSynonymUid(senses[0].getUid());
			return true;
		} else
			return false;
	}

	@Override
	public void autoAddAndAssignSense() {
		if (!autoAssignSense()){
			this.setSynonymUid(RemoteJGWNL.getInstance().addNewSense(name, Constants.DEFAULT_GLOSS+" "+name, Constants.TERM_VERB, true));
			RemoteJGWNL.getInstance().connect(Constants.getJGWNLAddress());
		}
	}
	
	@Override
	public boolean isConsistent() {
		TermSenseDTO[] senses = RemoteJGWNL.getInstance()
		.getTermSenses(getName(),
				Constants.TERM_VERB);
		for (TermSenseDTO s : senses) if (s.getUid()==getSynonymUid()) return true;
		return false;
	}

	@Override
	public TermDTO getTerm() {
		return this;
	}

}