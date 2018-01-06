/**
 * 
 */
package br.com.psystems.crud.test.unit;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

import br.com.psystems.crud.exception.MapperException;
import br.com.psystems.crud.mapper.impl.UnitMeasurementMapper;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.test.integration.AbstractTest;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * @author developer
 *
 */
public class UnitMeasurementMapperTest extends AbstractTest<UnitMeasurement>  {

	private UnitMeasurement entity;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		entity = getEntity();
	}

	/**
	 * Test method for {@link br.com.psystems.crud.mapper.impl.UnitMeasurementMapper#map(javax.servlet.http.HttpServletRequest)}.
	 * @throws MapperException 
	 */
	@Test
	public void testMap() throws MapperException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("name")).thenReturn("Unit Name " + ALIAS);
		when(request.getParameter("id")).thenReturn("1");

		UnitMeasurementMapper mapper = new UnitMeasurementMapper();
		UnitMeasurement mapped = mapper.map(request);

		assertNotNull(mapped);
		assertNotNull(mapped.getId());
		assertNotNull(mapped.getName());
		assertEquals(mapped.getId(), entity.getId());
		assertEquals(mapped.getName(), entity.getName());
	}

	@Override
	protected UnitMeasurement getEntity() {
		return new UnitMeasurement(1L, "Unit Name " + ALIAS);
	}

}
