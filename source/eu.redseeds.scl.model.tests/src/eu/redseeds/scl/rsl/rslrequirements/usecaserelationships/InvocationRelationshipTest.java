package eu.redseeds.scl.rsl.rslrequirements.usecaserelationships;

import junit.framework.TestCase;
import de.uni_koblenz.jgralab.GraphFactory;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.SCLSchema;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsspecifications.UseCaseDTOImpl;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.rsl.rslrequirements.usecaserelationships.InvocationRelationship;

public class InvocationRelationshipTest extends TestCase {

    public void testInvocationTarget() {
            SCLGraph graph = SCLSchema.instance().createSCLGraph(10, 10);
            InvocationRelationship rel = graph.createInvocationRelationship();
        RSLUseCase rsluc = graph.createRSLUseCase();
        rel.addTarget(rsluc);
        assertEquals(1, rel.getTargetList().size());
        //rel is accessible via rolename "Invoked"
        assertEquals(1, rsluc.getInvokedList().size());
        assertEquals(rel, rsluc.getInvokedList().get(0));
        //nothing is accessuble via rolename "Invoke"
        assertEquals(0, rsluc.getInvokeList().size());
    }
    
    public void testInvocationDTOTarget() {
    	GraphFactory factory = SCLSchema.instance().getGraphFactory();
    	factory.setVertexImplementationClass(RSLUseCase.class, UseCaseDTOImpl.class);
        SCLGraph graph = SCLSchema.instance().createSCLGraph(10, 10);
        InvocationRelationship rel = graph.createInvocationRelationship();
        UseCaseDTO rsluc = (UseCaseDTOImpl)graph.createRSLUseCase();
    rel.addTarget((UseCaseDTOImpl)rsluc);
    assertEquals(1, rel.getTargetList().size());
    //rel is accessible via rolename "Invoked"
    assertEquals(1, ((UseCaseDTOImpl)rsluc).getInvokedList().size());
    assertEquals(rel, ((UseCaseDTOImpl)rsluc).getInvokedList().get(0));
    //nothing is accessuble via rolename "Invoke"
    assertEquals(0, ((UseCaseDTOImpl)rsluc).getInvokeList().size());
}

    public void testInvocationSource() {
            SCLGraph graph = SCLSchema.instance().createSCLGraph(10, 10);
            InvocationRelationship rel = graph.createInvocationRelationship();
        RSLUseCase rsluc = graph.createRSLUseCase();
        rel.addSource(rsluc);
        //rel is accessible via rolename "Invoke"
        assertEquals(1, rel.getSourceList().size());
        assertEquals(1, rsluc.getInvokeList().size());
        assertEquals(rel, rsluc.getInvokeList().get(0));
        //nothing is accessuble via rolename "Invokes"
        assertEquals(0, rsluc.getInvokedList().size());
        RSLUseCase rsluc2 = graph.createRSLUseCase();
        rel.addTarget(rsluc2);
        //target is now accessible
        assertEquals(1, rel.getTargetList().size());
        assertEquals(1, rsluc2.getInvokedList().size());
        assertEquals(rel, rsluc2.getInvokedList().get(0));
        //source is unchanged
        assertEquals(1, rel.getSourceList().size());
        assertEquals(1, rsluc.getInvokeList().size());
        assertEquals(rel, rsluc.getInvokeList().get(0));

    }

}

