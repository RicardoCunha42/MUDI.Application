package br.com.alura.mvc.mudi.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.service.OfertaService;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaRest {
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private OfertaService ofertaService;
	
	@PostMapping
	public ResponseEntity<Oferta> criaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao) {
		Oferta oferta = this.ofertaService.AddOffer(requisicao);

		return ResponseEntity.ok().body(oferta);
	}
}
