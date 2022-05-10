package rsldl.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

import rsldl.RsldlPackage;
import rsldl.diagram.edit.parts.DLAlghoritmicTransitionNameEditPart;
import rsldl.diagram.edit.parts.DLAlghoritmicTransitionTypeEditPart;
import rsldl.diagram.edit.parts.DLAttributeLinkNameEditPart;
import rsldl.diagram.edit.parts.DLDataBasedReferenceNameEditPart;
import rsldl.diagram.edit.parts.DLDataBasedReferenceTypeEditPart;
import rsldl.diagram.edit.parts.DLEntityNameEditPart;
import rsldl.diagram.edit.parts.DLEntityTypeEditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionText2EditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionTextEditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionType2EditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionTypeEditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionText2EditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionTextEditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionType2EditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionTypeEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedReferenceNameEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedReferenceTypeEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedTransitionNameEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedTransitionTypeEditPart;
import rsldl.diagram.edit.parts.DLPrimitiveNameEditPart;
import rsldl.diagram.edit.parts.DLPropertyNameEditPart;
import rsldl.diagram.edit.parts.DLPropertyTypeEditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationMultiplicity2EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationMultiplicity3EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationMultiplicityEditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationName2EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationName3EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationNameEditPart;
import rsldl.diagram.edit.parts.DLValidityConditionText2EditPart;
import rsldl.diagram.edit.parts.DLValidityConditionTextEditPart;
import rsldl.diagram.edit.parts.DLValidityConditionType2EditPart;
import rsldl.diagram.edit.parts.DLValidityConditionTypeEditPart;
import rsldl.diagram.parsers.MessageFormatParser;
import rsldl.diagram.part.RsldlVisualIDRegistry;

/**
 * @generated
 */
public class RsldlParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser dLEntityName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getDLEntityName_5007Parser() {
		if (dLEntityName_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationshipParticipant_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationshipParticipant_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			dLEntityName_5007Parser = parser;
		}
		return dLEntityName_5007Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLEntityType_5008Parser;

