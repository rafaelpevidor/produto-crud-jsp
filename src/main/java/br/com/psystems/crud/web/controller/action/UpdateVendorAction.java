/**
 * 
 */
package br.com.psystems.crud.web.controller.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.MapperException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.mapper.impl.VendorMapper;
import br.com.psystems.crud.model.BaseEntity;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.service.CrudService;
import br.com.psystems.crud.service.VendorService;
import br.com.psystems.crud.web.controller.Controllable;
import br.com.psystems.crud.web.controller.Mappable;

/**
 * @author rafael.saldanha
 *
 */
public class UpdateVendorAction extends AbstractCrudAction<Vendor> implements Controllable, Mappable {

	public UpdateVendorAction(VendorService service) {
		super(service);
	}

	private static final long serialVersionUID = 8169250379905812667L;
	private static Logger logger = Logger.getLogger(UpdateVendorAction.class);
	
	private VendorService service;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Vendor vendor = (Vendor) map(request);
			service.update(vendor);
			addSuccessMessage(request, Constants.MESSAGE_UPDATE_SUCCESS);
			
			return Constants.PAGE_VENDOR_LIST;
		} catch (DAOException | MapperException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
			return Constants.PAGE_VENDOR_FORM;
		}
	}

	@Override
	protected void setService(CrudService<Vendor> service) {
		this.service = (VendorService) service;
	}

	@Override
	public BaseEntity map(HttpServletRequest request) throws MapperException {
		return new VendorMapper().map(request);
	}
}
