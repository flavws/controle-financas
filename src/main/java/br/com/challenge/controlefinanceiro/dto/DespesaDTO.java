package br.com.challenge.controlefinanceiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.challenge.controlefinanceiro.model.Categoria;
import br.com.challenge.controlefinanceiro.model.Despesa;
import br.com.challenge.controlefinanceiro.repository.DespesaRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DespesaDTO {

	private Long id;

	@Schema(name = "Descrição", example = "Remédios", required = false)
	@NotBlank
	private String descricao;

	@Schema(name = "Valor gasto", example = "200.00")
	@NotNull
	private BigDecimal valor;

	@Schema(name = "Data da despesa", example = "22/02/2022")
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate data;

	@Schema(name = "Categoria da despesa", example = "SAUDE")
	private Categoria categoria;

	public DespesaDTO (Despesa despesa){
		this.id = despesa.getId();
		this.descricao = despesa.getDescricao();
		this.valor = despesa.getValor();
		this.data = despesa.getData();
		this.categoria = despesa.getCategoria();
	}



	public static Page<DespesaDTO> converter(Page<Despesa> despesa){
		return despesa.map(DespesaDTO::new);
	}



	public Optional<Despesa> atualizar(Long id, DespesaRepository despesaRepository) {

		Optional<Despesa> despesa = despesaRepository.findById(id);
		despesa.get().setDescricao(this.descricao);
		despesa.get().setValor(this.valor);
		despesa.get().setData(this.data);
		despesa.get().setCategoria(this.categoria);

		despesaRepository.save(despesa.get());

		return despesa;
	}
}