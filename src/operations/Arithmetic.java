package operations;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import exceptions.ConversionException;
import exceptions.FunctionDomainException;
import numbers.CalcConstant;
import numbers.Complex;
import numbers.PNumber;
import numbers.Real;

public class Arithmetic {
	
	/* Attributes */
			
	/* Methods with Real Numbers */
	
	/** Basic Operations **/
	
	/*** using Real ***/
	
	public static Real add(Real a, Real b) { return new Real(a.getValue().add(b.getValue())); }
	
	public static Real subt(Real minuend, Real subtrahend) { return new Real(minuend.getValue().subtract(subtrahend.getValue())); }
	
	public static Real mult(Real a, Real b) { return new Real(a.getValue().multiply(b.getValue())); }
	
	// n / 0 -> sign(n) * Inf
	public static Real div(Real dividend, Real divisor) throws FunctionDomainException { return div(dividend, divisor, Real.DEFAULT_SCALE); }
	public static Real div(Real dividend, Real divisor, int scale) throws FunctionDomainException {
		if(divisor.getDouble() == 0) throw new FunctionDomainException("Division by zero.");
		return new Real(dividend.getValue().divide(divisor.getValue(), scale, Real.ROUNDING_MODE));
	}
	
	// mod(a, 0) ?? -> Exception
	// n mod 0 -> 0
	public static Real mod(Real dividend, Real divisor) { return new Real(dividend.getValue().remainder(divisor.getValue())); }
	
	public static Real pow(Real base, Real power) throws FunctionDomainException { return pow(base, power, Real.DEFAULT_SCALE); }
	
	public static Real pow(Real base, Real power, int scale) throws FunctionDomainException {
		if(base.getDouble() == 0 && power.getDouble() <= 0) throw new FunctionDomainException("0^x is only defined for positive real values of x."); // +-
		
		if(power.isNatural()) {
			Real result = new Real(1);
			for(int i = 0; i < power.getDouble(); i++) result = result.mult(base);
			return result;
		} else if(power.isInteger()) {
			Real result = new Real(1);
			for(int i = 0; i < power.negate().getDouble(); i++) result = result.div(base, scale);
			return result;
		} else {
			return Exponential.exp(power.mult(Exponential.ln(base, scale)), scale); // manter?
			// ln complexo!!
		} // nthRoot, Fraction?
	}
	
	// tetration
	// https://en.wikipedia.org/wiki/Tetration
	// Higher order approximations for real heights
	
	public static Complex sqrt(Real x) { return sqrt(x, Real.DEFAULT_SCALE); }
	
	public static Complex sqrt(Real x, int scale) { // Newton's method? nthRoot?
		MathContext mathCont = new MathContext(scale + 6, Real.ROUNDING_MODE);
		
		if(x.isNegative()) return new Complex(0, x.negate().getValue().sqrt(mathCont).setScale(scale, Real.ROUNDING_MODE));
		else return new Complex(x.getValue().sqrt(mathCont).setScale(scale, Real.ROUNDING_MODE), 0);
	}
	
	// cbrt
	
	// nthRoot
	//public static Real nthRoot() {}
	
	public static Complex[] quadraticFormula(Real a, Real b, Real c) throws FunctionDomainException { return quadraticFormula(a, b, c, Real.DEFAULT_SCALE); }
	
	public static Complex[] quadraticFormula(Real a, Real b, Real c, int scale) throws FunctionDomainException {
		Complex result[] = new Complex[2];
		
		result[0] = Complex.valueOf(b.negate()).add(sqrt(b.squared().subt(a.mult(c).mult(4)), scale)).div(a.mult(2).toComplex(), scale);
		result[1] = Complex.valueOf(b.negate()).subt(sqrt(b.squared().subt(a.mult(c).mult(4)), scale)).div(a.mult(2).toComplex(), scale);
		
		return result;
	}
	
	// quadratic complex abc?
	
	/*** using BigDecimal ***/
	
	public static Real add(BigDecimal a, BigDecimal b) { return new Real(a.add(b)); }
	
	public static Real subt(BigDecimal minuend, BigDecimal subtrahend) { return new Real(minuend.subtract(subtrahend)); }
	
	public static Real mult(BigDecimal a, BigDecimal b) { return new Real(a.multiply(b)); }
	
	public static Real div(BigDecimal dividend, BigDecimal divisor) { return div(dividend, divisor, Real.DEFAULT_SCALE); }
	public static Real div(BigDecimal dividend, BigDecimal divisor, int scale) { return new Real(dividend.divide(divisor, scale, Real.ROUNDING_MODE)); }
	
	public static Real mod(BigDecimal dividend, BigDecimal divisor) { return new Real(dividend.remainder(divisor)); }
	
	public static Real pow(BigDecimal base, BigDecimal power) throws FunctionDomainException { return pow(new Real(base), new Real(power)); }
	// pow scale
	
	public static Complex sqrt(BigDecimal x) { return sqrt(new Real(x)); }
	public static Complex sqrt(BigDecimal x, int scale) { return sqrt(new Real(x), scale); }
	
