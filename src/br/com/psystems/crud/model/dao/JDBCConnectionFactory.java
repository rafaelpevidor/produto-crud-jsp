/**
 * 
 */
package br.com.psystems.crud.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.PersistenceException;

/**
 * @author rafael.saldanha
 *
 */
public class JDBCConnectionFactory {

	//Informacões para conexão com banco de dados PostgreSQL.
	private static final String STR_DRIVER = "org.postgresql.Driver";
	private static final String DATABASE = "crud";
	private static final String STR_CON = "jdbc:postgresql://localhost:5432/" + DATABASE;
	private static final String USER = "psystems";
	private static final String PASSWORD = "p5yst3ms@4ppl1ktion";
	
	private static Logger logger = Logger.getLogger(JDBCConnectionFactory.class);

	public static Connection getConnection() throws PersistenceException {
		
		Connection conn = null;
		String mensagem =  "Conexão com o banco de dados obtida com sucesso.";
		
		try {
			
			Class.forName(STR_DRIVER);
			conn = DriverManager.getConnection(STR_CON, USER, PASSWORD);
			
			logger.info(mensagem);
			
			return conn;
		} catch (ClassNotFoundException e) {
			
			mensagem = "Driver (JDBC) não encontrado";
			logger.error(mensagem, e);
			throw new PersistenceException(mensagem, e);
			
		} catch (SQLException e) {
			
			mensagem = "Erro ao obter a conexão";
			throw new PersistenceException(mensagem, e);
		}
	}
	
	/*
	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/crud", "psystems", "p5yst3ms@4ppl1ktion");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	 * */
	
	public static void close(Connection connection) throws PersistenceException {
		
		String mensagem =  "Conexão com o banco de dados fechada com sucesso.";
		
		try {
			if (null != connection) {
				connection.close();
				logger.info(mensagem);
			}
		} catch (Exception e) {
			mensagem = "Houve um erro ao fechar a conexão com o banco de dados.";
			logger.error(mensagem,e);
			throw new PersistenceException(mensagem, e);
		}
	}
	
	public static void close(Connection connection, Statement statement) throws PersistenceException {
		
		String mensagem =  "Instrução de banco de dados (stmt) fechada com sucesso.";
		
		try {
			if (null != statement) {
				statement.close();
				logger.info(mensagem);
			}
		} catch (Exception e) {
			mensagem = "Houve um erro ao fechar a instrução de banco de dados.";
			logger.error(mensagem, e);
			throw new PersistenceException(mensagem, e);
		}
		
		if (null != connection) {
			close(connection);
		}
	}
	
	public static void close(Connection connection, Statement statement, ResultSet resultset) throws PersistenceException {
		
		String mensagem =  "Conjunto de resultados (rs) do banco de dados fechado com sucesso.";
		
		try {
			if (null != resultset) {
				resultset.close();
				logger.info(mensagem);
			}
		} catch (Exception e) {
			mensagem = "Houve um erro ao fechar o conjunto de resultados do banco de dados.";
			logger.error(mensagem, e);
			throw new PersistenceException(mensagem, e);
		}
		
		close(connection, statement);
	}
}
