/**
 * 
 */
package br.com.psystems.crud.base;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.exception.ConverterException;

/**
 * @author rafael.saldanha
 *
 */
public interface BaseBind <T extends BaseEntity> {
	
	public static final String BIND_ERROR_MESSAGE = "Desculpe, houve um erro ao aplicar valores do formulário.";

	public T buildEntity(HttpServletRequest request) throws ConverterException;
}
