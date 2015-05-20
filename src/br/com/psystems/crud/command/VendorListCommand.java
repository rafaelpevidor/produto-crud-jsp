/**
 * 
 */
package br.com.psystems.crud.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.exception.PersistenceException;
import br.com.psystems.crud.model.dao.VendorDAO;
import br.com.psystems.crud.model.domain.Vendor;

/**
 * @author rafael.saldanha
 *
 */
public class VendorListCommand extends AbstractCommand implements ICommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1642703734465768403L;
	
	private static final String SUCCESS = "listvendor.jsp";
	private static final String ERROR = SUCCESS;
	
	private VendorDAO vendorDAO;

	/**
	 * @see br.com.psystems.crud.command.ICommand#execute(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		
		init();
		
		try {
			
			List<Vendor> vendors = vendorDAO.getAll();
			
			if (vendors.isEmpty()) {
				request.setAttribute("vendors", null);
			} else {
				request.setAttribute("vendors", vendors);
			}
			
			setupEmptyList(request, vendors);
			
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
		vendorDAO = new VendorDAO();
	}

}
