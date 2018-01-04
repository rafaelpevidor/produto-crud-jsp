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

import br.com.psystems.crud.exception.BusinessException;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.EnviromentTypeEnum;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.model.dao.impl.UnitMeasurementDAOImpl;
import br.com.psystems.crud.service.UnitMeasurementService;
import br.com.psystems.crud.service.impl.UnitMeasurementServiceImpl;

public class UnitMeasurementServiceTest extends AbstractTest<UnitMeasurement> {
	
	private UnitMeasurement entity;
	private UnitMeasurementService service;

	@Before
	public void setUp() throws Exception {
		entity = getEntity();
		service = new UnitMeasurementServiceImpl(new UnitMeasurementDAOImpl(new ConnectionManager(EnviromentTypeEnum.TEST)));
	}

	@After
	public void tearDown() throws Exception {
		truncateCascade(UnitMeasurementDAOImpl.TABLE_NAME);
	}

	@Test
	public void testSave() throws DAOException, SystemException, SQLException {
		
		service.save(entity);
		
		Long id = getLastIdFrom(UnitMeasurementDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = service.findById(id);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(entity.getId(), id);
	}

	@Test
	public void testUpdate() throws DAOException, SystemException, SQLException {
		
		service.save(entity);
		
		Long id = getLastIdFrom(UnitMeasurementDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = service.findById(id);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(entity.getId(), id);
		
		entity.setName("Pacote");
		entity = service.update(entity);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		assertEquals(entity.getName(), "Pacote");
	}

	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {
		
		service.save(entity);
		
		Long id = getLastIdFrom(UnitMeasurementDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = service.findById(id);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(entity.getId(), id);
		
		service.delete(id);
		
		entity = null;
		entity = service.findById(id);
		
		assertNull(entity);
	}

	@Test
	public void testFindById() throws DAOException, SystemException, SQLException {
		
		service.save(entity);
		
		Long id = getLastIdFrom(UnitMeasurementDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = service.findById(id);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(entity.getId(), id);
	}

	@Test
	public void testFindByName() throws DAOException, SystemException {
		
		entity.setName("Litro");

		service.save(entity);
		
		entity.setName("Quilo");

		service.save(entity);
		
		List<UnitMeasurement> units = service.findByName("tro");
		
		assertTrue(null != units);
		assertTrue(!units.isEmpty());
		assertTrue(units.size() == 1 && null != units.get(0).getId());
		assertTrue(units.get(0).getName().equals("Litro"));
	}

	@Test
	public void testGetAll() throws DAOException, SystemException {
		
		entity.setName("Litro");

		service.save(entity);
		
		entity.setName("Quilo");

		service.save(entity);
		
		List<UnitMeasurement> units = service.getAll();
		
		assertTrue(null != units);
		assertTrue(!units.isEmpty());
		assertTrue(units.size() == 2 && null != units.get(0).getId());
	}

	@Test(expected = BusinessException.class)
	public void testValidateRequiredFieldsOf() throws BusinessException, SystemException {
		entity.setName(null);
		service.validateRequiredFieldsOf(entity);
	}

	@Override
	protected UnitMeasurement getEntity() {
		return new UnitMeasurement("Unit Name " + ALIAS);
	}

}
