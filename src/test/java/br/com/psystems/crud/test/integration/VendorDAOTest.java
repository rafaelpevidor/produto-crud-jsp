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

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.EnviromentTypeEnum;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.VendorDAO;
import br.com.psystems.crud.model.dao.impl.VendorDAOImpl;
import br.com.psystems.crud.test.builder.VendorBuilder;

/**
 * @author developer
 *
 */
public class VendorDAOTest extends AbstractTest<Vendor> {

	private Vendor entity;
	private VendorDAO dao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new VendorDAOImpl(new ConnectionManager(EnviromentTypeEnum.TEST));
		entity = getEntity();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		truncateCascade(VendorDAOImpl.TABLE_NAME);
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.VendorDAOImpl#delete(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(VendorDAOImpl.TABLE_NAME);

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
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.VendorDAOImpl#save(br.com.psystems.crud.model.Vendor)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testSaveVendor() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(VendorDAOImpl.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		assertNotNull(entity);
		assertTrue(entity.getId().equals(id));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.VendorDAOImpl#update(br.com.psystems.crud.model.Vendor)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testUpdateVendor() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(VendorDAOImpl.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));

		entity.setName("Fornecedor Premium");
		entity = dao.update(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		assertEquals(entity.getName(), "Fornecedor Premium");
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.VendorDAOImpl#findById(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testFindByIdLong() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(VendorDAOImpl.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.VendorDAOImpl#findByName(java.lang.String)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testFindByNameString() throws DAOException, SystemException {

		entity.setName("Rafael Pevidor");

		dao.save(entity);

		List<Vendor> vendors = dao.findByName("rafael");

		assertTrue(null != vendors);
		assertTrue(!vendors.isEmpty());
		assertTrue(vendors.size() == 1 && null != vendors.get(0).getId());
		assertTrue(vendors.get(0).getName().equals("Rafael Pevidor"));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.model.dao.impl.VendorDAOImpl#getAll()}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testGetAll() throws DAOException, SystemException {

		dao.save(entity);

		List<Vendor> vendors = dao.getAll();

		assertTrue(null != vendors);
		assertTrue(!vendors.isEmpty());
		assertTrue(1 == vendors.size());
	}
	
	@Test(expected = DAOException.class)
	public void testSaveVendorWithNullValues() throws DAOException, SystemException, SQLException {

		entity.setName(null);
		dao.save(entity);

	}

	@Test(expected = DAOException.class)
	public void testUpdateVendorToNullValues() throws DAOException, SystemException, SQLException {

		dao.save(entity);

		Long id = getLastIdFrom(VendorDAOImpl.TABLE_NAME);

		entity = null;
		entity = dao.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));

		entity.setName(null);
		entity = dao.update(entity);

	}

	@Override
	protected Vendor getEntity() {
		return new VendorBuilder()
				.setName("Vendor Name " + ALIAS)
				.setDescription("Em design gráfico e editoração, Lorem ipsum é um texto utilizado para preencher o espaço de texto em publicações, com a finalidade de verificar o lay-out, tipografia e formatação antes de utilizar conteúdo real. Muitas vezes este texto também é utilizado em catálogos de tipografia para demonstrar textos e títulos escritos com as fontes.")
				.build();
	}
}
