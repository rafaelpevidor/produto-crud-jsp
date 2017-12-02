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
public class AddUserCommand extends AbstractCrudCommand<User> implements Controllable, Mappable {

	public AddUserCommand(UserService service) {
		super(service);
	}

	private static final long serialVersionUID = -9136913987730291078L;
	private static Logger logger = Logger.getLogger(AddUserCommand.class);
	
	private UserService service;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			User user = (User) map(request);
			service.save(user);
			addInfoMessage(request, Constants.MESSAGE_ADD_SUCCESS);
			
			return Constants.PAGE_USER_LIST;
		} catch (MapperException | DAOException | SystemException e) {
			logger.error(e.getMessage(), e);
			setException(request, e);
			return Constants.PAGE_USER_FORM;
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
