package eu.redseeds.sc.query.engine.impl;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.query.engine.SimilarSCProject;
import eu.redseeds.sc.query.engine.SimilarityValue;

public class SimilarSCProjectImpl implements SimilarSCProject {

	private SCProject scProject;
	private SimilarityValue similarityValue;
	private SCProject currentScProject;

	public SimilarSCProjectImpl(){
	}

	public SimilarSCProjectImpl(SCProject currentScProject, SimilarityValue similarityValue,SCProject pastScProject){
		this.scProject = pastScProject;
		this.similarityValue = similarityValue;
		this.currentScProject=currentScProject;
	}

	public SCProject getScProject() {
		return scProject;
	}
	public void setScProject(SCProject scProject) {
		this.scProject = scProject;
	}
	public SimilarityValue getSimilarityValue() {
		return similarityValue;
	}
	public void setSimilarityValue(SimilarityValue similarityValue) {
		this.similarityValue = similarityValue;
	}

	public SCProject getCurrentScProject() {
		return currentScProject;
	}
}
