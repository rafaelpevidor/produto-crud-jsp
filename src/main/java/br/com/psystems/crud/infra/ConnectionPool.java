/**
 * 
 */
package br.com.psystems.crud.infra;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;

/**
 * @author developer
 *
 */
public class ConnectionPool {
	
	private ConnectionPool(){};
	
	private static Logger logger = Logger.getLogger(ConnectionPool.class);
	
	protected static ConnectionPool getInstance() throws SystemException {
		if (instance == null)
			instance = new ConnectionPool();
		return instance;
	}

	private static ConnectionPool instance;
	private Integer maxConnections;
	private ArrayBlockingQueue<Connection> freeConnections;
	private HashMap<String, Connection> connectionsInUse = new HashMap<>();
	private EnviromentTypeEnum enviroment;
	private Properties connectionProperties = new Properties();
	
	protected void loadConnectionProperties(EnviromentTypeEnum enviroment) throws SystemException {
		try {
			InputStream in = getClass().getResourceAsStream("/META-INF/enviroment.properties");
			this.connectionProperties.load(in);
	        in.close();	
			Integer maxConnections = Integer.parseInt(connectionProperties.getProperty(enviroment.getMaxConnections()));
			this.enviroment = enviroment;
			this.maxConnections = maxConnections;
			this.freeConnections = new ArrayBlockingQueue<Connection>(maxConnections, true);
		} catch (Exception e) {
			throw new SystemException("Erro ao carregar as configurações de banco de dados.", e);
		}
	}

	protected Connection getConnection(EnviromentTypeEnum enviroment) throws DAOException, SystemException {
		Connection con = null;
		
		if (null == this.enviroment) {
			loadConnectionProperties(enviroment);
		}
		
		try {
			if (connectionsInUse.size() < maxConnections) {
				con = freeConnections.poll();
				if(con == null)
					con = ConnectionFactory.getConnection(
							connectionProperties.getProperty(enviroment.getDriver()), 
							connectionProperties.getProperty(enviroment.getUrl()),
							connectionProperties.getProperty(enviroment.getHost()),
							connectionProperties.getProperty(enviroment.getPort()),
							connectionProperties.getProperty(enviroment.getDatabase()), 
							connectionProperties.getProperty(enviroment.getUser()), 
							connectionProperties.getProperty(enviroment.getPassword()));
				else if (con.isClosed()) {
					this.getConnection(enviroment);
				}
				connectionsInUse.put(con.toString(), con);
			}
		} catch (SQLException e) {
			logger.error("Falha ao obter conexões do pool.", e);
			throw new DAOException("Falha ao obter conexões do pool.", e);
		}
		return con;
	}
	
	protected void releaseConnection(Connection connection) {
		freeConnections.add(connection);
		connectionsInUse.remove(connection.toString());
	}
}
