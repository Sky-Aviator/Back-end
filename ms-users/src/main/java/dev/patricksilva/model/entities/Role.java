package dev.patricksilva.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.patricksilva.model.enums.ERole;

@Document(collection = "roles")
public class Role {
	@Id
	private String id;

	private ERole name;

	/**
	 * No args contructor.
	 */
	public Role() {

	}

	/**
	 * Role Contructor.
	 * 
	 * @param name
	 */
	public Role(ERole name) {
		this.name = name;
	}

	// Getters and Setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}