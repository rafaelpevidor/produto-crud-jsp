/**
 * 
 */
package br.com.psystems.crud.test.builder;

import br.com.psystems.crud.model.Vendor;

/**
 * @author developer
 *
 */
public class VendorBuilder {

	public VendorBuilder() {
		vendor = new Vendor();
	}

	private Vendor vendor;
	
	public VendorBuilder setName(String name) {
		vendor.setName(name);
		return this;
	}
	
	public VendorBuilder setDescription(String description) {
		vendor.setDescription(description);
		return this;
	}
	
	public Vendor build() {
		return vendor;
	}
}
