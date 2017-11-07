/**
 * 
 */
package br.com.psystems.crud.infra;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;

/**
 * @author developer
 *
 */
public class ConnectionManager {
	
	private static Logger logger = Logger.getLogger(ConnectionManager.class);
	
	public ConnectionManager(Connection connection) {
		this.connection = connection;
	}

	private Connection connection;
	
	public void doInTransaction(TransactionCallback callback) throws DAOException, SystemException {
		try {
			callback.execute(connection);
			connection.commit();
		} catch (Exception e) {
			logger.error("Erro ao executar operação.", e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
		} finally {
			closeConnection();
		}
	}
	
	public Connection getConnection() throws DAOException {
		return connection;
	}
	
	public void closeConnection() throws DAOException {

		try {
			if (null != connection) {
				connection.close();
				logger.info("Conexão com o banco de dados fechada com sucesso.");
			}
		} catch (Exception e) {
			logger.error("Houve um erro ao fechar a conexão com o banco de dados.",e);
			throw new DAOException(e);
		}
	
	}
	
	protected class ManagedConnection {
		 
		private Connection connection;
		private boolean inUse;
		
		public Connection getConnection() {
			return connection;
		}
		
		public void setConnection(Connection connection) {
			this.connection = connection;
		}
		
		public boolean isInUse() {
			return inUse;
		}
		
		public void setInUse(boolean inUse) {
			this.inUse = inUse;
		}
	}
}
