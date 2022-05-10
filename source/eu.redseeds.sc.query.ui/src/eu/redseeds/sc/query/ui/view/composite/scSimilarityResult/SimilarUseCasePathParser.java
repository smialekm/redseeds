package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import java.util.HashSet;
import java.util.Set;

import eu.redseeds.sc.query.engine.RequirementMatch;
import eu.redseeds.sc.query.engine.SimilarSCProject;
import eu.redseeds.scl.model.rsl.IHierarchyAware;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

class SimilarUseCasePathParser extends AbstractSimilarGenericElementPathParser{

	private static final Set<Class<? extends IHierarchyAware>> properTypes = new HashSet<Class<? extends IHierarchyAware>>();
	static {
		properTypes.add(UseCaseDTO.class);
		properTypes.add(RequirementDTO.class);
	}

	public SimilarUseCasePathParser(SimilarSCProject input) {
		super(input);
	}

	@Override
	protected Set<? extends IElementMatch> getAdaptedElementMatchs(
			SimilarSCProject newInput) {

		Set<RequirementMatchAdapter> result=new HashSet<RequirementMatchAdapter>();
		Set<RequirementMatch> requirementsPairs = newInput.getSimilarityValue().getRequirementsPairs();
		for (RequirementMatch requirementMatch : requirementsPairs) {
			result.add(new RequirementMatchAdapter(requirementMatch));
		}
		return result;
	}

	@Override
	protected Set<Class<? extends IHierarchyAware>> getProperTypes() {
		return properTypes;
	}
	private static class RequirementMatchAdapter implements IElementMatch{
		private final RequirementMatch requirementMatch;

		public RequirementMatchAdapter(RequirementMatch requirementMatch) {
			super();
			this.requirementMatch = requirementMatch;
		}

		@Override
		public IHierarchyAware getCurrentElement() {
			//this object should be compatible with IHierarchyAware
			@SuppressWarnings("unchecked")
			IHierarchyAware element=(IHierarchyAware) requirementMatch.getCurrentRequirement();
			return element;
		}

		@Override
		public IHierarchyAware getPastElement() {
			//this object should be compatible with IHierarchyAware
			@SuppressWarnings("unchecked")
			IHierarchyAware element=(IHierarchyAware) requirementMatch.getPastRequirement();
			return element;
		}

		@Override
		public double getSimilarityValue() {
			return requirementMatch.getSimilarityValue();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((requirementMatch == null) ? 0 : requirementMatch
							.hashCode());
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
			final RequirementMatchAdapter other = (RequirementMatchAdapter) obj;
			if (requirementMatch == null) {
				if (other.requirementMatch != null)
					return false;
			} else if (!requirementMatch.equals(other.requirementMatch))
				return false;
			return true;
		}



	}

}
