package eu.redseeds.sc.slicing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.slicing.impl.SliceFactory;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rsldomainelements.notions.DomainStatement;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ComplexVerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.Phrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.SimpleVerbPhrase;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.Predicate;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.Subject;

public class Slicer {

	private static Map<SliceType, String> sliceTypeToRPE;

	private static void createSliceTypeToRPEMap() {
		sliceTypeToRPE = new HashMap<SliceType, String>();
		sliceTypeToRPE.put(SliceType.MINIMAL_SLICE, MINIMAL_SLICE_RPE);
		sliceTypeToRPE.put(SliceType.MAXIMAL_SLICE, MAXIMAL_SLICE_RPE);
		sliceTypeToRPE.put(SliceType.DOMAIN_INCLUDING_SLICE,
				USECASE_WITH_DOMAIN_RPE);
		sliceTypeToRPE.put(SliceType.IDEAL_SLICE, IDEAL_SLICE_RPE);
	}

	/**
	 * The regular path expression for computing maximal slices.
	 */
	private static String MAXIMAL_SLICE_RPE = "<->*";

	public final static String RPE_IMPORT_DECLARATIONS = 
		"import rsl.rslrequirements.requirementsspecifications.*; " +
		"import rsl.rslkernel.elements.*; " +
		"import rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.*; " +
		"import rsl.rslrequirementrepresentations.interactionrepresentations.*; " +
		"import rsl.rslrequirementrepresentationsentences.svosentences.*; " +
		"import rsl.rslrequirementrepresentations.activityrepresentations.*; " +
		"import rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.*; " +
		"import rsl.rslrequirementrepresentationsentences.interactionsentenceconstructs.*; " +
		"import rsl.rslrequirementrepresentationsentences.controlsentences.*; " +
		"import rsl.rsldomainelements.phrases.*; " +
		"import rsl.rsldomainelements.terms.*; " +
		"import rsl.rsldomainelements.domainelements.*; " +
		"import rsl.rsldomainelements.notions.*; " + 
		"import rsl.rslrequirementrepresentations.uibehaviourrepresentations.*; " +
		"import sclkernel.*; " +
		"import uml.activities.basicactivities.*; " +
		"import uml.activities.intermediateactivities.*; " +
		"import uml.classes.interfaces.*; " +
		"import uml.classes.kernel.*; " +
		"import uml.classes.dependencies.*; " +
		"import uml.usecases.*; " +
		"import uml.interactions.basicinteractions.*; " +
		"import uml.interactions.fragments.*; " +
		"import uml.compositestructures.internalstructures.*; " +
		"import uml.components.basiccomponents.*; " +
		"import rsl.rslrequirements.requirementrelationships.*; " +
		"import java5.statements.*; " +
		"import java5.types.*; " +
		"import java5.typespecifications.*; " +
		"import java5.programs.*; " + 
		"import java5.basiclanguageelements.*; " +
		"import java5.comments.*; " +
		"import java5.expressions.*; " +
		"import java5.members.*; " +
		"import java5.programs.*; " +
		"import java5.annotations.*;";
	

	/**
	 * The regular path expression for computing minimal slices.
	 */
	private final static String MINIMAL_SLICE_RPE = " <->{FromLinksToSource, ToLinksToTarget, InvokeLinksToSource, InvokedLinksToTarget}*"
			+ "  (-->{RepresentableElementContainsName} | -->{RequirementContainsRepresentation}"
			+ "    (<>-- "
			+ "    | -->{ModalVerbLinkLinksToTarget, ModifierLinkLinksToTarget, "
			+ "         DeterminerLinkLinksToTarget, NounLinkLinksToTarget, "
			+ "         PrepositionLinkLinksToTarget, VerbLinkLinksToTarget}"
			+ "    )*"
			+ "  | [<--{AllocationToUMLLinksToAllocationSource, SatisfiesLinksToSatisfactionTarget} "
			+ "     -->{AllocationToRSLLinksToAllocationTarget, SatisfiesLinksToSatisfactionSource}"
			+ "      [<--{ClientDependentOnLinksToDependencySource}-->{SupplierDependentOnLinksToDependencyTarget}"
			+ "        [<--{ImplementsLinksToImplementationTarget}-->{ImplementsLinksToImplementationSource}]"
			+ "      ]" + "    ]" + "  ) -->{RepresentableElementContainsName}*";

