package eu.redseeds.sc.editor.sdsl.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import eu.redseeds.sc.editor.sdsl.Activator;
import eu.redseeds.ea.converter.interfaces.IConversions;
import eu.redseeds.scl.model.TransformationProfile;
import eu.redseeds.transformation.engine.interfaces.ITransformationExecution;
import eu.redseeds.sc.current.repository.SCProject;

public class RunTransformationsJob implements IRunnableWithProgress {

	private String name;
	private List<TransformationProfile> exTransfList;
	private SCProject currSCProject;
	private String eaModel;
	private int jobCount;
	private ITransformationExecution transfExecution;
	private IConversions eaConversions; 
	
	//@SuppressWarnings
	//private RunTransformationsJob() {};
	
	public RunTransformationsJob(String name, 
			                     List<TransformationProfile> exTransfList,
			                     SCProject currSCProject,
			                     String eaModel,
			                     int jobCount) {
		super();
		this.name = name;
		this.exTransfList = exTransfList;
		this.currSCProject = currSCProject;
		this.eaModel = eaModel;
		this.jobCount = jobCount;
		transfExecution = Activator.getDefault().getITransformationExecutionInstance();
		eaConversions = Activator.getDefault().getIConversionsInstance();	
		
	}
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		monitor.beginTask(name, jobCount + 2);
		monitor.subTask("Initial");
		monitor.worked(1);
		
		monitor.subTask("Extracting data from the Enterprise Architect");
		try {
			eaConversions.convertEAtoTG(currSCProject, eaModel);
		} catch (Exception ex) {
			Activator.log("Data transfer form Enterprise Architect failed", Status.ERROR);
		}
		finally {
			monitor.worked(1);
		}
		monitor.subTask("Extracting data from the Enterprise Architect finished");	
		Activator.log("Extracting data from the Enterprise Architect finished", Status.INFO);

		Activator.log("Execute transformation", Status.INFO);
		monitor.subTask("Execute transformation");
		for (TransformationProfile tmpTrProf : exTransfList) {
			try {
				transfExecution.execute(currSCProject, tmpTrProf);
			} catch (Exception ex) {
				Activator.log("Transfomation execution error", Status.ERROR);
			}
			finally {
				monitor.worked(1);
			}
		}
		monitor.subTask("Executing transformations finished");
		Activator.log("Executing transformations finished", Status.INFO);

		monitor.subTask("Final");
		monitor.worked(1);
		
		monitor.done();
	}

}
