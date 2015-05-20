/**
 * 
 */
package br.com.psystems.crud.exception;

/**
 * @author rafael.saldanha
 *
 */
public class ConverterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4884572782304934693L;

	public ConverterException() {
		super();
	}

	public ConverterException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConverterException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConverterException(String message) {
		super(message);
	}

	public ConverterException(Throwable cause) {
		super(cause);
	}

}
