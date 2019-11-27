package br.com.projetovendas.repository;

import br.com.projetovendas.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Pedido findByCliente(Long id);

}
