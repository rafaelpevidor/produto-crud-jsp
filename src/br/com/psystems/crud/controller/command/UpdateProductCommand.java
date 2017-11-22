/**
 * 
 */
package br.com.psystems.crud.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.controller.bind.ProductBind;
import br.com.psystems.crud.infra.exception.BindException;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.dao.ProductDAO;

/**
 * @author rafael.saldanha
 *
 */
public class UpdateProductCommand extends AbstractCommand {

	public UpdateProductCommand(ProductDAO dao, ProductBind bind) {
		this.dao = dao;
		this.bind = bind;
	}

	private static final long serialVersionUID = 8169250379905812667L;
	private static Logger logger = Logger.getLogger(UpdateProductCommand.class);
	
	private String pagina = PAGE_PRODUTO_LISTA;
	private ProductDAO dao;
	private ProductBind bind;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			Product produto = bind.buildEntity(request);
			dao.update(produto);
			addSuccessMessage(request, UPDATE_SUCCESS_MESSAGE);
			
		} catch (DAOException |BindException | SystemException e) {
			logger.error(e.getMessage());
			pagina = PAGE_PRODUTO_FORMULARIO;
			setException(request, e);
		}
		return pagina;
	}

}
