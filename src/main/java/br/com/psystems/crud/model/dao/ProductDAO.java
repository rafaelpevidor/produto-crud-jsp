/**
 * 
 */
package br.com.psystems.crud.model.dao;

import br.com.psystems.crud.model.Product;

/**
 * @author developer
 *
 */
public interface ProductDAO extends CrudBaseDAO<Product> {

	public static final String TABLE_NAME = "tb_product";
}
