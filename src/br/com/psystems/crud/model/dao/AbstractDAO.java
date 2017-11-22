package br.com.psystems.crud.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.BaseEntity;

abstract class AbstractDAO <T extends BaseEntity> {

	public AbstractDAO() throws DAOException {
		this.connectionManager = new ConnectionManager();
	}
	
	public abstract void save(T entity) throws DAOException, SystemException;
	public abstract T update(T entity) throws DAOException, SystemException;
	public abstract void delete(Long id) throws DAOException, SystemException;
	public abstract T findById(Long id) throws DAOException, SystemException;
	public abstract List<T> findByName(String nome) throws DAOException, SystemException;
	public abstract List<T> getAll() throws DAOException, SystemException;
	
	protected ConnectionManager connectionManager;
	
	public PreparedStatement getPreparedStatement(Connection connection, String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}
	
	public void set(Exception exception) throws DAOException, SystemException {
		exception.printStackTrace();
		if (exception instanceof SQLException)
			throw new DAOException(exception);
		else
			throw new SystemException(exception);
	}
}