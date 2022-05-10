package eu.remics.script.loader.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;
import eu.remics.script.loader.applogic.CLoadScripts;

public class LoadScript extends AbstractHandler {
	
	private boolean enabled = true;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		fireHandlerChanged(new HandlerEvent(this,true,false));
		
		CLoadScripts cLoadScripts = new CLoadScripts(); 
		cLoadScripts._ClicksLoadScriptsButton();
		
		return null;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
