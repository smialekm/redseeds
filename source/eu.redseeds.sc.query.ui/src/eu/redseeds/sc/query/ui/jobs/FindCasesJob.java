package eu.redseeds.sc.query.ui.jobs;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.common.jobs.IJob;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.query.engine.ComparisonType;
import eu.redseeds.sc.query.engine.SCQueryEngine;
import eu.redseeds.sc.query.engine.impl.SCQueryEngineImpl;
import eu.redseeds.sc.query.ui.Activator;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;

/**
 * Job used for running the query engine
 *
 */
public class FindCasesJob implements IJob {
	
	/**
	 * Found ('similar') SC projects.
	 */
	private Object[] foundProjects = null;
	private SCProject scProject = null;
	private double simLevel = 0;
	private ComparisonType comparisonType;
	private Set<Requirement> requirements;
	private Set<DomainElement> domainElements;

	/**
	 * Creates a job.
	 * @param scProject SCProject to compare other SC to
	 * @param simLevel similarity threshold used in calculations
	 */
	public FindCasesJob(SCProject scProject, double simLevel, ComparisonType type) {
		super();
		this.scProject = scProject;
		this.simLevel = simLevel;
		this.requirements = null;
		this.domainElements = null;
		this.comparisonType = type;
	}
	
	/**
	 * Creates a job.
	 * @param scProject SCProject to compare other SC to
	 * @param simLevel similarity threshold used in calculations
	 */
	public FindCasesJob(SCProject scProject, double simLevel, Set<Requirement> requirements, Set<DomainElement> domainElements, ComparisonType comparisonType) {
		super();
		this.scProject = scProject;
		this.simLevel = simLevel;
		this.requirements = requirements;
		this.domainElements = domainElements;
		this.comparisonType = comparisonType;
	}

	/**
	 * Gets found ('similar') SC projects. 
	 * @return
	 */
	public Object[] getFoundProjects() {
		return foundProjects;
	}

	@Override
	public String getName() {
		return "Looking for similar SC projects...";
	}

	@Override
	public int getTotalTime() {
		return 2;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		
		monitor.beginTask(getName(), getTotalTime());
		
		monitor.subTask("Initializing Query Engine...");
		SCQueryEngine queryEngine = new SCQueryEngineImpl();
		monitor.worked(1);
		
		monitor.subTask("Comparing Software Cases...");
		try {
			foundProjects = queryEngine.findCases(scProject, simLevel, comparisonType, requirements, domainElements)
					.toArray();
		} catch (Exception e) {
			e.printStackTrace();
			Activator.logError("Problem during running query: " + e.getMessage(), e);
			errorMB("Problem during running query, see Error Log for details");
			foundProjects = new Object[0];
		}
		finally {
			monitor.worked(1);
		}
		
		monitor.done();

	}
	
	/**
	 * displays an error message box
	 * 
	 * @param shell
	 */
	private void errorMB(String message) {
		MessageBox warnMB = new MessageBox(SCProjectHelper.getShell(),
				SWT.ICON_ERROR | SWT.OK);

		warnMB.setMessage(message);
		warnMB.setText("Query Engine problem problem");
		warnMB.open();
	}

}
