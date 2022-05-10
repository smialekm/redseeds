package eu.remics.common;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.recovery.model.dto.XScenariosCommonPart;


public class RecoveryManagerHelper {
	
	private static Object cAssignScenarioToUseCaseInstance;
	private static Object cMergeUseCasesInstance;
	private static Object cUnsplitScenarioInstance;
	private static UseCaseDTO firstScenarioToUnsplit;
	private static UseCaseDTO secondScenarioToUnsplit;
	private static UseCaseDTO assignTargetUseCase;
	private static int counterOfRelatedRequirementsLabelProviderInvocations = 0;
	
	public static void incrementCounter() {
		counterOfRelatedRequirementsLabelProviderInvocations++;
	}
	
	public static int getCounter() {
		return counterOfRelatedRequirementsLabelProviderInvocations;
	}
	
	public static void resetCounter() {
		counterOfRelatedRequirementsLabelProviderInvocations = 0;
	}
	
	public static IViewPart getUnassignedScenarioListView(){
		IWorkbenchWindow activeWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWindow != null ? activeWindow.getActivePage() : null;
		IPerspectiveDescriptor descr = activePage != null ? activePage.getPerspective() : null;
		if(descr != null && descr.getId().equals(Constans.TALEperspectiveID)){
			return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constans.UnassignedScenarioListViewID);
		}
		return null;
	}

	public static void showAssignScenarioView() {
		try {
			PlatformUI.getWorkbench().showPerspective(Constans.TALEperspectiveID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(Constans.AssignScenarioViewID);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
	}
	
	public static void hideAssignScenarioView(){
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(getAssignScenarioView());
	}
	
	public static void showSplitScenarioView() {
		try {
			PlatformUI.getWorkbench().showPerspective(Constans.TALEperspectiveID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(Constans.SplitScenarioViewID);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
	}
	
	public static void hideSplitScenarioView(){
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(getSplitScenarioView());
	}
	
	public static IViewPart getSplitScenarioView(){
		try {
			IPerspectiveDescriptor descr = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective();
			PlatformUI.getWorkbench().showPerspective(Constans.TALEperspectiveID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constans.SplitScenarioViewID);
			PlatformUI.getWorkbench().showPerspective(descr.getId(), PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			return view;
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void hidePreviewView(){
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(getPreviewView());
	}
	
	public static void showPreviewView() {
		try {
			PlatformUI.getWorkbench().showPerspective(Constans.TALEperspectiveID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(Constans.PreviewViewID);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
	}
	
	public static IViewPart getPreviewView(){
		try {
			IPerspectiveDescriptor descr = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective();
			PlatformUI.getWorkbench().showPerspective(Constans.TALEperspectiveID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constans.PreviewViewID);
			PlatformUI.getWorkbench().showPerspective(descr.getId(), PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			return view;
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void showFindSimilarScenariosView() {
		try {
			PlatformUI.getWorkbench().showPerspective(Constans.TALEperspectiveID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(Constans.FindSimilarScenariosViewID);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
	}
	
	public static void hideFindSimilarScenariosView(){
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(getFindSimilarScenariosView());
	}
	
	public static IViewPart getFindSimilarScenariosView(){
		try {
			IPerspectiveDescriptor descr = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective();
			PlatformUI.getWorkbench().showPerspective(Constans.TALEperspectiveID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constans.FindSimilarScenariosViewID);
			PlatformUI.getWorkbench().showPerspective(descr.getId(), PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			return view;
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void showDetailedSimilarScenariosView() {
		try {
			PlatformUI.getWorkbench().showPerspective(Constans.TALEperspectiveID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(Constans.DetailedSimilarScenariosViewID);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
	}
	
	public static void hideDetailedSimilarScenariosView(){
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(getDetailedSimilarScenariosView());
	}
	
	public static IViewPart getDetailedSimilarScenariosView(){
		try {
			IPerspectiveDescriptor descr = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective();
			PlatformUI.getWorkbench().showPerspective(Constans.TALEperspectiveID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constans.DetailedSimilarScenariosViewID);
			PlatformUI.getWorkbench().showPerspective(descr.getId(), PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			return view;
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static IViewPart getAssignScenarioView(){
		/*IPerspectiveDescriptor descr = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective();
		PlatformUI.getWorkbench().showPerspective(Constans.TALEperspectiveID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constans.AssignScenarioViewID);
		PlatformUI.getWorkbench().showPerspective(descr.getId(), PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		return view;*/
		IPerspectiveDescriptor descr = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective();
		if(descr.getId().equals(Constans.TALEperspectiveID)){
			return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constans.AssignScenarioViewID);
		}
		return null;
	}
	
	public static void showUnassignedScenarioList(){
		try {
			PlatformUI.getWorkbench().showPerspective(Constans.TALEperspectiveID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(Constans.UnassignedScenarioListViewID);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
	}
	
	public static void hideCodePreviewView(){
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(getPreviewView());
	}
	
	public static void showCodePreviewView() {
		try {
			PlatformUI.getWorkbench().showPerspective(Constans.REDSEEDSperspectiveID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(Constans.CodePreviewViewID);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
	}
	
	public static IViewPart getCodePreviewView(){
		try {
			IPerspectiveDescriptor descr = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective();
			PlatformUI.getWorkbench().showPerspective(Constans.REDSEEDSperspectiveID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constans.CodePreviewViewID);
			PlatformUI.getWorkbench().showPerspective(descr.getId(), PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			return view;
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * needed in java reflection to omit plugins cycle loop (not elegant solution)
	 */
	public static void setCAssignScenarioToUseCaseObject(Object cAssignScenarioToUseCaseObject){
		cAssignScenarioToUseCaseInstance = cAssignScenarioToUseCaseObject;
	}
	
	public static Object getInstanceOfCAssignScenarioToUseCase(){
		return cAssignScenarioToUseCaseInstance;
	}
	
	public static void setCMergeUseCasesObject(Object cMergeUseCasesObject){
		cMergeUseCasesInstance = cMergeUseCasesObject;
	}
	
	public static Object getInstanceOfCMergeUseCases(){
		return cMergeUseCasesInstance;
	}
	
	public static void setCUnsplitScenarioObject(Object cUnsplitScenarioObject){
		cUnsplitScenarioInstance = cUnsplitScenarioObject;
	}
	
	public static Object getInstanceOfCUnsplitScenario(){
		return cUnsplitScenarioInstance;
	}
	
	public static void setFirstScenarioToUnsplit(UseCaseDTO scen){
		firstScenarioToUnsplit = scen;
	}
	
	public static UseCaseDTO getFirstScenarioToUnsplit(){
		return firstScenarioToUnsplit;
	}
	
	public static void setSecondScenarioToUnsplit(UseCaseDTO scen){
		secondScenarioToUnsplit = scen;
	}
	
	public static UseCaseDTO getSecondScenarioToUnsplit(){
		return secondScenarioToUnsplit;
	}

	private static XScenariosCommonPart aSimilarScenario;
	private static boolean modelDeleteActionFlag;
	private static boolean diagramDeleteActionFlag;
	private static boolean undoActionFlag;
	private static boolean redoActionFlag;
	
	public static void setSimilarScenarios(XScenariosCommonPart similar){
		aSimilarScenario = similar;
	}
	
	public static XScenariosCommonPart getSimilarScenarios(){
		return aSimilarScenario;
	}
	
	public static boolean isModelDeleteActionOccur(){
		return modelDeleteActionFlag;
	}

	public static void setModelDeleteActionOccuredState(boolean b){
		modelDeleteActionFlag = b;
	}
	
	public static boolean isDeleteUndoRedoActionOccur(){
		return modelDeleteActionFlag || diagramDeleteActionFlag || undoActionFlag || redoActionFlag;
	}
	
	public static void setDiagramDeleteActionOccuredState(boolean b){
		diagramDeleteActionFlag = b;
	}
	
	public static void setUndoActionOccuredState(boolean b){
		undoActionFlag = b;
	}
	
	public static void setRedoActionOccuredState(boolean b){
		redoActionFlag = b;
	}
	
	public static void setAssignTargetUseCase(UseCaseDTO target) {
		assignTargetUseCase = target;
	}
	
	public static UseCaseDTO getAssignTargetUseCase() {
		return assignTargetUseCase;
	}
}
