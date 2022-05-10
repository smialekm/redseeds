package eu.remics.recovery.model.tests;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.BeforeClass;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.impl.BusinessLayerFacadeImpl;

public abstract class AbstractRecoveryModelTest {
	
	static protected SCLGraph sclGraph;
	static protected BusinessLayerFacade facade; 

	/**
	 * @throws java.lang.Exception
	 */
	@SuppressWarnings("restriction")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		IProgressMonitor progres = new NullProgressMonitor();
		IProject ip = ResourcesPlugin.getWorkspace().getRoot().getProject("MyProject");
		try {
			ip.create(progres);
		} catch (CoreException e) {
			if (!(e instanceof org.eclipse.core.internal.resources.ResourceException)) e.printStackTrace();
		}
		SCProject scp = new SCProject(ip);
		sclGraph = scp.getSCLGraph();
		facade = (BusinessLayerFacadeImpl)sclGraph;
		SCProjectContainer.instance().addSCProject(scp);
		SCProjectHelper.setActiveProject(ip);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

}
