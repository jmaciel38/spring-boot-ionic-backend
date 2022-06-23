package com.ccjmtecnologia.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemPedidoPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn( name = "pedido_id")
	private Pedido demand;

	@ManyToOne
	@JoinColumn( name = "produto_id")
	private Produto product;

	public Pedido getDemand() {
		return demand;
	}
	public void setDemand(Pedido demand) {
		this.demand = demand;
	}
	public Produto getProduct() {
		return product;
	}
	public void setProduct(Produto product) {
		this.product = product;
	}
	@Override
	public int hashCode() {
		return Objects.hash(demand, product);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoPK other = (ItemPedidoPK) obj;
		return Objects.equals(demand, other.demand) && Objects.equals(product, other.product);
	}

	
}