	public static Complex[] quadraticFormula(BigDecimal a, BigDecimal b, BigDecimal c) throws FunctionDomainException { return quadraticFormula(new Real(a), new Real(b), new Real(c)); }
	public static Complex[] quadraticFormula(BigDecimal a, BigDecimal b, BigDecimal c, int scale) throws FunctionDomainException { return quadraticFormula(new Real(a), new Real(b), new Real(c), scale); }
	
	/*** using double ***/
	
	public static Real add(double a, double b) { return add(new Real(a), new Real(b)); }
	
	public static Real subt(double minuend, double subtrahend) { return subt(new Real(minuend), new Real(subtrahend)); }
	
	public static Real mult(double a, double b) { return mult(new Real(a), new Real(b)); }
	
	public static Real div(double dividend, double divisor) throws FunctionDomainException { return div(new Real(dividend), new Real(divisor)); }
	public static Real div(double dividend, double divisor, int scale) throws FunctionDomainException { return div(new Real(dividend), new Real(divisor), scale); }
	
	public static Real mod(double dividend, double divisor) { return mod(new Real(dividend), new Real(divisor)); }
	
	public static Real pow(double base, double power) throws FunctionDomainException { return pow(new Real(base), new Real(power)); }
	public static Real pow(double base, double power, int scale) throws FunctionDomainException { return pow(new Real(base), new Real(power), scale); }
	
	public static Complex sqrt(double x) { return sqrt(new Real(x)); }
	public static Complex sqrt(double x, int scale) { return sqrt(new Real(x), scale); }
	
	public static Complex[] quadraticFormula(double a, double b, double c) throws FunctionDomainException { return quadraticFormula(new Real(a), new Real(b), new Real(c)); }
	public static Complex[] quadraticFormula(double a, double b, double c, int scale) throws FunctionDomainException { return quadraticFormula(new Real(a), new Real(b), new Real(c), scale); }
	
	/* Methods with Complex Numbers */
	
	/** Basic Operations **/
	
	public static Complex add(Complex z1, Complex z2) {
		return new Complex(z1.getRealPart().add(z2.getRealPart()), z1.getImaginaryPart().add(z2.getImaginaryPart()));
	}
	
	public static Complex subt(Complex z1, Complex z2) {
		return new Complex(z1.getRealPart().subt(z2.getRealPart()), z1.getImaginaryPart().subt(z2.getImaginaryPart()));
	}
	
	public static Complex mult(Complex z1, Complex z2) {
		Real a = z1.getRealPart(), b = z1.getImaginaryPart(), c = z2.getRealPart(), d = z2.getImaginaryPart();
		return new Complex(a.mult(c).subt(b.mult(d)), a.mult(d).add(c.mult(b)));
	}
	
	public static Complex div(Complex z1, Complex z2) throws FunctionDomainException { return div(z1, z2, Real.DEFAULT_SCALE); }
	
	public static Complex div(Complex z1, Complex z2, int scale) throws FunctionDomainException {
		Real a = z1.getRealPart(), b = z1.getImaginaryPart(), c = z2.getRealPart(), d = z2.getImaginaryPart();
		Real divisor = c.squared().add(d.squared());
		return new Complex(
				a.mult(c).add(b.mult(d)).div(divisor, scale), 
				b.mult(c).subt(a.mult(d)).div(divisor, scale)
				);
	}
	
	public static Complex pow(Complex base, Complex power) throws FunctionDomainException { return pow(base, power, Real.DEFAULT_SCALE); }
	
	// não 100% preciso, possivelmente por exp(lx(x)), mas até que preciso o suficiente
	public static Complex pow(Complex base, Complex power, int scale) throws FunctionDomainException {
		// 0^0, 0^-1 ... ? - base <= 0 - ln complexo... // ??
		if(base.isReal()) {
			try {
				return pow(base.toReal(), power, scale);
			} catch (ConversionException ex) { ex.printStackTrace(); return null; }
		}
		
		return base.abs().pow(power, scale).mult(Exponential.exp(new Complex(0, base.arg(scale)).mult(power), scale)).scaled(scale);
	}
	
	// sqrt, cbrt, nthRoot ...
	
	/* Methods with Real and Complex Numbers */
	
	public static Complex add(Real x, Complex z) { return add(x.toComplex(), z); }
	
	public static Complex subt(Real x, Complex z) { return subt(x.toComplex(), z); }
	
	public static Complex mult(Real x, Complex z) { return mult(x.toComplex(), z); }
	
	public static Complex div(Real x, Complex z) throws FunctionDomainException { return div(x.toComplex(), z); }
	public static Complex div(Real x, Complex z, int scale) throws FunctionDomainException { return div(x.toComplex(), z, scale); }
	
	// mod
	
	public static Complex pow(Real base, Complex power) throws FunctionDomainException { return pow(base, power, Real.DEFAULT_SCALE); }
	
	public static Complex pow(Real base, Complex power, int scale) throws FunctionDomainException {
		if(power.isReal()) {
			try {
				return pow(base, power.toReal(), scale).toComplex();
			} catch (ConversionException ex) { ex.printStackTrace(); return null; }
		}
		
		return base.pow(power.getRealPart(), scale).mult(Exponential.exp(new Complex(0, Exponential.ln(base, scale).mult(power.getImaginaryPart())), scale));
	}
	
	//bd, double?
	
}
