package br.com.alura.mvc.mudi.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.service.PedidoService;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	@Autowired
	private PedidoService pedidoService;

	@GetMapping("form")
	public String getForm(RequisicaoNovoPedido requisicaoNovoPedido) {
		return "pedido/form";
	}

	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicaoNovoPedido, BindingResult result) {
		if (result.hasErrors()) {
			return "pedido/form";
		}
		this.pedidoService.addPedido(requisicaoNovoPedido);
		
		return "redirect:/home";
	}
	
	@GetMapping("setAprovedStatus")
	public String setAprovedStatus(@RequestParam (name = "pedidoId") Long pedidoId, @RequestParam (name = "ofertaId") Long ofertaId, 
			Model model, Principal principal) {
		this.pedidoService.setAprovedStatus(pedidoId, ofertaId, model,principal);
		
		return "user/userHome";
	}

}
