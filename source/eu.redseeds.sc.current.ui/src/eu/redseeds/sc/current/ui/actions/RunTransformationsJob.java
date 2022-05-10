package eu.redseeds.sc.current.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;

import eu.redseeds.ea.converter.interfaces.IConversions;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.ui.Activator;
import eu.redseeds.scl.model.TransformationProfile;
import eu.redseeds.transformation.engine.interfaces.ITransformationExecution;

public class RunTransformationsJob implements IRunnableWithProgress {

//	@Override
//	public int getTotalTime() {
//		IWorkspace workspace = ResourcesPlugin.getWorkspace();
//		return workspace.getRoot().getProjects().length + 2;
//	}

	private String name;
	private List<TransformationProfile> exTransfList;
	private SCProject currSCProject;
	private boolean runTGtoEA;
	private boolean genCode;
	private int jobCount;
	private ITransformationExecution transfExecution;
	private IConversions eaConversions; 
	private boolean visualisationRSL;
	
	//@SuppressWarnings
	//private RunTransformationsJob() {};
	
	public RunTransformationsJob(String name, 
			List<TransformationProfile> exTransfList,
			SCProject currSCProject,
			boolean runTGtoEA,
			boolean genCode,
			boolean visualizationRSL,
			int jobCount) {
		super();
		this.name = name;
		this.exTransfList = exTransfList;
		this.currSCProject = currSCProject;
		this.runTGtoEA = runTGtoEA;
		this.genCode = genCode;
		this.visualisationRSL = visualizationRSL;
		this.jobCount = jobCount;
		transfExecution = Activator.getDefault().getITransformationExecutionInstance();
		eaConversions = Activator.getDefault().getIConversionsInstance();	

	}
	
	public RunTransformationsJob(String name, 
			                     List<TransformationProfile> exTransfList,
			                     SCProject currSCProject,
			                     boolean runTGtoEA,
			                     boolean visualizationRSL,
			                     int jobCount) {
		super();
		this.name = name;
		this.exTransfList = exTransfList;
		this.currSCProject = currSCProject;
		this.runTGtoEA = runTGtoEA;
		this.visualisationRSL = visualizationRSL;
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
		
		if (runTGtoEA) {
			monitor.subTask("Transfering data to the Enterprise Architect");
			try {
				eaConversions.convertTGtoEA(currSCProject,false,visualisationRSL,genCode);
			} catch (Exception ex) {
				Activator.log("Data transfer to Enterprise Architect failed", Status.ERROR);
			}
			finally {
				monitor.worked(1);
			}
			monitor.subTask("Transfering data to Enterprise Architect finished");	
			Activator.log("Transfering data to Enterprise Architect finished", Status.INFO);
		}

		monitor.subTask("Final");
		monitor.worked(1);
		
		monitor.done();
	}
	
//	@Override
//	public String getName() {
//		return "Reading workspace";
//	}
	
//	public static void sleep(int ms) {
//        try {
//            Thread.sleep(ms * 10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

}
