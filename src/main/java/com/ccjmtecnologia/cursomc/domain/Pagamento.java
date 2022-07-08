package com.ccjmtecnologia.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.ccjmtecnologia.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance( strategy = InheritanceType.JOINED )
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
public abstract class Pagamento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private Integer payState;

	@JsonIgnore
	@OneToOne
	@JoinColumn( name = "pedido_id" )
	@MapsId
	private Pedido demand;
	
	public Pagamento() {
		
	}

	public Pagamento(Integer id, EstadoPagamento payState, Pedido demand) {
		super();
		this.id = id;
		this.payState = (payState == null) ? null : payState.getCode();
		this.demand = demand;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getPayState() {
		return EstadoPagamento.toEnum(payState);
	}

	public void setPayState(EstadoPagamento payState) {
		this.payState = payState.getCode();
	}

	public Pedido getDemand() {
		return demand;
	}

	public void setDemand(Pedido demand) {
		this.demand = demand;
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
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}

	
}
