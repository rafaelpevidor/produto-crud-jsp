/**
 * 
 */
package br.com.psystems.crud.web.controller;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.exception.MapperException;
import br.com.psystems.crud.model.BaseEntity;

/**
 * @author developer
 *
 */
public interface Mappable {

	public BaseEntity map(HttpServletRequest request) throws MapperException;
}
