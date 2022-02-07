package br.com.challenge.controlefinanceiro.dto;

import lombok.Getter;

@Getter
public class TokenDTO {

    private String token;
    private String tipo;

    public TokenDTO(String token, String tipo) {

    }
}
