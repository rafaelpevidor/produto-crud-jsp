/**
 * 
 */
package br.com.psystems.crud.model.dao.impl;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.model.dao.DashboardDAO;

/**
 * @author developer
 *
 */
public class DashboardDAOImpl extends AbstractBaseDAO implements DashboardDAO {

	public DashboardDAOImpl(ConnectionManager connectionManager) throws DAOException {
		super(connectionManager);
	}

	public DashboardDAOImpl() throws DAOException {
		super();
	}

	@Override
	public void loadCollections() {
		// TODO Auto-generated method stub
		
	}

}
