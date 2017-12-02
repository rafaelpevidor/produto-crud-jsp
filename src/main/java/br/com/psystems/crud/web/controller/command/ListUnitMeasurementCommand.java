/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import java.util.List;

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
 * @author Rafael.Saldanha
 *
 */
public class ListUnitMeasurementCommand extends AbstractCrudCommand<UnitMeasurement> implements Controllable {

	public ListUnitMeasurementCommand(UnitMeasurementService service) {
		super(service);
	}

	private static final long serialVersionUID = 7904027167987556427L;
	private static Logger logger = Logger.getLogger(ListUnitMeasurementCommand.class);

	private UnitMeasurementService service;


	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Command#executar(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<UnitMeasurement> units = service.getAll();
			setEntityList(request, units);
			
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return Constants.PAGE_UNIT_LIST;
	}


	@Override
	protected void setService(BaseService<UnitMeasurement> service) {
		this.service = (UnitMeasurementService) service;
	}

}
