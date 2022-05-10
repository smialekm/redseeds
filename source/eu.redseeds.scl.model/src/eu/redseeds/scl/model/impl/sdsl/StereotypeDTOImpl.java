package eu.redseeds.scl.model.impl.sdsl;

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import eu.redseeds.scl.impl.uml.classes.kernel.StereotypeImpl;
import eu.redseeds.scl.model.sdsl.StereotypeDTO;

public class StereotypeDTOImpl extends StereotypeImpl implements StereotypeDTO {

	public StereotypeDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
	}
	
	public void readAttributeValues(GraphIO io) throws GraphIOException {
		try {
			keywords = io.matchUtfString();
			setKeywords(keywords);
			name = io.matchUtfString();
			setName(name);
			uid = io.matchUtfString();
			setUid(uid);
		} catch (GraphIOException ex) {
			if (name != null) {
				setUid(name);
				setName(null);
			}
		}
	}
	
}
