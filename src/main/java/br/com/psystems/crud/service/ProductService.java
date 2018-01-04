/**
 * 
 */
package br.com.psystems.crud.service;

import br.com.psystems.crud.exception.BusinessException;
import br.com.psystems.crud.model.Product;

/**
 * @author developer
 *
 */
public interface ProductService extends BaseService<Product> {

	public void validateRequiredFieldByBusinessRuleOf(Product entity) throws BusinessException;
}
