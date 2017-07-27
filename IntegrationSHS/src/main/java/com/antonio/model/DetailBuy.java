package com.antonio.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "DETAIL_BUY")
@AssociationOverrides({ 
	@AssociationOverride(name = "primaryKey.buy", joinColumns = @JoinColumn(name = "BUY_ID")),
	@AssociationOverride(name = "primaryKey.product", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
})
public class DetailBuy {

	private DetailBuyId primaryKey = new DetailBuyId();
	@Column(name = "QUANTITY")
	private float quantity;
	@Column(name = "PRODUCT_TOTAL")
	private float total;

	@EmbeddedId // Implementa la clase del ID compuesto como Primary Key
	public DetailBuyId getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(DetailBuyId primaryKey) {
		this.primaryKey = primaryKey;
	}

	/* Objetos de asignación al Objeto DetailBuyId */
	@Transient
	public Buy getBuy() {
		return getPrimaryKey().getBuy();
	}
	
	public void setBuy(Buy buy) {
		getPrimaryKey().setBuy(buy);
	}
	
	@Transient
	public Product getProduct() {
		return getPrimaryKey().getProduct();
	}
	
	public void setProduct(Product product) {
		getPrimaryKey().setProduct(product);
	}
	
	/* Demás campos de la tabla */
	
	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}
