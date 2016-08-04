/**
 * 
 */
package br.com.psystems.crud.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.base.AbstractCommand;
import br.com.psystems.crud.base.BaseBind;
import br.com.psystems.crud.base.BaseDAO;
import br.com.psystems.crud.bind.FornecedorBind;
import br.com.psystems.crud.dao.FornecedorDAO;
import br.com.psystems.crud.exception.ConverterException;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.model.Fornecedor;

/**
 * @author rafael.saldanha
 *
 */
public class AtualizarFornecedorCommand extends AbstractCommand {

	public AtualizarFornecedorCommand(FornecedorDAO dao, FornecedorBind bind) {
		this.dao = dao;
		this.bind = bind;
	}

	private static final long serialVersionUID = 8169250379905812667L;
	private static Logger logger = Logger.getLogger(AtualizarFornecedorCommand.class);
	
	private String pagina = PAGE_FORNECEDOR_LISTA;
	private BaseDAO<Fornecedor> dao;
	private BaseBind<Fornecedor> bind;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Fornecedor fornecedor = bind.buildEntity(request);
			dao.update(fornecedor);
			addSuccessMessage(request, UPDATE_SUCCESS_MESSAGE);
			
		} catch (DAOException |ConverterException e) {
			logger.error(e.getMessage());
			pagina = PAGE_FORNECEDOR_FORMULARIO;
			setException(request, e);
		}
		return pagina;
	}

}
