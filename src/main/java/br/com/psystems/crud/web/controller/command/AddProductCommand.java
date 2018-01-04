/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.MapperException;
import br.com.psystems.crud.exception.SystemException;
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
public class AddProductCommand extends AbstractCrudCommand<Product> implements Controllable, Mappable {

	public AddProductCommand(ProductService service) {
		super(service);
	}

	private static final long serialVersionUID = -9136913987730291078L;
	private static Logger logger = Logger.getLogger(AddProductCommand.class);
	
	private ProductService service;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			Product produto = (Product) map(request);
			service.save(produto);
			addInfoMessage(request, Constants.MESSAGE_ADD_SUCCESS);
			
			return Constants.PAGE_PRODUCT_LIST;
		} catch (MapperException | DAOException | SystemException e) {
			logger.error(e.getMessage(), e);
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
