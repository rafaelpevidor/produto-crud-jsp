/**
 * 
 */
package br.com.psystems.crud.infra.exception;

/**
 * @author rafael.saldanha
 *
 */
public class BindException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4884572782304934693L;

	public BindException() {
		super("Desculpe, houve um erro na conversão dos dados.");
	}

	public BindException(String message, Throwable cause) {
		super(message, cause);
	}
}
