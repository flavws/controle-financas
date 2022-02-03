package br.com.challenge.controlefinanceiro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.challenge.controlefinanceiro.exception.DespesaException;
import br.com.challenge.controlefinanceiro.model.Categoria;
import br.com.challenge.controlefinanceiro.model.Despesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.controlefinanceiro.dto.DespesaDTO;
import br.com.challenge.controlefinanceiro.repository.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository despesaRepository;

	public List<DespesaDTO> findAll(){
		return despesaRepository.findAll().stream().map(DespesaDTO::new).collect(Collectors.toList());
	}

	public Despesa save(Despesa despesa){
		Optional<Despesa> find = despesaRepository.findByDescricaoAndData(despesa.getDescricao(), despesa.getData());
		if (find.isPresent()){
			throw new DespesaException("Despesa já cadastrada no mês");
		}
		if (despesa.getCategoria() == null){
			despesa.setCategoria(Categoria.OUTROS);
		}
		despesa = despesaRepository.save(despesa);
		return despesa;
	}

	public List<DespesaDTO> findId(Long id) {
		Optional<Despesa> despesa = despesaRepository.findById(id);
		List<DespesaDTO> dto = despesa.stream().map(DespesaDTO::new).collect(Collectors.toList());
		return dto;
	}

	public List<DespesaDTO> findDescricao(String descricao){
		if (descricao == null){
			return despesaRepository.findAll().stream().map(DespesaDTO::new).collect(Collectors.toList());
		}
		return despesaRepository.findByDescricao(descricao).stream().map(DespesaDTO::new).collect(Collectors.toList());
	}



}
