/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.dao.impl.ProductDAO;

/**
 * @author rafael.saldanha
 *
 */
public class FindProductCommand extends AbstractCrudCommand {

	public FindProductCommand(ProductDAO dao) {
		super();
		this.dao = dao;
	}

	private static final long serialVersionUID = 7606485709102366372L;
	private static Logger logger = Logger.getLogger(FindProductCommand.class);
	
	private String page = ConstantsUtils.PAGE_PRODUCT_FORM;
	private ProductDAO dao;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Product produto = dao.findById(getID(request));
			setEntity(request, produto);
			
		} catch (DAOException | ServletException | SystemException e) {
			logger.error(e.getMessage());
			page = ConstantsUtils.PAGE_PRODUCT_LIST;
			setException(request, e);
		}
		return page;
	}

}
