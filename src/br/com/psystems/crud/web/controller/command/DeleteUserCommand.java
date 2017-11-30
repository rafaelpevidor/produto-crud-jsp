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
import br.com.psystems.crud.model.dao.impl.VendorDAO;

/**
 * @author rafael.saldanha
 *
 */
public class DeleteUserCommand extends AbstractCrudCommand {

	public DeleteUserCommand(VendorDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = -813686630847825683L;
	private static Logger logger = Logger.getLogger(DeleteUserCommand.class);

	private String pagina = ConstantsUtils.PAGE_VENDOR_LIST;
	private VendorDAO dao;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			dao.delete(getID(request));
			addSuccessMessage(request, ConstantsUtils.MESSAGE_DELETE_SUCCESS);
			
		} catch (DAOException | ServletException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return pagina;
	}

}
