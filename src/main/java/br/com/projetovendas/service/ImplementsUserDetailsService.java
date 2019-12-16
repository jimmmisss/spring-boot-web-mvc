package br.com.projetovendas.service;

import br.com.projetovendas.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ImplementsUserDetailsService implements UserDetailsService {

    private final ClienteService clienteService;

    @Autowired
    ImplementsUserDetailsService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Cliente cliente = clienteService.findByEmail(login);
        if (Objects.isNull(cliente)) {
            throw new UsernameNotFoundException("Cliente não encontrado");
        }
        return cliente;
    }
}
