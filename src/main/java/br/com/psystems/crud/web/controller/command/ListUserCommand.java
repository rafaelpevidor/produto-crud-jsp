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
import br.com.psystems.crud.model.User;
import br.com.psystems.crud.service.BaseService;
import br.com.psystems.crud.service.UserService;
import br.com.psystems.crud.web.controller.Controllable;

/**
 * @author Rafael.Saldanha
 *
 */
public class ListUserCommand extends AbstractCrudCommand<User> implements Controllable {

	public ListUserCommand(UserService service) {
		super(service);
	}

	private static final long serialVersionUID = 7904027167987556427L;
	private static Logger logger = Logger.getLogger(ListUserCommand.class);

	private UserService service;


	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Command#executar(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<User> users = service.getAll();
			setEntityList(request, users);
			
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return Constants.PAGE_VENDOR_LIST;
	}


	@Override
	protected void setService(BaseService<User> service) {
		this.service = (UserService) service;
	}

}
