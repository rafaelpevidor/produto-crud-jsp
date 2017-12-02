/**
 * 
 */
package br.com.psystems.crud.service;

import java.io.Serializable;
import java.util.List;

import br.com.psystems.crud.infra.exception.BusinessException;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.BaseEntity;

/**
 * @author developer
 *
 */
public interface BaseService<T extends BaseEntity> extends Serializable {

	public void save(T entity) throws DAOException, SystemException;
	public T update(T entity) throws DAOException, SystemException;
	public void delete(Long id) throws DAOException, SystemException;
	public T findById(Long id) throws DAOException, SystemException;
	public List<T> findByName(String name) throws DAOException, SystemException;
	public List<T> getAll() throws DAOException, SystemException;
	public void validateRequiredFieldsOf(T entity) throws BusinessException, SystemException;
	
}
