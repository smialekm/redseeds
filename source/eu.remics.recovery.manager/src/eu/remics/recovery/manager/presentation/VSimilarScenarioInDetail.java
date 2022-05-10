package eu.remics.recovery.manager.presentation;

import org.eclipse.jface.viewers.ViewerFilter;

import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.manager.views.DetailedSimilarScenariosView;
import eu.remics.recovery.manager.views.UseCasesFilter;

public class VSimilarScenarioInDetail {
	
	public void displays(){
		RecoveryManagerHelper.hideDetailedSimilarScenariosView();
		RecoveryManagerHelper.showDetailedSimilarScenariosView();
		((DetailedSimilarScenariosView)RecoveryManagerHelper.getDetailedSimilarScenariosView()).getScenarioTree().setFilters(new ViewerFilter[]{new UseCasesFilter()});
		((DetailedSimilarScenariosView)RecoveryManagerHelper.getDetailedSimilarScenariosView()).getReferencedScenarioTree().setFilters(new ViewerFilter[]{new UseCasesFilter()});
	}
}
