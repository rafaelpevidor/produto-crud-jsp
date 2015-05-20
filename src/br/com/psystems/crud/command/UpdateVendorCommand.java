/**
 * 
 */
package br.com.psystems.crud.command;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.bind.VendorBind;
import br.com.psystems.crud.exception.ConverterException;
import br.com.psystems.crud.exception.PersistenceException;
import br.com.psystems.crud.model.dao.VendorDAO;
import br.com.psystems.crud.model.domain.Vendor;

/**
 * @author rafael.saldanha
 *
 */
public class UpdateVendorCommand extends AbstractCommand implements ICommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8169250379905812667L;
	
//	private static final String SUCCESS = "listvendor.jsp";
	private static final String ERROR = "updatevendor.jsp";
	
	private VendorDAO vendorDAO;

	@Override
	public String execute(HttpServletRequest request) {
		
		init();
		
		try {
			
			Vendor vendor = VendorBind.getInstance().bind(request);
			vendorDAO.update(vendor);
			
			addMessage(request, "Fornecedor atualizado com sucesso!");
			
		} catch (PersistenceException | ConverterException e) {
			
			setupException(request, e);
			return ERROR;
		} catch (Exception e) {
			
			setupUnexpectedException(request, e);
			return ERROR;
		}
		return new VendorListCommand().execute(request);
	}

	@Override
	protected void init() {
		vendorDAO = new VendorDAO();
	}

}
