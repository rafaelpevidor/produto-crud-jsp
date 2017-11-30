/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.MapperException;
import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.mapper.UnitMeasurementMapper;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.model.dao.impl.UnitMeasurementDAO;

/**
 * @author rafael.saldanha
 *
 */
public class AddUnitMeasurementCommand extends AbstractCrudCommand {

	public AddUnitMeasurementCommand(UnitMeasurementDAO dao, UnitMeasurementMapper bind) {
		this.dao = dao;
		this.bind = bind;
	}

	private static final long serialVersionUID = -9136913987730291078L;
	private static Logger logger = Logger.getLogger(AddUnitMeasurementCommand.class);
	
	private String page = ConstantsUtils.PAGE_UNIT_LIST;
	private UnitMeasurementDAO dao;
	private UnitMeasurementMapper bind;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			UnitMeasurement unit = bind.map(request);
			dao.save(unit);
			addSuccessMessage(request, ConstantsUtils.MESSAGE_ADD_SUCCESS);
			
		} catch (DAOException |MapperException| SystemException e) {
			logger.error(e.getMessage());
			page = ConstantsUtils.PAGE_UNIT_FORM;
			setException(request, e);
		}
		return page;
	}
}
