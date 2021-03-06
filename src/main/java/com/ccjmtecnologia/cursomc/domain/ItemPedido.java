package com.ccjmtecnologia.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();

	private Double discount;
	private Integer qtd;
	private Double price;
	
	public ItemPedido() {
		
	}

	public ItemPedido(Pedido demand, Produto product, Double discount, Integer qtd, Double price) {
		super();
		id.setDemand(demand);
		id.setProduct(product);
		this.discount = discount;
		this.qtd = qtd;
		this.price = price;
	}

	public double getSubTotal() {
		return (price - discount) * qtd;
	}
	
	@JsonIgnore
	public Pedido getDemand() {
		return id.getDemand();
	}

	public void setDemand(Pedido demand) {
		 id.setDemand(demand);
	}

	public Produto getProduct() {
		return id.getProduct();
	}

	public void setProduct(Produto product) {
		 id.setProduct(product);
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduct().getName());
		builder.append(", Qtd: ");
		builder.append(getQtd());
		builder.append(", Unitary Price: ");
		builder.append(nf.format(getPrice()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}

	
}
