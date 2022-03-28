package template;

import br.com.challenge.controlefinanceiro.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
public class ItemDespesa {

    private final Long id;

    private final String descricao;

    private final BigDecimal valor;

    private final LocalDate data;

    private final Categoria categoria;
}
