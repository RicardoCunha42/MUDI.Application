package br.com.alura.mvc.mudi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;
import br.com.alura.mvc.mudi.service.PedidoService;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private UserRepository userRepository;
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
		Pedido pedido = requisicaoNovoPedido.toPedido();
		
		String username =SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> maybeUser = this.userRepository.findById(username);
		User user = maybeUser.get();
		pedido.setUser(user);
		this.pedidoRepository.save(pedido);
		return "redirect:/home";
	}
	
	@GetMapping("setAprovedStatus")
	public String setAprovedStatus(@RequestParam (name = "pedidoId") Long pedidoId, Model model) {
		List<Pedido> pedidos = this.pedidoService.setAprovedStatus(pedidoId);
		model.addAttribute("pedidos", pedidos);
		
		return "user/userHome";
	}

}
