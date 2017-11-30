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
public class AddUserCommand extends AbstractCrudCommand {

	public AddUserCommand(ProductDAO produtoDAO, ProductMapper bind) {
		this.dao = produtoDAO;
		this.bind = bind;
	}

	private static final long serialVersionUID = -9136913987730291078L;
	private static Logger logger = Logger.getLogger(AddUserCommand.class);
	
	private String pagina = ConstantsUtils.PAGE_USER_LIST;
	private ProductDAO dao;
	private ProductMapper bind;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Product produto = bind.map(request);
			dao.save(produto);
			addInfoMessage(request, ConstantsUtils.MESSAGE_ADD_SUCCESS);
			
		} catch (MapperException | DAOException | SystemException e) {
			logger.error(e.getMessage(), e);
			pagina = ConstantsUtils.PAGE_USER_FORM;
			setException(request, e);
		}
		return pagina;
	}

}
