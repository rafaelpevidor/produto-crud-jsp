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
import br.com.psystems.crud.dao.ProdutoDAO;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.model.Produto;

/**
 * @author rafael.saldanha
 *
 */
public class PesquisarProdutoCommand extends AbstractCommand {

	public PesquisarProdutoCommand(ProdutoDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = -4000181949308716513L;
	private static Logger logger = Logger.getLogger(PesquisarProdutoCommand.class);
	
	private String pagina = PAGE_PRODUTO_LISTA;
	private BaseDAO<Produto> dao;

	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Acao#executa(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Produto> produtos = dao.findByName(getKeyWord(request));
			setProdutos(request, produtos);
			
		} catch (DAOException | ServletException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return pagina;
	}

}
