package eu.redseeds.sc.query.engine.ukocompare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.query.comparefunctions.AbstractCompareFunction;
import eu.redseeds.sc.query.comparefunctions.RemoteNodesSimilarityLi;
import eu.redseeds.sc.query.comparefunctions.ScenarioSimilarityFunctionLi;
import eu.redseeds.sc.query.comparefunctions.TermSimilarityFunction;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementContainsStatement;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ComplexVerbPhraseContainsSimpleVerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.DeterminerIsPartOfSource;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.DeterminerLinkLinksToTarget;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ModifierIsPartOfSource;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ModifierLinkLinksToTarget;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounIsPartOfSource;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounLinkLinksToTarget;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.PhrasePrepositionLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.PhraseVerbLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.PrepositionIsPartOfSource;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.PrepositionLinkLinksToTarget;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.VerbIsPartOfSource;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.VerbLinkLinksToTarget;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.VerbPhraseContainsObject;
import eu.redseeds.scl.rsl.rslkernel.elements.SentenceListContainsSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.SentenceList;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinedSentenceLinksToRejoin;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ModalVerbLink;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ModalVerbLinkLinksToTarget;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.PredicateContainsTarget;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.PredicateIsPartOfSource;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SubjectContainsTarget;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SubjectIsPartOfSource;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.InvokedLinksToTarget;
import eu.redseeds.scl.rsl.rslrequirements.usecaserelationships.InvocationLinksToInvokeDescription;
import eu.redseeds.scl.rsl.rslrequirements.usecaserelationships.InvocationRelationship;

public abstract class AbstractCompareConfiguration implements CompareConfiguration {
	
	Map<Class<? extends Vertex>, List<CompareConfigurationEntry>> configurations;
	
	public AbstractCompareConfiguration() {
		configurations = new HashMap<Class<? extends Vertex>, List<CompareConfigurationEntry>>();
		init();
	}
	
	@Override
	public int size() {
		return configurations.size();
	}
	
	public Iterable<Class<? extends Vertex>> getConfiguredClasses() {
		return configurations.keySet();
	}
	
	@Override
	public Iterable<CompareConfigurationEntry> getConfigurations(Class<? extends Vertex> vc) {
		return configurations.get(vc);
	}
	
	protected void addConfigurationEntry(Class<? extends Vertex> vc, AbstractCompareFunction functionToUse, double weight) {
		List<CompareConfigurationEntry> list = configurations.get(vc);
		if (list == null) {
			list = new ArrayList<CompareConfigurationEntry>(1);
			configurations.put(vc, list);
		}
		list.add(new CompareConfigurationEntry(functionToUse, weight));
	}
	
	protected void replaceConfigurationEntry(Class<? extends Vertex> vc, AbstractCompareFunction functionToUse, double weight) {
		List<CompareConfigurationEntry> list = new ArrayList<CompareConfigurationEntry>(1);
		configurations.put(vc, list);
		list.add(new CompareConfigurationEntry(functionToUse, weight));
	}
	
	
	private void init() {
		/* Article */
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.terms.Article.class,
				             new TermSimilarityFunction(), 1.0);

