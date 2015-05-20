/**
 * 
 */
package br.com.psystems.crud.command;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import br.com.psystems.crud.exception.PersistenceException;
import br.com.psystems.crud.model.dao.ProductDAO;
import br.com.psystems.crud.model.domain.Product;

/**
 * @author rafael.saldanha
 *
 */
public class SearchProductCommand extends AbstractCommand implements ICommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4000181949308716513L;
	
	private static final String SUCCESS = "listproduct.jsp";
	private static final String ERROR = SUCCESS;
	
	private ProductDAO productDAO;

	/**
	 * @see br.com.psystems.crud.command.Acao#executa(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		
		init();
		
		try {
			
			String keyword = getKeyword(request);
			
			if (StringUtils.isNotEmpty(keyword)) {
				
				List<Product> products = productDAO.getByKeyword(keyword);
				request.setAttribute("products", products);
				
				setupEmptyList(request, products);
				
			} else {
				request.removeAttribute("keyword");
				return new ProductListCommand().execute(request);
			}
			
		} catch (PersistenceException | ServletException e) {
			
			setupException(request, e);
			return ERROR;
		} catch (Exception e) {
			
			setupUnexpectedException(request, e);
			return ERROR;
		}
		return SUCCESS;
	}

	@Override
	protected void init() {
		productDAO = new ProductDAO();
	}

}