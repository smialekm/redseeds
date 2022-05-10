package eu.redseeds.sc.project.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;

public class DefaultActionHandler extends AbstractHandler {
	
	private boolean enabled = true;

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		enabled = false;
		fireHandlerChanged(new HandlerEvent(this,true,false));
		return null;
	}

}
