/**
 * 
 */
package br.com.psystems.crud.infra;

import br.com.psystems.crud.dao.FornecedorDAO;
import br.com.psystems.crud.dao.ProdutoDAO;
import br.com.psystems.crud.exception.DAOException;

/**
 * @author rafaelpevidor
 *
 */
public class DAOFactory {

	public static ProdutoDAO getProdutoDAO() throws DAOException {
		return new ProdutoDAO(ConnectionFactory.getConnection());
	}
	
	public static FornecedorDAO getFornecedorDAO() throws DAOException {
		return new FornecedorDAO(ConnectionFactory.getConnection());
	}
}
