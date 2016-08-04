/**
 * 
 */
package br.com.psystems.crud.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.base.AbstractCommand;
import br.com.psystems.crud.base.BaseDAO;
import br.com.psystems.crud.dao.ProdutoDAO;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.model.Produto;

/**
 * @author Rafael.Saldanha
 *
 */
public class ListaProdutoCommand extends AbstractCommand {

	public ListaProdutoCommand(ProdutoDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = -7721458512554274161L;
	private static Logger logger = Logger.getLogger(ListaProdutoCommand.class);
	
	private String pagina = PAGE_PRODUTO_LISTA;
	private BaseDAO<Produto> dao;
	
	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Command#executar(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Produto> produtos = dao.getAll();
			setProdutos(request, produtos);
			
		} catch (DAOException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return pagina;
	}

}
