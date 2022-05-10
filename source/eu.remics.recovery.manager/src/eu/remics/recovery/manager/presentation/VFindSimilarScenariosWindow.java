package eu.remics.recovery.manager.presentation;

import eu.remics.common.RecoveryManagerHelper;

public class VFindSimilarScenariosWindow {
	
	public void displays(){
		RecoveryManagerHelper.hideFindSimilarScenariosView();
		RecoveryManagerHelper.showFindSimilarScenariosView();
	}
}