package com.antonio.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase encargada del Mapeo de la tabla PRODUCT de la base de datos. Las
 * anotaciones sustituyen al archivo xml, Product.hbm.xml.
 * 
 * @author avicentesh
 *
 */
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_ID")
	private long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "PRICE")
	private float price;
	@Column(name = "USER_ID")
	private int userId;
	@OneToMany(mappedBy = "primaryKey.product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<DetailBuy> detailBuy = new HashSet<DetailBuy>();

	public Product() {
	}

	public Product(long id, String name, String description, float price, int userId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Set<DetailBuy> getDetailBuy() {
		return detailBuy;
	}

	public void setDetailBuy(Set<DetailBuy> detailBuy) {
		this.detailBuy = detailBuy;
	}

	public void addDetailBuy(DetailBuy detailBuy) {
		this.detailBuy.add(detailBuy);
	}

}
