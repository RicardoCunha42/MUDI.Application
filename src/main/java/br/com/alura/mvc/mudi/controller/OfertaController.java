package br.com.alura.mvc.mudi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/oferta")
public class OfertaController {
	@Autowired
	PedidoRepository pedidoRepository;
	
	@GetMapping
	public String getFormularioParaOfertas(){
		return "ofertas/home";
	}
	
//	@GetMapping("/receivedOffers")
//	public String getFormularioParaOfertas(Model model){
//		Pedido pedido = (Pedido) model.getAttribute("pedido");
//		return "user/receivedOffers";
//	}
	
	@GetMapping("receivedOffers")
	public String getReceivedOffers(@RequestParam (name = "pedidoId") Long pedidoId, Model model){
		Optional<Pedido> maybePedido = this.pedidoRepository.findByIdJoinOfertas(pedidoId);
		Pedido pedido = maybePedido.get();
		List<Oferta> ofertas = pedido.getOfertas();
		
		model.addAttribute("ofertas", ofertas);
		
		return "user/receivedOffers";
	}
}
