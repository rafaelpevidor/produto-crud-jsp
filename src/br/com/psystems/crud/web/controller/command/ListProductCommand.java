/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.dao.impl.ProductDAO;

/**
 * @author Rafael.Saldanha
 *
 */
public class ListProductCommand extends AbstractCrudCommand {

	public ListProductCommand(ProductDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = -7721458512554274161L;
	private static Logger logger = Logger.getLogger(ListProductCommand.class);
	
	private String page = ConstantsUtils.PAGE_PRODUCT_LIST;
	private ProductDAO dao;
	
	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Command#executar(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Product> products = dao.getAll();
			setEntityList(request, products);
			
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return page;
	}

}
