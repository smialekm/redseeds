package eu.redseeds.sc.current.ui.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.scl.impl.rsl.rsldomainelements.terms.NounImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.remics.recovery.model.NotionKindHelper;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.preferences.MConfiguration;



public class NotionWithAttributesCreationOperation extends WorkspaceModifyOperation{

	List<NotionDTO> attributes;
	private boolean isDataView;
	private BusinessLayerFacade facade;
	private ConceptDataViewTransformationWizardPageName page;
	
	public NotionWithAttributesCreationOperation(BusinessLayerFacade facade, List<NotionDTO> attributes, boolean dataView, ConceptDataViewTransformationWizardPageName page) {
		this.facade=facade;
		this.attributes=attributes;
		this.isDataView = dataView;
		this.page = page;
	}
	
	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException,
			InvocationTargetException, InterruptedException {
		
		// start task
		monitor.beginTask(
				ResourceMessages.NewClipboardCreationOperation_creating, 2);
		monitor.subTask(ResourceMessages.NewClipboardCreationOperation_project);
		createSCLElement();
		monitor.worked(1);		
	}
	
	private void createSCLElement() {
		NotionDTO notion = facade.createNotionDTO();
		NounPhraseDTO phrase = facade.createNounPhraseDTO();
		NounDTO noun = page.getAssignedNoun();
		phrase.setNoun(noun);
		if (0==noun.getSynonymUid() && SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
			noun.autoAddAndAssignSense();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		((BusinessLayerFacade)((NounImpl)noun).getGraph()).cleanNouns(noun);
		phrase.setNounText(noun.getName());
		notion.setNamePhrase(phrase);	
		
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) notion);
		if (null==rmh.getNotionsPackage("Notions")){
			NotionsPackageDTO np = rmh.getBussinessLayerFacade().createNotionsPackageDTO();
			np.setName("Notions");
			rmh.getDomainSpecification().addNotionsPackageDTO(np);
		}
		rmh.getNotionsPackage("Notions").addNotionDTO(notion);
		if (isDataView)
			NotionKindHelper.setDefaultNotionKindInScenario(notion,true);
		else
			NotionKindHelper.setDefaultNotionKind(notion);
		for (NotionDTO attr:attributes){
			if (!isDataView || MConfiguration.isAllowAttributesForDataViews())
				notion.addNotionDTOAttribute(attr);
			else notion.addRelatedNotion(attr);
		}
		rmh.saveSCProject();
	}

}
