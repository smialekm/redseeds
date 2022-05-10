package eu.redseeds.engine.navigator.actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.jobs.IJob;

public class RunTerminologyServerAction implements
		IWorkbenchWindowActionDelegate {

	@Override
	public void run(IAction action) {
		FileDialog fileDialog = new FileDialog(new Shell());
		fileDialog.setText("Select Terminology server");
		fileDialog.setFilterExtensions(new String[] { "*.bat" });
		fileDialog.setFilterNames(new String[] { "Batch files(*.bat)" });
		String batch = fileDialog.open();
		String dir = "\"" + fileDialog.getFilterPath();
		if(batch != null){
			final String execCmd = "java -Xms128M -Xmx512M -cp " + dir + "\\jgralab_carnotaurus.jar\";" + dir + 
							"\\jgwnl.jar\" de.uni_koblenz.jgwnl.server.JGWNLServer -g " + dir +
							"\\wordnetgraph.tg\" - p 8080 -a 600";
			
			Job job = new Job(execCmd);
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
			try {
				dialog.run(true, false, job);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow window) {
	}

}

class Job implements IJob{
	
	private String execCmd;

	public Job(String execCmd){
		this.execCmd = execCmd;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		
		monitor.beginTask(getName(), getTotalTime());
		startProcess(monitor, execCmd);
		for(int i=0; i < 20; i++){
			Thread.sleep(1000);
			monitor.worked(i);
		}
		monitor.done();
	}
	
	private void startProcess(IProgressMonitor monitor, String execCmd){
		ProcessBuilder pb = new ProcessBuilder("cmd.exe","/c", execCmd);
		try {
			Process proc = pb.redirectErrorStream(true).start();
			
			/*InputStream is = proc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String in;
			while ((in = br.readLine()) != null) {
				System.out.println(in);
			}
			br.close();*/
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getTotalTime() {
		return 100;
	}

	@Override
	public String getName() {
		return "Starting Terminology server";
	}
}
