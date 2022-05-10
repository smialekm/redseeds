package eu.redseeds.sc.current.ui.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import eu.redseeds.common.Constants;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.current.ui.Activator;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;

public class MakePatternAction implements IViewActionDelegate {
	
	private IViewPart navigator;

	@Override
	public void run(IAction action) {
		Activator.log("MakePatternAction.run", Status.INFO);
		final IStructuredSelection select = (IStructuredSelection) navigator
			.getViewSite().getSelectionProvider().getSelection();
		
		for(Object selected: getSelectedObject(select)) {
			if(selected instanceof RequirementsPackageDTO) {
				RequirementsPackageDTO selectedPackage = (RequirementsPackageDTO)selected; 
				if((selectedPackage.getStereotypes().contains(Constants.ALP_STEREOTYPE))) {
					selectedPackage.removeStereotype(Constants.ALP_STEREOTYPE);
				}
				else {
					selectedPackage.addStereotype(Constants.ALP_STEREOTYPE);
				}
				getSCProject(select).save();
				refresh();
				return;
			}
		}
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void init(IViewPart view) {
		navigator = view;
		
	}
	
	protected List<Object> getSelectedObject(IStructuredSelection select) {
		ITreeSelection treeSelection = (ITreeSelection) select;

		List<Object> result = new ArrayList<Object>();
		TreePath[] tr = treeSelection.getPaths();

		for (int i = 0; i < tr.length; i++) {
			result.add(tr[i].getLastSegment());
		}
		return result;
	}
	
	protected SCProject getSCProject(IStructuredSelection select) {
		return SCProjectContainer.instance().getSCProject(
				SCProjectHelper.getIProject(select));
	}
	
	protected void refresh() {
		SCProjectHelper.refreshSCNavigator();
	}

}
