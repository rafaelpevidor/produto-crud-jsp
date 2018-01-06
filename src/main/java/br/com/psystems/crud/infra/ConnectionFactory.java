/**
 * 
 */
package br.com.psystems.crud.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.psystems.crud.exception.DAOException;

/**
 * @author rafael.saldanha
 *
 */
public class ConnectionFactory {

	/**
	 * @return {@link Connection}
	 * Retorna uma conexão com o banco de dados a partir do esquema passado como argumento
	 * */
	protected static Connection getConnection(String driver, String url, String host, String port, String database, String user, String password) throws DAOException {

		Connection conn = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(
					url.concat(host).concat(":").concat(port).concat("/").concat(database), 
					user, 
					password
			);
			conn.setAutoCommit(false);

			return conn;
		} catch (ClassNotFoundException e) {
			throw new DAOException("Driver (JDBC) não encontrado", e);

		} catch (SQLException e) {
			throw new DAOException("Erro ao obter a conexão", e);
		}
	}

}
