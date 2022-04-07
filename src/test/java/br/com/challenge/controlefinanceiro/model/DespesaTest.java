package br.com.challenge.controlefinanceiro.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DespesaTest {

    @Before
    public void before(){
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.template");
    }

    @Test
    public void deveTestar0ConstrutorDespesas(){

        Despesa construtorTeste = Fixture.from(Despesa.class).gimme("model");
        Despesa construtorDespesa = new Despesa(construtorTeste.getId(), construtorTeste.getDescricao(), construtorTeste.getValor(), construtorTeste.getData(), construtorTeste.getCategoria());

        assertEquals(construtorDespesa.getId(), construtorTeste.getId());
        assertEquals(construtorDespesa.getDescricao(), construtorTeste.getDescricao());
        assertEquals(construtorDespesa.getValor(), construtorTeste.getValor());
        assertEquals(construtorDespesa.getData(), construtorTeste.getData());
        assertEquals(construtorDespesa.getCategoria(), construtorTeste.getCategoria());
    }

    @Test
    public void deveTestarNoArgsConstructorDespesas(){

        Despesa construtorDespesa = new Despesa();

        assertNotNull(construtorDespesa);
    }

    @Test
    public void deveTestarGetSet(){
        Despesa despesaTemplate = Fixture.from(Despesa.class).gimme("model");
        Despesa despesa = new Despesa();

        despesa.setId(despesaTemplate.getId());
        despesa.setDescricao(despesaTemplate.getDescricao());
        despesa.setValor(despesaTemplate.getValor());
        despesa.setData(despesaTemplate.getData());
        despesa.setCategoria(despesaTemplate.getCategoria());

        Assert.assertEquals(despesa.getId(), despesaTemplate.getId());
        Assert.assertEquals(despesa.getValor(), despesaTemplate.getValor());
        Assert.assertEquals(despesa.getDescricao(), despesaTemplate.getDescricao());
        Assert.assertEquals(despesa.getData(), despesaTemplate.getData());
        Assert.assertEquals(despesa.getCategoria(), despesaTemplate.getCategoria());
    }
}
