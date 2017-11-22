/**
 * 
 */
package br.com.psystems.crud.controller.bind;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.psystems.crud.infra.exception.BindException;
import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.model.Vendor;

/**
 * @author rafael.saldanha
 *
 */
public final class VendorBind extends AbstractBind<Vendor> {
	
	@Override
	public Vendor buildEntity(HttpServletRequest request) throws BindException {
		
		Vendor fornecedor = new Vendor();
		try {
			fornecedor.setDescription((String) request.getParameter("description"));
			fornecedor.setName((String) request.getParameter("name"));
			fornecedor.setId(NumberUtils.createLong((String) request.getParameter("id")));
		} catch (Exception e) {
			throw new BindException(ConstantsUtils.BIND_ERROR_MESSAGE, e);
		}
		return fornecedor;
	}
}
