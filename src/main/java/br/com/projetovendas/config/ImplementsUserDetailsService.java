package br.com.projetovendas.config;

import br.com.projetovendas.entity.Cliente;
import br.com.projetovendas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Objects;

@Component
@Transactional
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
        return new User(cliente.getUsername(), cliente.getPassword(), true, true, true, true, cliente.getAuthorities());
    }
}
