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
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.model.dao.impl.UnitMeasurementDAO;

/**
 * @author rafael.saldanha
 *
 */
public class FindUnitMeasurementCommand extends AbstractCrudCommand {

	public FindUnitMeasurementCommand(UnitMeasurementDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = 7606485709102366372L;
	private static Logger logger = Logger.getLogger(FindUnitMeasurementCommand.class);

	private String page = ConstantsUtils.PAGE_UNIT_FORM;
	private UnitMeasurementDAO dao;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			UnitMeasurement fornecedor = dao.findById(getID(request));
			setEntity(request, fornecedor);
			
		} catch (DAOException | SystemException | ServletException e) {
			logger.error(e.getMessage());
			page = ConstantsUtils.PAGE_VENDOR_LIST;
			setException(request, e);
		}
		return page;
	}

}
