package com.ccjmtecnologia.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
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

	public double getTotal() {
		double sum = 0.0;
		for(ItemPedido ip: items) {
			sum += ip.getSubTotal();
		}
		return sum;
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

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido number: ");
		builder.append(getId());
		builder.append(" Moment: ");
		builder.append(sdf.format(getMoment()));
		builder.append(" Client: ");
		builder.append(getClient().getName());
		builder.append(" Payment State: ");
		builder.append(getPayment().getPayState().getDescription());
		builder.append("\nDetails: \n");
		for(ItemPedido ip: getItems()) {
			builder.append(ip.toString());
		}
		builder.append("Total value: ");
		builder.append(nf.format(getTotal()));
		return builder.toString();
	}

	

	
}
