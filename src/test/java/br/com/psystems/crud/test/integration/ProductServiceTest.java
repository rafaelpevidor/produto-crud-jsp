/**
 * 
 */
package br.com.psystems.crud.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import br.com.psystems.crud.model.dao.impl.ProductDAOImpl;
import br.com.psystems.crud.service.ProductService;
import br.com.psystems.crud.service.impl.ProductServiceImpl;
import br.com.psystems.crud.test.builder.ProductBuilder;

/**
 * @author developer
 *
 */
public class ProductServiceTest extends AbstractTest<Product> {

	private Product entity;
	private ProductService service;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		entity = getEntity();
		service = new ProductServiceImpl(new ProductDAOImpl(new ConnectionManager(EnviromentTypeEnum.TEST)));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		truncateCascade(ProductDAOImpl.TABLE_NAME);
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.ProductServiceImpl#save(br.com.psystems.crud.model.Product)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testSave() throws DAOException, SystemException, SQLException {
		
		service.save(entity);
		
		Long id = getLastIdFrom(ProductDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = service.findById(id);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.ProductServiceImpl#update(br.com.psystems.crud.model.Product)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testUpdate() throws DAOException, SystemException, SQLException {
		
		service.save(entity);
		
		Long id = getLastIdFrom(ProductDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = service.findById(id);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		
		entity.setName("espaguete");
		entity = service.update(entity);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		assertEquals(entity.getName(), "espaguete");
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.ProductServiceImpl#delete(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {
		
		service.save(entity);
		
		Long id = getLastIdFrom(ProductDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = service.findById(id);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		
		service.delete(id);

		entity = service.findById(id);

		assertNull(entity);
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.ProductServiceImpl#findById(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testFindById() throws DAOException, SystemException, SQLException {

		service.save(entity);
		
		Long id = getLastIdFrom(ProductDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = service.findById(id);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.ProductServiceImpl#findByName(java.lang.String)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testFindByName() throws DAOException, SystemException {

		entity.setName("espaguete a bolonhesa");
		
		service.save(entity);
		
		entity.setName("espaguete alho e óleo");
		
		service.save(entity);
		
		List<Product> products = service.findByName("bolo");
		
		assertTrue(null != products);
		assertTrue(!products.isEmpty());
		assertTrue(products.size() == 1 && null != products.get(0).getId());
		assertTrue(products.get(0).getName().equals("espaguete a bolonhesa"));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.ProductServiceImpl#getAll()}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testGetAll() throws DAOException, SystemException {
		
		entity.setName("espaguete a bolonhesa");
		
		service.save(entity);
		
		entity.setName("bolo de aipim");
		
		service.save(entity);
		
		List<Product> products = service.getAll();
		
		assertTrue(null != products);
		assertTrue(!products.isEmpty());
		assertTrue(products.size() == 2 && null != products.get(0).getId());
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.ProductServiceImpl#validateRequiredFieldsOf(br.com.psystems.crud.model.Product)}.
	 */
	@Test
	public void testValidateRequiredFieldsOf() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.ProductServiceImpl#validateRequiredFieldByBusinessRuleOf(br.com.psystems.crud.model.Product)}.
	 */
	@Test
	public void testValidateRequiredFieldByBusinessRuleOf() {
		fail("Not yet implemented"); // TODO
	}

	@Override
	protected Product getEntity() {
		return new ProductBuilder()
				.setDescription("Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI.")
				.setName("Product Name " + ALIAS)
				.setTags(Arrays.asList("comida", "massa", "pasta"))
				.setReferences(Arrays.asList(1L, 2L, 3L))
				.setMininumQuantity(new BigDecimal("42.8"))
				.setPrice(new BigDecimal("50.0"))
				.setOwnManufacturing(true)
				.setUnitMeasurement(getUnit())
				.build();
	}
	
	private UnitMeasurement getUnit() {
		return new UnitMeasurement(1L, "Unit Name " + ALIAS);
	}

}
