/**
 * 
 */
package eu.redseeds.sc.query.comparefunctions;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map.Entry;

import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.query.engine.ukocompare.UkoCompareEngine;


/**
 * @author horn
 * 
 */
public abstract class AbstractCompareFunction {

	public static boolean DEBUG = false;

	private class SumCountTuple {
		BigInteger sum;
		long count;

		SumCountTuple(BigInteger s, long c) {
			sum = s;
			count = c;
		}
	}

	public abstract double ukoCompare(UkoCompareEngine engine, Vertex vertex1, Vertex vertex2);
	
	protected static HashMap<String, SumCountTuple> computationTimes = new HashMap<String, SumCountTuple>();

	public void recordComputationTime(String functionName, long startTime,
			long endTime) {
		if (computationTimes.containsKey(functionName)) {
			SumCountTuple tup = computationTimes.get(functionName);
			tup.sum = tup.sum.add(new BigInteger(Long.toString(endTime
					- startTime)));
			tup.count++;
		} else {
			computationTimes.put(functionName, new SumCountTuple(
					new BigInteger(Long.toString(endTime - startTime)), 1));
		}
	}

	public static void printComputationTimes() {
		if (computationTimes.isEmpty()) {
			System.out.println("No compare times were recorded...");
			return;
		}
		System.out.println();
		System.out.println("The CompareFunctions took those average times:");
		System.out.println("----------------------------------------------");
		for (Entry<String, SumCountTuple> e : computationTimes.entrySet()) {
			SumCountTuple sct = e.getValue();
			System.out.println(e.getKey() + ": \t"
					+ sct.sum.divide(new BigInteger(Long.toString(sct.count)))
					+ " ns. (" + sct.count + " calls / " + sct.sum
					+ " ns in total)");
		}
	}
}
