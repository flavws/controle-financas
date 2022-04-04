package br.com.challenge.controlefinanceiro.template;

import br.com.challenge.controlefinanceiro.dto.DespesaDTO;
import br.com.challenge.controlefinanceiro.model.Categoria;
import br.com.challenge.controlefinanceiro.model.Despesa;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaDtoTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(DespesaDTO.class).addTemplate("valid", new Rule(){{
            add("id", random(Long.class, range(1L, 500L)));
            add("descricao", random("Mercado", "Farmacia", "Cursos"));
            add("valor", new BigDecimal("150"));
            add("data", LocalDate.now());
            add("categoria", Categoria.OUTROS);
        }});
    }


}
