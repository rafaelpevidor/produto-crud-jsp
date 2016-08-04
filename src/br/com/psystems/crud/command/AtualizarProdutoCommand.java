/**
 * 
 */
package br.com.psystems.crud.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.base.AbstractCommand;
import br.com.psystems.crud.base.BaseBind;
import br.com.psystems.crud.base.BaseDAO;
import br.com.psystems.crud.bind.ProdutoBind;
import br.com.psystems.crud.dao.ProdutoDAO;
import br.com.psystems.crud.exception.ConverterException;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.model.Produto;

/**
 * @author rafael.saldanha
 *
 */
public class AtualizarProdutoCommand extends AbstractCommand {

	public AtualizarProdutoCommand(ProdutoDAO dao, ProdutoBind bind) {
		this.dao = dao;
		this.bind = bind;
	}

	private static final long serialVersionUID = 8169250379905812667L;
	private static Logger logger = Logger.getLogger(AtualizarProdutoCommand.class);
	
	private String pagina = PAGE_PRODUTO_LISTA;
	private BaseDAO<Produto> dao;
	private BaseBind<Produto> bind;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			Produto produto = bind.buildEntity(request);
			dao.update(produto);
			addSuccessMessage(request, UPDATE_SUCCESS_MESSAGE);
			
		} catch (DAOException |ConverterException e) {
			logger.error(e.getMessage());
			pagina = PAGE_PRODUTO_FORMULARIO;
			setException(request, e);
		}
		return pagina;
	}

}
