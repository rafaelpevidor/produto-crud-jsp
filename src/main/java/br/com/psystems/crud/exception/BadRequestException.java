/**
 * 
 */
package br.com.psystems.crud.exception;

import javax.servlet.ServletException;

/**
 * @author developer
 *
 */
@SuppressWarnings("serial")
public class BadRequestException extends ServletException {

	public BadRequestException() {
		super();
	}

	public BadRequestException(String message, Throwable rootCause) {
		super(message, rootCause);
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(Throwable rootCause) {
		super(rootCause);
	}

}
