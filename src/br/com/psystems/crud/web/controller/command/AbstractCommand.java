/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import br.com.psystems.crud.infra.util.ConstantsUtils;

/**
 * @author developer
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractCommand implements BaseCommand {
	
//	private static Logger logger = Logger.getLogger(AbstractCommand.class);

	protected void addSuccessMessage(final HttpServletRequest request, final String mensagem) {
		request.setAttribute(ConstantsUtils.MESSAGE_KEY_SUCCESS, mensagem);
	}
	
	protected void addInfoMessage(final HttpServletRequest request, final String mensagem) {
		request.setAttribute(ConstantsUtils.MESSAGE_KEY_INFO, mensagem);
	}
	
	protected void addWarnMessage(final HttpServletRequest request, final String mensagem) {
		request.setAttribute(ConstantsUtils.MESSAGE_KEY_WARN, mensagem);
	}
	
	protected void addErrorMessage(final HttpServletRequest request, final String mensagem) {
		request.setAttribute(ConstantsUtils.MESSAGE_KEY_ERROR, mensagem);
	}
	
	protected void setException (final HttpServletRequest request, final Exception exception) {
		addErrorMessage(request, exception.getMessage());
	}
	
//	protected void setAttributeException (final HttpServletRequest request, final Exception exception) {
//		request.setAttribute(ConstantsUtils.MESSAGE_KEY_ERROR, ConstantsUtils.ERROR_MESSAGE_UNEXPECTED_ERROR_ATRIBUTE);
//		logger.error(ConstantsUtils.ERROR_MESSAGE_UNEXPECTED_ERROR_ATRIBUTE, exception);
//	}
}
