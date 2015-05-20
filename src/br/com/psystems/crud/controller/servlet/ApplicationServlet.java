package br.com.psystems.crud.controller.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.psystems.crud.command.ICommand;
import br.com.psystems.crud.exception.ConverterException;
import br.com.psystems.crud.utils.ConverterUtils;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(urlPatterns={"/application/vendor/main", "/application/product/main"})
public class ApplicationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String PACKAGE = "br.com.psystems.crud.command.";
	private static Logger logger = Logger.getLogger(ApplicationServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationServlet() {
        super();
    }
    
    private Map<Integer, String> getMapaComandos() {
    	Map<Integer, String> acoes = new HashMap<Integer, String>();
    	acoes.put(1, "AddVendorCommand");
    	acoes.put(2, "RecoverVendorCommand");
    	acoes.put(3, "UpdateVendorCommand");
    	acoes.put(4, "DeleteVendorCommand");
    	acoes.put(5, "SearchVendorCommand");
    	acoes.put(6, "VendorListCommand");
    	acoes.put(7, "AddProductCommand");
    	acoes.put(8, "RecoverProductCommand");
    	acoes.put(9, "UpdateProductCommand");
    	acoes.put(10, "DeleteProductCommand");
    	acoes.put(11, "SearchProductCommand");
    	acoes.put(12, "ProductListCommand");
    	return acoes;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//configura a codificação de caracteres para UTF-8
		request.setCharacterEncoding("UTF-8");
		
		//capturando atributo cmd do objeto request e atribuindo à variável acaoString
		String acaoString = (String) request.getParameter("cmd");
		
		
		Integer commandKey = 0;
		try {
			//realizando o parse (conversão) de String para Integer
			commandKey = ConverterUtils.convertStringToInteger(acaoString);
		} catch (NumberFormatException | ConverterException e) {
			
			//redireciona a requisição para a página obtida no método obtemPaginaParaComandoNaoEncontrado
			response.sendRedirect(getDefaultPageToCommandNotFound(request, response, e));
			return;
		}
		
		ICommand command;
		try {
			//obtendo o nome da classe/comando a ser posteriormente executado no mapa de comandos
			String commandClassName = PACKAGE.concat(getMapaComandos().get(commandKey));
			
			//obtendo uma instância da classe command a partir no nome.
			Class<?> commandClass = Class.forName(commandClassName);
			command = (ICommand) commandClass.newInstance();
			
		} catch (ClassNotFoundException e) {
			logger.error("Comando não encontrado.", e);
			throw new ServletException("Comando não encontrado.", e);
		} catch (InstantiationException e) {
			logger.error("Houve um erro inesperado ao inicializar o comando.", e);
			throw new ServletException("Houve um erro inesperado ao inicializar o comando.", e);
		} catch (IllegalAccessException e) {
			logger.error("Houve um erro inesperado ao acessar o comando.", e);
			throw new ServletException("Houve um erro inesperado ao acessar o comando.", e);
		}
		
		//encaminhando a requisição para a página obtida como resultado o método executar()
		request.getRequestDispatcher(command.execute(request)).forward(request, response);
	}

	private String getDefaultPageToCommandNotFound(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException {
		logger.error("Houve um erro ao encontrar o comando.", e);
		
		String paginaDestino = null;
		
		if (request.getRequestURI().contains("vendor/")) {
			paginaDestino = "listvendor.jsp?cmd=0";
		}
		
		if (request.getRequestURI().contains("product/")) {
			paginaDestino = "listproduct.jsp?cmd=0";
		}
		return paginaDestino;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}