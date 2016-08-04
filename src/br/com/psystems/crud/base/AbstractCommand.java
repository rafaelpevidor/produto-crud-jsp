/**
 * 
 */
package br.com.psystems.crud.base;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.model.Fornecedor;
import br.com.psystems.crud.model.Produto;

/**
 * @author rafael.saldanha
 *
 */
public abstract class AbstractCommand implements BaseCommand {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AbstractCommand.class);
	
	private static final String ENTITY_KEY = "entity";
	private static final String ENTITY_LIST_KEY = "entity";
	
	private static final String SUCCESS_MESSAGE_KEY = "msgsuccess";
	private static final String INFO_MESSAGE_KEY = "msginfo";
	private static final String WARN_MESSAGE_KEY = "msgwarn";
	private static final String ERROR_MESSAGE_KEY = "msgerror";
	
	private static final String UNEXPECTED_ERROR_ATRIBUTE_MESSAGE = "Desculpe, houve um erro inesperado ao recuperar o atributo desejado.";
//	private static final String UNEXPECTED_ERROR_ACTION_MESSAGE = "Desculpe, houve um erro inesperado ao executar a ação.";
	
	protected static final String ADD_SUCCESS_MESSAGE = "Salvo com sucesso!";
	protected static final String UPDATE_SUCCESS_MESSAGE = "Atualizado com sucesso!";
	protected static final String DELETE_SUCCESS_MESSAGE = "Apagado com sucesso!";
	
	protected static final String PAGE_FORNECEDOR_LISTA = "/fornecedor/list.jsp";
	protected static final String PAGE_FORNECEDOR_FORMULARIO = "/fornecedor/form.jsp";
	protected static final String PAGE_PRODUTO_LISTA = "/produto/lista.jsp";
	protected static final String PAGE_PRODUTO_FORMULARIO = "/produto/form.jsp";
	
	
	protected Long getID(HttpServletRequest request) throws ServletException {
		try {
			String id = (String) request.getParameter("id");
			Long pk = Long.parseLong(id);
			return pk;
		} catch (Exception e) {
			setAttributeException(request, e);
			throw new ServletException(UNEXPECTED_ERROR_ATRIBUTE_MESSAGE, e);
		}
	}
	
	protected String getKeyWord(HttpServletRequest request) throws ServletException {
		try {
			return (String) request.getParameter("keyword");
		} catch (Exception e) {
			setAttributeException(request, e);
			throw new ServletException(UNEXPECTED_ERROR_ATRIBUTE_MESSAGE, e);
		}
	}
	
	protected void addSuccessMessage(final HttpServletRequest request, final String mensagem) {
		request.setAttribute(SUCCESS_MESSAGE_KEY, mensagem);
	}
	
	protected void addInfoMessage(final HttpServletRequest request, final String mensagem) {
		request.setAttribute(INFO_MESSAGE_KEY, mensagem);
	}
	
	protected void addWarnMessage(final HttpServletRequest request, final String mensagem) {
		request.setAttribute(WARN_MESSAGE_KEY, mensagem);
	}
	
	protected void setFornecedores (final HttpServletRequest request, final List<Fornecedor> fornecedores ) {
		if (
				(null == fornecedores)||
				(fornecedores.isEmpty())
		) {
			addInfoMessage(request, "Nenhum registro encontrado.");
		} else {
			request.setAttribute(ENTITY_LIST_KEY, fornecedores);
		}
	}
	
	protected void setProdutos (final HttpServletRequest request, final List<Produto> produtos ) {
		if (
				(null == produtos)||
				(produtos.isEmpty())
		) {
			addInfoMessage(request, "Nenhum registro encontrado.");
		} else {
			request.setAttribute(ENTITY_LIST_KEY, produtos);
		}
	}
	
	protected void setEntity(final HttpServletRequest request, final BaseEntity entity) {
		request.setAttribute(ENTITY_KEY, entity);
	}
	
	protected void setException (final HttpServletRequest request, final Exception exception) {
		addErrorMessage(request, exception.getMessage());
	}
	
	private void setAttributeException (final HttpServletRequest request, final Exception exception) {
		request.setAttribute(ERROR_MESSAGE_KEY, UNEXPECTED_ERROR_ATRIBUTE_MESSAGE);
		logger.error(UNEXPECTED_ERROR_ATRIBUTE_MESSAGE, exception);
	}
	
	private void addErrorMessage(final HttpServletRequest request, final String mensagem) {
		request.setAttribute(ERROR_MESSAGE_KEY, mensagem);
	}
}