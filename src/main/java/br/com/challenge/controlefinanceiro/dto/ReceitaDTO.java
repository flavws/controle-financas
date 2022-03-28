package br.com.challenge.controlefinanceiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import br.com.challenge.controlefinanceiro.repository.ReceitaRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import br.com.challenge.controlefinanceiro.model.Receita;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceitaDTO {

	@NotNull
	private Long id;

	@Schema(name = "Descrição da receita", example = "Mercado")
	@NotBlank
	private String descricao;

	@Schema(name = "Valor da receita", example = "145.00")
	@NotNull
	private BigDecimal valor;

	@Schema(name = "Data da receita", example = "15/02/2022")
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate data;

	public ReceitaDTO(Receita receita){
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
		this.id = receita.getId();
	}

	public Receita converter(Receita receita) {
		receita.setDescricao(descricao);
		receita.setValor(valor);
		receita.setData(data);

		return receita;
	}


	public Optional<Receita> atualizar(Long id, ReceitaRepository receitaRepository) {

		Optional<Receita> receita = receitaRepository.findById(id);

		receita.get().setDescricao(this.descricao);
		receita.get().setValor(this.valor);
		receita.get().setData(this.data);

		receitaRepository.save(receita.get());

		return receita;

	}
}
