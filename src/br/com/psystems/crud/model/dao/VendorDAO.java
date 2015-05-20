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

/**
 * @author rafael.saldanha
 *
 */
public class VendorDAO implements IDAO<Vendor> {

	//SQL
	private static final String SALVAR_FORNECEDOR = "INSERT INTO fornecedor (nome, descricao) VALUES (?,?)";
	private static final String ATUALIZAR_FORNECEDOR = "UPDATE fornecedor SET nome = ?, descricao = ? WHERE id = ?";
	private static final String APAGAR_FORNECEDOR = "DELETE FROM fornecedor WHERE id = ?";
	private static final String OBTER_TODOS_FORNECEDORES = "SELECT * FROM fornecedor";
	private static final String OBTER_FORNECEDORES_POR_NOME = "SELECT * FROM fornecedor WHERE nome like ?";
	protected static final String OBTER_FORNECEDOR_POR_ID = "SELECT * FROM fornecedor WHERE id = ?";
	protected static final String OBTER_FORNECEDORES_POR_ID_DIF = "SELECT * FROM fornecedor WHERE id <> ?";
	
	private static Logger logger = Logger.getLogger(VendorDAO.class);
	
	//MENSAGENS
	private static final String SALVAR_SUCESSO = "Fornecedor inserido com sucesso!\n ";
	private static final String SALVAR_ERRO = "Erro ao inserir fornecedor.";
	private static final String ATUALIZAR_SUCESSO = "Fornecedor atualizado com sucesso!\n ";
	private static final String ATUALIZAR_ERRO = "Erro ao atualizar fornecedor.";
	private static final String APAGAR_SUCESSO = "Fornecedor apagado com sucesso!\n ";
	private static final String APAGAR_ERRO = "Erro ao apagar fornecedor.";
	private static final String OBTER_POR_ID_SUCESSO = "Fornecedor recuperado com sucesso!\n ";
	private static final String OBTER_POR_ID_ERRO = "Erro ao recuperar fornecedor.";
	private static final String OBTER_POR_PALAVRA_CHAVE_SUCESSO = "Fornecedores recuperados com sucesso!\n ";
	private static final String OBTER_POR_PALAVRA_CHAVE_ERRO = "Erro ao recuperar fornecedores a partir da palavra-chave.";
	private static final String OBTER_POR_ID_DIF_ERRO = "Erro ao recuperar fornecedores a partir do id.";
	private static final String OBTER_TODOS_SUCESSO = "Fornecedores recuperados com sucesso!";
	private static final String OBTER_TODOS_ERRO = "Erro ao recuperar fornecedores.";

	@Override
	public void save(Vendor entidade) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = JDBCConnectionFactory.getConnection();

			ps = con.prepareStatement(SALVAR_FORNECEDOR);
			ps.setString(1, entidade.getName());
			ps.setString(2, entidade.getDescription());
			ps.execute();

