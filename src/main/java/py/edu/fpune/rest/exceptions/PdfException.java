package py.edu.fpune.rest.exceptions;

public class PdfException extends RuntimeException{

	private static final String DESCRIPTION = "Pdf. file exception";
	
	public PdfException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
