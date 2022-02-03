package br.com.challenge.controlefinanceiro.service;

import br.com.challenge.controlefinanceiro.dto.GastoCategoriaDTO;
import br.com.challenge.controlefinanceiro.dto.ResumoMensalDTO;
import br.com.challenge.controlefinanceiro.repository.DespesaRepository;
import br.com.challenge.controlefinanceiro.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ResumoService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private ReceitaRepository receitaRepository;


    public ResumoMensalDTO resumoMes(Integer ano, Integer mes) {

        BigDecimal valorTotalR = receitaRepository.valorMes(ano, mes).orElse(BigDecimal.ZERO);
        BigDecimal valorTotalD = despesaRepository.valorMes(ano, mes).orElse(BigDecimal.ZERO);
        BigDecimal saldoFinal = valorTotalR.subtract(valorTotalD);

        List<GastoCategoriaDTO> gastosCategoria = despesaRepository.valorAgrupado(ano, mes);

        return new ResumoMensalDTO(valorTotalR, valorTotalD, saldoFinal, gastosCategoria);
    }
}
