package br.com.psystems.crud.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import br.com.psystems.crud.model.dao.ProductDAO;
import br.com.psystems.crud.model.dao.impl.ProductDAOImpl;
import br.com.psystems.crud.test.builder.ProductBuilder;

public class ProductDAOTest extends AbstractTest<Product> {
	
	private Product entity;
	private ProductDAO dao;

	@Before
	public void setUp() throws Exception {
		entity = getEntity();
		dao = new ProductDAOImpl(new ConnectionManager(EnviromentTypeEnum.TEST));
	}

	@After
	public void tearDown() throws Exception {
		truncateCascade(ProductDAOImpl.TABLE_NAME);
	}

	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);
		
		Long id = getLastIdFrom(ProductDAOImpl.TABLE_NAME);
		
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
		
		Long id = getLastIdFrom(ProductDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
	}

	@Test
	public void testUpdateProduct() throws DAOException, SystemException, SQLException {
		
		dao.save(entity);
		
		Long id = getLastIdFrom(ProductDAOImpl.TABLE_NAME);
		
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
	}

	@Test
	public void testFindByIdLong() throws DAOException, SystemException, SQLException {

		dao.save(entity);
		
		Long id = getLastIdFrom(ProductDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
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
		
		Long id = getLastIdFrom(ProductDAOImpl.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		
		entity.setName(null);
		entity = dao.update(entity);
		
	}

	@Override
	protected Product getEntity() {
		return new ProductBuilder()
				.setDescription("Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI.")
				.setName("Product Name " + ALIAS)
				.setTags(Arrays.asList("comida", "massa", "pasta"))
				.setReferences(Arrays.asList(1L, 2L, 3L))
				.setMininumQuantity(new BigDecimal("42.8"))
				.build();
	}

}
