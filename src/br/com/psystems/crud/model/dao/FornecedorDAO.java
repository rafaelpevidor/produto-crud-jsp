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

/**
 * @author rafael.saldanha
 *
 */
public class FornecedorDAO implements DAO<Fornecedor> {

	private static final String SALVAR_FORNECEDOR = "INSERT INTO fornecedor (nome, descricao) VALUES (?,?,?,?)";
	private static final String ATUALIZAR_FORNECEDOR = "UPDATE fornecedor SET nome = ?, descricao = ? WHERE id = ?";
	private static final String APAGAR_FORNECEDOR = "DELETE FROM fornecedor WHERE id = ?";
	private static final String OBTER_TODOS_FORNECEDORES = "SELECT * FROM fornecedor";
	private static final String OBTER_FORNECEDORES_POR_NOME = "SELECT * FROM fornecedor WHERE nome like ?";
	protected static final String OBTER_FORNECEDOR_POR_ID = "SELECT * FROM fornecedor WHERE id = ?";
	
	private static Logger logger = Logger.getLogger(FornecedorDAO.class);

	@Override
	public void salvar(Fornecedor entidade) throws PersistenciaException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConexaoFabrica.getConexao();

			ps = con.prepareStatement(SALVAR_FORNECEDOR);
			ps.setString(0, entidade.getNome());
			ps.setString(1, entidade.getDescricao());

			ps.executeUpdate();

			logger.info("Fornecedor inserido com sucesso!\n ".concat(entidade.toString()));
		} catch (SQLException e) {
			logger.error("Erro ao inserir fornecedor.", e);
			throw new PersistenciaException(e);
		} finally {
			ConexaoFabrica.fecharConexaoEstatement(con, ps);
		}
	}

	@Override
	public Fornecedor atualizar(Fornecedor entidade) throws PersistenciaException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConexaoFabrica.getConexao();

			ps = con.prepareStatement(ATUALIZAR_FORNECEDOR);
			ps.setString(0, entidade.getNome());
			ps.setString(1, entidade.getDescricao());
			ps.setInt(2, entidade.getId());

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
	public void apagar(Fornecedor entidade) throws PersistenciaException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConexaoFabrica.getConexao();

			ps = con.prepareStatement(APAGAR_FORNECEDOR);
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
	public Fornecedor obterPorId(Serializable id) throws PersistenciaException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Fornecedor fornecedor = null;

		try {
			con = ConexaoFabrica.getConexao();

			ps = con.prepareStatement(OBTER_FORNECEDOR_POR_ID);
			ps.setInt(0, (int) id);

			rs = ps.executeQuery();

			fornecedor = obtemFornecedor(rs);

			logger.info("Fornecedor recuperado com sucesso!\n ".concat(fornecedor.toString()));
		} catch (SQLException e) {
			logger.error("Erro ao recuperar fornecedor.", e);
			throw new PersistenciaException(e);
		} finally {
			ConexaoFabrica.fecharTodos(con, ps, rs);
		}

		return fornecedor;
	}

	@Override
	public List<Fornecedor> obterPorNome(String nome) throws PersistenciaException {

		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ConexaoFabrica.getConexao();
			
			ps = con.prepareStatement(OBTER_FORNECEDORES_POR_NOME);
			ps.setString(0, "%"+nome);
			
			rs = ps.executeQuery();

			while (rs.next()) {
				fornecedores.add(obtemFornecedor(rs));
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			ConexaoFabrica.fecharTodos(con, ps, rs);
		}

		return fornecedores;
	}

	@Override
	public List<Fornecedor> obterTodos() throws PersistenciaException {

		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ConexaoFabrica.getConexao();
			ps = con.prepareStatement(OBTER_TODOS_FORNECEDORES);
			rs = ps.executeQuery();

			while (rs.next()) {
				fornecedores.add(obtemFornecedor(rs));
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			ConexaoFabrica.fecharTodos(con, ps, rs);
		}

		return fornecedores;
	}

	private Fornecedor obtemFornecedor(ResultSet rs) throws SQLException {

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(rs.getInt("id"));
		fornecedor.setNome(rs.getString("nome"));
		fornecedor.setDescricao(rs.getString("descricao"));

		return fornecedor;
	}

}