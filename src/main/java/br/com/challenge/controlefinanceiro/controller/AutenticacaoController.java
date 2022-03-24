package br.com.challenge.controlefinanceiro.controller;

import br.com.challenge.controlefinanceiro.config.security.TokenService;
import br.com.challenge.controlefinanceiro.dto.LoginDTO;
import br.com.challenge.controlefinanceiro.dto.TokenDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "API REST Autenticação")
@CrossOrigin(origins = "*")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @ApiOperation(value = "Autentica token")
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
