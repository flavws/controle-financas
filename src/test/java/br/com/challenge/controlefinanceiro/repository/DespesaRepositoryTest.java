package br.com.challenge.controlefinanceiro.repository;

import br.com.challenge.controlefinanceiro.model.Categoria;
import br.com.challenge.controlefinanceiro.model.Despesa;
import br.com.challenge.controlefinanceiro.service.DespesaService;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DespesaRepositoryTest {

    @Autowired
    private DespesaRepository despesaRepository;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.template");
    }

    @Test
    public void deveCriarDespesaNoRepository(){
        Despesa despesa = new Despesa(1L, "Mercado",
                    new BigDecimal("150.00"), LocalDate.now(),
                Categoria.OUTROS);
        this.despesaRepository.save(despesa);
        Assertions.assertThat(despesa.getId()).isNotNull();
        Assertions.assertThat(despesa.getDescricao()).isEqualTo("Mercado");
        Assertions.assertThat(despesa.getValor()).isEqualTo(new BigDecimal("150.00"));
        Assertions.assertThat(despesa.getData()).isEqualTo(LocalDate.now());
        Assertions.assertThat(despesa.getCategoria()).isEqualTo(Categoria.OUTROS);
        Assert.assertNotNull(despesaRepository.save(despesa));
    }

    @Test
    public void deveDeletarDespesaNoRepository(){
        Despesa despesa = Fixture.from(Despesa.class).gimme("model");
        this.despesaRepository.save(despesa);
        despesaRepository.delete(despesa);
        Assertions.assertThat(despesaRepository.findById(despesa.getId())).isEmpty();
    }

    @Test
    public void deveAtualizarDespesaNoRepository(){
        Despesa despesa = Fixture.from(Despesa.class).gimme("model");
        this.despesaRepository.save(despesa);
        despesa = new Despesa(1L, "Restaurante",
                new BigDecimal("100.00"), LocalDate.now().plusDays(15L),
                Categoria.ALIMENTACAO);
        this.despesaRepository.save(despesa);

        Assertions.assertThat(despesa.getId()).isEqualTo(1L);
        Assertions.assertThat(despesa.getDescricao()).isEqualTo("Restaurante");
        Assertions.assertThat(despesa.getValor()).isEqualTo(new BigDecimal("100.00"));
        Assertions.assertThat(despesa.getData()).isEqualTo(LocalDate.now().plusDays(15L));
        Assertions.assertThat(despesa.getCategoria()).isEqualTo(Categoria.ALIMENTACAO);
    }
}
