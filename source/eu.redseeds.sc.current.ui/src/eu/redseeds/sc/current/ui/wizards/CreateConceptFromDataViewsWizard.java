package eu.redseeds.sc.current.ui.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.Wizard;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.ui.Activator;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;

public class CreateConceptFromDataViewsWizard extends Wizard {

	protected ConceptDataViewTransformationWizardPage page;
	protected ConceptDataViewTransformationWizardPageName pageName;
	private List<NotionDTO> notionsList;
	private BusinessLayerFacade facade;

	@Override
	public boolean performFinish() {
		try{
			getContainer().run(false, true, new NotionWithAttributesCreationOperation(facade, page.getAttributes(), false, pageName));
			SCProjectHelper.refreshSCNavigator();
			return true;
		} catch (InvocationTargetException e) {
			Activator.log(e.getMessage(), Status.ERROR); 
		} catch (InterruptedException e) {
			Activator.log(e.getMessage(), Status.ERROR);
		}
		return false;
	}
	
	@Override
	public boolean performCancel() {
		pageName.deleteNoun();
        return true;
	}
	
	public void init(List<NotionDTO> notionsList){
		this.notionsList=notionsList;
		facade = (BusinessLayerFacade) ((Notion) notionsList.get(0)).getGraph();
	}
	
	@Override
	public void addPages() {
		page = new ConceptDataViewTransformationWizardPage(MNotion.getRelatedAttributesFromNotions(notionsList),ResourceMessages.CreateConceptFromDataViews_windowTitle,ResourceMessages.CreateConceptFromDataViews_title,ResourceMessages.CreateConceptFromDataViews_description);
		addPage(page);
		pageName = new ConceptDataViewTransformationWizardPageName(facade,ResourceMessages.CreateConceptFromDataViews_windowTitle,ResourceMessages.CreateConceptFromDataViews_title);
		addPage(pageName);
	}
	
}