	/**
	 * The regular path expression for computing usecases with domain slice
	 */
	private final static String USECASE_WITH_DOMAIN_RPE =
		" <>--* -->{TermHyperlinkLinksToTarget}* " +
		" [ ([<--{PredicateContainsTarget}]-->{LinkingPredicateLinksToDomainStatement} [<--{DomainElementContainsStatement}]) |-->{LinkingSubjectLinksToDomainElement}] " +
		" <>--* -->{TermHyperlinkLinksToTarget}* ";


	private static String IDEAL_SLICE_RPE = "( (<--{FromLinksToSource}&{DependsOn}-->{ToLinksToTarget} "
			+ " | <--{InvokeLinksToSource}-->{InvokedLinksToTarget}"
			+ // traverse relationships between requirements from source to
			// target (DependsOn, InvocationRelationship)
			" | <--{ToLinksToTarget}&{Constrains, MakesPossible}-->{FromLinksToSource}"
			+ // traverse relationships between requirements from target to
			// source (Constrains, MakesPossible)
			" | <--{FromLinksToSource, ToLinksToTarget}&{DerivesFrom, Elaborates, Fulfils, IsSimilarTo, Operationalizes}-->{FromLinksToSource, ToLinksToTarget}"
			+ " )*"
			+ // traverse relationships between requirements in both directions
			// (DerivesFrom, Elaborates, IsSimilarTo, Operationalizes,
			// Fulfils)
			" (-->{RepresentableElementContainsName} | -->{RequirementContainsRepresentation}"
			+ // include requirements' representations
			"   (<>-- "
			+ // include components of requirement representations
			"   | -->{ModalVerbLinkLinksToTarget, ModifierLinkLinksToTarget, "
			+ "         DeterminerLinkLinksToTarget, NounLinkLinksToTarget, "
			+ "         PrepositionLinkLinksToTarget, VerbLinkLinksToTarget}"
			+ "   )*"
			// include elements of representations which are not connected by
			// aggregations but by LinksTo-edges
			+ " ( (-->{LinkingPredicateLinksToDomainStatement} [<--{DomainElementContainsStatement}]) "
		    + "      | -->{LinkingSubjectLinksToDomainElement}) <>--* "
		    // include domain elements 
			+ " | [<--{AllocationToUMLLinksToAllocationSource, SatisfiesLinksToSatisfactionTarget}"
			+ "    -->{AllocationToRSLLinksToAllocationTarget, SatisfiesLinksToSatisfactionSource} "
			+ "       [<--{SupplierDependentOnLinksToDependencyTarget}-->{ClientDependentOnLinksToDependencySource}]"
			+ // include architecture elements linked to requirements by
			// Satisfies or by IsAllocatedTo relationships, include design
			// elements linked to architecture elements by IsDependentOn
			// relationships
			"       (<>-- "
			+ "       | -->{OwningPropertyLinksToDefaultValue, EndLinksToRole} "
			+ // include elements nested in architecture or design elements
			// which are not connected by aggregations but by LinksTo-edges
			// from whole to part
			"       | <--{SuperPropertyLinksToSubsettedProperty} "
			+ // include elements nested in architecture or design elements
			// which are not connected by aggregations but by LinksTo-edges
			// from part to whole
			" 		| <--{ClientDependencyLinksToClient, ComponentRealizationLinksToRealizingClassifier, "
			+ // traverse UML traceability relationships between architecture or
			// between design elements - from client to supplier / importer
			// to imported direction
			"		      ExtenderLinksToExtendedCase, PackageImportLinksToImportingNamespace, "
			+ "			  ElementImportLinksToImportingNamespace}"
			+ "         -->{SupplierDependencyLinksToSupplier, RealizationIsPartOfAbstraction, "
			+ "             ExtendIsPartOfExtension, IncluderLinksToAddition, ImportingLinksToImportedPackage,"
			+ "             ImportLinksToImportedElement}"
			+ "       | <--{ContractLinksToInterfaceRealization, GeneralLinksToSpecialization}"
			+ // include generalizations and realized interfaces
			"       | -->{MemberEndLinksToAssociation}"
			+ // include nested elements (traversal of aggregations and
			// compositions created by users - explicit nesting
			// relationships)
			"         <--{MemberEndLinksToAssociation}"
			+ "       )*"
			+ "       [<--{ImplementsLinksToImplementationTarget}-->{ImplementsLinksToImplementationSource}"
			+ // include code elements linked to design elements by Implements
			// relationships
			"           (-->{IncludesSource}"
			+ // include code elements nested in / used by other code elements -
			// relationships represented by edges from whole to part / from
			// user to used
			"           | <--{IsAnnotationOfType, IsAnnotationOfVariable, IsAnnotationOfMember, "
			+ // include code elements nested in / used by other code elements -
			// relationships represented by edges from part to whole / from
			// used to user
			"                 IsAnnotationOfEnumConstant, IsMetaAnnotationOf, IsAnnotationOfPackage, "
			+ "                 IsAnnotationArgumentOf, IsAnnotationNameOf, IsEnumBlockOf, IsAnnotationBlockOf, "
			+ "  			      IsClassBlockOf, IsEnumConstantBlockOf, IsInterfaceBlockOf, IsMemberOf, "
			+ "                 IsFieldNameOf, IsLabelNameOf, IsTypeParameterDeclarationNameOf, "
			+ "                 IsNameOfInvocatedMethod, IsTypeNameOf, IsTypeParameterUsageNameOf, "
			+ "                 IsEnumConstantNameOf, IsBreakTargetOf, IsParameterNameOf, "
			+ "                 IsContinueTargetOf, IsAnnotationDefinitionNameOf, IsClassNameOf,"
			+ "                 IsNameOfMethod, IsVariableNameOf, IsEnumNameOf, IsNameOfConstructor, "
			+ "                 IsInterfaceNameOf, IsModifierOfAnnotation, IsModifierOfConstructor, "
			+ "                 IsModifierOfInterface, IsModifierOfMethod, IsModifierOfParameter, "
			+ "                 IsModifierOfVariable, IsModifierOfEnum, IsModifierOfClass, IsParameterOfMethod,"
			+ "                 IsParameterOfForEachClause, IsParameterOfConstructor, IsCaughtExceptionOf,"
			+ "                 IsTypeOfParameter, IsImportDefinitionOf, IsQualifiedTypeOf, IsCommentIn, "
			+ "                 IsTypeArgumentOfArrayCreation, IsDimensionInitializerOf, IsTypeOfCreatedArray, "
			+ "                 IsContentOf, IsSizeOf, IsCastedBuiltInTypeOf, IsCastedValueOf, IsCastedTypeOf, "
			+ "                 IsCastedObjectOf, IsConstructorInvocationOf, IsConditionOfExpression, IsMatchOf, "
			+ "                 IsMismatchOf, IsIteratorOf, IsSwitchArgumentOf, IsCaseConditionOf, IsMessageOf, "
			+ "                 IsConditionOfIf, IsConditionOfWhile, IsLeftHandSideOfInfixExpression, "
			+ "                 IsReturnedBy, IsMonitorOf, IsLeftHandSideOfPostFixExpression, IsConditionOfDoWhile,"
			+ "                 IsFieldContainerOf, IsInitializerOf, IsArgumentOfEnumConstant, IsEnumeratableOf, "
			+ "                 IsMethodContainerOf, IsConditionOfAssert, IsArrayElementIndexOf, IsInitializerOfVariable,"
			+ "                 IsArgumentOfMethodInvocation, IsRightHandSideOfPrefixExpression, IsThrownExceptionOf,"
			+ "                 IsForConditionOf, IsRightHandSideOfInfixExpression, IsTypeArgumentOfMethodInvocation,"
			+ "                 IsBodyOfConstructor, IsTypeParameterOfConstructor, IsExceptionThrownByConstructor, "
			+ "                 IsFieldCreationOf, java5.members.IsExceptionThrownByMethod, IsReturnTypeOf, "
			+ "                 IsTypeParameterOfMethod, IsBodyOfMethod, IsBodyOfStaticConstructor, "
			+ "                 IsBodyOfStaticInitializer, IsPackageOf, IsSubPackageOf, IsCaseOf, "
			+ "                 IsTranslationUnitIn, IsPartOf, IsPrimarySourceFor, IsSourceFor, IsSourceUsageIn,"
			+ "                 IsExternalDeclarationIn, IsStatementOfCase, IsHandlerOf, IsCatchBodyOf, "
			+ "                 IsSynchronizedBodyOf, IsFinallyBodyOf, IsTryBodyOf, IsStatementOfCompound, "
			+ "                 IsTypeArgumentOfVariable, IsTypeOfVariable, IsDefaultCaseOf, IsStatementOfDefaultCase,"
			+ "                 IsLoopBodyOfDoWhile, IsHeadOfFor, IsLoopBodyOfFor, IsThenOf, IsElseOf, "
			+ "                 IsAttachedTo, IsLoopBodyOfWhile, IsRunVariableInitializationOf, IsSuperClassOfClass,"
			+ "                 IsTypeParameterOfClass, IsInterfaceOfClass, IsInterfaceOfEnum, "
			+ "                 IsTypeParameterOfInterface, IsSuperClassOfInterface, IsElementTypeOf, "
			+ "                 IsSimpleArgumentOf, IsTypeOfSimpleArgument, IsTypeArgumentOfTypeSpecification, "
			+ "                 IsWildcardArgumentOf, IsUpperBoundOfTypeParameter, IsLowerBoundOfWildCardArgument,"
			+ "                 TypeSpecificationLinksToWildcardArgument}"
			+ "           )*" + "       ]" + "   ]" + "))";

