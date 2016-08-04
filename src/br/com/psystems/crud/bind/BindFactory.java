/**
 * 
 */
package br.com.psystems.crud.bind;

/**
 * @author rafaelpevidor
 *
 */
public class BindFactory {

	public static FornecedorBind getFornecedorBind() {
		return new FornecedorBind();
	}
	
	public static ProdutoBind getProdutoBind() {
		return new ProdutoBind();
	}
}
