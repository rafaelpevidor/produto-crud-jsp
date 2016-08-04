/**
 * 
 */
package br.com.psystems.crud.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.base.AbstractCommand;
import br.com.psystems.crud.base.BaseDAO;
import br.com.psystems.crud.dao.FornecedorDAO;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.model.Fornecedor;

/**
 * @author rafael.saldanha
 *
 */
public class ApagarFornecedorCommand extends AbstractCommand {

	public ApagarFornecedorCommand(FornecedorDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = -813686630847825683L;
	private static Logger logger = Logger.getLogger(ApagarFornecedorCommand.class);

	private String pagina = PAGE_FORNECEDOR_LISTA;
	private BaseDAO<Fornecedor> dao;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			dao.delete(getID(request));
			addSuccessMessage(request, DELETE_SUCCESS_MESSAGE);
			
		} catch (DAOException | ServletException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return pagina;
	}

}
