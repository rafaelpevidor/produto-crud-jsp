/**
 * 
 */
package br.com.psystems.crud.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.controller.bind.ProductBind;
import br.com.psystems.crud.exception.BindException;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.dao.ProductDAO;

/**
 * @author rafael.saldanha
 *
 */
public class AddUserCommand extends AbstractCommand {

	public AddUserCommand(ProductDAO produtoDAO, ProductBind bind) {
		this.dao = produtoDAO;
		this.bind = bind;
	}

	private static final long serialVersionUID = -9136913987730291078L;
	private static Logger logger = Logger.getLogger(AddUserCommand.class);
	
	private String pagina = PAGE_PRODUTO_LISTA;
	private ProductDAO dao;
	private ProductBind bind;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Product produto = bind.buildEntity(request);
			dao.save(produto);
			addInfoMessage(request, ADD_SUCCESS_MESSAGE);
			
		} catch (BindException | DAOException | SystemException e) {
			logger.error(e.getMessage(), e);
			pagina = PAGE_PRODUTO_FORMULARIO;
			setException(request, e);
		}
		return pagina;
	}

}
