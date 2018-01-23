/**
 * 
 */
package br.com.psystems.crud.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.MapperException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.EnviromentTypeEnum;
import br.com.psystems.crud.mapper.impl.ProductMapper;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.model.dao.UnitMeasurementDAO;
import br.com.psystems.crud.model.dao.impl.UnitMeasurementDAOImpl;
import br.com.psystems.crud.service.UnitMeasurementService;
import br.com.psystems.crud.service.impl.UnitMeasurementServiceImpl;
import br.com.psystems.crud.test.builder.ProductBuilder;
import br.com.psystems.crud.test.integration.AbstractTest;

/**
 * @author developer
 *
 */
public class ProductMapperTest extends AbstractTest<Product> {
	
	private Product entity;
	private UnitMeasurement unitMeasurement;
	private UnitMeasurementService unitMeasurementService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		unitMeasurementService = new UnitMeasurementServiceImpl(new UnitMeasurementDAOImpl(new ConnectionManager(EnviromentTypeEnum.TEST)));
		entity = getEntity();
	}

	/**
	 * Test method for {@link br.com.psystems.crud.mapper.impl.ProductMapper#map(javax.servlet.http.HttpServletRequest)}.
	 * @throws MapperException 
	 */
	@Test
	public void testMap() throws MapperException {
		
		entity.setId(1L);
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("name")).thenReturn("Product Name " + ALIAS);
		when(request.getParameter("id")).thenReturn("1");
		when(request.getParameter("description")).thenReturn("Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI.");
		when(request.getParameter("minquantity")).thenReturn("42.8");
		when(request.getParameter("ownmanufacturing")).thenReturn("true");
		when(request.getParameter("price")).thenReturn("50.0");
		when(request.getParameter("unit")).thenReturn(unitMeasurement.getId().toString());
		when(request.getParameterValues("references")).thenReturn((String[]) Arrays.asList("1", "2", "3").toArray());
		when(request.getParameterValues("tags")).thenReturn((String[]) Arrays.asList("comida", "massa", "pasta").toArray());

		ProductMapper mapper = new ProductMapper();
		Product mapped = mapper.map(request);

		assertNotNull(mapped);
		assertNotNull(mapped.getId());
		assertNotNull(mapped.getName());
		assertNotNull(mapped.getDescription());
		assertNotNull(mapped.getMininumQuantity());
		assertNotNull(mapped.getOwnManufacturing());
		assertNotNull(mapped.getPrice());
		assertNotNull(mapped.getReferences());
		assertNotNull(mapped.getTags());
		assertNotNull(mapped.getUnitMeasurementId());
		
		assertEquals(mapped.getId(), entity.getId());
		assertEquals(mapped.getName(), entity.getName());
		assertEquals(mapped.getDescription(), entity.getDescription());
		assertEquals(mapped.getMininumQuantity(), entity.getMininumQuantity());
		assertEquals(mapped.getOwnManufacturing(), entity.getOwnManufacturing());
		assertEquals(mapped.getPrice(), entity.getPrice());
		assertEquals(mapped.getReferences(), entity.getReferences());
		assertEquals(mapped.getTags(), entity.getTags());
		assertEquals(mapped.getUnitMeasurementId(), entity.getUnitMeasurementId());
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
		unitMeasurementService.save(new UnitMeasurement("Unit Name " + ALIAS));
		unitMeasurement = unitMeasurementService.findById(getLastIdFrom(UnitMeasurementDAO.TABLE_NAME));
		return unitMeasurement;
	}
}
