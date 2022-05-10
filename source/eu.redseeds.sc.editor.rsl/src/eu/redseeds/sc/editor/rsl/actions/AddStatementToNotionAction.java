package eu.redseeds.sc.editor.rsl.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;
import eu.remics.recovery.model.preferences.MConfiguration;

public class AddStatementToNotionAction extends Action {
	IWorkbenchPage activePage = null;
	private NotionDTO notion = null;
	private PhraseDTO phrase = null;
	private DomainStatementDTO statement = null;
	private SCProject scProject;
	private BusinessLayerFacade facade; 
	
	public AddStatementToNotionAction(SCProject scProject){
		this.scProject = scProject;
		this.facade = scProject.getBusinessLayerFacade();
		activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(); 
	}
	
	public void setPhrase(PhraseDTO phrase){
		this.phrase = phrase;
	}
	
	@Override
	public void run() {
		statement = facade.createDomainStatementDTO(phrase);
		notion.addDomainStatementDTO(statement);
		if (MConfiguration.isAssignCruds()) MNotion.autoAssignActionType(statement);
		scProject.save();
		SCProjectHelper.refreshSCNavigator();
	}
	
	public NotionDTO getNotion() {
		return notion;
	}

	public void setNotion(NotionDTO notion) {
		this.notion = notion;
	}

	public PhraseDTO getPhrase() {
		return phrase;
	}
}
