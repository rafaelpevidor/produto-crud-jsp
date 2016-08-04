/**
 * 
 */
package br.com.psystems.crud.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.base.AbstractCommand;
import br.com.psystems.crud.base.BaseDAO;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.model.Produto;

/**
 * @author rafael.saldanha
 *
 */
public class ApagarProdutoCommand extends AbstractCommand {

	public ApagarProdutoCommand(BaseDAO<Produto> dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = -813686630847825683L;
	private static Logger logger = Logger.getLogger(ApagarProdutoCommand.class);

	private String pagina = PAGE_PRODUTO_LISTA;
	private BaseDAO<Produto> dao;
	
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
