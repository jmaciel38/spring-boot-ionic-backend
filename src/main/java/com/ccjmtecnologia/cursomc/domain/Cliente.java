package com.ccjmtecnologia.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ccjmtecnologia.cursomc.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String cpfOuCnpj;
	private Integer type;

	@JsonManagedReference
	@OneToMany( mappedBy = "client" )
	private List<Endereco> addresses = new ArrayList<>();

	@ElementCollection
	@CollectionTable( name = "TELEFONE" )
	private Set<String> phones = new HashSet<>();
	
	@OneToMany( mappedBy = "client" )
	private List<Pedido> demands = new ArrayList<>();

	public Cliente() {
		
	}

	public Cliente( Integer id, String name, String email, String cpfOuCnpj, TipoCliente type ) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.type = type.getCode();
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getType() {
		return TipoCliente.toEnum(type);
	}

	public void setType(TipoCliente type) {
		this.type = type.getCode();
	}

	public List<Endereco> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Endereco> addresses) {
		this.addresses = addresses;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	public List<Pedido> getDemands() {
		return demands;
	}

	public void setDemands(List<Pedido> demands) {
		this.demands = demands;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

}
