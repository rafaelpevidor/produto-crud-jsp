/**
 * 
 */
package br.com.psystems.crud.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author developer
 *
 */
public class UnitMeasurement implements BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7410496823901739591L;
	
	public UnitMeasurement() {}
	
	public UnitMeasurement(String name) {
		this.name = name;
	}
	
	private Long id;
	private String name;

	/**
	 * @see br.com.psystems.crud.model.BaseEntity#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitMeasurement other = (UnitMeasurement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
