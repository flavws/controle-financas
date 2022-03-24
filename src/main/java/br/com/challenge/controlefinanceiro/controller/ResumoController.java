package br.com.challenge.controlefinanceiro.controller;

import br.com.challenge.controlefinanceiro.dto.ResumoMensalDTO;
import br.com.challenge.controlefinanceiro.service.ResumoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resumo")
@Api(value = "API REST Resumo")
@CrossOrigin(origins = "*")
public class ResumoController {

    @Autowired
    private ResumoService resumoService;

    @GetMapping("/{ano}/{mes}")
    @ApiOperation(value = "Calcula o resumo financeiro do mês")
    public ResumoMensalDTO resumoMes(@PathVariable Integer ano, @PathVariable Integer mes) {

        return resumoService.resumoMes(ano, mes);
    }
}
