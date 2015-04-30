/**
 * 
 */
package br.com.psystems.crud.model.dao;

import java.io.Serializable;
import java.util.List;

import br.com.psystems.crud.exception.PersistenciaException;
import br.com.psystems.crud.model.domain.EntidadeBase;

/**
 * @author rafael.saldanha
 *
 */
public interface DAO <T extends EntidadeBase> {

	public void salvar(T entidade) throws PersistenciaException;
	public T atualizar(T entidade) throws PersistenciaException;
	public void apagar(T entidade) throws PersistenciaException;
	public T obterPorId(Serializable id) throws PersistenciaException;
	public List<T> obterPorNome(String nome) throws PersistenciaException;
	public List<T> obterTodos() throws PersistenciaException;
	
}
