/**
 * 
 */
package br.com.psystems.crud.model;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * @author rafael.saldanha
 *
 */
public class Product implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6048749574413769644L;

	private Long id;
	private Long vendorId;
	private Long unitMeasurementId;
	private String name;
	private String description;
	private BigDecimal mininumQuantity;
	private Vendor vendor;
	private UnitMeasurement unitMeasurement;
	private List<String> tags;
	private List<Long> references;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getVendorId() {
		return vendorId;
	}
	
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Vendor getVendor() {
		if (null == vendor)
			vendor = new Vendor();
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
		setVendorId(null != vendor && null != vendor.getId()?vendor.getId():null);
	}
	
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<Long> getReferences() {
		return references;
	}

	public void setReferences(List<Long> references) {
		this.references = references;
	}
	
	public BigDecimal getMininumQuantity() {
		return mininumQuantity;
	}

	public void setMininumQuantity(BigDecimal mininumQuantity) {
		this.mininumQuantity = mininumQuantity;
	}

	public Long getUnitMeasurementId() {
		return unitMeasurementId;
	}

	public void setUnitMeasurementId(Long unitMeasurementId) {
		this.unitMeasurementId = unitMeasurementId;
	}

	public UnitMeasurement getUnitMeasurement() {
		return unitMeasurement;
	}

	public void setUnitMeasurement(UnitMeasurement unitMeasurement) {
		this.unitMeasurement = unitMeasurement;
		if (null != unitMeasurement && null != unitMeasurement.getId())
			setUnitMeasurementId(unitMeasurement.getId());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mininumQuantity == null) ? 0 : mininumQuantity.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((references == null) ? 0 : references.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
		result = prime * result + ((vendorId == null) ? 0 : vendorId.hashCode());
		result = prime * result + ((unitMeasurement == null) ? 0 : unitMeasurement.hashCode());
		result = prime * result + ((unitMeasurementId == null) ? 0 : unitMeasurementId.hashCode());
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
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mininumQuantity == null) {
			if (other.mininumQuantity != null)
				return false;
		} else if (!mininumQuantity.equals(other.mininumQuantity))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (references == null) {
			if (other.references != null)
				return false;
		} else if (!references.equals(other.references))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		if (unitMeasurement == null) {
			if (other.unitMeasurement != null)
				return false;
		} else if (!unitMeasurement.equals(other.unitMeasurement))
			return false;
		if (unitMeasurementId == null) {
			if (other.unitMeasurementId != null)
				return false;
		} else if (!unitMeasurementId.equals(other.unitMeasurementId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}