/**
 * 
 */
package br.com.psystems.crud.model.dao.impl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.TransactionCallback;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.model.dao.ProductDAO;

/**
 * @author rafael.saldanha
 *
 */
public class ProductDAOImpl extends AbstractDAO implements ProductDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1367862676478841213L;

	public ProductDAOImpl() throws DAOException {
		super();
	}
	
	public ProductDAOImpl(ConnectionManager connectionManager) throws DAOException {
		super(connectionManager);
	}

	private static final String SQL_INSERT = "INSERT INTO " + TABLE_NAME + " (unit_id, name, mininum_quantity, tags, reference, own_manufacturing, description) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE " + TABLE_NAME + " SET unit_id = ?, name = ?, mininum_quantity = ?, tags = ?, reference = ?, own_manufacturing = ?, description = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM " + TABLE_NAME + "";
	private static final String SQL_FIND_BY_NAME = "SELECT * FROM " + TABLE_NAME + " WHERE name like ?";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

	private static Logger logger = Logger.getLogger(ProductDAOImpl.class);

	@Override
	public void save(Product entity) throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {

			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {

				PreparedStatement ps = getPreparedStatement(connection, SQL_INSERT);
				ps.setLong(1, entity.getUnitMeasurementId());
				ps.setString(2, entity.getName());
				ps.setBigDecimal(3, entity.getMininumQuantity());
				ps.setArray(4, getTagsAsSQLArray(entity, connection));
				ps.setArray(5, getReferencesAsSQLArray(entity, connection));
				ps.setBoolean(6, entity.getOwnManufacturing());
				ps.setString(7, entity.getDescription());

				ps.execute();
				ps.close();
				logger.info("Produto inserido com sucesso!\n ".concat(entity.toString()));
			}

		});
	}
	
	@Override
	public Product update(Product entity) throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {

			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {

				PreparedStatement ps = getPreparedStatement(connection, SQL_UPDATE);
				ps.setLong(1, entity.getUnitMeasurementId());
				ps.setString(2, entity.getName());
				ps.setBigDecimal(3, entity.getMininumQuantity());
				ps.setArray(4, getTagsAsSQLArray(entity, connection));
				ps.setArray(5, getReferencesAsSQLArray(entity, connection));
				ps.setBoolean(6, entity.getOwnManufacturing());
				ps.setString(7, entity.getDescription());
				ps.setLong(8, entity.getId());

				int qtdLinhas = ps.executeUpdate();
				ps.close();

				if (0 >= qtdLinhas) {
					logger.info("Nenhum registro alterado.");
				} else {
					logger.info("Produto atualizado com sucesso!\n ".concat(entity.toString()));
				}
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
				ps.close();

				if (0 >= qtdLinhas) {
					logger.info("Nenhum registro apagado.");
				} else {
					logger.info("Produto apagado com sucesso!");
				}

			}
		});
	}

	@Override
	public Product findById(Long id) throws DAOException, SystemException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product entity = null;
		
		try {
			con = connectionManager.getConnection();
			
			ps = getPreparedStatement(con, SQL_FIND_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			
			entity = getEntity(rs);
			
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			set(e);
		} finally {
			connectionManager.close(con);
		}
		
		return entity;
	}

	@Override
	public List<Product> findByName(String name) throws DAOException, SystemException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> entityList = new ArrayList<>();
		
		try {
			con = connectionManager.getConnection();

			ps = getPreparedStatement(con, SQL_FIND_BY_NAME);
			ps.setString(1, "%"+name+"%");
			rs = ps.executeQuery();
			
			entityList = getEntityList(rs);
			
		} catch (Exception e) {
			set(e);
		} finally {
			connectionManager.close(con);
		}
	
		return entityList;
	}

	@Override
	public List<Product> getAll() throws DAOException, SystemException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> entityList = new ArrayList<>();
		
		try {
			con = connectionManager.getConnection();
			
			ps = getPreparedStatement(con, SQL_FIND_ALL);
			rs = ps.executeQuery();
			
			entityList = getEntityList(rs);
			
		} catch (Exception e) {
			set(e);
		} finally {
			connectionManager.close(con);
		}
		return entityList;
	}

	@Override
	protected Product getEntity(final ResultSet rs) throws SQLException {

		Product product = null;

		while (rs.next()) {
			product = createEntity(rs);
		}

		return product;
	}
	
	@Override
	protected List<Product> getEntityList(final ResultSet rs) throws SQLException {
		List<Product> products = new ArrayList<>();
		
		while (rs.next()) {
			products.add(createEntity(rs));
		}
		
		return products;
	}

	@Override
	protected Product createEntity(final ResultSet rs) throws SQLException {
		
		Product product = new Product();
		product.setDescription(rs.getString("description"));
		product.setId(rs.getLong("id"));
		product.setName(rs.getString("name"));
		product.setMininumQuantity(rs.getBigDecimal("mininum_quantity"));
		product.setTags(getTags(rs));
		product.setReferences(getReferences(rs));
		product.setOwnManufacturing(rs.getBoolean("own_manufacturing"));
		Long unitId = rs.getLong("unit_id");
		product.setUnitMeasurement(new UnitMeasurement(unitId, null));
		product.setUnitMeasurementId(unitId);
		return product;
	}
	
	private List<String> getTags(final ResultSet rs) throws SQLException {
		String[] tagsStrArray = (String[]) rs.getArray("tags").getArray();
		return Arrays.asList(tagsStrArray);
	}
	
	private List<Long> getReferences(final ResultSet rs) throws SQLException {
		Long[] referencesLongArray = (Long[]) rs.getArray("reference").getArray();
		return Arrays.asList(referencesLongArray);
	}

	private Array getTagsAsSQLArray(final Product entity, final Connection connection) throws SQLException {
		return connection.createArrayOf("varchar", entity.getTags().toArray());
	}
	
	private Array getReferencesAsSQLArray(final Product entity, final Connection connection) throws SQLException {
		return connection.createArrayOf("bigint", entity.getReferences().toArray());
	}

}