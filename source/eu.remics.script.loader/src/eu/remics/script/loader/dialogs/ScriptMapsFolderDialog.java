package eu.remics.script.loader.dialogs;

import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

public class ScriptMapsFolderDialog {
	
	DirectoryDialog dirDialog;
	private String dir;
	
	public ScriptMapsFolderDialog(){
		dirDialog = new DirectoryDialog(new Shell());
		dirDialog.setText("Select folder containing map files for scripts");
	}
	
	public void choose(){
		String selected = dirDialog.open();
        if (selected != null) {
        	this.setSelectedDir(selected);
        }
        else{
        	this.dir = null;
        }
	}
	
	private void setSelectedDir(String dir){
    	this.dir = dir;
    }
    
    public String getSelectedDir(){
    	return dir;
    }
}
