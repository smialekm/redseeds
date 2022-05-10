package eu.redseeds.sc.editor.sdsl.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
//import eu.redseeds.ea.converter.interfaces.IConversions;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.sdsl.actions.RunTransformationsJob;
import eu.redseeds.sc.editor.sdsl.Activator;
import eu.redseeds.scl.model.TransformationProfile;
//import eu.redseeds.transformation.engine.interfaces.ITransformationExecution;
import eu.redseeds.transformation.store.interfaces.ITransformationDetails;


public class GetArchitectureFromEA_SelectionListener extends SelectionAdapter {
	
	private SCProject currSCProject = null;
	//Architecture, Detailed Design
	public void widgetSelected(SelectionEvent event) {
		
		ITransformationDetails transfDetails = Activator.getDefault().getITransformationDetailsInstance();
		//ITransformationExecution transfExecution = Activator.getDefault().getITransformationExecutionInstance();
		//IConversions eaConversions = Activator.getDefault().getIConversionsInstance();	

		List<TransformationProfile> tmpTransfList = transfDetails.getTransformationsList();
		TransformationProfile tmpMerge = null;
		TransformationProfile tmpEAtoUML = null;
		
		//TODO customizable (multiple) transformations with the same type
		for (TransformationProfile prof : tmpTransfList) {
			//if (prof.getType().getType().equals("TRIVIAL_MERGE_TRANSFORMATION"))
			if (prof.getType().getType().equals("SIMPLE_MERGE_TRANSFORMATION"))
				tmpMerge = prof; 
			if (prof.getType().getType().equals("EA_UML_TRANSFORMATION"))
				tmpEAtoUML = prof; 
		}
		
		if (tmpEAtoUML == null ||  tmpMerge == null) {
			Activator.log("Appropriate transformations not found!", Status.ERROR);
			MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_ERROR);
			confirmMB.setMessage("Appropriate transformations not found!");
			confirmMB.open();
			return;
		}

		currSCProject = null;
		ITreeSelection currSelection = (ITreeSelection) SCProjectHelper.getSelection();
		if (currSelection != null) {
			//workaround for SCProjectHelper.getIProject(currSelection)
			TreePath[] tr = currSelection.getPaths();
			if (tr != null && tr.length >0) {
				IProject project = SCProjectHelper.getIProject(currSelection);
				currSCProject = SCProjectContainer.instance().getSCProject(project);
			}
		}
		
		if (currSCProject == null) {
			Activator.log("Can't locate current SCProject...", Status.ERROR);
			MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_ERROR);
			confirmMB.setMessage("Can't find selected project!");
			confirmMB.open();
			return;
		}
		
		
		// execute Transformations and convertTGtoEA
		final List<TransformationProfile> exTransfList = new ArrayList<TransformationProfile> ();
		exTransfList.add(tmpEAtoUML);
		exTransfList.add(tmpMerge);
		
		final IWorkbench workbench = PlatformUI.getWorkbench(); 
		workbench.getDisplay().syncExec(new Runnable() { 
			public void run() { 
				IWorkbenchWindow window 
					= workbench.getActiveWorkbenchWindow(); 
				if (window != null) { 
					Shell shell = window.getShell();
					boolean cancelable = false;
					RunTransformationsJob action = new RunTransformationsJob("Getting Architecture model from the Enterprise Architect",
							                                                  exTransfList,
							                                                  currSCProject,
							                                                  "Architecture",
							                                                  3);
					try {
			            ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
			            dialog.run(true, cancelable, action);
			        } catch (Exception ex) {
			            Activator.log("Error during getting Architecture", Status.ERROR);
			        }
				}
			}
		});
		
		//eaConversions.convertEAtoTG(null);
		//transfExecution.execute(null, tmpEAtoUML);
		//transfExecution.execute(null, tmpMerge);
		
		SCProjectHelper.refreshSCNavigator();

		Activator.log("Data extraction from EA completed ...", Status.INFO);
		MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION);
		confirmMB.setMessage("Data extraction from EA completed!");
		confirmMB.open();
  	  }
}
