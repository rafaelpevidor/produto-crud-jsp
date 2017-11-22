/**
 * 
 */
package br.com.psystems.crud.controller.bind;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.psystems.crud.infra.exception.BindException;
import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.model.Product;

/**
 * @author rafael.saldanha
 *
 */
public final class ProductBind extends AbstractBind<Product> {
	
	@Override
	public Product buildEntity(HttpServletRequest request) throws BindException {
		
		Product produto = new Product();
		try {
			produto.setDescription(request.getParameter("description"));
			produto.setName(request.getParameter("name"));
			produto.setVendorId(NumberUtils.createLong((String) request.getParameter("vendorid")));
			produto.setId(NumberUtils.createLong((String) request.getParameter("id")));
			
		} catch (Exception e) {
			throw new BindException(ConstantsUtils.BIND_ERROR_MESSAGE, e);
		}
		return produto;
	}
}
