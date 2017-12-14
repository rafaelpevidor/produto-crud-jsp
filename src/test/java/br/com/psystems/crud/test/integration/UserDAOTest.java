/**
 * 
 */
package br.com.psystems.crud.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.psystems.crud.infra.ConnectionFactory.EnviromentEnum;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.User;
import br.com.psystems.crud.model.dao.UserDAO;
import br.com.psystems.crud.model.dao.impl.UserDAOImpl;
import br.com.psystems.crud.model.enums.RoleEnum;
import br.com.psystems.crud.test.builder.UserBuilder;

/**
 * @author developer
 *
 */
public class UserDAOTest extends AbstractTest<User> {

	private User entity;
	private UserDAO dao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new UserDAOImpl(new ConnectionManager(EnviromentEnum.TEST));
		entity = getEntity(); 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		truncate(UserDAOImpl.TABLE_NAME);
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.UserDAOImpl#delete(java.lang.Long)}.
	 */
	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(UserDAOImpl.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));

		dao.delete(id);

		entity = dao.findById(id);

		assertNull(entity);
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.UserDAOImpl#save(br.com.psystems.crud.model.User)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testSaveUser() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(UserDAOImpl.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.UserDAOImpl#update(br.com.psystems.crud.model.User)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testUpdateUser() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(UserDAOImpl.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));

		entity.setEmail("novoenderecode@ymail.com");
		entity = dao.update(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		assertEquals(entity.getEmail(), "novoenderecode@ymail.com");

	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.UserDAOImpl#findById(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testFindByIdLong() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(UserDAOImpl.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.UserDAOImpl#findByName(java.lang.String)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testFindByNameString() throws DAOException, SystemException {
		
		entity.setName("Rafael Pevidor");

		dao.save(entity);
		
		List<User> users = dao.findByName("rafael");
		
		assertTrue(null != users);
		assertTrue(!users.isEmpty());
		assertTrue(users.size() == 1 && null != users.get(0).getId());
		assertTrue(users.get(0).getName().equals("Rafael Pevidor"));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.UserDAOImpl#getAll()}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testGetAll() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);
		
		List<User> users = dao.getAll();
		
		assertTrue(null != users);
		assertTrue(!users.isEmpty());
		assertTrue(1 == users.size());
		
	}
	
	@Test(expected = DAOException.class)
	public void testSaveUserWithNullValues() throws DAOException, SystemException, SQLException {

		entity.setEmail(null);
		dao.save(entity);
	}
	
	@Test(expected = DAOException.class)
	public void testUpdateUserToNullValues() throws DAOException, SystemException, SQLException {

		dao.save(entity);
		
		Long id = getLastIdFrom(UserDAOImpl.TABLE_NAME);
		
		entity = dao.findById(id);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		
		entity.setEmail(null);
		entity = dao.update(entity);

	}

	@Override
	protected User getEntity() {
		return new UserBuilder()
				.setEmail("u_"+ALIAS+"@email.com")
				.setName("User Name " + ALIAS)
				.setPassword("password_"+ALIAS)
				.setRole(RoleEnum.ADMIN)
				.build();
	}

}
