package eu.redseeds.sc.merging;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import eu.redseeds.common.jobs.IJob;
import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class MergeJob implements IJob {
	
	List<MergeConflictObject> conflicts;
	ClipboardDTO clipboard;
	SoftwareCaseDTO sc;

	public MergeJob(List<MergeConflictObject> conflicts,
			ClipboardDTO clipboard, SoftwareCaseDTO sc) {
		super();
		this.conflicts = conflicts;
		this.clipboard = clipboard;
		this.sc = sc;
	}

	@Override
	public String getName() {
		return "Merging Clipboard with Software Case...";
	}

	@Override
	public int getTotalTime() {
		return 5;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		
		monitor.beginTask(getName(), getTotalTime());
		
		monitor.subTask("Resolving conflicts...");
		SCMergeManager.resolveConflicts(conflicts);
		monitor.worked(1);
		
		monitor.subTask("Merging requirements...");
		SCMergeManager.mergeRequirements(clipboard, sc);
		monitor.worked(1);
		monitor.subTask("Merging architecture...");
		SCMergeManager.mergeArchitecture(clipboard, sc);
		monitor.worked(1);
		monitor.subTask("Merging detailed design...");
		SCMergeManager.mergeDesign(clipboard, sc);
		monitor.worked(1);
		
		monitor.subTask("Performing post-merge validation...");
		SCMergeManager.postMergeValidation(clipboard, sc);
		monitor.worked(1);
		
		monitor.done();

	}

}
