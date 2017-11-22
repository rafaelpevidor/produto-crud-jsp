/**
 * 
 */
package br.com.psystems.crud.test.builder;

import br.com.psystems.crud.model.User;
import br.com.psystems.crud.model.enums.RoleEnum;

/**
 * @author developer
 *
 */
public class UserBuilder {

	public UserBuilder() {
		user = new User();
	}

	private User user;
	
	public UserBuilder setName(String name) {
		user.setName(name);
		return this;
	}
	
	public UserBuilder setPassword(String password) {
		user.setPassword(password);
		return this;
	}
	
	public UserBuilder setEmail(String email) {
		user.setEmail(email);
		return this;
	}
	
	public UserBuilder setRole(RoleEnum role) {
		user.setRole(role);
		return this;
	}
	
	public User build() {
		return user;
	}
}
