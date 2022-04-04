package br.com.challenge.controlefinanceiro.controller;

import br.com.challenge.controlefinanceiro.dto.DespesaDTO;
import br.com.challenge.controlefinanceiro.model.Despesa;
import br.com.challenge.controlefinanceiro.repository.DespesaRepository;
import br.com.challenge.controlefinanceiro.service.DespesaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesa")
@Tag(name = "Controle das despesas")
public class DespesaController {

    @Autowired
    DespesaService despesaService;

    @Autowired
    DespesaRepository despesaRepository;

    @PostMapping
    public Despesa salvar(@RequestBody @Valid Despesa despesa) {
        return despesaService.save(despesa);
    }

    @GetMapping
    public List<DespesaDTO> findAll(){
        return despesaService.findAll();
    }

    @GetMapping("/pagina")
    public Page<DespesaDTO> findDescricao(@RequestParam(required = false) String descricao,
                                       @PageableDefault(sort = "descricao", direction = Sort.Direction.ASC, page = 0) Pageable paginacao){

        Page<Despesa> despesa = despesaRepository.findByDescricaoDespesas(descricao, paginacao);
        return DespesaDTO.converter(despesa);
    }

    @GetMapping(value = "/{id}")
    public List<DespesaDTO> buscarId(@PathVariable("id") Long id){

        if(id == null) {
            return despesaService.findAll();
        }
        return despesaService.findId(id);
    }

    @GetMapping(value = "/descricao/{descricao}")
    public List<DespesaDTO> buscarDescricao(@PathVariable("descricao") String descricao){

        return despesaService.findDescricao(descricao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaDTO> update(@PathVariable Long id, @RequestBody @Valid DespesaDTO despesaDTO){
        return despesaService.update(id, despesaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return despesaService.delete(id);
    }
}
