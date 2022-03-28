package br.com.challenge.controlefinanceiro.controller;

import br.com.challenge.controlefinanceiro.dto.ReceitaDTO;
import br.com.challenge.controlefinanceiro.model.Receita;
import br.com.challenge.controlefinanceiro.repository.ReceitaRepository;
import br.com.challenge.controlefinanceiro.service.ReceitaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receita")
@Tag(name = "Controle das receitas")
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @Autowired
    ReceitaRepository receitaRepository;

    @PostMapping
    public Receita adicionar(@RequestBody @Valid Receita receita){
        return receitaService.adicionar(receita);
    }

    @GetMapping
    public List<ReceitaDTO> findAll(@RequestBody @Valid ReceitaDTO receitaDTO){
        return receitaService.findAll();
    }

    @GetMapping(value = "/{id}")
    public List<ReceitaDTO> buscarId(@PathVariable("id") Long id){

        if(id == null) {
            return receitaService.findAll();
        }
        return receitaService.findId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaDTO> update(@PathVariable Long id, @RequestBody @Valid ReceitaDTO receitaDTO){
        Optional<Receita> receita = receitaDTO.atualizar(id, receitaRepository);
        return ResponseEntity.ok(new ReceitaDTO(receita.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        receitaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
