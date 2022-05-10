package eu.redset.transformation.engine.interfaces;

import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import eu.redseeds.sc.current.repository.SCProject;

public interface ITransformationExecution {

	public void execute(SCProject proj);
	
}
