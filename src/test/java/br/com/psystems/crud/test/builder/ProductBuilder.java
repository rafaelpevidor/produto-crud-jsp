/**
 * 
 */
package br.com.psystems.crud.test.builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.psystems.crud.model.Product;
import br.com.psystems.crud.model.UnitMeasurement;

/**
 * @author developer
 *
 */
public class ProductBuilder {

	public ProductBuilder() {
		product = new Product();
	}
	
	private Product product;
	
	public ProductBuilder setName(String name) {
		product.setName(name);
		return this;
	}
	
	public ProductBuilder setDescription(String description) {
		product.setDescription(description);
		return this;
	}
	
	public ProductBuilder setTags(Set<String> tags) {
		product.setTags(tags);
		return this;
	}
	
	public ProductBuilder setReferences(Set<Long> references) {
		product.setReferences(references);
		return this;
	}
	
	public ProductBuilder setTagsList(List<String> tags) {
		product.setTags(tags.stream().collect(Collectors.toSet()));
		return this;
	}
	
	public ProductBuilder setReferencesList(List<Long> references) {
		product.setReferences(references.stream().map(Long::new).collect(Collectors.toSet()));
		return this;
	}
	
	public ProductBuilder setMininumQuantity(BigDecimal mininumQuantity) {
		product.setMininumQuantity(mininumQuantity);
		return this;
	}
	
	public ProductBuilder setPrice(BigDecimal price) {
		product.setPrice(price);
		return this;
	}
	
	public ProductBuilder setOwnManufacturing(Boolean ownManufacturing) {
		product.setOwnManufacturing(ownManufacturing);
		return this;
	}
	
	public ProductBuilder setUnitMeasurement(UnitMeasurement unitMeasurement) {
		product.setUnitMeasurement(unitMeasurement);
		return this;
	}
	
	public Product build() {
		return product;
	}
}
