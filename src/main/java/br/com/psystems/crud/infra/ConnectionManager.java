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

import br.com.psystems.crud.infra.ConnectionFactory.EnviromentEnum;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;

/**
 * @author developer
 *
 */
public class ConnectionManager {
	
	public ConnectionManager() {}
	
	public ConnectionManager(EnviromentEnum enviroment) {
		this.enviroment = enviroment;
	}

	private static Logger logger = Logger.getLogger(ConnectionManager.class);
	
	private EnviromentEnum enviroment;
	
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
				throw new DAOException(e1);
			}
		} finally {
			close(connection);
		}
	}
	
	public Connection getConnection() throws DAOException {
		if (null == enviroment)
			enviroment = getEnviroment();
		return ConnectionFactory.getConnection(enviroment);
	}
	
	private EnviromentEnum getEnviroment() {
		Properties propertiesFile = new Properties();
		try {
			propertiesFile.load(new FileInputStream("/opt/product-crud-jsp/enviroment.properties"));//FIXME rever o local do arquivo
			return EnviromentEnum.valueOf(propertiesFile.getProperty("enviroment.name"));
		} catch (IOException e) {
			logger.error("Arquivo de configuração não encontrado.");
		}
		return null;
	}

	public void close(Connection connection) throws DAOException {

		try {
			if (null != connection && !connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			logger.error("Houve um erro ao fechar a conexão com o banco de dados.",e);
			throw new DAOException(e);
		}
	}
	
}
