package eu.redseeds.engine.navigator.actions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import de.uni_koblenz.jgwnl.client.gui.JGWNLClientFrame;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;

public class JGWNLClientAction implements IWorkbenchWindowActionDelegate {

	ExecutorService executorService;
	private static JGWNLClientFrame frame = null;

	@Override
	public void dispose() {
		if (executorService != null) {
			executorService.shutdown();
		}
	}

	@Override
	public void init(IWorkbenchWindow window) {
		executorService = Executors.newSingleThreadExecutor();
	}

	@Override
	public void run(IAction action) {
		executorService.execute(new Runnable() {
			public void run() {
				if (frame == null) {
					frame = new JGWNLClientFrame(RemoteJGWNL.getInstance()
							.getJGWNLClient(), true);
				}
				frame.setVisible(true);
			}
		});
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
	}
}
