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
public class BuscarFornecedorCommand extends AbstractCommand {

	public BuscarFornecedorCommand(FornecedorDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = 7606485709102366372L;
	private static Logger logger = Logger.getLogger(BuscarFornecedorCommand.class);

	private String pagina = PAGE_FORNECEDOR_FORMULARIO;
	private BaseDAO<Fornecedor> dao;
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			Fornecedor fornecedor = dao.findById(getID(request));
			setEntity(request, fornecedor);
			
		} catch (DAOException | ServletException e) {
			logger.error(e.getMessage());
			pagina = PAGE_FORNECEDOR_LISTA;
			setException(request, e);
		}
		return pagina;
	}

}
