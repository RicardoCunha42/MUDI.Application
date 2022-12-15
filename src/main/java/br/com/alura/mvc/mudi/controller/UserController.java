package br.com.alura.mvc.mudi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService userService;
	
	
	@GetMapping("pedido")
	public String home(Model model, Principal principal) {
		this.userService.getPedidos(model, principal);
		
		return "user/userHome";
	}
	
	@GetMapping("pedido/{status}")
	public String homeByStatus(@PathVariable String status, Model model, Principal principal) {
		this.userService.getPedidoByUserAndStatus(status, model, principal);
		
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
