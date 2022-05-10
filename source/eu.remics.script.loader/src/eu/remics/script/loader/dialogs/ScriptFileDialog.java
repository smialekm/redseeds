package eu.remics.script.loader.dialogs;

import java.io.File;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class ScriptFileDialog {
	
	FileDialog fileDialog = new FileDialog(new Shell(), SWT.MULTI);
	private String[] paths = null;
	
	public ScriptFileDialog(){
		fileDialog.setText("Select File");
		fileDialog.setFilterExtensions(new String[] { "*.rftss" });
		fileDialog.setFilterNames(new String[] { "Rational Functional Tester files(*.rftss)" });
	}
	
	public void choose(){
		String selected = null;
		boolean done = false;
		
		while(!done){
			paths = null;
			selected = fileDialog.open();
			if(selected == null){
				done = true;
			}
			else{
				// Append all the selected files. Since getFileNames() returns only 
				// the names, and not the path, prepend the path, normalizing
				// if necessary
				StringBuffer buf = new StringBuffer();
				String[] files = fileDialog.getFileNames();
				String path = fileDialog.getFilterPath();
				
				for(int i=0; i < files.length; i++){
					buf.append(path);
					if (buf.charAt(buf.length() - 1) != File.separatorChar) {
						buf.append(File.separatorChar);
					}
					buf.append(files[i]);
					buf.append("	");
				}
				paths = buf.toString().split("	");
				
				for (int i = 0; i < paths.length; i++) {
					File file = new File(paths[i]);
					if(!file.exists()){
						MessageDialog.openError(fileDialog.getParent(), "File load error", "File " + paths[i].substring(paths[i].lastIndexOf(File.separatorChar)+1) + " does not exists under selected path.");
						done = false;
						break;
					}
					else if(!paths[i].endsWith(".rftss")){
						MessageDialog.openError(fileDialog.getParent(), "File load error", "File " + paths[i].substring(paths[i].lastIndexOf(File.separatorChar)+1) + " has wrong extension.");
						done = false;
						break;
					}
					else{
						done = true;
					}
				}
			}
		}
		this.setSelectedPathsArray(paths);
	}
	
	private void setSelectedPathsArray(String[] paths){
		this.paths = paths;
	}

	public String[] getSelectedPaths(){
		return this.paths;
	}
}
