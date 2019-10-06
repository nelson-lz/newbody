package py.edu.fpune.rest.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NotFoundException.class })
	@ResponseBody
	public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ BadRequestException.class, DuplicateKeyException.class,
						HttpRequestMethodNotSupportedException.class, MethodArgumentNotValidException.class,
						MissingRequestHeaderException.class, MissingServletRequestParameterException.class,
						MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class })
	@ResponseBody
	public ErrorMessage badRequest(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ ForbiddenException.class })
	@ResponseBody
	public ErrorMessage forbiddenRequest(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ ConflictException.class })
	@ResponseBody
	public ErrorMessage conflict(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ UnauthorizedException.class, AccessDeniedException.class })
	public void unauthorized(Exception exception) {
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public ErrorMessage fatalErrorUnexpectedException(HttpServletRequest request, Exception exception) {
		exception.printStackTrace();
		return new ErrorMessage(exception, request.getRequestURI());
	}
}
