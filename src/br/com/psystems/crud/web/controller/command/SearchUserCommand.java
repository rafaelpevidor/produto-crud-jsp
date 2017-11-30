/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.impl.VendorDAO;

/**
 * @author rafael.saldanha
 *
 */
public class SearchUserCommand extends AbstractCrudCommand {

	public SearchUserCommand(VendorDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = 3699559009520920474L;
	private static Logger logger = Logger.getLogger(SearchUserCommand.class);
	
	private String page = ConstantsUtils.PAGE_VENDOR_LIST;
	private VendorDAO dao;

	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Acao#executa(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Vendor> fornecedores = dao.findByName(getKeyWord(request));
			setEntityList(request, fornecedores);
			
		} catch (DAOException | SystemException | ServletException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return page;
	}
}
