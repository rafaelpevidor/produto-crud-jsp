/**
 * 
 */
package br.com.psystems.crud.mapper;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.psystems.crud.infra.exception.MapperException;
import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.model.Product;

/**
 * @author rafael.saldanha
 *
 */
public final class ProductMapper implements EntityMapper<Product> {
	
	@Override
	public Product map(HttpServletRequest request) throws MapperException {
		
		Product produto = new Product();
		try {
			produto.setDescription(request.getParameter("description"));
			produto.setName(request.getParameter("name"));
			produto.setVendorId(NumberUtils.createLong((String) request.getParameter("vendorid")));
			produto.setId(NumberUtils.createLong((String) request.getParameter("id")));
			
		} catch (Exception e) {
			throw new MapperException(ConstantsUtils.ERROR_MESSAGE_MAPPER_ERROR, e);
		}
		return produto;
	}
}
