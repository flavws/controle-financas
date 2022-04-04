package br.com.challenge.controlefinanceiro.service;

import br.com.challenge.controlefinanceiro.model.Receita;
import br.com.challenge.controlefinanceiro.repository.ReceitaRepository;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
public class ReceitaServiceTest {

    @MockBean
    private ReceitaRepository repository;

    @InjectMocks
    private ReceitaService service;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.template");
    }

    @Test
    @DisplayName("deve encontrar todas as receitas")
    public void deveEncontrarTodasAsReceitas(){
        Receita receita = Fixture.from(Receita.class).gimme("model");
        service.adicionar(receita);

        Assert.assertNotNull(service.findAll());
    }

    @Test
    @DisplayName("deve encontrar uma receita especifica")
    public void deveEncontrarReceitaEspecifica(){
        Receita receita = Fixture.from(Receita.class).gimme("model");
        service.adicionar(receita);

        Assert.assertNotNull(service.findId(receita.getId()));
    }

    @Test
    public void deveCriarReceitaNoRepository(){
        Receita receita = new Receita(1L, "Material escolar",
                new BigDecimal("200.00"), LocalDate.now());
        this.service.adicionar(receita);
        Assertions.assertThat(receita.getId()).isNotNull();
        Assertions.assertThat(receita.getDescricao()).isEqualTo("Material escolar");
        Assertions.assertThat(receita.getValor()).isEqualTo(new BigDecimal("200.00"));
        Assertions.assertThat(receita.getData()).isEqualTo(LocalDate.now());;
    }

    @Test
    public void deveDeletarReceitaNoRepository(){
        Receita receita = Fixture.from(Receita.class).gimme("model");
        this.service.adicionar(receita);
        service.delete(receita.getId());
        Assertions.assertThat(service.findId(receita.getId())).isEmpty();
    }

    @Test
    public void deveAtualizarReceitaNoRepository(){
        Receita receita = Fixture.from(Receita.class).gimme("model");
        this.service.adicionar(receita);
        receita = new Receita(1L, "Pneu",
                new BigDecimal("280.00"), LocalDate.now().plusDays(20L));
        this.service.adicionar(receita);

        Assertions.assertThat(receita.getId()).isEqualTo(1L);
        Assertions.assertThat(receita.getDescricao()).isEqualTo("Pneu");
        Assertions.assertThat(receita.getValor()).isEqualTo(new BigDecimal("280.00"));
        Assertions.assertThat(receita.getData()).isEqualTo(LocalDate.now().plusDays(20L));
    }
}
