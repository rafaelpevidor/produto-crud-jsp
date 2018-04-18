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
public class NotFoundException extends ServletException {

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message, Throwable rootCause) {
		super(message, rootCause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable rootCause) {
		super(rootCause);
	}

}
