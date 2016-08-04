/**
 * 
 */
package br.com.psystems.crud.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.base.AbstractCommand;
import br.com.psystems.crud.base.BaseDAO;
import br.com.psystems.crud.dao.ProdutoDAO;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.model.Produto;

/**
 * @author rafael.saldanha
 *
 */
public class BuscarProdutoCommand extends AbstractCommand {

	public BuscarProdutoCommand(ProdutoDAO dao) {
		super();
		this.dao = dao;
	}

	private static final long serialVersionUID = 7606485709102366372L;
	private static Logger logger = Logger.getLogger(BuscarProdutoCommand.class);
	
	private String pagina = PAGE_PRODUTO_FORMULARIO;
	private BaseDAO<Produto> dao;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Produto produto = dao.findById(getID(request));
			setEntity(request, produto);
			
		} catch (DAOException | ServletException e) {
			logger.error(e.getMessage());
			pagina = PAGE_PRODUTO_LISTA;
			setException(request, e);
		}
		return pagina;
	}

}
