/**
 * 
 */
package br.com.psystems.crud.test.builder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author developer
 *
 */
public abstract class AbstractHttpServletRequest implements HttpServletRequest {

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getParameter(java.lang.String)
	 */
	@Override
	public String getParameter(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addParameter(String name, Object value) {
		// TODO adicionar em um mapa
	}

}
