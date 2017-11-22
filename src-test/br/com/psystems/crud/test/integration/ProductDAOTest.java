package br.com.psystems.crud.test.integration;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.ProductDAO;
import br.com.psystems.crud.model.dao.VendorDAO;
import br.com.psystems.crud.test.builder.ProductBuilder;
import br.com.psystems.crud.test.builder.VendorBuilder;

public class ProductDAOTest extends AbstractTest {
	
	private Product entity;
	private ProductDAO dao;
	private Vendor vendor;
	private VendorDAO vendorDAO;

	@Before
	public void setUp() throws Exception {

		dao = new ProductDAO();
		vendorDAO = new VendorDAO();
		
		entity = new ProductBuilder()
				.setDescription("Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI.")
				.setName("Product Name " + ALIAS)
				.setTags(Arrays.asList("comida", "massa", "pasta"))
				.setReferences(Arrays.asList(1L, 2L, 3L))
				.setMininumQuantity(new BigDecimal("42.8"))
				.build();
		
		vendor = new VendorBuilder()
				.setDescription("Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI.")
				.setName("Vendor Name " + ALIAS)
				.build();
		
		truncate(ProductDAO.TABLE_NAME, true);
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {
		
		vendorDAO.save(vendor);
		vendor = vendorDAO.findById(getLastIdFrom(VendorDAO.TABLE_NAME));
		
		Assert.assertNotNull(vendor);
		Assert.assertNotNull(vendor.getId());
		
		entity.setVendor(vendor);
		
		dao.save(entity);
		
		Long id = getLastIdFrom(ProductDAO.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertTrue(entity.getId().equals(id));
		
		dao.delete(id);

		entity = dao.findById(id);

		Assert.assertNull(entity);
	}

	@Test
	public void testSaveProduct() throws DAOException, SystemException, SQLException {
		
		vendorDAO.save(vendor);
		vendor = vendorDAO.findById(getLastIdFrom(VendorDAO.TABLE_NAME));
		
		Assert.assertNotNull(vendor);
		Assert.assertNotNull(vendor.getId());
		
		entity.setVendor(vendor);
		
		dao.save(entity);
		
		Long id = getLastIdFrom(ProductDAO.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertTrue(entity.getId().equals(id));
	}

	@Test
	public void testUpdateProduct() throws DAOException, SystemException, SQLException {
		
		vendorDAO.save(vendor);
		vendor = vendorDAO.findById(getLastIdFrom(VendorDAO.TABLE_NAME));
		
		Assert.assertNotNull(vendor);
		Assert.assertNotNull(vendor.getId());
		
		entity.setVendor(vendor);
		
		dao.save(entity);
		
		Long id = getLastIdFrom(ProductDAO.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertTrue(entity.getId().equals(id));
		
		entity.setName("espaguete");
		entity = dao.update(entity);
		
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertTrue(entity.getId().equals(id));
		Assert.assertEquals(entity.getName(), "espaguete");
	}

	@Test
	public void testFindByIdLong() throws DAOException, SystemException, SQLException {

		vendorDAO.save(vendor);
		vendor = vendorDAO.findById(getLastIdFrom(VendorDAO.TABLE_NAME));
		
		Assert.assertNotNull(vendor);
		Assert.assertNotNull(vendor.getId());
		
		entity.setVendor(vendor);
		
		dao.save(entity);
		
		Long id = getLastIdFrom(ProductDAO.TABLE_NAME);
		
		entity = null;
		entity = dao.findById(id);
		
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertTrue(entity.getId().equals(id));
	}

	@Test
	public void testFindByNameString() throws DAOException, SystemException, SQLException {
		
		vendorDAO.save(vendor);
		vendor = vendorDAO.findById(getLastIdFrom(VendorDAO.TABLE_NAME));
		
		Assert.assertNotNull(vendor);
		Assert.assertNotNull(vendor.getId());
		
		entity.setVendor(vendor);
		entity.setName("espaguete a bolonhesa");
		
		dao.save(entity);
		
		entity.setName("espaguete alho e óleo");
		
		dao.save(entity);
		
		List<Product> products = dao.findByName("bolo");
		
		Assert.assertTrue(null != products);
		Assert.assertTrue(!products.isEmpty());
		Assert.assertTrue(products.size() == 1 && null != products.get(0).getId());
		Assert.assertTrue(products.get(0).getName().equals("espaguete a bolonhesa"));
	}

	@Test
	public void testGetAll() throws DAOException, SystemException, SQLException {
		
		vendorDAO.save(vendor);
		vendor = vendorDAO.findById(getLastIdFrom(VendorDAO.TABLE_NAME));
		
		Assert.assertNotNull(vendor);
		Assert.assertNotNull(vendor.getId());
		
		entity.setVendor(vendor);
		entity.setName("espaguete a bolonhesa");
		
		dao.save(entity);
		
		entity.setName("bolo de aipim");
		
		dao.save(entity);
		
		List<Product> products = dao.getAll();
		
		Assert.assertTrue(null != products);
		Assert.assertTrue(!products.isEmpty());
		Assert.assertTrue(products.size() == 2 && null != products.get(0).getId());
	}

}
