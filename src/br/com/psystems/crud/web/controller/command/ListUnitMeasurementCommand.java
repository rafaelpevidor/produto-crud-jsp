/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.model.dao.impl.UnitMeasurementDAO;

/**
 * @author Rafael.Saldanha
 *
 */
public class ListUnitMeasurementCommand extends AbstractCrudCommand {

	public ListUnitMeasurementCommand(UnitMeasurementDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = 7904027167987556427L;
	private static Logger logger = Logger.getLogger(ListUnitMeasurementCommand.class);

	private String page = ConstantsUtils.PAGE_UNIT_LIST;
	private UnitMeasurementDAO dao;


	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Command#executar(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<UnitMeasurement> units = dao.getAll();
			setEntityList(request, units);
			
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return page;
	}

}
