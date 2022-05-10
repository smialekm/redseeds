package eu.redseeds.rsldl.engine.structure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rsldl.DLRepository;
import eu.redseeds.rsldl.engine.code.GMethod;
import eu.redseeds.rsldl.engine.code.IGElement;
import eu.redseeds.scl.model.BusinessLayerFacade;

public abstract class DLTransformationEngine {
	
	protected DLRepository repository;
	protected IDLSymbolicEngine symbolicEngine;
    protected Map<String, IGElement> result = new HashMap<String, IGElement>();
    
    public abstract GMethod solveQuery(String what, String [] knows, String basedOn);
    
    public abstract GMethod solveTransform(String to, String from, String basedOn);
    
    public abstract void solveCRUD(String what, String basedOn, BusinessLayerFacade businessLayerFacade);
    
    public void setRepository(DLRepository repository) {
        this.repository = repository;
    }
    
    public void setSymbolicEngine(IDLSymbolicEngine symbolicEngine) {
        this.symbolicEngine = symbolicEngine;
    }
    
    public List<IGElement> getResult() {
        List<IGElement> r = new ArrayList<IGElement>();
        r.addAll(result.values());
        return r;
    }
    
    public abstract void generateFilesForResult(String dir) throws IOException;

}
