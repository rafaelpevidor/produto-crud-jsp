/**
 * 
 */
package br.com.psystems.crud.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.exception.PersistenceException;
import br.com.psystems.crud.model.dao.ProductDAO;

/**
 * @author rafael.saldanha
 *
 */
public class DeleteProductCommand extends AbstractCommand implements ICommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -813686630847825683L;
	
	private static final String SUCCESS = "listproduct.jsp";
	private static final String ERROR = SUCCESS;
	
	private ProductDAO productDAO;

	@Override
	public String execute(HttpServletRequest request) {

		init();
		
		try {
			
			Integer pk = getId(request);
			productDAO.delete(pk);
			
			addMessage(request, "Produto apagado com sucesso!");
		
			//recurso do Java 7 para tratar mais de uma exceção em um mesm catch
		} catch (PersistenceException | ServletException e) {
			
			setupException(request, e);
			return ERROR;
		} catch (Exception e) {
			
			setupUnexpectedException(request, e);
			return ERROR;
		}
		return new ProductListCommand().execute(request);
	}

	@Override
	protected void init() {
		productDAO = new ProductDAO();
	}

}
