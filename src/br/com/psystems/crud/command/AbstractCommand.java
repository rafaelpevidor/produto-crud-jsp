/**
 * 
 */
package br.com.psystems.crud.command;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * @author rafael.saldanha
 *
 */
public abstract class AbstractCommand {
	
	private static final String MENSAGEM_ERRO_ID = "Houve um erro inesperado ao recuperar o atributo desejado.";
	private static final String MENSAGEM_ERRO_INESPERADO = "Houve um erro inesperado ao executar o comando/ação.";
	
	private static Logger logger = Logger.getLogger(AbstractCommand.class);
	
	protected abstract void init();
	protected static final String MENSAGEM = "msg";
	protected static final String MENSAGEM_ERRO = "msgerror";
	protected static final String MENSAGEM_REGISTROS = "msgregistros";
	
	protected Integer getId(HttpServletRequest request) throws ServletException {
		
		try {
			
			String id = (String) request.getParameter("id");
			Integer pk = Integer.parseInt(id);
			
			return pk;
			
		} catch (Exception e) {
			
			setupAttributeException(request, e);
			throw new ServletException(MENSAGEM_ERRO_ID, e);
		}
	}
	
	protected String getKeyword(HttpServletRequest request) throws ServletException {
		
		try {
			
			return (String) request.getParameter("keyword");
			
		} catch (Exception e) {
			
			setupAttributeException(request, e);
			throw new ServletException(MENSAGEM_ERRO_ID, e);
		}
	}
	
	protected void addMessage(final HttpServletRequest request, final String mensagem) {
		request.setAttribute(MENSAGEM, mensagem);
		logger.info("\nMensagem para o usuário: ".concat(mensagem).concat("\n"));
	}
	
	protected void setupEmptyList (final HttpServletRequest request, final List<?> lista ) {
		
		if (
				(null == lista)||
				(lista.isEmpty())
		) {
			request.setAttribute(MENSAGEM_REGISTROS, "Nenhum registro encontrado.");
		}
	}
	
	protected void setupUnexpectedException (final HttpServletRequest request, final Exception exception) {
		request.setAttribute(MENSAGEM_ERRO, MENSAGEM_ERRO_INESPERADO);
		logger.error(MENSAGEM_ERRO_INESPERADO, exception);
	}
	
	protected void setupException (final HttpServletRequest request, final Exception exception) {
		request.setAttribute(MENSAGEM_ERRO, exception.getMessage());
		logger.error(exception.getMessage(), exception);
	}
	
	private void setupAttributeException (final HttpServletRequest request, final Exception exception) {
		request.setAttribute(MENSAGEM_ERRO, MENSAGEM_ERRO_ID);
		logger.error(MENSAGEM_ERRO_ID, exception);
	}
}