/**
 * 
 */
package br.com.psystems.crud.infra;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.ConnectionFactory.EnviromentEnum;
import br.com.psystems.crud.infra.exception.DAOException;

/**
 * @author developer
 *
 */
public class ConnectionPool {
	
	private ConnectionPool(){};
	
	private static Logger logger = Logger.getLogger(ConnectionPool.class);
	
	protected static ConnectionPool getInstance(EnviromentEnum enviromentObj) {
		if (instance == null) {
			instance = new ConnectionPool();
			enviroment = enviromentObj;
			maxConnections = enviroment.getMaxConnections();
			freeConnections = new ArrayBlockingQueue<Connection>(maxConnections, true);
			connectionsInUse = new HashMap<>();
		}
		return instance;
	}

	private static ConnectionPool instance;
	private static Integer maxConnections;
	private static ArrayBlockingQueue<Connection> freeConnections;
	private static HashMap<String, Connection> connectionsInUse;
	private static EnviromentEnum enviroment;

	protected Connection getConnection() throws DAOException {
		Connection con = null;

		try {
			if (connectionsInUse.size() < maxConnections) {
				con = freeConnections.poll();
				if(con == null)
					con = ConnectionFactory.getConnection(enviroment);
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
