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
import br.com.psystems.crud.mapper.impl.UnitMeasurementMapper;
import br.com.psystems.crud.model.BaseEntity;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.service.BaseService;
import br.com.psystems.crud.service.UnitMeasurementService;
import br.com.psystems.crud.web.controller.Controllable;
import br.com.psystems.crud.web.controller.Mappable;

/**
 * @author rafael.saldanha
 *
 */
public class AddUnitMeasurementCommand extends AbstractCrudCommand<UnitMeasurement> implements Controllable, Mappable {

	public AddUnitMeasurementCommand(UnitMeasurementService service) {
		super(service);
	}

	private static final long serialVersionUID = -9136913987730291078L;
	private static Logger logger = Logger.getLogger(AddUnitMeasurementCommand.class);
	
	private UnitMeasurementService service;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			UnitMeasurement unit = (UnitMeasurement) map(request);
			service.save(unit);
			addSuccessMessage(request, Constants.MESSAGE_ADD_SUCCESS);
			
			return Constants.PAGE_UNIT_LIST;
		} catch (DAOException |MapperException| SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
			return Constants.PAGE_UNIT_FORM;
		}
	}

	@Override
	protected void setService(BaseService<UnitMeasurement> service) {
		this.service = (UnitMeasurementService) service;
	}

	@Override
	public BaseEntity map(HttpServletRequest request) throws MapperException {
		return new UnitMeasurementMapper().map(request);
	}

}
