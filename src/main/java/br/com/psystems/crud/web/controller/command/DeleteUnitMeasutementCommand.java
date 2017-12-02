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
import br.com.psystems.crud.service.impl.UnitMeasurementServiceImpl;
import br.com.psystems.crud.web.controller.Controllable;

/**
 * @author rafael.saldanha
 *
 */
public class DeleteUnitMeasutementCommand extends AbstractCrudCommand<UnitMeasurement> implements Controllable {

	public DeleteUnitMeasutementCommand(UnitMeasurementService service) {
		super(service);
	}

	private static final long serialVersionUID = -813686630847825683L;
	private static Logger logger = Logger.getLogger(DeleteUnitMeasutementCommand.class);

	private UnitMeasurementServiceImpl service;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			service.delete(getID(request));
			addSuccessMessage(request, Constants.MESSAGE_DELETE_SUCCESS);
			
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return Constants.PAGE_UNIT_LIST;
	}

	@Override
	protected void setService(BaseService<UnitMeasurement> service) {
		this.service = (UnitMeasurementServiceImpl) service;
	}

}
