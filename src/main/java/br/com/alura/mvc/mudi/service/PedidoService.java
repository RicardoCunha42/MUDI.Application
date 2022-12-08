package br.com.alura.mvc.mudi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> setAprovedStatus(Long pedidoId) {
		Optional<Pedido> maybePedido = this.pedidoRepository.findById(pedidoId);
		if(maybePedido.isEmpty())
			return null;
		
		Pedido pedido = maybePedido.get();
		pedido.setStatus(StatusPedido.APROVADO);
		this.pedidoRepository.save(pedido);
		
		return this.pedidoRepository.findAll();
	}
}
