package py.edu.fpune.rest.exceptions;

public class MailException extends ConflictException{

	private static final String DESCRIPTION = "Mail con algun problema";
	
	public MailException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
