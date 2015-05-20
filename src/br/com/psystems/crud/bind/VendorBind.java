/**
 * 
 */
package br.com.psystems.crud.bind;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.exception.ConverterException;
import br.com.psystems.crud.model.domain.Vendor;
import br.com.psystems.crud.utils.ConverterUtils;

/**
 * @author rafael.saldanha
 *
 */
public final class VendorBind implements IBind<Vendor> {
	
	private static VendorBind instance;

	@Override
	public Vendor bind(HttpServletRequest request) throws ConverterException {
		
		Vendor fornecedor = new Vendor();
		try {
			fornecedor.setDescription((String) request.getParameter("description"));
			fornecedor.setNome((String) request.getParameter("name"));
			fornecedor.setId(
					ConverterUtils.convertStringToInteger((String) request.getParameter("id"))
			);
		} catch (Exception e) {
			throw new ConverterException(IBind.BIND_ERROR_MESSAGE, e);
		}
		return fornecedor;
	}

	public static VendorBind getInstance() {
		if (null == instance) {
			instance = new VendorBind();
		}
		return instance;
	}

}
