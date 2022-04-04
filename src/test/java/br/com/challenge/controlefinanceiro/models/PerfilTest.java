package br.com.challenge.controlefinanceiro.models;

import br.com.challenge.controlefinanceiro.model.Perfil;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PerfilTest {

    @Before
    public void before(){
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.template");
    }

    @Test
    public void deveTestarGetSet(){
        Perfil perfilTemplate = Fixture.from(Perfil.class).gimme("model");
        Perfil perfil = new Perfil();

        perfil.setId(perfilTemplate.getId());
        perfil.setNome(perfilTemplate.getNome());

        Assert.assertEquals(perfil.getId(), perfilTemplate.getId());
        Assert.assertEquals(perfil.getNome(), perfilTemplate.getNome());
    }
}
