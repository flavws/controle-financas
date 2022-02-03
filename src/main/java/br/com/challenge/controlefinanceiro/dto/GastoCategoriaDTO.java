package br.com.challenge.controlefinanceiro.dto;

import br.com.challenge.controlefinanceiro.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class GastoCategoriaDTO {

    private Categoria categoria;
    private BigDecimal valorTotal;
}
