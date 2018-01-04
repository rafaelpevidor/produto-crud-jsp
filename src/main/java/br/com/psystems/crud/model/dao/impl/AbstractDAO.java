package br.com.psystems.crud.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.model.BaseEntity;

public abstract class AbstractDAO {

	public AbstractDAO() throws DAOException {
		this.connectionManager = new ConnectionManager();
	}
	
	public AbstractDAO(ConnectionManager connectionManager) throws DAOException {
		this.connectionManager = connectionManager;
	}
	
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
	
	protected abstract List<? extends BaseEntity> getEntityList(final ResultSet rs) throws SQLException;
	protected abstract BaseEntity getEntity(final ResultSet rs) throws SQLException, DAOException;
	protected abstract BaseEntity createEntity(final ResultSet rs) throws SQLException;
}