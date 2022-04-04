package br.com.challenge.controlefinanceiro.service;

import br.com.challenge.controlefinanceiro.dto.DespesaDTO;
import br.com.challenge.controlefinanceiro.model.Categoria;
import br.com.challenge.controlefinanceiro.model.Despesa;
import br.com.challenge.controlefinanceiro.repository.DespesaRepository;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@RunWith(SpringRunner.class)
public class DespesaServiceTest{

    @MockBean
    private DespesaRepository repository;

    @InjectMocks
    private DespesaService service;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.template");
    }


    @Test
    @DisplayName("deve encontrar todas as despesas")
    public void deveEncontrarTodasAsDespesa(){
        Despesa despesa = Fixture.from(Despesa.class).gimme("model");
        service.save(despesa);

        Assert.assertNotNull(service.findAll());
    }

    @Test
    @DisplayName("deve encontrar uma despesa especifica")
    public void deveEncontrarDespesaEspecifica(){
        Despesa despesa = Fixture.from(Despesa.class).gimme("model");
        service.save(despesa);

        Assert.assertNotNull(service.findId(despesa.getId()));
    }

    @Test
    @DisplayName("deve encontrar uma despesa pela descricao")
    public void deveEncontrarDespesaPorDescricao(){
        Despesa despesa = Fixture.from(Despesa.class).gimme("model");
        service.save(despesa);
        Assert.assertNotNull(service.findDescricao(despesa.getDescricao()));
    }

    @Test
    @DisplayName("deve criar uma despesa")
    public void deveCriarDespesa(){
        Despesa despesa = new Despesa(1L, "Mercado",
                new BigDecimal("150.00"), LocalDate.now(),
                Categoria.OUTROS);
        this.service.save(despesa);
        Assertions.assertThat(despesa.getId()).isNotNull();
        Assertions.assertThat(despesa.getDescricao()).isEqualTo("Mercado");
        Assertions.assertThat(despesa.getValor()).isEqualTo(new BigDecimal("150.00"));
        Assertions.assertThat(despesa.getData()).isEqualTo(LocalDate.now());
    }

    @Test
    public void deveDeletarDespesa(){
        Despesa despesa = Fixture.from(Despesa.class).gimme("model");
        this.service.save(despesa);
        service.delete(despesa.getId());
        Assertions.assertThat(service.findId(despesa.getId())).isEmpty();
    }

    @Test
    public void deveAtualizarDespesa(){
        Despesa despesa = Fixture.from(Despesa.class).gimme("model");
        this.service.save(despesa);
        despesa = new Despesa(1L, "Loja de Roupa",
                new BigDecimal("100.00"), LocalDate.now().plusDays(15L),
                Categoria.OUTROS);
        this.service.save(despesa);

        Assertions.assertThat(despesa.getId()).isEqualTo(1L);
        Assertions.assertThat(despesa.getDescricao()).isEqualTo("Loja de Roupa");
        Assertions.assertThat(despesa.getValor()).isEqualTo(new BigDecimal("100.00"));
        Assertions.assertThat(despesa.getData()).isEqualTo(LocalDate.now().plusDays(15L));
        Assertions.assertThat(despesa.getCategoria()).isEqualTo(Categoria.OUTROS);
    }
}
