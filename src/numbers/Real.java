package numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import exceptions.FunctionDomainException;
import operations.Arithmetic;

public class Real /*extends PNumber*/ implements Comparable<Real> { // Comparator?
	
	/* Attributes */
	
	private BigDecimal value;
	
	/* Static Attributes */
	
	public static final int REGULAR_SCALE = 10, SIMPLE_SCALE = 2, PRECISION_SCALE = 36;
	public static final int CONSTANT_SCALE = 1037; // 1037 seems enough. Use of Real.scaled() recommended.
	public static final int DEFAULT_SCALE = PRECISION_SCALE;
	
	public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP; // Note: HALF_UP isn't 100% precise, but it's really close to it. It's crucial to the loop in the exp function and other "infinite" series.
		
	/* Constructors */
	
	public Real() { this.value = BigDecimal.valueOf(0); }
	
	public Real(Real number) { this.value = number.getValue(); } // ?
	
	public Real(BigDecimal value) { this.value = value; }
	
	public Real(String value) { this.value = new BigDecimal(value); }
	
	// Double.POSITIVE_INFINITY ... ?
	public Real(double value) { this.value = BigDecimal.valueOf(value); } // Note: double is imprecise with some values.
	
	/* Getters & Setters */
	
	public BigDecimal getValue() { return this.value; }
	
	public double getDouble() { return this.getValue().doubleValue(); }
	
	public void setValue(BigDecimal value) { this.value = value; }
	
	public void setValue(String value) { this.value = new BigDecimal(value); }
	
	@Deprecated // usar?
	public void setValue(double value) { this.value = BigDecimal.valueOf(value); } // Note: double is imprecise with some values.
	
	@Override
	public String toString() { return this.getValue().stripTrailingZeros().toPlainString(); }
	// if(INFINITY) ...
	
	public String toStringE() { return this.getValue().stripTrailingZeros().toString(); }
	
	public String toString10() { return toStringE().replace("E", "*10^(").concat(")"); } //?
	// cientific notation // E >|< 0 ?
	
	/* Static Methods */ // scale, abs, negate, sign, floor, ceil, frac ? - autoScale?
	
	// valueOf(Real)? - pode ser útil para aceitar um número double como Real mais facilmente
	// 'new Real[] {3, 2, 1}' seria possível?
	
	public static Real valueOf(BigDecimal number) { return new Real(number); }
	
	// valueOf(String)?
	
	public static Real valueOf(double number) { return new Real(number); }
	
	/* Methods */
	
	public Complex toComplex() { return Complex.valueOf(this); }
	
	// ??
	public Real autoScale() { return autoScale(Real.DEFAULT_SCALE); }
	
	// ??
	public Real autoScale(int scale) {
		if(PNumber.automaticallyScale) {
			int cases = (int) (scale * 0.44);
			
			String str = "x";
			for(int n = 1; n < cases; n++) str += "x";
			
			if(this.toString().contains(str.replace('x', '0')))
				return this.scaled(str.indexOf("0") + cases, RoundingMode.HALF_UP);
			else if(this.toString().contains(str.replace('x', '9')))
				return this.scaled(str.indexOf("9") + cases, RoundingMode.HALF_UP);
		}
		return this;
	}
	
	public Real scaled() { return scaled(DEFAULT_SCALE, ROUNDING_MODE); }
	
	public Real scaled(int decimalCases) { return scaled(decimalCases, ROUNDING_MODE); }
	
	public Real scaled(RoundingMode roundingMode) { return scaled(DEFAULT_SCALE, roundingMode); }
	
	public Real scaled(int decimalCases, RoundingMode roundingMode) { return new Real(this.getValue().setScale(decimalCases, roundingMode)); }
	
	public boolean equals(Real number) { return this.compareTo(number) == 0; }
	
	public boolean equals(Complex number) { return number.equals(this); }
	
	@Override
	public int compareTo(Real number) { return this.getValue().compareTo(number.getValue()); }
	
	public int compareTo(BigDecimal number) { return this.getValue().compareTo(number); }
	
	public int compareTo(double number) { return this.getValue().compareTo(BigDecimal.valueOf(number)); }
	
	public boolean isNegative() { return this.sign() < 0; }
	
	// isPositive()?
	
	public boolean isInteger() { return this.getDouble() == (int) this.getDouble(); }

	public boolean isNatural() { return this.isInteger() && !this.isNegative(); }
	
	public boolean isEven() { return this.getDouble() % 2 == 0; }
	
	// isOdd?
	
	// isPrime ? //static?
	
