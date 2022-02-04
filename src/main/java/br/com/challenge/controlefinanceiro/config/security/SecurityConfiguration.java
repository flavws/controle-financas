package br.com.challenge.controlefinanceiro.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AutenticacaoService autenticacaoService;

    @Override
    public void configure(WebSecurity web) throws Exception {
    //configuração de requisições para arquivos (css, imagens, js, etc.) - não intercepta na parte de segurança
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //configurações de autenticação
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    //configurações de autorização

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/despesa").permitAll()
                .antMatchers(HttpMethod.GET, "/despesa/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
                //and - mais configurações / formLogin - o spring gera um formulario de autenticação
    }
}
