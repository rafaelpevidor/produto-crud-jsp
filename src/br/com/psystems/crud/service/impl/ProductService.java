/**
 * 
 */
package br.com.psystems.crud.service.impl;

import java.util.List;

import br.com.psystems.crud.infra.exception.BusinessException;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.BaseEntity;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.dao.BaseDAO;
import br.com.psystems.crud.model.dao.impl.ProductDAO;
import br.com.psystems.crud.service.AbstractService;
import br.com.psystems.crud.service.BaseService;

/**
 * @author developer
 *
 */
public class ProductService extends AbstractService implements BaseService<Product> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3969917881461784985L;
	
	private BaseDAO<Product> dao; 

	@Override
	public void save(Product entity) throws DAOException, SystemException {
		getDAO().save(entity);
	}

	@Override
	public Product update(Product entity) throws DAOException, SystemException {
		return getDAO().update(entity);
	}

	@Override
	public void delete(Long id) throws DAOException, SystemException {
		getDAO().delete(id);
	}

	@Override
	public Product findById(Long id) throws DAOException, SystemException {
		return getDAO().findById(id);
	}

	@Override
	public List<Product> findByName(String name) throws DAOException, SystemException {
		return getDAO().findByName(name);
	}

	@Override
	public List<Product> getAll() throws DAOException, SystemException {
		return getDAO().getAll();
	}

	@Override
	public void validateRequiredFieldsOf(BaseEntity entity) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BaseDAO<Product> getDAO() throws DAOException, SystemException {
		if (null == dao)
			dao = new ProductDAO();
		return dao;
	}

}
