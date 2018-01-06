/**
 * 
 */
package br.com.psystems.crud.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

import br.com.psystems.crud.exception.MapperException;
import br.com.psystems.crud.mapper.impl.ProductMapper;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.test.builder.ProductBuilder;
import br.com.psystems.crud.test.integration.AbstractTest;

/**
 * @author developer
 *
 */
public class ProductMapperTest extends AbstractTest<Product> {
	
	private Product entity;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
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
		when(request.getParameter("name")).thenReturn("Vendor Name " + ALIAS);
		when(request.getParameter("id")).thenReturn("1");

		ProductMapper mapper = new ProductMapper();
		Product mapped = mapper.map(request);

		assertNotNull(mapped);
		assertNotNull(mapped.getId());
		assertNotNull(mapped.getName());
		assertNotNull(mapped.getDescription());
		
		assertEquals(mapped.getId(), entity.getId());
		assertEquals(mapped.getName(), entity.getName());
		assertEquals(mapped.getDescription(), entity.getDescription());
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
