package eu.remics.recovery.manager.applogic.scenarios;
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
import eu.remics.recovery.manager.applogic.ActionsFactory;
import eu.remics.recovery.manager.presentation.VAssignScenarioWindow;
import eu.remics.recovery.manager.views.AssignScenarioView;
import eu.remics.recovery.manager.views.UseCasesFilter;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;
import eu.remics.recovery.model.domainlogic.usecases.MUseCase;
import eu.remics.util.SCNavigatorHelper;
/**
 * @author Stacja1
 * @version 1.0
 * @created 04-lis-2011 12:21:00
 */
public class CAssignScenarioToUseCase {

	public UseCaseDTO aScenario;
	UseCaseDTO previous;
	public UseCaseDTO aUseCase = null;
	MUseCase nUseCase;
	public VAssignScenarioWindow vAssignScenarioWindow;
	MScenario mScenario;
	public MUseCase mUseCase;
	private ViewerFilter filter = new UseCasesFilter();
	public boolean flag_similar = false;

	public CAssignScenarioToUseCase(){

	}

	/**
	 * 
	 * @param pScenario    redseeds_uid-6211133039110748816--8135591141382841873-
	 * 6115339205011704755--2559689315685506257redseeds_uid
	 */
	public void ClicksAssignScenarioButton(){
		if(aScenario.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().isEmpty()){
			MessageDialog.open(1, new Shell(), "Attach scenario error message", "Scenario is empty. Cannot attach empty scenario to use case.", SWT.NONE);
			return;
		}
		vAssignScenarioWindow = new VAssignScenarioWindow();
		vAssignScenarioWindow.cAssignScenarioToUseCase = this;
		vAssignScenarioWindow.displays(filter);
		
		if(flag_similar){
			AssignScenarioView view = (AssignScenarioView)RecoveryManagerHelper.getAssignScenarioView();
			view.setSimilarScenarios(MScenario.findScenarioCommonParts());
			flag_similar = false;
		}
	}
	
	public void DropOnAssignScenario(){
		UseCaseDTO target = RecoveryManagerHelper.getAssignTargetUseCase();
		
		this.setFilter(new InternalUseCasesFilter(target));
		this.ClicksAssignScenarioButton();
		
		AssignScenarioView view = (AssignScenarioView) RecoveryManagerHelper.getAssignScenarioView();
		view.getScenarioTable().setInput(target.getConstrainedLanguageScenarioDTOList().get(0));
		view.btnDown.setEnabled(true);
		ActionsFactory.disableAction(ActionsFactory.ATTACH);
		
		vAssignScenarioWindow.setScenario(target.getConstrainedLanguageScenarioDTOList().get(0));
		vAssignScenarioWindow.setUseCase(target);
		
		int[] shift = MScenario.compareScenarios(target.getConstrainedLanguageScenarioDTOList().get(0), this.getScenario().getConstrainedLanguageScenarioDTOList().get(0));
		view.initSimilarity(shift, target.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().size());
	}

	public int SelectsOption(int stepNumber){
		int res=0;
		if (res == 0 /*User clicks save*/) {
			aUseCase = vAssignScenarioWindow.getUseCase();
			if (aUseCase.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().size() == 1
					|| aUseCase.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().size() == 0/*First scenario*/) {
				MUseCase.assigns(aUseCase, aScenario, 0, null);
				
				SCNavigatorHelper.refresh();
				((AssignScenarioView)RecoveryManagerHelper.getAssignScenarioView()).getAssignScenarioTree().refresh();
				return res;
			}
			else if(!aScenario.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().isEmpty()){
				MUseCase.assigns(aUseCase, aScenario, stepNumber, vAssignScenarioWindow.getReferencedScenario());
				
				SCNavigatorHelper.refresh();
				((AssignScenarioView)RecoveryManagerHelper.getAssignScenarioView()).getAssignScenarioTree().refresh();
				return res;
			}
			res = 1;
		}
		else if (res == 1 /*User clicks cancel or alternative scenarios is empty*/) {
		}
		return res;
	}
	
	public void setScenario(UseCaseDTO dto){
		aScenario = dto;
	}
	
	public UseCaseDTO getScenario() {
		return aScenario;
	}
	
	public void setFilter(ViewerFilter filter){
		this.filter  = filter;
	}
	
	class InternalUseCasesFilter extends ViewerFilter{
		
		private UseCaseDTO uc;

		public InternalUseCasesFilter(UseCaseDTO origin){
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