/**
 * 
 */
package br.com.psystems.crud.command;

import java.util.List;

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
public class PesquisarFornecedorCommand extends AbstractCommand {

	public PesquisarFornecedorCommand(FornecedorDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = 3699559009520920474L;
	private static Logger logger = Logger.getLogger(PesquisarFornecedorCommand.class);
	
	private String pagina = PAGE_FORNECEDOR_LISTA;
	private BaseDAO<Fornecedor> dao;

	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Acao#executa(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Fornecedor> fornecedores = dao.findByName(getKeyWord(request));
			setFornecedores(request, fornecedores);
			
		} catch (DAOException | ServletException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return pagina;
	}
}
