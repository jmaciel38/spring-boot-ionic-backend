package com.ccjmtecnologia.cursomc.domain;

import javax.persistence.Entity;

import com.ccjmtecnologia.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento{

	private static final long serialVersionUID = 1L;

	private Integer numQuotas;
	
	public PagamentoComCartao() {
		
	}

	public PagamentoComCartao(Integer id, EstadoPagamento payState, Pedido demand, Integer numQuotas) {
		super(id, payState, demand);
		this.numQuotas = numQuotas;
	}

	public Integer getNumQuotas() {
		return numQuotas;
	}

	public void setNumQuotas(Integer numQuotas) {
		this.numQuotas = numQuotas;
	}

	
}
