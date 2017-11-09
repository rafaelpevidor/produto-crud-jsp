/**
 * 
 */
package br.com.psystems.crud.test.builder;

import br.com.psystems.crud.model.RoleEnum;
import br.com.psystems.crud.model.User;

/**
 * @author developer
 *
 */
public class UserBuilder {

	public UserBuilder() {
		user = new User();
	}

	private User user;
	
	public UserBuilder withName(String name) {
		user.setName(name);
		return this;
	}
	
	public UserBuilder withPassword(String password) {
		user.setPassword(password);
		return this;
	}
	
	public UserBuilder withEmail(String email) {
		user.setEmail(email);
		return this;
	}
	
	public UserBuilder withRole(RoleEnum role) {
		user.setRole(role);
		return this;
	}
	
	public User build() {
		return user;
	}
}
