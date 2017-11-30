/**
 * 
 */
package br.com.psystems.crud.mapper;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.psystems.crud.infra.exception.MapperException;
import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.model.Vendor;

/**
 * @author rafael.saldanha
 *
 */
public final class VendorMapper implements EntityMapper<Vendor> {
	
	@Override
	public Vendor map(HttpServletRequest request) throws MapperException {
		
		Vendor vendor = new Vendor();
		try {
			vendor.setDescription((String) request.getParameter("description"));
			vendor.setName((String) request.getParameter("name"));
			vendor.setId(NumberUtils.createLong((String) request.getParameter("id")));
		} catch (Exception e) {
			throw new MapperException(ConstantsUtils.ERROR_MESSAGE_MAPPER_ERROR, e);
		}
		return vendor;
	}
}
