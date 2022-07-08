package com.ccjmtecnologia.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.ccjmtecnologia.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento{

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dueDate;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date payDate;
	
	public PagamentoComBoleto() {
		
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento payState, Pedido demand, Date dueDate, Date payDate) {
		super(id, payState, demand);
		this.dueDate = dueDate;
		this.payDate = payDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	
}
