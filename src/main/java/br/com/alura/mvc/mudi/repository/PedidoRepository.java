package br.com.alura.mvc.mudi.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	@Cacheable(cacheNames = "last orders")
	List<Pedido> findByStatus(StatusPedido status, Pageable pageSort);
	
	@Query("select p from Pedido p join p.user u where u.username = :username")
	List<Pedido> findAllByUsername(@Param("username") String username);
	
	@Query("select p from Pedido p join p.user u where p.status = :status and u.username = :username")
	List<Pedido> findByStatusAndUser(@Param("status") StatusPedido status, @Param("username") String username);
	
	@Query("select p from Pedido p join fetch p.ofertas where p.id = :idPedido")
	Optional<Pedido> findByIdJoinOfertas(@Param("idPedido") Long idPedido);
	
}
