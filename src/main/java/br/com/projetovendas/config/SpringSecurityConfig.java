package br.com.projetovendas.config;

import br.com.projetovendas.service.ImplementsUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static br.com.projetovendas.constantes.Constantes.PERMISSAO_ADMINISTRADOR;
import static br.com.projetovendas.constantes.Constantes.PERMISSAO_USUARIO;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ImplementsUserDetailsService implementsUserDetailsService;

    @Autowired
    public SpringSecurityConfig(ImplementsUserDetailsService implementsUserDetailsService) {
        this.implementsUserDetailsService = implementsUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable();
        
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/dashboard/**").hasAnyRole(PERMISSAO_ADMINISTRADOR, PERMISSAO_USUARIO)
                .antMatchers("/categoria/**").hasAnyRole(PERMISSAO_ADMINISTRADOR)
                .antMatchers("/endereco/**").hasAnyRole(PERMISSAO_ADMINISTRADOR)
                .antMatchers("/pedido/**").hasAnyRole(PERMISSAO_ADMINISTRADOR)
                .antMatchers("/produto/**").hasAnyRole(PERMISSAO_ADMINISTRADOR)
                .antMatchers("/cliente/**").hasAnyRole(PERMISSAO_ADMINISTRADOR, PERMISSAO_USUARIO)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();

    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles(PERMISSAO_USUARIO)
                .and()
                .withUser("admin").password("password").roles(PERMISSAO_ADMINISTRADOR);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
