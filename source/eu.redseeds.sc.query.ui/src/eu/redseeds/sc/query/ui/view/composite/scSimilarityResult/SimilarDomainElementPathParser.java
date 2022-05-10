package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import java.util.HashSet;
import java.util.Set;

import eu.redseeds.sc.query.engine.DomainElementMatch;
import eu.redseeds.sc.query.engine.SimilarSCProject;
import eu.redseeds.scl.model.rsl.IHierarchyAware;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;

class SimilarDomainElementPathParser extends AbstractSimilarGenericElementPathParser{

	private static final Set<Class<? extends IHierarchyAware>> properTypes = new HashSet<Class<? extends IHierarchyAware>>();
	static {
		properTypes.add(NotionDTO.class);
		properTypes.add(ActorDTO.class);
		properTypes.add(SystemElementDTO.class);
	}

	public SimilarDomainElementPathParser(SimilarSCProject input) {
		super(input);
	}

	@Override
	protected Set<? extends IElementMatch> getAdaptedElementMatchs(SimilarSCProject newInput) {

		Set<DomainElementMatchAdapter> result = new HashSet<DomainElementMatchAdapter>();
		Set<DomainElementMatch> requirementsPairs = newInput.getSimilarityValue()
				.getDomainElementPairs();
		for (DomainElementMatch domainElementMatch : requirementsPairs) {
			result.add(new DomainElementMatchAdapter(domainElementMatch));
		}
		return result;
	}

	@Override
	protected Set<Class<? extends IHierarchyAware>> getProperTypes() {
		return properTypes;
	}

	private static class DomainElementMatchAdapter implements IElementMatch{
		private final DomainElementMatch domainElementMatch;

		public DomainElementMatchAdapter(DomainElementMatch domainElementMatch) {
			super();
			this.domainElementMatch = domainElementMatch;
		}

		@Override
		public IHierarchyAware getCurrentElement() {
			//this object should be compatible with IHierarchyAware
			@SuppressWarnings("unchecked")
			IHierarchyAware element=(IHierarchyAware) domainElementMatch.getCurrentDomainElement();
			return element;
		}

		@Override
		public IHierarchyAware getPastElement() {
			//this object should be compatible with IHierarchyAware
			@SuppressWarnings("unchecked")
			IHierarchyAware element=(IHierarchyAware) domainElementMatch.getPastDomainElement();
			return element;
		}

		@Override
		public double getSimilarityValue() {
			return domainElementMatch.getSimilarityValue();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((domainElementMatch == null) ? 0 : domainElementMatch
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
			final DomainElementMatchAdapter other = (DomainElementMatchAdapter) obj;
			if (domainElementMatch == null) {
				if (other.domainElementMatch != null)
					return false;
			} else if (!domainElementMatch.equals(other.domainElementMatch))
				return false;
			return true;
		}



	}

}
