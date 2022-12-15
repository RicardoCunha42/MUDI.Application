package br.com.alura.mvc.mudi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

	public void getReceivedOffers(Long pedidoId, Model model) {
		Optional<Pedido> maybePedido = this.pedidoRepository.findByIdJoinOfertas(pedidoId);
		Pedido pedido = maybePedido.get();
		List<Oferta> ofertas = pedido.getOfertas();
		
		model.addAttribute("ofertas", ofertas);
	}
}
