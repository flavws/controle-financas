package br.com.challenge.controlefinanceiro.service;

import br.com.challenge.controlefinanceiro.dto.ReceitaDTO;
import br.com.challenge.controlefinanceiro.exception.ReceitaException;
import br.com.challenge.controlefinanceiro.model.Receita;
import br.com.challenge.controlefinanceiro.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public List<ReceitaDTO> findAll(){
        return receitaRepository.findAll().stream().map(ReceitaDTO::new).collect(Collectors.toList());
    }

    public Receita adicionar(Receita receita){

        Optional<Receita> find = receitaRepository.findByDescricaoAndData(receita.getDescricao(), receita.getData());

        if(find.isPresent()){
            throw new ReceitaException("Receita já cadastrada no mês");
        }

        receita = receitaRepository.save(receita);

        return receita;
    }

    public List<ReceitaDTO> findId(Long id){
        Optional<Receita> receita = receitaRepository.findById(id);
        List<ReceitaDTO> dto = receita.stream().map(ReceitaDTO::new).collect(Collectors.toList());
        return dto;
    }
}
