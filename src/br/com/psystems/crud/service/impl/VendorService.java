/**
 * 
 */
package br.com.psystems.crud.service.impl;

import java.util.List;

import br.com.psystems.crud.infra.exception.BusinessException;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.BaseEntity;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.BaseDAO;
import br.com.psystems.crud.model.dao.impl.VendorDAO;
import br.com.psystems.crud.service.AbstractService;
import br.com.psystems.crud.service.BaseService;

/**
 * @author developer
 *
 */
public class VendorService extends AbstractService implements BaseService<Vendor> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2941442041851280901L;
	
	private BaseDAO<Vendor> dao;

	@Override
	public void save(Vendor entity) throws DAOException, SystemException {
		getDAO().save(entity);
	}

	@Override
	public Vendor update(Vendor entity) throws DAOException, SystemException {
		return getDAO().update(entity);
	}

	@Override
	public void delete(Long id) throws DAOException, SystemException {
		getDAO().delete(id);
	}

	@Override
	public Vendor findById(Long id) throws DAOException, SystemException {
		return getDAO().findById(id);
	}

	@Override
	public List<Vendor> findByName(String name) throws DAOException, SystemException {
		return getDAO().findByName(name);
	}

	@Override
	public List<Vendor> getAll() throws DAOException, SystemException {
		return getDAO().getAll();
	}

	@Override
	public void validateRequiredFieldsOf(BaseEntity entity) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BaseDAO<Vendor> getDAO() throws DAOException, SystemException {
		if (null == dao)
			dao = new VendorDAO();
		return dao;
	}
}
