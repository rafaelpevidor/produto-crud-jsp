package br.com.psystems.crud.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;

public abstract class AbstractDAO {

	public AbstractDAO() throws DAOException {
		this.connectionManager = new ConnectionManager();
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
}