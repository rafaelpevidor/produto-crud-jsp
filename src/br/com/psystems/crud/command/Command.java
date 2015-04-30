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
public interface Command extends Serializable {

	public String executar(HttpServletRequest request);
}
