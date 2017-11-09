/**
 * 
 */
package br.com.psystems.crud.test.integration;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.ConnectionFactory;
import br.com.psystems.crud.infra.ConnectionFactory.EnviromentEnum;
import br.com.psystems.crud.model.RoleEnum;
import br.com.psystems.crud.model.User;
import br.com.psystems.crud.model.dao.UserDAO;
import br.com.psystems.crud.test.builder.UserBuilder;

/**
 * @author developer
 *
 */
public class UserDAOTest {

	private static final long ALIAS = Calendar.getInstance().getTimeInMillis();
	
	private static Logger logger = Logger.getLogger(UserDAOTest.class);
	
	private UserDAO dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Connection con = ConnectionFactory.getConnection(EnviromentEnum.HML);
		PreparedStatement ps = con.prepareStatement(getDropTableSQL());
		ps.execute();
		con.commit();
		con.close();
		
		Connection con2 = ConnectionFactory.getConnection(EnviromentEnum.HML);
		PreparedStatement ps2 = con2.prepareStatement(getCreateTableSQL());
		ps2.execute();
		con2.commit();
		con2.close();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = getDAO();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UserDAO#delete(java.lang.Long)}.
	 */
	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {
		User user = new UserBuilder()
				.withEmail("u_"+ALIAS+"@email.com")
				.withName("User Name " + ALIAS)
				.withPassword("password_"+ALIAS)
				.withRole(RoleEnum.ADMIN)
				.build();
		
		dao.save(user);
		
		Long id = getId();
		
		user = null;
		dao = getDAO();
		user = dao.findById(id);
		
		Assert.assertNotNull(user);
		Assert.assertTrue(user.getId().equals(id));
		
		dao.delete(id);
		
		dao = getDAO();
		user = dao.findById(id);
		
		Assert.assertNull(user);
	}

	private UserDAO getDAO() throws DAOException {
		return new UserDAO();
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UserDAO#UserDAO(br.com.psystems.crud.infra.ConnectionManager)}.
	 */
	@Test
	public void testUserDAO() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UserDAO#save(br.com.psystems.crud.model.User)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testSaveUser() throws DAOException, SystemException, SQLException {
	
		User user = new UserBuilder()
				.withEmail("u_"+ALIAS+"@email.com")
				.withName("User Name " + ALIAS)
				.withPassword("password_"+ALIAS)
				.withRole(RoleEnum.ADMIN)
				.build();
		
		dao.save(user);
		
		Long id = getId();
		
		user = null;
		dao = getDAO();
		user = dao.findById(id);
		
		Assert.assertNotNull(user);
		Assert.assertTrue(user.getId().equals(id));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UserDAO#update(br.com.psystems.crud.model.User)}.
	 */
	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UserDAO#findById(java.lang.Long)}.
	 */
	@Test
	public void testFindByIdLong() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UserDAO#findByName(java.lang.String)}.
	 */
	@Test
	public void testFindByNameString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UserDAO#getAll()}.
	 */
	@Test
	public void testGetAll() {
		fail("Not yet implemented");
	}

	private Long getId() throws DAOException, SQLException {
		Long nextId = null;
		Connection con = ConnectionFactory.getConnection(EnviromentEnum.HML);
		PreparedStatement ps = con.prepareStatement("select nextval(pg_get_serial_sequence('tb_user', 'id')) as new_id");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			nextId = rs.getLong(1);
			nextId--;
		}
		
		con.close();
		
		logger.info("Conexão com o banbo de dados fechada com sucesso.");
		
		return nextId;
	}
	
	private static String getCreateTableSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE tb_user ( ");
		sb.append("id serial NOT NULL, ");
		sb.append("name character varying(255) NOT NULL, ");
		sb.append("mail character varying(255) NOT NULL, ");
		sb.append("password character varying(255) NOT NULL, ");
		sb.append("role character varying(10) NOT NULL, ");
		sb.append("CONSTRAINT pk_tb_user PRIMARY KEY (id)) ");
		
		return sb.toString();
	}
	
	private static String getDropTableSQL() {
		return "DROP TABLE tb_user";
	}
}
