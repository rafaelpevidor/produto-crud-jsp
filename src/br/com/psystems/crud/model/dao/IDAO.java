/**
 * 
 */
package br.com.psystems.crud.model.dao;

import java.io.Serializable;
import java.util.List;

import br.com.psystems.crud.exception.PersistenceException;
import br.com.psystems.crud.model.domain.BaseDomain;

/**
 * @author rafael.saldanha
 *
 */
public interface IDAO <T extends BaseDomain> {

	public void save(T entity) throws PersistenceException;
	public T update(T entity) throws PersistenceException;
	public void delete(Integer pk) throws PersistenceException;
	public T recoverByID(Serializable id) throws PersistenceException;
	public List<T> getByKeyword(String name) throws PersistenceException;
	public List<T> getAll() throws PersistenceException;
	
}
