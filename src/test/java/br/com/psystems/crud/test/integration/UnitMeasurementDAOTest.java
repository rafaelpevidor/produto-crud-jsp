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
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.model.dao.impl.UnitMeasurementDAOImpl;

/**
 * @author developer
 *
 */
public class UnitMeasurementDAOTest extends AbstractTest {
	
	private UnitMeasurement entity;
	private UnitMeasurementDAOImpl dao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		entity = new UnitMeasurement("Unit Name " + ALIAS);
		dao = new UnitMeasurementDAOImpl();
		
		truncate(UnitMeasurementDAOImpl.TABLE_NAME, true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.UnitMeasurementDAOImpl#delete(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);
		
		Long id = getLastIdFrom(UnitMeasurementDAOImpl.TABLE_NAME);
		
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
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.UnitMeasurementDAOImpl#save(br.com.psystems.crud.model.UnitMeasurement)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testSaveUnitOfMeasurement() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);
		
		Long id = getLastIdFrom(UnitMeasurementDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertEquals(entity.getId(), id);
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.UnitMeasurementDAOImpl#update(br.com.psystems.crud.model.UnitMeasurement)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testUpdateUnitOfMeasurement() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);
		
		Long id = getLastIdFrom(UnitMeasurementDAOImpl.TABLE_NAME);
		
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
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.UnitMeasurementDAOImpl#findById(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testFindByIdLong() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);
		
		Long id = getLastIdFrom(UnitMeasurementDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertEquals(entity.getId(), id);
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.UnitMeasurementDAOImpl#findByName(java.lang.String)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testFindByNameString() throws DAOException, SystemException {
		
		entity.setName("Litro");

		dao.save(entity);
		
		entity.setName("Quilo");

		dao.save(entity);
		
		List<UnitMeasurement> units = dao.findByName("tro");
		
		Assert.assertTrue(null != units);
		Assert.assertTrue(!units.isEmpty());
		Assert.assertTrue(units.size() == 1 && null != units.get(0).getId());
		Assert.assertTrue(units.get(0).getName().equals("Litro"));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.UnitMeasurementDAOImpl#getAll()}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testGetAll() throws DAOException, SystemException {
		
		entity.setName("Litro");

		dao.save(entity);
		
		entity.setName("Quilo");

		dao.save(entity);
		
		List<UnitMeasurement> units = dao.getAll();
		
		Assert.assertTrue(null != units);
		Assert.assertTrue(!units.isEmpty());
		Assert.assertTrue(units.size() == 2 && null != units.get(0).getId());
	}

}
