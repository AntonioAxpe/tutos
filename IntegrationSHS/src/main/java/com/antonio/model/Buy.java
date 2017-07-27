package com.antonio.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BUY")
public class Buy {

	@Id
	@GeneratedValue
	@Column(name = "BUY_ID")
	private int id;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "USER_ID")
	private int userId;
	@Column(name = "BUY_TOTAL")
	private int total;
	@Column(name = "DATE")
	private Date date;

	public Buy() {
	}

	public Buy(int id, String status, int userId, int total, Date date) {
		this.id = id;
		this.status = status;
		this.userId = userId;
		this.total = total;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getDate() {
		return date;
	}

	public void setDate() {
		this.date = new Date();
	}

}
