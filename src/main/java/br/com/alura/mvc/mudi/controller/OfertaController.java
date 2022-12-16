package br.com.alura.mvc.mudi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.mvc.mudi.service.OfertaService;

@Controller
@RequestMapping("/oferta")
public class OfertaController {
	@Autowired
	OfertaService ofertaService;
	
	@GetMapping
	public String getFormularioParaOfertas(){
		return "ofertas/home";
	}
	
	@GetMapping("/receivedOffers")
	public String getReceivedOffers(@RequestParam (name = "pedidoId") Long pedidoId, Model model){
		this.ofertaService.getReceivedOffers(pedidoId, model);
		
		return "user/receivedOffers";
	}
}
