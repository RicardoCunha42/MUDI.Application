package br.com.alura.mvc.mudi.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.service.OfertaService;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaRest {
	@Autowired
	private OfertaService ofertaService;
	
	@PostMapping
	public ResponseEntity<Oferta> criaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao) {
		Oferta oferta = this.ofertaService.AddOffer(requisicao);

		return ResponseEntity.ok().body(oferta);
	}
}
