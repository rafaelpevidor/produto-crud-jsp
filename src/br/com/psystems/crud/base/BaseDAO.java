/**
 * 
 */
package br.com.psystems.crud.base;

import java.util.List;

import br.com.psystems.crud.exception.DAOException;

/**
 * @author rafael.saldanha
 *
 */
public interface BaseDAO <T extends BaseEntity> {

	public void save(T entity) throws DAOException;
	public T update(T entity) throws DAOException;
	public void delete(Long id) throws DAOException;
	public T findById(Long id) throws DAOException;
	public List<T> findByName(String nome) throws DAOException;
	public List<T> getAll() throws DAOException;
	
}
