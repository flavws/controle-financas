package br.com.challenge.controlefinanceiro.controller;

import br.com.challenge.controlefinanceiro.config.security.TokenService;
import br.com.challenge.controlefinanceiro.dto.LoginDTO;
import br.com.challenge.controlefinanceiro.dto.TokenDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação do Token")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken dadosLogin = loginDTO.converter();
        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authenticate);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        }catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
