package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRespository;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	UserRespository userRepository;

	@GetMapping("form")
	public String getForm(RequisicaoNovoPedido requisicaoNovoPedido) {
		return "pedido/form";
	}

	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicaoNovoPedido, BindingResult result) {
		if (result.hasErrors()) {
			return "pedido/form";
		}
		Pedido pedido = requisicaoNovoPedido.toPedido();
		
		String username =SecurityContextHolder.getContext().getAuthentication().getName();
		User user = this.userRepository.findByUsername(username);
		
		pedido.setUser(user);
		this.pedidoRepository.save(pedido);
		return "redirect:/home";
	}

}
