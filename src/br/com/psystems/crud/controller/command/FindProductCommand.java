/**
 * 
 */
package br.com.psystems.crud.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.dao.ProductDAO;

/**
 * @author rafael.saldanha
 *
 */
public class FindProductCommand extends AbstractCommand {

	public FindProductCommand(ProductDAO dao) {
		super();
		this.dao = dao;
	}

	private static final long serialVersionUID = 7606485709102366372L;
	private static Logger logger = Logger.getLogger(FindProductCommand.class);
	
	private String pagina = PAGE_PRODUTO_FORMULARIO;
	private ProductDAO dao;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Product produto = dao.findById(getID(request));
			setEntity(request, produto);
			
		} catch (DAOException | ServletException | SystemException e) {
			logger.error(e.getMessage());
			pagina = PAGE_PRODUTO_LISTA;
			setException(request, e);
		}
		return pagina;
	}

}
