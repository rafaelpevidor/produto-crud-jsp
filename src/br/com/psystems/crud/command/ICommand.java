/**
 * 
 */
package br.com.psystems.crud.command;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rafael.saldanha
 *
 */
public interface ICommand extends Serializable {

	public String execute(HttpServletRequest request);
}
