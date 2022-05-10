package eu.redseeds.common.jobs;

import org.eclipse.jface.operation.IRunnableWithProgress;

public interface IJob extends IRunnableWithProgress {
    
    
    /**
     * number of subtasks  
     * @return
     */
    public int getTotalTime();
    
    /**
     * job's name
     * @return
     */
    public String getName();
    
}