	public Real abs() { //static?
		if(this.getDouble() >= 0) return new Real(this);
		else return new Real(this.negate());
	}
	
	public Real negate() { return new Real(this.mult(-1)); } //static?
	
	public byte sign() { //static? // sng(x)?
		if(this.getDouble() > 0) return 1;
		else if(this.getDouble() < 0) return -1;
		else return 0;
	}
	
	// floor, ceiling, fractional part - static?
	// https://en.wikipedia.org/wiki/Floor_and_ceiling_functions#Relations_among_the_functions
	public Real floor() { return new Real(this.getValue().setScale(0, RoundingMode.FLOOR)); }
	
	public Real ceil() { return new Real(this.getValue().setScale(0, RoundingMode.CEILING)); }
	
	public Real frac() { //static?
		if(this.isNegative()) return this.subt(this.ceil());
		else return this.subt(this.floor());
	} // definição ambígua para números negativos (wolfram usa essa)
	
	/** Basic Operations **/
	
	/*** using Real ***/
	
	public Real add(Real number) { return Arithmetic.add(this, number); }
	
	public Real subt(Real subtrahend) { return Arithmetic.subt(this, subtrahend); }
	
	public Real mult(Real number) { return Arithmetic.mult(this, number); }
	
	public Real div(Real divisor) throws FunctionDomainException { return Arithmetic.div(this, divisor); }
	public Real div(Real divisor, int scale) throws FunctionDomainException { return Arithmetic.div(this, divisor, scale); }
	
	public Real mod(Real divisor) { return Arithmetic.mod(this, divisor); }
	
	public Real pow(Real power) throws FunctionDomainException { return Arithmetic.pow(this, power); }
	public Real pow(Real power, int scale) throws FunctionDomainException { return Arithmetic.pow(this, power, scale); }
	
	// tetration
	
	public Complex sqrt() { return Arithmetic.sqrt(this); } // retornar complex?
	public Complex sqrt(int scale) { return Arithmetic.sqrt(this, scale); }
	
	// cbrt, nrt
	
	/*** using BigDecimal ***/ // new Real(BigDecimal)?
	
	public Real add(BigDecimal number) { return Arithmetic.add(this.getValue(), number); }
	
	public Real subt(BigDecimal subtrahend) { return Arithmetic.subt(this.getValue(), subtrahend); }
	
	public Real mult(BigDecimal number) { return Arithmetic.mult(this.getValue(), number); }
	
	public Real div(BigDecimal divisor) { return Arithmetic.div(this.getValue(), divisor); }
	public Real div(BigDecimal divisor, int scale) { return Arithmetic.div(this.getValue(), divisor, scale); }
	
	public Real mod(BigDecimal divisor) { return Arithmetic.mod(this.getValue(), divisor); }
	
	public Real pow(BigDecimal power) throws FunctionDomainException { return this.pow(new Real(power)); }
	// pow scale
	
	/*** using double ***/
	
	public Real add(double number) { return Arithmetic.add(this, new Real(number)); }
	
	public Real subt(double subtrahend) { return Arithmetic.subt(this, new Real(subtrahend)); }
	
	public Real mult(double number) { return Arithmetic.mult(this, new Real(number)); }
	
	public Real div(double divisor) throws FunctionDomainException { return Arithmetic.div(this, new Real(divisor)); }
	public Real div(double divisor, int scale) throws FunctionDomainException { return Arithmetic.div(this, new Real(divisor), scale); }
	
	public Real mod(double divisor) { return Arithmetic.mod(this, new Real(divisor)); }
	
	public Real pow(double power) throws FunctionDomainException { return this.pow(new Real(power)); }
	public Real pow(double power, int scale) throws FunctionDomainException { return this.pow(new Real(power), scale); }
	
	/*** using Complex ***/
	
	public Complex add(Complex z) { return Arithmetic.add(this, z); }
	
	public Complex subt(Complex z) { return Arithmetic.subt(this, z); }
	
	public Complex mult(Complex z) { return Arithmetic.mult(this, z); }
	
	public Complex div(Complex z) throws FunctionDomainException { return Arithmetic.div(this, z); }
	public Complex div(Complex z, int scale) throws FunctionDomainException { return Arithmetic.div(this, z, scale); }
	
	public Complex pow(Complex power) throws FunctionDomainException { return Arithmetic.pow(this, power); }
	public Complex pow(Complex power, int scale) throws FunctionDomainException { return Arithmetic.pow(this, power, scale); }
	
	// ...
	
	/** Other Operations **/
	
	public Real squared() { return this.mult(this); }
	
	public Real cubed() { return this.squared().mult(this); }
	
}
