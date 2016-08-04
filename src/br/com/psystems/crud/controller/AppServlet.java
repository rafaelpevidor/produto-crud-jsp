package br.com.psystems.crud.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.psystems.crud.base.BaseCommand;
import br.com.psystems.crud.bind.BindFactory;
import br.com.psystems.crud.command.AdicionarFornecedorCommand;
import br.com.psystems.crud.command.AdicionarProdutoCommand;
import br.com.psystems.crud.command.ApagarFornecedorCommand;
import br.com.psystems.crud.command.ApagarProdutoCommand;
import br.com.psystems.crud.command.AtualizarFornecedorCommand;
import br.com.psystems.crud.command.AtualizarProdutoCommand;
import br.com.psystems.crud.command.BuscarFornecedorCommand;
import br.com.psystems.crud.command.BuscarProdutoCommand;
import br.com.psystems.crud.command.ListaFornecedorCommand;
import br.com.psystems.crud.command.ListaProdutoCommand;
import br.com.psystems.crud.command.PesquisarFornecedorCommand;
import br.com.psystems.crud.command.PesquisarProdutoCommand;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.infra.DAOFactory;
import br.com.psystems.crud.util.Constants;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/app")
public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppServlet() {
        super();
    }
    
    private Map<String, BaseCommand> getActions() {
    	Map<String, BaseCommand> actions = new HashMap<String, BaseCommand>();
		try {
			actions.put(Constants.CREATE_FORNECEDOR, new AdicionarFornecedorCommand(DAOFactory.getFornecedorDAO(), BindFactory.getFornecedorBind()));
			actions.put(Constants.RECOVER_FORNECEDOR, new BuscarFornecedorCommand(DAOFactory.getFornecedorDAO()));
			actions.put(Constants.UPDATE_FORNECEDOR, new AtualizarFornecedorCommand(DAOFactory.getFornecedorDAO(), BindFactory.getFornecedorBind()));
			actions.put(Constants.DELETE_FORNECEDOR, new ApagarFornecedorCommand(DAOFactory.getFornecedorDAO()));
			actions.put(Constants.SEARCH_FORNECEDOR, new PesquisarFornecedorCommand(DAOFactory.getFornecedorDAO()));
			actions.put(Constants.LIST_FORNECEDOR, new ListaFornecedorCommand(DAOFactory.getFornecedorDAO()));
			actions.put(Constants.CREATE_PRODUTO, new AdicionarProdutoCommand(DAOFactory.getProdutoDAO(), BindFactory.getProdutoBind()));
			actions.put(Constants.RECOVER_PRODUTO, new BuscarProdutoCommand(DAOFactory.getProdutoDAO()));
			actions.put(Constants.UPDATE_PRODUTO, new AtualizarProdutoCommand(DAOFactory.getProdutoDAO(), BindFactory.getProdutoBind()));
			actions.put(Constants.DELETE_PRODUTO, new ApagarProdutoCommand(DAOFactory.getProdutoDAO()));
			actions.put(Constants.SEARCH_PRODUTO, new PesquisarProdutoCommand(DAOFactory.getProdutoDAO()));
			actions.put(Constants.LIST_PRODUTO, new ListaProdutoCommand(DAOFactory.getProdutoDAO()));
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return actions;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionStr = (String) request.getAttribute(Constants.CMD_ATTRIBUTE);
		BaseCommand action = getActions().get(actionStr);
		
		RequestDispatcher rd = request.getRequestDispatcher(action.execute(request));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}