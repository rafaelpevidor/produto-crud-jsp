/**
 * 
 */
package br.com.psystems.crud.web.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.service.CrudService;
import br.com.psystems.crud.service.ProductService;
import br.com.psystems.crud.web.controller.Controllable;

/**
 * @author Rafael.Saldanha
 *
 */
public class ListProductAction extends AbstractCrudAction<Product> implements Controllable {

	public ListProductAction(ProductService service) {
		super(service);
	}

	private static final long serialVersionUID = -7721458512554274161L;
	private static Logger logger = Logger.getLogger(ListProductAction.class);
	
	private ProductService service;
	
	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Command#executar(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Product> products = service.getAll();
			setEntityList(request, products);
			
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return Constants.PAGE_PRODUCT_LIST;
	}

	@Override
	protected void setService(CrudService<Product> service) {
		this.service = (ProductService) service;
	}
	
}
