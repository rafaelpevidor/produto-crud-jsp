/**
 * 
 */
package br.com.psystems.crud.mapper.impl;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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
	
	public ProductMapper() {
		super();
	}

//	private UnitMeasurementService service;
	
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
			produto.setUnitMeasurementId(NumberUtils.createLong(request.getParameter("unit")));
			produto.setReferences(getReferencesFrom(request.getParameterValues("references")));
			produto.setTags(getTagsFrom(request.getParameterValues("tags")));
//			produto.setUnitMeasurement(getUnitBy(NumberUtils.createLong(request.getParameter("unit"))));
			
		} catch (Exception e) {
			throw new MapperException(Constants.ERROR_MESSAGE_MAPPER_ERROR, e);
		}
		return produto;
	}
	
	private Set<Long> getReferencesFrom(String[] referenceList) {
		return Arrays.asList(referenceList).stream().map(Long::new).collect(Collectors.toSet());
	}

//	private UnitMeasurementService getService() throws DAOException {
//		if (service == null)
//			new UnitMeasurementServiceImpl(new UnitMeasurementDAOImpl());
//		return service;
//	}
	
//	private UnitMeasurement getUnitBy(Long id) throws DAOException, SystemException {
//		return getService().findById(id);
//	}
	
	private Set<String> getTagsFrom(String[] tagList) {
		return Arrays.asList(tagList).stream().collect(Collectors.toSet());
	}
}
