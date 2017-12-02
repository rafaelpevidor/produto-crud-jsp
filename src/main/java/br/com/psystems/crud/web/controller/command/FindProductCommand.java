/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.service.BaseService;
import br.com.psystems.crud.service.ProductService;
import br.com.psystems.crud.web.controller.Controllable;

/**
 * @author rafael.saldanha
 *
 */
public class FindProductCommand extends AbstractCrudCommand<Product> implements Controllable {

	public FindProductCommand(ProductService service) {
		super(service);
	}

	private static final long serialVersionUID = 7606485709102366372L;
	private static Logger logger = Logger.getLogger(FindProductCommand.class);
	
	private ProductService service;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			Product entity = service.findById(getID(request));
			setEntity(request, entity);
			
			return Constants.PAGE_PRODUCT_FORM;
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
			return Constants.PAGE_PRODUCT_LIST;
		}
	}

	@Override
	protected void setService(BaseService<Product> service) {
		this.service = (ProductService) service;
	}
	
}