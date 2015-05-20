/**
 * 
 */
package br.com.psystems.crud.bind;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.exception.ConverterException;
import br.com.psystems.crud.model.domain.Product;
import br.com.psystems.crud.utils.ConverterUtils;

/**
 * @author rafael.saldanha
 *
 */
public final class ProductBind implements IBind<Product> {
	
	private static ProductBind instance;

	@Override
	public Product bind(HttpServletRequest request) throws ConverterException {
		
		Product produto = new Product();
		try {
			produto.setDescription((String) request.getParameter("description"));
			produto.setAllotment((String) request.getParameter("allotment"));
			produto.setName((String) request.getParameter("name"));
			produto.setVendorId(
					ConverterUtils.convertStringToInteger((String) request.getParameter("vendorid"))
			);
			produto.setAmount(
					ConverterUtils.convertStringToDouble((String) request.getParameter("amount"))
			);
			produto.setPurchasePrice(
					ConverterUtils.convertStringToDouble((String) request.getParameter("purchaseprice"))
			);
			produto.setSalePrice(
					ConverterUtils.convertStringToDouble((String) request.getParameter("saleprice"))
			);
			produto.setId(
					ConverterUtils.convertStringToInteger((String) request.getParameter("id"))
			);
		} catch (Exception e) {
			throw new ConverterException(IBind.BIND_ERROR_MESSAGE, e);
		}
		return produto;
	}

	public static ProductBind getInstance() {
		if (null == instance) {
			instance = new ProductBind();
		}
		return instance;
	}

}
