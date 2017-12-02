package br.com.psystems.crud.web.controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.web.controller.Controllable;
import br.com.psystems.crud.web.controller.command.enums.CommandMapEnum;

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
    
    /*private Map<String, BaseCommand> getActions() throws DAOException {
    	Map<String, BaseCommand> actions = new HashMap<String, BaseCommand>();

		actions.put(ConstantsUtils.CREATE_VENDOR, new AddVendorCommand(new VendorDAO(), new VendorMapper()));
		actions.put(ConstantsUtils.RECOVER_VENDOR, new FindVendorCommand(new VendorDAO()));
		actions.put(ConstantsUtils.UPDATE_VENDOR, new UpdateVendorCommand(new VendorDAO(), new VendorMapper()));
		actions.put(ConstantsUtils.DELETE_VENDOR, new DeleteVendorCommand(new VendorDAO()));
		actions.put(ConstantsUtils.SEARCH_VENDOR, new SearchVendorCommand(new VendorDAO()));
		actions.put(ConstantsUtils.LIST_VENDOR, new ListVendorCommand(new VendorDAO()));

		actions.put(ConstantsUtils.CREATE_PRODUCT, new AddProductCommand(new ProductDAO(), new ProductMapper()));
		actions.put(ConstantsUtils.RECOVER_PRODUCT, new FindProductCommand(new ProductDAO()));
		actions.put(ConstantsUtils.UPDATE_PRODUCT, new UpdateProductCommand(new ProductDAO(), new ProductMapper()));
		actions.put(ConstantsUtils.DELETE_PRODUCT, new DeleteProductCommand(new ProductDAO()));
		actions.put(ConstantsUtils.SEARCH_PRODUCT, new SearchProductCommand(new ProductDAO()));
		actions.put(ConstantsUtils.LIST_PRODUCT, new ListProductCommand(new ProductDAO()));
	
    	return actions;
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String commandParam = (String) request.getAttribute(Constants.CMD_ATTRIBUTE);
		Controllable command;
		try {
			command = CommandMapEnum.getById(commandParam).getInstance();
			RequestDispatcher rd = request.getRequestDispatcher(command.execute(request));
			rd.forward(request, response);
		} catch (Exception e) {
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