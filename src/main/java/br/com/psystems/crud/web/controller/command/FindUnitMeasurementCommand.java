/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.service.BaseService;
import br.com.psystems.crud.service.UnitMeasurementService;
import br.com.psystems.crud.web.controller.Controllable;

/**
 * @author rafael.saldanha
 *
 */
public class FindUnitMeasurementCommand extends AbstractCrudCommand<UnitMeasurement> implements Controllable {

	public FindUnitMeasurementCommand(UnitMeasurementService service) {
		super(service);
	}

	private static final long serialVersionUID = 7606485709102366372L;
	private static Logger logger = Logger.getLogger(FindUnitMeasurementCommand.class);

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
	protected void setService(BaseService<UnitMeasurement> service) {
		this.service = (UnitMeasurementService) service;
	}

}
