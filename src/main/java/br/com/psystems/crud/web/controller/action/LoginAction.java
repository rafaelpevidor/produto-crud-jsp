/**
 * 
 */
package br.com.psystems.crud.web.controller.action;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.web.controller.Controllable;

/**
 * @author developer
 *
 */
public class LoginAction extends AbstractAction implements Controllable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7216992290664392131L;

	/* (non-Javadoc)
	 * @see br.com.psystems.crud.web.controller.Controllable#execute(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		return Constants.PAGE_LOGIN;
	}

}
