package eu.remics.alp;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import eu.redseeds.common.jobs.IJob;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;

public class SliceJob implements IJob {
	
	PatternSlice pSlice;

	RequirementsPackageDTO pattern;

	public SliceJob(RequirementsPackageDTO pattern) {
		super();
		this.pattern = pattern;
	}

	@Override
	public String getName() {
		return Constants.SLICING_JOB_NAME;
	}

	@Override
	public int getTotalTime() {
		return 10;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		monitor.beginTask(getName(), getTotalTime());
		monitor.subTask("Preparing...");
		List<RequirementsPackageDTO> patterns = ImportManager.calculateRelatedPatterns(pattern);
		patterns.add(pattern);
		monitor.worked(2);
		monitor.subTask("Analysing domain...");
		pSlice = ImportManager.getPatternSlice(patterns);
		monitor.worked(8);
		monitor.done();
	}
	
	public PatternSlice getpSlice() {
		return pSlice;
	}

}
