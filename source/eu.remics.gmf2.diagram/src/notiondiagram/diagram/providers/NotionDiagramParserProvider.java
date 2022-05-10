package notiondiagram.diagram.providers;

import notiondiagram.NotiondiagramPackage;
import notiondiagram.diagram.edit.parts.DirectedRelationSourceMultiplicityEditPart;
import notiondiagram.diagram.edit.parts.DirectedRelationStereotypeEditPart;
import notiondiagram.diagram.edit.parts.DirectedRelationTargetMultiplicityEditPart;
import notiondiagram.diagram.edit.parts.IndirectRelationSourceMultiplicityEditPart;
import notiondiagram.diagram.edit.parts.IndirectRelationStereotypeEditPart;
import notiondiagram.diagram.edit.parts.IndirectRelationTargetMultiplicityEditPart;
import notiondiagram.diagram.edit.parts.NotionNameEditPart;
import notiondiagram.diagram.edit.parts.NotionTypeEditPart;
import notiondiagram.diagram.edit.parts.PhraseTextEditPart;
import notiondiagram.diagram.parsers.MessageFormatParser;
import notiondiagram.diagram.part.NotionDiagramVisualIDRegistry;

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

/**
 * @generated
 */
public class NotionDiagramParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser notionName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getNotionName_5001Parser() {
		if (notionName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { NotiondiagramPackage.eINSTANCE
					.getNotion_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { NotiondiagramPackage.eINSTANCE
					.getNotion_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			notionName_5001Parser = parser;
		}
		return notionName_5001Parser;
	}

	/**
	 * @generated
	 */
	private IParser notionType_5003Parser;

	/**
	 * @generated
	 */
	private IParser getNotionType_5003Parser() {
		if (notionType_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { NotiondiagramPackage.eINSTANCE
					.getNotion_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			notionType_5003Parser = parser;
		}
		return notionType_5003Parser;
	}

	/**
	 * @generated
	 */
	private IParser phraseText_5002Parser;

	/**
	 * @generated
	 */
	private IParser getPhraseText_5002Parser() {
		if (phraseText_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { NotiondiagramPackage.eINSTANCE
					.getPhrase_Text() };
			MessageFormatParser parser = new MessageFormatParser(features);
			phraseText_5002Parser = parser;
		}
		return phraseText_5002Parser;
	}

	/**
	 * @generated
	 */
	private IParser directedRelationSourceMultiplicity_6001Parser;

	/**
	 * @generated
	 */
	private IParser getDirectedRelationSourceMultiplicity_6001Parser() {
		if (directedRelationSourceMultiplicity_6001Parser == null) {
			EAttribute[] features = new EAttribute[] { NotiondiagramPackage.eINSTANCE
					.getRelation_SourceMultiplicity() };
			MessageFormatParser parser = new MessageFormatParser(features);
			directedRelationSourceMultiplicity_6001Parser = parser;
		}
		return directedRelationSourceMultiplicity_6001Parser;
	}

	/**
	 * @generated
	 */
	private IParser directedRelationTargetMultiplicity_6002Parser;

	/**
	 * @generated
	 */
	private IParser getDirectedRelationTargetMultiplicity_6002Parser() {
		if (directedRelationTargetMultiplicity_6002Parser == null) {
			EAttribute[] features = new EAttribute[] { NotiondiagramPackage.eINSTANCE
					.getRelation_TargetMultiplicity() };
			MessageFormatParser parser = new MessageFormatParser(features);
			directedRelationTargetMultiplicity_6002Parser = parser;
		}
		return directedRelationTargetMultiplicity_6002Parser;
	}

	/**
	 * @generated
	 */
	private IParser directedRelationStereotype_6005Parser;

	/**
	 * @generated
	 */
	private IParser getDirectedRelationStereotype_6005Parser() {
		if (directedRelationStereotype_6005Parser == null) {
			EAttribute[] features = new EAttribute[] { NotiondiagramPackage.eINSTANCE
					.getRelation_Stereotype() };
			MessageFormatParser parser = new MessageFormatParser(features);
			directedRelationStereotype_6005Parser = parser;
		}
		return directedRelationStereotype_6005Parser;
	}

	/**
	 * @generated
	 */
	private IParser indirectRelationSourceMultiplicity_6003Parser;

	/**
	 * @generated
	 */
	private IParser getIndirectRelationSourceMultiplicity_6003Parser() {
		if (indirectRelationSourceMultiplicity_6003Parser == null) {
			EAttribute[] features = new EAttribute[] { NotiondiagramPackage.eINSTANCE
					.getRelation_SourceMultiplicity() };
			MessageFormatParser parser = new MessageFormatParser(features);
			indirectRelationSourceMultiplicity_6003Parser = parser;
		}
		return indirectRelationSourceMultiplicity_6003Parser;
	}

	/**
	 * @generated
	 */
	private IParser indirectRelationTargetMultiplicity_6004Parser;

	/**
	 * @generated
	 */
	private IParser getIndirectRelationTargetMultiplicity_6004Parser() {
		if (indirectRelationTargetMultiplicity_6004Parser == null) {
			EAttribute[] features = new EAttribute[] { NotiondiagramPackage.eINSTANCE
					.getRelation_TargetMultiplicity() };
			MessageFormatParser parser = new MessageFormatParser(features);
			indirectRelationTargetMultiplicity_6004Parser = parser;
		}
		return indirectRelationTargetMultiplicity_6004Parser;
	}

	/**
	 * @generated
	 */
	private IParser indirectRelationStereotype_6006Parser;

	/**
	 * @generated
	 */
	private IParser getIndirectRelationStereotype_6006Parser() {
		if (indirectRelationStereotype_6006Parser == null) {
			EAttribute[] features = new EAttribute[] { NotiondiagramPackage.eINSTANCE
					.getRelation_Stereotype() };
			MessageFormatParser parser = new MessageFormatParser(features);
			indirectRelationStereotype_6006Parser = parser;
		}
		return indirectRelationStereotype_6006Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case NotionNameEditPart.VISUAL_ID:
			return getNotionName_5001Parser();
		case NotionTypeEditPart.VISUAL_ID:
			return getNotionType_5003Parser();
		case PhraseTextEditPart.VISUAL_ID:
			return getPhraseText_5002Parser();
		case DirectedRelationSourceMultiplicityEditPart.VISUAL_ID:
			return getDirectedRelationSourceMultiplicity_6001Parser();
		case DirectedRelationTargetMultiplicityEditPart.VISUAL_ID:
			return getDirectedRelationTargetMultiplicity_6002Parser();
		case DirectedRelationStereotypeEditPart.VISUAL_ID:
			return getDirectedRelationStereotype_6005Parser();
		case IndirectRelationSourceMultiplicityEditPart.VISUAL_ID:
			return getIndirectRelationSourceMultiplicity_6003Parser();
		case IndirectRelationTargetMultiplicityEditPart.VISUAL_ID:
			return getIndirectRelationTargetMultiplicity_6004Parser();
		case IndirectRelationStereotypeEditPart.VISUAL_ID:
			return getIndirectRelationStereotype_6006Parser();
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
			return getParser(NotionDiagramVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(NotionDiagramVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (NotionDiagramElementTypes.getElement(hint) == null) {
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
