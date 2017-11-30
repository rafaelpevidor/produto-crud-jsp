/**
 * 
 */
package br.com.psystems.crud.mapper;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.psystems.crud.infra.exception.MapperException;
import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.model.UnitMeasurement;

/**
 * @author rafael.saldanha
 *
 */
public final class UnitMeasurementMapper implements EntityMapper<UnitMeasurement> {
	
	@Override
	public UnitMeasurement map(HttpServletRequest request) throws MapperException {
		
		UnitMeasurement unit = new UnitMeasurement();
		try {
			unit.setName((String) request.getParameter("name"));
			unit.setId(NumberUtils.createLong((String) request.getParameter("id")));
		} catch (Exception e) {
			throw new MapperException(ConstantsUtils.ERROR_MESSAGE_MAPPER_ERROR, e);
		}
		return unit;
	}
}
