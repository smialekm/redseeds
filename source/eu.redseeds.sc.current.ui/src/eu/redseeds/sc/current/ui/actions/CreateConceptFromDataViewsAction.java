package eu.redseeds.sc.current.ui.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import eu.redseeds.sc.current.ui.wizards.CreateConceptFromDataViewsWizard;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;

public class CreateConceptFromDataViewsAction implements IWorkbenchWindowActionDelegate,
IViewActionDelegate {
	
	private IViewPart navigator;
	
	private int dialogWidth = 655;
	private int dialogHeight = 360;

	@Override
	public void run(IAction action) {
		IStructuredSelection select = (IStructuredSelection) navigator.getViewSite().getSelectionProvider().getSelection();
		List<NotionDTO> notionsList = getSelectedNotions(select);
		
		CreateConceptFromDataViewsWizard wiz = new CreateConceptFromDataViewsWizard();
		
		WizardDialog dialog = new WizardDialog(null, wiz){
			@Override
	        protected void configureShell(Shell newShell) {
				super.configureShell(newShell);
				newShell.setSize(dialogWidth, dialogHeight);
				Shell parentShell = Display.getCurrent().getActiveShell();
				newShell.setLocation( 
						(parentShell.getSize().x - dialogWidth) / 2,
						(parentShell.getSize().y - dialogHeight) / 2 
				);
			}	
		};
		
		wiz.init(notionsList);
		
		dialog.open();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void init(IViewPart view) {
		navigator = view;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow window) {
	}
	
	protected List<NotionDTO> getSelectedNotions(IStructuredSelection select) {
		ITreeSelection treeSelection = (ITreeSelection) select;

		List<NotionDTO> result = new ArrayList<NotionDTO>();
		TreePath[] tr = treeSelection.getPaths();

		for (TreePath element : tr) {
			if(element.getLastSegment() instanceof NotionDTO)
				result.add((NotionDTO) element.getLastSegment());
		}
		return result;
	}

}
