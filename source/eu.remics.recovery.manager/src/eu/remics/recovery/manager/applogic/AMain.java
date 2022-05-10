package eu.remics.recovery.manager.applogic;
import eu.remics.recovery.manager.applogic.scenarios.CAssignScenarioToUseCase;
import eu.remics.recovery.manager.applogic.scenarios.CDeassignScenarioFromUseCase;
import eu.remics.recovery.manager.applogic.scenarios.CDeleteScenario;
import eu.remics.recovery.manager.applogic.scenarios.CFindSimilarScenarios;
import eu.remics.recovery.manager.applogic.scenarios.CPreviewScenario;
import eu.remics.recovery.manager.applogic.scenarios.CSplitScenario;
import eu.remics.recovery.manager.applogic.scenarios.CUnsplitScenario;
import eu.remics.recovery.manager.applogic.usecases.CCreateUseCaseFromUnassignedScenario;
import eu.remics.recovery.manager.applogic.usecases.CMergeUseCases;
/**
 * @author Stacja1
 * @version 1.0
 * @created 04-lis-2011 12:20:57
 */
public class AMain {

	public static CAssignScenarioToUseCase cAssignScenarioToUseCase = new CAssignScenarioToUseCase();
	public static CCreateUseCaseFromUnassignedScenario cCreateUseCaseFromUnassignedScenario = new CCreateUseCaseFromUnassignedScenario();
	public static CDeassignScenarioFromUseCase cDeassignScenarioFromUseCase = new CDeassignScenarioFromUseCase();
	public static CDeleteScenario cDeleteScenario = new CDeleteScenario();
	public static CSplitScenario cSplitScenario = new CSplitScenario();
	public static CFindSimilarScenarios cFindsimilarScenarios = new CFindSimilarScenarios();
	public static CMergeUseCases cMergeUseCases = new CMergeUseCases();
	public static CUnsplitScenario cUnsplitScenario = new CUnsplitScenario();
	public static CPreviewScenario cPreviewScenario = new CPreviewScenario();
}