/**
 * 
 */
package br.com.psystems.crud.mapper.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import br.com.psystems.crud.exception.MapperException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.mapper.BaseMapper;
import br.com.psystems.crud.model.Product;

/**
 * @author rafael.saldanha
 *
 */
public final class ProductMapper implements BaseMapper<Product> {
	
	@Override
	public Product map(HttpServletRequest request) throws MapperException {
		
		Product produto = new Product();
		try {
			produto.setDescription(request.getParameter("description"));
			produto.setName(request.getParameter("name"));
			produto.setId(NumberUtils.createLong((String) request.getParameter("id")));
			produto.setMininumQuantity(NumberUtils.createBigDecimal(request.getParameter("minquantity")));
			produto.setOwnManufacturing(BooleanUtils.toBooleanObject(request.getParameter("ownmanufacturing")));
			produto.setPrice(NumberUtils.createBigDecimal(request.getParameter("price")));
			produto.setReferences(null);
			produto.setTags(null);//FIXME desenvolver lógica do parse
			produto.setUnitMeasurementId(NumberUtils.createLong(request.getParameter("unit")));
			
		} catch (Exception e) {
			throw new MapperException(Constants.ERROR_MESSAGE_MAPPER_ERROR, e);
		}
		return produto;
	}
}
