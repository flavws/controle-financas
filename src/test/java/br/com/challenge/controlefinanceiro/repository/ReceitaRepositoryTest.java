package br.com.challenge.controlefinanceiro.repository;

import br.com.challenge.controlefinanceiro.model.Receita;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReceitaRepositoryTest {

    @Autowired
    private ReceitaRepository repository;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.template");
    }

    @Test
    public void deveCriarReceitaNoRepository(){
        Receita receita = new Receita(1L, "Material escolar",
                new BigDecimal("200.00"), LocalDate.now());
        this.repository.save(receita);
        Assertions.assertThat(receita.getId()).isNotNull();
        Assertions.assertThat(receita.getDescricao()).isEqualTo("Material escolar");
        Assertions.assertThat(receita.getValor()).isEqualTo(new BigDecimal("200.00"));
        Assertions.assertThat(receita.getData()).isEqualTo(LocalDate.now());
        Assert.assertNotNull(repository.save(receita));
    }

    @Test
    public void deveDeletarReceitaNoRepository(){
        Receita receita = Fixture.from(Receita.class).gimme("model");
        this.repository.save(receita);
        repository.delete(receita);
        Assertions.assertThat(repository.findById(receita.getId())).isEmpty();
    }

    @Test
    public void deveAtualizarReceitaNoRepository(){
        Receita receita = Fixture.from(Receita.class).gimme("model");
        this.repository.save(receita);
        receita = new Receita(1L, "Pneu",
                new BigDecimal("280.00"), LocalDate.now().plusDays(20L));
        this.repository.save(receita);

        Assertions.assertThat(receita.getId()).isEqualTo(1L);
        Assertions.assertThat(receita.getDescricao()).isEqualTo("Pneu");
        Assertions.assertThat(receita.getValor()).isEqualTo(new BigDecimal("280.00"));
        Assertions.assertThat(receita.getData()).isEqualTo(LocalDate.now().plusDays(20L));
    }
}
