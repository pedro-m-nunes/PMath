package exceptions;

public class ConversionException extends Exception {
	
	/* Attributes */
	
	private static final long serialVersionUID = 1L;

	/* Constructor */
	
	public ConversionException() { super(); }
	
	public ConversionException(String msg) { super(msg); }
	
}
