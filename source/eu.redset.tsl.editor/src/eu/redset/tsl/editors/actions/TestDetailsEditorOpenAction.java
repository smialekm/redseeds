package eu.redset.tsl.editors.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redset.emf.model.tsl.Test;
import eu.redset.tsl.editor.editors.TestDetailsEditor;

public class TestDetailsEditorOpenAction extends Action{

	String testDetailsPropertyEditorID = "eu.redset.tsl.editor.editors.TestDetailsEditor";
	IWorkbenchPage activePage = null;
	private Test test = null;
	private SCProject project;
	//private Object parent;
	
	
	public TestDetailsEditorOpenAction(){
		activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(); 
	}
	
	public void setTest(Test test){
		this.test = test;
	}
	
	@Override
	public void run() {
		
		try {
			TestDetailsEditor view = (TestDetailsEditor)activePage.findView(testDetailsPropertyEditorID);
			if (view == null){
				view = (TestDetailsEditor)activePage.showView(testDetailsPropertyEditorID);
				view.setScProject(project);
				//view.setEditor(parent);
				view.setIncomingObject(test);
				view.setContent();
			} else {
				if (!test.equals(view.getIncomingObject())){
					if (view.isDirty()){
						
						MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), 
								SWT.ICON_QUESTION  | SWT.YES | SWT.NO | SWT.CANCEL);

						confirmMB.setMessage("Do you want to save your changes?");

						confirmMB.setText("Confirm save");
						
						int resultMB = confirmMB.open();
						
						if(resultMB == SWT.CANCEL) {
							return; //do nothing
						}
						
						if(resultMB == SWT.YES) {
							view.doSave(new NullProgressMonitor());
							view.clean();
							view.setScProject(project);
							//view.setEditor(parent);
							view.setIncomingObject(test);
							view.setContent();
							view.setDirty(false);
						}
						
						if(resultMB == SWT.NO) {
							view.clean();
							view.setScProject(project);
							//view.setEditor(parent);
							view.setIncomingObject(test);
							view.setContent();
							view.setDirty(false);
						}
						
						
					} else {
						view.clean();
						view.setScProject(project);
						//view.setEditor(parent);
						view.setIncomingObject(test);
						view.setContent();
					}
				} else{
					view.clean();
					view.setScProject(project);
					//view.setEditor(parent);
					view.setIncomingObject(test);
					view.setContent();
				}
				view.setScProject(project);
				//view.setEditor(parent);
				activePage.activate(view);
			}
			
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SCProject getProject() {
		return project;
	}

	public void setProject(SCProject project) {
		this.project = project;
	}
	
	/*
	public Object getParent() {
		return parent;
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}
*/
}
