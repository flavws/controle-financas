package br.com.challenge.controlefinanceiro.template;


import br.com.challenge.controlefinanceiro.model.Perfil;
import br.com.challenge.controlefinanceiro.model.Usuario;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;



public class PerfilTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Perfil.class).addTemplate("model", new Rule(){{
            add("id", random(Long.class, range(1L, 500L)));
            add("nome", random("Flavia", "Maria", "Livia"));
        }});
    }
}
