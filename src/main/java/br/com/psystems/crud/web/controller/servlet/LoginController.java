/**
 * 
 */
package br.com.psystems.crud.web.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.web.controller.AbstractController;
import br.com.psystems.crud.web.controller.util.URLAction;

/**
 * @author developer
 *
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4200432274630069410L;

	/* (non-Javadoc)
	 * @see br.com.psystems.crud.web.controller.AbstractController#processRequest(br.com.psystems.crud.web.controller.util.URLAction, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void processRequest(URLAction command, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			String page = getAction(request.getServletPath()).getInstance().execute(request);
			forward(page, request, response);
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
}
