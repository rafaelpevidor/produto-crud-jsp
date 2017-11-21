/**
 * 
 */
package br.com.psystems.crud.test.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.infra.ConnectionFactory;
import br.com.psystems.crud.infra.ConnectionFactory.EnviromentEnum;

/**
 * @author developer
 *
 */
public abstract class AbstractTest {
	
	private static Logger logger = Logger.getLogger(AbstractTest.class);
	protected static final long ALIAS = Calendar.getInstance().getTimeInMillis();

	public void truncate(String tableName, boolean cascade) throws SQLException, DAOException {
		Connection con = ConnectionFactory.getConnection(EnviromentEnum.HML);
		
		PreparedStatement ps = con.prepareStatement("TRUNCATE TABLE "
				.concat(tableName)
				.concat(cascade?" CASCADE":""));
		ps.execute();
		
		con.commit();
		ps.close();
		con.close();
	}
	
	protected Integer getTotalRecodsFrom(String table) throws SQLException, DAOException {
		Connection con = ConnectionFactory.getConnection(EnviromentEnum.HML);
		PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM tb_user");
		ResultSet rs = ps.executeQuery();
		rs.next();
		con.commit();
		con.close();
		return rs.getInt(1);
	}
	
	protected Long getLastIdFrom(String table) throws SQLException, DAOException {

		Connection con = ConnectionFactory.getConnection(EnviromentEnum.HML);
		PreparedStatement ps = con.prepareStatement("select nextval(pg_get_serial_sequence('" +table + "', 'id')) as new_id");
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Long lastId = rs.getLong(1);

		rs.close();
		ps.close();
		con.close();

		logger.info("Conexão com o banbo de dados fechada com sucesso.");

		return --lastId;
	}
	
}