		/* ConditionalConjunction*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.terms.ConditionalConjunction.class,
							  new TermSimilarityFunction(), 1.0);

		/* Determiner */
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.terms.Determiner.class,
				  new TermSimilarityFunction(), 1.0);
		
		/* ModalVerb*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.terms.ModalVerb.class,
				  new TermSimilarityFunction(), 1.0);
		
		/* Modifier */
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.terms.Modifier.class,
				  new TermSimilarityFunction(), 1.0);

		/* Noun */
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.terms.Noun.class,
				  new TermSimilarityFunction(), 1.0);
		
		/* Preposition */
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.terms.Preposition.class,
				  new TermSimilarityFunction(), 1.0);
		
		/* Quantifier */
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.terms.Quantifier.class,
				  new TermSimilarityFunction(), 1.0);

		/* Verb */ 
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.terms.Verb.class,
				  new TermSimilarityFunction(), 1.0);
	
		/* Attribute */ 
		addConfigurationEntry(eu.redseeds.scl.rsl.rslkernel.attributes.Attribute.class,
				  new TermSimilarityFunction(), 1.0);

		/* NounPhrase*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase.class,
				new RemoteNodesSimilarityLi(NounIsPartOfSource.class, EdgeDirection.IN), 0.7);
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase.class,
				new RemoteNodesSimilarityLi(ModifierIsPartOfSource.class, EdgeDirection.IN), 0.2);
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase.class,
				new RemoteNodesSimilarityLi(DeterminerIsPartOfSource.class, EdgeDirection.IN), 0.1);

	
		/* NounLink*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.phrases.NounLink.class,
				new RemoteNodesSimilarityLi(NounLinkLinksToTarget.class, EdgeDirection.OUT), 1.0);


		/* DeterminerLink*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.phrases.DeterminerLink.class,
				new RemoteNodesSimilarityLi(DeterminerLinkLinksToTarget.class, EdgeDirection.OUT), 1.0);
		

	
		/* ModifierLink*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.phrases.ModifierLink.class,
				new RemoteNodesSimilarityLi(ModifierLinkLinksToTarget.class, EdgeDirection.OUT), 1.0);
	
		/* ModalVerbLink*/
		addConfigurationEntry(ModalVerbLink.class,
				new RemoteNodesSimilarityLi(ModalVerbLinkLinksToTarget.class, EdgeDirection.OUT), 1.0);

	
		/* PrepositionLink*/		
		addConfigurationEntry(PhrasePrepositionLink.class,
				new RemoteNodesSimilarityLi(PrepositionLinkLinksToTarget.class, EdgeDirection.OUT), 1.0);	


		/* VerbLink*/
		addConfigurationEntry(PhraseVerbLink.class,
				new RemoteNodesSimilarityLi(VerbLinkLinksToTarget.class, EdgeDirection.OUT), 1.0);
		

		/* ComplexVerbPhrase*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.phrases.ComplexVerbPhrase.class,
				new RemoteNodesSimilarityLi(ComplexVerbPhraseContainsSimpleVerbPhrase.class, EdgeDirection.OUT), 0.65);
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.phrases.ComplexVerbPhrase.class,
				new RemoteNodesSimilarityLi(VerbPhraseContainsObject.class, EdgeDirection.OUT), 0.3);
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.phrases.ComplexVerbPhrase.class,
				new RemoteNodesSimilarityLi(PrepositionIsPartOfSource.class, EdgeDirection.IN), 0.05);
		

		/* SimpleVerbPhrase*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.phrases.SimpleVerbPhrase.class,
				new RemoteNodesSimilarityLi(VerbPhraseContainsObject.class, EdgeDirection.OUT), 0.45);
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.phrases.SimpleVerbPhrase.class,
				new RemoteNodesSimilarityLi(VerbIsPartOfSource.class, EdgeDirection.IN), 0.5);
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.phrases.SimpleVerbPhrase.class,
				new RemoteNodesSimilarityLi(PrepositionIsPartOfSource.class, EdgeDirection.IN), 0.05);
	
		/* Subject */
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.Subject.class,
				new RemoteNodesSimilarityLi(SubjectContainsTarget.class, EdgeDirection.OUT), 1.0);
		
		/* Predicate */
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.Predicate.class,
				new RemoteNodesSimilarityLi(PredicateContainsTarget.class, EdgeDirection.OUT), 1.0);
		
		
		/* SVOSentence*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence.class,
				new RemoteNodesSimilarityLi(SubjectIsPartOfSource.class, EdgeDirection.IN), 0.2);
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence.class,
				new RemoteNodesSimilarityLi(PredicateIsPartOfSource.class, EdgeDirection.IN), 0.8);
				
		/* RejoinSentence*/
		addConfigurationEntry(RejoinSentence.class,
				new RemoteNodesSimilarityLi(RejoinedSentenceLinksToRejoin.class, EdgeDirection.IN), 1.0);
		
		/* InvocationSentence*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentence.class,
				new RemoteNodesSimilarityLi(InvocationLinksToInvokeDescription.class, EdgeDirection.IN), 1.0);

		/* InvocationRelationship*/
		addConfigurationEntry(InvocationRelationship.class,
				new RemoteNodesSimilarityLi(InvokedLinksToTarget.class, EdgeDirection.OUT), 1.0);
		

		/* DomainElementRepresentation*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.domainelementrepresentations.DomainElementRepresentation.class,
				new RemoteNodesSimilarityLi(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.representationsentences.RepresentationContainsSentence.class, EdgeDirection.OUT), 1.0);


		/* Notion*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.notions.Notion.class,
				new RemoteNodesSimilarityLi(DomainElementContainsStatement.class, EdgeDirection.OUT), 0.5);
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.notions.Notion.class,
				new RemoteNodesSimilarityLi(eu.redseeds.scl.rsl.rsldomainelements.notions.NotionContainsName.class, EdgeDirection.OUT), 0.5);


		/* DomainStatement*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.notions.DomainStatement.class,
				new RemoteNodesSimilarityLi(eu.redseeds.scl.rsl.rsldomainelements.notions.StatementContainsName.class, EdgeDirection.OUT), 1.0);

		/* Actor*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.actors.Actor.class,
				new RemoteNodesSimilarityLi(eu.redseeds.scl.rsl.rsldomainelements.domainelements.ElementContainsName.class, EdgeDirection.OUT), 1.0);


		/* SystemElement */
		addConfigurationEntry(eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElement.class,
				new RemoteNodesSimilarityLi(eu.redseeds.scl.rsl.rsldomainelements.domainelements.ElementContainsName.class, EdgeDirection.OUT), 1.0);

		List<Class<? extends Vertex>> allowedElements = new ArrayList<Class<? extends Vertex>>();
		allowedElements.add(SentenceList.class);
		/* Requirement */
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement.class,
				new RemoteNodesSimilarityLi(eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementContainsName.class, EdgeDirection.OUT), 0.1);
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement.class,
				new RemoteNodesSimilarityLi(eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementContainsRepresentation.class, EdgeDirection.OUT, allowedElements, 1.0), 0.9);
	
		allowedElements = new ArrayList<Class<? extends Vertex>>();
		allowedElements.add(ConstrainedLanguageScenario.class);
		allowedElements.add(SentenceList.class);
		/* UseCase */
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase.class,
				new RemoteNodesSimilarityLi(eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementContainsName.class, EdgeDirection.OUT), 0.1);
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase.class,
				new RemoteNodesSimilarityLi(eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementContainsRepresentation.class, EdgeDirection.OUT, allowedElements, 1.0), 0.9);
		
		/* Scenario */
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario.class,
				new ScenarioSimilarityFunctionLi(), 1.0);
		
		/* SentenceList */
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.SentenceList.class,
				new RemoteNodesSimilarityLi(SentenceListContainsSentence.class, EdgeDirection.OUT), 1.0);

	}

}
