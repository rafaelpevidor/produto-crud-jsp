/**
 * 
 */
package br.com.psystems.crud.controller.command;

import java.util.List;

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
public class SearchProductCommand extends AbstractCommand {

	public SearchProductCommand(ProductDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = -4000181949308716513L;
	private static Logger logger = Logger.getLogger(SearchProductCommand.class);
	
	private String pagina = PAGE_PRODUTO_LISTA;
	private ProductDAO dao;

	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Acao#executa(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Product> produtos = dao.findByName(getKeyWord(request));
			setProdutos(request, produtos);
			
		} catch (DAOException | ServletException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return pagina;
	}

}
