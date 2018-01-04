/**
 * 
 */
package br.com.psystems.crud.infra;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;

/**
 * @author developer
 *
 */
public class ConnectionPool {
	
	private ConnectionPool(){};
	
	private static Logger logger = Logger.getLogger(ConnectionPool.class);
	
	protected static ConnectionPool getInstance(EnviromentTypeEnum enviroment) {
		if (instance == null) {
			instance = new ConnectionPool();
			loadConnectionProperties(enviroment);
			freeConnections = new ArrayBlockingQueue<Connection>(maxConnections, true);
			connectionsInUse = new HashMap<>();
		} else if (!ConnectionPool.enviroment.equals(enviroment)) {
			loadConnectionProperties(enviroment);
		}
		return instance;
	}

	private static ConnectionPool instance;
	private static Integer maxConnections;
	private static ArrayBlockingQueue<Connection> freeConnections;
	private static HashMap<String, Connection> connectionsInUse;
	private static EnviromentTypeEnum enviroment;
	private static ResourceBundle connectionProperties;
	
	protected static void loadConnectionProperties(EnviromentTypeEnum enviroment) {
		ConnectionPool.connectionProperties = ResourceBundle.getBundle("META-INF/enviroment.properties");
		Integer amountOfMaxConnections = Integer.parseInt(connectionProperties.getString(enviroment.getMaxConnections()));
		Integer maxConnections = null;
		if (
				(null == ConnectionPool.enviroment)||
				(
						(null != (maxConnections = Integer.parseInt(ConnectionPool.enviroment.getMaxConnections())) && 
						amountOfMaxConnections > maxConnections)
				)
		) {
			ConnectionPool.maxConnections = amountOfMaxConnections;
		}
		ConnectionPool.enviroment = enviroment;
	}

	protected Connection getConnection() throws DAOException {
		Connection con = null;

		try {
			if (connectionsInUse.size() < maxConnections) {
				con = freeConnections.poll();
				if(con == null)
					con = ConnectionFactory.getConnection(
							connectionProperties.getString(enviroment.getDriver()), 
							connectionProperties.getString(enviroment.getUrl()),
							connectionProperties.getString(enviroment.getHost()),
							connectionProperties.getString(enviroment.getPort()),
							connectionProperties.getString(enviroment.getDatabase()), 
							connectionProperties.getString(enviroment.getUser()), 
							connectionProperties.getString(enviroment.getPassword()));
				else if (con.isClosed()) {
					this.getConnection();
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
