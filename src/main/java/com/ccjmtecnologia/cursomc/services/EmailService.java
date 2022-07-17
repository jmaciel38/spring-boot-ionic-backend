package com.ccjmtecnologia.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.ccjmtecnologia.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
}
