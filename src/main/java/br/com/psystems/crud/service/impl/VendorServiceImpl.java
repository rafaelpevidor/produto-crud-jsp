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
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.VendorDAO;
import br.com.psystems.crud.service.VendorService;

/**
 * @author developer
 *
 */
public class VendorServiceImpl extends AbstractCrudService implements VendorService {
	
	public VendorServiceImpl(VendorDAO dao) {
		super();
		this.dao = dao;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2941442041851280901L;
	
	private VendorDAO dao;

	@Override
	public void save(Vendor entity) throws DAOException, SystemException {
		dao.save(entity);
	}

	@Override
	public Vendor update(Vendor entity) throws DAOException, SystemException {
		return dao.update(entity);
	}

	@Override
	public void delete(Long id) throws DAOException, SystemException {
		dao.delete(id);
	}

	@Override
	public Vendor findById(Long id) throws DAOException, SystemException {
		return dao.findById(id);
	}

	@Override
	public List<Vendor> findByName(String name) throws DAOException, SystemException {
		return dao.findByName(name);
	}

	@Override
	public List<Vendor> getAll() throws DAOException, SystemException {
		return dao.getAll();
	}

	@Override
	public void validateRequiredFieldsOf(Vendor entity) throws BusinessException, SystemException {
		try {
			Validate.notEmpty(entity.getName(), "O campo {0} é obrigatório.", "'nome'");
		} catch (NullPointerException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new SystemException(Constants.ERROR_MESSAGE_UNEXPECTED_ERROR_ATRIBUTE, e);
		}
	}

}
