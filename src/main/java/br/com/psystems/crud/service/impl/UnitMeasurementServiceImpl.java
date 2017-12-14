/**
 * 
 */
package br.com.psystems.crud.service.impl;

import java.util.List;

import org.apache.commons.lang3.Validate;

import br.com.psystems.crud.infra.exception.BusinessException;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.model.dao.UnitMeasurementDAO;
import br.com.psystems.crud.service.UnitMeasurementService;

/**
 * @author developer
 *
 */
public class UnitMeasurementServiceImpl extends AbstractCrudService implements UnitMeasurementService {

	public UnitMeasurementServiceImpl(UnitMeasurementDAO dao) {
		super(dao);
		this.dao = dao;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5530110292531229277L;
	
	private UnitMeasurementDAO dao;

	@Override
	public void save(UnitMeasurement entity) throws DAOException, SystemException {
		dao.save(entity);
	}

	@Override
	public UnitMeasurement update(UnitMeasurement entity) throws DAOException, SystemException {
		return dao.update(entity);
	}

	@Override
	public void delete(Long id) throws DAOException, SystemException {
		dao.delete(id);
	}

	@Override
	public UnitMeasurement findById(Long id) throws DAOException, SystemException {
		return dao.findById(id);
	}

	@Override
	public List<UnitMeasurement> findByName(String name) throws DAOException, SystemException {
		return dao.findByName(name);
	}

	@Override
	public List<UnitMeasurement> getAll() throws DAOException, SystemException {
		return dao.getAll();
	}

	@Override
	public void validateRequiredFieldsOf(UnitMeasurement entity) throws BusinessException, SystemException {
		try {
			Validate.notEmpty(entity.getName(), "O campo 'nome' é obrigatório.");
		} catch (NullPointerException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new SystemException(Constants.ERROR_MESSAGE_UNEXPECTED_ERROR_ATRIBUTE, e);
		}
	}
	
}
