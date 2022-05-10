package eu.remics.domainlogic.translation;

public class Pair<L, R> {
	private final L classObject;
	private final R operation;
	public Pair(L left, R right){
		classObject = left;
		operation = right;
	}
	public L getClassObject(){return classObject;}
	public R getOperation(){return operation;}
}
