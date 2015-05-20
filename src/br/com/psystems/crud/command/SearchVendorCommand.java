/**
 * 
 */
package br.com.psystems.crud.command;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import br.com.psystems.crud.exception.PersistenceException;
import br.com.psystems.crud.model.dao.VendorDAO;
import br.com.psystems.crud.model.domain.Vendor;

/**
 * @author rafael.saldanha
 *
 */
public class SearchVendorCommand extends AbstractCommand implements ICommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3699559009520920474L;
	
	private static final String SUCCESS = "listvendor.jsp";
	private static final String ERROR = SUCCESS;
	
	private VendorDAO vendorDAO;

	/**
	 * @see br.com.psystems.crud.command.Acao#executa(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		
		init();
		
		try {
			
			String keyword = getKeyword(request);
			
			if (StringUtils.isNotEmpty(keyword)) {
				
				List<Vendor> vendors = vendorDAO.getByKeyword(keyword);
				request.setAttribute("vendors", vendors);
				
				setupEmptyList(request, vendors);
				
			} else {
				request.removeAttribute("keyword");
				return new VendorListCommand().execute(request);
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
		vendorDAO = new VendorDAO();
	}

}
