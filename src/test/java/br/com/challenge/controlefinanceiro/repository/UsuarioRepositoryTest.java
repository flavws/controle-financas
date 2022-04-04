package br.com.challenge.controlefinanceiro.repository;

import br.com.challenge.controlefinanceiro.model.Perfil;
import br.com.challenge.controlefinanceiro.model.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void deveCriarUsuarioNoRepository(){
        List<Perfil> perfils = new ArrayList<>();

        Usuario usuario = new Usuario(1L, "Flavia",
                "flavia@gmail.com", "123456", perfils);
        this.usuarioRepository.save(usuario);
        Assertions.assertThat(usuario.getId()).isNotNull();
        Assertions.assertThat(usuario.getNome()).isEqualTo("Flavia");
        Assertions.assertThat(usuario.getEmail()).isEqualTo("flavia@gmail.com");
        Assertions.assertThat(usuario.getPassword()).isEqualTo("123456");
        Assert.assertNotNull(usuarioRepository.save(usuario));
    }
}
