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
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.TransactionCallback;
import br.com.psystems.crud.model.RoleEnum;
import br.com.psystems.crud.model.User;

/**
 * @author rafael.saldanha
 *
 */
public class UserDAO extends AbstractDAO<User> {
	
	public UserDAO() throws DAOException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Logger logger = Logger.getLogger(UserDAO.class);
	
	private static final String SQL_INSERT = "INSERT INTO tb_user (name, mail, password, role) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE tb_user SET name = ?, mail = ?, password = ?, role = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM tb_user WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM tb_user";
	private static final String SQL_FIND_BY_NOME = "SELECT * FROM tb_user WHERE name like ?";
	protected static final String SQL_FIND_BY_ID = "SELECT * FROM tb_user WHERE id = ?";
	
	@Override
	public void save(User entity) throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				PreparedStatement ps = getPreparedStatement(connection, SQL_INSERT);
				ps.setString(1, entity.getName());
				ps.setString(2, entity.getEmail());
				ps.setString(3, entity.getPassword());
				ps.setString(4, entity.getRole().name());
				ps.execute();
				
				logger.info("Salvo com sucesso!");
			}
		});
	}

	@Override
	public User update(User entity) throws DAOException, SystemException {

		connectionManager.doInTransaction(new TransactionCallback() {
			
			@Override
			public void execute(Connection connection) throws SQLException {

				PreparedStatement ps = getPreparedStatement(connection, SQL_UPDATE);
				ps.setString(1, entity.getName());
				ps.setString(2, entity.getEmail());
				ps.setString(3, entity.getPassword());
				ps.setString(4, entity.getRole().name());
				ps.setLong(5, entity.getId());

				int qtdLinhas = ps.executeUpdate();

				if (0 <= qtdLinhas) {
					logger.info("Nenhum registro alterado.");
				} else {
					logger.info("Atualizado com sucesso!\n ".concat(entity.toString()));
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

				if (0 <= qtdLinhas) {
					logger.info("Nenhum registro apagado.");
				} else {
					logger.info("Usuário apagado com sucesso!");
				}
			}
		});
	}

	@Override
	public User findById(Long id) throws DAOException, SystemException {

		PreparedStatement ps;
		
		try {
			ps = getPreparedStatement(connectionManager.getConnection(), SQL_FIND_BY_ID);
			ps.setLong(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			return getUser(rs);
		} catch (Exception e) {
			set(e);
			return null;
		} finally {
			connectionManager.closeConnection();
			logger.info("Conexão com o banbo de dados fechada com sucesso.");
		}
	}

	@Override
	public List<User> findByName(String name) throws DAOException, SystemException {

		List<User> users = new ArrayList<User>();

		PreparedStatement ps;
		try {
			ps = getPreparedStatement(connectionManager.getConnection(), SQL_FIND_BY_NOME);
			ps.setString(1, "%"+name);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				users.add(getUser(rs));
			}
			
		} catch (Exception e) {
			set(e);
		} finally {
			connectionManager.closeConnection();
		}
	
		return users;
	}

	@Override
	public List<User> getAll() throws DAOException, SystemException {
		
		PreparedStatement ps;
		try {
			ps = getPreparedStatement(connectionManager.getConnection(), SQL_FIND_ALL);
			ResultSet rs = ps.executeQuery();
			return getUsers(rs);
		} catch (Exception e) {
			set(e);
		} finally {
			connectionManager.closeConnection();
		}

		return null;
	}

	private User getUser(final ResultSet rs) throws SQLException {
		User user = null;
		
		while (rs.next()) {
			user = createUser(rs);
		}
		
		return user;
	}
	
	private List<User> getUsers(final ResultSet rs) throws SQLException {

		List<User> users = new ArrayList<>();
		
		while (rs.next()) {
			users.add(getUser(rs));
		}
		return users;
	}
	
	private User createUser(final ResultSet rs) throws SQLException {

		User user = new User();
		user.setId(rs.getLong("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("mail"));
		user.setEmail(rs.getString("password"));
		user.setRole(RoleEnum.valueOf(rs.getString("role")));
	
		return user;
	}

}