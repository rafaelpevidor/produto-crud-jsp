/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rafael.saldanha
 *
 */
public interface BaseCommand extends Serializable {

	public String execute(HttpServletRequest request);
}
