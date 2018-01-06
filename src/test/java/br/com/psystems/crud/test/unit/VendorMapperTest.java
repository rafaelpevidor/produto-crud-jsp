/**
 * 
 */
package br.com.psystems.crud.test.unit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

import br.com.psystems.crud.exception.MapperException;
import br.com.psystems.crud.mapper.impl.VendorMapper;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.test.builder.VendorBuilder;
import br.com.psystems.crud.test.integration.AbstractTest;

/**
 * @author developer
 *
 */
public class VendorMapperTest extends AbstractTest<Vendor> {

	private Vendor entity;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		entity = getEntity();
	}

	/**
	 * Test method for {@link br.com.psystems.crud.mapper.impl.VendorMapper#map(javax.servlet.http.HttpServletRequest)}.
	 * @throws MapperException 
	 */
	@Test
	public void testMap() throws MapperException {
		
		entity.setId(1L);
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("name")).thenReturn("Vendor Name " + ALIAS);
		when(request.getParameter("id")).thenReturn("1");

		VendorMapper mapper = new VendorMapper();
		Vendor mapped = mapper.map(request);

		assertNotNull(mapped);
		assertNotNull(mapped.getId());
		assertNotNull(mapped.getName());
		assertNotNull(mapped.getDescription());
		
		assertEquals(mapped.getId(), entity.getId());
		assertEquals(mapped.getName(), entity.getName());
		assertEquals(mapped.getDescription(), entity.getDescription());
	}

	@Override
	protected Vendor getEntity() {
		return new VendorBuilder()
				.setName("Vendor Name " + ALIAS)
				.setDescription("Em design gráfico e editoração, Lorem ipsum é um texto utilizado para preencher o espaço de texto em publicações, com a finalidade de verificar o lay-out, tipografia e formatação antes de utilizar conteúdo real. Muitas vezes este texto também é utilizado em catálogos de tipografia para demonstrar textos e títulos escritos com as fontes.")
				.build();
	}

}
