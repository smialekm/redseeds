package eu.redseeds.rsldl.engine.code.utilities;

import java.util.Comparator;

import eu.redseeds.rsldl.engine.code.GMethod;

public class GMethodSequenceComparator implements Comparator<GMethod> {

    @Override
    public int compare(GMethod o1, GMethod o2) {
        if (o1.isStatic() && !o2.isStatic()) return -1;
        if (!o1.isStatic() && o2.isStatic()) return 1;
        if (o1.getReturnType().isEmpty() && !o2.getReturnType().isEmpty()) return -1;
        if (!o1.getReturnType().isEmpty() && o2.getReturnType().isEmpty()) return 1;
        return 0;
    }
    
}
