package br.com.alura.mvc.mudi.service;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private UserRepository userRepository;

	public void setAprovedStatus(Long pedidoId, Long ofertaId, Model model, Principal principal) {
		Optional<Pedido> maybePedido = this.pedidoRepository.findByIdJoinOfertas(pedidoId);

		Pedido pedido = maybePedido.get();
		Oferta oferta = null;
		Iterator<Oferta> iterator = pedido.getOfertas().iterator();
		while(iterator.hasNext()) {
			Oferta maybeOferta = iterator.next();
			if(ofertaId == maybeOferta.getId()) {
				oferta = maybeOferta;
				break;
			}
		}
		
		pedido.setStatus(StatusPedido.APROVADO);
		pedido.setValor(oferta.getValor());
		pedido.setDataEntrega(oferta.getDataEntrega());
		this.pedidoRepository.save(pedido);
		
		List<Pedido> pedidos = this.pedidoRepository.findAllByUsername(principal.getName());
		model.addAttribute("pedidos", pedidos);
		
	}

	public void addPedido(@Valid RequisicaoNovoPedido requisicaoNovoPedido) {
		Pedido pedido = requisicaoNovoPedido.toPedido();

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> maybeUser = this.userRepository.findById(username);
		User user = maybeUser.get();
		pedido.setUser(user);
		this.pedidoRepository.save(pedido);

	}

	public List<Pedido> getOrdersWaiting() {
		Sort sort = Sort.by("id").ascending();
		PageRequest paginacao = PageRequest.of(0, 5, sort);
		return this.pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao);
	}

	public void getPedidosEntregues(Model model, Principal principal) {
		Sort sort = Sort.by("dataEntrega").descending();
		PageRequest paginacao = PageRequest.of(0, 10, sort);
		List<Pedido> pedidos = this.pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao);
		model.addAttribute("pedidos", pedidos);

	}
}
