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
import br.com.psystems.crud.infra.exception.BusinessException;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.User;
import br.com.psystems.crud.model.dao.impl.UserDAOImpl;
import br.com.psystems.crud.model.enums.RoleEnum;
import br.com.psystems.crud.service.UserService;
import br.com.psystems.crud.service.impl.UserServiceImpl;
import br.com.psystems.crud.test.builder.UserBuilder;

public class UserServiceTest extends AbstractTest<User> {

	private User entity;
	private UserService service;

	@Before
	public void setUp() throws Exception {
		entity = getEntity();
		service = new UserServiceImpl(new UserDAOImpl(new ConnectionManager(EnviromentEnum.DEV)));
	}

	@After
	public void tearDown() throws Exception {
		truncate(UserDAOImpl.TABLE_NAME);
	}

	@Test
	public void testSave() throws DAOException, SystemException, SQLException {

		service.save(entity);

		Long id = getLastIdFrom(UserDAOImpl.TABLE_NAME);

		entity = null;
		entity = service.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
	}

	@Test
	public void testUpdate() throws DAOException, SystemException, SQLException {
		service.save(entity);

		Long id = getLastIdFrom(UserDAOImpl.TABLE_NAME);

		entity = null;
		entity = service.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));

		entity.setEmail("novoenderecode@ymail.com");
		entity = service.update(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		assertEquals(entity.getEmail(), "novoenderecode@ymail.com");
	}

	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {

		service.save(entity);

		Long id = getLastIdFrom(UserDAOImpl.TABLE_NAME);

		entity = null;
		entity = service.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));

		service.delete(id);

		entity = service.findById(id);

		assertNull(entity);
	}

	@Test
	public void testFindById() throws DAOException, SystemException, SQLException {
		service.save(entity);

		Long id = getLastIdFrom(UserDAOImpl.TABLE_NAME);

		entity = null;
		entity = service.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
	}

	@Test
	public void testFindByName() throws DAOException, SystemException {
		entity.setName("Rafael Pevidor");

		service.save(entity);

		List<User> users = service.findByName("rafael");

		assertTrue(null != users);
		assertTrue(!users.isEmpty());
		assertTrue(users.size() == 1 && null != users.get(0).getId());
		assertTrue(users.get(0).getName().equals("Rafael Pevidor"));
	}

	@Test
	public void testGetAll() throws DAOException, SystemException {
		service.save(entity);

		List<User> users = service.getAll();

		assertTrue(null != users);
		assertTrue(!users.isEmpty());
		assertTrue(1 == users.size());
	}

	@Test(expected = BusinessException.class)
	public void testValidateRequiredFieldsOf() throws BusinessException, SystemException {
		
		entity.setName(null);
		service.validateRequiredFieldsOf(entity);
		
		entity = getEntity();
		entity.setEmail(null);
		service.validateRequiredFieldsOf(entity);
		
		entity = getEntity();
		entity.setPassword(null);
		service.validateRequiredFieldsOf(entity);
		
		entity = getEntity();
		entity.setRole(null);
		service.validateRequiredFieldsOf(entity);
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
