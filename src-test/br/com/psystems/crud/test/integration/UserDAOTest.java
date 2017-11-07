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
import br.com.psystems.crud.infra.ConnectionFactory.SchemaEnum;
import br.com.psystems.crud.infra.ConnectionManager;
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
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new UserDAO(new ConnectionManager(ConnectionFactory.getConnection(SchemaEnum.HML)));
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		Connection con = ConnectionFactory.getConnection(SchemaEnum.HML);
		PreparedStatement ps = con.prepareStatement("DROP TABLE tb_user;");
		ps.execute();
		con.commit();
		con.close();
		logger.info("Conexão com o banbo de dados fechada com sucesso.");
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UserDAO#delete(java.lang.Long)}.
	 */
	@Test
	public void testDelete() {
		fail("Not yet implemented");
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
		
		Long nextId = getId();
		
		user = null;
		dao = new UserDAO(new ConnectionManager(ConnectionFactory.getConnection(SchemaEnum.HML)));
		user = dao.findById(nextId);
		
		Assert.assertNotNull(user);
		Assert.assertTrue(user.getId().equals(nextId));
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
		Connection con = ConnectionFactory.getConnection(SchemaEnum.HML);
		PreparedStatement ps = con.prepareStatement("select nextval(pg_get_serial_sequence('tb_user', 'id')) as new_id");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Object obj = rs.getObject(1);
			System.out.println(obj);
			nextId = rs.getLong(1);
			nextId--;
		}
		
		con.close();
		
		logger.info("Conexão com o banbo de dados fechada com sucesso.");
		
		return nextId;
	}
	
	private String getCreateTableSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE public.tb_user)");
		sb.append("CREATE TABLE public.tb_user)");
		sb.append("CREATE TABLE public.tb_user)");
		sb.append("CREATE TABLE public.tb_user)");
		
		sb.append("CREATE TABLE public.tb_user)");
		sb.append("CREATE TABLE public.tb_user)");
		
		
		return sb.toString();
	}
}
