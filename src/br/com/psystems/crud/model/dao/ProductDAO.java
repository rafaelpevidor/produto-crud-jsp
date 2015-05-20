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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.PersistenceException;
import br.com.psystems.crud.model.domain.Vendor;
import br.com.psystems.crud.model.domain.Product;

/**
 * @author rafael.saldanha
 *
 */
public class ProductDAO implements IDAO<Product> {
	
	//SQL
	private static final String SALVAR_PRODUTO = "INSERT INTO produto (fornecedor_id, nome, lote, quantidade, vlr_compra, vlr_venda, descricao) VALUES (?,?,?,?,?,?,?)";
	private static final String ATUALIZAR_PRODUTO = "UPDATE produto SET fornecedor_id = ?, nome = ?, lote = ?, quantidade = ?, vlr_compra = ?, vlr_venda = ?, descricao = ? WHERE id = ?";
	private static final String APAGAR_PRODUTO = "DELETE FROM produto WHERE id = ?";
	private static final String OBTER_TODOS_PRODUTOS = "SELECT * FROM produto";
	private static final String OBTER_PRODUTOS_POR_NOME = "SELECT * FROM produto WHERE nome like ?";
	private static final String OBTER_PRODUTO_POR_ID = "SELECT * FROM produto WHERE id = ?";
	protected static final String OBTER_PRODUTOS_POR_FORNECEDORID = "SELECT * FROM produto WHERE fornecedor_id = ?";
	
	private static Logger logger = Logger.getLogger(VendorDAO.class);
	
	//MENSAGENS
	private static final String SALVAR_SUCESSO = "Produto inserido com sucesso!\n ";
	private static final String SALVAR_ERRO = "Erro ao inserir produto.";
	private static final String ATUALIZAR_SUCESSO = "Produto atualizado com sucesso!\n ";
	private static final String ATUALIZAR_ERRO = "Erro ao atualizar produto.";
	private static final String APAGAR_SUCESSO = "Produto apagado com sucesso!\n ";
	private static final String APAGAR_ERRO = "Erro ao apagar produto.";
	private static final String OBTER_POR_ID_SUCESSO = "Produto recuperado com sucesso!\n ";
	private static final String OBTER_POR_ID_ERRO = "Erro ao recuperar produto.";
	private static final String OBTER_POR_PALAVRA_CHAVE_SUCESSO = "Produtos recuperados com sucesso!\n ";
	private static final String OBTER_POR_PALAVRA_CHAVE_ERRO = "Erro ao recuperar produtos a partir da palavra-chave.";
	private static final String OBTER_TODOS_SUCESSO = "Produtos recuperados com sucesso!";
	private static final String OBTER_TODOS_ERRO = "Erro ao recuperar produtos.";
	
	@Override
	public void save(Product entidade) throws PersistenceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = JDBCConnectionFactory.getConnection();
			
			ps = con.prepareStatement(SALVAR_PRODUTO);
			ps.setInt(1, entidade.getVendorId());
			ps.setString(2, entidade.getName());
			ps.setString(3, entidade.getAllotment());
			ps.setDouble(4, entidade.getAmount());
			ps.setDouble(5, entidade.getPurchasePrice());
			ps.setDouble(6, entidade.getSalePrice());
			ps.setString(7, entidade.getDescription());
			ps.execute();
			
