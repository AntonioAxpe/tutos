package com.antonio.model;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Clase Primary Key de la tabla intermedia DETAILS_BUY (BUY - PRODUCT)
 * @author Antonio Soto
 */
@Embeddable // La anotación Embeddable indica que esta clase puede ser Integrada en otra entidad.
public class DetailBuyId {

	@ManyToOne(cascade = CascadeType.ALL)
	private Buy buy;
	@ManyToOne(cascade = CascadeType.ALL)
	private Product product;

	public DetailBuyId() {
	}

	public DetailBuyId(Buy buy, Product product) {
		this.buy = buy;
		this.product = product;
	}

	public Buy getBuy() {
		return buy;
	}

	public void setBuy(Buy buy) {
		this.buy = buy;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
