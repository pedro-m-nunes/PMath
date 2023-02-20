package numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import exceptions.ConversionException;
import exceptions.FunctionDomainException;
import operations.Arithmetic;
import operations.Trigonometry;

public class Complex /*extends PNumber*/ {
	
	/* Attributes */
	
	private Real realPart;
	private Real imaginaryPart;
	
	public static final char IMAGINARY_UNIT_SYMBOL = 'i';
	
	/* Constructors */
	
	public Complex() {
		this.realPart = new Real();
		this.imaginaryPart = new Real();
	}
	
	public Complex(Real realPart, Real imaginaryPart) {
		this.realPart = realPart;
		this.imaginaryPart = imaginaryPart;
	}
	
	public Complex(BigDecimal realPart, BigDecimal imaginaryPart) {
		this.realPart = new Real(realPart);
		this.imaginaryPart = new Real(imaginaryPart);
	}
	
	public Complex(String realPart, String imaginaryPart) {
		this.realPart = new Real(realPart);
		this.imaginaryPart = new Real(imaginaryPart);
	}
	
	public Complex(double realPart, double imaginaryPart) {
		this.realPart = new Real(realPart);
		this.imaginaryPart = new Real(imaginaryPart);
	}
	
	/** Purely Real Constructors **/
	
	public Complex(Real realPart, double imaginaryPart) {
		this.realPart = realPart;
		this.imaginaryPart = new Real(imaginaryPart);
	}
	
	public Complex(BigDecimal realPart, double imaginaryPart) {
		this.realPart = new Real(realPart);
		this.imaginaryPart = new Real(imaginaryPart);
	}
	
	public Complex(String realPart, double imaginaryPart) {
		this.realPart = new Real(realPart);
		this.imaginaryPart = new Real(imaginaryPart);
	}
	
	/** Purely Imaginary Constructors **/
	
	public Complex(double realPart, Real imaginaryPart) {
		this.realPart = new Real(realPart);
		this.imaginaryPart = imaginaryPart;
	}
	
	public Complex(double realPart, BigDecimal imaginaryPart) {
		this.realPart = new Real(realPart);
		this.imaginaryPart = new Real(imaginaryPart);
	}
	
	public Complex(double realPart, String imaginaryPart) {
		this.realPart = new Real(realPart);
		this.imaginaryPart = new Real(imaginaryPart);
	}
	
	/* Getters & Setters */
	
	public Real getRealPart() { return this.realPart; }
	
	public Real getImaginaryPart() { return this.imaginaryPart; }
	
	public void setRealPart(Real realPart) { this.realPart = realPart; }
	
	public void setImaginaryPart(Real imaginaryPart) { this.imaginaryPart = imaginaryPart; }
	
	// set(BigDecimal|String?|double)
	
	// set(re, im)
	
	@Override
	public String toString() {
		double re = this.getRealPart().getDouble(), im = this.getImaginaryPart().getDouble();
		
		if(this.isReal()) return String.format("%s", this.getRealPart().toString()); // Purely real
		
		if(re == 0 && im != 0) { // Purely imaginary
			if(im == -1) return String.format("-%c", IMAGINARY_UNIT_SYMBOL);
			return String.format("%s%c", im == 1 ? "" : this.getImaginaryPart().toString(), IMAGINARY_UNIT_SYMBOL);
		}
		
		String realStr = this.getRealPart().toString();
		char sign = im < 0 ? '-' : '+';
		String imagStr = this.getImaginaryPart().abs().getDouble() == 1 ? "" : this.getImaginaryPart().abs().toString();
		
		return String.format("%s%c%s%c", realStr, sign, imagStr, IMAGINARY_UNIT_SYMBOL);
	}
	
	// toStringExp() ? - degrees...
	
	/* Static Methods */ // conjugate, abs, angle/arg? - autoScale?
	
	public static Complex valueOf(Real number) { return new Complex(number, 0); }
	
	/* Methods */
	
	public Real toReal() throws ConversionException {
		if(this.isReal()) return new Real(this.getRealPart());
		else throw new ConversionException("Number is not real.");
	}
	
	public boolean isReal() { return this.getImaginaryPart().getDouble() == 0; }
	
	// ??
	public Complex autoScale() { return autoScale(Real.DEFAULT_SCALE); }
	
	// ??
	public Complex autoScale(int scale) {
		return new Complex(this.getRealPart().autoScale(scale), this.getImaginaryPart().autoScale(scale));
	}
	
	public Complex scaled() { return new Complex(this.getRealPart().scaled(), this.getImaginaryPart().scaled()); }
	
