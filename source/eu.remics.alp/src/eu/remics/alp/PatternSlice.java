package eu.remics.alp;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.slicing.Slice;

/**
 * Container class for 'traditional slice' + other elements 
 * @author aambroziewicz
 *
 */
public class PatternSlice {
	
	protected Slice domainSlice;
	protected List<Object[]> domainInstantiationInfo = new ArrayList<Object[]>();

	/**
	 * Creates pattern slice, adds elements missed by slicing algorithm
	 * @param domainSlice slice calculated using the SliceType.DOMAIN_INCLUDING_SLICE method
	 */
	public PatternSlice(Slice domainSlice) {
		if(validateSlice(domainSlice)) {
			this.domainSlice = domainSlice;
		}
	}
	
	private boolean validateSlice(Slice domainSlice) {
		//TODO - validate criterion, method etc.
		return true;
	}
	
	public List<Object> getAllElements() {
		List<Object> result = new ArrayList<Object>();
		for(Object element : domainSlice.getVertices()) {
			result.add(element);
		}
		return result;
	}
	
	public Slice getSlice() {
		return domainSlice;
	}

	public List<Object[]> getDomainInstantiationInfo() {
		return domainInstantiationInfo;
	}

	public void setDomainInstantiationInfo(List<Object[]> domainInstantiationInfo) {
		this.domainInstantiationInfo = domainInstantiationInfo;
	}
	
}
