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
		super("Desculpe, houve um erro na conversão dos dados.");
	}

	public ConverterException(String message, Throwable cause) {
		super(message, cause);
	}
}
