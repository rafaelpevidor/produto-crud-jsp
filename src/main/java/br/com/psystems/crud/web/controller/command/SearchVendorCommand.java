/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.service.BaseService;
import br.com.psystems.crud.service.VendorService;
import br.com.psystems.crud.web.controller.Controllable;

/**
 * @author rafael.saldanha
 *
 */
public class SearchVendorCommand extends AbstractCrudCommand<Vendor> implements Controllable {

	public SearchVendorCommand(VendorService service) {
		super(service);
	}

	private static final long serialVersionUID = 3699559009520920474L;
	private static Logger logger = Logger.getLogger(SearchVendorCommand.class);
	
	private VendorService service;

	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Acao#executa(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Vendor> vendors = service.findByName(getSearchKeyWord(request));
			setEntityList(request, vendors);
			
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return Constants.PAGE_VENDOR_LIST;
	}

	@Override
	protected void setService(BaseService<Vendor> service) {
		this.service = (VendorService) service;
	}
}
