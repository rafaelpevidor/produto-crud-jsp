/**
 * 
 */
package br.com.psystems.crud.model.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * @author rafael.saldanha
 *
 */
public class Product implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6048749574413769644L;

	private Integer id;

	private Integer vendorId;

	private Vendor vendor;

	private String name;
	
	private String allotment;

	private Double amount;

	private Double purchasePrice;

	private Double salePrice;

	private String description;

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getVendorId() {
		return vendorId;
	}



	public void setVendorId(Integer fornecedorId) {
		this.vendorId = fornecedorId;
	}



	public Vendor getVendor() {
		return vendor;
	}



	public void setVendor(Vendor fornecedor) {
		this.vendor = fornecedor;
	}



	public String getName() {
		return name;
	}



	public void setName(String nome) {
		this.name = nome;
	}



	public Double getPurchasePrice() {
		return purchasePrice;
	}



	public void setPurchasePrice(Double valorDeCompra) {
		this.purchasePrice = valorDeCompra;
	}



	public Double getSalePrice() {
		return salePrice;
	}



	public void setSalePrice(Double valorDeVenda) {
		this.salePrice = valorDeVenda;
	}



	public String getAllotment() {
		return allotment;
	}



	public void setAllotment(String lote) {
		this.allotment = lote;
	}



	public Double getAmount() {
		return amount;
	}



	public void setAmount(Double quantidade) {
		this.amount = quantidade;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String descricao) {
		this.description = descricao;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((vendor == null) ? 0 : vendor.hashCode());
		result = prime * result
				+ ((vendorId == null) ? 0 : vendorId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((allotment == null) ? 0 : allotment.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((purchasePrice == null) ? 0 : purchasePrice.hashCode());
		result = prime * result
				+ ((salePrice == null) ? 0 : salePrice.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (allotment == null) {
			if (other.allotment != null)
				return false;
		} else if (!allotment.equals(other.allotment))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (purchasePrice == null) {
			if (other.purchasePrice != null)
				return false;
		} else if (!purchasePrice.equals(other.purchasePrice))
			return false;
		if (salePrice == null) {
			if (other.salePrice != null)
				return false;
		} else if (!salePrice.equals(other.salePrice))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}