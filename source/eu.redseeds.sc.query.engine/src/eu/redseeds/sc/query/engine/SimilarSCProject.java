package eu.redseeds.sc.query.engine;

import eu.redseeds.sc.current.repository.SCProject;

public interface SimilarSCProject {


	public SCProject getScProject();

	public void setScProject(SCProject scProject);

	public SimilarityValue getSimilarityValue();

	public void setSimilarityValue(SimilarityValue similarityValue);

	public SCProject getCurrentScProject();

}
