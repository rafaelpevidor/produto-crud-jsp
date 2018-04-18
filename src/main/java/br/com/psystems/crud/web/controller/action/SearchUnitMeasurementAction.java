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
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.service.CrudService;
import br.com.psystems.crud.service.UnitMeasurementService;
import br.com.psystems.crud.web.controller.Controllable;

/**
 * @author rafael.saldanha
 *
 */
public class SearchUnitMeasurementAction extends AbstractCrudAction<UnitMeasurement> implements Controllable {

	public SearchUnitMeasurementAction(UnitMeasurementService service) {
		super(service);
	}

	private static final long serialVersionUID = 3699559009520920474L;
	private static Logger logger = Logger.getLogger(SearchUnitMeasurementAction.class);
	
	private UnitMeasurementService service;

	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Acao#executa(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<UnitMeasurement> units = service.findByName(getSearchKeyWord(request));
			setEntityList(request, units);
			
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return Constants.PAGE_UNIT_LIST;
	}

	@Override
	protected void setService(CrudService<UnitMeasurement> service) {
		this.service = (UnitMeasurementService) service;
	}
}
