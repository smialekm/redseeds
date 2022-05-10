package notiondiagram.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import notiondiagram.Notion;
import notiondiagram.diagram.edit.policies.NotionItemSemanticEditPolicy;
import notiondiagram.diagram.part.NotionDiagramVisualIDRegistry;
import notiondiagram.diagram.providers.NotionDiagramElementTypes;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionFilter;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditor;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditorInput;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;
import eu.remics.recovery.model.preferences.MConfiguration;

/**
 * @generated
 */
public class NotionEditPart extends ShapeNodeEditPart implements IActionFilter {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2001;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public NotionEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy());
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new NotionItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return primaryShape = new NotionFigure();
	}

	/**
	 * @generated
	 */
	public NotionFigure getPrimaryShape() {
		return (NotionFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof NotionNameEditPart) {
			((NotionNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureNotionNameFigure());
			return true;
		}
		if (childEditPart instanceof NotionTypeEditPart) {
			((NotionTypeEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureNotionTypeFigure());
			return true;
		}
		if (childEditPart instanceof NotionNotionCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureNotionCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((NotionNotionCompartmentEditPart) childEditPart)
					.getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof NotionNameEditPart) {
			return true;
		}
		if (childEditPart instanceof NotionTypeEditPart) {
			return true;
		}
		if (childEditPart instanceof NotionNotionCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureNotionCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.remove(((NotionNotionCompartmentEditPart) childEditPart)
					.getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		if (editPart instanceof NotionNotionCompartmentEditPart) {
			return getPrimaryShape().getFigureNotionCompartmentFigure();
		}
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(NotionDiagramVisualIDRegistry
				.getType(NotionNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSource() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(4);
		types.add(NotionDiagramElementTypes.Generalization_4001);
		types.add(NotionDiagramElementTypes.AttributeRelation_4002);
		types.add(NotionDiagramElementTypes.DirectedRelation_4003);
		types.add(NotionDiagramElementTypes.IndirectRelation_4004);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSourceAndTarget(
			IGraphicalEditPart targetEditPart) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof notiondiagram.diagram.edit.parts.NotionEditPart) {
			types.add(NotionDiagramElementTypes.Generalization_4001);
		}
		if (targetEditPart instanceof notiondiagram.diagram.edit.parts.NotionEditPart) {
			types.add(NotionDiagramElementTypes.AttributeRelation_4002);
		}
		if (targetEditPart instanceof notiondiagram.diagram.edit.parts.NotionEditPart) {
			types.add(NotionDiagramElementTypes.DirectedRelation_4003);
		}
		if (targetEditPart instanceof notiondiagram.diagram.edit.parts.NotionEditPart) {
			types.add(NotionDiagramElementTypes.IndirectRelation_4004);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == NotionDiagramElementTypes.Generalization_4001) {
			types.add(NotionDiagramElementTypes.Notion_2001);
		} else if (relationshipType == NotionDiagramElementTypes.AttributeRelation_4002) {
			types.add(NotionDiagramElementTypes.Notion_2001);
		} else if (relationshipType == NotionDiagramElementTypes.DirectedRelation_4003) {
			types.add(NotionDiagramElementTypes.Notion_2001);
		} else if (relationshipType == NotionDiagramElementTypes.IndirectRelation_4004) {
			types.add(NotionDiagramElementTypes.Notion_2001);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnTarget() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(4);
		types.add(NotionDiagramElementTypes.Generalization_4001);
		types.add(NotionDiagramElementTypes.AttributeRelation_4002);
		types.add(NotionDiagramElementTypes.DirectedRelation_4003);
		types.add(NotionDiagramElementTypes.IndirectRelation_4004);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForSource(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == NotionDiagramElementTypes.Generalization_4001) {
			types.add(NotionDiagramElementTypes.Notion_2001);
		} else if (relationshipType == NotionDiagramElementTypes.AttributeRelation_4002) {
			types.add(NotionDiagramElementTypes.Notion_2001);
		} else if (relationshipType == NotionDiagramElementTypes.DirectedRelation_4003) {
			types.add(NotionDiagramElementTypes.Notion_2001);
		} else if (relationshipType == NotionDiagramElementTypes.IndirectRelation_4004) {
			types.add(NotionDiagramElementTypes.Notion_2001);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public EditPart getTargetEditPart(Request request) {
		if (request instanceof CreateViewAndElementRequest) {
			CreateElementRequestAdapter adapter = ((CreateViewAndElementRequest) request)
					.getViewAndElementDescriptor()
					.getCreateElementRequestAdapter();
			IElementType type = (IElementType) adapter
					.getAdapter(IElementType.class);
			if (type == NotionDiagramElementTypes.Phrase_3001) {
				return getChildBySemanticHint(NotionDiagramVisualIDRegistry
						.getType(NotionNotionCompartmentEditPart.VISUAL_ID));
			}
		}
		return super.getTargetEditPart(request);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void performRequest(Request request) {
		if (RequestConstants.REQ_OPEN.equals(request.getType())) {
			if (request instanceof SelectionRequest) {
				EObject elem = ((NodeImpl) this.getModel()).getElement();
				if (elem instanceof Notion) {
					Notion notion = (Notion) elem;
					RecoveryModelHelper rmh = RecoveryModelHelper.instance(elem
							.eResource());
					NotionDTO selectedElement = rmh.getNotionByVertexID(notion
							.getId());
					IWorkbenchPage activePage = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();

					NotionEditorInput notionInput = new NotionEditorInput();
					notionInput.setNotionDTO((NotionDTO) selectedElement);
					NotionEditor notionEditor;
					try {
						notionEditor = (NotionEditor) activePage.openEditor(
								notionInput, NotionEditor.EDITOR_ID, false, 1);
						notionEditor.setNotion((NotionDTO) selectedElement);
						notionEditor.setFacade(SCProjectContainer
								.instance()
								.getSCProject(
										SCProjectHelper.getActiveProject())
								.getBusinessLayerFacade());
						notionEditor.setScProject(SCProjectContainer.instance()
								.getSCProject(
										SCProjectHelper.getActiveProject()));
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		if (!isEditModeEnabled()) {
			if (RequestConstants.REQ_OPEN.equals(request.getType())) {
			} else {
				return;
			}
		}

		if (RequestConstants.REQ_DIRECT_EDIT == request.getType()) {
			performDirectEditRequest(request);
		} else {
			EditPart targetEditPart = getTargetEditPart(request);
			if (targetEditPart != null) {
				Command command = targetEditPart.getCommand(request);
				if (command != null) {
					getDiagramEditDomain().getDiagramCommandStack().execute(
							command);
					return;
				}
			}
		}
	}

	/**
	 * @generated
	 */
	public class NotionFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureNotionNameFigure;
		/**
		 * @generated
		 */
		private RectangleFigure fFigureNotionCompartmentFigure;

		/**
		 * @generated
		 */
		private WrappingLabel fFigureNotionTypeFigure;

		/**
		 * @generated
		 */
		public NotionFigure() {

			BorderLayout layoutThis = new BorderLayout();
			this.setLayoutManager(layoutThis);

			this.setForegroundColor(THIS_FORE);
			this.setBackgroundColor(THIS_BACK);
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			RectangleFigure notionTitleFigure0 = new RectangleFigure();
			notionTitleFigure0.setFill(false);
			notionTitleFigure0.setOutline(false);

			this.add(notionTitleFigure0, BorderLayout.TOP);

			BorderLayout layoutNotionTitleFigure0 = new BorderLayout();
			notionTitleFigure0.setLayoutManager(layoutNotionTitleFigure0);

			fFigureNotionNameFigure = new WrappingLabel();
			fFigureNotionNameFigure.setText("");

			notionTitleFigure0
					.add(fFigureNotionNameFigure, BorderLayout.CENTER);

			fFigureNotionTypeFigure = new WrappingLabel();
			fFigureNotionTypeFigure.setText("");

			fFigureNotionTypeFigure.setFont(FFIGURENOTIONTYPEFIGURE_FONT);

			notionTitleFigure0.add(fFigureNotionTypeFigure, BorderLayout.TOP);

			fFigureNotionCompartmentFigure = new RectangleFigure();

			this.add(fFigureNotionCompartmentFigure, BorderLayout.CENTER);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureNotionNameFigure() {
			return fFigureNotionNameFigure;
		}

		/**
		 * @generated
		 */
		public RectangleFigure getFigureNotionCompartmentFigure() {
			return fFigureNotionCompartmentFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureNotionTypeFigure() {
			return fFigureNotionTypeFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_FORE = new Color(null, 0, 0, 0);

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 215, 235, 250);

	/**
	 * @generated
	 */
	static final Font FFIGURENOTIONTYPEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 7, SWT.ITALIC);

	@Override
	public boolean testAttribute(Object target, String name, String value) {
		if (!(target instanceof NotionEditPart)) return false;
		Notion not = (Notion) ((NotionEditPart) target).resolveSemanticElement();
		RecoveryModelHelper rmh = RecoveryModelHelper.instance(not.eResource());
		NotionDTO notion = rmh.getNotionByVertexID(not.getId());
		if ("canConvertTo".equals(name)){
			if ("Concept".equals(value)){
				if (notion.getType().isEmpty()) return false;
				if (!MConfiguration.isCheckRelations()) return true;
				return MNotion.canBeConcept(notion);
			} else if ("Screen".equals(value)){
				if (NotionTypesEnum.Screen.tag().equals(notion.getType())) return false;
				if (!MConfiguration.isCheckRelations()) return true;
				return MNotion.canBeScreen(notion);
			} else if ("Message".equals(value)){
				if (NotionTypesEnum.Message.tag().equals(notion.getType())) return false;
				if (!MConfiguration.isCheckRelations()) return true;
				return MNotion.canBeMessage(notion);
			} else if ("Confirmation Dialog".equals(value)){
				if (NotionTypesEnum.Confirmation_Dialog.tag().equals(notion.getType())) return false;
				if (!MConfiguration.isCheckRelations()) return true;
				return MNotion.canBeConfirmationDialog(notion);
			} else if ("Trigger".equals(value)){
				if (NotionTypesEnum.Trigger.tag().equals(notion.getType())) return false;
				if (!MConfiguration.isCheckRelations()) return true;
				return MNotion.canBeTrigger(notion);
			} else if ("Attribute".equals(value)){
				if (NotionTypesEnum.Attribute.tag().equals(notion.getType())) return false;
				if (!MConfiguration.isCheckRelations()) return true;
				return MNotion.canBeAttribute(notion);
			} else if ("Option".equals(value)){
				if (NotionTypesEnum.Option.tag().equals(notion.getType())) return false;
				if (!MConfiguration.isCheckRelations()) return true;
				return MNotion.canBeOption(notion);
			} else if ("List View".equals(value)){
				if (NotionTypesEnum.List_View.tag().equals(notion.getType())) return false;
				if (!MConfiguration.isCheckRelations()) return true;
				return MNotion.canBeListView(notion);
			} else if ("Simple View".equals(value)){
				if (NotionTypesEnum.Simple_View.tag().equals(notion.getType())) return false;
				if (!MConfiguration.isCheckRelations()) return true;
				return MNotion.canBeSimpleView(notion);
			} else if ("Tree View".equals(value)){
				if (NotionTypesEnum.Tree_View.tag().equals(notion.getType())) return false;
				if (!MConfiguration.isCheckRelations()) return true;
				return MNotion.canBeTreeView(notion);
			}
		}
		return false;
	}

}
