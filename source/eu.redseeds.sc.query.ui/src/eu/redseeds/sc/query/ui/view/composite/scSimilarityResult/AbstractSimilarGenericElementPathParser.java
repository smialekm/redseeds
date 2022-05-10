package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import eu.redseeds.sc.query.engine.SimilarSCProject;
import eu.redseeds.scl.model.rsl.IHierarchyAware;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

abstract class AbstractSimilarGenericElementPathParser implements ISimilarGenericElementPathParser {

	private final SimilarSCProject input;
	// ALWAYS USE THIS INSTEAD OF
	// input.getSimilarityValue().get*Pairs(). This are pairs with elements of type that we support
	private Set<IElementMatch> properElementsPairs = new HashSet<IElementMatch>();

	/** supported types*/
	private  final Set<Class<? extends IHierarchyAware>> properTypes = getProperTypes();

	/** Map<key=supported type,value=Map<key=currElement,value=path to this element>>*/
	private final Map<Class<? extends IHierarchyAware>, Map<IHierarchyAware, List<IHierarchyAware>>> currentElementPath = new HashMap<Class<? extends IHierarchyAware>, Map<IHierarchyAware, List<IHierarchyAware>>>();
	/** Map<key=supported type,value=Map<key=pastElement,value=path to this element>>*/
	private final Map<Class<? extends IHierarchyAware>, Map<IHierarchyAware, List<IHierarchyAware>>> pastElementPath = new HashMap<Class<? extends IHierarchyAware>, Map<IHierarchyAware, List<IHierarchyAware>>>();
	/** Map<key=supported type,value=Map<key=currElement,value=elements that are match(similar) to key>>*/
	private final Map<Class<? extends IHierarchyAware>, Map<IHierarchyAware, Set<IElementMatch>>> curretElementToElementMatchs = new HashMap<Class<? extends IHierarchyAware>, Map<IHierarchyAware, Set<IElementMatch>>>();

	/**
	 * Return set of types that are supported by this implementation
	 * @return set of supported types
	 */
	protected abstract Set<Class<? extends IHierarchyAware>> getProperTypes();

	public AbstractSimilarGenericElementPathParser(SimilarSCProject input) {
		super();
		this.input = input;
		initCollections();
		if (input != null) {
			this.properElementsPairs = prepareProperElementsPairs(getAdaptedElementMatchs(input));
			parse();
		}
	}
	public boolean contains(IHierarchyAware element){
		Set<IHierarchyAware> allHierarchyAware=new HashSet<IHierarchyAware>();
		allHierarchyAware.addAll(getAllHierarchyAware(currentElementPath));
		allHierarchyAware.addAll(getAllHierarchyAware(pastElementPath));
		return allHierarchyAware.contains(element);
	}
	private Set<IHierarchyAware> getAllHierarchyAware(Map<Class<? extends IHierarchyAware>, Map<IHierarchyAware, List<IHierarchyAware>>> collection){
		Set<IHierarchyAware> allHierarchyAware=new HashSet<IHierarchyAware>();
		 for (Entry<Class<? extends IHierarchyAware>, Map<IHierarchyAware, List<IHierarchyAware>>> entry : collection.entrySet()) {
			 for (Entry<IHierarchyAware, List<IHierarchyAware>> entry2 : entry.getValue().entrySet()) {
				 allHierarchyAware.add(entry2.getKey());
				 allHierarchyAware.addAll(entry2.getValue());
			}
		}
		 return allHierarchyAware;
	}
	public boolean contains(IElementMatch elementMatch){
		for (Entry<Class<? extends IHierarchyAware>, Map<IHierarchyAware, Set<IElementMatch>>> entry : curretElementToElementMatchs.entrySet()) {
			for (Entry<IHierarchyAware, Set<IElementMatch>> entry2 : entry.getValue().entrySet()) {
				if(entry2.getValue().contains(elementMatch)){
					return true;
				}
			}
		}
		return false;
	}

	private void initCollections() {
		for (Class<? extends IHierarchyAware> properType : properTypes) {
			currentElementPath.put(properType, new HashMap<IHierarchyAware, List<IHierarchyAware>>());
			pastElementPath.put(properType, new HashMap<IHierarchyAware, List<IHierarchyAware>>());
			curretElementToElementMatchs.put(properType, new HashMap<IHierarchyAware, Set<IElementMatch>>());
		}
	}

	/**
	 * Adapted proper elements match pair to IElementMatch
	 * @param newInput take proper elements match pair from here
	 * @return elements match pair adapted to IElementMatch
	 */
	protected abstract Set<? extends IElementMatch> getAdaptedElementMatchs(SimilarSCProject newInput);

