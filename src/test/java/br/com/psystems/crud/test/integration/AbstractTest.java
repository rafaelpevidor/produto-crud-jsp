/**
 * 
 */
package br.com.psystems.crud.test.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.EnviromentTypeEnum;
import br.com.psystems.crud.model.BaseEntity;

/**
 * @author developer
 *
 */
public abstract class AbstractTest<T extends BaseEntity> {
	
	protected static final long ALIAS = Calendar.getInstance().getTimeInMillis();
	
	protected abstract T getEntity() throws DAOException, SystemException, SQLException;
	
	public void truncate(String tableName) throws SQLException, DAOException, SystemException {
		truncate(tableName, false);
	}
	
	public void truncateCascade(String tableName) throws SQLException, DAOException, SystemException {
		truncate(tableName, true);
	}

	private void truncate(String tableName, boolean cascade) throws SQLException, DAOException, SystemException {
		ConnectionManager connectionManager = new ConnectionManager(EnviromentTypeEnum.TEST);
		Connection con = connectionManager.getConnection();
		
		PreparedStatement ps = con.prepareStatement("TRUNCATE TABLE "
				.concat(tableName)
				.concat(cascade?" CASCADE":""));
		ps.execute();
		
		con.commit();
		connectionManager.close(con);
	}
	
	protected Integer getTotalRecodsFrom(String table) throws SQLException, DAOException, SystemException {
		Connection con = new ConnectionManager(EnviromentTypeEnum.TEST).getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM tb_user");
		ResultSet rs = ps.executeQuery();
		rs.next();
		con.commit();
		con.close();
		return rs.getInt(1);
	}
	
	protected Long getLastIdFrom(String table) throws SQLException, DAOException, SystemException {

		Connection con = new ConnectionManager(EnviromentTypeEnum.TEST).getConnection();
		PreparedStatement ps = con.prepareStatement("select nextval(pg_get_serial_sequence('" +table + "', 'id')) as new_id");
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Long lastId = rs.getLong(1);

		rs.close();
		ps.close();
		con.close();

		return --lastId;
	}
	
}
