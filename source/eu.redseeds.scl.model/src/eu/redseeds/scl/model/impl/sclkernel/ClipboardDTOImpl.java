package eu.redseeds.scl.model.impl.sclkernel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.greql2.evaluator.GreqlEvaluator;
import de.uni_koblenz.jgralab.greql2.jvalue.JValue;
import de.uni_koblenz.jgralab.greql2.jvalue.JValueSet;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.impl.sclkernel.ArchitecturalModelImpl;
import eu.redseeds.scl.impl.sclkernel.ClipboardImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTOImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.sclkernel.ArchitecturalModelDTO;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.model.sclkernel.DetailedDesignModelDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Term;
import eu.redseeds.scl.rsl.rsldomainelements.terms.TermHyperlink;
import eu.redseeds.scl.sclkernel.DetailedDesignModel;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.scl.sclkernel.SoftwareArtifact;


public class ClipboardDTOImpl extends ClipboardImpl implements ClipboardDTO {
	
	/*
	 * the set of members of this clipboard, computed as soon it is needed 
	 * and recomputed every time the graph has changed
	 */
	private Set<Vertex> clipboardMembers = null;
	
	/* the version of the graph for which the set of clipboard members was computed.
	 * it this version changes, the set of clipboard members needs to be computed
	 * again the next time it is needed
	 */
	private long graphVersion = 0;
	
	public ClipboardDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
	}
	
	@Override
	public RequirementsSpecificationDTO getRequirementsSpecificationDTO() {
		for(SoftwareArtifact artifact : super.getArtifactList()) {
			if(artifact instanceof RequirementsSpecificationDTOImpl){
				return (RequirementsSpecificationDTO)artifact;
			}
		}
		return null;
	}
	
	@Override
	public ArchitecturalModelDTO getArchitecturalModelDTO() {
		for(SoftwareArtifact artifact : super.getArtifactList()) {
			if(artifact instanceof ArchitecturalModelImpl){
				return (ArchitecturalModelDTO)artifact;
			}
		}
		return null;
	}	
	
	@Override
	public DetailedDesignModelDTO getDetailedDesignModelDTO() {
		for(SoftwareArtifact artifact : super.getArtifactList()) {
			if(artifact instanceof DetailedDesignModel){
				return (DetailedDesignModelDTO)artifact;
			}
		}
		return null;
	}

	@Override
	public SoftwareCaseDTO getParentSC() {
		if(getParentSoftwareCaseList() != null) {
			return (SoftwareCaseDTO)getParentSoftwareCaseList().get(0);
		}
		return null;
	}
	
	@Override
	public List<Term> getExclusivelyUsedTerms() {
		List<Term> termList = new ArrayList<Term>();
		for (Term v : ((SCLGraph) getGraph()).getTermVertices()) {
			if (termUsedExclusively(v))
				termList.add(v);
		}
		return termList;
	}

	/**
	 * Checks if the term <code>term</code> is used exclusively in this clipboard
	 * @param term to term to check for usage
	 * @return true if the term <code>term</code> is used in this clipboard and only in this clipboard,
	 * 		   false if the term is used also in other clipboards of this case, in the case itself 
	 *         or if it is not used in this clipboard
	 */
	private boolean termUsedExclusively(Term term) {
		boolean usedInClipboard = false;
		for (Edge e : term.getTermHyperlinkLinksToTargetIncidences(EdgeDirection.IN)) { 
			TermHyperlink t = (TermHyperlink) e.getThat();
			Edge sourceEdge = t.getFirstTermHyperlinkLinksToSource(EdgeDirection.OUT);
			if (sourceEdge != null) { // deal with broken graphs
				SCLElement linkingElement = (SCLElement) sourceEdge.getThat();
				if (isClipboardMember(linkingElement))
					usedInClipboard = true;
				else
					return false;
			}
		}
		return usedInClipboard;
	}
	
	@Override
	public void delete() {
		
		RequirementsSpecificationDTO reqSpec = getRequirementsSpecificationDTO(); 
		
		if(reqSpec != null) {
			for(RequirementsPackageDTO reqpack : reqSpec.getRequirementsPackagesDTOList()) {
				reqpack.deleteRecursively();
			}
			
			for(ActorsPackageDTO actPack : reqSpec.getDomainSpecificationDTO().getActorsPackageDTOList()) {
				actPack.deleteRecursively();
			}
			
			for(NotionsPackageDTO notPack : reqSpec.getDomainSpecificationDTO().getNotionsPackageDTOList()) {
				notPack.deleteRecursively();
			}
			
			for(SystemElementsPackageDTO selPack : reqSpec.getDomainSpecificationDTO().getSystemElementsPackageDTOList()) {
				selPack.deleteRecursively();
			}
		}
		
		ArchitecturalModelDTO arch = getArchitecturalModelDTO();
		
		if(arch != null) {
			for(PackageDTO pack : arch.getPackageDTOList()) {
				pack.deleteRecursively();
			}
		}
		
		DetailedDesignModelDTO design = getDetailedDesignModelDTO();
		
		if(design != null) {
			for(PackageDTO pack : design.getPackageDTOList()) {
				pack.deleteRecursively();
			}
		}
		
		super.delete();
	}
	
	private Set<Vertex> calculateClipboardElements() {
		Map<String, JValue> boundVars = new HashMap<String, JValue>();
		boundVars.put("clipboard", new JValue(this));
		try {
			GreqlEvaluator eval = new GreqlEvaluator(MEMBER_QUERY, this.getGraph(), boundVars);
			eval.startEvaluation(true);
			JValueSet resultSet = (JValueSet) eval.getEvaluationResult();
			Set<Vertex> memberElements = new HashSet<Vertex>();
			for (JValue val : resultSet) {
				memberElements.add(val.toVertex());
			}
			return memberElements;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}


	private static final String MEMBER_QUERY =
		"import sclkernel.*; " +
		"import rsl.rslkernel.elements.*; " +
		"import rsl.rslrequirements.requirementsspecifications.*; " +
		"import rsl.rslkernel.attributes.*; " +
		"import rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.*; " +
		
		"using clipboard : " +
		"  (--<>  " +
		"  | <--{RequirementsSpecificationLinksToDomainSpecification} " +
		"  | <--{HyperlinkLinksToTarget} " +
		"  | -->{HyperlinkLinksToSource}" +
		"  | <--{AttributeLinksToAttributeDefinition} " +
		"  | <--{RepresentableElementLinksToAttributeSet} " +
		" )+ clipboard ";
	
	
	@Override
	public boolean isClipboardMember(SCLElement possibleElement) {
		if (possibleElement == this)
			return true;
		if ((clipboardMembers == null) || (graphVersion != getGraph().getGraphVersion()))
			clipboardMembers = calculateClipboardElements();
		return clipboardMembers.contains(possibleElement);
	}

	
} 



