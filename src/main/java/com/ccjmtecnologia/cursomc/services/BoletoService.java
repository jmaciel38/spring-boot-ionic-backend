package com.ccjmtecnologia.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ccjmtecnologia.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void fillPagamentoComBoleto(PagamentoComBoleto pagto, Date demandMoment) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(demandMoment);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDueDate(cal.getTime());
	}
}
