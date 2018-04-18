/**
 * 
 */
package br.com.psystems.crud.exception;

/**
 * @author developer
 *
 */
public class BusinessException extends AbstractException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -222749582058523225L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}
	
}
