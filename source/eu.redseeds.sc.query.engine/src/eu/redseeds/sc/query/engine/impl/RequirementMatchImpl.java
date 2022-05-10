package eu.redseeds.sc.query.engine.impl;

import eu.redseeds.sc.query.engine.RequirementMatch;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;

public class RequirementMatchImpl implements RequirementMatch {

	private final double similarity;

	private final Requirement currentRequirement;

	private final Requirement pastRequirement;

	public RequirementMatchImpl(Requirement currentRequirement,
			Requirement pastRequirement, double similarity) {
		this.currentRequirement = currentRequirement;
		this.pastRequirement = pastRequirement;
		this.similarity = similarity;
	}

	@Override
	public Requirement getCurrentRequirement() {
		return currentRequirement;
	}

	@Override
	public Requirement getPastRequirement() {
		return pastRequirement;
	}

	@Override
	public double getSimilarityValue() {
		return similarity;
	}

	@Override
	public int compareTo(RequirementMatch arg0) {
		double simDiff = similarity - arg0.getSimilarityValue();
		if (simDiff < 0) {
			return -1;
		}
		if (simDiff > 0) {
			return 1;
		}
		return hashCode() - arg0.hashCode();
	}
}
