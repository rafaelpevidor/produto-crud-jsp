/**
 * 
 */
package br.com.psystems.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.psystems.crud.base.BaseDAO;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.infra.ConnectionFactory;
import br.com.psystems.crud.model.Fornecedor;

/**
 * @author rafael.saldanha
 *
 */
public class FornecedorDAO implements BaseDAO<Fornecedor> {

	public FornecedorDAO(Connection connection) {
		this.connection = connection;
	}

	protected FornecedorDAO() {}

	private static final String SQL_INSERT = "INSERT INTO fornecedor (nome, descricao) VALUES (?,?)";
	private static final String SQL_UPDATE = "UPDATE fornecedor SET nome = ?, descricao = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM fornecedor WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM fornecedor";
	private static final String SQL_FIND_BY_NOME = "SELECT * FROM fornecedor WHERE nome like ?";
	protected static final String SQL_FIND_BY_ID = "SELECT * FROM fornecedor WHERE id = ?";
	
	private static Logger logger = Logger.getLogger(FornecedorDAO.class);
	
	private Connection connection;

	@Override
	public void save(Fornecedor entity) throws DAOException {

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(SQL_INSERT);
			ps.setString(0, entity.getNome());
			ps.setString(1, entity.getDescricao());

			ps.executeUpdate();

			logger.info("Fornecedor inserido com sucesso!\n ".concat(entity.toString()));
		} catch (SQLException e) {
			logger.error("Erro ao inserir fornecedor.", e);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnectionAndStatement(connection, ps);
		}
	}

	@Override
	public Fornecedor update(Fornecedor entity) throws DAOException {

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(SQL_UPDATE);
			ps.setString(0, entity.getNome());
			ps.setString(1, entity.getDescricao());
			ps.setLong(2, entity.getId());

			int qtdLinhas = ps.executeUpdate();

			if (0 <= qtdLinhas) {
//				logger.info("Nenhum registro alterado.");
				throw new DAOException("Nenhum registro alterado.");
			}

			logger.info("Fornecedor atualizado com sucesso!\n ".concat(entity.toString()));
		} catch (SQLException e) {
			logger.error("Erro ao atualizar fornecedor.", e);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnectionAndStatement(connection, ps);
		}

		return entity;
	}

	@Override
	public void delete(Long id) throws DAOException {

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(SQL_DELETE);
			ps.setLong(0, id);

			int qtdLinhas = ps.executeUpdate();

			if (0 <= qtdLinhas) {
				throw new DAOException("Nenhum registro apagado.");
			}

			logger.info("Fornecedor apagado com sucesso!");
		} catch (SQLException e) {
			logger.error("Erro ao apagar fornecedor.", e);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnectionAndStatement(connection, ps);
		}
	}

	@Override
	public Fornecedor findById(Long id) throws DAOException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Fornecedor fornecedor = null;

		try {
			ps = connection.prepareStatement(SQL_FIND_BY_ID);
			ps.setLong(0, id);

			rs = ps.executeQuery();

			fornecedor = getFornecedor(rs);

			logger.info("Fornecedor recuperado com sucesso!\n ".concat(fornecedor.toString()));
		} catch (SQLException e) {
			logger.error("Erro ao recuperar fornecedor.", e);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeAll(connection, ps, rs);
		}

		return fornecedor;
	}

	@Override
	public List<Fornecedor> findByName(String nome) throws DAOException {

		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(SQL_FIND_BY_NOME);
			ps.setString(0, "%"+nome);
			
			rs = ps.executeQuery();

			while (rs.next()) {
				fornecedores.add(getFornecedor(rs));
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeAll(connection, ps, rs);
		}

		return fornecedores;
	}

	@Override
	public List<Fornecedor> getAll() throws DAOException {

		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			ps = connection.prepareStatement(SQL_FIND_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				fornecedores.add(getFornecedor(rs));
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeAll(connection, ps, rs);
		}

		return fornecedores;
	}

	private Fornecedor getFornecedor(ResultSet rs) throws SQLException {

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(rs.getLong("id"));
		fornecedor.setNome(rs.getString("nome"));
		fornecedor.setDescricao(rs.getString("descricao"));

		return fornecedor;
	}

}