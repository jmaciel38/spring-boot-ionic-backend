package com.ccjmtecnologia.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccjmtecnologia.cursomc.domain.ItemPedido;
import com.ccjmtecnologia.cursomc.domain.PagamentoComBoleto;
import com.ccjmtecnologia.cursomc.domain.Pedido;
import com.ccjmtecnologia.cursomc.domain.enums.EstadoPagamento;
import com.ccjmtecnologia.cursomc.repositories.ItemPedidoRepository;
import com.ccjmtecnologia.cursomc.repositories.PagamentoRepository;
import com.ccjmtecnologia.cursomc.repositories.PedidoRepository;
import com.ccjmtecnologia.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository paymentRepo;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;

	public Pedido find(Integer id) {

		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found. Id: " + id + ", Type: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido save(Pedido obj) {
		obj.setId(null);
		obj.setMoment(new Date());
		obj.setClient(clienteService.find(obj.getClient().getId()));
		obj.getPayment().setPayState(EstadoPagamento.PENDENTE);
		obj.getPayment().setDemand(obj);
		if (obj.getPayment() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPayment();
			boletoService.fillPagamentoComBoleto(pagto, obj.getMoment());
		}
		obj = repo.save(obj);
		paymentRepo.save(obj.getPayment());
		for (ItemPedido ip : obj.getItems()) {
			ip.setDiscount(0.0);
			ip.setProduct(produtoService.find(ip.getProduct().getId()));
			ip.setPrice(ip.getProduct().getPrice());
			ip.setDemand(obj);
		}
		itemPedidoRepo.saveAll(obj.getItems());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
}
