package br.com.challenge.controlefinanceiro.models;

import br.com.challenge.controlefinanceiro.config.DespesaTemplate;
import br.com.challenge.controlefinanceiro.model.Despesa;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DespesaTest {

    @Before
    public void before(){
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.config");
    }

    @Test
    public void deveVerificarSeDespesaEstaNula(){
        Despesa despesa = Fixture.from(Despesa.class).gimme("valid");

        assertNotNull(despesa);
        assertNotNull(despesa.getId());
        assertNotNull(despesa.getDescricao());
        assertNotNull(despesa.getValor());
        assertNotNull(despesa.getData());
        assertNotNull(despesa.getCategoria());
    }

    @Test
    public void deveTestarOConstrutorDespesas(){

        Despesa esperado = Fixture.from(Despesa.class).gimme("valid");

        Despesa atual = new Despesa(esperado.getId(), esperado.getDescricao(), esperado.getValor(), esperado.getData(), esperado.getCategoria());

        assertEquals(atual.getId(), esperado.getId());
        assertEquals(atual.getDescricao(), esperado.getDescricao());
        assertEquals(atual.getValor(), esperado.getValor());
        assertEquals(atual.getData(), esperado.getData());
        assertEquals(atual.getCategoria(), esperado.getCategoria());
    }
}
