package br.com.projetovendas.service;

import br.com.projetovendas.entity.Cliente;
import br.com.projetovendas.repository.ClienteRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Cliente não encontrado", Cliente.class.getName()));
    }

    public Cliente findByEmail(String email) {
        return Optional.of(clienteRepository.findByEmail(email)).orElseThrow(()
                -> new ObjectNotFoundException("Busca de cliente por email não encontrado", Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        Cliente cliente = findById(id);
        clienteRepository.delete(cliente);
    }

}
