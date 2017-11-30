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
import br.com.psystems.crud.mapper.ProductMapper;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.dao.impl.ProductDAO;

/**
 * @author rafael.saldanha
 *
 */
public class UpdateProductCommand extends AbstractCrudCommand {

	public UpdateProductCommand(ProductDAO dao, ProductMapper bind) {
		this.dao = dao;
		this.bind = bind;
	}

	private static final long serialVersionUID = 8169250379905812667L;
	private static Logger logger = Logger.getLogger(UpdateProductCommand.class);
	
	private String pagina = ConstantsUtils.PAGE_PRODUCT_LIST;
	private ProductDAO dao;
	private ProductMapper bind;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			Product produto = bind.map(request);
			dao.update(produto);
			addSuccessMessage(request, ConstantsUtils.MESSAGE_UPDATE_SUCCESS);
			
		} catch (DAOException |MapperException | SystemException e) {
			logger.error(e.getMessage());
			pagina = ConstantsUtils.PAGE_PRODUCT_FORM;
			setException(request, e);
		}
		return pagina;
	}

}
