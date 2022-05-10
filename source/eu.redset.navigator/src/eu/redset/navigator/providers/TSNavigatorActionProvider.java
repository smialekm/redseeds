package eu.redset.navigator.providers;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;


public class TSNavigatorActionProvider extends CommonActionProvider {

    private OpenAction open;


    @Override
	public void init(ICommonActionExtensionSite site) {
		ICommonViewerWorkbenchSite workbenchSite = null;
		if (site.getViewSite() instanceof ICommonViewerWorkbenchSite) {
			workbenchSite = (ICommonViewerWorkbenchSite) site.getViewSite();
		}
		if (workbenchSite != null) {
			if (workbenchSite.getPart() != null && workbenchSite.getPart() instanceof IViewPart) {
				IViewPart viewPart = (IViewPart) workbenchSite.getPart();
				//TODO TP: for testing rmv if factory is working properly
//				open = new OpenAction(viewPart, workbenchSite.getSelectionProvider());
				open = OpenAction.newInstanceGetSelectionToEditFromActivePage(viewPart, workbenchSite.getSelectionProvider());
			}
		}
	}

	@Override
	public void fillContextMenu(IMenuManager menu) {
		if(open != null && open.isEnabled()) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, open);
		}
	}

	@Override
	public void fillActionBars(IActionBars actionBars) {
		super.fillActionBars(actionBars);
		if (open != null && open.isEnabled()) {
				actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, open);
		}
	}


}