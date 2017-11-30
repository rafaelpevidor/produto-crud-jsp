/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.MapperException;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.mapper.VendorMapper;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.impl.VendorDAO;

/**
 * @author rafael.saldanha
 *
 */
public class AddVendorCommand extends AbstractCrudCommand {

	public AddVendorCommand(VendorDAO fornecedorDAO, VendorMapper fornecedorBind) {
		this.dao = fornecedorDAO;
		this.bind = fornecedorBind;
	}

	private static final long serialVersionUID = -9136913987730291078L;
	private static Logger logger = Logger.getLogger(AddVendorCommand.class);
	
	private String pagina = ConstantsUtils.PAGE_VENDOR_LIST;
	private VendorDAO dao;
	private VendorMapper bind;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			Vendor fornecedor = bind.map(request);
			dao.save(fornecedor);
			addSuccessMessage(request, ConstantsUtils.MESSAGE_ADD_SUCCESS);
			
		} catch (DAOException |MapperException| SystemException e) {
			logger.error(e.getMessage());
			pagina = ConstantsUtils.PAGE_VENDOR_FORM;
			setException(request, e);
		}
		return pagina;
	}
}
