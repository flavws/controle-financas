package br.com.challenge.controlefinanceiro.template

import br.com.challenge.controlefinanceiro.dto.GastoCategoriaDTO
import br.com.challenge.controlefinanceiro.model.Categoria
import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader

import java.time.LocalDate

class GastoCategoriaDtoTemplate implements TemplateLoader{
    @Override
    void load() {
        Fixture.of(GastoCategoriaDTO .class).addTemplate("dto", new Rule(){{
            add("categoria", Categoria.OUTROS);
            add("valorTotal", new BigDecimal("600"));
        }});
    }
}
