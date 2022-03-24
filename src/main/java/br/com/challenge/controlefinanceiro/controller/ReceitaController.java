package br.com.challenge.controlefinanceiro.controller;

import br.com.challenge.controlefinanceiro.dto.ReceitaDTO;
import br.com.challenge.controlefinanceiro.model.Receita;
import br.com.challenge.controlefinanceiro.repository.ReceitaRepository;
import br.com.challenge.controlefinanceiro.service.ReceitaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receita")
@Api(value = "API REST Receitas")
@CrossOrigin(origins = "*")
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @Autowired
    ReceitaRepository receitaRepository;

    @PostMapping
    @ApiOperation(value = "Cria uma receita")
    public Receita adicionar(@RequestBody @Valid Receita receita){
        return receitaService.adicionar(receita);
    }

    @GetMapping
    @ApiOperation(value = "Lista todas as receitas")
    public List<ReceitaDTO> findAll(@RequestBody @Valid ReceitaDTO receitaDTO){

        return receitaService.findAll(receitaDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza uma receita")
    public ResponseEntity<ReceitaDTO> update(@PathVariable Long id, @RequestBody @Valid ReceitaDTO receitaDTO){
        Optional<Receita> receita = receitaDTO.atualizar(id, receitaRepository);
        return ResponseEntity.ok(new ReceitaDTO(receita.get()));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta uma receita")
    public ResponseEntity<?> delete(@PathVariable Long id){
        receitaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
