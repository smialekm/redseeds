package eu.redseeds.scl.model.sclkernel;

import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Term;
import eu.redseeds.scl.sclkernel.SCLElement;


public interface ClipboardDTO {
	
	RequirementsSpecificationDTO getRequirementsSpecificationDTO();
	
	ArchitecturalModelDTO getArchitecturalModelDTO();
	
	DetailedDesignModelDTO getDetailedDesignModelDTO(); 
	
	public void setName(String name);
	
	public String getName();
	
	public void delete();
	
	public SoftwareCaseDTO getParentSC();
	
	/**
	 * Checks if the given SCLElement <code>possibleElement</code> is a member of this clipboard
	 * @param possibleElement the element to test for membership in this clipboard
	 * @return true if <code>possibleElement</code> is a member of this clipboard, false otherwise
	 */
	public boolean isClipboardMember(SCLElement possibleElement);
	
//	/**
//	 * Checks if the given term <code>term</code> is a member of this clipboard
//	 * @param term the term to test for membership in this clipboard
//	 * @return true if <code>term</code> is a member of this clipboard, false otherwise
//	 */
//	public boolean isTermAClipboardMember(TermDTO term);

	
	/**
	 * Retrieves a list of Term that are used exclusively in this clipboard, i.e. they are
	 * used in this clipboard and they are not used in the case itself or in any other 
	 * clipboard of this case
	 * @return the list of exclusively used terms
	 */
	public List<Term> getExclusivelyUsedTerms();
}