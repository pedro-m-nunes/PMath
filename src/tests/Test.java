package tests;
import java.math.BigDecimal;

import calculus.Calculus;
import exceptions.FunctionDomainException;
import numbers.Complex;
import numbers.PNumber;
import numbers.PNumber;
import numbers.Real;
import operations.Arithmetic;
import operations.Exponential;
import operations.Trigonometry;

public class Test {
	public static void main(String[] args) throws FunctionDomainException {
		//Real n = new Real(32);
		//Real m = new Real(4);
		
		/*System.out.println(Operations.sinh(n));
		System.out.println(Operations.cosh(n));
		System.out.println(Operations.tanh(n));*/
		
		/*System.out.println(new Complex(1, 0));
		System.out.println(new Complex(0, 0));
		System.out.println(new Complex(0, 1));
		System.out.println(new Complex(0, -1));
		System.out.println(new Complex(0, 2));
		System.out.println(new Complex(0, -2));
		System.out.println(new Complex(1, 1));
		System.out.println(new Complex(-1, -1));
		System.out.println(new Complex(-2, -2).conjugate());*/
		
		/*System.out.println(Arithmetic.add(n, m));
		System.out.println(Arithmetic.subt(n, m));
		System.out.println(Arithmetic.mult(n, m));
		System.out.println(Arithmetic.div(n, m));*/
		
		//System.out.println(Math.PI + " + " + Math.E);
		//System.out.println(Arithmetic.add(3.141592653589793, 2.718281828459045));
		
		//Real r = new Real("2");
		//Real w = new Real("3");
		
		//System.out.println(Arithmetic.pow(r.getValue(), w.getValue()));
				
		/*double a = 3, b = 0;
		System.out.println(Arithmetic.add(a, b));
		System.out.println(Arithmetic.subt(a, b));
		System.out.println(Arithmetic.mult(a, b));
		System.out.println(Arithmetic.div(a, b));*/
				
		//System.out.println(Trigonometry.cosh(w).squared().subt(Trigonometry.sinh(w).squared())); // devia ser 1
		
		//System.out.println(new Real(-2).isEven());
				
		//System.out.println(Arithmetic.pow(-2, 3));
		
		
		/*Real e = Exponential.exp(1);
		Real x = new Real("-0.36787944117144232159552377016146026"); // mínimo sem dar erro para productLog
		
		System.out.println(x);
		System.out.println(Exponential.productLog(x));*/
		
		/*Real n = new Real(25);
		
		System.out.println(Arithmetic.sqrt(n));
		System.out.println(Arithmetic.sqrt(n.negate()));*/
		
		//System.out.println(Trigonometry.tan(new Real(2)));
		//System.out.println(Trigonometry.cot(new Real(2)));
		
		//System.out.println(new Complex(12, 5).abs());
				
		/*double d = 1;
		System.out.println(Arithmetic.doubleFactorial(2 * d - 1).div(Arithmetic.doubleFactorial(2 * d)));
		System.out.println(Arithmetic.factorial(2 * d).div(Arithmetic.mult(Arithmetic.pow(2, d), Arithmetic.factorial(d)).squared()));
		
		Real t = new Real(1);
		System.out.printf("arcsin(%s) = %s\n", t, Trigonometry.arcsin(t));*/
		
		//System.out.println(Exponential.exp(1));
		/*Real a = new Real(1), b = new Real(0), c = new Real(1);
		Complex[] res = Arithmetic.quadraticFormula(a, b, c);
		for(Complex z : res) System.out.println(z);*/
		
		//System.out.println(new Real(1).div(Calculus.INFINITY));
		
		Complex z1 = new Complex(0, 1), z2 = new Complex(1, 0);
		System.out.println(z1.equals(z2));
		
		// JUnit...
		
		System.out.println("2.8332133440562160802495346178731265355882030125857447872972377378...");
		System.out.println(Exponential.ln(new Real(17)));
		System.out.println("0.6931471805599453094172321214581765680755001343602552541206800094...");
		System.out.println(Exponential.ln(new Real(2)));
		
		System.out.println(PNumber.e(0));
		System.out.println(PNumber.pi(0));
		
		//System.out.println(new Real(-1.1).frac().add(new Real(-1.1).ceil()));
		
		//System.out.println(PNumber.phi());
		//System.out.println(Arithmetic.add(new Complex(1, 0), Arithmetic.sqrt(5, PNumber.SUPER_PRECISION_SCALE)).div(new Complex(2, 0), PNumber.SUPER_PRECISION_SCALE));
		
		System.out.println(Exponential.exp(new Complex(0, PNumber.pi())));
		
		System.out.println(new Real("150000000000000000").toStringE());
		System.out.println(new Real("150000000000000000").toString10());
		
		//System.out.println(Exponential.ln(PNumber.e()));
		//System.out.println(Exponential.ln(PNumber.e(39), 39).scaled(36)); //?
		/*
		System.out.println("0.8813735870195430252326093249797923090281603282616354107532956086...");
		System.out.println(Trigonometry.arcsinh(new Real(1)));
		
		System.out.println("1.3169578969248167086250463473079684440269819714675164797684722569...");
		System.out.println(Trigonometry.arccosh(new Real(2)));
		
		System.out.println("0.5493061443340548456976226184612628523237452789113747258673471668...");
		System.out.println(Trigonometry.arctanh(new Real(0.5)));
		*/
		// arcsech, arccsch arccoth
		//System.out.println(Trigonometry.arcsech(new Real(0.5)));
		//System.out.println(Trigonometry.arccsch(new Real(2)));
		//System.out.println(Trigonometry.arccoth(new Real(2)));
		
		//System.out.println(Exponential.log(new Real(3), new Real(9)));
		
		// testar log, log10!
		
		System.out.println(PNumber.i().squared());
		
		System.out.println(Exponential.exp(-1, 100).negate());
	}
}
