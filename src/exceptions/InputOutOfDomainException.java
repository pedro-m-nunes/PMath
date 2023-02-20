package exceptions;

public class InputOutOfDomainException extends Exception { // FunctionDomainException?
	
	/* Attributes */
	
	private static final long serialVersionUID = 1L;

	/* Constructor */
	
	public InputOutOfDomainException() { super(); }
	
	public InputOutOfDomainException(String msg) { super(msg); }
	
}
