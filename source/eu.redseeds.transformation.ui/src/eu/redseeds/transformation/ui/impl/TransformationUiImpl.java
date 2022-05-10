package eu.redseeds.transformation.ui.impl;

import org.eclipse.core.runtime.Status;
import org.eclipse.ui.dialogs.*; 
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.dialogs.MessageDialog;

import eu.redseeds.scl.model.TransformationProfile;
import eu.redseeds.transformation.engine.interfaces.ITransformationExecution;
import eu.redseeds.transformation.store.interfaces.ITransformationDetails;
import eu.redseeds.transformation.ui.interfaces.ITransformationBrowserLogic;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.ea.converter.interfaces.IConversions;
import eu.redseeds.transformation.ui.Activator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class TransformationUiImpl implements ITransformationBrowserLogic{
	
	public void openTransformationsBrowser() {
	    // TODO method stub
		Activator.log("openTransformationsBrowser invoked ...", Status.INFO);

		ITransformationExecution transfExecution = Activator.getDefault().getITransformationExecutionInstance();
		ITransformationDetails transfDetails = Activator.getDefault().getITransformationDetailsInstance();
		
		
		List<TransformationProfile> tmpTransfList = transfDetails.getTransformationsList();
		String[] tmpStr = new String[tmpTransfList.size()];
		
		for (int i = 0; i < tmpTransfList.size(); i++) {
			tmpStr[i] = tmpTransfList.get(i).getName() + " : " +  
			                 tmpTransfList.get(i).getType(); 		
		}
		
		ElementListSelectionDialog dlg = new ElementListSelectionDialog(null,
	            								new LabelProvider());
        dlg.setTitle("TransformationsBrowser");
		dlg.setElements(tmpTransfList.toArray());
		dlg.setMultipleSelection(false);
        dlg.open();
	    Object tmpRes = dlg.getFirstResult();
	    if (tmpRes == null) return;
		
	    Properties molaProperties = new Properties();
	    try {
			FileInputStream input = new FileInputStream("mola.properties");
			molaProperties.load( input ); // load properties
			input.close();
	    } catch (IOException e) {
	    	//mola.properties file not yet created, do nothing
	    }
	    
	    molaProperties.setProperty("uml_to_ea_kind", "All");
	    try {
	    	molaProperties.store(new FileOutputStream("mola.properties"), null);
	    } catch (Exception e) {
            Activator.log("Error during writing MOLA properties file", Status.ERROR);
	    }
		
		if (tmpTransfList.size()>0) {
		
		  // if EA_UML_TRANSFORMATION is chosen execute EA_to_TG before this transformation
		  if (((TransformationProfile)tmpRes).getType().getType().equals("EA_UML_TRANSFORMATION")) {
			IConversions eaConversions = Activator.getDefault().getIConversionsInstance();			  
			eaConversions.convertEAtoTG(null, null);
		  }	
				
		  transfExecution.execute(null, (TransformationProfile)tmpRes);
		 
		  // if UML_EA_TRANSFORMATION is chosen execute TG_to_EA after this transformation
		  if (((TransformationProfile)tmpRes).getType().getType().equals("UML_EA_TRANSFORMATION")) {
			IConversions eaConversions = Activator.getDefault().getIConversionsInstance();			  
			eaConversions.convertTGtoEA(null, false, false, false);
		  }
			  
		}  
		else
		  transfExecution.execute(null, new TransformationProfile());
		
		Activator.getStandardDisplay().syncExec(new Runnable() {
            public void run() {
           	 MessageDialog.openInformation(null, "Transformations", "Transformation completed ...");
            }});		
		
		tmpTransfList = null;

		SCProjectHelper.refreshSCNavigator();
	}
	
}
