/**
 * 
 */
package br.com.psystems.crud.service.impl;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.BusinessException;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.model.User;
import br.com.psystems.crud.model.dao.UserDAO;
import br.com.psystems.crud.service.UserService;

/**
 * @author developer
 *
 */
public class UserServiceImpl extends AbstractCrudService implements UserService {
	
	public UserServiceImpl(UserDAO dao) {
		super(dao);
		this.dao = dao;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1455221387956022407L;
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	private UserDAO dao;

	@Override
	public void save(User entity) throws DAOException, SystemException {
		dao.save(entity);
	}

	@Override
	public User update(User entity) throws DAOException, SystemException {
		return dao.update(entity);
	}

	@Override
	public void delete(Long id) throws DAOException, SystemException {
		dao.delete(id);
	}

	@Override
	public User findById(Long id) throws DAOException, SystemException {
		return dao.findById(id);
	}

	@Override
	public List<User> findByName(String name) throws DAOException, SystemException {
		return dao.findByName(name);
	}

	@Override
	public List<User> getAll() throws DAOException, SystemException {
		return dao.getAll();
	}

	@Override
	public void validateRequiredFieldsOf(User user) throws BusinessException, SystemException {
		try {
			Validate.notEmpty(user.getEmail(), "O campo 'e-mail' é obrigatório.");
			Validate.notEmpty(user.getName(), "O campo 'nome' é obrigatório.");
			Validate.notEmpty(user.getPassword(), "O campo 'senha' é obrigatório.");
			Validate.notNull(user.getRole(), "O campo 'perfil' é obrigatório.");
		} catch (NullPointerException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new SystemException(Constants.ERROR_MESSAGE_UNEXPECTED_ERROR_ATRIBUTE, e);
		}
	}

}
