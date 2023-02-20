package cps;

import java.math.BigDecimal;

import exceptions.FunctionDomainException;
import numbers.Real;
import operations.Arithmetic;

public class Combinatorics {
	
	/* Attributes */
	
	/* Methods */
	
	public static Real factorial(Real n) {
		if(n.isNatural()) {
			if(n.getDouble() == 0) return new Real(1);
			
			Real result = new Real(n);
			result = result.mult(new Real(factorial(n.subt(1))));
			return result;
			
		} else { return null; } // Exception | integral (piFunction, gammaFunction)
	}
	
	public static Real factorial(BigDecimal n) { return factorial(new Real(n)); }
	
	public static Real factorial(double n) { return factorial(new Real(n)); }
	
	public static Real doubleFactorial(Real n) { // números negativos? // manter msm sem integrais?
		if(n.isNatural()) { // -1!! =[def]= 1
			if(n.getDouble() == 0 || n.getDouble() == 1) return new Real(1);
			
			Real result = new Real(n);
			result = result.mult(new Real(doubleFactorial(n.subt(2))));
			return result;
			
		} else { return null; } // NaN | Exception
	}
	
	public static Real doubleFactorial(BigDecimal n) { return doubleFactorial(new Real(n)); }
	
	public static Real doubleFactorial(double n) { return doubleFactorial(new Real(n)); }
	
	// fallingFactorial, risingFactorial ?
	
	// binomialCoefficient
	public static Real binomialCoefficient(Real n, Real k) { return binomialCoefficient(n, k, Real.DEFAULT_SCALE); }
	
	public static Real binomialCoefficient(Real n, Real k, int scale) {
		if(n.isNatural() && k.isNatural()) {
			try {
				return factorial(n).div(factorial(k).mult(factorial(n.subt(k))), scale);
			} catch (Exception ex) { ex.printStackTrace(); return null; }
		//} else if(n.isInteger()) {
		} else { return null; } // exception, gammaFunction
	}
	
	// pochhammer ?
	// pochhamer(x, y) = binomialCoefficient(x, y) * factorial(y)
	// factorial(x) = gamma(x + 1)
	/*public static Real pochhammer(Real x, Real n) { // não funciona
		Real result = x;
		Real multiplicand;
		for(int i = 0; true; i++) {
			multiplicand = x.add(i);
			result = result.mult(multiplicand);
			//System.out.println(result); //
			//System.out.println(multiplicand); //
			if(x.add(n).subt(1).equals(multiplicand)) break;
		}
		return result;
	}//*/
	// https://mathworld.wolfram.com/StirlingNumberoftheFirstKind.html
	// https://mathworld.wolfram.com/BinomialCoefficient.html
	// https://mathworld.wolfram.com/PochhammerSymbol.html
	
	// kroneckerDelta, harmonicNumber, binomialCoefficient
}
