package br.com.psystems.crud.test.integration;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.ProductDAO;
import br.com.psystems.crud.model.dao.VendorDAO;
import br.com.psystems.crud.test.builder.ProductBuilder;
import br.com.psystems.crud.test.builder.VendorBuilder;

public class ProductDAOTest extends AbstractTest {
	
	private Product product;
	private ProductDAO dao;
	private Vendor vendor;
	private VendorDAO vendorDAO;

	@Before
	public void setUp() throws Exception {
		dao = new ProductDAO();
	
		vendorDAO = new VendorDAO();
		
		product = new ProductBuilder()
				.setDescription("Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI.")
				.setName("Product Name " + ALIAS)
				.setTags(Arrays.asList("nike", "tenis", "running"))
				.setReferences(Arrays.asList(1L, 2L, 3L))
				.build();
		
		vendor = new VendorBuilder()
				.setDescription("Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI.")
				.setName("Vendor Name " + ALIAS)
				.build();
		
//		truncate(ProductDAO.TABLE_NAME, true);
//		truncate(VendorDAO.TABLE_NAME, true);
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {
		
		vendorDAO.save(vendor);
		vendor = vendorDAO.findById(getLastIdFrom(VendorDAO.TABLE_NAME));
		
		Assert.assertNotNull(vendor);
		Assert.assertNotNull(vendor.getId());
		
		product.setVendor(vendor);
		
		dao.save(product);
		
		Long id = getLastIdFrom(ProductDAO.TABLE_NAME);
		
		product = null;
		product = dao.findById(id);
		
		Assert.assertNotNull(product);
		Assert.assertNotNull(product.getId());
		Assert.assertTrue(product.getId().equals(id));
		
		dao.delete(id);

		product = dao.findById(id);

		Assert.assertNull(product);
	}

	@Test
	public void testSaveProduct() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUpdateProduct() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testFindByIdLong() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testFindByNameString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented"); // TODO
	}

}
