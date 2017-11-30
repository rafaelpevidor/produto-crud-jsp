/**
 * 
 */
package br.com.psystems.crud.service;

import br.com.psystems.crud.infra.exception.BusinessException;
import br.com.psystems.crud.model.BaseEntity;

/**
 * @author developer
 *
 */
public abstract class AbstractService {

	public abstract void validateRequiredFieldsOf(BaseEntity entity) throws BusinessException;
}
