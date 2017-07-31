package com.antonio.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Clase constructora de la Primary Key de la tabla intermedia DETAILS_BUY
 * @author Antonio Soto
 */
@Embeddable
public class DetailBuyId implements Serializable {

	private Buy buy;
	private Product product;

	@ManyToOne(cascade = CascadeType.ALL)
	public Buy getBuy() {
		return buy;
	}

	public void setBuy(Buy buy) {
		this.buy = buy;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
