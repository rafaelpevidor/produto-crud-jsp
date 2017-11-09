/**
 * 
 */
package br.com.psystems.crud.infra;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.ConnectionFactory.EnviromentEnum;

/**
 * @author developer
 *
 */
public class ConnectionManager {
	
	private static Logger logger = Logger.getLogger(ConnectionManager.class);
	
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
		if (null == connection) {
			connection = ConnectionFactory.getConnection(getEnviroment());
		}
		return connection;
	}
	
	private EnviromentEnum getEnviroment() {
		Properties propertiesFile = new Properties();
		try {
			propertiesFile.load(new FileInputStream("/opt/product-crud-jsp/enviroment.properties"));
			return EnviromentEnum.valueOf(propertiesFile.getProperty("enviroment.name"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