	/**
	 * @generated
	 */
	private IParser getDLEntityType_5008Parser() {
		if (dLEntityType_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLNotion_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLEntityType_5008Parser = parser;
		}
		return dLEntityType_5008Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLPropertyName_5015Parser;

	/**
	 * @generated
	 */
	private IParser getDLPropertyName_5015Parser() {
		if (dLPropertyName_5015Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationshipParticipant_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationshipParticipant_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			dLPropertyName_5015Parser = parser;
		}
		return dLPropertyName_5015Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLPropertyTypeValueType_5016Parser;

	/**
	 * @generated
	 */
	private IParser getDLPropertyTypeValueType_5016Parser() {
		if (dLPropertyTypeValueType_5016Parser == null) {
			EAttribute[] features = new EAttribute[] {
					RsldlPackage.eINSTANCE.getDLNotion_Type(),
					RsldlPackage.eINSTANCE.getDLProperty_ValueType() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}, value ({1})"); //$NON-NLS-1$
			parser.setEditorPattern("{0}, value ({1})"); //$NON-NLS-1$
			parser.setEditPattern("{0}, value ({1})"); //$NON-NLS-1$
			dLPropertyTypeValueType_5016Parser = parser;
		}
		return dLPropertyTypeValueType_5016Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLPrimitiveName_5017Parser;

	/**
	 * @generated
	 */
	private IParser getDLPrimitiveName_5017Parser() {
		if (dLPrimitiveName_5017Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationshipParticipant_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationshipParticipant_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			dLPrimitiveName_5017Parser = parser;
		}
		return dLPrimitiveName_5017Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLDataBasedReferenceName_5019Parser;

	/**
	 * @generated
	 */
	private IParser getDLDataBasedReferenceName_5019Parser() {
		if (dLDataBasedReferenceName_5019Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationship_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationship_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			dLDataBasedReferenceName_5019Parser = parser;
		}
		return dLDataBasedReferenceName_5019Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLDataBasedReferenceType_5020Parser;

	/**
	 * @generated
	 */
	private IParser getDLDataBasedReferenceType_5020Parser() {
		if (dLDataBasedReferenceType_5020Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationship_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLDataBasedReferenceType_5020Parser = parser;
		}
		return dLDataBasedReferenceType_5020Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLPatternBasedReferenceName_5021Parser;

	/**
	 * @generated
	 */
	private IParser getDLPatternBasedReferenceName_5021Parser() {
		if (dLPatternBasedReferenceName_5021Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationship_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationship_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			dLPatternBasedReferenceName_5021Parser = parser;
		}
		return dLPatternBasedReferenceName_5021Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLPatternBasedReferenceType_5022Parser;

	/**
	 * @generated
	 */
	private IParser getDLPatternBasedReferenceType_5022Parser() {
		if (dLPatternBasedReferenceType_5022Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationship_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLPatternBasedReferenceType_5022Parser = parser;
		}
		return dLPatternBasedReferenceType_5022Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLAlghoritmicTransitionName_5023Parser;

	/**
	 * @generated
	 */
	private IParser getDLAlghoritmicTransitionName_5023Parser() {
		if (dLAlghoritmicTransitionName_5023Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationship_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationship_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			dLAlghoritmicTransitionName_5023Parser = parser;
		}
		return dLAlghoritmicTransitionName_5023Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLAlghoritmicTransitionType_5024Parser;

	/**
	 * @generated
	 */
	private IParser getDLAlghoritmicTransitionType_5024Parser() {
		if (dLAlghoritmicTransitionType_5024Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationship_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLAlghoritmicTransitionType_5024Parser = parser;
		}
		return dLAlghoritmicTransitionType_5024Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLPatternBasedTransitionName_5025Parser;

	/**
	 * @generated
	 */
	private IParser getDLPatternBasedTransitionName_5025Parser() {
		if (dLPatternBasedTransitionName_5025Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationship_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationship_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			dLPatternBasedTransitionName_5025Parser = parser;
		}
		return dLPatternBasedTransitionName_5025Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLPatternBasedTransitionType_5026Parser;

	/**
	 * @generated
	 */
	private IParser getDLPatternBasedTransitionType_5026Parser() {
		if (dLPatternBasedTransitionType_5026Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationship_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLPatternBasedTransitionType_5026Parser = parser;
		}
		return dLPatternBasedTransitionType_5026Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLIdentityConditionType_5001Parser;

	/**
	 * @generated
	 */
	private IParser getDLIdentityConditionType_5001Parser() {
		if (dLIdentityConditionType_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLCondition_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLIdentityConditionType_5001Parser = parser;
		}
		return dLIdentityConditionType_5001Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLIdentityConditionText_5002Parser;

	/**
	 * @generated
	 */
	private IParser getDLIdentityConditionText_5002Parser() {
		if (dLIdentityConditionText_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLCondition_Text() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLIdentityConditionText_5002Parser = parser;
		}
		return dLIdentityConditionText_5002Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLInheritanceConditionType_5003Parser;

	/**
	 * @generated
	 */
	private IParser getDLInheritanceConditionType_5003Parser() {
		if (dLInheritanceConditionType_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLCondition_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLInheritanceConditionType_5003Parser = parser;
		}
		return dLInheritanceConditionType_5003Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLInheritanceConditionText_5004Parser;

	/**
	 * @generated
	 */
	private IParser getDLInheritanceConditionText_5004Parser() {
		if (dLInheritanceConditionText_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLCondition_Text() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLInheritanceConditionText_5004Parser = parser;
		}
		return dLInheritanceConditionText_5004Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLValidityConditionType_5005Parser;

	/**
	 * @generated
	 */
	private IParser getDLValidityConditionType_5005Parser() {
		if (dLValidityConditionType_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLCondition_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLValidityConditionType_5005Parser = parser;
		}
		return dLValidityConditionType_5005Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLValidityConditionText_5006Parser;

	/**
	 * @generated
	 */
	private IParser getDLValidityConditionText_5006Parser() {
		if (dLValidityConditionText_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLCondition_Text() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLValidityConditionText_5006Parser = parser;
		}
		return dLValidityConditionText_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLIdentityConditionType_5009Parser;

	/**
	 * @generated
	 */
	private IParser getDLIdentityConditionType_5009Parser() {
		if (dLIdentityConditionType_5009Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLCondition_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLIdentityConditionType_5009Parser = parser;
		}
		return dLIdentityConditionType_5009Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLIdentityConditionText_5010Parser;

	/**
	 * @generated
	 */
	private IParser getDLIdentityConditionText_5010Parser() {
		if (dLIdentityConditionText_5010Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLCondition_Text() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLIdentityConditionText_5010Parser = parser;
		}
		return dLIdentityConditionText_5010Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLInheritanceConditionType_5011Parser;

	/**
	 * @generated
	 */
	private IParser getDLInheritanceConditionType_5011Parser() {
		if (dLInheritanceConditionType_5011Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLCondition_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLInheritanceConditionType_5011Parser = parser;
		}
		return dLInheritanceConditionType_5011Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLInheritanceConditionText_5012Parser;

	/**
	 * @generated
	 */
	private IParser getDLInheritanceConditionText_5012Parser() {
		if (dLInheritanceConditionText_5012Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLCondition_Text() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLInheritanceConditionText_5012Parser = parser;
		}
		return dLInheritanceConditionText_5012Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLValidityConditionType_5013Parser;

	/**
	 * @generated
	 */
	private IParser getDLValidityConditionType_5013Parser() {
		if (dLValidityConditionType_5013Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLCondition_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLValidityConditionType_5013Parser = parser;
		}
		return dLValidityConditionType_5013Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLValidityConditionText_5014Parser;

	/**
	 * @generated
	 */
	private IParser getDLValidityConditionText_5014Parser() {
		if (dLValidityConditionText_5014Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLCondition_Text() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLValidityConditionText_5014Parser = parser;
		}
		return dLValidityConditionText_5014Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLRelationshipParticipationName_6001Parser;

	/**
	 * @generated
	 */
	private IParser getDLRelationshipParticipationName_6001Parser() {
		if (dLRelationshipParticipationName_6001Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLNamedLink_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLNamedLink_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			dLRelationshipParticipationName_6001Parser = parser;
		}
		return dLRelationshipParticipationName_6001Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLRelationshipParticipationMultiplicity_6002Parser;

	/**
	 * @generated
	 */
	private IParser getDLRelationshipParticipationMultiplicity_6002Parser() {
		if (dLRelationshipParticipationMultiplicity_6002Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationshipParticipation_Multiplicity() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLRelationshipParticipationMultiplicity_6002Parser = parser;
		}
		return dLRelationshipParticipationMultiplicity_6002Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLRelationshipParticipationName_6003Parser;

	/**
	 * @generated
	 */
	private IParser getDLRelationshipParticipationName_6003Parser() {
		if (dLRelationshipParticipationName_6003Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLNamedLink_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLNamedLink_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			dLRelationshipParticipationName_6003Parser = parser;
		}
		return dLRelationshipParticipationName_6003Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLRelationshipParticipationMultiplicity_6004Parser;

	/**
	 * @generated
	 */
	private IParser getDLRelationshipParticipationMultiplicity_6004Parser() {
		if (dLRelationshipParticipationMultiplicity_6004Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationshipParticipation_Multiplicity() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLRelationshipParticipationMultiplicity_6004Parser = parser;
		}
		return dLRelationshipParticipationMultiplicity_6004Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLRelationshipParticipationName_6005Parser;

	/**
	 * @generated
	 */
	private IParser getDLRelationshipParticipationName_6005Parser() {
		if (dLRelationshipParticipationName_6005Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLNamedLink_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLNamedLink_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			dLRelationshipParticipationName_6005Parser = parser;
		}
		return dLRelationshipParticipationName_6005Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLRelationshipParticipationMultiplicity_6006Parser;

	/**
	 * @generated
	 */
	private IParser getDLRelationshipParticipationMultiplicity_6006Parser() {
		if (dLRelationshipParticipationMultiplicity_6006Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLRelationshipParticipation_Multiplicity() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dLRelationshipParticipationMultiplicity_6006Parser = parser;
		}
		return dLRelationshipParticipationMultiplicity_6006Parser;
	}

	/**
	 * @generated
	 */
	private IParser dLAttributeLinkName_6007Parser;

	/**
	 * @generated
	 */
	private IParser getDLAttributeLinkName_6007Parser() {
		if (dLAttributeLinkName_6007Parser == null) {
			EAttribute[] features = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLNamedLink_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { RsldlPackage.eINSTANCE
					.getDLNamedLink_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			dLAttributeLinkName_6007Parser = parser;
		}
		return dLAttributeLinkName_6007Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case DLEntityNameEditPart.VISUAL_ID:
			return getDLEntityName_5007Parser();
		case DLEntityTypeEditPart.VISUAL_ID:
			return getDLEntityType_5008Parser();
		case DLPropertyNameEditPart.VISUAL_ID:
			return getDLPropertyName_5015Parser();
		case DLPropertyTypeEditPart.VISUAL_ID:
			return getDLPropertyTypeValueType_5016Parser();
		case DLPrimitiveNameEditPart.VISUAL_ID:
			return getDLPrimitiveName_5017Parser();
		case DLDataBasedReferenceNameEditPart.VISUAL_ID:
			return getDLDataBasedReferenceName_5019Parser();
		case DLDataBasedReferenceTypeEditPart.VISUAL_ID:
			return getDLDataBasedReferenceType_5020Parser();
		case DLPatternBasedReferenceNameEditPart.VISUAL_ID:
			return getDLPatternBasedReferenceName_5021Parser();
		case DLPatternBasedReferenceTypeEditPart.VISUAL_ID:
			return getDLPatternBasedReferenceType_5022Parser();
		case DLAlghoritmicTransitionNameEditPart.VISUAL_ID:
			return getDLAlghoritmicTransitionName_5023Parser();
		case DLAlghoritmicTransitionTypeEditPart.VISUAL_ID:
			return getDLAlghoritmicTransitionType_5024Parser();
		case DLPatternBasedTransitionNameEditPart.VISUAL_ID:
			return getDLPatternBasedTransitionName_5025Parser();
		case DLPatternBasedTransitionTypeEditPart.VISUAL_ID:
			return getDLPatternBasedTransitionType_5026Parser();
		case DLIdentityConditionTypeEditPart.VISUAL_ID:
			return getDLIdentityConditionType_5001Parser();
		case DLIdentityConditionTextEditPart.VISUAL_ID:
			return getDLIdentityConditionText_5002Parser();
		case DLInheritanceConditionTypeEditPart.VISUAL_ID:
			return getDLInheritanceConditionType_5003Parser();
		case DLInheritanceConditionTextEditPart.VISUAL_ID:
			return getDLInheritanceConditionText_5004Parser();
		case DLValidityConditionTypeEditPart.VISUAL_ID:
			return getDLValidityConditionType_5005Parser();
		case DLValidityConditionTextEditPart.VISUAL_ID:
			return getDLValidityConditionText_5006Parser();
		case DLIdentityConditionType2EditPart.VISUAL_ID:
			return getDLIdentityConditionType_5009Parser();
		case DLIdentityConditionText2EditPart.VISUAL_ID:
			return getDLIdentityConditionText_5010Parser();
		case DLInheritanceConditionType2EditPart.VISUAL_ID:
			return getDLInheritanceConditionType_5011Parser();
		case DLInheritanceConditionText2EditPart.VISUAL_ID:
			return getDLInheritanceConditionText_5012Parser();
		case DLValidityConditionType2EditPart.VISUAL_ID:
			return getDLValidityConditionType_5013Parser();
		case DLValidityConditionText2EditPart.VISUAL_ID:
			return getDLValidityConditionText_5014Parser();
		case DLRelationshipParticipationNameEditPart.VISUAL_ID:
			return getDLRelationshipParticipationName_6001Parser();
		case DLRelationshipParticipationMultiplicityEditPart.VISUAL_ID:
			return getDLRelationshipParticipationMultiplicity_6002Parser();
		case DLRelationshipParticipationName2EditPart.VISUAL_ID:
			return getDLRelationshipParticipationName_6003Parser();
		case DLRelationshipParticipationMultiplicity2EditPart.VISUAL_ID:
			return getDLRelationshipParticipationMultiplicity_6004Parser();
		case DLRelationshipParticipationName3EditPart.VISUAL_ID:
			return getDLRelationshipParticipationName_6005Parser();
		case DLRelationshipParticipationMultiplicity3EditPart.VISUAL_ID:
			return getDLRelationshipParticipationMultiplicity_6006Parser();
		case DLAttributeLinkNameEditPart.VISUAL_ID:
			return getDLAttributeLinkName_6007Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(RsldlVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(RsldlVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (RsldlElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
