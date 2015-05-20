/**
 * 
 */
package br.com.psystems.crud.bind;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.exception.ConverterException;
import br.com.psystems.crud.model.domain.BaseDomain;

/**
 * @author rafael.saldanha
 *
 */
public interface IBind <T extends BaseDomain> {
	
	public static final String BIND_ERROR_MESSAGE = "Desculpe, houve um erro ao aplicar valores do formulário.";

	public T bind(HttpServletRequest request) throws ConverterException;
}