	/**
	 * Returns the slice for the slicing criterion (<code>inputVertices</code>,
	 * <code>IDEAL_SLICE_RPE</code>), i.e. the ideal slice for the given set of
	 * vertices.
	 * 
	 * @param inputVertices
	 *            the set of vertices for which the ideal slice shall be
	 *            returned
	 * @return the ideal slice for the given set of vertices
	 */
	public static Slice computeSlice(Set<Vertex> inputVertices, SliceType type) {
		if (sliceTypeToRPE == null) 
			createSliceTypeToRPEMap();
		if ((inputVertices != null) && (!inputVertices.isEmpty())) {
			for (Vertex v : inputVertices) {
				prepareGraphForSlice((SCLGraph)v.getGraph());
				break;
			}
		}
		return SliceFactory.slice(inputVertices, sliceTypeToRPE.get(type));
	}
		
	private static Set<SCLGraph> preparedGraphs;
	
	private static void prepareSimpleVerbPhrase(SimpleVerbPhrase name, DomainStatement statement, SCLGraph g, List<SimpleVerbPhrase> unlinkedPhrases) {
	//	System.out.println("Preparing simple verb phrase " + name);
		Iterator<SimpleVerbPhrase> iter = unlinkedPhrases.iterator();
		while (iter.hasNext()) {
		    SimpleVerbPhrase phrase = iter.next();
			SimpleVerbPhraseDTO phraseDTO = (SimpleVerbPhraseDTO) phrase;
			if (phraseDTO.equals((SimpleVerbPhraseDTO)name)) {
			//	System.out.println("Found similar phrase DTO " + phraseDTO);
				//phrase is a usage of the statement, create links 
				Edge predicateEdge = phrase.getFirstPredicateContainsTarget(EdgeDirection.IN);
				if (predicateEdge == null) {
				//	System.out.println("Found unlinked phrase as member of ComplexVerbPhrase " + phrase);
					Edge complexVerbPhraseEdge = phrase.getFirstComplexVerbPhraseContainsSimpleVerbPhrase();
					if (complexVerbPhraseEdge != null) {
					//	System.out.println("Linking SimpleVerbPhrase " + phrase);
						//create artificial predicate for SimpleVerbPhrase in a ComplexVerbPhrase
						Predicate predicate = g.createPredicate();
						predicate.addTarget(phrase);
						predicate.addDomainStatement(statement);
						iter.remove();
					} else {
						iter.remove();
					}
				} else {
					Predicate predicate = (Predicate) predicateEdge.getThat();
					predicate.addDomainStatement(statement);
					iter.remove();
				}						
			}
		}
	}
	
	
	private static void prepareComplexVerbPhrase(ComplexVerbPhrase name, DomainStatement statement, List<ComplexVerbPhrase> unlinkedPhrases) {
		Iterator<ComplexVerbPhrase> iter = unlinkedPhrases.iterator();
		while (iter.hasNext()) {
		    ComplexVerbPhrase phrase = iter.next();
			ComplexVerbPhraseDTO phraseDTO = (ComplexVerbPhraseDTO) phrase;
			Edge predicateEdge = phrase.getFirstPredicateContainsTarget(EdgeDirection.IN);
			if (predicateEdge != null) {
				if (phraseDTO.equals((ComplexVerbPhraseDTO)name)) {
					Predicate predicate = (Predicate) predicateEdge.getThat();
					predicate.addDomainStatement(statement);
					iter.remove();
				}	
			} else {
				iter.remove();
			}
		}
	}
	
