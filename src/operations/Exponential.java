package operations;

import cps.Combinatorics;
import exceptions.FunctionDomainException;
import numbers.Complex;
import numbers.PNumber;
import numbers.Real;

public class Exponential {
	
	/* Attributes */
	
	public static final int LN_ITERATIONS = 20;
	public static final int W_ITERATIONS = 50; // Used by the productLog function. The way it's implemented, 50 seems to be the ideal value.
	
	/* Methods */
	
	/** Exponentials **/
	
	public static Real exp(Real x) { return exp(x, Real.DEFAULT_SCALE); }
	
	public static Real exp(Real x, int scale) { // não precisa da exceção // "infinite" series ?
		Real result = new Real(1); // 0^0
		Real previous = result;
		
		for(int n = 1; true; n++) {
			try {
				result = result.add(x.pow(n).div(Combinatorics.factorial(n), scale));
			} catch (FunctionDomainException ex) { ex.printStackTrace(); return null; }
			if(result.equals(previous)) return result;
			previous = result;
		}
	}
	
	// exp(BigDecimal)?
	
	public static Real exp(double x) { return exp(new Real(x)); }
	
	public static Real exp(double x, int scale) { return exp(new Real(x), scale); }
	
	public static Complex exp(Complex z) { return exp(z, Real.DEFAULT_SCALE); }
	
	public static Complex exp(Complex z, int scale) {
		return new Complex(
				exp(z.getRealPart(), scale).mult(Trigonometry.cos(z.getImaginaryPart(), scale)), 
				exp(z.getRealPart(), scale).mult(Trigonometry.sin(z.getImaginaryPart(), scale))
				);
	}
	
	public static Real productExp(Real x) { return productExp(x, Real.DEFAULT_SCALE); } // Inverse of W(x).
	
	public static Real productExp(Real x, int scale) { return x.mult(exp(x, scale)); }
	
	// productExp(BigDecimal)?
	
	public static Real productExp(double x) { return productExp(new Real(x)); }
	
	public static Real productExp(double x, int scale) { return productExp(new Real(x), scale); }
	
	/** Logarithms **/
	
	// ln/naturalLog, log/log10, log(base, x)
	// https://en.wikipedia.org/wiki/Natural_logarithm#Efficient_computation
	// https://stackoverflow.com/questions/739532/logarithm-of-a-bigdecimal
	public static Real ln(Real x) throws FunctionDomainException { return ln(x, Real.DEFAULT_SCALE, LN_ITERATIONS); }
	
	public static Real ln(Real x, int scale) throws FunctionDomainException { return ln(x, scale, LN_ITERATIONS); }
	
	// https://en.wikipedia.org/wiki/Complex_logarithm - Calculating the principal value
	// ln x, branch
	public static Real ln(Real x, int scale, int iterations) throws FunctionDomainException { // Approximated by Newton's/Halley's method.
		if(x.getDouble() <= 0) throw new FunctionDomainException("ln(x) undefined for real values x <= 0."); // complex
		
		Real an = new Real(1);
		Real an1 = an;
		
		for(int n = 0; n < iterations; n++) {
			an1 = an.add(Arithmetic.div(x.subt(exp(an)), x.add(exp(an)), scale).mult(2));
			if(an1.equals(an)) return an1;
			an = an1;
		}
		
		return an1;
	}
	
	// ln BigDecimal
	
	public static Real ln(double x) throws FunctionDomainException { return ln(new Real(x)); }
	
	public static Real ln(double x, int scale) throws FunctionDomainException { return ln(new Real(x), scale); }
	
	public static Real ln(double x, int scale, int iterations) throws FunctionDomainException { return ln(new Real(x), scale, iterations); }
	
	// Log(z) = ln|z| + i*arg(z)
	
	public static Real log(Real base, Real x) throws FunctionDomainException { return log(base, x, Real.DEFAULT_SCALE, LN_ITERATIONS); }
	
	public static Real log(Real base, Real x, int scale) throws FunctionDomainException { return log(base, x, scale, LN_ITERATIONS); }
	
	public static Real log(Real base, Real x, int scale, int iterations) throws FunctionDomainException {
		//if(base.getDouble() <= 0 || base.getDouble() == 1) return null; //Exception ?? (deixar para ln lidar)?
		// base == 1, == 0 ??
		return ln(x, scale, iterations).div(ln(base, scale, iterations), scale);
	}
	
	// log BD double
	
	public static Real log10(Real x) throws FunctionDomainException { return log(new Real(10), x); } // double 10
	
	public static Real log10(Real x, int scale) throws FunctionDomainException { return log(new Real(10), x, scale); }
	
	public static Real log10(Real x, int scale, int iterations) throws FunctionDomainException { return log(new Real(10), x, scale, iterations); }
	
	// log10 BD double
	
	// w/productLog(x, branch)
	// https://www.quora.com/How-is-the-Lambert-W-Function-computed
	public static Real productLog(Real x) throws FunctionDomainException { return productLog(x, Real.DEFAULT_SCALE, W_ITERATIONS); }
	
	public static Real productLog(Real x, int scale) throws FunctionDomainException { return productLog(x, scale, W_ITERATIONS); }
	
	public static Real productLog(Real x, int scale, int iterations) throws FunctionDomainException { // Lambert W function, approximated by Halley's method.
		if(x.compareTo(new Real(-1).div(PNumber.e(), Real.CONSTANT_SCALE)) < 0) throw new FunctionDomainException("W(x) undefined for real values x < -1/e."); // retorna Complex
		//x.compareTo(exp(-1).negate()) < 0
		
		Real an1 = new Real(1); //lnx?
		Real an = an1;
		
		for(int n = 0; n < iterations; n++) {
			an1 = an1.subt(productExp(an1, scale).subt(x).div(an1.add(1).mult(exp(an1)).subt(an1.add(2).mult(productExp(an1).subt(x)).div(an1.mult(2).add(2), scale)), scale));
			if(an1.equals(an)) return an1;
			an = an1;
		}
		
		return an1;
	}
	
	// productLog(BigDecimal), productLog(double) ?
	
}
