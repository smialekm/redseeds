package usecasediagram.diagram.dnd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import usecasediagram.UsecasediagramPackage;
import usecasediagram.diagram.part.UseCaseDiagramDiagramEditor;
import usecasediagram.diagram.part.UseCaseDiagramDiagramEditorUtil;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.recovery.model.RecoveryModelHelper;

public class DiagramElementTransfer extends ByteArrayTransfer {
	private static DiagramElementTransfer instance = new DiagramElementTransfer();
	private static final String TYPE_NAME = "sclelement-transfer-format";
	private static final int TYPEID = registerType(TYPE_NAME);

	/**
	 * Returns the singleton DiagramElementTransfer instance.
	 */
	public static DiagramElementTransfer getInstance() {
		return instance;
	}
	
	/**
	 * Avoid explicit instantiation
	 */
	private DiagramElementTransfer() {
	}

	/*
	 * Method declared on Transfer.
	 */
	protected int[] getTypeIds() {
		return new int[] { TYPEID };
	}
	
	/*
	 * Method declared on Transfer.
	 */
	protected String[] getTypeNames() {
		return new String[] { TYPE_NAME };
	}

	/*
	 * Method declared on Transfer.
	 */
	protected void javaToNative(Object object, TransferData transferData) {
		byte[] bytes = toByteArray(new Object[] {object});
		if (bytes != null)
			super.javaToNative(bytes, transferData);
	}

	/*
	 * Method declared on Transfer.
	 */
	protected Object nativeToJava(TransferData transferData) {
		byte[] bytes = (byte[]) super.nativeToJava(transferData);
		return fromByteArray(bytes);
	}
	
	 protected byte[] toByteArray(Object[] sclelems) {
		byte[] bytes = new byte[4];
		
		Object[] elems = (Object[])sclelems[0];

		for (int i = 0; i < elems.length; i++) {
			Object object = elems[i];
			if (object instanceof eu.redseeds.scl.sclkernel.SCLElement) {
				eu.redseeds.scl.sclkernel.SCLElement elem 
					= (eu.redseeds.scl.sclkernel.SCLElement) object;
				int vertexId = elem.getId();
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
			    DataOutputStream dos = new DataOutputStream(bos);
			    try {
					dos.writeInt(vertexId);
					dos.flush();
				} catch (IOException e) {
				}
				return bos.toByteArray();
			}
		}
		return bytes;
	}
	 
	 protected int fromByteArray(byte[] bytes) {
		 DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
		 try {
			return in.readInt();
		} catch (IOException e) {
			return -1;
		}
	 }
	 
	/**
	 * determines if object is of supported type
	 * @param obj
	 * @return
	 */
	public boolean isSupportedType(Object obj) {
		
		if(obj instanceof UseCaseDTO) {
			return true;
		}
		else if(obj instanceof ActorDTO) {
			return true;
		}
		else if(obj instanceof NotionDTO) {
			return true;
		}
		else if(obj instanceof SystemElementDTO) {
			return true;
		}
		return false;
	}
}
