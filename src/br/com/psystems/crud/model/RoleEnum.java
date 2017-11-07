/**
 * 
 */
package br.com.psystems.crud.model;

/**
 * @author developer
 *
 */
public enum RoleEnum {

	ADMIN(1,"Admin",""),
	USER(2,"User","")
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
