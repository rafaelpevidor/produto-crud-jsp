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

import br.com.psystems.crud.controller.bind.ProductBind;
import br.com.psystems.crud.controller.bind.VendorBind;
import br.com.psystems.crud.controller.command.AddProductCommand;
import br.com.psystems.crud.controller.command.AddVendorCommand;
import br.com.psystems.crud.controller.command.BaseCommand;
import br.com.psystems.crud.controller.command.DeleteProductCommand;
import br.com.psystems.crud.controller.command.DeleteVendorCommand;
import br.com.psystems.crud.controller.command.FindProductCommand;
import br.com.psystems.crud.controller.command.FindVendorCommand;
import br.com.psystems.crud.controller.command.ListProductCommand;
import br.com.psystems.crud.controller.command.ListVendorCommand;
import br.com.psystems.crud.controller.command.SearchProductCommand;
import br.com.psystems.crud.controller.command.SearchVendorCommand;
import br.com.psystems.crud.controller.command.UpdateProductCommand;
import br.com.psystems.crud.controller.command.UpdateVendorCommand;
import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.infra.ConnectionFactory;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.infra.ConnectionFactory.EnviromentEnum;
import br.com.psystems.crud.model.dao.ProductDAO;
import br.com.psystems.crud.model.dao.VendorDAO;
import br.com.psystems.crud.util.ConstantsUtils;

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
    
    private Map<String, BaseCommand> getActions() throws DAOException {
    	Map<String, BaseCommand> actions = new HashMap<String, BaseCommand>();

		actions.put(ConstantsUtils.CREATE_FORNECEDOR, new AddVendorCommand(new VendorDAO(), new VendorBind()));
		actions.put(ConstantsUtils.RECOVER_FORNECEDOR, new FindVendorCommand(new VendorDAO()));
		actions.put(ConstantsUtils.UPDATE_FORNECEDOR, new UpdateVendorCommand(new VendorDAO(), new VendorBind()));
		actions.put(ConstantsUtils.DELETE_FORNECEDOR, new DeleteVendorCommand(new VendorDAO()));
		actions.put(ConstantsUtils.SEARCH_FORNECEDOR, new SearchVendorCommand(new VendorDAO()));
		actions.put(ConstantsUtils.LIST_FORNECEDOR, new ListVendorCommand(new VendorDAO()));
		actions.put(ConstantsUtils.CREATE_PRODUTO, new AddProductCommand(new ProductDAO(), new ProductBind()));
		actions.put(ConstantsUtils.RECOVER_PRODUTO, new FindProductCommand(new ProductDAO()));
		actions.put(ConstantsUtils.UPDATE_PRODUTO, new UpdateProductCommand(new ProductDAO(), new ProductBind()));
		actions.put(ConstantsUtils.DELETE_PRODUTO, new DeleteProductCommand(new ProductDAO()));
		actions.put(ConstantsUtils.SEARCH_PRODUTO, new SearchProductCommand(new ProductDAO()));
		actions.put(ConstantsUtils.LIST_PRODUTO, new ListProductCommand(new ProductDAO()));
		actions.put(ConstantsUtils.CREATE_PRODUTO, new AddProductCommand(new ProductDAO(), new ProductBind()));
		actions.put(ConstantsUtils.RECOVER_PRODUTO, new FindProductCommand(new ProductDAO()));
		actions.put(ConstantsUtils.UPDATE_PRODUTO, new UpdateProductCommand(new ProductDAO(), new ProductBind()));
		actions.put(ConstantsUtils.DELETE_PRODUTO, new DeleteProductCommand(new ProductDAO()));
		actions.put(ConstantsUtils.SEARCH_PRODUTO, new SearchProductCommand(new ProductDAO()));
		actions.put(ConstantsUtils.LIST_PRODUTO, new ListProductCommand(new ProductDAO()));
	
    	return actions;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionStr = (String) request.getAttribute(ConstantsUtils.CMD_ATTRIBUTE);
		BaseCommand action;
		try {
			action = getActions().get(actionStr);
			RequestDispatcher rd = request.getRequestDispatcher(action.execute(request));
			rd.forward(request, response);
		} catch (DAOException e) {
			// TODO redirecionar para uma página de erro
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}