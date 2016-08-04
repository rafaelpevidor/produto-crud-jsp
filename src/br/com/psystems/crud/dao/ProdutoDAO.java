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
import br.com.psystems.crud.model.Produto;

/**
 * @author rafael.saldanha
 *
 */
public class ProdutoDAO implements BaseDAO<Produto> {
	
	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	protected ProdutoDAO() {}

	private static final String SQL_INSERT = "INSERT INTO produto (fornecedor_id, nome, cor, referencia, lote, quantidade, vlr_compra, preco, descricao) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE produto SET fornecedor_id = ?, nome = ?, cor = ?, referencia = ?, lote = ?, quantidade = ?, vlr_compra = ?, preco = ?, descricao = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM produto WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM produto";
	private static final String SQL_FIND_BY_NAME = "SELECT * FROM produto WHERE nome like ?";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM produto WHERE id = ?";
	protected static final String SQL_FIND_BY_FORNECEDOR = "SELECT * FROM produto WHERE fornecedor_id = ?";
	
	private static Logger logger = Logger.getLogger(FornecedorDAO.class);
	
	private Connection connection;
	
	@Override
	public void save(Produto entidade) throws DAOException {
		
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(SQL_INSERT);
			ps.setLong(0, entidade.getFornecedorId());
			ps.setString(1, entidade.getNome());
			ps.setString(2, entidade.getDescricao());
			ps.setDouble(3, entidade.getValorDeVenda());
			ps.setDouble(4, entidade.getValorDeCompra());
			ps.setDouble(5, entidade.getQuantidade());
			ps.setString(6, entidade.getLote());
			
			ps.executeUpdate();
			
			logger.info("Fornecedor inserido com sucesso!\n ".concat(entidade.toString()));
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			ConnectionFactory.closeConnectionAndStatement(connection, ps);
		}
	}

	@Override
	public Produto update(Produto entidade) throws DAOException {
		
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(SQL_UPDATE);
			ps.setLong(0, entidade.getFornecedorId());
			ps.setString(1, entidade.getNome());
			ps.setString(2, entidade.getDescricao());
			ps.setDouble(3, entidade.getValorDeVenda());
			ps.setDouble(4, entidade.getValorDeCompra());
			ps.setDouble(5, entidade.getQuantidade());
			ps.setString(6, entidade.getLote());
			ps.setLong(7, entidade.getId());

			int qtdLinhas = ps.executeUpdate();

			if (0 <= qtdLinhas) {
//				logger.info("Nenhum registro alterado.");
				throw new DAOException("Nenhum registro alterado.");
			}

			logger.info("Fornecedor atualizado com sucesso!\n ".concat(entidade.toString()));
		} catch (SQLException e) {
			logger.error("Erro ao atualizar fornecedor.", e);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeConnectionAndStatement(connection, ps);
		}

		return entidade;
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
	public Produto findById(Long id) throws DAOException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Produto produto = null;

		try {
			ps = connection.prepareStatement(SQL_FIND_BY_ID);
			ps.setLong(0, id);

			rs = ps.executeQuery();

			produto = getProduto(rs);

			logger.info("Fornecedor recuperado com sucesso!\n ".concat(produto.toString()));
		} catch (SQLException e) {
			logger.error("Erro ao recuperar fornecedor.", e);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeAll(connection, ps, rs);
		}

		return produto;
	}

	@Override
	public List<Produto> findByName(String nome) throws DAOException {

		List<Produto> produtos = new ArrayList<Produto>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(SQL_FIND_BY_NAME);
			ps.setString(0, "%"+nome);
			
			rs = ps.executeQuery();

			while (rs.next()) {
				produtos.add(getProduto(rs));
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeAll(connection, ps, rs);
		}

		return produtos;
	}

	@Override
	public List<Produto> getAll() throws DAOException {

		List<Produto> produtos = new ArrayList<Produto>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(SQL_FIND_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				produtos.add(getProduto(rs));
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ConnectionFactory.closeAll(connection, ps, rs);
		}

		return produtos;
	}

	private Produto getProduto(ResultSet rs) throws SQLException, DAOException {
		
		Produto produto = new Produto();
		produto.setDescricao(rs.getString("descricao"));
		produto.setFornecedorId(rs.getLong("fornecedor_id"));
//		produto.setFornecedor(obtemFornecedorPorId(produto.getFornecedorId()));
		produto.setId(rs.getLong("id"));
		produto.setLote(rs.getString("lote"));
		produto.setNome(rs.getString("nome"));
		produto.setQuantidade(rs.getDouble("quantidade"));
		produto.setValorDeCompra(rs.getDouble("vlr_compra"));
		produto.setValorDeVenda(rs.getDouble("vlr_venda"));
		
		return produto;
	}
	
//	private Fornecedor obtemFornecedorPorId(Integer id) throws PersistenciaException {
//		return new FornecedorDAO().obterPorId(id);
//	}
}