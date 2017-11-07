/**
 * 
 */
package br.com.psystems.crud.model;

import java.math.BigDecimal;

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
	private String name;
	private String color;
	private Integer reference;
	private String lot;
	private BigDecimal amount;
	private BigDecimal price;
	private BigDecimal cost;
	private String description;
	private Vendor vendor;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getVendorId() {
		return vendorId;
	}
	
	public void setVendorId(Long fornecedorId) {
		this.vendorId = fornecedorId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String nome) {
		this.name = nome;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String cor) {
		this.color = cor;
	}
	
	public Integer getReference() {
		return reference;
	}
	
	public void setReference(Integer referencia) {
		this.reference = referencia;
	}
	
	public String getLot() {
		return lot;
	}
	
	public void setLot(String lote) {
		this.lot = lote;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal quantidade) {
		this.amount = quantidade;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal valorDeCompra) {
		this.price = valorDeCompra;
	}
	
	public BigDecimal getCost() {
		return cost;
	}
	
	public void setCost(BigDecimal valorDeVenda) {
		this.cost = valorDeVenda;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String descricao) {
		this.description = descricao;
	}
	
	public Vendor getVendor() {
		if (null == vendor)
			vendor = new Vendor();
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lot == null) ? 0 : lot.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((vendorId == null) ? 0 : vendorId.hashCode());
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
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
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
		if (lot == null) {
			if (other.lot != null)
				return false;
		} else if (!lot.equals(other.lot))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}