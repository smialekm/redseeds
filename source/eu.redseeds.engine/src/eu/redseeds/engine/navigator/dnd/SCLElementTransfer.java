package eu.redseeds.engine.navigator.dnd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class SCLElementTransfer extends ByteArrayTransfer {
	private static SCLElementTransfer instance = new SCLElementTransfer();
	private static final String TYPE_NAME = "sclelement-transfer-format";
	private static final int TYPEID = registerType(TYPE_NAME);

	/**
	 * Returns the singleton SCLElementTransfer instance.
	 */
	public static SCLElementTransfer getInstance() {
		return instance;
	}
	
	/**
	 * Avoid explicit instantiation
	 */
	private SCLElementTransfer() {
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
		Object[] elems = (Object[])sclelems[0];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    DataOutputStream dos = new DataOutputStream(bos);
		for (int i = 0; i < elems.length; i++) {
			Object object = elems[i];
			if (object instanceof eu.redseeds.scl.sclkernel.SCLElement) {
				eu.redseeds.scl.sclkernel.SCLElement elem 
					= (eu.redseeds.scl.sclkernel.SCLElement) object;
				long vertexId = elem.getId();
			    try {
					dos.writeLong(vertexId);
				} catch (IOException e) {
					// TODO: handle exception
				}
			}
		}
		try {
			dos.flush();
		} catch (IOException e) {
			// TODO: handle exception
		}
		if (bos.size()>0) return bos.toByteArray();
		else return new byte[1];
	}
	 
	 protected Long[] fromByteArray(byte[] bytes) {
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
		boolean end = false;
		List<Long> result = new ArrayList<Long>();
		while (!end){
			try {
				result.add(in.readLong());
			} catch (IOException e) {
				end = true;
			}
		}
		return result.toArray(new Long[0]);
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
		else if(obj instanceof RequirementDTO) {
			return true;
		}
		else if(obj instanceof RequirementsPackageDTO) {
			return true;
		}
		else if(obj instanceof ActorDTO) {
			return true;
		}
		else if(obj instanceof ActorsPackageDTO) {
			return true;
		}
		else if(obj instanceof NotionDTO) {
			return true;
		}
		else if(obj instanceof NotionsPackageDTO) {
			return true;
		}
		else if(obj instanceof SystemElementDTO) {
			return true;
		}
		else if(obj instanceof SystemElementsPackageDTO) {
			return true;
		}
		else if(obj instanceof IFile) {
			return true;
		}
		return false;
	}

}
