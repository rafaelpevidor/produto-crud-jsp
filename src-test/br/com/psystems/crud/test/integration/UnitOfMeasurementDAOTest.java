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
import br.com.psystems.crud.model.UnitOfMeasurement;
import br.com.psystems.crud.model.dao.UnitOfMeasurementDAO;

/**
 * @author developer
 *
 */
public class UnitOfMeasurementDAOTest extends AbstractTest {
	
	private UnitOfMeasurement entity;
	private UnitOfMeasurementDAO dao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		entity = new UnitOfMeasurement("Unit Name " + ALIAS);
		dao = new UnitOfMeasurementDAO();
		
		truncate(UnitOfMeasurementDAO.TABLE_NAME, true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UnitOfMeasurementDAO#delete(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);
		
		Long id = getLastIdFrom(UnitOfMeasurementDAO.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertEquals(entity.getId(), id);
		
		dao.delete(id);
		
		entity = null;
		entity = dao.findById(id);
		
		Assert.assertNull(entity);
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UnitOfMeasurementDAO#save(br.com.psystems.crud.model.UnitOfMeasurement)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testSaveUnitOfMeasurement() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);
		
		Long id = getLastIdFrom(UnitOfMeasurementDAO.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertEquals(entity.getId(), id);
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UnitOfMeasurementDAO#update(br.com.psystems.crud.model.UnitOfMeasurement)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testUpdateUnitOfMeasurement() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);
		
		Long id = getLastIdFrom(UnitOfMeasurementDAO.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertEquals(entity.getId(), id);
		
		entity.setName("Pacote");
		entity = dao.update(entity);
		
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertTrue(entity.getId().equals(id));
		Assert.assertEquals(entity.getName(), "Pacote");
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UnitOfMeasurementDAO#findById(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testFindByIdLong() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);
		
		Long id = getLastIdFrom(UnitOfMeasurementDAO.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertEquals(entity.getId(), id);
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UnitOfMeasurementDAO#findByName(java.lang.String)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testFindByNameString() throws DAOException, SystemException {
		
		entity.setName("Litro");

		dao.save(entity);
		
		entity.setName("Quilo");

		dao.save(entity);
		
		List<UnitOfMeasurement> units = dao.findByName("tro");
		
		Assert.assertTrue(null != units);
		Assert.assertTrue(!units.isEmpty());
		Assert.assertTrue(units.size() == 1 && null != units.get(0).getId());
		Assert.assertTrue(units.get(0).getName().equals("Litro"));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.UnitOfMeasurementDAO#getAll()}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testGetAll() throws DAOException, SystemException {
		
		entity.setName("Litro");

		dao.save(entity);
		
		entity.setName("Quilo");

		dao.save(entity);
		
		List<UnitOfMeasurement> units = dao.getAll();
		
		Assert.assertTrue(null != units);
		Assert.assertTrue(!units.isEmpty());
		Assert.assertTrue(units.size() == 2 && null != units.get(0).getId());
	}

}
