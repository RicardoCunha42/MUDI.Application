package br.com.alura.mvc.mudi.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import br.com.alura.mvc.mudi.dto.UserDto;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.Role;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.RoleRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;
@Service
public class UserService  {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public void saveUser(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(encoder.encode(userDto.getPassword()));
		Optional<Role> maybeRole = this.roleRepository.findById("ROLE_USER");
		user.setRoles(maybeRole.get());
		
		this.userRepository.save(user);
	}

	public void getPedidos(Model model, Principal principal) {
		List<Pedido> pedidos = this.pedidoRepository.findAllByUsername(principal.getName());
		model.addAttribute("pedidos", pedidos);
	}

	public void getPedidoByUserAndStatus(String status, Model model, Principal principal) {
		List<Pedido> pedidos = this.pedidoRepository
				.findByStatusAndUser(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		
	}

	public void getUserDto(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		
	}
}
