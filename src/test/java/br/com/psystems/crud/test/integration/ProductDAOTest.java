package br.com.psystems.crud.test.integration;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.EnviromentTypeEnum;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.model.dao.ProductDAO;
import br.com.psystems.crud.model.dao.UnitMeasurementDAO;
import br.com.psystems.crud.model.dao.impl.ProductDAOImpl;
import br.com.psystems.crud.model.dao.impl.UnitMeasurementDAOImpl;
import br.com.psystems.crud.test.builder.ProductBuilder;

public class ProductDAOTest extends AbstractTest<Product> {

	private Product entity;
	private ProductDAO dao;
	private UnitMeasurement unitMeasurement;
	private UnitMeasurementDAO unitMeasurementDAO;

	@Before
	public void setUp() throws Exception {
		unitMeasurementDAO = new UnitMeasurementDAOImpl(new ConnectionManager(EnviromentTypeEnum.TEST));
		dao = new ProductDAOImpl(new ConnectionManager(EnviromentTypeEnum.TEST));
		entity = getEntity();
	}

	@After
	public void tearDown() throws Exception {
		truncateCascade(ProductDAO.TABLE_NAME);
		truncateCascade(UnitMeasurementDAO.TABLE_NAME);
	}

	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(ProductDAO.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));

		dao.delete(id);

		entity = dao.findById(id);

		assertNull(entity);
	}

	@Test
	public void testSaveProduct() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);

		Long id = getLastIdFrom(ProductDAO.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		assertTrue(entity.getUnitMeasurementId().equals(unitMeasurement.getId()));
	}

	@Test
	public void testUpdateProduct() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);

		Long id = getLastIdFrom(ProductDAO.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));

		entity.setName("espaguete");
		entity = dao.update(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		assertEquals(entity.getName(), "espaguete");
		assertTrue(entity.getUnitMeasurementId().equals(unitMeasurement.getId()));
	}

	@Test
	public void testFindByIdLong() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(ProductDAO.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		assertTrue(entity.getUnitMeasurementId().equals(unitMeasurement.getId()));
	}

	@Test
	public void testFindByNameString() throws DAOException, SystemException, SQLException {

		entity.setName("espaguete a bolonhesa");

		dao.save(entity);

		entity.setName("espaguete alho e óleo");

		dao.save(entity);

		List<Product> products = dao.findByName("bolo");

		assertTrue(null != products);
		assertTrue(!products.isEmpty());
		assertTrue(products.size() == 1 && null != products.get(0).getId());
		assertTrue(products.get(0).getName().equals("espaguete a bolonhesa"));
	}

	@Test
	public void testGetAll() throws DAOException, SystemException, SQLException {

		entity.setName("espaguete a bolonhesa");

		dao.save(entity);

		entity.setName("bolo de aipim");

		dao.save(entity);

		List<Product> products = dao.getAll();

		assertTrue(null != products);
		assertTrue(!products.isEmpty());
		assertTrue(products.size() == 2 && null != products.get(0).getId());
	}

	@Test(expected = DAOException.class)
	public void testSaveProductWithNullValues() throws DAOException, SystemException, SQLException {

		entity.setName(null);
		dao.save(entity);
	}

	@Test(expected = DAOException.class)
	public void testUpdateProductToNullValues() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(ProductDAO.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));

		entity.setName(null);
		entity = dao.update(entity);
	}

	@Override
	protected Product getEntity() throws DAOException, SystemException, SQLException {
		return new ProductBuilder()
				.setDescription("Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI.")
				.setName("Product Name " + ALIAS)
				.setTagsList(Arrays.asList("comida", "massa", "pasta"))
				.setReferencesList(Arrays.asList(1L, 2L, 3L))
				.setMininumQuantity(new BigDecimal("42.8"))
				.setPrice(new BigDecimal("50.0"))
				.setOwnManufacturing(true)
				.setUnitMeasurement(getUnit())
				.build();
	}

	private UnitMeasurement getUnit() throws DAOException, SystemException, SQLException {
		unitMeasurementDAO.save(new UnitMeasurement("Unit Name " + ALIAS));
		unitMeasurement = unitMeasurementDAO.findById(getLastIdFrom(UnitMeasurementDAO.TABLE_NAME));
		return unitMeasurement;
	}

}