			logger.info(SALVAR_SUCESSO.concat(entidade.toString()));
			
		} catch (SQLException e) {
			logger.error(SALVAR_ERRO, e);
			throw new PersistenceException(SALVAR_ERRO, e);
		} finally {
			JDBCConnectionFactory.close(con, ps);
		}
	}

	@Override
	public Vendor update(Vendor entidade) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = JDBCConnectionFactory.getConnection();

			ps = con.prepareStatement(ATUALIZAR_FORNECEDOR);
			ps.setString(1, entidade.getName());
			ps.setString(2, entidade.getDescription());
			ps.setInt(3, entidade.getId());
			ps.executeUpdate();

			logger.info(ATUALIZAR_SUCESSO.concat(entidade.toString()));
			
		} catch (SQLException e) {
			logger.error(ATUALIZAR_ERRO, e);
			throw new PersistenceException(ATUALIZAR_ERRO, e);
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

			ps = con.prepareStatement(APAGAR_FORNECEDOR);
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
	public Vendor recoverByID(Serializable id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vendor fornecedor = null;

		try {
			con = JDBCConnectionFactory.getConnection();

			ps = con.prepareStatement(OBTER_FORNECEDOR_POR_ID);
			ps.setInt(1, (int) id);
			//ps.closeOnCompletion(); Ainda não implementado por todos os SGBDs em suas libs jdbc.

			rs = ps.executeQuery();

			while (rs.next()) {
				fornecedor = getVendorByResultSet(rs);
			}

			if (null != fornecedor) {
				logger.info(OBTER_POR_ID_SUCESSO.concat(fornecedor.toString()));
			} else {
				logger.warn("Fornecedor não encontrado.".concat("\nID: "+id));
			}
			
		} catch (SQLException e) {
			logger.error(OBTER_POR_ID_ERRO, e);
			throw new PersistenceException(OBTER_POR_ID_ERRO,e);
		} finally {
			JDBCConnectionFactory.close(con, ps, rs);
		}

		return fornecedor;
	}

	@Override
	public List<Vendor> getByKeyword(String keyword) throws PersistenceException {

		List<Vendor> fornecedores = new ArrayList<Vendor>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			if (StringUtils.isNotEmpty(keyword)) {
				
				con = JDBCConnectionFactory.getConnection();
				
				ps = con.prepareStatement(OBTER_FORNECEDORES_POR_NOME);
				ps.setString(1, keyword.concat("%"));
//				ps.closeOnCompletion();
				
				rs = ps.executeQuery();
				
				while (rs.next()) {
					fornecedores.add(getVendorByResultSet(rs));
				}
				
				if (0 < fornecedores.size()) {
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

		return fornecedores;
	}
	
	public List<Vendor> getByIdDifferent(Integer id) throws PersistenceException {

		List<Vendor> fornecedores = new ArrayList<Vendor>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = JDBCConnectionFactory.getConnection();
			
			ps = con.prepareStatement(OBTER_FORNECEDORES_POR_ID_DIF);
			ps.setInt(1, id);
//			ps.closeOnCompletion();
			
			rs = ps.executeQuery();

			while (rs.next()) {
				fornecedores.add(getVendorByResultSet(rs));
			}
			
			if (0 < fornecedores.size()) {
				logger.info("Fornecedores com id diferente de ".concat(""+id).concat(" obtidos com sucesso!"));
			} else {
				logger.warn("Não foram encontrados fornecedores com id diferente de ".concat(""+id));
			}
			
		} catch (SQLException e) {
			logger.error(OBTER_POR_ID_DIF_ERRO, e);
			throw new PersistenceException(OBTER_POR_ID_DIF_ERRO, e);
		} finally {
			JDBCConnectionFactory.close(con, ps, rs);
		}

		return fornecedores;
	}

	@Override
	public List<Vendor> getAll() throws PersistenceException {

		List<Vendor> fornecedores = new ArrayList<Vendor>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = JDBCConnectionFactory.getConnection();
			
			ps = con.prepareStatement(OBTER_TODOS_FORNECEDORES);
//			ps.closeOnCompletion();
			
			rs = ps.executeQuery();

			while (rs.next()) {
				fornecedores.add(getVendorByResultSet(rs));
			}
			
			if (0 < fornecedores.size()) {
				logger.info(OBTER_TODOS_SUCESSO);
			} else {
				logger.warn("Fornecedores não encontrados.");
			}
			
		} catch (SQLException e) {
			logger.error(OBTER_TODOS_ERRO, e);
			throw new PersistenceException(OBTER_TODOS_ERRO, e);
		} finally {
			JDBCConnectionFactory.close(con, ps, rs);
		}

		return fornecedores;
	}

	private Vendor getVendorByResultSet(ResultSet rs) throws SQLException {

		Vendor fornecedor = new Vendor();
		fornecedor.setId(rs.getInt("id"));
		fornecedor.setNome(rs.getString("nome"));
		fornecedor.setDescription(rs.getString("descricao"));

		return fornecedor;
	}

}