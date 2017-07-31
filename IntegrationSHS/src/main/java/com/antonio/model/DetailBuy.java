package com.antonio.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Clase Mapeadora de la tabla DETAIL_BUY, encargada de realizar automaticamente la creación 
 * de los registros entre las tablas BUY y PRODUCT.
 * @author Antonio Soto
 *
 */
@Entity
@Table(name = "DETAIL_BUY")
@AssociationOverrides({
	@AssociationOverride(name = "primaryKey.buy", joinColumns = @JoinColumn(name = "BUY_ID")),
	@AssociationOverride(name = "primaryKey.product", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
})
public class DetailBuy {

	private DetailBuyId primaryKey = new DetailBuyId();
	private int quantity;
	private float total;

	@EmbeddedId
	public DetailBuyId getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(DetailBuyId primaryKey) {
		this.primaryKey = primaryKey;
	}

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
	
	@Column(name = "QUANTITY")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "PRODUCT_TOTAL")
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}
