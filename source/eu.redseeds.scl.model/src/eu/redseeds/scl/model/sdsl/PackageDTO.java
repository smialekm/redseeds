package eu.redseeds.scl.model.sdsl;

import java.util.List;

public interface PackageDTO {
	
	public String getName();
	
	public void setName(String name);
	
	public List<PackageDTO> getPackageDTOList();
	
	public List<ActorDTO> getActorDTOList();
	
	public List<ClassDTO> getClassDTOList();
	
	public List<ComponentDTO> getComponentDTOList();
	
	public List<InterfaceDTO> getInterfaceDTOList();
	
	public void deleteRecursively();
	
	public String toString();
	
	public PackageDTO getParent();
	
	/**
	 * Not fully implemented
	 * @param parent if null removes model-parent
	 */
	public void setParent(PackageDTO parent);
	
	public void addActor(ActorDTO actor);
	
	public void addClass(ClassDTO cl);
	
	public void addInterface(InterfaceDTO interf);
	
	public void addComponent(ComponentDTO comp);
	
	public void addPackage(PackageDTO pack);
	
	public void delete();

}
