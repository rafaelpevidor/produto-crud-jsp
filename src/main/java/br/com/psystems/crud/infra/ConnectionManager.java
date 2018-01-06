/**
 * 
 */
package br.com.psystems.crud.infra;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;

/**
 * @author developer
 *
 */
public class ConnectionManager {
	
	public ConnectionManager() {}
	
	public ConnectionManager(EnviromentTypeEnum enviroment) {
		this.enviroment = enviroment;
	}

	private static Logger logger = Logger.getLogger(ConnectionManager.class);
	
	private EnviromentTypeEnum enviroment;
	
	public void doInTransaction(TransactionCallback callback) throws DAOException, SystemException {
		Connection connection = null;
		
		try {
			connection = getConnection();
			callback.execute(connection);
			connection.commit();
		} catch (Exception e) {
			logger.error("Erro ao executar operação.", e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DAOException("Desculpe, não foi possível executar esta ação. O sistema não conseguiu se recuperar.", e1);
			}
			throw new DAOException("Desculpe, não foi possível executar esta ação.");
		} finally {
			close(connection);
		}
	}
	
	public Connection getConnection() throws DAOException, SystemException {
		if (null == enviroment)
			enviroment = getEnviroment();
		return ConnectionPool.getInstance().getConnection(enviroment);
	}
	
	private EnviromentTypeEnum getEnviroment() {
		Properties propertiesFile = new Properties();
		try {
			propertiesFile.load(new FileInputStream("/opt/product-crud-jsp/enviroment.properties"));//FIXME rever o local do arquivo
			return EnviromentTypeEnum.valueOf(propertiesFile.getProperty("enviroment.type"));
		} catch (IOException e) {
			logger.error("Arquivo de configuração não encontrado.");
		}
		return null;
	}

	public void close(Connection connection) throws DAOException, SystemException {
		ConnectionPool.getInstance().releaseConnection(connection);
	}
	
}
