/**
 *
 */
package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import eu.redseeds.engine.navigator.IImageKeys;
import eu.redseeds.scl.model.rsl.IHierarchyAware;
import eu.redseeds.scl.model.rsl.rsldomainelements.DomainElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;

class SCSimilarityMainTreeColumnLabelProvider extends ColumnLabelProvider {
	private final ISimilarGenericElementPathParser similarUseCasePathParser;
	private final ISimilarGenericElementPathParser similarDomainElementPathParser;

	public SCSimilarityMainTreeColumnLabelProvider(ISimilarGenericElementPathParser similarUseCasePathParser,ISimilarGenericElementPathParser similarDomainElementPathParser) {
		super();
		this.similarUseCasePathParser = similarUseCasePathParser;
		this.similarDomainElementPathParser=similarDomainElementPathParser;
	}

	@Override
	public Image getImage(Object element) {
		Image result = null;
		if (element instanceof SoftwareCaseDTO) {
			// result =
			// AbstractUIPlugin.imageDescriptorFromPlugin(EU_REDSEEDS_ENGINE,
			// IImageKeys.DOMAIN_PACKAGE).createImage();
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
		}

		//UC
		 if(element == TopElement.REQS_PACKS){
			result = AbstractUIPlugin.imageDescriptorFromPlugin(SCSimilarityResultComposite.EU_REDSEEDS_ENGINE, IImageKeys.REQUIREMENTS_PACKAGE).createImage();
		}
		if (element instanceof RequirementsPackageDTO) {
			result = AbstractUIPlugin.imageDescriptorFromPlugin(SCSimilarityResultComposite.EU_REDSEEDS_ENGINE, IImageKeys.REQUIREMENTS_PACKAGE).createImage();
		}
		if(element instanceof UseCaseDTO){
			result = AbstractUIPlugin.imageDescriptorFromPlugin(SCSimilarityResultComposite.EU_REDSEEDS_ENGINE, IImageKeys.USE_CASE).createImage();
		}else if (element instanceof Requirement) {
			result = AbstractUIPlugin.imageDescriptorFromPlugin(SCSimilarityResultComposite.EU_REDSEEDS_ENGINE, IImageKeys.REQUIREMENT).createImage();
		}

		//Domain
		if(element == TopElement.DOMAIN){
			result = AbstractUIPlugin.imageDescriptorFromPlugin(SCSimilarityResultComposite.EU_REDSEEDS_ENGINE, IImageKeys.DOMAIN_PACKAGE).createImage();
		}
		if (element instanceof DomainElementsPackageDTO) {
			result = AbstractUIPlugin.imageDescriptorFromPlugin(SCSimilarityResultComposite.EU_REDSEEDS_ENGINE, IImageKeys.DOMAIN_PACKAGE).createImage();
		}

		if (element instanceof ActorDTO) {
			result = AbstractUIPlugin.imageDescriptorFromPlugin(SCSimilarityResultComposite.EU_REDSEEDS_ENGINE, IImageKeys.ACTOR).createImage();
		}
		if (element instanceof SystemElementDTO) {
			result = AbstractUIPlugin.imageDescriptorFromPlugin(SCSimilarityResultComposite.EU_REDSEEDS_ENGINE, IImageKeys.SYSTEM_ELEMENT).createImage();
		}
		if (element instanceof NotionDTO) {
			result = AbstractUIPlugin.imageDescriptorFromPlugin(SCSimilarityResultComposite.EU_REDSEEDS_ENGINE, IImageKeys.NOTION).createImage();
		}
		if (element instanceof IElementMatch) {
			IElementMatch elementMatch=(IElementMatch) element;
			result = getImage(elementMatch.getCurrentElement());
		}
		if(element instanceof LeafHierarchyAware){
			LeafHierarchyAware leafHierarchyAware=(LeafHierarchyAware) element;
			result=getImage(leafHierarchyAware.getHierarchyAware());
		}

		return result;
	}

	@Override
	public String getText(Object element) {
		String result = null;

		if (element instanceof SoftwareCaseDTO) {
			SoftwareCaseDTO caseDTO = (SoftwareCaseDTO) element;
			result = caseDTO.getName();
		}else if(element instanceof TopElement){
			TopElement topElement=(TopElement) element;
			return topElement.getName();
		}else if(element instanceof LeafHierarchyAware){
			LeafHierarchyAware leafHierarchyAware=(LeafHierarchyAware) element;
			IElementMatch elementMatch = leafHierarchyAware.getElementMatch();
			if(similarUseCasePathParser.contains(elementMatch)){
				result = similarUseCasePathParser.getPastPathAsString(leafHierarchyAware.getHierarchyAware());
			}
			if(similarDomainElementPathParser.contains(elementMatch)){
				result = similarDomainElementPathParser.getPastPathAsString(leafHierarchyAware.getHierarchyAware());
			}
		}else if (element instanceof IHierarchyAware) {
			IHierarchyAware hierarchyAware = (IHierarchyAware) element;
			result = hierarchyAware.getName();
		}else if (element instanceof IElementMatch) {
			IElementMatch elementMatch = (IElementMatch) element;
			IHierarchyAware useCaseDTO = elementMatch.getCurrentElement();
			result=useCaseDTO.getName();

		}
		return result;
	}
}