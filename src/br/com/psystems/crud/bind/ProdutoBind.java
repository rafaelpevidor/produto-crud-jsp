/**
 * 
 */
package br.com.psystems.crud.bind;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.base.BaseBind;
import br.com.psystems.crud.exception.ConverterException;
import br.com.psystems.crud.model.Produto;
import br.com.psystems.crud.util.ConverterUtils;

/**
 * @author rafael.saldanha
 *
 */
public final class ProdutoBind implements BaseBind<Produto> {
	
	protected ProdutoBind() {}

	@Override
	public Produto buildEntity(HttpServletRequest request) throws ConverterException {
		
		Produto produto = new Produto();
		try {
			produto.setDescricao(request.getParameter("descricao"));
			produto.setLote(request.getParameter("lote"));
			produto.setNome(request.getParameter("nome"));
			produto.setFornecedorId(ConverterUtils.convertStringToLong((String) request.getParameter("fornecedorid")));
			produto.setQuantidade(ConverterUtils.convertStringToDouble((String) request.getParameter("quantidade")));
			produto.setValorDeCompra(ConverterUtils.convertStringToDouble((String) request.getParameter("valorcompra")));
			produto.setValorDeVenda(ConverterUtils.convertStringToDouble((String) request.getParameter("valorvenda")));
			produto.setCor(request.getParameter("cor"));
			produto.setReferencia(ConverterUtils.convertStringToInteger((String) request.getParameter("referencia")));
			produto.setId(ConverterUtils.convertStringToLong((String) request.getParameter("id")));
		} catch (Exception e) {
			throw new ConverterException(BaseBind.BIND_ERROR_MESSAGE, e);
		}
		return produto;
	}
}
