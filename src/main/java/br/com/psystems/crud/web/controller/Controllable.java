/**
 * 
 */
package br.com.psystems.crud.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rafael.saldanha
 *
 */
public interface Controllable extends Serializable {

	public String execute(HttpServletRequest request);
	
}
