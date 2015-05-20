/**
 * 
 */
package br.com.psystems.crud.exception;

/**
 * @author rafael.saldanha
 *
 */
public class PersistenceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersistenceException() {
		super();
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(Throwable cause) {
		super(cause);
	}
}
