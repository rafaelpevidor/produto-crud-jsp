/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.infra.util.ConstantsUtils;
import br.com.psystems.crud.model.BaseEntity;

/**
 * @author rafael.saldanha
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractCrudCommand extends AbstractCommand {

//	private static Logger logger = Logger.getLogger(AbstractCrudCommand.class);
	
	protected Long getID(HttpServletRequest request) throws ServletException {
		try {
			String id = (String) request.getParameter("id");
			Long pk = Long.parseLong(id);
			return pk;
		} catch (Exception e) {
			throw new ServletException(ConstantsUtils.ERROR_MESSAGE_UNEXPECTED_ERROR_ATRIBUTE, e);
		}
	}
	
	protected String getKeyWord(HttpServletRequest request) throws ServletException {
		try {
			return (String) request.getParameter("keyword");
		} catch (Exception e) {
			throw new ServletException(ConstantsUtils.ERROR_MESSAGE_UNEXPECTED_ERROR_ATRIBUTE, e);
		}
	}
	
	protected void setEntityList (final HttpServletRequest request, final List<?> entityList ) {
		if (
				(null == entityList)||
				(entityList.isEmpty())
		) {
			addInfoMessage(request, "Nenhum registro encontrado.");
		} else {
			request.setAttribute(ConstantsUtils.ENTITY_LIST_KEY, entityList);
		}
	}
	
	protected void setEntity(final HttpServletRequest request, final BaseEntity entity) {
		request.setAttribute(ConstantsUtils.ENTITY_KEY, entity);
	}
	
}