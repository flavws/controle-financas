package br.com.challenge.controlefinanceiro.controller;

import br.com.challenge.controlefinanceiro.dto.DespesaDTO;
import br.com.challenge.controlefinanceiro.model.Despesa;
import br.com.challenge.controlefinanceiro.repository.DespesaRepository;
import br.com.challenge.controlefinanceiro.service.DespesaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/despesa")
@Api(value = "API REST Despesas")
@CrossOrigin(origins = "*")
public class DespesaController {

    @Autowired
    DespesaService despesaService;

    @Autowired
    DespesaRepository despesaRepository;

    @PostMapping
    @ApiOperation(value = "Cria uma despesa")
    public Despesa salvar(@RequestBody @Valid Despesa despesa) {
        return despesaService.save(despesa);
    }

    @GetMapping
    @ApiOperation(value = "Lista todas as despesas")
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
    @ApiOperation(value = "Lista despesa por ID")
    public List<DespesaDTO> buscarId(@PathVariable("id") Long id){

        if(id == null) {
            return despesaService.findAll();
        }
        return despesaService.findId(id);
    }

    @GetMapping(value = "/descricao/{descricao}")
    @ApiOperation(value = "Lista despesa por descrição")
    public List<DespesaDTO> buscarDescricao(@PathVariable("descricao") String descricao){

        return despesaService.findDescricao(descricao);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza despesa")
// @Transactional commit
    public ResponseEntity<DespesaDTO> update(@PathVariable Long id, @RequestBody @Valid DespesaDTO despesaDTO){
        Optional<Despesa> despesa = despesaDTO.atualizar(id, despesaRepository);
        return ResponseEntity.ok(new DespesaDTO(despesa.get()));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta despesa")
    public ResponseEntity<?> delete(@PathVariable Long id){
        despesaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
