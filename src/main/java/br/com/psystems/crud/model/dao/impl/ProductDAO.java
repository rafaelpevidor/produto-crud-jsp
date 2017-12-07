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

import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.TransactionCallback;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.dao.BaseDAO;

/**
 * @author rafael.saldanha
 *
 */
public class ProductDAO extends AbstractDAO<Product> implements BaseDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1367862676478841213L;

	public ProductDAO() throws DAOException {
		super();
	}
	
	public ProductDAO(ConnectionManager connectionManager) throws DAOException {
		super(connectionManager);
	}

	public static final String TABLE_NAME = "tb_product";
	private static final String SQL_INSERT = "INSERT INTO " + TABLE_NAME + " (name, mininum_quantity, tags, reference, own_manufacturing, description) VALUES (?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE " + TABLE_NAME + " SET name = ?, mininum_quantity = ?, tags = ?, reference = ?, own_manufacturing = ?, description = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM " + TABLE_NAME + "";
	private static final String SQL_FIND_BY_NAME = "SELECT * FROM " + TABLE_NAME + " WHERE name like ?";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

	private static Logger logger = Logger.getLogger(ProductDAO.class);

	@Override
	public void save(Product entity) throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {

			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {

				PreparedStatement ps = getPreparedStatement(connection, SQL_INSERT);
				ps.setString(1, entity.getName());
				ps.setBigDecimal(2, entity.getMininumQuantity());
				ps.setArray(3, getTagsAsSQLArray(entity, connection));
				ps.setArray(4, getReferencesAsSQLArray(entity, connection));
				ps.setBoolean(5, entity.getOwnManufacturing());
				ps.setString(6, entity.getDescription());

				ps.execute();

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
				
				ps.setString(1, entity.getName());
				ps.setBigDecimal(2, entity.getMininumQuantity());
				ps.setArray(3, getTagsAsSQLArray(entity, connection));
				ps.setArray(4, getReferencesAsSQLArray(entity, connection));
				ps.setBoolean(5, entity.getOwnManufacturing());
				ps.setString(6, entity.getDescription());
				ps.setLong(7, entity.getId());

				int qtdLinhas = ps.executeUpdate();

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
	public List<Product> findByName(String name) throws DAOException, SystemException {

		Connection con = null;
		
		try {
			con = connectionManager.getConnection();

			PreparedStatement ps = getPreparedStatement(con, SQL_FIND_BY_NAME);
			ps.setString(1, "%"+name+"%");
			
			return getEntityList(ps.executeQuery());
			
		} catch (Exception e) {
			set(e);
			return null;
		} finally {
			connectionManager.close(con);
		}
	
	}

	@Override
	public List<Product> getAll() throws DAOException, SystemException {

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