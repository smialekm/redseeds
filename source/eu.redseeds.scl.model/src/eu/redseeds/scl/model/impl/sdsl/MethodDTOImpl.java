package eu.redseeds.scl.model.impl.sdsl;

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import eu.redseeds.scl.impl.ea.MethodImpl;

public class MethodDTOImpl extends MethodImpl {

	public MethodDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
	}
	
	//In old graph
	//3468 Method <7426 7427 -7429 -8829> \null f f "create" \null \null \null "";
	public void readAttributeValues(GraphIO io) throws GraphIOException {
		code = io.matchUtfString();
		setCode(code);
		try {
			guid = io.matchUtfString();
			setGuid(guid);
		} catch (GraphIOException ex) {
			//old graph
		}
		isAbstract = io.matchBoolean();
		setIsAbstract(isAbstract);
		isStatic = io.matchBoolean();
		setIsStatic(isStatic);
		name = io.matchUtfString();
		setName(name);
		stereotype = io.matchUtfString();
		setStereotype(stereotype);
		throwsException = io.matchUtfString();
		setThrowsException(throwsException);
		type = io.matchUtfString();
		setType(type);
		visibility = io.matchUtfString();
		setVisibility(visibility);
	}
	

}
