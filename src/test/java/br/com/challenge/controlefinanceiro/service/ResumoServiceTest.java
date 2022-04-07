package br.com.challenge.controlefinanceiro.service;

import br.com.challenge.controlefinanceiro.dto.GastoCategoriaDTO;
import br.com.challenge.controlefinanceiro.dto.ResumoMensalDTO;
import br.com.challenge.controlefinanceiro.model.Despesa;
import br.com.challenge.controlefinanceiro.model.Receita;
import br.com.challenge.controlefinanceiro.repository.DespesaRepository;
import br.com.challenge.controlefinanceiro.repository.ReceitaRepository;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
public class ResumoServiceTest {

    @MockBean
    DespesaRepository despesaRepository;

    @MockBean
    ReceitaRepository receitaRepository;

    @InjectMocks
    ResumoService resumoService;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.template");
    }

    @Test
    public void deveFazerResumoMensal(){
        Receita receita = Fixture.from(Receita.class).gimme("model");
        BigDecimal valorTotalR = receitaRepository.valorMes(receita.getData().getYear(), receita.getData().getMonthValue()).orElse(BigDecimal.ZERO);
        Despesa despesa = Fixture.from(Despesa.class).gimme("model");
        BigDecimal valorTotalD = despesaRepository.valorMes(despesa.getData().getYear(), despesa.getData().getMonthValue()).orElse(BigDecimal.ZERO);

        BigDecimal saldoFinal = valorTotalR.subtract(valorTotalD);

        List<GastoCategoriaDTO> gastosCategoria = despesaRepository.valorAgrupado(despesa.getData().getYear(), despesa.getData().getMonthValue());

        ResumoMensalDTO resumoMensalDTO = new ResumoMensalDTO(valorTotalR, valorTotalD, saldoFinal, gastosCategoria);


        Assert.assertEquals(saldoFinal, valorTotalD.subtract(valorTotalD));
        Assert.assertEquals(gastosCategoria, despesaRepository.valorAgrupado(despesa.getData().getYear(), despesa.getData().getMonthValue()));
        Assert.assertNotNull(resumoMensalDTO);
        Assert.assertNotNull(resumoService.resumoMes(despesa.getData().getYear(), despesa.getData().getMonthValue()));
    }
}
