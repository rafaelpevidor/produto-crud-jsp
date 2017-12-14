/**
 * 
 */
package br.com.psystems.crud.model.enums;

/**
 * @author developer
 *
 */
public enum RoleEnum {

	ROOT(0,"root","super user"),
	ADMIN(1,"Admin","Admin"),
	USER(2,"User","User")
	;
	
	private RoleEnum(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	private Integer id;
	private String name;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
}
