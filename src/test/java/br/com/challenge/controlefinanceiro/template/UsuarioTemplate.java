package br.com.challenge.controlefinanceiro.template;

import br.com.challenge.controlefinanceiro.model.Perfil;
import br.com.challenge.controlefinanceiro.model.Usuario;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class UsuarioTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Usuario.class).addTemplate("model", new Rule(){{
            add("id", random(Long.class, range(1L, 500L)));
            add("nome", random("Flavia", "Maria", "Livia"));
            add("email", "${nome}@gmail.com");
            add("senha", "123456");
            add("perfis", has(1).of(Perfil.class, "model"));
        }});
    }
}
