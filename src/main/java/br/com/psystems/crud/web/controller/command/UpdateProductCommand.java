/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.MapperException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.mapper.impl.ProductMapper;
import br.com.psystems.crud.model.BaseEntity;
import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.service.BaseService;
import br.com.psystems.crud.service.ProductService;
import br.com.psystems.crud.web.controller.Controllable;
import br.com.psystems.crud.web.controller.Mappable;

/**
 * @author rafael.saldanha
 *
 */
public class UpdateProductCommand extends AbstractCrudCommand<Product> implements Controllable, Mappable {

	public UpdateProductCommand(ProductService service) {
		super(service);
	}

	private static final long serialVersionUID = 8169250379905812667L;
	private static Logger logger = Logger.getLogger(UpdateProductCommand.class);
	
	private ProductService service;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			Product produto = (Product) map(request);
			service.update(produto);
			addSuccessMessage(request, Constants.MESSAGE_UPDATE_SUCCESS);
			
			return Constants.PAGE_PRODUCT_LIST;
		} catch (DAOException |MapperException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
			return Constants.PAGE_PRODUCT_FORM;
		}
	}

	@Override
	protected void setService(BaseService<Product> service) {
		this.service = (ProductService) service;
	}

	@Override
	public BaseEntity map(HttpServletRequest request) throws MapperException {
		return new ProductMapper().map(request);
	}
}
