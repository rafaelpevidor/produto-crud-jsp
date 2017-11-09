/**
 * 
 */
package br.com.psystems.crud.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.TransactionCallback;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.model.Product;

/**
 * @author rafael.saldanha
 *
 */
public class ProductDAO extends AbstractDAO<Product> {
	
	

	public ProductDAO() throws DAOException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final String SQL_INSERT = "INSERT INTO tb_product (vendor_id, name, color, reference, lot, amount, cost, price, description) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE tb_product SET vendor_id = ?, nmae = ?, color = ?, reference = ?, lot = ?, amount = ?, cost = ?, price = ?, description = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM tb_product WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM tb_product";
	private static final String SQL_FIND_BY_NAME = "SELECT * FROM tb_product WHERE name like ?";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM tb_product WHERE id = ?";
	protected static final String SQL_FIND_BY_FORNECEDOR = "SELECT * FROM tb_product WHERE vendor_id = ?";
	
	private static Logger logger = Logger.getLogger(VendorDAO.class);
	
//	private VendorDAO vendorDAO;
	
	@Override
	public void save(Product entidade) throws DAOException, SystemException {
		
		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
				
				PreparedStatement ps = getPreparedStatement(connection, SQL_INSERT);
				ps.setLong(1, entidade.getVendorId());
				ps.setString(2, entidade.getName());
				ps.setString(3, entidade.getDescription());
				ps.setBigDecimal(4, entidade.getCost());
				ps.setBigDecimal(5, entidade.getPrice());
				ps.setBigDecimal(6, entidade.getAmount());
				ps.setString(7, entidade.getLot());
				
				ps.executeUpdate();
				
				logger.info("Produto inserido com sucesso!\n ".concat(entidade.toString()));
			}
		});
	}

	@Override
	public Product update(Product entidade) throws DAOException, SystemException {
		
		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
				
				PreparedStatement ps = null;
				ps = getPreparedStatement(connection, SQL_UPDATE);
				ps.setLong(1, entidade.getVendorId());
				ps.setString(2, entidade.getName());
				ps.setString(3, entidade.getDescription());
				ps.setBigDecimal(4, entidade.getCost());
				ps.setBigDecimal(5, entidade.getPrice());
				ps.setBigDecimal(6, entidade.getAmount());
				ps.setString(7, entidade.getLot());
				ps.setLong(8, entidade.getId());

				int qtdLinhas = ps.executeUpdate();

				if (0 <= qtdLinhas) {
					logger.info("Nenhum registro alterado.");
				} else {
					logger.info("Produto atualizado com sucesso!\n ".concat(entidade.toString()));
				}
			}
		});
		return findById(entidade.getId());
	}

	@Override
	public void delete(Long id) throws DAOException, SystemException {
		
		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
			
				PreparedStatement ps = null;
				ps = getPreparedStatement(connection, SQL_DELETE);
				ps.setLong(0, id);

				int qtdLinhas = ps.executeUpdate();

				if (0 <= qtdLinhas) {
					throw new DAOException("Nenhum registro apagado.");
				} else {
					logger.info("Fornecedor apagado com sucesso!");
				}

			}
		});
	}

	@Override
	public Product findById(Long id) throws DAOException, SystemException {
		
		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
				
				PreparedStatement ps = null;
				ResultSet rs = null;
				Product produto = null;
				
				ps = getPreparedStatement(connection, SQL_FIND_BY_ID);
				ps.setLong(0, id);

				rs = ps.executeQuery();

				produto = getProduto(rs);

				logger.info("Produto recuperado com sucesso!\n ".concat(produto.toString()));
			}
		});
		
		return null;
	}

	@Override
	public List<Product> findByName(String name) throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
				
				List<Product> produtos = new ArrayList<Product>();
				PreparedStatement ps = null;
				ResultSet rs = null;
				ps = getPreparedStatement(connection, SQL_FIND_BY_NAME);
				ps.setString(1, "%"+name);
				
				rs = ps.executeQuery();

				while (rs.next()) {
					produtos.add(getProduto(rs));
				}
			}
		});
		
		return null;
	}

	@Override
	public List<Product> getAll() throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
				
				List<Product> produtos = new ArrayList<Product>();
				PreparedStatement ps = null;
				ResultSet rs = null;
				ps = getPreparedStatement(connection, SQL_FIND_ALL);
				rs = ps.executeQuery();

				while (rs.next()) {
					produtos.add(getProduto(rs));
				}
			}
		});
		
		return null;
	}

	private Product getProduto(ResultSet rs) throws SQLException, DAOException {
		
		Product produto = new Product();
		produto.setDescription(rs.getString("description"));
		produto.setVendorId(rs.getLong("vendor_id"));
		produto.setId(rs.getLong("id"));
		produto.setLot(rs.getString("lot"));
		produto.setName(rs.getString("name"));
		produto.setAmount(rs.getBigDecimal("amount"));
		produto.setPrice(rs.getBigDecimal("price"));
		produto.setCost(rs.getBigDecimal("cost"));
		
		return produto;
	}
	
//    protected Product getProductWithVendor(ResultSet rs) throws SQLException, DAOException {
//		
//		Product produto = new Product();
//		produto.setDescription(rs.getString("description"));
//		produto.setVendorId(rs.getLong("vendor_id"));
//		if (null != vendorDAO)
//			produto.setVendor(vendorDAO.findById(produto.getVendorId()));
//		produto.setId(rs.getLong("id"));
//		produto.setLot(rs.getString("lot"));
//		produto.setName(rs.getString("name"));
//		produto.setAmount(rs.getDouble("amount"));
//		produto.setPrice(rs.getDouble("price"));
//		produto.setCost(rs.getDouble("cost"));
//		
//		return produto;
//	}
}