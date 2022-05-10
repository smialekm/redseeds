package eu.redseeds.sc.merging.conflicts;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class MergeCheckTerm implements IMergeCheck {

	@Override
	public MergeConflictObject[] check(Object element, SoftwareCaseDTO targetSC) {
		List<MergeConflictObject> conflicts = new ArrayList<MergeConflictObject>();
		if (element instanceof TermDTO) {
			TermDTO term = (TermDTO) element;
			BusinessLayerFacade target 
				= SCProjectContainer.instance()
					.getSCProject(targetSC).getBusinessLayerFacade();
			//check the meaning
			for(TermDTO otherTerm : target.getAllTerms()) {
				if(term.getName().equalsIgnoreCase(otherTerm.getName()) && term.getSynonymUid() != otherTerm.getSynonymUid()) {//same names, diff meanings
					if(!target.isAnyClipboardMember(otherTerm)) {//is in SC
						conflicts.add( 
						        MergeConflictFactory.createMergeConflictObject(
						        		otherTerm, term, MergeConstants.MERGE_CONFLICT_MEANING_TERM_ID));
					}
				}
			}
		}
		return (MergeConflictObject[])conflicts.toArray(new MergeConflictObject[conflicts.size()]);
	}

}
