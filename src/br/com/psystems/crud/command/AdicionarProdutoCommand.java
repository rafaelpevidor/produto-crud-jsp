/**
 * 
 */
package br.com.psystems.crud.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.base.AbstractCommand;
import br.com.psystems.crud.base.BaseBind;
import br.com.psystems.crud.base.BaseDAO;
import br.com.psystems.crud.exception.ConverterException;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.model.Produto;

/**
 * @author rafael.saldanha
 *
 */
public class AdicionarProdutoCommand extends AbstractCommand {

	public AdicionarProdutoCommand(BaseDAO<Produto> dao, BaseBind<Produto> bind) {
		this.dao = dao;
		this.bind = bind;
	}

	private static final long serialVersionUID = -9136913987730291078L;
	private static Logger logger = Logger.getLogger(AdicionarProdutoCommand.class);
	
	private String pagina = PAGE_PRODUTO_LISTA;
	private BaseDAO<Produto> dao;
	private BaseBind<Produto> bind;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Produto produto = bind.buildEntity(request);
			dao.save(produto);
			addInfoMessage(request, ADD_SUCCESS_MESSAGE);
			
		} catch (ConverterException | DAOException e) {
			logger.error(e.getMessage(), e);
			pagina = PAGE_PRODUTO_FORMULARIO;
			setException(request, e);
		}
		return pagina;
	}

}
