/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.impl.VendorDAO;

/**
 * @author rafael.saldanha
 *
 */
public class FindUserCommand extends AbstractCrudCommand {

	public FindUserCommand(VendorDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = 7606485709102366372L;
	private static Logger logger = Logger.getLogger(FindUserCommand.class);

	private String page = ConstantsUtils.PAGE_VENDOR_FORM;
	private VendorDAO dao;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			Vendor vendor = dao.findById(getID(request));
			setEntity(request, vendor);
			
		} catch (DAOException | SystemException | ServletException e) {
			logger.error(e.getMessage());
			page = ConstantsUtils.PAGE_VENDOR_LIST;
			setException(request, e);
		}
		return page;
	}

}
