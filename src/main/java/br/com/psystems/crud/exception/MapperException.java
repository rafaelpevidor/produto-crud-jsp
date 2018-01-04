/**
 * 
 */
package br.com.psystems.crud.exception;

/**
 * @author rafael.saldanha
 *
 */
public class MapperException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4884572782304934693L;

	public MapperException() {
		super("Desculpe, houve um erro na conversão dos dados.");
	}

	public MapperException(String message, Throwable cause) {
		super(message, cause);
	}
}
