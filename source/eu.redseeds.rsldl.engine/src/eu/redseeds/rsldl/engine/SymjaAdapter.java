package eu.redseeds.rsldl.engine;

import org.matheclipse.core.basic.Config;
import org.matheclipse.core.eval.ExprEvaluator;

import eu.redseeds.rsldl.engine.structure.IDLSymbolicEngine;

public class SymjaAdapter implements IDLSymbolicEngine {


    public SymjaAdapter() {
        Config.PARSER_USE_LOWERCASE_SYMBOLS = false;
    }
    
    @Override
    public String[] solve(String formula, String variable) {
        ExprEvaluator sv = new ExprEvaluator();
        String r = sv.evaluate("Solve("+formula+","+variable+")").toString();
        if (!r.contains("->"))
            return null;
        r = r.replaceAll("^\\{\\{"+variable.toLowerCase()+"->","").replaceAll("\n", "");
        return r.split("\\},\\{"+variable.toLowerCase()+"->|\\}\\}$");
    }

    @Override
    public boolean canSolve(String formula, String variable) {
        return null!=solve(formula, variable);
    }

}
