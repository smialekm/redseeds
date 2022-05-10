package eu.redseeds.sc.query.engine.ukocompare;

import de.uni_koblenz.jgralab.EdgeDirection;
import eu.redseeds.sc.query.comparefunctions.RemoteNodesSimilarityLi;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ConditionalConjunctionIsPartOfSource;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ConditionalSentenceContainsConditionalClause;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ModalVerbIsPartOfSource;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.OwnedSentenceContainsMainClause;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.PredicateIsPartOfSource;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SubjectIsPartOfSource;

public class CombinedCompareConfiguration extends AbstractCompareConfiguration {
	
	public CombinedCompareConfiguration() {
		super();
		init();
	}
	
	private void init() {
		/* SVOLanguageSentence */
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence.class,
				new RemoteNodesSimilarityLi(SubjectIsPartOfSource.class, EdgeDirection.IN), 0.33);
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence.class,
				new RemoteNodesSimilarityLi(PredicateIsPartOfSource.class, EdgeDirection.IN), 0.67);
		
		/* ModalSVOSentence*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ModalSVOSentence.class,
				new RemoteNodesSimilarityLi(SubjectIsPartOfSource.class, EdgeDirection.IN), 0.3);
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ModalSVOSentence.class,
				new RemoteNodesSimilarityLi(PredicateIsPartOfSource.class, EdgeDirection.IN), 0.6);
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ModalSVOSentence.class,
				new RemoteNodesSimilarityLi(ModalVerbIsPartOfSource.class, EdgeDirection.IN), 0.1);

		
		/* ConditionalSentence*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ConditionalSentence.class,
				new RemoteNodesSimilarityLi(ConditionalSentenceContainsConditionalClause.class, EdgeDirection.OUT), 0.47);
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ConditionalSentence.class,
				new RemoteNodesSimilarityLi(OwnedSentenceContainsMainClause.class, EdgeDirection.OUT), 0.48);
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ConditionalSentence.class,
				new RemoteNodesSimilarityLi(ConditionalConjunctionIsPartOfSource.class, EdgeDirection.IN), 0.05);


		/* NaturalLanguageHypertextSentence*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentence.class,
				new eu.redseeds.sc.query.comparefunctions.de.fhg.iese.NaturalLanguageSentenceSimilarityFunction(), 1.0);


		/* PreconditionSentence*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentence.class,
				new eu.redseeds.sc.query.comparefunctions.de.fhg.iese.NaturalLanguageSentenceSimilarityFunction(), 1.0);
		

		/* PostconditionSentence*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentence.class,
				new eu.redseeds.sc.query.comparefunctions.de.fhg.iese.NaturalLanguageSentenceSimilarityFunction(), 1.0);


		/* ConditionSentence*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentence.class,
				new eu.redseeds.sc.query.comparefunctions.de.fhg.iese.NaturalLanguageSentenceSimilarityFunction(), 1.0);
	}



}
