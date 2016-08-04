/**
 * 
 */
package br.com.psystems.crud.bind;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.base.BaseBind;
import br.com.psystems.crud.exception.ConverterException;
import br.com.psystems.crud.model.Fornecedor;
import br.com.psystems.crud.util.ConverterUtils;

/**
 * @author rafael.saldanha
 *
 */
public final class FornecedorBind implements BaseBind<Fornecedor> {
	
	protected FornecedorBind() {}

	@Override
	public Fornecedor buildEntity(HttpServletRequest request) throws ConverterException {
		
		Fornecedor fornecedor = new Fornecedor();
		try {
			fornecedor.setDescricao((String) request.getParameter("descricao"));
			fornecedor.setNome((String) request.getParameter("nome"));
			fornecedor.setId(ConverterUtils.convertStringToLong((String) request.getParameter("id")));
		} catch (Exception e) {
			throw new ConverterException(BaseBind.BIND_ERROR_MESSAGE, e);
		}
		return fornecedor;
	}
}
