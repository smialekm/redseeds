package rsldl.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import rsldl.diagram.edit.commands.DLAlghoritmicTransitionCreateCommand;
import rsldl.diagram.edit.commands.DLDataBasedReferenceCreateCommand;
import rsldl.diagram.edit.commands.DLEntityCreateCommand;
import rsldl.diagram.edit.commands.DLPatternBasedReferenceCreateCommand;
import rsldl.diagram.edit.commands.DLPatternBasedTransitionCreateCommand;
import rsldl.diagram.edit.commands.DLPrimitiveCreateCommand;
import rsldl.diagram.edit.commands.DLPropertyCreateCommand;
import rsldl.diagram.providers.RsldlElementTypes;

/**
 * @generated
 */
public class DLDiagramItemSemanticEditPolicy extends
		RsldlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public DLDiagramItemSemanticEditPolicy() {
		super(RsldlElementTypes.DLDiagram_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (RsldlElementTypes.DLEntity_2001 == req.getElementType()) {
			return getGEFWrapper(new DLEntityCreateCommand(req));
		}
		if (RsldlElementTypes.DLProperty_2002 == req.getElementType()) {
			return getGEFWrapper(new DLPropertyCreateCommand(req));
		}
		if (RsldlElementTypes.DLPrimitive_2003 == req.getElementType()) {
			return getGEFWrapper(new DLPrimitiveCreateCommand(req));
		}
		if (RsldlElementTypes.DLDataBasedReference_2004 == req.getElementType()) {
			return getGEFWrapper(new DLDataBasedReferenceCreateCommand(req));
		}
		if (RsldlElementTypes.DLPatternBasedReference_2005 == req
				.getElementType()) {
			return getGEFWrapper(new DLPatternBasedReferenceCreateCommand(req));
		}
		if (RsldlElementTypes.DLAlghoritmicTransition_2006 == req
				.getElementType()) {
			return getGEFWrapper(new DLAlghoritmicTransitionCreateCommand(req));
		}
		if (RsldlElementTypes.DLPatternBasedTransition_2007 == req
				.getElementType()) {
			return getGEFWrapper(new DLPatternBasedTransitionCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}

	}

}
