package cps;

import java.util.Arrays;

import exceptions.ConversionException;
import exceptions.FunctionDomainException;
import numbers.Real;
import operations.Arithmetic;

public class Statistics { // class DataSet? (Population, Sample?)
	
	/* Attributes */
	
	/* Methods */
	
	// double[] => Real[]
	
	public static Real sum(Real[] dataSet) {
		Real sum = new Real(0);
		
		for(Real number : dataSet) sum = sum.add(number);
		
		return sum;
	}
	
	// sumOfSquares
	
	// outra exception? - length = 0
	// "arithmeticMean"?, "arithMean"?
	public static Real avg(Real[] dataSet) throws FunctionDomainException { return avg(dataSet, Real.DEFAULT_SCALE); }
	
	public static Real avg(Real[] dataSet, int scale) throws FunctionDomainException {
		return sum(dataSet).div(dataSet.length, scale);
	}
	
	// geometricMean, harmonicMean
	// https://en.wikipedia.org/wiki/Average
	
	public static Real variance(Real[] dataSet) throws FunctionDomainException { return variance(dataSet, Real.DEFAULT_SCALE); }
	
	public static Real variance(Real[] dataSet, int scale) throws FunctionDomainException {
		Real sum = new Real(0);
		
		for(Real number : dataSet) sum = sum.add(number.subt(avg(dataSet, scale)).squared());
		
		return sum.div(dataSet.length, scale);
	}
	
	public static Real varianceSample(Real[] dataSet) throws FunctionDomainException { return varianceSample(dataSet, Real.DEFAULT_SCALE); }
	
	public static Real varianceSample(Real[] dataSet, int scale) throws FunctionDomainException {
		Real sum = new Real(0);
		
		for(Real number : dataSet) sum = sum.add(number.subt(avg(dataSet, scale)).squared());
		
		return sum.div(dataSet.length - 1, scale);
	}
	
	public static Real standardDeviation(Real[] dataSet) throws FunctionDomainException { return standardDeviation(dataSet, Real.DEFAULT_SCALE); }
	
	public static Real standardDeviation(Real[] dataSet, int scale) throws FunctionDomainException {
		try {
			return Arithmetic.sqrt(variance(dataSet), scale).toReal();
		} catch (ConversionException ex) { ex.printStackTrace(); return null; }
	}
	
	public static Real standardDeviationSample(Real[] dataSet) throws FunctionDomainException { return standardDeviationSample(dataSet, Real.DEFAULT_SCALE); }
	
	public static Real standardDeviationSample(Real[] dataSet, int scale) throws FunctionDomainException {
		try {
			return Arithmetic.sqrt(varianceSample(dataSet), scale).toReal();
		} catch (ConversionException ex) { ex.printStackTrace(); return null; }
	}
	
	// sortAsc ??? - fazer próprio sort?
	
	public static Real median(Real[] dataSet) { return median(dataSet, Real.DEFAULT_SCALE); }
	
	public static Real median(Real[] dataSet, int scale) {
		Real[] sortedNumbers = Arrays.copyOf(dataSet, dataSet.length);
		Arrays.sort(sortedNumbers);
		
		if(Real.valueOf(sortedNumbers.length).isEven())
			try {
				return sortedNumbers[(sortedNumbers.length - 1) / 2].add(sortedNumbers[(sortedNumbers.length - 1) / 2 + 1]).div(2, scale);
			} catch (FunctionDomainException ex) { ex.printStackTrace(); return null; }
		else return sortedNumbers[sortedNumbers.length / 2];
	}
	
	// mode
	
	public static Real min(Real[] dataSet) {
		Real[] sortedNumbers = Arrays.copyOf(dataSet, dataSet.length);
		Arrays.sort(sortedNumbers);
		return sortedNumbers[0];
	}
	
	public static Real max(Real[] dataSet) {
		Real[] sortedNumbers = Arrays.copyOf(dataSet, dataSet.length);
		Arrays.sort(sortedNumbers);
		return sortedNumbers[sortedNumbers.length - 1];
	}
	
	public static Real range(Real[] dataSet) { // amplitude
		return max(dataSet).subt(min(dataSet));
	}
	
	public static Real midrange(Real[] dataSet) { return midrange(dataSet, Real.DEFAULT_SCALE); }
	
	public static Real midrange(Real[] dataSet, int scale) {
		try {
			return max(dataSet).add(min(dataSet)).div(2, scale);
		} catch (FunctionDomainException ex) { ex.printStackTrace(); return null; }
	}
	
}
