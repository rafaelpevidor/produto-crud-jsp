package br.com.psystems.crud.controller.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.psystems.crud.command.Command;
import br.com.psystems.crud.command.ApagaFornecedorCommand;
import br.com.psystems.crud.command.ApagaProdutoCommand;
import br.com.psystems.crud.command.AtualizaFornecedorCommand;
import br.com.psystems.crud.command.AtualizaProdutoCommand;
import br.com.psystems.crud.command.BuscaFornecedorCommand;
import br.com.psystems.crud.command.BuscaProdutoCommand;
import br.com.psystems.crud.command.InsereFornecedorCommand;
import br.com.psystems.crud.command.InsereProdutoCommand;
import br.com.psystems.crud.command.PesquisaFornecedorCommand;
import br.com.psystems.crud.command.PesquisaProdutoCommand;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
    }
    
    private Map<String, Command> getMapaAcoes() {
    	Map<String, Command> acoes = new HashMap<String, Command>();
    	acoes.put("1", new InsereFornecedorCommand());
    	acoes.put("2", new BuscaFornecedorCommand());
    	acoes.put("3", new AtualizaFornecedorCommand());
    	acoes.put("4", new ApagaFornecedorCommand());
    	acoes.put("5", new PesquisaFornecedorCommand());
    	acoes.put("6", new InsereProdutoCommand());
    	acoes.put("7", new BuscaProdutoCommand());
    	acoes.put("8", new AtualizaProdutoCommand());
    	acoes.put("9", new ApagaProdutoCommand());
    	acoes.put("10", new PesquisaProdutoCommand());
    	return acoes;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acaoString = (String) request.getAttribute("cmd");
		Command acao = getMapaAcoes().get(acaoString);
		
		RequestDispatcher rd = request.getRequestDispatcher(acao.executar(request));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}