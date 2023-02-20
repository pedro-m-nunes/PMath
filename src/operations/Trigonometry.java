package operations;

import cps.Combinatorics;
import exceptions.ConversionException;
import exceptions.FunctionDomainException;
import numbers.PNumber;
import numbers.Real;

public class Trigonometry {
	
	/* Attributes */
	
	//public static final int ARC_ITERATIONS = 2000; // Used by inverse trigonometric functions. The bigger, the closer the function output is to the actual value, but the longer the computation process.
	
	/* Methods */
	
	public static Real toDegrees(Real radians) { return toDegrees(radians, Real.DEFAULT_SCALE); }
	
	public static Real toDegrees(Real radians, int scale) {
		try {
			return radians.mult(180).div(PNumber.pi(), scale);
		} catch (FunctionDomainException ex) { ex.printStackTrace(); return null; }
	}
	
	// BigDecimal, double
	
	public static Real toRadians(Real degrees) { return toRadians(degrees, Real.DEFAULT_SCALE); }
	
	public static Real toRadians(Real degrees, int scale) {
		try {
			return degrees.mult(PNumber.pi(scale)).div(180, scale);
		} catch (FunctionDomainException ex) { ex.printStackTrace(); return null; }
	}
	
	// BigDecimal, double
	
	/** Trigonometric Functions **/
	
	// sin(z) = i * sinh(z)
	// (ie^(-ix) - ie^(ix)) / 2
	public static Real sin(Real x) { return sin(x, Real.DEFAULT_SCALE); }
	
	public static Real sin(Real x, int scale) {
		Real result = new Real(0);
		Real previous = result;
		
		for(int n = 0; true; n++) {
			try {
				result = result.add(x.pow(2 * n + 1).mult(Arithmetic.pow(-1, n)).div(Combinatorics.factorial(2 * n + 1), scale));
			} catch (FunctionDomainException ex) { ex.printStackTrace(); }
			if(result.equals(previous)) return result;
			previous = result;
		}
	}
	
	// sinc ?
	
	// cospi(x)?
	// (e^(-ix) + e^(ix)) / 2
	public static Real cos(Real x) { return cos(x, Real.DEFAULT_SCALE); }
	
	public static Real cos(Real x, int scale) {
		Real result = new Real(1); // 0^0
		Real previous = result;
		
		for(int n = 1; true; n++) {
			try {
				result = result.add(x.pow(2 * n).mult(Arithmetic.pow(-1, n)).div(Combinatorics.factorial(2 * n), scale));
			} catch (FunctionDomainException ex) { ex.printStackTrace(); return null; }
			if(result.equals(previous)) return result;
			previous = result;
		}
	}
	
	public static Real tan(Real x) throws FunctionDomainException { return tan(x, Real.DEFAULT_SCALE); }
	
	public static Real tan(Real x, int scale) throws FunctionDomainException { // taylor series? (bernoulli numbers)
		return sin(x, scale).div(cos(x, scale));
	}
	
	public static Real sec(Real x) throws FunctionDomainException { return sec(x, Real.DEFAULT_SCALE); }
	
	public static Real sec(Real x, int scale) throws FunctionDomainException { return new Real(1).div(cos(x, scale), scale); }
	
	public static Real csc(Real x) throws FunctionDomainException { return csc(x, Real.DEFAULT_SCALE); }
	
	public static Real csc(Real x, int scale) throws FunctionDomainException { return new Real(1).div(sin(x, scale), scale); }
	
	public static Real cot(Real x) throws FunctionDomainException { return cot(x, Real.DEFAULT_SCALE); }
	
	public static Real cot(Real x, int scale) throws FunctionDomainException { return cos(x, scale).div(sin(x, scale)); }
	
	// BigDecimal, double ...
	
	/** Inverse Trigonometric Functions **/ // 2*pi*n
	
	// arcsin // [-1, 1]
	
	// arccos(x) = pi/2 - arcsin(x)
		
	//https://mathworld.wolfram.com/InverseTangent.html
	public static Real arctan(Real x) { return arctan(x, Real.DEFAULT_SCALE); }
	
	public static Real arctan(Real x, int scale) { // return pi/2 // algo errado?
		//if(abs(x) == Inf) return sign(x) * pi/2; ...
		
		Real result = new Real(0);
		Real previous = result;
		
		for(int n = 0; true; n++) {
			try {
				result = result.add(
						x.pow(2 * n + 1, scale).mult(Arithmetic.pow(2, 2 * n, scale)).mult(Combinatorics.factorial(n).squared())
						.div(Combinatorics.factorial(2 * n + 1).mult(x.squared().add(1).pow(n + 1, scale)), scale)
						);
			} catch (Exception ex) { ex.printStackTrace(); return null; }
			if(result.equals(previous)) return result;
			previous = result;
		}
	}
	
