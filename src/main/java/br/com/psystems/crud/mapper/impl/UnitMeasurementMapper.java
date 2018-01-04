/**
 * 
 */
package br.com.psystems.crud.mapper.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.psystems.crud.exception.MapperException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.mapper.BaseMapper;
import br.com.psystems.crud.model.UnitMeasurement;

/**
 * @author rafael.saldanha
 *
 */
public final class UnitMeasurementMapper implements BaseMapper<UnitMeasurement> {
	
	@Override
	public UnitMeasurement map(HttpServletRequest request) throws MapperException {
		
		UnitMeasurement unit = new UnitMeasurement();
		try {
			unit.setName((String) request.getParameter("name"));
			unit.setId(NumberUtils.createLong((String) request.getParameter("id")));
		} catch (Exception e) {
			throw new MapperException(Constants.ERROR_MESSAGE_MAPPER_ERROR, e);
		}
		return unit;
	}
}
