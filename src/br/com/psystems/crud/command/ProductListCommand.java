/**
 * 
 */
package br.com.psystems.crud.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.exception.PersistenceException;
import br.com.psystems.crud.model.dao.ProductDAO;
import br.com.psystems.crud.model.domain.Product;

/**
 * @author rafael.saldanha
 *
 */
public class ProductListCommand extends AbstractCommand implements ICommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7949387118821391627L;
	
	private static final String SUCCESS = "listproduct.jsp";
	private static final String ERROR = SUCCESS;
	
	private ProductDAO productDAO;

	/**
	 * @see br.com.psystems.crud.command.ICommand#execute(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		
		init();
		
		try {
			
			List<Product> products = productDAO.getAll();
			
			if (products.isEmpty()) {
				request.setAttribute("products", null);
			} else {
				request.setAttribute("products", products);
			}
			
			setupEmptyList(request, products);
			
		} catch (PersistenceException e) {
			
			setupException(request, e);
			return ERROR;
		} catch (Exception e) {
			
			setupUnexpectedException(request, e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * @see br.com.psystems.crud.command.AbstractCommand#init()
	 */
	@Override
	protected void init() {
		productDAO = new ProductDAO();
	}

}