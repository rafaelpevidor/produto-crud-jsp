/**
 * 
 */
package br.com.psystems.crud.command;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.exception.PersistenceException;
import br.com.psystems.crud.model.dao.VendorDAO;
import br.com.psystems.crud.model.dao.ProductDAO;
import br.com.psystems.crud.model.domain.Vendor;
import br.com.psystems.crud.model.domain.Product;

/**
 * @author rafael.saldanha
 *
 */
public class RecoverProductCommand extends AbstractCommand implements ICommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7606485709102366372L;
	
	private static final String SUCCESS = "updateproduct.jsp";
//	private static final String ERROR = "listproduct.jsp";
	
	private ProductDAO productDAO;
	private VendorDAO vendorDAO;

	@Override
	public String execute(HttpServletRequest request) {
		
		init();
		
		try {
			
			Product product = productDAO.recoverByID(getId(request));
			request.setAttribute("product", product);
			
			List<Vendor> vendors = vendorDAO.getByIdDifferent(product.getVendorId());
			request.setAttribute("vendors", vendors);
			
		} catch (PersistenceException | ServletException e) {
			
			setupException(request, e);
			return new ProductListCommand().execute(request);
			
		} catch (Exception e) {
			
			setupUnexpectedException(request, e);
			return new ProductListCommand().execute(request);
		}
		return SUCCESS;
	}

	@Override
	protected void init() {
		productDAO = new ProductDAO();
		vendorDAO = new VendorDAO();
	}

}