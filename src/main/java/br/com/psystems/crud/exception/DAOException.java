/**
 * 
 */
package br.com.psystems.crud.exception;

/**
 * @author rafael.saldanha
 *
 */
public class DAOException extends AbstractException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3441673626052050335L;

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}
}
