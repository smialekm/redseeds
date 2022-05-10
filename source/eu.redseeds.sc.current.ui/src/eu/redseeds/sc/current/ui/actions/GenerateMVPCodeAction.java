package eu.redseeds.sc.current.ui.actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
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

import eu.redseeds.common.Constants;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.modelio.converter.ModelioRunner;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.current.ui.Activator;
import eu.redseeds.sc.current.ui.validation.ValidationMarkerFactory;
import eu.redseeds.scl.model.TransformationProfile;
import eu.redseeds.transformation.store.interfaces.ITransformationDetails;

public class GenerateMVPCodeAction implements IWorkbenchWindowActionDelegate,
IViewActionDelegate {
	
	private IViewPart navigator;
	private SCProject currSCProject = null;
	
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
				confirmMB.setMessage("Validation errors must be fixed before running R->C transformation");
				confirmMB.setText("Transformation");
				confirmMB.open();
				return;
			}
		}
		
		// Receiving selected elements and their paths from Project Explorer

		ITransformationDetails transfDetails = Activator.getDefault().getITransformationDetailsInstance();

		List<TransformationProfile> tmpTransfList = transfDetails.getTransformationsList();
		TransformationProfile tmpRtoC = null;
		TransformationProfile tmpUMLtoEA = null;

	    Properties molaProperties = new Properties();
	    try {
			FileInputStream input = new FileInputStream("mola.properties");
			molaProperties.load( input ); // load properties
			input.close();
	    } catch (IOException e) {
	    	//mola.properties file not yet created, do nothing
	    }
	    String transfType = "RSL_TO_CODE";

		//TODO customizable (multiple) transformations with the same type
		for (TransformationProfile prof : tmpTransfList) {
			if (prof.getType().getType().equals(transfType))
				tmpRtoC = prof; 
			if (prof.getType().getType().equals("UML_EA_TRANSFORMATION"))
				tmpUMLtoEA = prof; 
		}

		if (tmpRtoC == null ||  tmpUMLtoEA == null) {
			Activator.log("Appropriate transformations not found!", Status.ERROR);
			MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_ERROR);
			confirmMB.setMessage("Appropriate transformations not found!");
			confirmMB.open();
			return;
		}
		
		currSCProject = null;
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
		
		
		//Requirements -> Detail Design
		// execute Transformations
		try {
			runTransformation(tmpRtoC, tmpUMLtoEA);
		} catch (Exception e) {
			return;
		}
		
		Activator.log("Generation process completed ...", Status.INFO);
		MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_INFORMATION);
		confirmMB.setMessage("Generation process completed!");
		confirmMB.open();
		
		SCProjectHelper.refreshSCNavigator();
	}
	
	private void runTransformation(TransformationProfile tmpRtoC, TransformationProfile tmpUMLtoEA) throws Exception{
		final List<TransformationProfile> exTransfList = new ArrayList<TransformationProfile> ();
		exTransfList.add(tmpRtoC);
		
		boolean runTGtoEA = false;
		String radioBtnOption = eu.redseeds.common.Activator.getDefault().getPreferenceStore().getString(Constants.SELECT_GEN_METHOD);
		boolean inModelioGeneration = radioBtnOption.equals(Constants.MODELIO_MODEL_GEN) ? true : false;
		boolean inEAGeneration = radioBtnOption.equals(Constants.EA_MODEL_GEN) ? true : false;
		final boolean codeGenFlag = eu.redseeds.common.Activator.getDefault().getPreferenceStore().getBoolean(Constants.CODE_GEN);
		
		if(inEAGeneration){
			exTransfList.add(tmpUMLtoEA);
			runTGtoEA = true;
		}
		IWorkbench workbench = PlatformUI.getWorkbench(); 
		TransformationRunnable tr = new TransformationRunnable(exTransfList, runTGtoEA, codeGenFlag);
		workbench.getDisplay().syncExec(tr);
		if(tr.isErrorOccur()){
			//Error during transformation
			throw new Exception();
		}
		
		boolean isModelioConfigSet = eu.redseeds.common.Activator.getDefault().getPreferenceStore().getBoolean(Constants.SET_CONFIGURATION);
		if(inModelioGeneration){
			if(isModelioConfigSet){
				//open Modelio application instance
				String scProjLocation = currSCProject.getEclipseProject().getLocation().toString();
				ModelioRunner.openModelio(scProjLocation);
			} else{
				//Modelio configuration must be set
				MessageDialog.openError(SCProjectHelper.getShell(), "Configuration error", "Modelio configuration is not provided. Set it up.");
				throw new Exception();
			}
		}
	}
	
	class TransformationRunnable implements Runnable {
		
		private List<TransformationProfile> exTransfList;
		private boolean runTGtoEA;
		private boolean codeGenFlag;
		private boolean exceptionError = false;

		public TransformationRunnable(List<TransformationProfile> exTransfList, boolean runTGtoEA, boolean codeGenFlag) {
			this.exTransfList = exTransfList;
			this.runTGtoEA = runTGtoEA;
			this.codeGenFlag = codeGenFlag;
		}
		
		public boolean isErrorOccur() {
			return exceptionError;
		}

		@Override
		public void run() {
			IWorkbenchWindow window 
			= PlatformUI.getWorkbench().getActiveWorkbenchWindow(); 
			if (window != null) { 
				Shell shell = window.getShell();
				boolean cancelable = false;
				RunTransformationsJob action = new RunTransformationsJob("Generating software artifacts",
						exTransfList,
						currSCProject,
						runTGtoEA,
						codeGenFlag,
						false,
						3);
				try {
					ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
					dialog.run(true, cancelable, action);
				} catch (Exception ex) {
					Activator.log("Error during generation process", Status.ERROR);
					exceptionError = true;
				}
			}
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void init(IWorkbenchWindow window) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void init(IViewPart view) {
		navigator = view;
	}

}