	private static void prepareNounPhrase(NounPhrase name, DomainElement element,  List<NounPhrase> unlinkedPhrases) {
		Iterator<NounPhrase> iter = unlinkedPhrases.iterator();
		while (iter.hasNext()) {
			NounPhrase phrase =  iter.next();
			Edge subjectEdge = phrase.getFirstSubjectContainsTarget(EdgeDirection.IN);
			if ((subjectEdge != null) && (((NounPhraseDTO)phrase).equals((NounPhraseDTO)name))) {
					Subject subject = (Subject) subjectEdge.getThat();
					subject.addDomainElement(element);
					iter.remove();
			}
		}	
	}
	
	
	/**
	 * Prepare the given graph to be sliced, e.g. creates explicit links from 
	 * requirements to domain elements
	 * @param g
	 */
	private static void prepareGraphForSlice(SCLGraph g) {
		if (preparedGraphs == null)
			preparedGraphs = new HashSet<SCLGraph>();
		if (preparedGraphs.contains(g))
			return;
		else
			preparedGraphs.add(g);
		
		Map<DomainStatement, ComplexVerbPhrase> statementsWithComplexVerbPhrase = new HashMap<DomainStatement, ComplexVerbPhrase>();
		Map<DomainStatement, SimpleVerbPhrase> statementsWithSimpleVerbPhrase = new HashMap<DomainStatement, SimpleVerbPhrase>();
		Map<DomainElement, NounPhrase> elementsWithNounPhrase = new HashMap<DomainElement, NounPhrase>();
		
		List<ComplexVerbPhrase> unlinkedComplexVerbPhrases = new ArrayList<ComplexVerbPhrase>();
		List<SimpleVerbPhrase> unlinkedSimpleVerbPhrases = new ArrayList<SimpleVerbPhrase>();
		List<NounPhrase> unlinkedNounPhrases = new ArrayList<NounPhrase>();
				
		for (DomainStatement statement : g.getDomainStatementVertices()) {
			if (statement.getFirstLinkingPredicateLinksToDomainStatement(EdgeDirection.IN) != null)
				continue;
			Edge statementNameEdge = statement.getFirstStatementContainsName();
			if (statementNameEdge == null)
				continue;
			Phrase name = (Phrase) statementNameEdge.getThat();
			if (name instanceof SimpleVerbPhrase) {
				statementsWithSimpleVerbPhrase.put(statement, (SimpleVerbPhrase) name);
			} else if (name instanceof ComplexVerbPhrase) {
				statementsWithComplexVerbPhrase.put(statement, (ComplexVerbPhrase) name);
			} 
		}
		for (DomainElement element : g.getDomainElementVertices()) {
			if (element.getFirstLinkingSubjectLinksToDomainElement(EdgeDirection.IN) != null)
				continue;
			NounPhrase name = (NounPhrase) element.getFirstRepresentableElementContainsName().getThat();
			elementsWithNounPhrase.put(element, name);
		}
		
		for (Phrase p : g.getPhraseVertices()) {
			if (p instanceof SimpleVerbPhrase) {
				unlinkedSimpleVerbPhrases.add((SimpleVerbPhrase) p);
				continue;
			} 
			if (p instanceof ComplexVerbPhrase) {
				unlinkedComplexVerbPhrases.add((ComplexVerbPhrase) p);
				continue;
			} 
			if (p instanceof NounPhrase) {
				unlinkedNounPhrases.add((NounPhrase) p);
			} 
		}
		
		
		for (Entry<DomainStatement, ComplexVerbPhrase> entry : statementsWithComplexVerbPhrase.entrySet()) {
			prepareComplexVerbPhrase(entry.getValue(), entry.getKey(), unlinkedComplexVerbPhrases);
		}
		for (Entry<DomainStatement, SimpleVerbPhrase> entry : statementsWithSimpleVerbPhrase.entrySet()) {
			prepareSimpleVerbPhrase(entry.getValue(), entry.getKey(), g, unlinkedSimpleVerbPhrases);
		}
		for (Entry<DomainElement, NounPhrase> entry : elementsWithNounPhrase.entrySet()) {
			prepareNounPhrase(entry.getValue(), entry.getKey(), unlinkedNounPhrases);
		}
		
		
	}
}