	// atan2 ?
	
	// arcsec, arccsc, arccot
	
	/** Hyperbolic Trigonometric Functions **/
	
	public static Real sinh(Real x) { return sinh(x, Real.DEFAULT_SCALE); }
	
	public static Real sinh(Real x, int scale) {
		try {
			return Exponential.exp(x, scale).subt(Exponential.exp(x.negate(), scale)).div(2, scale);
		} catch (FunctionDomainException ex) { ex.printStackTrace(); return null; }
	}
	
	public static Real cosh(Real x) { return cosh(x, Real.DEFAULT_SCALE); }
	
	public static Real cosh(Real x, int scale) {
		try {
			return Exponential.exp(x, scale).add(Exponential.exp(x.negate(), scale)).div(2, scale);
		} catch (FunctionDomainException ex) { ex.printStackTrace(); return null; }
	}
	
	public static Real tanh(Real x) { return tanh(x, Real.DEFAULT_SCALE); }
	
	public static Real tanh(Real x, int scale) {
		try {
			return Exponential.exp(x, scale).subt(Exponential.exp(x.negate(), scale)).div(Exponential.exp(x, scale).add(Exponential.exp(x.negate(), scale)), scale);
		} catch (FunctionDomainException ex) { ex.printStackTrace(); return null; }
	}
	
	public static Real sech(Real x) { return sech(x, Real.DEFAULT_SCALE); }
	
	public static Real sech(Real x, int scale) {
		try {
			return new Real(1).div(cosh(x, scale), scale);
		} catch (FunctionDomainException ex) { ex.printStackTrace(); return null; }
	}
	
	public static Real csch(Real x) throws FunctionDomainException { return csch(x, Real.DEFAULT_SCALE); }

	public static Real csch(Real x, int scale) throws FunctionDomainException { return new Real(1).div(sinh(x, scale), scale); }
	
	public static Real coth(Real x) throws FunctionDomainException { return coth(x, Real.DEFAULT_SCALE); }
	
	public static Real coth(Real x, int scale) throws FunctionDomainException { return new Real(1).div(tanh(x, scale), scale); }
	
	// BigDecimal, double ...
	
	/** Inverse Hyperbolic Trigonometric Functions **/ // 2*pi*n
	
	// ConversionException??
	
	public static Real arcsinh(Real x) throws ConversionException { return arcsinh(x, Real.DEFAULT_SCALE); }
	
	public static Real arcsinh(Real x, int scale) throws ConversionException {
		try {
			return Exponential.ln(x.add(x.squared().add(1).sqrt(scale)).toReal(), scale);
		} catch (FunctionDomainException ex) { ex.printStackTrace(); return null; }
	}
	
	public static Real arccosh(Real x) throws FunctionDomainException, ConversionException { return arccosh(x, Real.DEFAULT_SCALE); }
	
	public static Real arccosh(Real x, int scale) throws FunctionDomainException, ConversionException {
		return Exponential.ln(x.add(x.squared().subt(1).sqrt(scale)).toReal(), scale);
	}
	
	public static Real arctanh(Real x) throws FunctionDomainException { return arctanh(x, Real.DEFAULT_SCALE); }
	
	public static Real arctanh(Real x, int scale) throws FunctionDomainException {
		return Exponential.ln(x.add(1).div(x.negate().add(1)), scale).div(2, scale);
	}
	
	public static Real arcsech(Real x) throws FunctionDomainException, ConversionException { return arcsech(x, Real.DEFAULT_SCALE); }
	
	public static Real arcsech(Real x, int scale) throws FunctionDomainException, ConversionException { return arccosh(new Real(1).div(x, scale), scale); }
	
	public static Real arccsch(Real x) throws FunctionDomainException, ConversionException { return arccsch(x, Real.DEFAULT_SCALE); }
	
	public static Real arccsch(Real x, int scale) throws FunctionDomainException, ConversionException { return arcsinh(new Real(1).div(x, scale), scale); }
	
	public static Real arccoth(Real x) throws FunctionDomainException { return arccoth(x, Real.DEFAULT_SCALE); }
	
	public static Real arccoth(Real x, int scale) throws FunctionDomainException { return arctanh(new Real(1).div(x, scale), scale); }
	
	// BigDecimal, double ...
	
	/** Trigonometric Functions with Complex Numbers **/
	
}
