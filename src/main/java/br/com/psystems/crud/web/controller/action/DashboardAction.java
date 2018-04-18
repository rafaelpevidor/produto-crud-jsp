/**
 * 
 */
package br.com.psystems.crud.web.controller.action;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.service.DashboardService;
import br.com.psystems.crud.web.controller.Controllable;

/**
 * @author developer
 *
 */
public class DashboardAction extends AbstractAction implements Controllable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4570018579687639304L;

	public DashboardAction(DashboardService dashBoardService) {
		this.service = dashBoardService;
	}

	/* (non-Javadoc)
	 * @see br.com.psystems.crud.web.controller.Controllable#execute(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		addInfoMessage(request, "Bem vindo!");
		service.loadCollections();
		return Constants.PAGE_DASHBOARD;
	}

	private DashboardService service;
}
