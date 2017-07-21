package net.codejava.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SPORT")
public class Sport {

	private int id;
	private String name;
	

	public Sport() {
	}

	public Sport(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue
	@Column(name = "SPORT_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
