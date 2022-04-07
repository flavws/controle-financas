package br.com.challenge.controlefinanceiro.template;

import br.com.challenge.controlefinanceiro.dto.GastoCategoriaDTO;
import br.com.challenge.controlefinanceiro.dto.ResumoMensalDTO;
import br.com.challenge.controlefinanceiro.model.Categoria;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ResumoMensalDtoTemplate implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(ResumoMensalDTO .class).addTemplate("dto", new Rule(){{
            add("valorTotalR", new BigDecimal("300"));
            add("valorTotalD", new BigDecimal("150"));
            add("saldoFinal", new BigDecimal("150"));
            add("gastoCategoria", has(1).of(GastoCategoriaDTO.class, "dto"));
        }});

}

}

