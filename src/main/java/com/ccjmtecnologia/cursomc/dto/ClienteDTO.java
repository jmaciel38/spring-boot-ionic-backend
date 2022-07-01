package com.ccjmtecnologia.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ccjmtecnologia.cursomc.domain.Cliente;

public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Obrigatory field.")
	@Length(min = 5, max = 120, message = "Length between 5 and 120 caracters.")
	private String name;
	@NotEmpty(message = "Obrigatory field.")
	@Email(message = "Invalid email.")
	private String email;

	public ClienteDTO() {
		
	}

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
