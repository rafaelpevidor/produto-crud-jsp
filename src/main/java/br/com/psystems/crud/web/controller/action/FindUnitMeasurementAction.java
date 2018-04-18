/**
 * 
 */
package br.com.psystems.crud.web.controller.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.service.CrudService;
import br.com.psystems.crud.service.UnitMeasurementService;
import br.com.psystems.crud.web.controller.Controllable;

/**
 * @author rafael.saldanha
 *
 */
public class FindUnitMeasurementAction extends AbstractCrudAction<UnitMeasurement> implements Controllable {

	public FindUnitMeasurementAction(UnitMeasurementService service) {
		super(service);
	}

	private static final long serialVersionUID = 7606485709102366372L;
	private static Logger logger = Logger.getLogger(FindUnitMeasurementAction.class);

	private UnitMeasurementService service;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			UnitMeasurement fornecedor = service.findById(getID(request));
			setEntity(request, fornecedor);
			
			return Constants.PAGE_UNIT_FORM;
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
			return Constants.PAGE_VENDOR_LIST;
		}
	}

	@Override
	protected void setService(CrudService<UnitMeasurement> service) {
		this.service = (UnitMeasurementService) service;
	}

}