	public Complex scaled(int decimalCases) { return new Complex(this.getRealPart().scaled(decimalCases), this.getImaginaryPart().scaled(decimalCases)); }
	
	public Complex scaled(RoundingMode roundingMode) { return new Complex(this.getRealPart().scaled(roundingMode), this.getImaginaryPart().scaled(roundingMode)); }
	
	public Complex scaled(int decimalCases, RoundingMode roundingMode) { return new Complex(this.getRealPart().scaled(decimalCases, roundingMode), this.getImaginaryPart().scaled(decimalCases, roundingMode)); }
	
	public boolean equals(Complex number) {
		return this.getRealPart().equals(number.getRealPart()) && this.getImaginaryPart().equals(number.getImaginaryPart());
	}
	
	public boolean equals(Real number) { return equals(valueOf(number)); }
	
	// static?
	public Complex conjugate() { return new Complex(this.getRealPart(), this.getImaginaryPart().negate()); }
	
	public Real abs() { // static? // scale?
		try {
			return this.getRealPart().squared().add(this.getImaginaryPart().squared()).sqrt().toReal();
		} catch(Exception ex) { ex.printStackTrace(); return null; }
	}
	
	//public Real angle() // argument/arg? // arctan(b/a) // static?
	// arg(n) <=> 2 * pi * n
	public Real arg() { return arg(Real.DEFAULT_SCALE); }
	
	public Real arg(int scale) { // arg(-1) = pi // algo errado? atan2?
		try {
			return Trigonometry.arctan(this.getImaginaryPart().div(this.getRealPart(), scale), scale);
		} catch(FunctionDomainException ex) { // 1 / 0 = Inf
			return new Real(
					"1.5707963267948966192313216916397514420985846996875529104874722961539082031431044993140174126710585339910740432566411533235469223047752911158626797040642405587251420513509692605527798223114744774651909822144054878329667230642378241168933915826356009545728242834617301743052271633241066968036301245706368622935033031577940874407604604814146270458576821839462951800056652652744102332606920734759707558047165286351828797959765460930586909663058965525592740372311899813747836759428763624456139690915059745649168366812203283215430106974731976123685953510899304718513852696085881465883761923374092338347025660002840635726317804138928856713788948045868185893607342204506124767150732747926855253961398446294617710099780560645109804320172090799068148873856549802593536056749999991864890249755298658664080481592975122297276734541513212611541266723425176309655940855050015689193764432937666041907103085888345736517991267452143777343655797814319411768937968759788909288902660856134033065009639383055979546082100994690476286005327429316394329680766909139841151509760176509264844978868112997069456248608876417395657577874286212270753479754147665584308639279445375491908773187324696596275302004638508355695049244120064291808017818538300523550909714777980994733839187247241276898873634235520237673231040233421295347456466568385144945760523760810284830120290190750967556266912150177938201237482366319570996363021349613983911773908180046708608206099622931575151430914872778533749192527472942934634978454636053987546514776605826724936013779801182403327495599409173988767831849037132712639312759092087873364454888863969000408235300080726245960866086073861750707209867842740806805786762760667378709247342192616619536970716672738812084312594917847427810496096110921362751271284438358952473008267334024943136163958930428921919139839883407270504769418931804753400321125626025586964924480420642443134728021209826425111053305931533721393110195974725235618568934804781821859586437338823287869812069454329163229979066952390137950497328820394756347341991762978549129113102612447038633597391342413007384954513"
					).scaled(scale).mult(this.getImaginaryPart().sign());
		}
	}
	
	/** Basic Operations **/
	
	public Complex add(Complex z) { return Arithmetic.add(this, z); }
	
	public Complex subt(Complex z) { return Arithmetic.subt(this, z); }
	
	public Complex mult(Complex z) { return Arithmetic.mult(this, z); }
	
	public Complex div(Complex z) throws FunctionDomainException { return Arithmetic.div(this, z); }
	public Complex div(Complex z, int scale) throws FunctionDomainException { return Arithmetic.div(this, z, scale); }
	
	// op(Real)!!!!!!!!!!!!!!! - double, BD - é útil
	
	public Complex pow(Complex power) throws FunctionDomainException { return Arithmetic.pow(this, power); }
	public Complex pow(Complex power, int scale) throws FunctionDomainException { return Arithmetic.pow(this, power, scale); }
	
	// sqrt, cbrt, nthRoot ...
	
	/** Other Operations **/
	
	public Complex squared() { return this.mult(this); }
	
	public Complex cubed() { return this.squared().mult(this); }
	
	// Riemann Zeta Function - pow(C)
}
