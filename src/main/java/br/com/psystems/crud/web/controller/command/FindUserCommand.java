/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.model.User;
import br.com.psystems.crud.service.BaseService;
import br.com.psystems.crud.service.UserService;
import br.com.psystems.crud.web.controller.Controllable;

/**
 * @author rafael.saldanha
 *
 */
public class FindUserCommand extends AbstractCrudCommand<User> implements Controllable {

	public FindUserCommand(UserService service) {
		super(service);
	}

	private static final long serialVersionUID = 7606485709102366372L;
	private static Logger logger = Logger.getLogger(FindUserCommand.class);

	private UserService service;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			User entity = service.findById(getID(request));
			setEntity(request, entity);
			
			return Constants.PAGE_VENDOR_FORM;
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
			return Constants.PAGE_VENDOR_LIST;
		}
	}

	@Override
	protected void setService(BaseService<User> service) {
		this.service = (UserService) service;
	}

}
