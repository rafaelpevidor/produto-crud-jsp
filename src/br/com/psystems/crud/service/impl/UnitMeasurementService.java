/**
 * 
 */
package br.com.psystems.crud.service.impl;

import java.util.List;

import br.com.psystems.crud.infra.exception.BusinessException;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.BaseEntity;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.model.dao.BaseDAO;
import br.com.psystems.crud.model.dao.impl.UnitMeasurementDAO;
import br.com.psystems.crud.service.AbstractService;
import br.com.psystems.crud.service.BaseService;

/**
 * @author developer
 *
 */
public class UnitMeasurementService extends AbstractService implements BaseService<UnitMeasurement> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5530110292531229277L;
	
	private BaseDAO<UnitMeasurement> dao;

	@Override
	public void save(UnitMeasurement entity) throws DAOException, SystemException {
		getDAO().save(entity);
	}

	@Override
	public UnitMeasurement update(UnitMeasurement entity) throws DAOException, SystemException {
		return getDAO().update(entity);
	}

	@Override
	public void delete(Long id) throws DAOException, SystemException {
		getDAO().delete(id);
	}

	@Override
	public UnitMeasurement findById(Long id) throws DAOException, SystemException {
		return getDAO().findById(id);
	}

	@Override
	public List<UnitMeasurement> findByName(String name) throws DAOException, SystemException {
		return getDAO().findByName(name);
	}

	@Override
	public List<UnitMeasurement> getAll() throws DAOException, SystemException {
		return getDAO().getAll();
	}

	@Override
	public void validateRequiredFieldsOf(BaseEntity entity) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public BaseDAO<UnitMeasurement> getDAO() throws DAOException, SystemException {
		if (null == dao)
			dao = new UnitMeasurementDAO();
		return dao;
	}
}
