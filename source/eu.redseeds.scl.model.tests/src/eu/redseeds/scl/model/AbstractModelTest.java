/**
 * 
 */
package eu.redseeds.scl.model;


import org.junit.Before;
import org.junit.BeforeClass;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.SCLSchema;
import eu.redseeds.scl.model.impl.BusinessLayerFacadeImpl;

/**
 * @author bojarsj1
 *
 */
public abstract class AbstractModelTest {
	
	static protected SCLGraph sclGraph;
	static protected BusinessLayerFacade facade; 

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		SCProject.setGraphImplementationClasses();
		
		sclGraph = SCLSchema.instance().createSCLGraph(100, 200);
		facade = (BusinessLayerFacadeImpl)sclGraph;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

}
