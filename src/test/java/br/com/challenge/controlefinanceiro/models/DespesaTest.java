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

        var esperado = ItemDespesa.builder().id(1L).descricao("Mercado").valor(new BigDecimal("150.00")).data(LocalDate.of(2022, 02, 15)).categoria(Categoria.valueOf("ALIMENTACAO")).build();

        Despesa atual = new Despesa(esperado.getId(), esperado.getDescricao(), esperado.getValor(), esperado.getData(), esperado.getCategoria());

        assertEquals(atual.getId(), esperado.getId());
        assertEquals(atual.getDescricao(), esperado.getDescricao());
        assertEquals(atual.getValor(), esperado.getValor());
        assertEquals(atual.getData(), esperado.getData());
        assertEquals(atual.getCategoria(), esperado.getCategoria());
    }
}
