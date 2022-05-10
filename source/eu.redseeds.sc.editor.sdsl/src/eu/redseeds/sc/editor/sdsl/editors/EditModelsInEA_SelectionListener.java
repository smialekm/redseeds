package eu.redseeds.sc.editor.sdsl.editors;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import eu.redseeds.ea.converter.interfaces.IConversions;
import eu.redseeds.sc.editor.sdsl.Activator;

public class EditModelsInEA_SelectionListener extends SelectionAdapter {
    
	public void widgetSelected(SelectionEvent event) {
		IConversions eaConversions = Activator.getDefault().getIConversionsInstance();	
		eaConversions.openEA(null);		
  	  }
	
}
