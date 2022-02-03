package br.com.challenge.controlefinanceiro.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErroFormDTO {

    private String campo;
    private String erro;

}