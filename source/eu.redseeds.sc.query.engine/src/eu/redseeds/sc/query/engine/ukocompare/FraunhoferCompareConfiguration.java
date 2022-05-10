package eu.redseeds.sc.query.engine.ukocompare;


public class FraunhoferCompareConfiguration extends AbstractCompareConfiguration {
	
	public FraunhoferCompareConfiguration() {
		super();
		init();
	}
	
	private void init() {
		/* SVOLanguageSentence */
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence.class,
				new eu.redseeds.sc.query.comparefunctions.de.fhg.iese.NaturalLanguageSentenceSimilarityFunction(), 1.0);

		/* ModalSVOSentence*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ModalSVOSentence.class,
				new eu.redseeds.sc.query.comparefunctions.de.fhg.iese.NaturalLanguageSentenceSimilarityFunction(), 1.0);
		
		/* ConditionalSentence*/
		addConfigurationEntry(eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ConditionalSentence.class,
				new eu.redseeds.sc.query.comparefunctions.de.fhg.iese.NaturalLanguageSentenceSimilarityFunction(), 1.0);

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
