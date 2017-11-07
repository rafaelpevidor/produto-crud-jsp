/**
 * 
 */
package br.com.psystems.crud.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.controller.bind.VendorBind;
import br.com.psystems.crud.exception.BindException;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.VendorDAO;

/**
 * @author rafael.saldanha
 *
 */
public class UpdateUserCommand extends AbstractCommand {

	public UpdateUserCommand(VendorDAO dao, VendorBind bind) {
		this.dao = dao;
		this.bind = bind;
	}

	private static final long serialVersionUID = 8169250379905812667L;
	private static Logger logger = Logger.getLogger(UpdateUserCommand.class);
	
	private String pagina = PAGE_FORNECEDOR_LISTA;
	private VendorDAO dao;
	private VendorBind bind;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Vendor fornecedor = bind.buildEntity(request);
			dao.update(fornecedor);
			addSuccessMessage(request, UPDATE_SUCCESS_MESSAGE);
			
		} catch (DAOException | BindException | SystemException e) {
			logger.error(e.getMessage());
			pagina = PAGE_FORNECEDOR_FORMULARIO;
			setException(request, e);
		}
		return pagina;
	}

}
