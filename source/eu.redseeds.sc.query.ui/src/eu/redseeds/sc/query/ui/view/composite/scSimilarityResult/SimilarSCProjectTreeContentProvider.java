package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ITreePathContentProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;

import eu.redseeds.sc.query.engine.SimilarSCProject;
import eu.redseeds.sc.query.ui.Activator;
import eu.redseeds.scl.model.rsl.IHierarchyAware;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

class SimilarSCProjectTreeContentProvider implements
		ITreePathContentProvider {

	private ISimilarGenericElementPathParser similarUseCasePathParser;
	private ISimilarGenericElementPathParser similarDomainElementPathParser;

	private static List<TopElement> topElements = new ArrayList<TopElement>();
	static {
		topElements.add(TopElement.REQS_PACKS);
		topElements.add(TopElement.DOMAIN);
	}

	@Override
	public Object[] getChildren(TreePath parentPath) {
		Object[] result = new Object[0];
		if (parentPath.getLastSegment() instanceof SoftwareCaseDTO) {
			result = topElements.toArray();
		}

		// UC
		if (parentPath.getLastSegment() == TopElement.REQS_PACKS) {
			result = similarUseCasePathParser
					.getTopCurrentPackages().toArray();
		}

		// Domain elements
		if (parentPath.getLastSegment() == TopElement.DOMAIN) {
			result = similarDomainElementPathParser
					.getTopCurrentPackages().toArray();
		}

		if(parentPath.getLastSegment() instanceof IElementMatch){
			IElementMatch elementMatch=(IElementMatch) parentPath.getLastSegment();
			result=Arrays.asList(new LeafHierarchyAware(elementMatch)).toArray();
		}

		if (parentPath.getLastSegment() instanceof IHierarchyAware) {
			IHierarchyAware hierarchyAware = (IHierarchyAware) parentPath
					.getLastSegment();
			Set<?> childrens = similarUseCasePathParser.getChildrens(
					hierarchyAware);
			if(childrens.isEmpty()){
				childrens=similarDomainElementPathParser.getChildrens(hierarchyAware);
			}
			result = childrens.toArray();
		}
		return result;
	}

	@Override
	public TreePath[] getParents(Object element) {
		return new TreePath[0];
	}

	@Override
	public boolean hasChildren(TreePath path) {
		boolean result=false;
		Object lastSegment = path.getLastSegment();
		if(lastSegment instanceof TopElement){
			result=true;
		}else if(lastSegment instanceof SoftwareCaseDTO){
			result=true;
		}else if(lastSegment instanceof LeafHierarchyAware){
			result=false;
		}else if(lastSegment instanceof IElementMatch){
			result=true;
		}else if(result==false){
			result=similarUseCasePathParser.hasChildren(lastSegment) || similarDomainElementPathParser.hasChildren(lastSegment);
		}
		return result;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return similarUseCasePathParser
				.getSoftwareCaseDTOForCurrentRequirement() != null ? Collections
				.singleton(
						similarUseCasePathParser
								.getSoftwareCaseDTOForCurrentRequirement())
				.toArray()
				: new Object[0];

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	@SuppressWarnings("static-access")
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (newInput != null) {
			if (newInput instanceof SimilarSCProject) {
				SimilarSCProject input = (SimilarSCProject) newInput;
				similarUseCasePathParser = new SimilarUseCasePathParser(input);
				similarDomainElementPathParser = new SimilarDomainElementPathParser(
						input);
			} else {
				Activator.getDefault().log("not supported type of input data",
						IStatus.WARNING);
			}
		}
	}

}
