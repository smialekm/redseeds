package eu.redseeds.sc.query.engine.ukocompare;

import de.uni_koblenz.jgralab.Vertex;



public interface CompareConfiguration {

	/**
	 * 
	 * @param vc
	 * @param entry
	 */
	//public void addConfigurationEntry(Class<? extends Vertex> vc, AbstractCompareFunction functionToUse, double weight);
	
	public Iterable<CompareConfigurationEntry> getConfigurations(Class<? extends Vertex> vc);
	
	//public void replaceConfigurationEntry(Class<? extends Vertex> vc, AbstractCompareFunction functionToUse, double weight);
	
	/**
	 * Returns the number of elements for which there is a configuration
	 */
	public int size();
	
	public Iterable<Class<? extends Vertex>> getConfiguredClasses();
	
}
