/**
 * 
 */
package br.com.psystems.crud.service.impl;

import java.io.Serializable;

import br.com.psystems.crud.model.dao.BaseDAO;

/**
 * @author developer
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractCrudService implements Serializable {

	public AbstractCrudService(BaseDAO dao) {
		
	}
	
}
