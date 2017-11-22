/**
 * 
 */
package br.com.psystems.crud.controller.bind;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.infra.exception.BindException;
import br.com.psystems.crud.model.BaseEntity;

/**
 * @author rafael.saldanha
 *
 */
abstract class AbstractBind <T extends BaseEntity> {
	
	public abstract T buildEntity(HttpServletRequest request) throws BindException;
}
