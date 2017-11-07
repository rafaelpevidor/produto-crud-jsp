/**
 * 
 */
package br.com.psystems.crud.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.VendorDAO;

/**
 * @author rafael.saldanha
 *
 */
public class FindUserCommand extends AbstractCommand {

	public FindUserCommand(VendorDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = 7606485709102366372L;
	private static Logger logger = Logger.getLogger(FindUserCommand.class);

	private String pagina = PAGE_FORNECEDOR_FORMULARIO;
	private VendorDAO dao;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			Vendor fornecedor = dao.findById(getID(request));
			setEntity(request, fornecedor);
			
		} catch (DAOException | SystemException | ServletException e) {
			logger.error(e.getMessage());
			pagina = PAGE_FORNECEDOR_LISTA;
			setException(request, e);
		}
		return pagina;
	}

}
