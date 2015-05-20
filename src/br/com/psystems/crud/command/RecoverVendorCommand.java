/**
 * 
 */
package br.com.psystems.crud.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.exception.PersistenceException;
import br.com.psystems.crud.model.dao.VendorDAO;
import br.com.psystems.crud.model.domain.Vendor;

/**
 * @author rafael.saldanha
 *
 */
public class RecoverVendorCommand extends AbstractCommand implements ICommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7606485709102366372L;
	
	private static final String SUCCESS = "updatevendor.jsp";
//	private static final String ERROR = "listvendor.jsp";
	
	private VendorDAO vendorDAO;

	@Override
	public String execute(HttpServletRequest request) {

		init();
		
		try {
			
			Vendor vendor = vendorDAO.recoverByID(getId(request));
			request.setAttribute("vendor", vendor);
			
		} catch (PersistenceException | ServletException e) {
			
			setupException(request, e);
			return new VendorListCommand().execute(request);
			
		} catch (Exception e) {
			
			setupUnexpectedException(request, e);
			return new VendorListCommand().execute(request);
		}
		return SUCCESS;
	}

	@Override
	protected void init() {
		vendorDAO = new VendorDAO();
	}

}
