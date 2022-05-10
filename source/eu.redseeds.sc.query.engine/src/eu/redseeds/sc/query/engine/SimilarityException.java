package eu.redseeds.sc.query.engine;

public class SimilarityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7438880374500576271L;

	public SimilarityException(String message) {
		super(message);
	}
	
	public SimilarityException(String message, Exception cause) {
		super(message, cause);
	}

}
