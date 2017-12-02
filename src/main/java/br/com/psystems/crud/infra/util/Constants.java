/**
 * 
 */
package br.com.psystems.crud.infra.util;

/**
 * @author rafaelpevidor
 *
 */
public class Constants {
	
	public static final String ENTITY_KEY = "entity";
	public static final String ENTITY_LIST_KEY = "entityList";
	
	public static final String MESSAGE_KEY_SUCCESS = "msgsuccess";
	public static final String MESSAGE_KEY_INFO = "msginfo";
	public static final String MESSAGE_KEY_WARN = "msgwarn";
	public static final String MESSAGE_KEY_ERROR = "msgerror";
	
	public static final String MESSAGE_ADD_SUCCESS = "Salvo com sucesso!";
	public static final String MESSAGE_UPDATE_SUCCESS = "Atualizado com sucesso!";
	public static final String MESSAGE_DELETE_SUCCESS = "Apagado com sucesso!";
	
	public static final String PAGE_VENDOR_LIST = "/vendor/list.jsp";
	public static final String PAGE_VENDOR_FORM = "/vendor/form.jsp";
	public static final String PAGE_USER_LIST = "/user/list.jsp";
	public static final String PAGE_USER_FORM = "/user/form.jsp";
	public static final String PAGE_UNIT_LIST = "/unit/list.jsp";
	public static final String PAGE_UNIT_FORM = "/unit/form.jsp";
	public static final String PAGE_PRODUCT_LIST = "/product/list.jsp";
	public static final String PAGE_PRODUCT_FORM = "/procut/form.jsp";

	public static final String CMD_ATTRIBUTE = "cmd";
	
//	public static final String CREATE_VENDOR = "1";
//	public static final String RECOVER_VENDOR = "2";
//	public static final String UPDATE_VENDOR = "3";
//	public static final String DELETE_VENDOR = "4";
//	public static final String SEARCH_VENDOR = "5";
//	public static final String LIST_VENDOR = "6";
//	
//	public static final String CREATE_PRODUCT = "7";
//	public static final String RECOVER_PRODUCT = "8";
//	public static final String UPDATE_PRODUCT = "9";
//	public static final String DELETE_PRODUCT = "10";
//	public static final String SEARCH_PRODUCT = "11";
//	public static final String LIST_PRODUCT = "12";
//	
//	public static final String CREATE_USER = "13";
//	public static final String RECOVER_USER = "14";
//	public static final String UPDATE_USER = "15";
//	public static final String DELETE_USER = "16";
//	public static final String SEARCH_USER = "17";
//	public static final String LIST_USER = "18";
//	
//	public static final String CREATE_UNIT = "13";
//	public static final String RECOVER_UNIT = "14";
//	public static final String UPDATE_UNIT = "15";
//	public static final String DELETE_UNIT = "16";
//	public static final String SEARCH_UNIT = "17";
//	public static final String LIST_UNIT = "18";
	
	//messages
	public static final String ERROR_MESSAGE_CLOSE_STATEMENT = "Houve um erro ao fechar a instrução de banco de dados.";
	public static final String ERROR_MESSAGE_CLOSE_RESULTSET = "Houve um erro ao fechar o conjunto de resultados do banco de dados.";
	public static final String ERROR_MESSAGE_UNEXPECTED_ERROR_ATRIBUTE = "Desculpe, houve um erro inesperado ao recuperar o atributo desejado.";
	public static final String ERROR_MESSAGE_MAPPER_ERROR = "Desculpe, houve um erro ao aplicar valores do formulário.";
	
}
