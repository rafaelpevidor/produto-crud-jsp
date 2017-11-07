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
public class AddVendorCommand extends AbstractCommand {

	public AddVendorCommand(VendorDAO fornecedorDAO, VendorBind fornecedorBind) {
		this.dao = fornecedorDAO;
		this.bind = fornecedorBind;
	}

	private static final long serialVersionUID = -9136913987730291078L;
	private static Logger logger = Logger.getLogger(AddVendorCommand.class);
	
	private String pagina = PAGE_FORNECEDOR_LISTA;
	private VendorDAO dao;
	private VendorBind bind;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			Vendor fornecedor = bind.buildEntity(request);
			dao.save(fornecedor);
			addSuccessMessage(request, ADD_SUCCESS_MESSAGE);
			
		} catch (DAOException |BindException| SystemException e) {
			logger.error(e.getMessage());
			pagina = PAGE_FORNECEDOR_FORMULARIO;
			setException(request, e);
		}
		return pagina;
	}
}
