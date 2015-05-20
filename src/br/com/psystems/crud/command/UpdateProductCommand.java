/**
 * 
 */
package br.com.psystems.crud.command;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.bind.ProductBind;
import br.com.psystems.crud.exception.ConverterException;
import br.com.psystems.crud.exception.PersistenceException;
import br.com.psystems.crud.model.dao.ProductDAO;
import br.com.psystems.crud.model.domain.Product;

/**
 * @author rafael.saldanha
 *
 */
public class UpdateProductCommand extends AbstractCommand implements ICommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8169250379905812667L;
	
//	private static final String SUCCESS = "listproduct.jsp";
	private static final String ERROR = "updateproduct.jsp";
	
	private ProductDAO productDAO;

	@Override
	public String execute(HttpServletRequest request) {
		
		init();
		
		try {
			
			Product product = ProductBind.getInstance().bind(request);
			productDAO.update(product);
			
			addMessage(request, "Produto atualizado com sucesso!");
			
		} catch (PersistenceException | ConverterException e) {
			
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