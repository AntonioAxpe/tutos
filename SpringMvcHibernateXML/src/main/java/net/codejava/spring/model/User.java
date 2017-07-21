package net.codejava.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.ManyToMany;


/**
 * Clase de Mapeo de Hibernate. Sustituye al archivo User.hbm.xml.
 * 
 * @author avicentesh
 */
@Entity
@Table(name = "USERS")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private int id;
	private String username;
	private String password;
	private String email;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "USER_SPORT",	// Se especifica la tabla de unión
			joinColumns = { @JoinColumn(name = "USER_ID") }, // Indicando los campos que se unen
			inverseJoinColumns = { @JoinColumn(name = "SPORT_ID") })
	private Set<Sport> sports = new HashSet<Sport>();

	
	public int getId() {
		return id;
	}

	public Set<Sport> getSports() {
		return sports;
	}

	public void setSports(Set<Sport> sports) {
		this.sports = sports;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Tenga en cuenta que si los nombres de atributos de la clase de modelo son
	 * idénticos a los nombres de columnas en la base de datos, no es necesario
	 * especificar la correlación de columnas explícitamente. Es decir, no hace
	 * falta la anotación @Column.
	 */

	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
