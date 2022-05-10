package rsldl.diagram.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import rsldl.diagram.providers.RsldlElementTypes;

/**
 * @generated
 */
public class RsldlPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createObjects1Group());
		paletteRoot.add(createRelationships2Group());
		paletteRoot.add(createConnections3Group());
	}

	/**
	 * Creates "Objects" palette tool group
	 * @generated
	 */
	private PaletteContainer createObjects1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Objects1Group_title);
		paletteContainer.setId("createObjects1Group"); //$NON-NLS-1$
		paletteContainer.setDescription(Messages.Objects1Group_desc);
		paletteContainer.add(createEntity1CreationTool());
		paletteContainer.add(createProperty2CreationTool());
		paletteContainer.add(createPrimitive3CreationTool());
		paletteContainer.add(createIdentityCondition4CreationTool());
		paletteContainer.add(createInheritanceCondition5CreationTool());
		paletteContainer.add(createValidityCondition6CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Relationships" palette tool group
	 * @generated
	 */
	private PaletteContainer createRelationships2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Relationships2Group_title);
		paletteContainer.setId("createRelationships2Group"); //$NON-NLS-1$
		paletteContainer.add(createDataBasedReference1CreationTool());
		paletteContainer.add(createPatternBasedReference2CreationTool());
		paletteContainer.add(createAlghoritmicTransition3CreationTool());
		paletteContainer.add(createPatternBasedTransition4CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Connections" palette tool group
	 * @generated
	 */
	private PaletteContainer createConnections3Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Connections3Group_title);
		paletteContainer.setId("createConnections3Group"); //$NON-NLS-1$
		paletteContainer.add(createRelationshipParticipation1CreationTool());
		paletteContainer
				.add(createRelationshipSourceParticipation2CreationTool());
		paletteContainer
				.add(createRelationshipTargetParticipation3CreationTool());
		paletteContainer.add(createAttributeLink4CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEntity1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Entity1CreationTool_title, null,
				Collections.singletonList(RsldlElementTypes.DLEntity_2001));
		entry.setId("createEntity1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLEntity_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProperty2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Property2CreationTool_title,
				Messages.Property2CreationTool_desc,
				Collections.singletonList(RsldlElementTypes.DLProperty_2002));
		entry.setId("createProperty2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLProperty_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPrimitive3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Primitive3CreationTool_title, null,
				Collections.singletonList(RsldlElementTypes.DLPrimitive_2003));
		entry.setId("createPrimitive3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLPrimitive_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createIdentityCondition4CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(RsldlElementTypes.DLIdentityCondition_3001);
		types.add(RsldlElementTypes.DLIdentityCondition_3004);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.IdentityCondition4CreationTool_title, null, types);
		entry.setId("createIdentityCondition4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLIdentityCondition_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createInheritanceCondition5CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(RsldlElementTypes.DLInheritanceCondition_3002);
		types.add(RsldlElementTypes.DLInheritanceCondition_3005);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.InheritanceCondition5CreationTool_title, null, types);
		entry.setId("createInheritanceCondition5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLInheritanceCondition_3002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createValidityCondition6CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(RsldlElementTypes.DLValidityCondition_3003);
		types.add(RsldlElementTypes.DLValidityCondition_3006);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.ValidityCondition6CreationTool_title, null, types);
		entry.setId("createValidityCondition6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLValidityCondition_3003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createDataBasedReference1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.DataBasedReference1CreationTool_title,
				null,
				Collections
						.singletonList(RsldlElementTypes.DLDataBasedReference_2004));
		entry.setId("createDataBasedReference1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLDataBasedReference_2004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPatternBasedReference2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.PatternBasedReference2CreationTool_title,
				null,
				Collections
						.singletonList(RsldlElementTypes.DLPatternBasedReference_2005));
		entry.setId("createPatternBasedReference2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLPatternBasedReference_2005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAlghoritmicTransition3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.AlghoritmicTransition3CreationTool_title,
				null,
				Collections
						.singletonList(RsldlElementTypes.DLAlghoritmicTransition_2006));
		entry.setId("createAlghoritmicTransition3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLAlghoritmicTransition_2006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPatternBasedTransition4CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.PatternBasedTransition4CreationTool_title,
				null,
				Collections
						.singletonList(RsldlElementTypes.DLPatternBasedTransition_2007));
		entry.setId("createPatternBasedTransition4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLPatternBasedTransition_2007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRelationshipParticipation1CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.RelationshipParticipation1CreationTool_title,
				Messages.RelationshipParticipation1CreationTool_desc,
				Collections
						.singletonList(RsldlElementTypes.DLRelationshipParticipation_4001));
		entry.setId("createRelationshipParticipation1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLRelationshipParticipation_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRelationshipSourceParticipation2CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.RelationshipSourceParticipation2CreationTool_title,
				Messages.RelationshipSourceParticipation2CreationTool_desc,
				Collections
						.singletonList(RsldlElementTypes.DLRelationshipParticipation_4002));
		entry.setId("createRelationshipSourceParticipation2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLRelationshipParticipation_4002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRelationshipTargetParticipation3CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.RelationshipTargetParticipation3CreationTool_title,
				Messages.RelationshipTargetParticipation3CreationTool_desc,
				Collections
						.singletonList(RsldlElementTypes.DLRelationshipParticipation_4003));
		entry.setId("createRelationshipTargetParticipation3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLRelationshipParticipation_4003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAttributeLink4CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.AttributeLink4CreationTool_title, null,
				Collections
						.singletonList(RsldlElementTypes.DLAttributeLink_4004));
		entry.setId("createAttributeLink4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(RsldlElementTypes
				.getImageDescriptor(RsldlElementTypes.DLAttributeLink_4004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List<IElementType> elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List<IElementType> relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
