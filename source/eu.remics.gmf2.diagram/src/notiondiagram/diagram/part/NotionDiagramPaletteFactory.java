package notiondiagram.diagram.part;

import java.util.Collections;
import java.util.List;

import notiondiagram.diagram.providers.NotionDiagramElementTypes;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class NotionDiagramPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createElements1Group());
		paletteRoot.add(createConnections2Group());
	}

	/**
	 * Creates "Elements" palette tool group
	 * @generated NOT
	 */
	private PaletteContainer createElements1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Elements1Group_title);
		paletteContainer.setId("createElements1Group"); //$NON-NLS-1$
		paletteContainer.add(createNotion1Stack());
		return paletteContainer;
	}

	/**
	 * Creates "Connections" palette tool group
	 * @generated
	 */
	private PaletteContainer createConnections2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Connections2Group_title);
		paletteContainer.setId("createConnections2Group"); //$NON-NLS-1$
		paletteContainer.add(createGeneralization1CreationTool());
		paletteContainer.add(createAttributeRelation2CreationTool());
		paletteContainer.add(createDirectedRelation3CreationTool());
		paletteContainer.add(createIndirectRelation4CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated NOT
	 */
	private PaletteStack createNotion1Stack() {
		PaletteStack paletteStack = new PaletteStack(
				Messages.Notion1CreationTool_title,
				null,
				NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif"));
		paletteStack.add(createNotion1CreationTool());
		paletteStack.add(createNotion2CreationTool());
		paletteStack.add(createNotion3CreationTool());
		paletteStack.add(createNotion4CreationTool());
		paletteStack.add(createNotion5CreationTool());
		paletteStack.add(createNotion6CreationTool());
		paletteStack.add(createNotion7CreationTool());
		paletteStack.add(createNotion8CreationTool());
		paletteStack.add(createNotion9CreationTool());
		paletteStack.add(createNotion10CreationTool());
		return paletteStack;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNotion1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Notion1CreationTool_title, null,
				Collections
						.singletonList(NotionDiagramElementTypes.Notion_2001));
		entry.setId("createNotion1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNotion2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Notion2CreationTool_title, null,
				Collections
						.singletonList(NotionDiagramElementTypes.Notion_2002));
		entry.setId("createNotion2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif"));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNotion3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Notion3CreationTool_title, null,
				Collections
						.singletonList(NotionDiagramElementTypes.Notion_2003));
		entry.setId("createNotion3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif"));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNotion4CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Notion4CreationTool_title, null,
				Collections
						.singletonList(NotionDiagramElementTypes.Notion_2004));
		entry.setId("createNotion4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif"));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNotion5CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Notion5CreationTool_title, null,
				Collections
						.singletonList(NotionDiagramElementTypes.Notion_2005));
		entry.setId("createNotion5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif"));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNotion6CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Notion6CreationTool_title, null,
				Collections
						.singletonList(NotionDiagramElementTypes.Notion_2006));
		entry.setId("createNotion6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif"));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNotion7CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Notion7CreationTool_title, null,
				Collections
						.singletonList(NotionDiagramElementTypes.Notion_2007));
		entry.setId("createNotion7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif"));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNotion8CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Notion8CreationTool_title, null,
				Collections
						.singletonList(NotionDiagramElementTypes.Notion_2008));
		entry.setId("createNotion8CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif"));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNotion9CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Notion9CreationTool_title, null,
				Collections
						.singletonList(NotionDiagramElementTypes.Notion_2009));
		entry.setId("createNotion9CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif"));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNotion10CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Notion10CreationTool_title, null,
				Collections
						.singletonList(NotionDiagramElementTypes.Notion_2010));
		entry.setId("createNotion10CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/notion.gif"));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createGeneralization1CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Generalization1CreationTool_title,
				null,
				Collections
						.singletonList(NotionDiagramElementTypes.Generalization_4001));
		entry.setId("createGeneralization1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/gen.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAttributeRelation2CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.AttributeRelation2CreationTool_title,
				null,
				Collections
						.singletonList(NotionDiagramElementTypes.AttributeRelation_4002));
		entry.setId("createAttributeRelation2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/attr.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createDirectedRelation3CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.DirectedRelation3CreationTool_title,
				null,
				Collections
						.singletonList(NotionDiagramElementTypes.DirectedRelation_4003));
		entry.setId("createDirectedRelation3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/dirrel.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createIndirectRelation4CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.IndirectRelation4CreationTool_title,
				null,
				Collections
						.singletonList(NotionDiagramElementTypes.IndirectRelation_4004));
		entry.setId("createIndirectRelation4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NotionDiagramDiagramEditorPlugin
				.findImageDescriptor("/eu.remics.gmf2/model/icons/indrel.gif")); //$NON-NLS-1$
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
