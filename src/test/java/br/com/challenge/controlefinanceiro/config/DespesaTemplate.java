package br.com.challenge.controlefinanceiro.config;

import br.com.challenge.controlefinanceiro.model.Despesa;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DespesaTemplate implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Despesa.class).addTemplate("valid", new Rule(){
            {
                add("id", random(Long.class, range(1L, 100L)));
                add("descricao", "gastos");
                add("valor", "300.00");
                add("data", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
                add("categoria", "OUTROS");
            }
        });
        
    }
}
