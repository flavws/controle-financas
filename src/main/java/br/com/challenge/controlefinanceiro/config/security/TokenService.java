package br.com.challenge.controlefinanceiro.config.security;

import br.com.challenge.controlefinanceiro.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${cf.jwt.expiration}") //injetar propriedades do application.properties
    private String expiration;

    @Value("${cf.jwt.secret}") //injetar propriedades do application.properties
    private String secret;



    public String gerarToken(Authentication authenticate) {

        Usuario log = (Usuario) authenticate.getPrincipal(); //método para recuperar usuário que está logado
        // o authenticate recebe um object, por isso fiz um cast para o usuário funcionar.

        Date hoje = new Date();

        Date dateExpiration = new Date(hoje.getTime() + Long.parseLong(expiration)); //fiquei com dúvida

        return Jwts.builder()
                .setIssuer("API de Controle Financeiro") //quem gerou o token
                .setSubject(log.getId().toString())//quem é o usuário autenticado que possui o token
                .setIssuedAt(hoje) //data de geração do token
                .setExpiration(dateExpiration)  //data de expiração do token
                .signWith(SignatureAlgorithm.HS256, secret) //criptografa o token
                .compact(); //compacta e transforma em string
    }
}
