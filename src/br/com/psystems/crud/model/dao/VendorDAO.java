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
import br.com.psystems.crud.model.Vendor;

/**
 * @author rafael.saldanha
 *
 */
public class VendorDAO extends AbstractDAO<Vendor> {


	public VendorDAO() throws DAOException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final String SQL_INSERT = "INSERT INTO tb_vendor (name, description) VALUES (?,?)";
	private static final String SQL_UPDATE = "UPDATE tb_vendor SET name = ?, description = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM tb_vendor WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM tb_vendor";
	private static final String SQL_FIND_BY_NOME = "SELECT * FROM tb_vendor WHERE name like ?";
	protected static final String SQL_FIND_BY_ID = "SELECT * FROM tb_vendor WHERE id = ?";
	
	private static Logger logger = Logger.getLogger(VendorDAO.class);
	
	@Override
	public void save(Vendor entity) throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
				
				PreparedStatement ps = null;
				ps = getPreparedStatement(connection, SQL_INSERT);
				ps.setString(0, entity.getName());
				ps.setString(1, entity.getDescription());

				ps.executeUpdate();

				logger.info("Fornecedor inserido com sucesso!\n ".concat(entity.toString()));
			}
		});
	}

	@Override
	public Vendor update(Vendor entity) throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
				
				PreparedStatement ps = null;
				ps = getPreparedStatement(connection, SQL_UPDATE);
				ps.setString(0, entity.getName());
				ps.setString(1, entity.getDescription());
				ps.setLong(2, entity.getId());

				int qtdLinhas = ps.executeUpdate();

				if (0 <= qtdLinhas) {
					logger.info("Nenhum registro alterado.");
				}

				logger.info("Fornecedor atualizado com sucesso!\n ".concat(entity.toString()));
			}
		});
		
		return entity;
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
				}

				logger.info("Fornecedor apagado com sucesso!");
			}
		});
	}

	@Override
	public Vendor findById(Long id) throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
				
				ResultSet rs = null;
				Vendor fornecedor = null;

				PreparedStatement ps = null;
				ps = getPreparedStatement(connection, SQL_FIND_BY_ID);
				ps.setLong(0, id);

				rs = ps.executeQuery();

				fornecedor = getFornecedor(rs);

				logger.info("Fornecedor recuperado com sucesso!\n ".concat(fornecedor.toString()));
			}
		});

		return null;
	}

	@Override
	public List<Vendor> findByName(String nome) throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
				
				List<Vendor> fornecedores = new ArrayList<Vendor>();
				
				PreparedStatement ps = null;
				ps = getPreparedStatement(connection, SQL_FIND_BY_NOME);
				ps.setString(0, "%"+nome);
				
				ResultSet rs = null;
				rs = ps.executeQuery();

				while (rs.next()) {
					fornecedores.add(getFornecedor(rs));
				}

			}
		});

		return null;
	}

	@Override
	public List<Vendor> getAll() throws DAOException, SystemException {
		
		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException, DAOException, SystemException {
				
				List<Vendor> fornecedores = new ArrayList<Vendor>();
				
				PreparedStatement ps = null;
				ps = getPreparedStatement(connection, SQL_FIND_ALL);
				
				ResultSet rs = null;
				rs = ps.executeQuery();

				while (rs.next()) {
					fornecedores.add(getFornecedor(rs));
				}

			}
		});

		return null;
	}

	private Vendor getFornecedor(ResultSet rs) throws SQLException {

		Vendor fornecedor = new Vendor();
		fornecedor.setId(rs.getLong("id"));
		fornecedor.setName(rs.getString("name"));
		fornecedor.setDescription(rs.getString("description"));

		return fornecedor;
	}

}