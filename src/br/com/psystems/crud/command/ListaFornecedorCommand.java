/**
 * 
 */
package br.com.psystems.crud.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.base.AbstractCommand;
import br.com.psystems.crud.base.BaseDAO;
import br.com.psystems.crud.dao.FornecedorDAO;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.model.Fornecedor;

/**
 * @author Rafael.Saldanha
 *
 */
public class ListaFornecedorCommand extends AbstractCommand {

	public ListaFornecedorCommand(FornecedorDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = 7904027167987556427L;
	private static Logger logger = Logger.getLogger(ListaFornecedorCommand.class);

	private String pagina = PAGE_FORNECEDOR_LISTA;
	private BaseDAO<Fornecedor> dao;


	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Command#executar(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Fornecedor> fornecedores = dao.getAll();
			setFornecedores(request, fornecedores);
			
		} catch (DAOException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return pagina;
	}

}
