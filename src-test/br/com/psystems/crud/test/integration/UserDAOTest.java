/**
 * 
 */
package br.com.psystems.crud.test.integration;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.User;
import br.com.psystems.crud.model.dao.UserDAO;
import br.com.psystems.crud.model.enums.RoleEnum;
import br.com.psystems.crud.test.builder.UserBuilder;

/**
 * @author developer
 *
 */
public class UserDAOTest extends AbstractTest {

	private User entity;
	private UserDAO dao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		dao = new UserDAO();
		entity = new UserBuilder()
				.setEmail("u_"+ALIAS+"@email.com")
				.setName("User Name " + ALIAS)
				.setPassword("password_"+ALIAS)
				.setRole(RoleEnum.ADMIN)
				.build();
		
		truncate(UserDAO.TABLE_NAME, false);
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

		dao.save(entity);

		Long id = getLastIdFrom(UserDAO.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertTrue(entity.getId().equals(id));

		dao.delete(id);

		entity = dao.findById(id);

		Assert.assertNull(entity);
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UserDAO#save(br.com.psystems.crud.model.User)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testSaveUser() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(UserDAO.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertTrue(entity.getId().equals(id));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UserDAO#update(br.com.psystems.crud.model.User)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testUpdateUser() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(UserDAO.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertTrue(entity.getId().equals(id));

		entity.setEmail("novoenderecode@ymail.com");
		entity = dao.update(entity);

		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertTrue(entity.getId().equals(id));
		Assert.assertEquals(entity.getEmail(), "novoenderecode@ymail.com");

	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UserDAO#findById(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testFindByIdLong() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(UserDAO.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertTrue(entity.getId().equals(id));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UserDAO#findByName(java.lang.String)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testFindByNameString() throws DAOException, SystemException {
		
		entity.setName("Rafael Pevidor");

		dao.save(entity);
		
		List<User> users = dao.findByName("rafael");
		
		Assert.assertTrue(null != users);
		Assert.assertTrue(!users.isEmpty());
		Assert.assertTrue(users.size() == 1 && null != users.get(0).getId());
		Assert.assertTrue(users.get(0).getName().equals("Rafael Pevidor"));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UserDAO#getAll()}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testGetAll() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);
		
		List<User> users = dao.getAll();
		
		Assert.assertTrue(null != users);
		Assert.assertTrue(!users.isEmpty());
		Assert.assertTrue(1 == users.size());
		
	}

}
