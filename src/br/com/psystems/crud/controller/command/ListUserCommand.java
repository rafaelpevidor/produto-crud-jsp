/**
 * 
 */
package br.com.psystems.crud.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.model.Vendor;
import br.com.psystems.crud.model.dao.VendorDAO;

/**
 * @author Rafael.Saldanha
 *
 */
public class ListUserCommand extends AbstractCommand {

	public ListUserCommand(VendorDAO dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = 7904027167987556427L;
	private static Logger logger = Logger.getLogger(ListUserCommand.class);

	private String pagina = PAGE_FORNECEDOR_LISTA;
	private VendorDAO dao;


	/* (non-Javadoc)
	 * @see br.com.psystems.crud.command.Command#executar(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Vendor> fornecedores = dao.getAll();
			setFornecedores(request, fornecedores);
			
		} catch (DAOException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
		}
		return pagina;
	}

}
