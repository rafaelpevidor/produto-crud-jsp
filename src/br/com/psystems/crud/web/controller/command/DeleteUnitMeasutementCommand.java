/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.model.dao.impl.UnitMeasurementDAO;

/**
 * @author rafael.saldanha
 *
 */
public class DeleteUnitMeasutementCommand extends AbstractCrudCommand {

	public DeleteUnitMeasutementCommand(UnitMeasurementDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = -813686630847825683L;
	private static Logger logger = Logger.getLogger(DeleteUnitMeasutementCommand.class);

	private String page = ConstantsUtils.PAGE_UNIT_LIST;
	private UnitMeasurementDAO dao;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			dao.delete(getID(request));
			addSuccessMessage(request, ConstantsUtils.MESSAGE_DELETE_SUCCESS);
			
		} catch (DAOException | ServletException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return page;
	}

}
