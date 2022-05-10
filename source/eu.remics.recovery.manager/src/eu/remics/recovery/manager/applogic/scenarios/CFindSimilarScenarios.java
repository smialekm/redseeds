package eu.remics.recovery.manager.applogic.scenarios;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.manager.applogic.AMain;
import eu.remics.recovery.manager.applogic.ActionsFactory;
import eu.remics.recovery.manager.presentation.VFindSimilarScenariosWindow;
import eu.remics.recovery.manager.presentation.VSimilarScenarioInDetail;
import eu.remics.recovery.manager.views.AssignScenarioView;
import eu.remics.recovery.manager.views.DetailedSimilarScenariosView;
import eu.remics.recovery.manager.views.FindSimilarScenariosView;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;
import eu.remics.recovery.model.domainlogic.usecases.MUnassignedScenariosList;
import eu.remics.recovery.model.dto.XScenariosCommonPart;

public class CFindSimilarScenarios {
	public VFindSimilarScenariosWindow vFindSimilarScenariosWindow;
	public VSimilarScenarioInDetail vSimilarScenarioInDetail;
	private XScenariosCommonPart aSimilarScenario;

	public void ClicksFindSimilarScenarios(){
		List<XScenariosCommonPart> similar = MScenario.findScenarioCommonParts();
		if(similar.isEmpty()){
			MessageDialog.openInformation(new Shell(), "Similar scenarios status", "Similar scenarios were not found.");
		}
		else{
			vFindSimilarScenariosWindow = new VFindSimilarScenariosWindow();
			vFindSimilarScenariosWindow.displays();
			FindSimilarScenariosView view = (FindSimilarScenariosView) RecoveryManagerHelper.getFindSimilarScenariosView();
			view.getViewer().setInput(similar);
		}
	}
	
	public void ShowInDetail(){
		if(MUnassignedScenariosList.isInUnassignedList(aSimilarScenario.getReferenceScenario()) 
				&& MUnassignedScenariosList.isInUnassignedList(aSimilarScenario.getScenario())){
			MessageDialog.open(2, new Shell(), "Information", "Both scenarios are in detached list and cannot be merged or attached.", SWT.NONE);
		}
		else if(MUnassignedScenariosList.isInUnassignedList(aSimilarScenario.getReferenceScenario()) ){
			AMain.cAssignScenarioToUseCase.setScenario(aSimilarScenario.getReferenceScenario().getParent());
			
			AMain.cAssignScenarioToUseCase.setFilter(new UseCasesFilter(aSimilarScenario.getScenario().getParent()));
			AMain.cAssignScenarioToUseCase.flag_similar = true;
			AMain.cAssignScenarioToUseCase.ClicksAssignScenarioButton();
			
			AssignScenarioView view = (AssignScenarioView) RecoveryManagerHelper.getAssignScenarioView();
			view.getScenarioTable().setInput(aSimilarScenario.getScenario());
			
			view.btnDown.setEnabled(true);
			ActionsFactory.disableAction(ActionsFactory.ATTACH);
			
			AMain.cAssignScenarioToUseCase.vAssignScenarioWindow.setScenario(aSimilarScenario.getScenario());
			AMain.cAssignScenarioToUseCase.vAssignScenarioWindow.setUseCase(aSimilarScenario.getScenario().getParent());
			
			int[] shift = MScenario.compareScenarios(aSimilarScenario.getScenario(), aSimilarScenario.getReferenceScenario());
			view.initSimilarity(shift, aSimilarScenario.getScenario().getScenarioSentenceList().size());
		}
		else if(MUnassignedScenariosList.isInUnassignedList(aSimilarScenario.getScenario())){
			AMain.cAssignScenarioToUseCase.setScenario(aSimilarScenario.getScenario().getParent());
			
			AMain.cAssignScenarioToUseCase.setFilter(new UseCasesFilter(aSimilarScenario.getReferenceScenario().getParent()));
			AMain.cAssignScenarioToUseCase.flag_similar = true;
			AMain.cAssignScenarioToUseCase.ClicksAssignScenarioButton();
			
			AssignScenarioView view = (AssignScenarioView) RecoveryManagerHelper.getAssignScenarioView();
			view.getScenarioTable().setInput(aSimilarScenario.getReferenceScenario());
			
			view.btnDown.setEnabled(true);
			ActionsFactory.disableAction(ActionsFactory.ATTACH);
			
			AMain.cAssignScenarioToUseCase.vAssignScenarioWindow.setScenario(aSimilarScenario.getReferenceScenario());
			AMain.cAssignScenarioToUseCase.vAssignScenarioWindow.setUseCase(aSimilarScenario.getReferenceScenario().getParent());
			
			int[] shift = MScenario.compareScenarios(aSimilarScenario.getReferenceScenario(), aSimilarScenario.getScenario());
			view.initSimilarity(shift, aSimilarScenario.getReferenceScenario().getScenarioSentenceList().size());
		}
		else{
			vSimilarScenarioInDetail = new VSimilarScenarioInDetail();
			vSimilarScenarioInDetail.displays();

			DetailedSimilarScenariosView view = (DetailedSimilarScenariosView) RecoveryManagerHelper.getDetailedSimilarScenariosView();
			view.getScenarioViewer().setInput(aSimilarScenario.getReferenceScenario());
			view.getSimilarScenarioViewer().setInput(aSimilarScenario.getScenario());

			view.getScenarioTree().setFilters(new ViewerFilter[]{new UseCasesFilter(aSimilarScenario.getReferenceScenario().getParent())});
			view.getScenarioTree().expandAll();

			view.getReferencedScenarioTree().setFilters(new ViewerFilter[]{new UseCasesFilter(aSimilarScenario.getScenario().getParent())});
			view.getReferencedScenarioTree().expandAll();

			int[] shift = MScenario.compareScenarios(aSimilarScenario.getReferenceScenario(), aSimilarScenario.getScenario());
			view.initSimilarity(shift, aSimilarScenario.getReferenceScenario().getScenarioSentenceList().size());
		}
	}
	
	public void setSimilarScenarios(XScenariosCommonPart similar){
		aSimilarScenario = similar;
		RecoveryManagerHelper.setSimilarScenarios(aSimilarScenario);
	}
	
	class UseCasesFilter extends ViewerFilter{
		
		private UseCaseDTO uc;

		public UseCasesFilter(UseCaseDTO origin){
			uc = origin;
		}

		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			if(element instanceof RequirementsPackageDTO){
				RequirementsPackageDTO recoveredPack = (RequirementsPackageDTO) element;
				if(recoveredPack.getName().equalsIgnoreCase("RecoveredScenarios")){
					return false;
				}
			}
			return (element instanceof RequirementsSpecificationDTO || element instanceof RequirementsPackageDTO || (element instanceof UseCaseDTO && element.equals(uc)) ||
					element instanceof ConstrainedLanguageScenarioDTO);
		}
	}
}
