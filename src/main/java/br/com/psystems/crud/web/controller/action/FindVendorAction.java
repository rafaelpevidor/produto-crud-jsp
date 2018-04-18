/**
 * 
 */
package br.com.psystems.crud.web.controller.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.service.CrudService;
import br.com.psystems.crud.service.VendorService;
import br.com.psystems.crud.web.controller.Controllable;

/**
 * @author rafael.saldanha
 *
 */
public class FindVendorAction extends AbstractCrudAction<Vendor> implements Controllable {

	public FindVendorAction(VendorService service) {
		super(service);
	}

	private static final long serialVersionUID = 7606485709102366372L;
	private static Logger logger = Logger.getLogger(FindVendorAction.class);

	private VendorService service;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			Vendor fornecedor = service.findById(getID(request));
			setEntity(request, fornecedor);
			
			return Constants.PAGE_VENDOR_FORM;
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
			return Constants.PAGE_VENDOR_LIST;
		}
	}

	@Override
	protected void setService(CrudService<Vendor> service) {
		this.service = (VendorService) service;
	}

}