	/**
	 * Remove elements that are not supported
	 *
	 * @return
	 */
	private Set<IElementMatch> prepareProperElementsPairs(Set<? extends IElementMatch> elementMatchs) {
		Set<IElementMatch> result = new HashSet<IElementMatch>();
		Set<? extends IElementMatch> requirementsPairs = elementMatchs;
		for (IElementMatch elementMatch : requirementsPairs) {
			if (isPairOfProperType(elementMatch)) {
				result.add(elementMatch);
			}
		}
		return result;
	}

	private void parse() {
		parseElementPath();
		parseCurretElementsToElementMatchs();
	}

	private void parseElementPath() {
		Set<IElementMatch> localElementPairs = getProperElementPairs();
		if (localElementPairs != null) {
			for (IElementMatch elementMatch : localElementPairs) {
				IHierarchyAware currentElement =  elementMatch
						.getCurrentElement();
				IHierarchyAware pastElement =  elementMatch
						.getPastElement();

				for (Class<? extends IHierarchyAware> properType : properTypes) {
					// thx to prepareProperElementPairs we know that
					// curr and past are the same types
					if (properType.isInstance(currentElement)) {
						parseElementPathStrategy(currentElement,
								currentElementPath.get(properType));
						parseElementPathStrategy(pastElement,
								pastElementPath.get(properType));
					}
				}
			}
		}
	}

	private void parseCurretElementsToElementMatchs() {

		Set<IElementMatch> localElementPairs = getProperElementPairs();
		if (localElementPairs != null) {
			for (IElementMatch elementMatch : localElementPairs) {
				IHierarchyAware currentElement = elementMatch
						.getCurrentElement();
				for (Class<? extends IHierarchyAware> properType : properTypes) {
					// thx to prepareProperElementPairs we know that
					// curr and past are the same types
					if (properType.isInstance(currentElement)) {
						Set<IElementMatch> elementMatchSet = curretElementToElementMatchs
								.get(properType).get(currentElement);
						elementMatchSet = elementMatchSet == null ? new HashSet<IElementMatch>()
								: elementMatchSet;
						elementMatchSet.add(elementMatch);
						curretElementToElementMatchs
								.get(properType).put(currentElement,
										elementMatchSet);
					}
				}
			}
		}
	}

	private void parseElementPathStrategy(
			final IHierarchyAware element,
			final Map<IHierarchyAware, List<IHierarchyAware>> putElementPathHere) {

		if (!putElementPathHere.containsKey(element)) {
			List<IHierarchyAware> path = findPath(element);
			putElementPathHere.put(element, path);
		}
	}

	private List<IHierarchyAware> findPath(
			final IHierarchyAware element) {
		getSoftwareCaseDTOForCurrentRequirement();
		List<IHierarchyAware> result = new ArrayList<IHierarchyAware>();
		for (IHierarchyAware elementsPackageDTO = element
				.getParent(); elementsPackageDTO != null; elementsPackageDTO = elementsPackageDTO
				.getParent()) {
			result.add(elementsPackageDTO);
		}
		return result;
	}

	private boolean isPairOfProperType(IElementMatch elementMatch) {
		for (Class<? extends IHierarchyAware> properType : properTypes) {
			if (properType.isInstance(elementMatch
					.getCurrentElement())
					&& properType.isInstance(elementMatch
							.getPastElement())) {
				return true;
			}
		}
		return false;
	}

	private Set<Object> getChildrenStrategy(
			IHierarchyAware element,
			Class<? extends IHierarchyAware> properType) {

		Map<IHierarchyAware, List<IHierarchyAware>> properSearchHere=currentElementPath.get(properType);

		Set<Object> children = new HashSet<Object>();
		for (Entry<IHierarchyAware, List<IHierarchyAware>> entry : properSearchHere
				.entrySet()) {
			List<IHierarchyAware> path = entry.getValue();
			int parentIdx = path.indexOf(element);
			if (parentIdx != -1) {
				//if there are more elements in the path list then get next path element
				if(parentIdx > 0){
					Object child =path.get(parentIdx - 1);
					children.add(child);
				}
				//if not then take key element
				else{
//					child = entry.getKey();
					//pair of currElement and matching past element
					Set<IElementMatch> matchingSet = curretElementToElementMatchs.get(properType).get(entry.getKey());
					children.addAll(matchingSet);
				}
			}
		}
		return children;
	}
	private Set<IHierarchyAware> getTopElementsPackages(
			Map<IHierarchyAware, List<IHierarchyAware>> getFromHere) {

		Set<IHierarchyAware> topRequirementsPackages = new HashSet<IHierarchyAware>();
		for (Entry<IHierarchyAware, List<IHierarchyAware>> entry : getFromHere
				.entrySet()) {
			List<IHierarchyAware> path = entry.getValue();
			if (!path.isEmpty()) {
				topRequirementsPackages.add(path.get(path.size() - 1));
			}
		}
		return topRequirementsPackages;
	}
	public boolean hasChildren(Object object){
		if(object instanceof IHierarchyAware){
			return !getChildrens((IHierarchyAware) object).isEmpty();
		}
		return false;
	}

