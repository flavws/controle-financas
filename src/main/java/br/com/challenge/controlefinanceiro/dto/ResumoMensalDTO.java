package br.com.challenge.controlefinanceiro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class ResumoMensalDTO {

    private BigDecimal valorTotalR;
    private BigDecimal valorTotalD;
    private BigDecimal saldoFinal;
    private List<GastoCategoriaDTO> gastoCategoria;

}
