/**
 * 
 */
package br.com.psystems.crud.web.controller.servlet;

import javax.servlet.annotation.WebServlet;

import br.com.psystems.crud.web.controller.AbstractController;

/**
 * @author developer
 *
 */
@WebServlet(
		name = "product", 
		urlPatterns={"/app/product/create","/app/product/read","/app/product/update","/app/product/delete","/app/product/add","/app/product/edit","/app/product/list"})
public class ProductController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2304698254306746301L;

}
