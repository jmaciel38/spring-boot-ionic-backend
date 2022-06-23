package com.ccjmtecnologia.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date moment;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "demand")
	private Pagamento payment;
	
	@ManyToOne
	@JoinColumn( name = "cliente_id" )
	private Cliente client;
	
	@ManyToOne
	@JoinColumn( name = "endereco_de_entrega_id" )
	private Endereco deliveryAddress;
	
	@OneToMany( mappedBy = "id.demand")
	private Set<ItemPedido> items = new HashSet<>();

	public Pedido() {
		
	}

	public Pedido(Integer id, Date moment, Cliente client, Endereco deliveryAddress) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
		this.deliveryAddress = deliveryAddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public Pagamento getPayment() {
		return payment;
	}

	public void setPayment(Pagamento payment) {
		this.payment = payment;
	}

	public Cliente getClient() {
		return client;
	}

	public void setClient(Cliente client) {
		this.client = client;
	}

	public Endereco getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Endereco deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Set<ItemPedido> getItems() {
		return items;
	}

	public void setItems(Set<ItemPedido> items) {
		this.items = items;
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

	
}
