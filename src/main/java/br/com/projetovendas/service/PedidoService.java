package br.com.projetovendas.service;

import br.com.projetovendas.entity.Pedido;
import br.com.projetovendas.repository.PedidoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Pedido não encontrado", Pedido.class.getName()));
    }

    public Pedido findByCliente(Long id) {
        Pedido pedido = pedidoRepository.findByCliente(id);
        if (Objects.isNull(pedido)) {
            throw new ObjectNotFoundException("Pedido não encontrado", Pedido.class.getName());
        }
        return pedido;
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public void save(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

}
