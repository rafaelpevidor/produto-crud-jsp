package br.com.psystems.crud.controller.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.command.ProductListCommand;
import br.com.psystems.crud.command.VendorListCommand;
import br.com.psystems.crud.exception.PersistenceException;
import br.com.psystems.crud.model.dao.VendorDAO;
import br.com.psystems.crud.model.domain.Vendor;

/**
 * Servlet Filter implementation class ApplicacaoFilter
 */
@WebFilter(
		filterName="mainfilter", 
		urlPatterns={"/application/vendor/listvendor.jsp", "/application/product/listproduct.jsp", "/application/product/addproduct.jsp"})
public class ApplicationFilter implements Filter {
	
	private static Logger logger = Logger.getLogger(ApplicationFilter.class);

    /**
     * Default constructor. 
     */
    public ApplicationFilter() {}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		if (httpRequest.getRequestURI().contains("listvendor")) {
			
			new VendorListCommand().execute(httpRequest);
			
		} else if (httpRequest.getRequestURI().contains("listproduct")) {
			
			new ProductListCommand().execute(httpRequest);
			
		} else if (httpRequest.getRequestURI().contains("addproduct")) {
			
			try {
				List<Vendor> vendors = new VendorDAO().getAll();
				httpRequest.setAttribute("vendors", vendors);
				
			} catch (PersistenceException e) {
				request.setAttribute("msgerror", e.getMessage());
				logger.error(e.getMessage(), e);
			}
		}
		
		//capturando atributo cmd do objeto request e atribuindo à variável command
		String command = (String) request.getParameter("cmd");
		
		if (
				(null != command) &&
				(command.equals("0"))
		) {
			request.setAttribute("msgerror", "Houve um erro ao encontrar o comando.");
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
//	protected void printParameters(final HttpServletRequest request) {
//		Enumeration<String> params = request.getParameterNames();
//		while (params.hasMoreElements()) {
//			String paramName = params.nextElement();
//			String param = request.getParameter(paramName);
//			System.out.println(paramName+":"+param);
//		}
//	}

}
