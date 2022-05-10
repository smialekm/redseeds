package eu.redseeds.scl.model.traceability;

import java.util.List;

/**
 * Each DTO must implement this interface to allow accessing its traceability links
 *
 */
public interface TraceableObjectDTO {
	
	/**
	 * Gets list of traceability links for the object
	 * @return
	 */
	public List<TraceabilityLinkDTO> getTraceabilityLinkDTOList();
	
}
