package eu.redseeds.sc.current.ui.actions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.current.ui.Activator;
import eu.redseeds.sc.current.ui.validation.ValidationMarkerFactory;
import eu.redseeds.scl.model.TransformationProfile;
import eu.redseeds.transformation.store.interfaces.ITransformationDetails;

public class CreateArchitectureAction implements IWorkbenchWindowActionDelegate,
IViewActionDelegate {

	private IViewPart navigator;
	private SCProject currSCProject = null;
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
	}

	@Override
	public void run(IAction action) {				
		//check if requirements are valid
		ValidateAction validation = new ValidateAction();
		validation.init(navigator);
		validation.run(action);
		//check markers
		ITreeSelection currSelection = (ITreeSelection) SCProjectHelper.getSelection();
		if (currSelection != null) {
			IProject project = SCProjectHelper.getIProject(currSelection);
			int validationMarkers = ValidationMarkerFactory.getNumberOfErrorMarkers(project);
			if(validationMarkers == -1) {
				MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_ERROR);
				confirmMB.setMessage("Error occurred - see log for more details");
				confirmMB.setText("Transformation");
				confirmMB.open();
				return;
			}
			if(validationMarkers > 0) {
				MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_WARNING);
				confirmMB.setMessage("Validation errors must be fixed before running R->A transformation");
				confirmMB.setText("Transformation");
				confirmMB.open();
				return;
			}
		}
		
		// Receiving selected elements and their paths from Project Explorer
		
		ITransformationDetails transfDetails = Activator.getDefault().getITransformationDetailsInstance();
//		ITransformationExecution transfExecution = Activator.getDefault().getITransformationExecutionInstance();
//		IConversions eaConversions = Activator.getDefault().getIConversionsInstance();	

		List<TransformationProfile> tmpTransfList = transfDetails.getTransformationsList();
		TransformationProfile tmpRtoA = null;
		TransformationProfile tmpUMLtoEA = null;

	    Properties molaProperties = new Properties();
	    try {
			FileInputStream input = new FileInputStream("mola.properties");
			molaProperties.load( input ); // load properties
			input.close();
	    } catch (IOException e) {
	    	//mola.properties file not yet created, do nothing
	    }
	    String transfStyle = molaProperties.getProperty("TransformationStyle", "Basic style");
	    String transfType = "REQ_ARCH_TRANSFORMATION";
	    if (!transfStyle.equals("Basic style"))
	    	transfType = "REQ_PIM_TRANSFORMATION";
	    	
		//TODO customizable (multiple) transformations with the same type
		for (TransformationProfile prof : tmpTransfList) {
			if (prof.getType().getType().equals(transfType))
				tmpRtoA = prof; 
			if (prof.getType().getType().equals("UML_EA_TRANSFORMATION"))
				tmpUMLtoEA = prof; 
		}
		
		if (tmpRtoA == null ||  tmpUMLtoEA == null) {
			Activator.log("Appropriate transformations not found!", Status.ERROR);
			MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_ERROR);
			confirmMB.setMessage("Appropriate transformations not found!");
			confirmMB.open();
			return;
		}
		
		currSCProject = null;
//		ITreeSelection currSelection = (ITreeSelection) SCProjectHelper.getSelection(); - defined above
		if (currSelection != null) {
			IProject project = SCProjectHelper.getIProject(currSelection);
			currSCProject = SCProjectContainer.instance().getSCProject(project);
		}
		if (currSCProject == null) {
			Activator.log("Can't locate current SCProject...", Status.ERROR);
			MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_ERROR);
			confirmMB.setMessage("Can't find selected project!");
			confirmMB.open();
			return;
		}
		
	    // Write properties file.
	    molaProperties.setProperty("uml_to_ea_kind", "Architecture");
	    try {
	    	molaProperties.store(new FileOutputStream("mola.properties"), null);
	    } catch (Exception e) {
            Activator.log("Error during writing MOLA properties file", Status.ERROR);
	    }
		
		// execute Transformations and convertTGtoEA
		final List<TransformationProfile> exTransfList = new ArrayList<TransformationProfile> ();
		exTransfList.add(tmpRtoA);
		exTransfList.add(tmpUMLtoEA);
		
		final IWorkbench workbench = PlatformUI.getWorkbench(); 
		workbench.getDisplay().syncExec(new Runnable() { 
			public void run() { 
				IWorkbenchWindow window 
					= workbench.getActiveWorkbenchWindow(); 
				if (window != null) { 
					Shell shell = window.getShell();
					boolean cancelable = false;
					RunTransformationsJob action = new RunTransformationsJob("Generating Architecture model",
							                                                  exTransfList,
							                                                  currSCProject,
							                                                  true,
							                                                  false,
							                                                  3);
					try {
			            ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
			            dialog.run(true, cancelable, action);
			        } catch (Exception ex) {
			            Activator.log("Error during generating Architecture", Status.ERROR);
			        }
				}
			}
		});
		
		
		//transfExecution.execute(tmpRtoA);
		//transfExecution.execute(tmpUMLtoEA);
		//eaConversions.convertTGtoEA(false,false);

		SCProjectHelper.refreshSCNavigator();
		
		Activator.log("Architecture generation completed ...", Status.INFO);
		MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION);
		confirmMB.setMessage("Architecture generation completed!");
		confirmMB.open();
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IViewPart view) {
		navigator = view;
	}

}