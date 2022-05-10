package eu.redseeds.sc.query.engine.ukocompare;

import java.util.ArrayList;

import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.DeterminerLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ModifierLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.PhrasePrepositionLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.PhraseVerbLink;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ConditionalConjunctionLink;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ModalVerbLink;
import eu.redseeds.scl.rsl.rslrequirements.usecaserelationships.InvocationRelationship;

public class CompareSequence extends ArrayList<Class<? extends Vertex>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6738936171827812089L;

	CompareSequence() {
		super();
		/* Terms */
		add(eu.redseeds.scl.rsl.rsldomainelements.terms.Article.class);
		add(eu.redseeds.scl.rsl.rsldomainelements.terms.ConditionalConjunction.class);
		add(eu.redseeds.scl.rsl.rsldomainelements.terms.Determiner.class);
		add(eu.redseeds.scl.rsl.rsldomainelements.terms.ModalVerb.class);
		add(eu.redseeds.scl.rsl.rsldomainelements.terms.Modifier.class);
		add(eu.redseeds.scl.rsl.rsldomainelements.terms.Noun.class);
		add(eu.redseeds.scl.rsl.rsldomainelements.terms.Preposition.class);
		add(eu.redseeds.scl.rsl.rsldomainelements.terms.Quantifier.class);
		add(eu.redseeds.scl.rsl.rsldomainelements.terms.Verb.class);
			
		/* Term Links */
		add(ConditionalConjunctionLink.class);
		add(DeterminerLink.class);
		add(ModalVerbLink.class);
		add(ModifierLink.class);
		add(NounLink.class);
		add(PhrasePrepositionLink.class);
		add(PhraseVerbLink.class);
	    	    
	    /* Match phrases that are based on terms   */
	    add(eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase.class);
	    add(eu.redseeds.scl.rsl.rsldomainelements.phrases.SimpleVerbPhrase.class);
	    add(eu.redseeds.scl.rsl.rsldomainelements.phrases.ComplexVerbPhrase.class);
		
		/* Match subject and predicate */
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.Subject.class);
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.Predicate.class);

	    /* Match sentences   */		
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence.class);
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ModalSVOSentence.class);
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ConditionalSentence.class);
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentence.class);
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentence.class);
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentence.class);

	    add(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentence.class);
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentence.class);

	    /* Match invocation sentence */
	    add(InvocationRelationship.class);
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentence.class);
	    
	    /* Domain Elements */
	    add(eu.redseeds.scl.rsl.rsldomainelements.notions.DomainStatement.class);
	    add(eu.redseeds.scl.rsl.rsldomainelements.notions.Notion.class);
	    add(eu.redseeds.scl.rsl.rsldomainelements.actors.Actor.class);
	    add(eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElement.class);

		
		/* Match sentence lists   */
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.SentenceList.class);

	    /* Match scenarios */
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario.class);

	    /* Match usecase and requirement */
	    add(eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement.class);
	    
	    /* Match invocation sentences the second time. Their value can change because of the
	     * (temporary) similarities calculated for the use-cases they refer to */
	    add(InvocationRelationship.class);
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentence.class);
	    
	    /* Match scenarios, usecase and requirement the second time. Since the similarity of the
	     * InvocationSentences has probably changed the similarity of the scenarios containing
	     * them and the requirements and use-cases containing the scenarios may also change  */
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario.class);
   	    add(eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement.class);
	    
	    /* Match invocation sentence the third and last time*/
	    add(InvocationRelationship.class);
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentence.class);
	    
	    /* Match scenarios, usecase and requirement the third and last time */
	    add(eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario.class);
  	    add(eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement.class);
	}
	
}
