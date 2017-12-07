/**
 * 
 */
package br.com.psystems.crud.service.impl;

import java.util.List;

import br.com.psystems.crud.infra.exception.BusinessException;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.dao.impl.ProductDAO;
import br.com.psystems.crud.service.ProductService;

/**
 * @author developer
 *
 */
public class ProductServiceImpl extends AbstractCrudService implements ProductService {
	
	public ProductServiceImpl(ProductDAO dao) {
		super();
		this.dao = dao;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3969917881461784985L;
	
	private ProductDAO dao; 

	@Override
	public void save(Product entity) throws DAOException, SystemException {
		dao.save(entity);
	}

	@Override
	public Product update(Product entity) throws DAOException, SystemException {
		return dao.update(entity);
	}

	@Override
	public void delete(Long id) throws DAOException, SystemException {
		dao.delete(id);
	}

	@Override
	public Product findById(Long id) throws DAOException, SystemException {
		return dao.findById(id);
	}

	@Override
	public List<Product> findByName(String name) throws DAOException, SystemException {
		return dao.findByName(name);
	}

	@Override
	public List<Product> getAll() throws DAOException, SystemException {
		return dao.getAll();
	}

	@Override
	public void validateRequiredFieldsOf(Product entity) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateRequiredFieldByBusinessRuleOf(Product entity) throws BusinessException {
		// TODO Auto-generated method stub
		// se o produto é de fabricação própria o fornecedor deve ser nulo (e vice-versa)
	}

}
