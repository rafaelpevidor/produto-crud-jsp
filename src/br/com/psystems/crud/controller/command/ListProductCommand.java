/**
 * 
 */
package br.com.psystems.crud.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.dao.ProductDAO;

/**
 * @author Rafael.Saldanha
 *
 */
public class ListProductCommand extends AbstractCommand {

	public ListProductCommand(ProductDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = -7721458512554274161L;
	private static Logger logger = Logger.getLogger(ListProductCommand.class);
	
	private String pagina = PAGE_PRODUTO_LISTA;
	private ProductDAO dao;
	
	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Command#executar(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Product> produtos = dao.getAll();
			setProdutos(request, produtos);
			
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return pagina;
	}

}
