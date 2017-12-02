/**
 * 
 */
package br.com.psystems.crud.mapper;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.infra.exception.MapperException;
import br.com.psystems.crud.model.BaseEntity;

/**
 * @author rafael.saldanha
 *
 */
public interface BaseMapper <T extends BaseEntity> {
	
	public abstract T map(HttpServletRequest request) throws MapperException;
}
