/**
 * 
 */
package br.com.psystems.crud.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.web.controller.enums.ActionEnum;
import br.com.psystems.crud.web.controller.util.URLAction;

/**
 * @author developer
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractController extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(AbstractController.class);

	public void processRequest(URLAction command, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			String page = null;
			if (null == command)
				page = getAction(request.getServletPath()).getPage();
			else
				page = getAction(request.getServletPath()).getInstance().execute(request);
			forward(page, request, response);
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}

	public void doProcessRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {

			URLAction command = URLAction.parse(request.getRequestURI());
			
			if (command != null)
	            logger.info("Command " + command.getName() + "-" + command.getValue());
	        else
	        	logger.info("Nenhum comando encontrado na requisição");
			
			this.processRequest(command, request, response);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		this.doProcessRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		this.doProcessRequest(request, response);
	}

	public void forward(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(page).forward(request, response);
	}

	public void redirect(String page, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(page);
	}

	public ActionEnum getAction(String servletPath) throws DAOException {
		return ActionEnum.getByPath(servletPath);
	}
}
