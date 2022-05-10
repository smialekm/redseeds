package eu.redseeds.scl.model.sdsl;

import java.util.List;

public interface InterfaceDTO {
	
	public String getName();

	public void setName(String name);

	public String toString();

	public PackageDTO getParent();
		
	public void setParent(PackageDTO parent);
	
	public List<OperationDTO> getOperationDTOList();
	
	public void addOperation(OperationDTO operation);

}
