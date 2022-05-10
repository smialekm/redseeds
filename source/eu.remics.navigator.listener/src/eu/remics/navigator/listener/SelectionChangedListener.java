package eu.remics.navigator.listener;

import java.util.Iterator;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import eu.redseeds.engine.navigator.SCNavigator.ProjectSelectListener;
import eu.redseeds.engine.navigator.dnd.SCLElementTreeDropAdapter;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.manager.applogic.AMain;
import eu.remics.recovery.manager.views.UnassignedScenariosView;
import eu.remics.recovery.model.domainlogic.usecases.MUnassignedScenariosList;
import eu.remics.util.SCNavigatorHelper;

public class SelectionChangedListener implements ISelectionListener {
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if(selection instanceof ITreeSelection) {
			
			ProjectSelectListener.processSelection(part, selection);
			
			ITreeSelection selection1 = (ITreeSelection)selection;
			for (Iterator<?> iterator = selection1.iterator(); iterator.hasNext();) {
				Object domain = (Object)iterator.next();
				UnassignedScenariosView uslv = (UnassignedScenariosView) RecoveryManagerHelper.getUnassignedScenarioListView();
				/*
				 * special cases for disable buttons on Unassigned List
				 */
				if(!(domain instanceof IProject) && !(domain instanceof IFolder)){
					if(MUnassignedScenariosList.getUnnasignedScenarios() != null){
						SCProjectContainer.instance().getSCProject(domain);
						if(uslv != null)
							uslv.disableButtons();
					}
				}
				else if(domain instanceof IProject && !(domain instanceof IFolder)){
					if(MUnassignedScenariosList.getUnnasignedScenarios() != null){
						SCProjectContainer.instance().getSCProject((IProject)domain);
						if(uslv != null)
							uslv.disableButtons();
					}
				}
				/*else if(domain instanceof IFolder){
					ProjectSelectListener.processSelection(part, selection);
				}*/
				
				/*
				 * handling dynamic change of dragging to navigator from unassigned list
				 */
				if(SCNavigatorHelper.getViewer() != null && SCNavigatorHelper.getViewer().dropTarget != null){
					for(DropTargetListener a : SCNavigatorHelper.getViewer().dropTarget.getDropListeners())
						SCNavigatorHelper.getViewer().dropTarget.removeDropListener(/*(SCLElementTreeDropAdapter)*/a);
					
					SCNavigatorHelper.getViewer().dropTarget.addDropListener(SCNavigatorHelper.getViewer().dropAdapter);
				}
				
				/*
				 * setting things for application logic goes here
				 */
				if(domain instanceof UseCaseDTO){
					UseCaseDTO obj = (UseCaseDTO) domain;
					AMain.cMergeUseCases.setUseCase(obj);
				}
				else if(domain instanceof ConstrainedLanguageScenarioDTO){
				}
				else if(domain instanceof RequirementsPackageDTO){
					RequirementsPackageDTO obj = (RequirementsPackageDTO) domain;
					AMain.cCreateUseCaseFromUnassignedScenario.setUseCasePackage(obj);
				}
			}
		}
	}
}

