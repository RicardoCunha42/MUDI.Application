package br.com.alura.mvc.mudi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.service.PedidoService;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private PedidoService pedidoService;
	
	
	@GetMapping()
	public String home(Model model, Principal principal) {
		this.pedidoService.getPedidosEntregues(model, principal);
		
		return "home";
	}
	
}
