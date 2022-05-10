package eu.remics.navigator.providers;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.engine.navigator.IImageKeys;
import eu.redseeds.engine.navigator.providers.ImageCache;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class ViewLabelProvider extends LabelProvider {
	@Override
	public String getText(Object obj) {
		/*if(obj instanceof XTopPackage){
			return ((XTopPackage)obj).getName();
		}*/
		if(obj instanceof UseCaseDTO){
			return ((UseCaseDTO)obj).getName();
		}
		else if(obj instanceof NotionDTO){
			return ((NotionDTO)obj).getName();
		}
		else if(obj instanceof ConstrainedLanguageScenarioDTO){
			return ((ConstrainedLanguageScenarioDTO)obj).getName();
		}
		else if(obj instanceof NotionsPackageDTO){
			return ((NotionsPackageDTO)obj).getName();
		}
		else if(obj instanceof RequirementsPackageDTO){
			return ((RequirementsPackageDTO)obj).getName();
		}
		return obj.toString();
	}
	@Override
	public Image getImage(Object obj) {
		if (obj instanceof NotionsPackageDTO)
			return ImageCache.getImage(IImageKeys.DOMAIN_PACKAGE);
		else if (obj instanceof RequirementsPackageDTO)
			return ImageCache.getImage(IImageKeys.REQUIREMENTS_PACKAGE);
		else if (obj instanceof NotionDTO)
			return ImageCache.getImage(IImageKeys.NOTION);
		else if (obj instanceof UseCaseDTO)
			return ImageCache.getImage(IImageKeys.USE_CASE);
		else if (obj instanceof ConstrainedLanguageScenarioDTO)
			return ImageCache.getImage(IImageKeys.SCENARIO);
		/*else if (obj instanceof XTopPackage)
			return ImageCache.getImage(IImageKeys.PACKAGE);*/
		return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
	}
}