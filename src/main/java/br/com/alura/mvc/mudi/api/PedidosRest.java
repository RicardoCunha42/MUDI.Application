package br.com.alura.mvc.mudi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.service.PedidoService;

@RestController
@RequestMapping("api/pedidos")
public class PedidosRest {
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping("aguardando")
	public List<Pedido> getPedidosAguardandoOfertas(){
		return this.pedidoService.getOrdersWaiting();
	}
}
