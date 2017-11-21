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

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.VendorDAO;
import br.com.psystems.crud.test.builder.VendorBuilder;

/**
 * @author developer
 *
 */
public class VendorDAOTest extends AbstractTest {
	
	private Vendor vendor;
	private VendorDAO dao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new VendorDAO();
		vendor = new VendorBuilder()
				.setName("Vendor Name " + ALIAS)
				.setDescription("Em design gráfico e editoração, Lorem ipsum é um texto utilizado para preencher o espaço de texto em publicações, com a finalidade de verificar o lay-out, tipografia e formatação antes de utilizar conteúdo real. Muitas vezes este texto também é utilizado em catálogos de tipografia para demonstrar textos e títulos escritos com as fontes.")
				.build();
		
		truncate(VendorDAO.TABLE_NAME, true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.VendorDAO#delete(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {
		
		dao.save(vendor);
		
		Long id = getLastIdFrom(VendorDAO.TABLE_NAME);
		
		vendor = null;
		vendor = dao.findById(id);
		
		Assert.assertNotNull(vendor);
		Assert.assertNotNull(vendor.getId());
		Assert.assertEquals(vendor.getId(), id);
		
		dao.delete(id);
		
		vendor = null;
		vendor = dao.findById(id);
		
		Assert.assertNull(vendor);
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.VendorDAO#save(br.com.psystems.crud.model.Vendor)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testSaveVendor() throws DAOException, SystemException, SQLException {
		
		dao.save(vendor);
		
		Long id = getLastIdFrom(VendorDAO.TABLE_NAME);
		
		vendor = null;
		vendor = dao.findById(id);
		
		Assert.assertNotNull(vendor);
		Assert.assertTrue(vendor.getId().equals(id));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.VendorDAO#update(br.com.psystems.crud.model.Vendor)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testUpdateVendor() throws DAOException, SystemException, SQLException {
		
		dao.save(vendor);
		
		Long id = getLastIdFrom(VendorDAO.TABLE_NAME);
		
		vendor = null;
		vendor = dao.findById(id);
		
		Assert.assertNotNull(vendor);
		Assert.assertNotNull(vendor.getId());
		Assert.assertTrue(vendor.getId().equals(id));
		
		vendor.setName("Fornecedor Premium");
		
		dao.update(vendor);
		
		vendor = null;
		vendor = dao.findById(id);
		
		Assert.assertNotNull(vendor);
		Assert.assertNotNull(vendor.getId());
		Assert.assertTrue(vendor.getId().equals(id));
		Assert.assertEquals(vendor.getName(), "Fornecedor Premium");
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.VendorDAO#findById(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testFindByIdLong() throws DAOException, SystemException, SQLException {
		
		dao.save(vendor);
		
		Long id = getLastIdFrom(VendorDAO.TABLE_NAME);
		
		vendor = null;
		vendor = dao.findById(id);
		
		Assert.assertNotNull(vendor);
		Assert.assertNotNull(vendor.getId());
		Assert.assertTrue(vendor.getId().equals(id));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.VendorDAO#findByName(java.lang.String)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testFindByNameString() throws DAOException, SystemException {
		
		vendor.setName("Rafael Pevidor");

		dao.save(vendor);
		
		List<Vendor> vendors = dao.findByName("rafael");
		
		Assert.assertTrue(null != vendors);
		Assert.assertTrue(!vendors.isEmpty());
		Assert.assertTrue(vendors.size() == 1 && null != vendors.get(0).getId());
		Assert.assertTrue(vendors.get(0).getName().equals("Rafael Pevidor"));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.VendorDAO#getAll()}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testGetAll() throws DAOException, SystemException {
		
		dao.save(vendor);
		
		List<Vendor> vendors = dao.getAll();
		
		Assert.assertTrue(null != vendors);
		Assert.assertTrue(!vendors.isEmpty());
		Assert.assertTrue(1 == vendors.size());
	}

}
