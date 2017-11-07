/**
 * 
 */
package br.com.psystems.crud.controller.bind;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.psystems.crud.exception.BindException;
import br.com.psystems.crud.model.RoleEnum;
import br.com.psystems.crud.model.User;
import br.com.psystems.crud.util.ConstantsUtils;

/**
 * @author rafael.saldanha
 *
 */
public final class UserBind extends AbstractBind<User> {
	
	@Override
	public User buildEntity(HttpServletRequest request) throws BindException {
		
		User user = new User();
		try {
			user.setEmail((String) request.getParameter("email"));
			user.setName((String) request.getParameter("name"));
			user.setId(NumberUtils.createLong((String) request.getParameter("id")));
			user.setPassword((String) request.getParameter("password"));
			user.setRole(RoleEnum.valueOf((String) request.getParameter("role")));
		} catch (Exception e) {
			throw new BindException(ConstantsUtils.BIND_ERROR_MESSAGE, e);
		}
		return user;
	}
}
