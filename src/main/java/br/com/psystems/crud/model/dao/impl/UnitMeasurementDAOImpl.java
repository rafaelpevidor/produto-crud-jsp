/**
 * 
 */
package br.com.psystems.crud.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.TransactionCallback;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.model.dao.UnitMeasurementDAO;

/**
 * @author rafael.saldanha
 *
 */
public class UnitMeasurementDAOImpl extends AbstractCrudDAO implements UnitMeasurementDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5745258755017204398L;

	public UnitMeasurementDAOImpl() throws DAOException {
		super();
	}
	
	public UnitMeasurementDAOImpl(ConnectionManager connectionManager) throws DAOException {
		super(connectionManager);
	}

	protected static final String SQL_FIND_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO " + TABLE_NAME + " (name) VALUES (?)";
	private static final String SQL_UPDATE = "UPDATE " + TABLE_NAME + " SET name = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM " + TABLE_NAME + "";
	private static final String SQL_FIND_BY_NOME = "SELECT * FROM " + TABLE_NAME + " WHERE UPPER(name) like UPPER(?)";
	
	private static Logger logger = Logger.getLogger(UnitMeasurementDAOImpl.class);
	
	@Override
	public void save(UnitMeasurement entity) throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
				
				PreparedStatement ps = getPreparedStatement(connection, SQL_INSERT);
				ps.setString(1, entity.getName());

				ps.execute();

				logger.info("Unidade de Medida inserida com sucesso!\n ".concat(entity.toString()));
			}
		});
	}

	@Override
	public UnitMeasurement update(UnitMeasurement entity) throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
				
				PreparedStatement ps = getPreparedStatement(connection, SQL_UPDATE);
				ps.setString(1, entity.getName());
				ps.setLong(2, entity.getId());

				int qtdLinhas = ps.executeUpdate();

				if (0 >= qtdLinhas) {
					logger.info("Nenhum registro alterado.");
				}

				logger.info("Unidade de Medida atualizada com sucesso!\n ".concat(entity.toString()));
			}
		});
		
		return findById(entity.getId());
	}

	@Override
	public void delete(Long id) throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
				
				PreparedStatement ps = getPreparedStatement(connection, SQL_DELETE);
				ps.setLong(1, id);

				int qtdLinhas = ps.executeUpdate();

				if (0 >= qtdLinhas) {
					throw new DAOException("Nenhum registro apagado.");
				}

				logger.info("Unidade de Medida apagada com sucesso!");
			}
		});
	}

	@Override
	public UnitMeasurement findById(Long id) throws DAOException, SystemException {

		Connection con = null;
		
		try {
			con = connectionManager.getConnection();
			
			PreparedStatement ps = getPreparedStatement(con, SQL_FIND_BY_ID);
			ps.setLong(1, id);

			return getEntity(ps.executeQuery());
			
		} catch (Exception e) {
			set(e);
			return null;
		} finally {
			connectionManager.close(con);
		}

	}

	@Override
	public List<UnitMeasurement> findByName(String nome) throws DAOException, SystemException {

		Connection con = null;
		
		try {
			con = connectionManager.getConnection();
			PreparedStatement ps = getPreparedStatement(con, SQL_FIND_BY_NOME);
			ps.setString(1, "%" + nome + "%");
			
			return getEntityList(ps.executeQuery());
			
		} catch (Exception e) {
			set(e);
			return null;
		} finally {
			connectionManager.close(con);
		}
	}

	@Override
	public List<UnitMeasurement> getAll() throws DAOException, SystemException {
		
		Connection con = null;
		
		try {
			con = connectionManager.getConnection();
			PreparedStatement ps = getPreparedStatement(con, SQL_FIND_ALL);
			
			return getEntityList(ps.executeQuery());
			
		} catch (Exception e) {
			set(e);
			return null;
		} finally {
			connectionManager.close(con);
		}
	}
	
	@Override
	protected List<UnitMeasurement> getEntityList(final ResultSet rs) throws SQLException {
		List<UnitMeasurement> units = new ArrayList<>();
		
		while (rs.next())
			units.add(createEntity(rs));
		
		return units;
	}

	@Override
	protected UnitMeasurement getEntity(final ResultSet rs) throws SQLException {
		UnitMeasurement unit = null;
		while (rs.next()) {
			unit = createEntity(rs);
		}
		return unit;
	}

	@Override
	protected UnitMeasurement createEntity(final ResultSet rs) throws SQLException {
		UnitMeasurement unit = new UnitMeasurement();
		unit.setId(rs.getLong("id"));
		unit.setName(rs.getString("name"));
		return unit;
	}

}