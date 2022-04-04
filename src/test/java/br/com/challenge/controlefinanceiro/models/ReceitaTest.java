package br.com.challenge.controlefinanceiro.models;

import br.com.challenge.controlefinanceiro.model.Receita;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class ReceitaTest {

    @Before
    public void before(){
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.template");
    }

    @Test
    public void deveTestar0ConstrutorReceita(){

        Receita construtorTeste = Fixture.from(Receita.class).gimme("model");

        Receita construtorReceita = new Receita(construtorTeste.getId(), construtorTeste.getDescricao(), construtorTeste.getValor(), construtorTeste.getData());

        assertEquals(construtorReceita.getId(), construtorTeste.getId());
        assertEquals(construtorReceita.getDescricao(), construtorTeste.getDescricao());
        assertEquals(construtorReceita.getValor(), construtorTeste.getValor());
        assertEquals(construtorReceita.getData(), construtorTeste.getData());
    }

    @Test
    public void deveTestarGetSet(){
        Receita receitaTemplate = Fixture.from(Receita.class).gimme("model");;
        Receita receita = new Receita();

        receita.setId(receitaTemplate.getId());
        receita.setDescricao(receitaTemplate.getDescricao());
        receita.setValor(receitaTemplate.getValor());
        receita.setData(receitaTemplate.getData());

        Assert.assertEquals(receita.getId(), receitaTemplate.getId());
        Assert.assertEquals(receita.getValor(), receitaTemplate.getValor());
        Assert.assertEquals(receita.getDescricao(), receitaTemplate.getDescricao());
        Assert.assertEquals(receita.getData(), receitaTemplate.getData());
    }
}
