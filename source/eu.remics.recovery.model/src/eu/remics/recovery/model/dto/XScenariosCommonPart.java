package eu.remics.recovery.model.dto;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;

public class XScenariosCommonPart implements Comparable<XScenariosCommonPart> {

	ConstrainedLanguageScenarioDTO referenceScenario,scenario;
	int commonSentenceNumber;
	
	public XScenariosCommonPart() {}

	public XScenariosCommonPart(ConstrainedLanguageScenarioDTO referenceScenario, ConstrainedLanguageScenarioDTO scenario, int commonSentenceNumber) {
		this.referenceScenario = referenceScenario;
		this.scenario = scenario;
		this.commonSentenceNumber = commonSentenceNumber;
	}

	public ConstrainedLanguageScenarioDTO getReferenceScenario() {
		return referenceScenario;
	}
	
	public void setReferenceScenario(ConstrainedLanguageScenarioDTO referenceScenario) {
		this.referenceScenario = referenceScenario;
	}
	
	public ConstrainedLanguageScenarioDTO getScenario() {
		return scenario;
	}
	
	public void setScenario(ConstrainedLanguageScenarioDTO scenario) {
		this.scenario = scenario;
	}
	
	public int getCommonSentenceNumber() {
		return commonSentenceNumber;
	}
	
	public void setCommonSentenceNumber(int commonSentenceNumber) {
		this.commonSentenceNumber = commonSentenceNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((referenceScenario == null) ? 0 : referenceScenario
						.hashCode());
		result = prime * result
				+ ((scenario == null) ? 0 : scenario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XScenariosCommonPart other = (XScenariosCommonPart) obj;
		if (referenceScenario==null && scenario==null) return other.referenceScenario==null && other.scenario==null;
		if (referenceScenario==null) return other.referenceScenario==null && scenario.equals(other.scenario) || other.scenario==null && scenario.equals(other.referenceScenario);
		if (scenario==null) return other.scenario==null && referenceScenario.equals(other.referenceScenario) || other.referenceScenario==null && referenceScenario.equals(other.scenario);
		return referenceScenario.equals(other.referenceScenario) && scenario.equals(other.scenario) || referenceScenario.equals(other.scenario) && scenario.equals(other.referenceScenario);
	}

	@Override
	public int compareTo(XScenariosCommonPart o) {
		return this.commonSentenceNumber-o.commonSentenceNumber;
	}
	
}
