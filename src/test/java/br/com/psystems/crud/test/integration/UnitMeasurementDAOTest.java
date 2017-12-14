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
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.model.dao.UnitMeasurementDAO;
import br.com.psystems.crud.model.dao.impl.UnitMeasurementDAOImpl;

/**
 * @author developer
 *
 */
public class UnitMeasurementDAOTest extends AbstractTest<UnitMeasurement> {
	
	private UnitMeasurement entity;
	private UnitMeasurementDAO dao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		entity = getEntity();
		dao = new UnitMeasurementDAOImpl(new ConnectionManager(EnviromentEnum.TEST));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		truncateCascade(UnitMeasurementDAOImpl.TABLE_NAME);
	}

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
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(entity.getId(), id);
		
		dao.delete(id);
		
		entity = null;
		entity = dao.findById(id);
		
		assertNull(entity);
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
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(entity.getId(), id);
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
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(entity.getId(), id);
		
		entity.setName("Pacote");
		entity = dao.update(entity);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		assertEquals(entity.getName(), "Pacote");
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
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(entity.getId(), id);
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
		
		assertTrue(null != units);
		assertTrue(!units.isEmpty());
		assertTrue(units.size() == 1 && null != units.get(0).getId());
		assertTrue(units.get(0).getName().equals("Litro"));
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
		
		assertTrue(null != units);
		assertTrue(!units.isEmpty());
		assertTrue(units.size() == 2 && null != units.get(0).getId());
	}
	
	@Test(expected = DAOException.class)
	public void testSaveUnitOfMeasurementWithNullValues() throws DAOException, SystemException, SQLException {
		
		entity.setName(null);
		dao.save(entity);
		
	}

	@Test(expected = DAOException.class)
	public void testUpdateUnitOfMeasurementToNullValues() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);
		
		Long id = getLastIdFrom(UnitMeasurementDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(entity.getId(), id);
		
		entity.setName(null);
		entity = dao.update(entity);
		
	}

	@Override
	protected UnitMeasurement getEntity() {
		return new UnitMeasurement("Unit Name " + ALIAS);
	}
}
