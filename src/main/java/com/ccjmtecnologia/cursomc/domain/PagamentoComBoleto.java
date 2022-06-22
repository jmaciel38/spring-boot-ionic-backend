package com.ccjmtecnologia.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.ccjmtecnologia.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento{

	private static final long serialVersionUID = 1L;

	private Date dueDate;
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
