package br.com.alura.mvc.mudi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Service
public class OfertaService {
	@Autowired
	PedidoRepository pedidoRepository;
	
	public Oferta AddOffer (RequisicaoNovaOferta requisicao) {
		System.out.println(requisicao.getPedidoId() + " " + requisicao.getValor() + " " + requisicao.getValor() + " " + requisicao.getDataEntrega());
		Oferta oferta = requisicao.toOferta();
		Optional<Pedido> pedidoBuscado = this.pedidoRepository.findByIdJoinOfertas(requisicao.getPedidoId());
			if(pedidoBuscado.isEmpty()) {
				System.out.println("Vazio parça");
				return null;
			}
		Pedido pedido = pedidoBuscado.get();
		
		oferta.setPedido(pedido);
		pedido.getOfertas().add(oferta);
		this.pedidoRepository.save(pedido);
		return oferta;
	}
}
