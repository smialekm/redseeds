package eu.redseeds.sc.query.comparefunctions;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.query.engine.ukocompare.UkoCompareEngine;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkedSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentence;

/**
 * This function compares two RSL {@link HyperlinkedSentence}s on the basis of
 * their words by using Levensthein's editing difference. This method is fine for
 * {@link NaturalLanguageHypertextSentence}s.
 * 
 * @author dbildh
 * 
 */
public abstract class AbstractLevenshteinSimilarity<T> extends
		AbstractCompareFunction {
	
	
		
		
	protected double compareElementLists(UkoCompareEngine engine, List<T> list1, List<T> list2) {	
		double ldist = levenshteinDistance(engine, list1, list2);
	    int size = 0;
	    if (list2.size() > list1.size())
	       size = list2.size();
	    else
	       size = list1.size();
		double normalizedLDist = ldist / size;
		double result = 1-normalizedLDist;
		return result;
	}
	
           
	            
	private class Matrix {
	
	    List<List<Double>> cells;
	    
	    Matrix(int rows, int cols) {
	    	cells = new ArrayList<List<Double>>();
	    	for (int i=0;i<rows;i++) {
	    		List<Double> currentList = new ArrayList<Double>();
	    		cells.add(currentList);
	    		for (int j=0; j<cols; j++)
	    			currentList.add(0.0);
	    	}
	    }
	    
	    double get(int row, int col) {
	    	return cells.get(row).get(col);
	    }
	    
	    void set(int row, int col, double val) {
	    	cells.get(row).set(col, val);
	    }
	        	   
	}

	
    private double minimum(double val1, double val2, double val3) {
    	if (val1 < val2) {
    		return val1 < val3 ? val1 : val3; 
    	} else {
    		return val2 < val3 ? val2 : val3;
    	}
    }
	
	
	private double levenshteinDistance(UkoCompareEngine engine, List<T> list1, List<T> list2) {
	     Matrix distance = new Matrix(list1.size()+1, list2.size()+1);
	     for (int i=0; i<list1.size()+1; i++) {
	    	 distance.set(i,0,i);
	     }
	     for (int j=0; j<list2.size()+1; j++) {
	    	 distance.set(0,j,j);
	     }
	     for (int i=1; i<list1.size()+1; i++) {
		     for (int j=1; j<list2.size()+1; j++) {
	    		 double costs = 1-elementSimilarity(engine, list1.get(i-1), list2.get(j-1));
	    		 double min = minimum(distance.get(i-1,j)+1, distance.get(i, j-1)+1, distance.get(i-1,j-1)+costs);
		    	 distance.set(i,j,min);
		     }
	     }
	     return distance.get(list1.size(), list2.size());
    }
	            

	protected abstract double elementSimilarity(UkoCompareEngine engine, T element1, T element2);




}
