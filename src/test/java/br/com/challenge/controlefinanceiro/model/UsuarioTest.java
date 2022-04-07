package br.com.challenge.controlefinanceiro.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UsuarioTest {

    @Before
    public void before(){
        FixtureFactoryLoader.loadTemplates("br.com.challenge.controlefinanceiro.template");
    }

    @Test
    public void deveTestar0ConstrutorUsuario(){

        Usuario construtorTeste = Fixture.from(Usuario.class).gimme("model");
        Usuario construtorUsuario = new Usuario(construtorTeste.getId(), construtorTeste.getNome(), construtorTeste.getEmail(), construtorTeste.getSenha(), construtorTeste.getPerfis());

        System.out.println(construtorTeste.getPerfis());
        System.out.println(construtorTeste.getEmail());
        assertEquals(construtorUsuario.getId(), construtorTeste.getId());
        assertEquals(construtorUsuario.getNome(), construtorTeste.getNome());
        assertEquals(construtorUsuario.getEmail(), construtorTeste.getEmail());
        assertEquals(construtorUsuario.getSenha(), construtorTeste.getSenha());
        assertEquals(construtorUsuario.getPerfis(), construtorTeste.getPerfis());
    }

    @Test
    public void deveTestarGetSet(){
        Usuario usuarioTemplate = Fixture.from(Usuario.class).gimme("model");
        Usuario usuario = new Usuario();

        usuario.setId(usuarioTemplate.getId());
        usuario.setNome(usuarioTemplate.getNome());
        usuario.setEmail(usuarioTemplate.getEmail());
        usuario.setSenha(usuarioTemplate.getSenha());
        usuario.setPerfis(usuarioTemplate.getPerfis());

        Assert.assertEquals(usuario.getId(), usuarioTemplate.getId());
        Assert.assertEquals(usuario.getNome(), usuarioTemplate.getNome());
        Assert.assertEquals(usuario.getEmail(), usuarioTemplate.getEmail());
        Assert.assertEquals(usuario.getSenha(), usuarioTemplate.getSenha());
        Assert.assertEquals(usuario.getPerfis(), usuarioTemplate.getPerfis());
    }
}
