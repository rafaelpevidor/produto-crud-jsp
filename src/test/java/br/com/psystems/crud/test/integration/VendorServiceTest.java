/**
 * 
 */
package br.com.psystems.crud.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.psystems.crud.infra.ConnectionFactory.EnviromentEnum;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.exception.BusinessException;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.impl.VendorDAO;
import br.com.psystems.crud.service.VendorService;
import br.com.psystems.crud.service.impl.VendorServiceImpl;
import br.com.psystems.crud.test.builder.VendorBuilder;

/**
 * @author developer
 *
 */
public class VendorServiceTest extends AbstractTest<Vendor> {

	private Vendor entity;
	private VendorService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		entity = getEntity();
		service = new VendorServiceImpl(new VendorDAO(new ConnectionManager(EnviromentEnum.DEV)));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		truncateCascade(VendorDAO.TABLE_NAME);
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.VendorServiceImpl#save(br.com.psystems.crud.model.Vendor)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testSave() throws DAOException, SystemException, SQLException {

		service.save(entity);

		Long id = getLastIdFrom(VendorDAO.TABLE_NAME);

		entity = null;
		entity = service.findById(id);

		assertNotNull(entity);
		assertTrue(entity.getId().equals(id));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.VendorServiceImpl#update(br.com.psystems.crud.model.Vendor)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testUpdate() throws DAOException, SystemException, SQLException {

		service.save(entity);

		Long id = getLastIdFrom(VendorDAO.TABLE_NAME);

		entity = null;
		entity = service.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));

		entity.setName("Fornecedor Premium");
		entity = service.update(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
		assertEquals(entity.getName(), "Fornecedor Premium");
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.VendorServiceImpl#delete(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testDelete() throws DAOException, SystemException, SQLException {

		service.save(entity);

		Long id = getLastIdFrom(VendorDAO.TABLE_NAME);

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

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.VendorServiceImpl#findById(java.lang.Long)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	@Test
	public void testFindById() throws DAOException, SystemException, SQLException {

		service.save(entity);

		Long id = getLastIdFrom(VendorDAO.TABLE_NAME);

		entity = null;
		entity = service.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertTrue(entity.getId().equals(id));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.VendorServiceImpl#findByName(java.lang.String)}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testFindByName() throws DAOException, SystemException {

		entity.setName("Rafael Pevidor");

		service.save(entity);

		List<Vendor> vendors = service.findByName("rafael");

		assertTrue(null != vendors);
		assertTrue(!vendors.isEmpty());
		assertTrue(vendors.size() == 1 && null != vendors.get(0).getId());
		assertTrue(vendors.get(0).getName().equals("Rafael Pevidor"));
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.VendorServiceImpl#getAll()}.
	 * @throws SystemException 
	 * @throws DAOException 
	 */
	@Test
	public void testGetAll() throws DAOException, SystemException {

		service.save(entity);

		List<Vendor> vendors = service.getAll();

		assertTrue(null != vendors);
		assertTrue(!vendors.isEmpty());
		assertTrue(1 == vendors.size());
	}

	/**
	 * Test method for {@link br.com.psystems.crud.service.impl.VendorServiceImpl#validateRequiredFieldsOf(br.com.psystems.crud.model.Vendor)}.
	 */
	@Test(expected = BusinessException.class)
	public void testValidateRequiredFieldsOf() {
		fail("Not yet implemented"); // TODO
	}

	@Override
	protected Vendor getEntity() {
		return new VendorBuilder()
				.setName("Vendor Name " + ALIAS)
				.setDescription("Em design gráfico e editoração, Lorem ipsum é um texto utilizado para preencher o espaço de texto em publicações, com a finalidade de verificar o lay-out, tipografia e formatação antes de utilizar conteúdo real. Muitas vezes este texto também é utilizado em catálogos de tipografia para demonstrar textos e títulos escritos com as fontes.")
				.build();
	}

}
