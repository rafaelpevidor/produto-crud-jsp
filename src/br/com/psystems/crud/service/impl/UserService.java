/**
 * 
 */
package br.com.psystems.crud.service.impl;

import java.util.List;

import br.com.psystems.crud.infra.exception.BusinessException;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.BaseEntity;
import br.com.psystems.crud.model.User;
import br.com.psystems.crud.model.dao.BaseDAO;
import br.com.psystems.crud.model.dao.impl.UserDAO;
import br.com.psystems.crud.service.AbstractService;
import br.com.psystems.crud.service.BaseService;

/**
 * @author developer
 *
 */
public class UserService extends AbstractService implements BaseService<User> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1455221387956022407L;
	
	private BaseDAO<User> dao;

	@Override
	public void save(User entity) throws DAOException, SystemException {
		getDAO().save(entity);
	}

	@Override
	public User update(User entity) throws DAOException, SystemException {
		return getDAO().update(entity);
	}

	@Override
	public void delete(Long id) throws DAOException, SystemException {
		getDAO().delete(id);
	}

	@Override
	public User findById(Long id) throws DAOException, SystemException {
		return getDAO().findById(id);
	}

	@Override
	public List<User> findByName(String name) throws DAOException, SystemException {
		return getDAO().findByName(name);
	}

	@Override
	public List<User> getAll() throws DAOException, SystemException {
		return getDAO().getAll();
	}

	@Override
	public void validateRequiredFieldsOf(BaseEntity entity) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BaseDAO<User> getDAO() throws DAOException, SystemException {
		if (null == dao)
			dao = new UserDAO();
		return dao;
	}
}
