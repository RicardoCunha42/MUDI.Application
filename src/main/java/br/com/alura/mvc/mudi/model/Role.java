package br.com.alura.mvc.mudi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@Column(nullable = false, unique = true)
	private String description;
	@ManyToMany(mappedBy = "roles")
	private List<User> users = new ArrayList<>();

	public Role() {
		super();
	}

	public Role(String description, List<User> users) {
		super();
		this.description = description;
		this.users = users;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
