/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.MapperException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.mapper.impl.VendorMapper;
import br.com.psystems.crud.model.BaseEntity;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.service.BaseService;
import br.com.psystems.crud.service.VendorService;
import br.com.psystems.crud.web.controller.Controllable;
import br.com.psystems.crud.web.controller.Mappable;

/**
 * @author rafael.saldanha
 *
 */
public class AddVendorCommand extends AbstractCrudCommand<Vendor> implements Controllable, Mappable {

	public AddVendorCommand(VendorService service) {
		super(service);
	}

	private static final long serialVersionUID = -9136913987730291078L;
	private static Logger logger = Logger.getLogger(AddVendorCommand.class);
	
	private VendorService service;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			Vendor fornecedor = (Vendor) map(request);
			service.save(fornecedor);
			addSuccessMessage(request, Constants.MESSAGE_ADD_SUCCESS);
			
			return Constants.PAGE_VENDOR_LIST;
		} catch (DAOException |MapperException| SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
			return Constants.PAGE_VENDOR_FORM;
		}
	}

	@Override
	protected void setService(BaseService<Vendor> service) {
		this.service = (VendorService) service;
	}

	@Override
	public BaseEntity map(HttpServletRequest request) throws MapperException {
		return new VendorMapper().map(request);
	}
}
