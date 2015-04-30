/**
 * 
 */
package br.com.psystems.crud.model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.PersistenciaException;
import br.com.psystems.crud.model.domain.Fornecedor;
import br.com.psystems.crud.model.domain.Produto;

/**
 * @author rafael.saldanha
 *
 */
public class ProdutoDAO implements DAO<Produto> {
	
	private static final String SALVAR_PRODUTO = "INSERT INTO produto (fornecedor_id, nome, descricao, preco, preco_compra, quantidade, lote) VALUES (?,?,?,?)";
	private static final String ATUALIZAR_PRODUTO = "UPDATE produto SET fornecedor_id = ?, nome = ?, descricao = ?, preco = ?, preco_compra = ?, quantidade = ?, lote = ? WHERE id = ?";
	private static final String APAGAR_PRODUTO = "DELETE FROM produto WHERE id = ?";
	private static final String OBTER_TODOS_PRODUTOS = "SELECT * FROM produto";
	private static final String OBTER_PRODUTOS_POR_NOME = "SELECT * FROM produto WHERE nome like ?";
	private static final String OBTER_PRODUTO_POR_ID = "SELECT * FROM produto WHERE id = ?";
	protected static final String OBTER_PRODUTOS_POR_FORNECEDORID = "SELECT * FROM produto WHERE fornecedor_id = ?";
	
	private static Logger logger = Logger.getLogger(FornecedorDAO.class);
	
	@Override
	public void salvar(Produto entidade) throws PersistenciaException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConexaoFabrica.getConexao();
			
			ps = con.prepareStatement(SALVAR_PRODUTO);
			ps.setInt(0, entidade.getFornecedorId());
			ps.setString(1, entidade.getNome());
			ps.setString(2, entidade.getDescricao());
			ps.setDouble(3, entidade.getValorDeVenda());
			ps.setDouble(4, entidade.getValorDeCompra());
			ps.setDouble(5, entidade.getQuantidade());
			ps.setString(6, entidade.getLote());
			
			ps.executeUpdate();
			
			logger.info("Fornecedor inserido com sucesso!\n ".concat(entidade.toString()));
		} catch (SQLException e) {
			throw new PersistenciaException();
		} finally {
			ConexaoFabrica.fecharConexaoEstatement(con, ps);
		}
	}

	@Override
	public Produto atualizar(Produto entidade) throws PersistenciaException {
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConexaoFabrica.getConexao();

			ps = con.prepareStatement(ATUALIZAR_PRODUTO);
			ps.setInt(0, entidade.getFornecedorId());
			ps.setString(1, entidade.getNome());
			ps.setString(2, entidade.getDescricao());
			ps.setDouble(3, entidade.getValorDeVenda());
			ps.setDouble(4, entidade.getValorDeCompra());
			ps.setDouble(5, entidade.getQuantidade());
			ps.setString(6, entidade.getLote());
			ps.setInt(7, entidade.getId());

			int qtdLinhas = ps.executeUpdate();

			if (0 <= qtdLinhas) {
//				logger.info("Nenhum registro alterado.");
				throw new PersistenciaException("Nenhum registro alterado.");
			}

			logger.info("Fornecedor atualizado com sucesso!\n ".concat(entidade.toString()));
		} catch (SQLException e) {
			logger.error("Erro ao atualizar fornecedor.", e);
			throw new PersistenciaException(e);
		} finally {
			ConexaoFabrica.fecharConexaoEstatement(con, ps);
		}

		return entidade;
	}

	@Override
	public void apagar(Produto entidade) throws PersistenciaException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConexaoFabrica.getConexao();

			ps = con.prepareStatement(APAGAR_PRODUTO);
			ps.setInt(0, entidade.getId());

			int qtdLinhas = ps.executeUpdate();

			if (0 <= qtdLinhas) {
				throw new PersistenciaException("Nenhum registro apagado.");
			}

			logger.info("Fornecedor apagado com sucesso!\n ".concat(entidade.toString()));
		} catch (SQLException e) {
			logger.error("Erro ao apagar fornecedor.", e);
			throw new PersistenciaException(e);
		} finally {
			ConexaoFabrica.fecharConexaoEstatement(con, ps);
		}
	}

	@Override
	public Produto obterPorId(Serializable id) throws PersistenciaException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Produto produto = null;

		try {
			con = ConexaoFabrica.getConexao();

			ps = con.prepareStatement(OBTER_PRODUTO_POR_ID);
			ps.setInt(0, (int) id);

			rs = ps.executeQuery();

			produto = obtemProduto(rs);

			logger.info("Fornecedor recuperado com sucesso!\n ".concat(produto.toString()));
		} catch (SQLException e) {
			logger.error("Erro ao recuperar fornecedor.", e);
			throw new PersistenciaException(e);
		} finally {
			ConexaoFabrica.fecharTodos(con, ps, rs);
		}

		return produto;
	}

	@Override
	public List<Produto> obterPorNome(String nome) throws PersistenciaException {

		List<Produto> produtos = new ArrayList<Produto>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ConexaoFabrica.getConexao();
			
			ps = con.prepareStatement(OBTER_PRODUTOS_POR_NOME);
			ps.setString(0, "%"+nome);
			
			rs = ps.executeQuery();

			while (rs.next()) {
				produtos.add(obtemProduto(rs));
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			ConexaoFabrica.fecharTodos(con, ps, rs);
		}

		return produtos;
	}

	@Override
	public List<Produto> obterTodos() throws PersistenciaException {

		List<Produto> produtos = new ArrayList<Produto>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ConexaoFabrica.getConexao();
			ps = con.prepareStatement(OBTER_TODOS_PRODUTOS);
			rs = ps.executeQuery();

			while (rs.next()) {
				produtos.add(obtemProduto(rs));
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			ConexaoFabrica.fecharTodos(con, ps, rs);
		}

		return produtos;
	}

	private Produto obtemProduto(ResultSet rs) throws SQLException, PersistenciaException {
		
		Produto produto = new Produto();
		produto.setDescricao(rs.getString("descricao"));
		produto.setFornecedorId(rs.getInt("fornecedor_id"));
		produto.setFornecedor(obtemFornecedorPorId(produto.getFornecedorId()));
		produto.setId(rs.getInt("id"));
		produto.setLote(rs.getString("lote"));
		produto.setNome(rs.getString("nome"));
		produto.setQuantidade(rs.getDouble("quantidade"));
		produto.setValorDeCompra(rs.getDouble("vlr_compra"));
		produto.setValorDeVenda(rs.getDouble("vlr_venda"));
		
		return produto;
	}
	
	private Fornecedor obtemFornecedorPorId(Integer id) throws PersistenciaException {
		return new FornecedorDAO().obterPorId(id);
	}
}