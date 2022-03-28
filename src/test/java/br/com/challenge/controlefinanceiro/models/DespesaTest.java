package br.com.challenge.controlefinanceiro.models;

import br.com.challenge.controlefinanceiro.model.Categoria;
import br.com.challenge.controlefinanceiro.model.Despesa;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Before;
import org.junit.Test;
import template.ItemDespesa;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DespesaTest {

    @Before
    public void before(){
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.config");
    }

    @Test
    public void deveVerificarSeDespesaEstaNula(){
        var desp = ItemDespesa.builder().id(1L).descricao("Mercado").valor(new BigDecimal("150.00")).data(LocalDate.of(2022, 02, 15)).categoria(Categoria.valueOf("ALIMENTACAO")).build();

        assertNotNull(desp);

    }

    @Test
    public void deveTestarOConstrutorDespesas(){

        var construtorTeste = ItemDespesa.builder().id(1L).descricao("Mercado").valor(new BigDecimal("150.00")).data(LocalDate.of(2022, 02, 15)).categoria(Categoria.valueOf("ALIMENTACAO")).build();

        Despesa construtorDespesa = new Despesa(construtorTeste.getId(), construtorTeste.getDescricao(), construtorTeste.getValor(), construtorTeste.getData(), construtorTeste.getCategoria());

        assertEquals(construtorDespesa.getId(), construtorTeste.getId());
        assertEquals(construtorDespesa.getDescricao(), construtorTeste.getDescricao());
        assertEquals(construtorDespesa.getValor(), construtorTeste.getValor());
        assertEquals(construtorDespesa.getData(), construtorTeste.getData());
        assertEquals(construtorDespesa.getCategoria(), construtorTeste.getCategoria());
    }
}
