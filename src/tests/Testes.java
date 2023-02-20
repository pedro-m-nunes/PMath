package tests;

import java.math.RoundingMode;

import cps.*;
import exceptions.*;
import numbers.*;
import operations.*;

public class Testes {
	public static void main(String[] args) throws FunctionDomainException {
		//System.out.println(Constants.pi(36).div(2) + "\n");
		
		//System.out.println("-0.0996686524911620273784461198780205902432783225043146480155087768...");
		//System.out.println(Trigonometry.arctan(new Real(-0.1), 36));
		
		//System.out.println(Constants.pi());
		//System.out.println(Trigonometry.arctan(new Real(1), 1037).mult(4));
		
		
		//System.out.println(new Real(0).equals(new Complex(0, 1)));
		
		//System.out.println(Combinatorics.binomialCoefficient(new Real(3), new Real(3), 36));
		//System.out.println(": " + Combinatorics.pochhammer(new Real(2), new Real(2)));
		
		// https://mathworld.wolfram.com/ForwardDifference.html // delta
		
		
		
		
		//System.out.println(Arithmetic.pow(new Real(2), new Complex(1, 1)));
		
		//System.out.println(Arithmetic.pow(new Complex(0, 1), new Complex(3, 0))); // ????
		
		//System.out.println(Arithmetic.pow(-1, 2));
		
		/*System.out.println(CalcConstant.INF_NEG.getClass() == CalcConstant.class);
		
		// POSITIVE_INFINITY
		Real infinity = new Real(Double.MAX_VALUE); //Arithmetic.pow(10, 9999, 9999).subt(1);
		//System.out.println(infinity);
		
		Real zero = new Real(Double.MIN_VALUE); //Arithmetic.pow(10, -9997, 9999);
		//System.out.println(zero);
		
		System.out.println(Exponential.exp(infinity.negate())); // ??
		
		System.out.println(Math.atan(Double.POSITIVE_INFINITY));*/
		
		
		
		
//		System.out.println(autoScale(Exponential.productLog(new Real(-1).div(PNumber.e()))));
//		System.out.println(autoScale(Arithmetic.pow(new Complex(0, 1), new Complex(3, 0))));
				
//		Real a = Trigonometry.sin(PNumber.pi().div(2)); //Arithmetic.pow(8, 1/3.0); //Arithmetic.pow(new Complex(0, 1), new Complex(3, 0)); //
//		System.out.println(a);
//		System.out.println(a.autoScale());
		
		
		
		//System.out.println(Arithmetic.pow(-1, 0.5));
		
		
		Complex A = new Complex(-3, -4); // ln(A) = 1.609... - 2.214...i
		System.out.println(Exponential.ln(A.abs()).add(A.arg().mult(PNumber.i()))); // arrumar arg
		
		System.out.println(A.arg()); // -2.214... rad
		
	}
}
