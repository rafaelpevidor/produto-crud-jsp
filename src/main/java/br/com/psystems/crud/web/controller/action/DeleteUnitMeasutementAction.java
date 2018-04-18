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
import br.com.psystems.crud.service.impl.UnitMeasurementServiceImpl;
import br.com.psystems.crud.web.controller.Controllable;

/**
 * @author rafael.saldanha
 *
 */
public class DeleteUnitMeasutementAction extends AbstractCrudAction<UnitMeasurement> implements Controllable {

	public DeleteUnitMeasutementAction(UnitMeasurementService service) {
		super(service);
	}

	private static final long serialVersionUID = -813686630847825683L;
	private static Logger logger = Logger.getLogger(DeleteUnitMeasutementAction.class);

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
	protected void setService(CrudService<UnitMeasurement> service) {
		this.service = (UnitMeasurementServiceImpl) service;
	}

}
