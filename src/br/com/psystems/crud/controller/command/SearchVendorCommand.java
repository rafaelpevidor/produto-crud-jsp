/**
 * 
 */
package br.com.psystems.crud.controller.command;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.exception.SystemException;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.VendorDAO;

/**
 * @author rafael.saldanha
 *
 */
public class SearchVendorCommand extends AbstractCommand {

	public SearchVendorCommand(VendorDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = 3699559009520920474L;
	private static Logger logger = Logger.getLogger(SearchVendorCommand.class);
	
	private String pagina = PAGE_FORNECEDOR_LISTA;
	private VendorDAO dao;

	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Acao#executa(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Vendor> fornecedores = dao.findByName(getKeyWord(request));
			setFornecedores(request, fornecedores);
			
		} catch (DAOException | SystemException | ServletException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return pagina;
	}
}
