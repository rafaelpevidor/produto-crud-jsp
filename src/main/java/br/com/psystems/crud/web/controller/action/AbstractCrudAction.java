/**
 * 
 */
package br.com.psystems.crud.web.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.model.BaseEntity;
import br.com.psystems.crud.service.CrudService;

/**
 * @author rafael.saldanha
 *
 */
public abstract class AbstractCrudAction<T extends BaseEntity> extends AbstractAction {
	
	public AbstractCrudAction(CrudService<T> service) {
		setService(service);
	}
	
	protected abstract void setService(CrudService<T> service);

	private static Logger logger = Logger.getLogger(AbstractCrudAction.class);
	
	protected Long getID(HttpServletRequest request) throws SystemException {
		try {
			String id = (String) request.getParameter("id");
			Long pk = Long.parseLong(id);
			return pk;
		} catch (Exception e) {
			logger.error(Constants.ERROR_MESSAGE_UNEXPECTED_ERROR_ATRIBUTE, e);
			throw new SystemException(Constants.ERROR_MESSAGE_UNEXPECTED_ERROR_ATRIBUTE, e);
		}
	}

	protected String getSearchKeyWord(HttpServletRequest request) throws SystemException {
		try {
			return (String) request.getParameter("keyword");
		} catch (Exception e) {
			logger.error(Constants.ERROR_MESSAGE_UNEXPECTED_ERROR_ATRIBUTE, e);
			throw new SystemException(Constants.ERROR_MESSAGE_UNEXPECTED_ERROR_ATRIBUTE, e);
		}
	}

	protected void setEntityList (final HttpServletRequest request, final List<?> entityList ) {
		if (
				(null == entityList)||
				(entityList.isEmpty())
		) {
			addInfoMessage(request, "Nenhum registro encontrado.");
		} else {
			request.setAttribute(Constants.ENTITY_LIST_KEY, entityList);
		}
	}

	protected void setEntity(final HttpServletRequest request, final BaseEntity entity) {
		request.setAttribute(Constants.ENTITY_KEY, entity);
	}

}