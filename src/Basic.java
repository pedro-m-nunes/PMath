
public class Basic {
	public static double quadraticFormulaPos(double a, double b, double c) {
		return (- b + Math.sqrt(b * b - 4 * a * c)) / 2.0;
	}
	
	public static double quadraticFormulaNeg(double a, double b, double c) {
		return (- b - Math.sqrt(b * b - 4 * a * c)) / 2.0;
	}
	
	//cubicFormula(a, b, c)
	
	public static double sinh(double x) {
		return (Math.exp(x) - Math.exp(-x)) / 2.0;
	}
	
	public static double cosh(double x) {
		return (Math.exp(x) + Math.exp(-x)) / 2.0;
	}
	
	//public static tanh(double x) {}
	
	// Geometry
	
	// points, distances, lines ...
	
	// Trigonometry
	// sin, cos, sinh, cosh, tan ...
	
	// Combinatorics
	
	// Linear Algebra
	// matrices, vectors ...
}
