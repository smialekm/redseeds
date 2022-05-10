package eu.remics.recovery.manager.applogic.usecases;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.sc.editor.rsl.editors.UseCaseEditorInput;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.manager.presentation.VSimilarScenarioInDetail;
import eu.remics.recovery.manager.presentation.VUseCaseNameInputDialog;
import eu.remics.recovery.manager.views.DetailedSimilarScenariosView;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;
import eu.remics.recovery.model.domainlogic.usecases.MUseCase;
import eu.remics.recovery.model.dto.XScenariosCommonPart;
import eu.remics.util.SCNavigatorHelper;
/**
 * @author Norbert J
 * @version 1.0
 * @created 29-lis-2011 13:52:08
 */
public class CMergeUseCases {

	public UseCaseDTO aUseCase;
	MUseCase nUseCase;
	XScenariosCommonPart aSimilarScenario;

	public MUseCase mUseCase;

	public CMergeUseCases(){
		RecoveryManagerHelper.setCMergeUseCasesObject(this);
	}
	
	public void ShowUseCaseMergeView(){
		aSimilarScenario = RecoveryManagerHelper.getSimilarScenarios();
		if(aSimilarScenario.getScenario().getScenarioSentenceList().isEmpty()
				|| aSimilarScenario.getScenario().getScenarioSentenceList().size() == 1){
			MessageDialog.open(1, new Shell(), "Merge use case error message", "Scenario " + aSimilarScenario.getScenario().getName() + " is empty. Cannot merge use case with empty scenario.", SWT.NONE);
			return;
		}
		
		VSimilarScenarioInDetail vSimilarScenarioInDetail = new VSimilarScenarioInDetail();
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

	public void _ClicksMergeUseCasesOption(){
		ConfirmsMergingUseCase();
	}
	
	public void _ClicksPartialMergeUseCasesOption(){
		ConfirmsPartialMergingUseCase();
	}

	public void ConfirmsMergingUseCase(){
		aSimilarScenario = RecoveryManagerHelper.getSimilarScenarios();
		UseCaseDTO refUseCase = aSimilarScenario.getReferenceScenario().getParent();
		UseCaseDTO similarUseCase = aSimilarScenario.getScenario().getParent();
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) refUseCase);
		
		DetailedSimilarScenariosView view = (DetailedSimilarScenariosView) RecoveryManagerHelper.getDetailedSimilarScenariosView();
		
		String useCaseName = new VUseCaseNameInputDialog().displayForPartiallyMerge(refUseCase.getName());
		
		if(useCaseName == null) return;
		if(rmh.getUseCase(useCaseName) != null 
				&& !refUseCase.getName().equals(useCaseName)
				&& !similarUseCase.getName().equals(useCaseName)){
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "Use Case Name", "Use Case with this name already exists in project. Please rename it.");
			ConfirmsMergingUseCase();
			return;
		}
		
		closeEditor(refUseCase);
		closeEditor(similarUseCase);
		
		MUseCase.merges(refUseCase, similarUseCase, useCaseName, view.getReferencedStepNumber(), aSimilarScenario.getReferenceScenario());
		
		SCNavigatorHelper.refresh();
		
		MessageDialog.open(2, new Shell(), "Merge use case information", "Use cases were merged.", SWT.NONE);
		
		RecoveryManagerHelper.hideDetailedSimilarScenariosView();
		RecoveryManagerHelper.hideFindSimilarScenariosView();
	}
	
	public void ConfirmsPartialMergingUseCase(){
		aSimilarScenario = RecoveryManagerHelper.getSimilarScenarios();
		UseCaseDTO refUseCase = aSimilarScenario.getReferenceScenario().getParent();
		UseCaseDTO similarUseCase = aSimilarScenario.getScenario().getParent();
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) refUseCase);
		
		DetailedSimilarScenariosView view = (DetailedSimilarScenariosView) RecoveryManagerHelper.getDetailedSimilarScenariosView();
		if(similarUseCase.getConstrainedLanguageScenarioDTOList().size() == 1){
			String useCaseName = new VUseCaseNameInputDialog().displayForPartiallyMerge(refUseCase.getName());
			if(useCaseName == null){
				return;
			}
			if(rmh.getUseCase(useCaseName) != null 
					&& !refUseCase.getName().equals(useCaseName)
					&& !similarUseCase.getName().equals(useCaseName)){
				MessageDialog.openError(Display.getCurrent().getActiveShell(), "Use Case Name", "Use Case with this name already exists in project. Please rename it.");
				ConfirmsMergingUseCase();
				return;
			}
			
			closeEditor(refUseCase);
			closeEditor(similarUseCase);
			
			MUseCase.partialMerges(refUseCase, aSimilarScenario.getScenario(), view.getReferencedStepNumber(), aSimilarScenario.getReferenceScenario());
			MUseCase.endMerges(refUseCase, similarUseCase, useCaseName);
			view.refreshScenarioTree();
			view.refreshReferenceScenarioTree();
			SCNavigatorHelper.refresh();
			MessageDialog.open(2, new Shell(), "Move scenario information", "Scenario was moved.", SWT.NONE);
			
			RecoveryManagerHelper.hideDetailedSimilarScenariosView();
			RecoveryManagerHelper.hideFindSimilarScenariosView();
		}
		else{
			closeEditor(refUseCase);
			closeEditor(similarUseCase);
			
			MUseCase.partialMerges(refUseCase, aSimilarScenario.getScenario(), view.getReferencedStepNumber(), aSimilarScenario.getReferenceScenario());
			view.refreshScenarioTree();
			view.refreshReferenceScenarioTree();
			view.refreshScenarioViewer(refUseCase);
			view.refreshSimilarScenarioViewer(similarUseCase);
			int[] shift = MScenario.compareScenarios(refUseCase.getConstrainedLanguageScenarioDTOList().get(0), similarUseCase.getConstrainedLanguageScenarioDTOList().get(0));
			view.initSimilarity(shift, aSimilarScenario.getReferenceScenario().getScenarioSentenceList().size());
			
			SCNavigatorHelper.refresh();
			MessageDialog.open(2, new Shell(), "Move scenario information", "Scenario was moved.", SWT.NONE);
		}
	}
	
	private void closeEditor(UseCaseDTO uc){
		IEditorInput input = new UseCaseEditorInput();
		((UseCaseEditorInput) input).setUseCaseDTO(uc);
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if (input != null) {
			IEditorPart editor = page.findEditor(input);
			page.closeEditor(editor, true);
		}
	}

	public void setUseCase(UseCaseDTO obj){
		aUseCase = obj;
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