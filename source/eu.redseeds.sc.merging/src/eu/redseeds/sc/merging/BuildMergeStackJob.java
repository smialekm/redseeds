package eu.redseeds.sc.merging;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.runtime.IProgressMonitor;
import eu.redseeds.common.jobs.IJob;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;

public class BuildMergeStackJob implements IJob {
	
	private MergeStack mergeStack = null;
	private ClipboardDTO clipboard = null;

	public BuildMergeStackJob(ClipboardDTO clipboard) {
		super();
		this.clipboard = clipboard;
	}

	@Override
	public String getName() {
		return "Gathering elements for merge...";
	}

	@Override
	public int getTotalTime() {
		return 2;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		monitor.beginTask(getName(), getTotalTime());
		
		monitor.subTask("");
		mergeStack = new MergeStack(clipboard);
		monitor.worked(1);
		monitor.subTask("");
		mergeStack.build();
		monitor.worked(1);
		
		monitor.done();
	}

	public MergeStack getMergeStack() {
		return mergeStack;
	}

}
