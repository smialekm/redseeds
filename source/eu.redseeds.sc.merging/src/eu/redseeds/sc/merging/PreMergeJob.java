package eu.redseeds.sc.merging;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import eu.redseeds.common.jobs.IJob;
import eu.redseeds.sc.merging.conflicts.IMergeCheck;
import eu.redseeds.sc.merging.conflicts.MergeCheckAdapter;
import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

/**
 * Gathers clipboard elements before merging and checks for possible merge conflicts
 *
 */
public class PreMergeJob implements IJob {
	
	private MergeStack objectsToMerge;
	private SoftwareCaseDTO targetSC;
	private List<MergeConflictObject> foundConflicts;

	public PreMergeJob(MergeStack objectsToMerge, SoftwareCaseDTO targetSC) {
		super();
		this.objectsToMerge = objectsToMerge;
		this.targetSC = targetSC;
	}

	@Override
	public String getName() {
		return "Preparing for merge...";
	}

	@Override
	public int getTotalTime() {
		return objectsToMerge.size();
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		
		monitor.beginTask(getName(), getTotalTime());
		
		foundConflicts = new ArrayList<MergeConflictObject>();
		
		while(!objectsToMerge.empty()) {
			monitor.subTask("Analysing "+objectsToMerge.peek()+"...");
			IMergeCheck check = MergeCheckAdapter.adapt(objectsToMerge.peek());
			foundConflicts.addAll(Arrays.asList(check.check(objectsToMerge.pop(), targetSC)));
			monitor.worked(1);
		}
		
		monitor.done();
		
	}

	/**
	 * To be invoked after "run". Gets found conflicts
	 * @return
	 */
	public List<MergeConflictObject> getFoundConflicts() {
		return foundConflicts;
	}

}
