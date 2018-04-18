/**
 * 
 */
package br.com.psystems.crud.service.impl;

import br.com.psystems.crud.model.dao.DashboardDAO;
import br.com.psystems.crud.service.DashboardService;

/**
 * @author developer
 *
 */
public class DashboardServiceImpl implements DashboardService {

	public DashboardServiceImpl(DashboardDAO dashboardDAO) {
		this.dao = dashboardDAO;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7058155370349173191L;

	private DashboardDAO dao;

	@Override
	public void loadCollections() {
		// TODO Auto-generated method stub
	}
}
