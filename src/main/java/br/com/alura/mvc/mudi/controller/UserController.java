package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRespository;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserRespository userRespository;
	@Autowired
	PedidoRepository pedidoRepository;
	
	
	@GetMapping("pedido")
	public String home(Model model, Principal principal) {
		List<Pedido> pedidos = this.pedidoRepository.findAllByUsername(principal.getName());
		model.addAttribute("pedidos", pedidos);
		return "user/userHome";
	}
	
	@GetMapping("pedido/{status}")
	public String homeByStatus(@PathVariable String status, Model model, Principal principal) {
		List<Pedido> pedidos = this.pedidoRepository
				.findByStatusAndUser(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "user/userHome";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:user/pedido";
	}

//	@GetMapping("/home")
//	public ModelAndView home() {
//		List<Pedido> pedidos = this.pedidoRepository.findAll();
//		ModelAndView mv = new ModelAndView("home");
//		mv.addObject("pedidos", pedidos);
//		return mv;
//	}

}
