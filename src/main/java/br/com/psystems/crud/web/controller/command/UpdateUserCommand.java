/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.MapperException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.mapper.impl.UserMapper;
import br.com.psystems.crud.model.BaseEntity;
import br.com.psystems.crud.model.User;
import br.com.psystems.crud.service.BaseService;
import br.com.psystems.crud.service.UserService;
import br.com.psystems.crud.web.controller.Controllable;
import br.com.psystems.crud.web.controller.Mappable;

/**
 * @author rafael.saldanha
 *
 */
public class UpdateUserCommand extends AbstractCrudCommand<User> implements Controllable, Mappable {

	public UpdateUserCommand(UserService service) {
		super(service);
	}

	private static final long serialVersionUID = 8169250379905812667L;
	private static Logger logger = Logger.getLogger(UpdateUserCommand.class);
	
	private UserService service;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			User user = (User) map(request);
			service.update(user);
			addSuccessMessage(request, Constants.MESSAGE_UPDATE_SUCCESS);
			
			return Constants.PAGE_VENDOR_LIST;
		} catch (DAOException | MapperException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
			return Constants.PAGE_VENDOR_FORM;
		}
	}

	@Override
	protected void setService(BaseService<User> service) {
		this.service = (UserService) service;
	}

	@Override
	public BaseEntity map(HttpServletRequest request) throws MapperException {
		return new UserMapper().map(request);
	}
}
