package br.com.challenge.controlefinanceiro.controller;

import br.com.challenge.controlefinanceiro.dto.ResumoMensalDTO;
import br.com.challenge.controlefinanceiro.service.ResumoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resumo")
@Tag(name = "Resumo financeiro mensal")
@CrossOrigin(origins = "*")
public class ResumoController {

    @Autowired
    private ResumoService resumoService;

    @GetMapping("/{ano}/{mes}")
    public ResumoMensalDTO resumoMes(@PathVariable Integer ano, @PathVariable Integer mes) {

        return resumoService.resumoMes(ano, mes);
    }
}
