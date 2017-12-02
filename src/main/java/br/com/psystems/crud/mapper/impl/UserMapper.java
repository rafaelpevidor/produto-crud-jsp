/**
 * 
 */
package br.com.psystems.crud.mapper.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.psystems.crud.infra.exception.MapperException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.mapper.BaseMapper;
import br.com.psystems.crud.model.User;
import br.com.psystems.crud.model.enums.RoleEnum;

/**
 * @author rafael.saldanha
 *
 */
public final class UserMapper implements BaseMapper<User> {
	
	@Override
	public User map(HttpServletRequest request) throws MapperException {
		
		User user = new User();
		try {
			user.setEmail((String) request.getParameter("email"));
			user.setName((String) request.getParameter("name"));
			user.setId(NumberUtils.createLong((String) request.getParameter("id")));
			user.setPassword((String) request.getParameter("password"));
			user.setRole(RoleEnum.valueOf((String) request.getParameter("role")));
		} catch (Exception e) {
			throw new MapperException(Constants.ERROR_MESSAGE_MAPPER_ERROR, e);
		}
		return user;
	}
}