	@Override
	public Set<?> getChildrens(
			IHierarchyAware hierarchyAware) {

		Set<?> result=findLeafChildrens(hierarchyAware);
		if(result.isEmpty()){
			result=findChildrensForPackage(hierarchyAware);
		}
		return result;
	}

	private Set<IElementMatch> findLeafChildrens(IHierarchyAware element) {

		for (Class<? extends IHierarchyAware> properType : properTypes) {
			if (properType.isInstance(element)) {
				return curretElementToElementMatchs.get(properType)
						.get(element);
			}
		}
		return new HashSet<IElementMatch>();
	}

	private Set<Object> findChildrensForPackage(
			IHierarchyAware element) {

		Set<Object> children=new  HashSet<Object>();
		for (Class<? extends IHierarchyAware> properType : properTypes) {
			children = getChildrenStrategy(element, properType);
			if(!children.isEmpty()){
				break;
			}
		}
		return children;
	}

	@Override
	public List<IHierarchyAware> getCurentPath(
			IHierarchyAware element) {

		for (Class<? extends IHierarchyAware> properType : properTypes) {
			if(properType.isInstance(element)){
				return currentElementPath.get(properType).get(element);
			}
		}
		return new ArrayList<IHierarchyAware>();
	}

	@Override
	public List<IHierarchyAware> getPastPath(
			IHierarchyAware element) {

		for (Class<? extends IHierarchyAware> properType : properTypes) {
			if(properType.isInstance(element)){
				return pastElementPath.get(properType).get(element);
			}
		}
		return new ArrayList<IHierarchyAware>();
	}

	@Override
	public SoftwareCaseDTO getSoftwareCaseDTOForCurrentRequirement() {
		return input != null ? input.getCurrentScProject().getMainCase() : null;
	}

	@Override
	public SoftwareCaseDTO getSoftwareCaseDTOForPastRequirement() {
		return input != null ? input.getScProject().getMainCase() : null;
	}

	@Override
	public Set<IHierarchyAware> getTopCurrentPackages() {
		Set<IHierarchyAware> result=new HashSet<IHierarchyAware>();
		for (Class<? extends IHierarchyAware> properType : properTypes) {
			result.addAll(getTopElementsPackages(currentElementPath.get(properType)));
		}
		return result;
	}

	private Set<IElementMatch> getProperElementPairs() {
		return properElementsPairs;
	}

	public String getCurentPathAsString(IHierarchyAware element){
		return formatPathName(getSoftwareCaseDTOForCurrentRequirement(), getCurentPath(element),
				element);
	}

	public String getPastPathAsString(IHierarchyAware element){
		return formatPathName(getSoftwareCaseDTOForPastRequirement(), getPastPath(element),
				element);
	}

	private String formatPathName(SoftwareCaseDTO softwareCaseDTO, List<IHierarchyAware> domainElementsPackageDTO, IHierarchyAware requirement) {
		List<String> packageNames =new ArrayList<String>();
		if(domainElementsPackageDTO!=null){
			for (IHierarchyAware req : domainElementsPackageDTO) {
				packageNames.add(req.getName());
			}
		}
		String eleName=requirement!=null?requirement.getName():"";
		return formatPathName(softwareCaseDTO,packageNames,eleName);
	}

	private String formatPathName(SoftwareCaseDTO softwareCaseDTO, List<String> packageNames, String elementName) {
		final String SEPARATOR = "/";
		StringBuilder stringBuilder = new StringBuilder();
		if (softwareCaseDTO != null) {
			stringBuilder.append(softwareCaseDTO.getName());

		}
		stringBuilder.append(SEPARATOR);
		if (packageNames != null) {
			for (String packageName : packageNames) {
				stringBuilder.append(packageName);
				stringBuilder.append(SEPARATOR);
			}
		}
		if (elementName != null) {
			stringBuilder.append(elementName);
		}
		return stringBuilder.toString();
	}
}
