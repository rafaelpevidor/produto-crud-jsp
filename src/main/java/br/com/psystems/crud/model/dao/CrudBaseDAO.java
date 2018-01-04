/**
 * 
 */
package br.com.psystems.crud.model.dao;

import java.util.List;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.model.BaseEntity;

/**
 * @author developer
 *
 */
public interface CrudBaseDAO<T extends BaseEntity> extends BaseDAO {

	public void save(T entity) throws DAOException, SystemException;
	public T update(T entity) throws DAOException, SystemException;
	public void delete(Long id) throws DAOException, SystemException;
	public T findById(Long id) throws DAOException, SystemException;
	public List<T> findByName(String nome) throws DAOException, SystemException;
	public List<T> getAll() throws DAOException, SystemException;
}