			logger.info(SALVAR_SUCESSO.concat(entidade.toString()));
			
		} catch (SQLException e) {
			
			logger.error(SALVAR_ERRO.concat(entidade.toString()));
			throw new PersistenceException(SALVAR_ERRO, e);
			
		} finally {
			JDBCConnectionFactory.close(con, ps);
		}
	}

	@Override
	public Product update(Product entidade) throws PersistenceException {
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = JDBCConnectionFactory.getConnection();

			ps = con.prepareStatement(ATUALIZAR_PRODUTO);
			ps.setInt(1, entidade.getVendorId());
			ps.setString(2, entidade.getName());
			ps.setString(3, entidade.getAllotment());
			ps.setDouble(4, entidade.getAmount());
			ps.setDouble(5, entidade.getPurchasePrice());
			ps.setDouble(6, entidade.getSalePrice());
			ps.setString(7, entidade.getDescription());
			ps.setInt(8, entidade.getId());
			ps.executeUpdate();

			logger.info(ATUALIZAR_SUCESSO.concat(entidade.toString()));
			
		} catch (SQLException e) {
			logger.error(ATUALIZAR_ERRO, e);
			throw new PersistenceException(ATUALIZAR_ERRO,e);
			
		} finally {
			JDBCConnectionFactory.close(con, ps);
		}

		return entidade;
	}

	@Override
	public void delete(Integer pk) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = JDBCConnectionFactory.getConnection();

			ps = con.prepareStatement(APAGAR_PRODUTO);
			ps.setInt(1, pk);
			ps.executeUpdate();

			logger.info(APAGAR_SUCESSO);
			
		} catch (SQLException e) {
			logger.error(APAGAR_ERRO, e);
			throw new PersistenceException(APAGAR_ERRO, e);
			
		} finally {
			JDBCConnectionFactory.close(con, ps);
		}
	}

	@Override
	public Product recoverByID(Serializable id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product produto = null;

		try {
			con = JDBCConnectionFactory.getConnection();

			ps = con.prepareStatement(OBTER_PRODUTO_POR_ID);
			ps.setInt(1, (int) id);
			//ps.closeOnCompletion(); Ainda não implementado por todos os SGBDs em suas libs jdbc.

			rs = ps.executeQuery();

			while (rs.next()) {
				produto = getVendorByResultSet(rs);
			}

			if (null != produto) {
				logger.info(OBTER_POR_ID_SUCESSO.concat(produto.toString()));
			} else {
				logger.warn("Produto não encontrado.".concat("\nID: "+id));
			}
			
		} catch (SQLException e) {
			logger.error(OBTER_POR_ID_ERRO, e);
			throw new PersistenceException(OBTER_POR_ID_ERRO, e);
			
		} finally {
			JDBCConnectionFactory.close(con, ps, rs);
		}

		return produto;
	}

	@Override
	public List<Product> getByKeyword(String keyword) throws PersistenceException {

		List<Product> produtos = new ArrayList<Product>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			if (StringUtils.isNotEmpty(keyword)) {
				
				con = JDBCConnectionFactory.getConnection();
				
				ps = con.prepareStatement(OBTER_PRODUTOS_POR_NOME);
				ps.setString(1, keyword.concat("%"));
//			ps.closeOnCompletion();
				
				rs = ps.executeQuery();
				
				while (rs.next()) {
					produtos.add(getVendorByResultSet(rs));
				}
				
				if (0 < produtos.size()) {
					logger.info(OBTER_POR_PALAVRA_CHAVE_SUCESSO.concat("Palavra-chave: ").concat(keyword));
				} else {
					logger.warn("Não foram encontrados resultados na pesquisa.".concat("Palavra-chave: ").concat(keyword));
				}
				
			} else {
				getAll();
			}
			

		} catch (SQLException e) {
			logger.error(OBTER_POR_PALAVRA_CHAVE_ERRO, e);
			throw new PersistenceException(OBTER_POR_PALAVRA_CHAVE_ERRO, e);
		} finally {
			JDBCConnectionFactory.close(con, ps, rs);
		}

		return produtos;
	}

	@Override
	public List<Product> getAll() throws PersistenceException {

		List<Product> produtos = new ArrayList<Product>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = JDBCConnectionFactory.getConnection();
			
			ps = con.prepareStatement(OBTER_TODOS_PRODUTOS);
//			ps.closeOnCompletion();
			
			rs = ps.executeQuery();

			while (rs.next()) {
				produtos.add(getVendorByResultSet(rs));
			}
			
			if (0 < produtos.size()) {
				logger.info(OBTER_TODOS_SUCESSO);
			} else {
				logger.warn("Produtos não encontrados.");
			}
			

		} catch (SQLException e) {
			logger.error(OBTER_TODOS_ERRO, e);
			throw new PersistenceException(OBTER_TODOS_ERRO, e);
			
		} finally {
			JDBCConnectionFactory.close(con, ps, rs);
		}

		return produtos;
	}

	private Product getVendorByResultSet(ResultSet rs) throws SQLException, PersistenceException {
		
		Product produto = new Product();
		produto.setDescription(rs.getString("descricao"));
		produto.setVendorId(rs.getInt("fornecedor_id"));
		produto.setVendor(getVendorByID(produto.getVendorId()));
		produto.setId(rs.getInt("id"));
		produto.setAllotment(rs.getString("lote"));
		produto.setName(rs.getString("nome"));
		produto.setAmount(rs.getDouble("quantidade"));
		produto.setPurchasePrice(rs.getDouble("vlr_compra"));
		produto.setSalePrice(rs.getDouble("vlr_venda"));
		
		return produto;
	}
	
	private Vendor getVendorByID(Integer id) throws PersistenceException {
		return new VendorDAO().recoverByID(id);
	}
}