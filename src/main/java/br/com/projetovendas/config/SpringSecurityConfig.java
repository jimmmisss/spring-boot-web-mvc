package br.com.projetovendas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementsUserDetailsService implementsUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable();
        
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/dashboard/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/categoria/adicionar").hasAnyRole("ADMIN")
                .antMatchers("/categoria/listar").hasAnyRole("ADMIN", "USER")
                .antMatchers("/endereco/**").hasAnyRole("ADMIN")
                .antMatchers("/pedido/adicionar").hasAnyRole("ADMIN")
                .antMatchers("/pedido/listar").hasAnyRole("ADMIN", "USER")
                .antMatchers("/produto/adicionar").hasAnyRole("ADMIN")
                .antMatchers("/produto/listar").hasAnyRole("ADMIN", "USER")
                .antMatchers("/cliente/**").hasAnyRole("ADMIN", "USER")
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
        auth.userDetailsService(implementsUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
